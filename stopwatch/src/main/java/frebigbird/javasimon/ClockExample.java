package frebigbird.javasimon;

import org.javasimon.SimonManager;
import org.javasimon.Split;
import org.javasimon.clock.SimonClock;

/**
 * Created by jhkim on 2018-08-28.
 */
public class ClockExample {
    public static void main(String[] args) throws InterruptedException {
        String stopwatchName = "stopwatch";

        Split cpuSplit = Split.start(SimonClock.CPU);
        Split systemSplit = Split.start();

        System.out.println("cpuSplit = " + cpuSplit);
        System.out.println("systemSplit = " + systemSplit);

        for (int loop = 0; loop < 10; loop++) {
            for (int i = 0; i < 1000000; i++) {
                SimonManager.getStopwatch(stopwatchName);
            }
            Thread.sleep(200); // this should cause roughly 200 ms difference on each loop
            System.out.println("\nAfter iteration #" + loop);
            System.out.println("cpuSplit = " + cpuSplit);
            System.out.println("systemSplit = " + systemSplit);
        }
    }
}
