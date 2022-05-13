package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
//@WebFilter(urlPatterns = {"/customer","/item","/order"}) // filter only these servlets
@WebFilter(urlPatterns = "/*") //filter all requests
public class MyFilter implements Filter {

    public MyFilter() {
        System.out.println("Object Was Created From MyFilter Class");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Now MyFilter Class is processing Filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //Before the request send
        System.out.println("First");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;


        // without this line the request will not proceed to the servlet
        filterChain.doFilter(servletRequest, servletResponse); // proceed request to the servlet

        PrintWriter writer = servletResponse.getWriter();
        writer.write("Added from MyFilter");


        //
        res.addHeader("MyCompany", "IJSE");


        //After the servlet response
        System.out.println("Second");
    }

    @Override
    public void destroy() {
        System.out.println("My Filter Was Destroyed");
    }
}
