package ser.mockito;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by esadykov on 28.02.2015.
 */
@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository repository;

    public void serviceMethod() {
        repository.persistSomething();
    }
}
