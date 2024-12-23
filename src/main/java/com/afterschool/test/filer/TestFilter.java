//package com.afterschool.test.filer;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@WebFilter("/*")
//@Component
//@Order(1)
//public class TestFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("TestFilter init");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("TestFilter doFilter");
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        filterChain.doFilter(servletRequest, servletResponse);
//        System.out.println("TestFilter doFilter two");
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("TestFilter destroy");
//    }
//}
