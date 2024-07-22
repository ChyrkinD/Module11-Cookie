package org.example.servlet.controlers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.config.TemplateConfig;
import org.example.service.TimeService;
import org.thymeleaf.context.Context;
import java.io.IOException;

@WebServlet(name = "TimeServlet", urlPatterns = {"/", "/time"})
public class TimeServlet extends HttpServlet {
    private TemplateConfig  templateConfig;
    private TimeService timeService;

    public TimeServlet() throws ServletException {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        templateConfig = new TemplateConfig();
        timeService = new TimeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = null;
        String timezone = req.getParameter("timezone");
        if (timezone != null) {
            resp.addCookie(new Cookie("tz",timezone.replace(" ","+")));
        }else {
            timezone = getCookie(req.getCookies());
        }

        result = timeService.getDateTimeString(timezone);
        Context context= new Context();
        context.setVariable("time", result);
        templateConfig.process("index",context,resp);
    }

    private String getCookie(Cookie[] cookies) {
        String result = null;
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("tz")) {
                    result = cookie.getValue();
                }
            }
        }
        return result;
    }
}
