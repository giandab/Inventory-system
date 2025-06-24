import io.cucumber.java.en.Given;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources",
    // glue = "com.example.stepdefinitions", 
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true
)
public class Runner {    
}

