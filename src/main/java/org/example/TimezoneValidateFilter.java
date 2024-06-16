package org.example;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.ZoneId;

@WebFilter(value = "/time")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String timezone = req.getParameter("timezone");

        if (timezone != null) {
            try{
                ZoneId.of(timezone.replace(" ","+"));
                chain.doFilter(req, res);
            }catch (DateTimeException dte){
                res.setStatus(400);
                res.setContentType("text/html");
                res.setCharacterEncoding("UTF-8");
                res.getWriter().write("Invalid Timezone");
                res.getWriter().close();
            }
        }else{
            chain.doFilter(req, res);
        }
    }
}
