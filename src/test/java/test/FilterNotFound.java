package test;

import java.text.ParseException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobject.DashboardPage;
import pageobject.RegistriesPage;
import wait.WaitUntil;

public class FilterNotFound {
	private WebDriver driver;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@Test
	public void dataNotFound() throws InterruptedException, ParseException {
//		Initialize
		DashboardPage dashboard = new DashboardPage(driver);
		RegistriesPage registries = new RegistriesPage(driver);
		WaitUntil waitUntil = new WaitUntil(driver);
		String web = "https://www.amazon.com/";

//		Open web Amazon.com
		driver.get(web);
		dashboard.getLogo().click();

//		Click tab registry
		dashboard.getTab("Registry").click();

//		Verify label Registry & Gifting
		Assert.assertTrue(registries.getText("Registry & Gifting").isDisplayed());

//		Input name "John"
		registries.getInputName().sendKeys("Test");

//		Select gift type
		registries.getBtnSearchUrl().click();
		registries.getOption("Birthday Gift List").click();
		registries.getBtnSearch().click();

//		Validate result from filter date
		registries.getLocation().click();
		registries.selectLocation("Alabama").click();
		registries.getBtnFilter().click();

		waitUntil.waitLoadElement(registries.waitTxtNotFound(), 10);
//		registries.waitTxtNotFound().isDisplayed();
		System.out.println(registries.waitTxtNotFound().isDisplayed()+" true niiiich");
		System.out.println(registries.waitTxtNotFound().isDisplayed() + " MUncul");
		Assert.assertTrue(registries.waitTxtNotFound().isDisplayed());
	}

	@AfterMethod
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}
}
