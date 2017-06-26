package ser.i.sch;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author esadykov
 * @since 15.04.2016 11:25
 */
@Service
public class TestService {

    @Cacheable
    public String get(Integer id) {
        System.out.println("evaluate for " + id);
        return String.valueOf(id * id);
    }

    @CacheEvict
    public void clear (Integer id) {
        System.out.println("clear cache for " + id);
    }
}
