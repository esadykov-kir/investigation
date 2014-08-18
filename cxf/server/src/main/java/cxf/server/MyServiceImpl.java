package cxf.server;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.Date;

/**
 * @author esadykov
 * @since 14.08.2014 12:17
 */
@WebService
public class MyServiceImpl implements MyService {

    @Override

    public String getDate() {
        String s = (new Date()).toString();
        System.out.println(s);
        return s;
    }

}
