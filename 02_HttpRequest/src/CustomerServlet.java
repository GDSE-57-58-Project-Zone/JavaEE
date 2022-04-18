import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
/*if someone send a request to localhost:8080/absd/customer url this class will handle that
 request and process a response according to the method which requested from the client*/

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    /*Request methods*/
    /*if customer receives get request this will execute*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        System.out.println("Request Method is "+method);
        PrintWriter writer = resp.getWriter();
        writer.write("Response from Customer Servlet Generated");
    }

    /*if customer receives post request this fill execute*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("Post Method Called from Customer Servlet");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("PUT Method called");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("Delete Method Called");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("OPTION method invoked");
    }
}
