package s.e.r.i.trash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author esadykov
 * @since 08.10.2015 12:50
 */
public class LoggingTest {

    public static void  main(String[] args) {
        for (int i = 0; i<10000; i++) {
            new Thread(new Flooder(i)).start();
        }
    }

    static class Flooder implements Runnable {

        private final Logger logger;

        public Flooder(int i) {
            logger = LoggerFactory.getLogger("flooder-" + i);
        }

        @Override
        public void run() {
            int i = 0;
            while (1000>i++) {
                logger.debug("some message for debug");
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    logger.error("error on sleep", e);
                }
            }
        }
    }
}
