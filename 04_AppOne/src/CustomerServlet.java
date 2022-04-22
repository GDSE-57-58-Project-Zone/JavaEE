import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    // Json Formats
    // String json="{id:C001,name:Dasun,address:Galle,salary:1000}"; //single customer info
    // String jsonSet="[{id:C001,name:Dasun,address:Galle,salary:1000},{id:C001,name:Dasun,address:Galle,salary:1000}]"; //multiple customer info
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //The Media Type of the Content of the response
            resp.setContentType("application/json"); // MIME Types (Multipurpose Internet Mail Extensions)

            //meta data for response from headers
            resp.addHeader("Institute","IJSE");
            resp.addHeader("Course","GDSE");


            //Initialize the connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "sanu");
            ResultSet rst = connection.prepareStatement("select * from Customer").executeQuery();
            String allRecords = "";
            // Access the records and generate a json object
            while (rst.next()) {
                String id = rst.getString(1);
                String name = rst.getString(2);
                String address = rst.getString(3);
                double salary = rst.getDouble(4);

                //Convert one record for json
                String customer = "{\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"address\":\"" + address + "\",\"salary\":" + salary + "},";
                allRecords = allRecords + customer;
            }
            //Output of allRecords for now
            //{id:C001,name:Dasun,address:Galle,salary:1000},{id:C001,name:Dasun,address:Galle,salary:1000},

            //How it should be formatted
            //[{id:C001,name:Dasun,address:Galle,salary:1000},{id:C001,name:Dasun,address:Galle,salary:1000}]

           //After last customer object, ',' should be removed
            String finalJson = "[" + allRecords.substring(0,allRecords.length()-1) + "]";

            //Then print it as the response
            PrintWriter writer = resp.getWriter();
            writer.write(finalJson); //Possible response types -> //text //xml //html //json

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
