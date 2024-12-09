package com.rbhatt.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFarmManager {

    private PropertyFileReader propertyFileReader;

    public BrowserFarmManager(PropertyFileReader propertyFileReader) {
        this.propertyFileReader = propertyFileReader;
    }

    public WebDriver getRemoteWebDriver() throws MalformedURLException {
        String browserFarm = propertyFileReader.getProperty("browserFarm");
        String username = propertyFileReader.getProperty("browserFarmUsername");
        String accessKey = propertyFileReader.getProperty("browserFarmAccessKey");
        String browser = propertyFileReader.getProperty("browser");
        String browserVersion = propertyFileReader.getProperty("browserVersion");
        String os = propertyFileReader.getProperty("os");
        String osVersion = propertyFileReader.getProperty("osVersion");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", browserVersion);
        capabilities.setCapability("os", os);
        capabilities.setCapability("os_version", osVersion);

        String remoteUrl = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

        return new RemoteWebDriver(new URL(remoteUrl), capabilities);
    }
}
