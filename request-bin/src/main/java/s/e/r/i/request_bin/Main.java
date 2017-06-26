package s.e.r.i.request_bin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 * @author esadykov
 * @since 09.11.2016 12:04
 */
@WebServlet("/")
public class Main implements Servlet {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";

        logger.debug(result);

        res.getWriter().print("ok " + (new Date()).toString());
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
