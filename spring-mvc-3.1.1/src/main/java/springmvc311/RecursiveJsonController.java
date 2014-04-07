package springmvc311;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author esadykov
 * @since 28.03.14 7:10
 */
@Controller
public class RecursiveJsonController
{
    @RequestMapping("/recursive-json")
    @ResponseBody
    public RecursiveObject request()
    {
        RecursiveObject ro = new RecursiveObject();
        //ro.setRo(ro);

        return ro;
    }

}


class RecursiveObject
{
    private RecursiveObject ro;
    private String something = "ro";

    RecursiveObject getRo()
    {
        return ro;
    }

    void setRo(RecursiveObject ro)
    {
        this.ro = ro;
    }

    String getSomething()
    {
        return something;
    }

    void setSomething(String something)
    {
        this.something = something;
    }
}
