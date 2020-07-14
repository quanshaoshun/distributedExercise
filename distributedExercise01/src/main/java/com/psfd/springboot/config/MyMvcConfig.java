package com.psfd.springboot.config;

import com.psfd.springboot.component.LoginHandlerlnterceptor;
import com.psfd.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author quanshaoshun
 * @date 2020/7/9 - 20:47
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {

            public void addViewControllers(ViewControllerRegistry registry) {
                //重定向
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
            }

            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerlnterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/webjars/**", "/asserts/**", "/index.html", "/", "/user/login", "/**/*.png", "/**/*.jpg",
                                "/**/*.jpeg", "/**/*.gif", "/**/fonts/*", "/**/*.svg");
            }
        };
        return webMvcConfigurer;
    }
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
