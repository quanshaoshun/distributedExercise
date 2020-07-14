package com.psfd.springboot.component;


import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author quanshaoshun
 * @date 2020/7/9 - 20:29
 */
public class MyLocaleResolver implements LocaleResolver {



    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("x");
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(l)) {
            String[] split = l.split("_");
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }


    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
