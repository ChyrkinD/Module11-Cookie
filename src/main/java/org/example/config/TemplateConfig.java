package org.example.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.io.IOException;
import java.util.Map;

public class TemplateConfig {
    TemplateEngine templateEngine;

    public TemplateConfig() throws ServletException {
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
    }

    private ITemplateResolver templateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("resources/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    public void process(String templateName, Context context, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        templateEngine.process(templateName,context, response.getWriter());
    }
}
