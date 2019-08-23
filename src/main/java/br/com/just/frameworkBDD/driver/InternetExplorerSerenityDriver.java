package br.com.just.frameworkBDD.driver;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.thucydides.core.webdriver.DriverSource;

public class InternetExplorerSerenityDriver implements DriverSource {

	@SuppressWarnings("deprecation")
	public WebDriver newDriver() {
		try{
		System.setProperty("webdriver.ie.driver", "IEDriverServer_Win32_3.11.1.exe");		
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		capabilities.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, false);
		capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.IGNORE);
		
		return new InternetExplorerDriver(capabilities);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public boolean takesScreenshots() {
		return true;
	}
}
