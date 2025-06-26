package Client;

import Repository.Repository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  static Connection connection;
  static Scanner scanner = new Scanner(System.in);
  static String userInput;
  static ArrayList<String> queryResult = new ArrayList<>();

  public static void main(String[] args) throws SQLException, ClassNotFoundException {

    connection = getConnection();
    userInput = getUserIntention();

    while (!userInput.equals("exit")) {

      switch (userInput) {
        case "view":
          view();
          break;
        case "add":
            System.out.println("Enter the name of the product");
    String productName = scanner.nextLine();

    System.out.println("Enter the amount in stock");
    String stock_input =  scanner.nextLine();
    int stock = Integer.parseInt(stock_input);
          add(productName,stock);
        default:
          break;
      }

      userInput = getUserIntention();
    }
    Repository.closeConnection(connection);
    scanner.close();
  }

  public static Connection getConnection() throws SQLException, ClassNotFoundException {
    connection = Repository.connectToDatabase();
    Repository.executeUpdate(
        "CREATE TABLE IF NOT EXISTS Products (ProductID SERIAL PRIMARY KEY, ProductName varchar(255), InStock int);",
        connection);
        System.out.println("Connected to the database");
    return connection;
  }

  public static String getUserIntention() {
    String userMessge =
        "Welcome to the invnetory management program. Type \n"
            + //
            " 'view' to view all records in the database \n"
            + //
            " 'add' to add a new product \n"
            + //
            //" 'update' to update an existing product \n"
            " 'exit' to exit";
    System.out.println(userMessge);
    userInput = scanner.nextLine();
    return userInput;
  }

  public static ArrayList<String> view() throws SQLException {
    queryResult.clear();
    queryResult = Repository.executeQuery("SELECT * FROM Products", connection);
    System.out.println("Result: "+queryResult);
    return queryResult;
  }

  public static int add(String productName,int stock) throws SQLException {


    String update =
        "INSERT INTO Products (ProductName,InStock) VALUES ('" + productName + "','" + stock + "')";
    System.out.println(update);
    int linesUpdated = Repository.executeUpdate(update, connection);
    System.out.println("Lines updated "+linesUpdated);
    return linesUpdated;
  }
}
