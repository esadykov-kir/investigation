package s.e.r.i.ehcache.cluster;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Scanner;

/**
 * @author esadykov
 * @since 20.05.2015 14:20
 */
public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private static Cache cache;

    public static void main(String[] args) {

        CacheManager cacheManager = new CacheManager(ClassLoader.getSystemResourceAsStream("ehcache" + args[0] + ".xml"));

        cache = cacheManager.getCache("cache");

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Press enter or type 'quit': ");
            String command = scanner.nextLine();
            if ("quit".equals(command)) {
                cacheManager.shutdown();
                System.exit(0);

            }
            if (!command.isEmpty()) {
                updateCache(command);
            }
            list();
        }
        while (true);
    }

    private static void updateCache(String key) {
        cache.put(new Element(key, new Date().toString()));
    }

    private static void list() {
        for (Object key : cache.getKeys()) {
            Element element = cache.get(key);
            logger.debug("key: {}, value: {}", key, element == null ? "<empty>" : element.getValue());
        }
    }
}

