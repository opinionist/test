package com.afterschool.test.filer;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TestFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("TestFilter doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("TestFilter doFilter two");
    }

    @Override
    public void destroy() {
        System.out.println("TestFilter destroy");
    }
}
