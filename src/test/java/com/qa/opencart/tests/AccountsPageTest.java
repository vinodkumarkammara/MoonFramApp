package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 200: Feature Name : Accounts Page for Demo Shop Application")
@Story("User Story - 301: Desgin Accounts Page for application with different test cases")
public class AccountsPageTest extends BaseTest {


	@BeforeClass
	public void accountsPageSetUp() {
		accountsPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}


	@Description("accounts Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void accountsPageTitleTest() {
		String title = accountsPage.getAccountsPageTitle();
		System.out.println("Accounts Page Title is : "+ title);
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE);

	}

	@Description("verify Accounts Page Header Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void verifyAccountsPageHeaderTest() {
		String text= accountsPage.getAccountsHeaderValue();
		System.out.println("Accounts Page Header Value :" + text);
		Assert.assertEquals(text, Constants.ACCOUNTS_PAGE_HEADER);

	}

	@Test(priority = 3)
	@Description("verify Acc Page Sections Count Test")
	@Severity(SeverityLevel.NORMAL)
	public void verifyAccountsPageSectionsTest() {
		Assert.assertTrue(accountsPage.getAccountsSectioncount()== Constants.ACCOUNTS_SECTION_LIST);
	} 

	@Test(priority = 4)
	@Description("verify Acc Page Sections List Test")
	@Severity(SeverityLevel.NORMAL)
	public void verifyAccountsSectionListTest() {
		List<String> list = accountsPage.getAccountSectionList();
		System.out.println(list);

	}

	@DataProvider
	public Object[][] searchData() {
		return new Object[][] {{"iMac"}, 
			{"iPhone"}, 
			{"Macbook"}};
	}

	@Test(priority = 5, dataProvider = "searchData")
	@Description("product search with Macbook")
	@Severity(SeverityLevel.CRITICAL)
	public void searchTest(String productName) {
		Assert.assertTrue(accountsPage.doSearch(productName));

	}
	
	@Test(priority = 6 )
	@Description("verify Product Results Test for iMac")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyProductResultsTest() {
		accountsPage.doSearch("iMac");
		accountsPage.selectProductFromResults("iMac");
	}



}
