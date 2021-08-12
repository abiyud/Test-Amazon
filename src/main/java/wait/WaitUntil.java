package wait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUntil {
	private WebDriver driver;
	private static final int TIMEOUT = 30;
	private static final int POLLING_TIME = 3;

	public WaitUntil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement waitUntilElement(By by) {
		Wait<WebDriver> fw = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class);
		return fw.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public WebElement waitFluentElement(final By by) {
		Wait<WebDriver> fw = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class);
		WebElement result = fw.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver t) {
				// TODO Auto-generated method stub
				return driver.findElement(by);
			}
		});
		return result;
	}
	
	public List<WebElement> waitUntilElements(By by) {
		Wait<WebDriver> fw = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class);
		return fw.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}
	
	public List<WebElement> waitFluentElements(final By by) {
		Wait<WebDriver> fw = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class);
		List<WebElement> result = fw.until(new Function<WebDriver, List<WebElement>>() {

			@Override
			public List<WebElement> apply(WebDriver t) {
				// TODO Auto-generated method stub
				return driver.findElements(by);
			}
		});
		return result;
	}
	
	public WebElement waitFluentElementa(final WebElement element) {
		Wait<WebDriver> fw = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class);
		WebElement result = fw.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver t) {
				// TODO Auto-generated method stub
				return element;
			}
		});
		return result;
	}
	
//	Without xpath
	
	public WebElement waitLoadElement(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public List<WebElement> waitLoadElements(List<WebElement> elements, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		System.out.println(wait.until(ExpectedConditions.visibilityOfAllElements(elements))+" inside function");
		return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
}
