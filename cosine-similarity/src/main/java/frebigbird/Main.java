package frebigbird;

import org.apache.commons.text.similarity.CosineSimilarity;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        CosineSimilarity cs = new CosineSimilarity();

        Map<CharSequence, Integer> left = new HashMap<>();
        left.put("하나", 1);
        left.put("둘", 1);

        Map<CharSequence, Integer> right = new HashMap<>();
        right.put("둘", 1);
        right.put("셋", 1);
        right.put("넷", 1);
        right.put("다섯", 1);
        right.put("여섯", 1);

        Double score = 0.0;
        for (int i = 0; i < 10000000; i++) {
            score = cs.cosineSimilarity(left, right);
        }
        System.out.println(score);
    }
}
