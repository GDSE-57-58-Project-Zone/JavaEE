import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
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
@WebServlet(urlPatterns = "/json")
public class JSONServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //id:C00-001, name:Ramal , address: Galle ,Salary : 10000

        resp.setContentType("application/json");

        //How to generate a single JSON object using JSON Processing
        JsonObjectBuilder objectB = Json.createObjectBuilder();
        objectB.add("id","C001");
        objectB.add("name","Ramal");
        objectB.add("address","Galle");
        objectB.add("salary",1000.00);
        JsonObject build = objectB.build();


        PrintWriter writer = resp.getWriter();
        writer.print(build);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Method Invoked");

        /*ServletInputStream inputStream = req.getInputStream();

        int read;
        while ((read = inputStream.read()) != -1) {
            System.out.print((char)read);
        }*/

        //How to work with JSON processing
        //How to retrieve data from JSON request using JSON Processing Spec
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        System.out.println(id+" "+name+" "+address);


    }
}
