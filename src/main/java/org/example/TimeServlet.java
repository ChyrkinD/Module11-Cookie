package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String timezone = req.getParameter("timezone");
        String result = getDateTimeString(timezone);
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write(result);
        resp.getWriter().close();
    }

    private static String getDateTimeString(String timezone) {
        String result;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (timezone == null) {
            LocalDateTime dateTime= LocalDateTime.now();
            result = dateTime.format(formatter) + " UTC";
        }else {
            timezone = timezone.replace(" ","+");
            LocalDateTime dateTime = LocalDateTime.now(ZoneId.of(timezone));
            result = dateTime.format(formatter) + " " + timezone;
        }
        return result;
    }
}
