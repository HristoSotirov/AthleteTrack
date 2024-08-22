package com.athletetrack.jwtToken;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class FilterInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic jwtFilterRegistration = servletContext.addFilter("JwtFilter", new JwtFilter());
        jwtFilterRegistration.addMappingForUrlPatterns(null, false, "/*");
    }
}
