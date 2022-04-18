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

//Wild Card Mapping // /items/I001/I002
@WebServlet(urlPatterns = "/items/*")
public class WildCardMappingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Wild Mapping Spec Invoked");
    }
}
