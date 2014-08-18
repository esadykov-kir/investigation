package s.e.r.i.cxf;

import cxf.server.MyService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * @author esadykov
 * @since 14.08.2014 16:34
 */
public class MyServiceClient {

    public static void main (String args[]) throws InterruptedException{

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(MyService.class);
        //factory.setAddress("http://localhost:10000/MyService");
        factory.setAddress("http://localhost:8280/services/MyServiceProxy");
        MyService myService = (MyService) factory.create();
        for (int i = 1; i<=1000; i++){


            System.out.printf("%d\t%s\n", i, myService.getDate());
            Thread.sleep(100);
        }

    }
}
