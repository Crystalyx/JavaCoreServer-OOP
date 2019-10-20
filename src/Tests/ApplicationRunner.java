package Tests;

import com.faceless.Application;
import com.faceless.HttpServer;

public class ApplicationRunner {
    public static void run(Application application) throws IllegalStateException, Exception {
        Thread thread = new Thread(() -> new Application().main(" ".split(" ")));
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Server didn't run");
        }
    }
}
