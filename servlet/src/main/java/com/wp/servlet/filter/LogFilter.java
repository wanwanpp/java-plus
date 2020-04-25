package com.wp.servlet.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LogFilter implements Filter {
    FilterConfig config;

    public void destroy() {
        this.config = null;
    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        // 获取ServletContext 对象，用于记录日志
        ServletContext context = this.config.getServletContext();
        //long before = System.currentTimeMillis();  
        System.out.println("before the log filter!");
        //context.log("开始过滤");  
        // 将请求转换成HttpServletRequest 请求  
        HttpServletRequest hreq = (HttpServletRequest) req;
        // 记录日志  
        System.out.println("Log Filter已经截获到用户的请求的地址:" + hreq.getServletPath());
//        context.log("Filter已经截获到用户的请求的地址: " + hreq.getServletPath());
        try {
            // Filter 只是链式处理，请求依然转发到目的地址。  
            chain.doFilter(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("after the log filter!");
        //long after = System.currentTimeMillis();  
        // 记录日志  
        //context.log("过滤结束");  
        // 再次记录日志  
        //context.log(" 请求被定位到" + ((HttpServletRequest) req).getRequestURI()  
        //      + "所花的时间为: " + (after - before));  
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("begin do the log filter!");
        this.config = config;
    }

}