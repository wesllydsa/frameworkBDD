package br.com.just.frameworkBDD.driver;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import net.thucydides.core.webdriver.DriverSource;

public class ChromeSerenityDriver implements DriverSource {

	public WebDriver newDriver() {
		String pathChromeDriver = "C:" + File.separator + "Just" + File.separator + "Drivers" + File.separator;
		String pathChrome = "C:" + File.separator + "Program Files (x86)" + File.separator + "Google" + File.separator
				+ "Chrome" + File.separator + "Application" + File.separator + "chrome.exe";

		ChromeInfo chromeBinaryInfo;
		try {
			chromeBinaryInfo = new ChromeInfo(new File(pathChrome));

			String defaultDriverVersion;
			if (chromeBinaryInfo.getChromeMajorVersion() >= 77) {
				defaultDriverVersion = "chromedriver_77.0.3865.10";
			} else if (chromeBinaryInfo.getChromeMajorVersion() >= 76) {
				defaultDriverVersion = "chromedriver_76.0.3809.68";
			} else if (chromeBinaryInfo.getChromeMajorVersion() >= 75) {
				defaultDriverVersion = "chromedriver_75.0.3770.140";
			} else if (chromeBinaryInfo.getChromeMajorVersion() >= 74) {
				defaultDriverVersion = "chromedriver_74.0.3729.6";
			} else if (chromeBinaryInfo.getChromeMajorVersion() >= 73) {
				defaultDriverVersion = "chromedriver_73.0.3683.68";
			} else {
				defaultDriverVersion = "chromedriver_v2.28_win32.exe";
			}

			System.setProperty("webdriver.chrome.driver", pathChromeDriver + defaultDriverVersion);

		} catch (IOException e) {
			e.printStackTrace();
		}

		ChromeOptions options = new ChromeOptions();

		options.setBinary(new File(pathChrome));
		options.addArguments("disable-gpu", "disable-extensions");
		options.addArguments("--start-maximized");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("user-data-dir=%USERPROFILE%/AppData/Local/Google/Chrome/User Data");
		options.addArguments("google-chrome-stable --disable-web-security --user-data-dir");

		return new ChromeDriver(options);
	}

	public boolean takesScreenshots() {
		return true;
	}
	
}
