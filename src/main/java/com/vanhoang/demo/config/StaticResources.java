package com.vanhoang.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class StaticResources implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/resources/vendor/**").addResourceLocations("classpath:/static/vendor/");
        registry.addResourceHandler("/resources/fonts/**").addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/resources/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/resources/img/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/resources/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/resources/svg/**").addResourceLocations("classpath:/static/svg/");
    }
}
