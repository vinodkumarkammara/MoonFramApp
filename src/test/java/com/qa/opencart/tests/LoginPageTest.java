package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest {

	@Test 
	public void LoginPageTitleTest() {	
		String title = loginPage.getLoginpageTitle();
		System.out.println("Login Page title :" + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);

	}

	@Test
	public void isForgotPwdLinkExist() {
		try {
			Assert.assertTrue(loginPage.ForgotPwdLinkisExist());
		} catch (Exception e) {

		}

	} 	

	@Test
	public void LoginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccountsPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
	}



}
