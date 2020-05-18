package hu.unideb.inf;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class StepDefinitions {

    static WebDriver webDriver;
    static List<RegistrationField> registrationFields;

    static {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.MINUTES);
        registrationFields = new ArrayList<>();
        registrationFields.add(new RegistrationField("customer_firstname", "Test firstname"));
        registrationFields.add(new RegistrationField("customer_lastname", "Test lastname"));
        registrationFields.add(new RegistrationField("passwd", "abc123"));
        registrationFields.add(new RegistrationField("address1", "Test address"));
        registrationFields.add(new RegistrationField("city", "Test city"));
        registrationFields.add(new RegistrationField("postcode", "00000"));
        registrationFields.add(new RegistrationField("phone_mobile", "06201234123"));
        registrationFields.add(new RegistrationField("id_state", "1", true));
    }

    @Given("The home page is opened")
    public void theHomePageIsOpened() {
        webDriver.get("http://automationpractice.com/");
    }

    @And("The Sign in link is clicked")
    public void theSignInLinkIsClicked() {
        webDriver.findElement(By.className("login")).click();
    }

    @Given("The Create an account is clicked")
    public void theCreateAnAccountIsClicked() {
        webDriver.findElement(By.id("SubmitCreate")).click();
    }

    @Then("an Invalid email address error message is shown")
    public void anInvalidEmailAddressErrorMessageIsShown() {
        List<WebElement> elements = webDriver.findElements(By.xpath("//*[@id=\"create_account_error\"]/ol/li"));
        Assert.assertNotEquals(0, elements.size());
        Assert.assertEquals("Invalid email address.", elements.get(0).getText());
    }

    @Given("The Email address field is field with random valid email")
    public void theEmailAddressFieldIsFieldWithRandomValidEmail() {
        String uniqueID = UUID.randomUUID().toString();
        String email = uniqueID + "@test.com";
        webDriver.findElement(By.id("email_create")).sendKeys(email);
    }

    @When("I click on Create an account")
    public void iClickOnCreateAnAccount() {
        webDriver.findElement(By.id("SubmitCreate")).click();
    }

    @And("I leave empty this {string}")
    public void iLeaveEmptyThisField(String field) {
        for (int i = 0; i < registrationFields.size(); i++) {
            RegistrationField rfield = registrationFields.get(i);
            if(rfield.equals(new RegistrationField(field, ""))) {
                continue;
            }
            if(!rfield.isSelect()) {
                webDriver.findElement(By.id(rfield.getId())).sendKeys(rfield.getValue());
            } else {
                Select tmp = new Select(webDriver.findElement(By.id(rfield.getId())));
                tmp.selectByValue(rfield.getValue());
            }
        }
    }

    @And("I click on the register button")
    public void iClickOnTheRegisterButton() {
        webDriver.findElement(By.id("submitAccount")).click();
    }

    @Then("I get {string} error message")
    public void iGetMsgErrorMessage(String msg) {
        List<WebElement> elements = webDriver.findElements(By.xpath("//*[@id=\"center_column\"]/div/ol/li"));
        Assert.assertEquals(1, elements.size());
        Assert.assertEquals(msg, elements.get(0).getText());
    }

    @Given("The search field is filled with {string}")
    public void theSearchFieldIsFilledWithData(String data) {
        webDriver.findElement(By.id("search_query_top")).sendKeys(data);
    }

    @And("I click the search button")
    public void iClickTheSearchButton() {
        webDriver.findElement(By.xpath("//*[@id=\"searchbox\"]/button")).click();
    }

    @Then("I get {string}")
    public void iGetProduct(String product) {
        List<WebElement> elements = webDriver.findElements(By.xpath("//*[@id=\"center_column\"]/ul/li"));
        Assert.assertNotEquals(0, elements.size());
        Assert.assertEquals(product, elements.get(0).findElement(By.className("product-name")).getText());
    }
}
