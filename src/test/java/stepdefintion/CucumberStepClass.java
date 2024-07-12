package stepdefintion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import util.DriverFactory;

public class CucumberStepClass
{
	private WebDriver driver;

    @Before
    public void setUp() {
        // Initialize WebDriver using DriverFactory
        driver = DriverFactory.getDriver();
    }

    @Given("I open Google search page")
    public void i_open_Google_search_page() {
        driver.get("https://www.google.com");
    }

    @When("I search for {string}")
    public void i_search_for(String keyword) {
        driver.findElement(By.name("q")).sendKeys(keyword);
        driver.findElement(By.name("q")).submit();
    }

    @Then("I should see results related to {string}")
    public void i_should_see_results_related_to(String keyword) {
        String pageTitle = driver.getTitle();
        // Validate if the page title or content contains expected keyword
        assert pageTitle.toLowerCase().contains(keyword.toLowerCase());
    }

    @After
    public void tearDown() {
        // Quit WebDriver after each scenario
        DriverFactory.quitDriver();
    }
}
