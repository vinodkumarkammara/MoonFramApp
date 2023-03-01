package AddressTestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CRUD.Address;
import CRUD.AddressPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AddressTest {

	private WebDriver driver;
	private AddressPage addressPage;
	@BeforeTest
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://a.testaddressbook.com/sign_in");
		driver.findElement(By.id("session_email")).sendKeys("kumar95530@gmail.com");
		driver.findElement(By.id("session_password")).sendKeys("9553022972");
		driver.findElement(By.name("commit")).click();		
		addressPage = new AddressPage(driver); 

	}
	@Test
	public void createAddressTest() throws InterruptedException {
		Address address = new Address("Vinod", "Automation", "Hyderabad", "Hyderabad", "11111");
		String msg = addressPage.addAddress(address);
		Assert.assertEquals(msg, "Address was successfully created.");

	}
	@Test
	public void updateAddressTest() throws InterruptedException {
		Address address = new Address("Kumar", "Automation", "Hyderabad", "Hyderabad", "11111");
		addressPage.addAddress(address);
		address.setFirstName("Vinod");
		address.setCity("Dubai");
		String msg = addressPage.updateAddress(address, address.getFirstName());
		Assert.assertEquals(msg, "Address was successfully updated.");
	}
	@Test
	public void deleteAddressTest() throws InterruptedException {
		Address address = new Address("KumarDelete", "Automation Delete", "Hyderabad Delete", "Hyderabad Delete", "11111");
		addressPage.addAddress(address);
		String successMsg = addressPage.deleteAddress("KumarDelete");
		Assert.assertEquals(successMsg, "Address was successfully destroyed.");
	}
	@Test
	public void showAddressTest() throws InterruptedException {
		Address address = new Address("KumarShow", "Automation Show", "Hyderabad Show", "Hyderabad Show", "11111");
		addressPage.addAddress(address);
		String actfistname = addressPage.getAddress("KumarShow");
		Assert.assertEquals(actfistname, address.getFirstName());
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	} 

}
