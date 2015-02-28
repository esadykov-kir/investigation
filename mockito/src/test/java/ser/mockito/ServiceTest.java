package ser.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

    @InjectMocks
    private Service service;

    @Mock
    private Repository repository;

    @Test
    public void testServiceMethodSuccess() throws Exception {

        when(repository.persistSomething()).thenReturn(null);

        service.serviceMethod();

        verify(repository).persistSomething();
    }

    @Test
    public void testServiceMethodFail() throws Exception {

        when(repository.persistSomething()).thenReturn(null);

        verify(repository).persistSomething();
    }

}