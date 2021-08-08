package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import wait.WaitUntil;

public class RegistriesPage {

	public WebDriver driver;

	By inputName = By.name("name");
	By drpdwnSearchUrl = By.xpath("//*[@name='searchUrl']/following-sibling::*");
	By btnSearch = By.xpath("//button[normalize-space()='Search']");
	By btnFromMonth = By.id("a-autoid-1");
	By btnFromYear = By.id("a-autoid-2");
	By btnToMonth = By.id("a-autoid-3");
	By btnToYear = By.id("a-autoid-4");
	By btnFilterGift = By.id("a-autoid-5");
	By listResultDate = By
			.xpath("//*[@id='search-result-container']//*[contains(@class,'date') and not(contains(@class,'header'))]");
	By firstResultDate = By.xpath(
			"(//*[@id='search-result-container']//div[contains(@class,'date') and not(contains(@class,'header'))])[1]");
	By txtDataNotFound = By.xpath("//*[normalize-space(text())='Sorry, no Birthday Gift Lists match your search.']");

	public RegistriesPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getText(String text) {
		return driver.findElement(By.xpath("//*[normalize-space(text())='" + text + "']"));
	}

	public WebElement getInputName() {
		return driver.findElement(inputName);
	}

	public WebElement getBtnSearch() {
		return driver.findElement(btnSearch);
	}

	public WebElement getBtnSearchUrl() {
		return driver.findElement(drpdwnSearchUrl);
	}

	public WebElement getOption(String text) {
		return driver.findElement(By.xpath("//li/*[normalize-space()='" + text + "']"));
	}

	public List<WebElement> getListSearch(String text) {
		String temp = text.toLowerCase();
		return driver.findElements(By.xpath(
				"//*[contains(@class,'name')]/a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'"
						+ temp + "')]"));
	}

	public WebElement getFromMonth() {
		return driver.findElement(btnFromMonth);
	}

	public WebElement getToMonth() {
		return driver.findElement(btnToMonth);
	}

	public WebElement getFromYear() {
		return driver.findElement(btnFromYear);
	}

	public WebElement getToYear() {
		return driver.findElement(btnToYear);
	}

	public WebElement selectFromMonth(String text) {
		return driver.findElement(
				By.xpath("//*[contains(@id,'gr-search-from-month') and normalize-space()='" + text + "']/parent::*"));
	}

	public WebElement selectToMonth(String text) {
		return driver.findElement(
				By.xpath("//*[contains(@id,'gr-search-to-month') and normalize-space()='" + text + "']/parent::*"));
	}

	public WebElement selectFromYear(String text) {
		return driver.findElement(
				By.xpath("//*[contains(@id,'gr-search-from-year') and normalize-space()='" + text + "']/parent::*"));
	}

	public WebElement selectToYear(String text) {
		return driver.findElement(
				By.xpath("//*[contains(@id,'gr-search-to-year') and normalize-space()='" + text + "']/parent::*"));
	}

	public WebElement getBtnFilter() {
		return driver.findElement(btnFilterGift);
	}

	public List<WebElement> getListResultDate() {
		return driver.findElements(listResultDate);
	}

	public WebElement getFirstResultDate() {
		return driver.findElement(firstResultDate);
	}

	public WebElement getTxtNotFound() {
		return driver.findElement(txtDataNotFound);
	}

	public WebElement waitTxtNotFound(int timeout) {
		WaitUntil waitUntil = new WaitUntil(driver);
		return waitUntil.waitUntilLoad(timeout, txtDataNotFound);
	}
}
