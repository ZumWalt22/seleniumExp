package main.seleniumEx;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

//selenium関連

//ドライバは下記サイト「Selenium Client & WebDriver Language Bindings」「Java」のダウンロードリンクからダウンロード
//https://www.selenium.dev/downloads/
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

//画面キャプチャ関連
//https://jar-download.com/artifacts/ru.yandex.qatools.ashot/ashot/1.5.4
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


		String inputStr = "";

		//MEMO 起動時引数を取得の場合
//		for (int i = 0; i < args.length; i++) {
//			System.out.println("args : " + i + "=" + args[i]);
//		}
//		inputStr = args[0];

		//キーボードからスクリーンキャプチャのブラウザの種類を取得
		System.out.println("input target test browser 0:Chrome 1:IE 2:FireFox >");
	    BufferedReader com = new BufferedReader(new InputStreamReader(System.in));
    	try {
    		inputStr = com.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

    	//入力値チェック 入力値無効の場合はChromeで実施
    	if(!inputStr.equals("0") && !inputStr.equals("1") && !inputStr.equals("2")) {
    		inputStr = "0";
    	}

		//WebDriver設定 0:Chrome 1:IE 2:FireFox
		createWebDriver(Integer.parseInt(inputStr));

		//Seleniumサンプル実行
		doSeleniumSample();

		//スクリーンキャプチャ取得
		captImg();
	}

	/**
	 * WebDriver設定
	 * @param webDriverTypeCode
	 * @throws InterruptedException
	 */
	public static void createWebDriver(int webDriverTypeCode) throws InterruptedException {

		System.out.println("Start createWebDriver ");

		switch (webDriverTypeCode){
		  case 0:
			// Driverパスを設定
			//https://sites.google.com/a/chromium.org/chromedriver/downloads
			//chromedriver_win32.zip　をダウンロード　
			System.setProperty("webdriver.chrome.driver", "selenium/chrome/83.0/chromedriver.exe");

			// Chromeドライバーインスタンスを作成
			ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--incognito"); //シークレットモード
//				chromeOptions.addArguments("--headless"); //ブラウザ非表示モード
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver = new ChromeDriver(chromeOptions);

		    break;
		  case 1:
			//IEブラウザ事前設定
			//インターネットのオプション→セキュリティタブを開く
			//インターネット、ローカルイントラネット、信用済みサイト、制限付きサイトの「保護モードを有効にする(Internet Explorerの再起動が必要)」にチェックを入れる
			//「拡張保護モードを有効にする」チェックボックスを外す

			// Driverパスを設定
			//ドライバは下記サイトから取得し配置する
			//https://www.selenium.dev/downloads/
			//selenium-java-3.141.59.zip　をダウンロード
			System.setProperty("webdriver.ie.driver", "selenium/ie/3.14/IEDriverServer");

			// Chromeドライバーインスタンスを作成
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			ieOptions.takeFullPageScreenshot();
//				ieOptions.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
//				ieOptions.addCommandSwitches("-private");
			driver = new InternetExplorerDriver(ieOptions);
		    break;

		  case 2:
			//ドライバは下記サイトから取得し配置する
			//https://github.com/mozilla/geckodriver/releases
			//geckodriver-v0.26.0-win32.zip　をダウンロード　
			//FireFoxのドライバ配置場所はフルパスで記述する必要あり
			System.setProperty("webdriver.gecko.driver", "C:/pleiades/workspace/seleniumExp/selenium/firefox/0.26/geckodriver.exe");

			FirefoxOptions firefoxOptions = new FirefoxOptions();
//			internetExplorerOptions.addCommandSwitches(switches)
//				internetExplorerOptions.addArguments("--headless"); //ブラウザ非表示モード
			// Chromeドライバーインスタンスを作成する
			driver = new FirefoxDriver(firefoxOptions);
			break;

		}

		System.out.println("End createWebDriver ");
	}

	/**
	 * 画面キャプチャ実施
	 * @throws InterruptedException
	 */
	public static void captImg() throws InterruptedException {

		System.out.println("Start createWebDriverImpl ");

		//Headerタイトル等を削除する場合、事前に対象のElementを削除
//		JavascriptExecutor jexec = (JavascriptExecutor) driver;
//		jexec.executeScript("document.getElementById('xxx').innerHTML = ''");

		//Ashotライブラリでフルスクリーンショットを取得
	    Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1500)).takeScreenshot(driver);
	    try {
			ImageIO.write(screenshot.getImage(),"PNG",new File("sampleScreenShot.png"));
		} catch (IOException e) {
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
