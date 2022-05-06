import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/hello")
public class MyServlet extends HttpServlet {

    //Life cycle of a servlet

    public MyServlet() {
        System.out.println("Onna object ekak haduna");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Onna mama Servlet ekak una");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Onna Get Request ekak labuna");
    }


    @Override
    public void destroy() {
        System.out.println("Onna mama mala");
    }
}
