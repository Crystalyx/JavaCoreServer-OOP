import com.faceless.Application;

public class ApplicationRunner {
    public static void run(Application application) throws IllegalStateException, Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Application().main(" ".split(" "));
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Server didn't run");
        }
    }
}
