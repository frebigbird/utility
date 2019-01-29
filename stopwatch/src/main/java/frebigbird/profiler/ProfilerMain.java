package frebigbird.profiler;

import com.clarkware.Profiler;

/**
 * Created by jhkim on 2016-12-14.
 */
public class ProfilerMain {
    private static void method1() {
        Profiler.begin("method1");
        // do something
        Profiler.end("method1");
    }

    private static void method2() {
        Profiler.begin("method2");
        // do something
        Profiler.end("method2");
    }

    public static void main(String[] args) {
        method1();
        method2();

        Profiler.print(new java.io.PrintWriter(System.out));
        Profiler.clear();
    }
}
