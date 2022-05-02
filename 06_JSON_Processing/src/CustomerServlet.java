import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String option = req.getParameter("option");
            //The Media Type of the Content of the response
            resp.setContentType("application/json");
            //Initialize the connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "sanu");
            PrintWriter writer = resp.getWriter();
            switch (option) {
                case "SEARCH":

                    //write the code for customer search

                    break;
                case "GETALL":
                    ResultSet rst = connection.prepareStatement("select * from Customer").executeQuery();

                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(); // json array

                    // Access the records and generate a json object
                    while (rst.next()) {
                        String id = rst.getString(1);
                        String name = rst.getString(2);
                        String address = rst.getString(3);
                        double salary = rst.getDouble(4);

                        //{ id:C001,name:Kasun,address:Galle,salary:1000 }
                        //Create a json object and store values
                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("id", id);
                        objectBuilder.add("name", name);
                        objectBuilder.add("address", address);
                        objectBuilder.add("salary", salary);

                        // add the json object to the json array
                        arrayBuilder.add(objectBuilder.build());
                    }
                    //Generate a custom response with json
                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("status", 200);
                    response.add("message", "Done");
                    response.add("data", arrayBuilder.build());
                    writer.print(response.build());
                    break;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //This method can be used to save a customer
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      /*  resp.setStatus(404);//only status code- no error message
        resp.sendError(404); //status and relavent error message for 404*/

        String customerID = req.getParameter("customerID"); // name value from the input field
        String customerName = req.getParameter("customerName");
        String customerAddress = req.getParameter("customerAddress");
        String salary = req.getParameter("customerSalary");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "sanu");

            PreparedStatement pstm = connection.prepareStatement("Insert into Customer values(?,?,?,?)");
            pstm.setObject(1, customerID);
            pstm.setObject(2, customerName);
            pstm.setObject(3, customerAddress);
            pstm.setObject(4, salary);

            if (pstm.executeUpdate() > 0) {
                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_CREATED);//201
                response.add("status", 200);
                response.add("message", "Successfully Added");
                response.add("data", "");
                writer.print(response.build());
            }

        } catch (ClassNotFoundException e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", e.getLocalizedMessage());
            writer.print(response.build());

            resp.setStatus(HttpServletResponse.SC_OK); //200
            e.printStackTrace();
        } catch (SQLException throwables) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", throwables.getLocalizedMessage());
            writer.print(response.build());

            resp.setStatus(HttpServletResponse.SC_OK); //200
            throwables.printStackTrace();
        }
    }

    //This method can be used to delete a customer.
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request Received for delete");
        //if we send data from the application/x-www-form-urlencoded type, doDelete will not
        //catch values from req.getParameter(); that type is not supported with doDelete
        //but we can send data via Query String
        String customerID = req.getParameter("CusID");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "sanu");
            PreparedStatement pstm = connection.prepareStatement("Delete from Customer where id=?");
            pstm.setObject(1, customerID);

            if (pstm.executeUpdate() > 0) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 200);
                objectBuilder.add("data", "");
                objectBuilder.add("message", "Successfully Deleted");
                writer.print(objectBuilder.build());
            } else {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 400);
                objectBuilder.add("data", "Wrong Id Inserted");
                objectBuilder.add("message", "");
                writer.print(objectBuilder.build());
            }


        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            resp.sendError(500, e.getMessage());
            resp.setStatus(200);
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Error");
            objectBuilder.add("data", e.getLocalizedMessage());
            writer.print(objectBuilder.build());

        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            resp.sendError(500, throwables.getMessage());
            resp.setStatus(200);
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Error");
            objectBuilder.add("data", throwables.getLocalizedMessage());
            writer.print(objectBuilder.build());
        }

    }

    //This method can be used to update a customer
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //we have to get updated data from JSON format
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String customerID = jsonObject.getString("id");
        String customerName = jsonObject.getString("name");
        String customerAddress = jsonObject.getString("address");
        String customerSalary = jsonObject.getString("salary");
        PrintWriter writer = resp.getWriter();

        resp.setContentType("application/json");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "sanu");

            PreparedStatement pstm = connection.prepareStatement("Update Customer set name=?,address=?,salary=? where id=?");
            pstm.setObject(1, customerName);
            pstm.setObject(2, customerAddress);
            pstm.setObject(3, customerSalary);
            pstm.setObject(4, customerID);


            if (pstm.executeUpdate() > 0) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 200);
                objectBuilder.add("message", "Successfully Updated");
                objectBuilder.add("data", "");
                writer.print(objectBuilder.build());
            } else {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 400);
                objectBuilder.add("message", "Update Failed");
                objectBuilder.add("data", "");
                writer.print(objectBuilder.build());
            }

        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            resp.sendError(500, e.getMessage());
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Update Failed");
            objectBuilder.add("data", e.getLocalizedMessage());
            writer.print(objectBuilder.build());
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            resp.sendError(500, throwables.getMessage());
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Update Failed");
            objectBuilder.add("data", throwables.getLocalizedMessage());
            writer.print(objectBuilder.build());
        }
    }
}
