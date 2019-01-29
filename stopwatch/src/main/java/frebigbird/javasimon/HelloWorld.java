package frebigbird.javasimon;

import org.javasimon.SimonManager;
import org.javasimon.Split;
import org.javasimon.Stopwatch;

/**
 * Created by jhkim on 2018-08-28.
 */
public class HelloWorld {
    public static void main(String[] args) {
        Stopwatch stopwatch = SimonManager.getStopwatch("org.javasimon.examples.HelloWorld-stopwatch");

        Split split = stopwatch.start();
        System.out.println("Hello world, " + stopwatch);
        split.stop();

        // or this way using try-with-resource construction
        try (Split ignored = stopwatch.start()){
            System.out.println("Hello world (try-with-resource), " + stopwatch);
        }

        System.out.println("Result: " + stopwatch);
    }
}
