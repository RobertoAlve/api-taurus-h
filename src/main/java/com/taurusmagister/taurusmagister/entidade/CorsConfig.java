package com.taurusmagister.taurusmagister.entidade;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://sistema-taurus.herokuapp.com", "http://localhost:3000/", "http://54.173.177.100:3000/",
                        "http://taurus.zapto.org", "https://taurus.zapto.org")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT", "PATCH");
    }
}