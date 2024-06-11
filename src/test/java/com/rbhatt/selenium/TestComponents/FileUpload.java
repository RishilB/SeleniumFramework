package com.rbhatt.selenium.TestComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;

public class FileUpload {
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://altoconvertpdftojpg.com/");
		driver.findElement(By.id("browse")).click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "file_upload.exe");
		
	}
}
