package test;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
		registries.getInputName().sendKeys("tttttttest");

//		Select gift type
		registries.getBtnSearchUrl().click();
		registries.getOption("Birthday Gift List").click();
		registries.getBtnSearch().click();

//		Validate list name "tttttttest"
		Assert.assertEquals(!registries.getListSearch("tttttttest").isEmpty(), true);

//		Change range date from January 2021 to April 2021
		registries.getFromMonth().click();
		registries.selectFromMonth("January").click();
		registries.getFromYear().click();
		registries.selectFromYear("2021").click();
		registries.getToMonth().click();
		registries.selectToMonth("April").click();
		registries.getToYear().click();
		registries.selectToYear("2021").click();
		registries.getBtnFilter().click();

//		Validate result from filter date
		WebElement element = waitUntil.waitUntilElement(30, registries.getTxtNotFound());
		Assert.assertEquals(element.isDisplayed(), true);
		
	}

	@AfterMethod
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}
}