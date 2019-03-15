package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_AddOffer extends PO_NavView{

	static public void fillForm(WebDriver driver, String titulo,String descripcion,String precio, String url) {
		WebElement tituloe = driver.findElement(By.name("title"));
		tituloe.click();
		tituloe.clear();
		tituloe.sendKeys(titulo);				
		WebElement  descripcione= driver.findElement(By.name("description"));
		descripcione.click();
		descripcione.clear();
		descripcione.sendKeys(descripcion);
		WebElement  precioe= driver.findElement(By.name("price"));
		precioe.click();
		precioe.clear();
		precioe.sendKeys(precio);
		WebElement  urle= driver.findElement(By.name("picture"));
		urle.click();
		urle.clear();
		urle.sendKeys(url);
		//Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}
