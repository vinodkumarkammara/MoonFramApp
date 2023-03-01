package CRUD;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddressPage {
	private WebDriver driver;

	private By firstName = By.id("address_first_name");
	private By lastName = By.id("address_last_name");
	private By Address = By.id("address_street_address");
	private By city = By.id("address_city");
	private By zipcode = By.name("address[zip_code]");
	private By Addresses = By.xpath("//a[@data-test='addresses']");
	private By Newaddress = By.linkText("New Address");
	private By createBtn  = By.name("commit");
	private By AlertMsg = By.cssSelector("div.alert.alert-notice");
	private By firstvalue = By.xpath("//span[@data-test='first_name']");

	public AddressPage(WebDriver driver) {
		this.driver = driver;

	}

	private String fillAddressForm(Address address) {
		driver.findElement(firstName).clear();
		driver.findElement(firstName).sendKeys(address.getFirstName());
		driver.findElement(lastName).clear();
		driver.findElement(lastName).sendKeys(address.getLastName());
		driver.findElement(Address).clear();
		driver.findElement(Address).sendKeys(address.getAddress1());
		driver.findElement(city).clear();
		driver.findElement(city).sendKeys(address.getCity());
		driver.findElement(createBtn).click();
		driver.findElement(zipcode).sendKeys(address.getZipcode());
		driver.findElement(createBtn).click();
		return driver.findElement(AlertMsg).getText();
	}


	public void clickAddressLink() {
		driver.findElement(Addresses).click();

	}

	public String addAddress(Address address) throws InterruptedException {
		clickAddressLink();
		Thread.sleep(2000);
		driver.findElement(Newaddress).click();
		return fillAddressForm(address);

	}

	public String updateAddress(Address address , String fName) {
		clickAddressLink();
		driver.findElement(By.xpath("//td[text()='"+fName+"']//following-sibling::td/a[text()='Edit']")).click();
		return fillAddressForm(address);
	}

	public String deleteAddress(String fName) {
		clickAddressLink();
		driver.findElement(By.xpath("//td[text()='"+fName+"']//following-sibling::td/a[text()='Destroy']")).click();
		WebDriverWait wait = new WebDriverWait(driver ,10);
		wait.until(ExpectedConditions.alertIsPresent()).accept();
		return driver.findElement(AlertMsg).getText();
	}

	public String getAddress(String fName) {
		clickAddressLink();
		driver.findElement(By.xpath("//td[text()='"+fName+"']//following-sibling::td/a[text()='Show']")).click();
		return driver.findElement(firstvalue).getText();

	}



}
