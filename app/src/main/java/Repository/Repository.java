package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Repository {
    static String jdbcURL = "jdbc:postgresql://localhost:5432/postgres"; // TODO: Replace
    static String username = "postgres"; // TODO: Replace
    static String password = "hello"; // TODO: Replace
    static Statement statement;
    static ResultSet resultSet;
    static int count;
    static    ArrayList<String> result = new ArrayList<>();

  public static Connection connectToDatabase() throws ClassNotFoundException, SQLException {

    Class.forName("org.postgresql.Driver");
    Connection connection = DriverManager.getConnection(jdbcURL, username, password);
    System.out.println("Connected to PostgreSQL database!");

    return (connection);
  }

  public static ArrayList<String> executeQuery(String query, Connection connection)
      throws SQLException {

    statement = connection.createStatement();
    resultSet = statement.executeQuery(query);

    while (resultSet.next()) { 
      result.add(
          resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
    }
    return result;
  }

  public static int executeUpdate(String query, Connection connection) throws SQLException {

    statement = connection.createStatement();
    count = statement.executeUpdate(query);
    return count;
  }

  public static void closeConnection(Connection connection) throws SQLException {
    connection.close();
    System.out.println("Connection closed.");
  }
}
