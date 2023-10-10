import java.sql.*;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {


        String url = "jdbc:mysql://localhost:3306/student_info";
        String username = "root";
        String password = "aditya";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //Loading the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //establish the connection
            connection = DriverManager.getConnection(url, username, password);

            statement = connection.createStatement();

            String sqlQuery = "SELECT first_name FROM student";
            resultSet = statement.executeQuery(sqlQuery);

            while(resultSet.next()){
                String firstName = resultSet.getString("first_name");
                System.out.println("Student Name: " + firstName);
            }
        }catch (ClassNotFoundException ex){
            System.err.println("MySQL JDBC driver not found.");
            ex.printStackTrace();
        }catch (SQLException e){
            System.err.println("Failed to connect to the database or execute the query");
            e.printStackTrace();
        }finally {
            try{
                if(resultSet != null){
                    resultSet.close();
                }if(statement != null){
                    statement.close();
                }if(connection != null){
                    statement.close();
                }
            }catch (SQLException e){
                System.err.println("Error closing resources.");
                e.printStackTrace();
            }
        }
    }
}