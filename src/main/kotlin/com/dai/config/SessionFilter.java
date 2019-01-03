package com.dai.config;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

@WebFilter(filterName = "sessionFilter", urlPatterns = {"/*"})
public class SessionFilter implements Filter {

    private final Logger logger = Logger.getLogger(SessionFilter.class);
    String[] includeUrls = new String[]{"login", "register"};

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);
        String uri = request.getRequestURI();
        if (uri.contains("login")){

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * @param uri
     * @Author: xxxxx
     * @Description: 是否需要过滤
     * @Date: 2018-03-12 13:20:54
     */
    private boolean isNeedFilter(String uri) {

        for (String includeUrl : includeUrls) {
            if (includeUrl.contains(uri)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void destroy() {

    }
}
