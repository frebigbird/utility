package frebigbird.perf4j;

import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;

/**
 * Created by jhkim on 2018-08-31.
 */
public class GetPerformance {
    public static void main(String[] args) {
        new GetPerformance().testGetPerformance();
    }

    public void testGetPerformance() {
        final StopWatch watch = new LoggingStopWatch("addListeners");
        for (int i = 0; i < 100; i++) {
            sleep();
        }
        watch.stop("addListeners");

        watch.start("warmup");
        for (int i = 0; i < 100; ++i) {
            sleep();
        }
        watch.stop("warmup");

        watch.start("get");
        for (int i = 0; i < 100; ++i) {
            sleep();
        }
        watch.stop("get");
    }

    public void sleep() {
        try { Thread.sleep(1); } catch (InterruptedException e) {}
    }
}
