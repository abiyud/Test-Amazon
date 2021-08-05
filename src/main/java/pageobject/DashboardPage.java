package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {
	
	public WebDriver driver;
	
	By btnDontChange = By.xpath("//*[contains(@class,'toaster')]//*[contains(text(),'Don')]/preceding-sibling::input");
	By logoApp = By.id("nav-logo-sprites");

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getTab(String text) {
		return driver.findElement(By.xpath("//a[normalize-space()='"+text+"']"));
	}
	
	public WebElement getLogo() {
		return driver.findElement(logoApp);
	}
	
	public WebElement getBtnDontChange() {
		return driver.findElement(btnDontChange);
	}
	
}
