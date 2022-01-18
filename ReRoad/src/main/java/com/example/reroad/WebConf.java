package com.example.reroad;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConf implements WebMvcConfigurer {
    //    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/static/", "classpath:/public/", "classpath:/",
//            "classpath:/resources/", "classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/" };


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // registry.addResourceHandler("/static/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);


        // 윈도우용 설정
        //registry.addResourceHandler("/upload/**").addResourceLocations("file:///C:/upload/");

        // 리눅스용 설정
        registry.addResourceHandler("/upload/**").addResourceLocations("file:///upload/");
    }
}
