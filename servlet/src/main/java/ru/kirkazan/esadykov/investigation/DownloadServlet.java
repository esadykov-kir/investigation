package ru.kirkazan.esadykov.investigation;

import javax.mail.internet.MimeUtility;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author esadykov
 * @since 27.02.14 15:55
 */
@WebServlet (urlPatterns = "/download")
public class DownloadServlet implements Servlet
{
    @Override
    public void init(ServletConfig config) throws ServletException
    {
    }

    @Override
    public ServletConfig getServletConfig()
    {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
    {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setContentType("plain/text");
        response.setContentLength(1);
        response.setHeader("Content-Disposition", "attachment; " + "filename=\""
                + MimeUtility.encodeText("Текстовый файл", "UTF8", null) + "\"");
        response.getOutputStream().print(1);
    }

    @Override
    public String getServletInfo()
    {
        return this.getClass().getCanonicalName();
    }

    @Override
    public void destroy()
    {
    }
}
