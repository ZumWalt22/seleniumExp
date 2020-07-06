package main.seleniumEx;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * @author sss
 *
 */
public class seleniumEx {

	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

//		for (int i = 0; i < args.length; i++) {
//			System.out.println("args : " + i + "=" + args[i]);
//		}

		//キーボードの入力ストリームを取得
//		String data = null;
//	    BufferedReader com = new BufferedReader(new InputStreamReader(System.in));
//    	try {
//			data = com.readLine();
//		} catch (IOException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}

		//WebDriver設定
		createWebDriverImpl(0);

		//Selenium事項
		doSeleniumSample();

		//スクリーンキャプチャ取得
		captImg();
	}

	/**
	 * WebDriver設定
	 * @param webDriverTypeCode
	 * @throws InterruptedException
	 */
	public static void createWebDriverImpl(int webDriverTypeCode) throws InterruptedException {

		System.out.println("Start createWebDriverImpl ");

		switch (webDriverTypeCode){
		  case 0:
			// Driverパスを設定
			System.setProperty("webdriver.chrome.driver", "selenium/chrome/83.0/chromedriver");

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito"); //シークレットモード
//				chromeOptions.addArguments("--headless"); //ブラウザ非表示モード
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			// Chromeドライバーインスタンスを作成する
			driver = new ChromeDriver(chromeOptions);

		    break;
		  case 1:
			// Driverパスを設定
			System.setProperty("webdriver.ie.driver", "selenium/ie/3.14/IEDriverServer");

			InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
//			internetExplorerOptions.addCommandSwitches(switches)
//				internetExplorerOptions.addArguments("--headless"); //ブラウザ非表示モード
			// Chromeドライバーインスタンスを作成する
			driver = new InternetExplorerDriver(internetExplorerOptions);
		    break;

		  case 2:
			// Driverパスを設定
			System.setProperty("webdriver.gecko.driver", "selenium/ie/0.26/geckodriver");

			FirefoxOptions firefoxOptions = new FirefoxOptions();
//			firefoxOptions.
//			internetExplorerOptions.addCommandSwitches(switches)
//				internetExplorerOptions.addArguments("--headless"); //ブラウザ非表示モード
			// Chromeドライバーインスタンスを作成する
			driver = new FirefoxDriver(firefoxOptions);
		    break;

		}

		System.out.println("End createWebDriverImpl ");
	}

	/**
	 * 画面キャプチャ実施
	 * @throws InterruptedException
	 */
	public static void captImg() throws InterruptedException {

		System.out.println("Start createWebDriverImpl ");

		//Headerタイトル等を削除する場合、以下を使用
//		JavascriptExecutor jexec = (JavascriptExecutor) driver;
//		jexec.executeScript("document.getElementById('xxx').innerHTML = ''");

		//Ashotライブラリでフルスクリーンショットを取得
	    Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
	    try {
			ImageIO.write(screenshot.getImage(),"PNG",new File("yyy.png"));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//-------------MEMO

		//カレントページのみスクリーンショットを取得
//	    File sfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//	    try {
//			Files.copy(sfile.toPath(), new File("C:/jar/xxx.png").toPath());
//		} catch (IOException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}

		System.out.println("End createWebDriverImpl ");
	}

	/**
	 * Selenium実行サンプル
	 * @throws InterruptedException
	 */
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
		driver = new ChromeDriver(options);

		driver.get("https://www.google.com");

		Thread.sleep(1000);

		WebElement searchElement = driver.findElement(By.name("q"));
		searchElement.sendKeys("Selenium");

		Thread.sleep(1000);

		searchElement.submit();

		Thread.sleep(1000);

		//-------------MEMO

		//JavaScript実行
//		JavascriptExecutor jexec = (JavascriptExecutor) driver;
//		jexec.executeScript("");

	}

}
