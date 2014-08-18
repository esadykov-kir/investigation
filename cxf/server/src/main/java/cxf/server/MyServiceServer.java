package cxf.server;

import javax.xml.ws.Endpoint;

/**
 * @author esadykov
 * @since 14.08.2014 12:52
 */
public class MyServiceServer {

    protected MyServiceServer() throws Exception {
        // START SNIPPET: publish
        System.out.println("Starting Server");
        MyServiceImpl implementor = new MyServiceImpl();
        String address = "http://localhost:10000/MyService";
        Endpoint.publish(address, implementor);
        // END SNIPPET: publish
    }

    public static void main(String args[]) throws Exception {
        new MyServiceServer();
        System.out.println("Server ready...");

        Thread.sleep(60 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}
