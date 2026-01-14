package Locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.FindBy;

public class OrangeHRM {
	
	@FindBy(xpath="//input[@name='username']")
	public WebElement userName;
	
	@FindBy(xpath="//input[@name='password']")
	public WebElement passWord;
	
	@FindBy(xpath="//button[@type='submit']")
	public WebElement loginBtn;
	
	
	@FindAll({
		@FindBy(xpath="//a[contains(@href,'Performance')]"),
		@FindBy(xpath="//a[contains(@class,'menu')]")
	})
	public WebElement Performance;

}
