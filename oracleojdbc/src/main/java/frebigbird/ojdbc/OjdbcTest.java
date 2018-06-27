package frebigbird.ojdbc;

import java.sql.*;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class OjdbcTest
{
    private String url = "jdbc:oracle:thin:@211.63.24.64:1522:supertalk";
    private String id = "shson";
        private String pw = "shson";

        public static void main( String[] args ) throws ClassNotFoundException, SQLException {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            new OjdbcTest().runTest();
        }

        private void runTest() throws SQLException {
        System.out.println("테스트 시작");
        sleep(5000);

        log("쿼리 실행");
        Connection conn = getConnection();
        PreparedStatement pstmt1 = executeQuery(conn);
        PreparedStatement pstmt2 = executeQuery(conn);

        sleep(5000);

        log("자원 해제");
        pstmt1.close();
        pstmt2.close();
        conn.close();

        sleep(5000);

        log("가비지컬렉션을 유발하는 어떤 작업 시작");
        doSometingCauseGC();

        log("종료");
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, id, pw);
    }

    private PreparedStatement executeQuery(Connection conn) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("select * from test");
        log("기본 패치 크기 : " + pstmt.getFetchSize());
        pstmt.setFetchSize(10000);
        ResultSet result = pstmt.executeQuery();
        return pstmt;
    }

    private void doSometingCauseGC() {
        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < 5000000; i++) {
            map.put("key" + i, "value" + i);
            if (i % 10000 == 0)
                sleep(100);
        }
    }

    private static void sleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { }
    }

    private void log(String s) {
        System.out.println(s);
    }
}
