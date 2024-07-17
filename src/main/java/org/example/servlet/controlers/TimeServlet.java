package org.example.servlet.controlers;

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

@WebServlet(urlPatterns = {"/","/time"})
public class TimeServlet extends HttpServlet {
    private TemplateConfig  templateConfig = new TemplateConfig();
    private TimeService timeService = new TimeService();


    public TimeServlet() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        Context context = new Context();
        String timezone = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("timezone")) {
                    timezone = cookie.getValue();
                    break;
                }
            }
        }else {
                timezone = timeService.getDateTimeString(req.getParameter("timezone"));

        }
        context.setVariable("timezone", timezone);
        templateConfig.process("index",context,resp);
    }
}
