package wait;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class WaitUntil {
	private WebDriver driver;
	public final WebElement element=null;

	public WaitUntil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement waitUntilElement(int timeout, WebElement element) {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);

		WebElement waitUntil = wait.until(new Function<WebDriver, WebElement>() {

			private WebElement element;

			@Override
			public WebElement apply(WebDriver driver) {
				return this.element;
			}

		});
		return element;
	}
}
