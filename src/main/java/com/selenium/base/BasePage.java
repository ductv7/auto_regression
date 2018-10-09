package com.selenium.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.selenium.common.BasicProperties;

public class BasePage {
	
	public WebDriver driver;

	protected BasicProperties bp;

	protected Properties pros_env;

	protected Properties pros_obj;

	public WebElement ele;

}
