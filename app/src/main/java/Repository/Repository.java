package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Repository {

  public static Connection connectToDatabase() throws ClassNotFoundException, SQLException {

    Class.forName("org.postgresql.Driver");

    String jdbcURL = ""; // TODO: Replace
    String username = ""; // TODO: Replace
    String password = ""; // TODO: Replace

    Connection connection = DriverManager.getConnection(jdbcURL, username, password);
    System.out.println("Connected to PostgreSQL database!");

    return (connection);
  }

  public static void executeQuery(String query, Connection connection) throws SQLException {

    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);

    while (resultSet.next()) {
      System.out.println(
          resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
    }
  }

  public static void executeUpdate(String query, Connection connection) throws SQLException {

    Statement statement = connection.createStatement();
    int count = statement.executeUpdate(query);
    System.out.println("Number of rows affected by this query: " + count);
  }

  public static void closeConnection(Connection connection) throws SQLException {
    connection.close();
    System.out.println("Connection closed.");
  }
}
