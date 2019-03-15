package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView{

	static public void fillForm(WebDriver driver, String email, String password) {
		WebElement emailElement = driver.findElement(By.name("username"));
		emailElement.click();
		emailElement.clear();
		emailElement.sendKeys(email);				
		WebElement passwordElement = driver.findElement(By.name("password"));
		passwordElement.click();
		passwordElement.clear();
		passwordElement.sendKeys(password);
		//Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

}
