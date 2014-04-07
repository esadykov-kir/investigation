package i.spring.mvc;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

@Controller
@RequestMapping("/")
public class HelloController {

    //public static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    public static final Logger logger = Logger.getLogger(HelloController.class);

/*
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate2;
*/

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String handler(HttpServletRequest request, HttpServletResponse response)
    {

        logger.debug("some exception", new RuntimeException("some exception"));

/*
        jdbcTemplate.query("select 3", new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt(1);
            }
        });

        jdbcTemplate2.query("select 4", new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt(1);
            }
        });
*/
        RequestDispatcher rd = request.getRequestDispatcher("/another-path");

        final ByteArrayOutputStream os = new ByteArrayOutputStream(100000);
        final PrintWriter pw = new PrintWriter(os);

        final ServletOutputStream sos = new ServletOutputStream() {
            @Override
            public void write(int b) throws IOException {
                os.write(b);
            }
        };
        try
        {
            rd.forward(request, new HttpServletResponseWrapper(response)
            {
                @Override
                public PrintWriter getWriter() throws IOException {
                    return pw;
                }

                @Override
                public ServletOutputStream getOutputStream() throws IOException {
                    return sos;
                }
            });
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        System.out.println("---");
        System.out.println(new String(os.toByteArray()));
        System.out.println("---");
        return "hello";
	}


    @RequestMapping(value="/another-path", method = RequestMethod.GET)
    @ResponseBody
    public String anotherHandler()
    {
        return "another-handler";
    }

}