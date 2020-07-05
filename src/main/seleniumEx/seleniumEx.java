package main.seleniumEx;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class seleniumEx {

	public static void main(String[] args) throws InterruptedException {

		for (int i = 0; i < args.length; i++) {
			System.out.println("args : " + i + "=" + args[i]);
		}

		doSeleniumSample();
	}

	public static void doSeleniumSample() throws InterruptedException {
		System.out.println("Start Selenium ");

		// ChromeDriverまでのパスを設定する
		System.setProperty("webdriver.chrome.driver", "selenium/chrome/83.0/chromedriver");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito"); //シークレットモード
		//			    options.addArguments("--headless"); //ブラウザ非表示モード
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		// Chromeドライバーインスタンスを作成する
		WebDriver driver = new ChromeDriver(options);

		driver.get("https://www.google.com");

		Thread.sleep(1000);

		WebElement searchElement = driver.findElement(By.name("q"));
		searchElement.sendKeys("Selenium");

		Thread.sleep(1000);

		searchElement.submit();
	}

}
