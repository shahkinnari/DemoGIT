package stepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Test_Steps2 {
	private static WebDriver driver = null;
	
	@Given("^Open insider web$")
	public void user_is_on_Home_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://new.insider.in/mumbai");	
	   // throw new PendingException();
	}

	@When("^User change city from the city Dropdown$")
	public void user_change_city_from_the_city_Dropdown() throws Throwable {
		driver.findElement(By.xpath("//div[@class='icon-button-large icon-location']/span")).click();;
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='there-you-go']/div/div[1]/nav/ul[2]/li[4]/div[2]/ul/li[1]/a")).click();;
		Thread.sleep(2000);
	}

	@Then("^Selected language should be visible$")
	public void selected_language_should_be_visible() throws Throwable {
		WebElement cityText = driver.findElement(By.xpath("//div[@class='icon-button-large icon-location']/span"));
		if(cityText.getText().equalsIgnoreCase("Banglore")){
			System.err.println("Login Successfully");
		}else{
			System.err.println("Selected city is not visible.");
		}
	}

	
	@When("^User Navigate to LogIn Page$")
	public void user_Navigate_to_LogIn_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//a[@class='icon-button-large icon-account']")).click();;
		driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();;
	    //throw new PendingException();
	}

	@When("^User enters UserName and Password$")
	public void user_enters_UserName_and_Password() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		driver.findElement(By.name("email")).sendKeys("bhavin.nayee@kiwiqa.com");;
		driver.findElement(By.xpath("//button[@class='std-form-primary']")).click();
		driver.findElement(By.name("password")).sendKeys("123456");;
		driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
	    //throw new PendingException();
	}

	@Then("^Message displayed Login Successfully$")
	public void message_displayed_Login_Successfully() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement loginSuccess = driver.findElement(By.xpath("//a[@class='icon-button-large user-initials']"));
		if(loginSuccess.isDisplayed()){
			System.err.println("Login Successfully");
		}else{
			System.err.println("Login functionality is not working");
		}
	   // throw new PendingException();
	}

	@When("^User LogOut from the Application$")
	public void user_LogOut_from_the_Application() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(2000);
		driver.findElement (By.xpath("//a[@class='icon-button-large user-initials']")).click();
		Thread.sleep(2000);
		driver.findElement (By.xpath("//a[contains(.,'Sign out')]")).click();
		Thread.sleep(2000);
	    //throw new PendingException();
	}

	@Then("^Message displayed LogOut Successfully$")
	public void message_displayed_LogOut_Successfully() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement cityhead = driver.findElement(By.xpath("//div[@class='city-head']"));
		
		if(cityhead.isDisplayed()){
			System.err.println("Logout Successfully");
		}else{
			System.err.println("Logout functionality is not working");
		}
		
		driver.quit();
	  //  throw new PendingException();
	}


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
