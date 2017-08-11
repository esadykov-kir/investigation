package ser.i.cxf.war;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.Serializable;

/**
 * @author esadykov
 * @since 26.06.2017
 */
@Path("/service")
@Consumes("application/json")
@Produces("application/json")
public class RestService {

    @GET
    @Path("/resource/subresource")
    public Response getSomething(@BeanParam Criteria criteria) {
        return Response.ok(criteria).build();
    }

    public static class Criteria implements Serializable {
        @QueryParam("param1")
        private String param1;
        @MatrixParam("param2")
        private String param2;
        @MatrixParam("param2")
        private String param3;

        public Criteria() {
        }

        public String getParam1() {
            return param1;
        }

        public void setParam1(String param1) {
            this.param1 = param1;
        }

        public String getParam2() {
            return param2;
        }

        public void setParam2(String param2) {
            this.param2 = param2;
        }

        public String getParam3() {
            return param3;
        }

        public void setParam3(String param3) {
            this.param3 = param3;
        }
    }

}
