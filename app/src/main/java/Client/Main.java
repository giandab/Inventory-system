package Client;

import Repository.Repository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws SQLException, ClassNotFoundException {

    Connection connection = Repository.connectToDatabase();
    Repository.executeUpdate(
        "CREATE TABLE IF NOT EXISTS Products (ProductID SERIAL PRIMARY KEY, ProductName varchar(255), InStock int);",
        connection);

    Scanner scanner = new Scanner(System.in);
    String userMessge =
        "Welcome to the invnetory management program. Type \n"
            + //
            " 'view' to view all records in the database \n"
            + //
            " 'add' to add a new product \n"
            + //
            " 'update' to update an existing product \n"
            + //
            " 'exit' to exit";
    System.out.println(userMessge);

    String userInention = scanner.nextLine();
    System.out.println("User intention " + userInention);

    while (!userInention.equals("exit")) {
      System.out.println("User intention " + userInention);

      switch (userInention) {
        case "view":
          Repository.executeQuery("SELECT * FROM Products", connection);
          break;
        case "add":
          System.out.println("Enter the name of the product");
          String productName = scanner.nextLine();

          System.out.println("Enter the amount in stock");
          String stock = scanner.nextLine();

          String update =
              "INSERT INTO Products (ProductName,InStock) VALUES ('"
                  + productName
                  + "','"
                  + stock
                  + "')";
          System.out.println(update);
          Repository.executeUpdate(update, connection);

        default:
          break;
      }

      System.out.println(userMessge);
      userInention = scanner.nextLine();
    }
    Repository.closeConnection(connection);
    scanner.close();
  }
}
