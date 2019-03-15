package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_Register {
	
	static public void fillForm(WebDriver driver, String email,String name,String lastName, String password,String passwordConfirm) {
		WebElement emaile = driver.findElement(By.name("email"));
		emaile.click();
		emaile.clear();
		emaile.sendKeys(email);				
		WebElement  namee= driver.findElement(By.name("name"));
		namee.click();
		namee.clear();
		namee.sendKeys(name);
		WebElement  lastnamee= driver.findElement(By.name("lastName"));
		lastnamee.click();
		lastnamee.clear();
		lastnamee.sendKeys(lastName);
		WebElement  passe= driver.findElement(By.name("password"));
		passe.click();
		passe.clear();
		passe.sendKeys(password);
		WebElement  passec= driver.findElement(By.name("passwordConfirm"));
		passec.click();
		passec.clear();
		passec.sendKeys(passwordConfirm);
		//Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}
