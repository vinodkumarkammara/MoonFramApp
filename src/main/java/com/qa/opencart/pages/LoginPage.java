package com.qa.opencart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private static final Logger LOGGER = Logger.getLogger(String.valueOf(LoginPage.class));
	private WebDriver driver;
	private ElementUtil Util;


	//1 . By Locators 

	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.cssSelector("input[value='Login']");
	private By Forgotpwd = By.cssSelector("div.form-group a");
	private By registerLink = By.linkText("Register");

	// 2. By Constructors of Page Class:

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		Util= new ElementUtil(this.driver);	

	}
	// 3. Page actions / Methods:
	public String getLoginpageTitle() {
		return Util.waitForPageTitlePresent(Constants.LOGIN_PAGE_TITLE, 5);

	}

	public boolean ForgotPwdLinkisExist() {
		return Util.doIsDisplayed(Forgotpwd);
	
	}

	public AccountsPage doLogin(String un , String pwd) {
		System.out.println("Login With : " +  un + "" + pwd);
		Util.doSendKeys(username, un);
		Util.doSendKeys(password, pwd);
		Util.doClick(loginBtn);

		return new AccountsPage(driver);

	}

	@Step("navigate To Register Page")
	public RegisterPage navigateToRegisterPage() {
		LOGGER.info("Navigate to Register page....");
		Util.doClick(registerLink);
		return new RegisterPage(driver);

	}
}
