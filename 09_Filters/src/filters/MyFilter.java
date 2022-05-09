package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
//@WebFilter(urlPatterns = {"/customer","/item","/order"})
@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {

    public MyFilter() {
        System.out.println("Object Created from MyFilter");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("My Filter Initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //Before the request send
        System.out.println("First");

        filterChain.doFilter(servletRequest, servletResponse); // proceed request to the servlet

        //After the servlet response
        System.out.println("Second");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy method invoked");
    }
}
