package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;


public class AccountsPage {

	private WebDriver driver;
	private ElementUtil Util;

	private By header = By.cssSelector("div#logo a");
	private By accountssectionHeaders = By.cssSelector("div#content h2");
	private By searchText = By.cssSelector("div#search input[name='search']");
	private By Button = By.cssSelector("div#search button[type='button']");
	private By searchitemresults = By.cssSelector("div.product-layout div.product-thumb");
	private By resultsItems = By.cssSelector("div.product-thumb h4 a");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		Util = new ElementUtil(this.driver);

	}

	public String getAccountsPageTitle() {
		return Util.waitForPageTitlePresent(Constants.ACCOUNTS_PAGE_TITLE, 5);
	}

	public String getAccountsHeaderValue() {
		if(Util.doIsDisplayed(header)) {
			return Util.doGetText(header);
		}
		return null;
	}

	public int getAccountsSectioncount() {
		return Util.getElements(accountssectionHeaders).size();
	}

	public List<String> getAccountSectionList() {
		List<String> accountslist= new ArrayList<>();
		List<WebElement> accountsection = Util.getElements(accountssectionHeaders);

		for(WebElement e : accountsection) {
			String text= e.getText();
			System.out.println(text);
			accountslist.add(text);

		}


		return accountslist;

	}
	//seacrch features Page Actions:

	public boolean doSearch(String ProductName) {
		Util.doSendKeys(searchText, ProductName);
		Util.doClick(Button);
		if(Util.getElements(searchitemresults).size() > 0) {
			return true;
		}

		return false;
	}
	public ProductInfoPage selectProductFromResults(String ProductName) {
		List<WebElement> resultsItemlist = Util.getElements(resultsItems);
		System.out.println("Total Number of items Displayed :" + resultsItemlist.size());

		for(WebElement e : resultsItemlist) {
			if(e.getText().equals(ProductName)) {
				e.click();
				break;
			}

		}

		return new ProductInfoPage(driver);
	}

} 
