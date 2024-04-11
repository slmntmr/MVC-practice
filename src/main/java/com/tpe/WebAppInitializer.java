package com.tpe;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Database ve Hibernate ile ilgili konfigurasyonlar
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootContextConfig.class
        };
    }

    // Spring MVC config
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebMvcConfig.class
        };
    }

    // URL ve Servlet bağlantısı
    @Override
    protected String[] getServletMappings() {
        return new String[]{
                "/"
        };
    }
}
