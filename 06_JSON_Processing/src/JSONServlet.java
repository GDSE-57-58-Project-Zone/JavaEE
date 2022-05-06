import javax.json.*;
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
@WebServlet(urlPatterns = "/json")
public class JSONServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //id:C00-001, name:Ramal , address: Galle ,Salary : 10000

        resp.setContentType("application/json");

       /* //How to generate a single JSON object using JSON Processing
        JsonObjectBuilder objectB = Json.createObjectBuilder();
        objectB.add("id","C001");
        objectB.add("name","Ramal");
        objectB.add("address","Galle");
        objectB.add("salary",1000.00);
        JsonObject build = objectB.build();

        PrintWriter writer = resp.getWriter();
        writer.print(build);*/


        //How to send JSON Object Array with JSON Processing
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(); // create a json array

        JsonObjectBuilder objectB = Json.createObjectBuilder(); //Create json object one
        objectB.add("id", "C001");
        objectB.add("name", "Ramal");
        objectB.add("address", "Galle");
        objectB.add("salary", 1000.00);

        JsonObjectBuilder objectB2 = Json.createObjectBuilder();// Create json Object two
        objectB2.add("id", "C002");
        objectB2.add("name", "Sunimal");
        objectB2.add("address", "Panadura");
        objectB2.add("salary", 1000.00);

        arrayBuilder.add(objectB.build()); // add json object one to the json array
        arrayBuilder.add(objectB2.build()); // add json object two to the json array

        PrintWriter writer = resp.getWriter();
        writer.print(arrayBuilder.build()); // then print the jso array as the response

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
        System.out.println(id + " " + name + " " + address);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //read json array from a request
        JsonReader reader = Json.createReader(req.getReader());
        JsonArray jsonArray = reader.readArray();

        for (JsonValue jsonValue : jsonArray) {

            String customerID = jsonValue.asJsonObject().getString("id");
            String customerName = jsonValue.asJsonObject().getString("name");
            String customerAddress = jsonValue.asJsonObject().getString("address");
            String customerSalary = jsonValue.asJsonObject().getString("salary");
            System.out.println(customerID + " " + customerName + " " + customerAddress + " " + customerSalary);
        }


    }
}
