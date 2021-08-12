package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobject.DashboardPage;
import pageobject.RegistriesPage;
import wait.WaitUntil;

public class GetDataFilter {
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
	public void filterGift() throws InterruptedException, ParseException {
//		Initialize
		DashboardPage dashboard = new DashboardPage(driver);
		RegistriesPage registries = new RegistriesPage(driver);
		WaitUntil waitUntil = new WaitUntil(driver);
		String web = "https://www.amazon.com/";
		Date dateFrom;
		Date dateTo;
		Date date;

//		Open web Amazon.com
		driver.get(web);
		dashboard.getLogo().click();

//		Click tab registry
		dashboard.getTab("Registry").click();

//		Verify label Registry & Gifting
		Assert.assertTrue(registries.getText("Registry & Gifting").isDisplayed());

//		Input name "John"
		registries.getInputName().sendKeys("John");

//		Select gift type
		registries.getBtnSearchUrl().click();
		registries.getOption("Birthday Gift List").click();
		registries.getBtnSearch().click();

//		Validate list name "John"
		Assert.assertEquals(!registries.getListSearch("John").isEmpty(), true);

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

		
		String fromDate = registries.getFromMonth().getAttribute("textContent") + " "
				+ registries.getFromYear().getAttribute("textContent");
		String toDate = registries.getToMonth().getAttribute("textContent") + " "
				+ registries.getToYear().getAttribute("textContent");
		DateFormat format = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
		dateFrom = format.parse(fromDate);
		dateTo = format.parse(toDate);
		
//		Validate result from filter date

//		WebElement elementDate = registries.getListResultDate().get(0);
		
		
//		Thread.sleep(3000);

//		List<WebElement> elements = registries.getListResultDate();
//		for (int i = 0; i < elements.size(); i++) {
//			String[] yearSelect = elements.get(i).getText().split(",");
//			String[] monthSelect = elements.get(i).getText().split(" ");
//			String temp = monthSelect[0] + " " + yearSelect[1];
//			date = format.parse(temp);
//			Assert.assertEquals(date.getTime() > dateFrom.getTime() && date.getTime() < dateTo.getTime()
//					|| date.getTime() == dateFrom.getTime() || date.getTime() == dateTo.getTime(), true);
//		}

	}

	@AfterMethod
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}

}
