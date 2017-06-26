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
    private static CacheManager cacheManager;

    public static void main(String[] args) {

        cacheManager = new CacheManager(ClassLoader.getSystemResourceAsStream("ehcache1.xml"));
//        CacheManager cacheManager = new CacheManager(ClassLoader.getSystemResourceAsStream("ehcache" + args[0] + ".xml"));

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Press enter or type 'quit': ");
            String command = scanner.nextLine();
            if ("quit".equals(command)) {
              //  cacheManager.shutdown();
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
        checkCache();
        cache.put(new Element(key, new Date().toString()));
//        for (int i = 1 ; i < 1000; i ++) {
//            cache.put(new Element(String.valueOf(i), new Date()));
//        }

    }

    private static void checkCache() {
        if (cache == null) {
            logger.debug("no cache");
            cacheManager.addCache("cache");
            cache = cacheManager.getCache("cache");
        }
    }

    private static void list() {
        checkCache();
        for (Object key : cache.getKeys()) {
            Element element = cache.get(key);
            logger.debug("key: {}, value: {}", key, element == null ? "<empty>" : element.getValue());
        }
    }
}

