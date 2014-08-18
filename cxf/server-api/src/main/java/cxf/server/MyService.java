package cxf.server;

import javax.jws.WebService;

/**
 * @author esadykov
 * @since 14.08.2014 12:10
 */
@WebService
public interface MyService {
    public String getDate();
}
