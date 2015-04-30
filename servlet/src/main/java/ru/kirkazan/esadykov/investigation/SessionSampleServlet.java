package ru.kirkazan.esadykov.investigation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author esadykov
 * @since 28.04.2015 18:04
 */
@WebServlet(urlPatterns = "/session")
public class SessionSampleServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestParam = req.getParameter("p");
        if (requestParam != null)
            req.getSession().setAttribute("p", new SampleBean(requestParam));

        SampleBean sessionParam = (SampleBean) req.getSession().getAttribute("p");
        resp.getWriter().append("param is ").append(sessionParam == null ? "null" : sessionParam.getValue());
        resp.getWriter().append("\n");
        resp.getWriter().append("session id is ").append(req.getSession().getId());
    }
}
