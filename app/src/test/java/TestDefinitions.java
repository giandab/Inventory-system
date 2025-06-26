import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Client.Main;

public class TestDefinitions {
  Connection connection;
  ArrayList<String> queryResult = new ArrayList<>();

  @Given("That I add a new product to the database")
  public void that_i_add_a_new_product_to_the_database()
      throws ClassNotFoundException, SQLException {
        connection = Main.getConnection();
        Main.add("testProduct", 10);
      }

  @When("I search the database")
  public void i_search_the_database() throws ClassNotFoundException, SQLException {
    queryResult = Main.view();
  }

  @Then("I can see the new product")
  public void i_can_see_the_new_product() {
    String product = queryResult.get(queryResult.size()-1);
    product = product.substring(product.length()-14,product.length()-3);
    System.out.println(product);
    assertTrue(product.equals("testProduct"));
  }
}
