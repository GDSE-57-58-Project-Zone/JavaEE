package listeners;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/

@WebListener
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Context Initialized");
        // This method invokes suddenly just after the creation of Servlet Context
        //How to create the DBCP pool

        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUrl("jdbc:mysql://localhost:3306/company");
        bds.setUsername("root");
        bds.setPassword("sanu");
        bds.setMaxTotal(5); // how many connections
        bds.setInitialSize(5); // how many connection we should initialize

        ServletContext servletContext = servletContextEvent.getServletContext();// a common place for all servlet
        servletContext.setAttribute("bds", bds); // store the pool inside the Servlet Context
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Context Destroyed");
    }
}
