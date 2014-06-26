package springmvc311;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author esadykov
 * @since 28.03.14 7:10
 */
@Controller
@RequestMapping("/recursive-json")
public class RecursiveJsonController {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public RecursiveObject request(@RequestParam(required = false) String param) {
        RecursiveObject ro = new RecursiveObject();
        ro.setSomething(param);
        //ro.setRo(ro);
        return ro;
    }
}