package spectra.poi.word;

import org.apache.commons.cli.*;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 워드 파일 내의 대상 치환 문자열을 일치하는 프로퍼티의 값으로 치환한다.
 */
public class WordTemplate {
    /**
     * 파싱된 인자 클래스
     */
    private static class ParsedArgs {
        private static String templateFile = "template.docx";
        private static String outputFile = "output.docx";
        private static String propertyFile = "template.properties";

        public static void parse(String[] args) {
            CommandLineParser parser = new DefaultParser();

            Options options = new Options();

            options.addOption(Option.builder("t").longOpt("template").desc("템플릿 워드 파일 이름").hasArg().required().build());
            options.addOption(Option.builder("o").longOpt("output").desc("출력 워드 파일 이름 (기본값: output.docs)").hasArg().build());
            options.addOption(Option.builder("p").longOpt("property").desc("치환 프로퍼티 파일 이름").hasArg().required().build());

            try {
                CommandLine line = parser.parse(options, args);

                templateFile = line.getOptionValue("template");
                outputFile = line.getOptionValue("output", outputFile);
                propertyFile = line.getOptionValue("property");
            } catch (ParseException e) {
                // automatically generate the help statement
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("WordTemplate", options);
                System.exit(-1);
            }
        }
    }

    /**
     * 설정을 파일로 분리하려면 이 클래스를 확장해야 한다.
     */
    private static class Configuration {
        private static String STAG = "%";
        private static String ETAG = "%";
    }

    /**
     * main
     */
    public static void main(String[] args) throws IOException {
        ParsedArgs.parse(args);
        new WordTemplate();
    }

    /**
     * WordTemplate
     */
    public WordTemplate() throws IOException {
        XWPFDocument doc = null;
        FileOutputStream out = null;
        try {
            doc = new XWPFDocument(new FileInputStream(ParsedArgs.templateFile));
            out = new FileOutputStream(ParsedArgs.outputFile);

            replaceAllParagraphs(doc, loadPropertiesAndToMap(ParsedArgs.propertyFile));

            doc.write(out);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (out != null) out.close();
                if (doc != null) doc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 프로퍼티 파일을 읽어서 map으로 변환한다.
     */
    private Map<String, String> loadPropertiesAndToMap(String file) throws IOException {
        Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");

        Properties prop = new Properties();
        prop.load(reader);
        Map<String, String> map = new HashMap<String, String>();

        for (String name : prop.stringPropertyNames()) {
            map.put(Configuration.STAG + name + Configuration.ETAG, prop.getProperty(name));
        }
        return map;
    }

    /**
     * 본문, 테이블, Header, Footer 내의 Paragraphs 들을 대상으로 치환을 한다.
     */
    private void replaceAllParagraphs(XWPFDocument document, Map<String, String> map) {
        // replace paragraphs
        replaceParagraphs(document.getParagraphs(), map);

        // replace tables
        for (XWPFTable table : document.getTables()) {
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    List<XWPFParagraph> paragraphListTable = cell.getParagraphs();
                    replaceParagraphs(paragraphListTable, map);
                }
            }
        }

        // replace header and footer
        XWPFHeaderFooterPolicy policy = document.getHeaderFooterPolicy();

        // headers
        for (XWPFHeader header : document.getHeaderList()) {
            replaceParagraphs(header.getParagraphs(), map);

            for (XWPFTable table : header.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        replaceParagraphs(cell.getParagraphs(), map);
                    }
                }
            }
        }

        // footers
        for (XWPFFooter footer : document.getFooterList()) {
            replaceParagraphs(footer.getParagraphs(), map);

            for (XWPFTable table : footer.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        replaceParagraphs(cell.getParagraphs(), map);
                    }
                }
            }
        }
    }

    /**
     * Paragraph 내 에서 map의 키와 일치할 경우 map의 값으로 치환한다.
     */
    private long replaceParagraphs(List<XWPFParagraph> paragraphs, Map<String, String> map) {
        long count = 0;
        for (XWPFParagraph paragraph : paragraphs) {
            List<XWPFRun> runs = paragraph.getRuns();

            for (Map.Entry<String, String> entry : map.entrySet()) {
                String find = entry.getKey();
                String repl = entry.getValue();
                TextSegement found = paragraph.searchText(find, new PositionInParagraph());
                if (found != null) {
                    count++;
                    if (found.getBeginRun() == found.getEndRun()) {
                        // whole search string is in one Run
                        XWPFRun run = runs.get(found.getBeginRun());
                        String runText = run.getText(run.getTextPosition());
                        String replaced = runText.replace(find, repl);
                        run.setText(replaced, 0);
                    } else {
                        // The search string spans over more than one Run
                        // Put the Strings together
                        StringBuilder b = new StringBuilder();
                        for (int runPos = found.getBeginRun(); runPos <= found.getEndRun(); runPos++) {
                            XWPFRun run = runs.get(runPos);
                            b.append(run.getText(run.getTextPosition()));
                        }
                        String connectedRuns = b.toString();
                        String replaced = connectedRuns.replace(find, repl);

                        // The first Run receives the replaced String of all connected Runs
                        XWPFRun partOne = runs.get(found.getBeginRun());
                        partOne.setText(replaced, 0);
                        // Removing the text in the other Runs.
                        for (int runPos = found.getBeginRun() + 1; runPos <= found.getEndRun(); runPos++) {
                            XWPFRun partNext = runs.get(runPos);
                            partNext.setText("", 0);
                        }
                    }
                }
            }
        }
        return count;
    }
}
