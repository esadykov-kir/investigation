package ru.kirkazan.esadykov.investigation;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author esadykov
 * @since 28.02.14 12:28
 */
@WebServlet(urlPatterns = "proxy")
public class ProxyServlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.service(req, resp);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
