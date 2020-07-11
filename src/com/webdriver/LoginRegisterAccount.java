package com.webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginRegisterAccount {
	WebDriver driver;

	/*
	 * TC01 - Login with empty Email and Password TC02 - Login with invalid
	 * Email TC03 - Login with Password < 6 characters TC04 - Login with
	 * incorrect Password TC05 - Login with valid Email and Password TC06 -
	 * Create a new account
	 */
	@BeforeClass
	public void beforeClass() {
		//
		// System.setProperty("webdriver.chrome.driver",
		// "D:\\data\\autotest\\workspace\\1.WebDriverAPI\\LoginRegisterProject\\driver\\chromedriver.exe");
		// driver = new ChromeDriver();

		driver = new FirefoxDriver();

		// Step 1: Truy cập vào trang http://live.demoguru99.com/
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 2: Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.linkText("MY ACCOUNT")).click();

	}

	/*
	 * TC01 - Login with empty Email and Password Step 1: Truy cập vào trang
	 * http://live.demoguru99.com/ Step 2: Click vào link "My Account" để tới
	 * trang đăng nhập Step 3: Để trống Username/Password Step 4: Click Login
	 * Button Step 5: Verify error message xuất hiện tại 2 trường là: This is a
	 * required field
	 */
	@Test
	public void TC01_LoginWithEmptyEmailandPassword() {
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		String requiredEmail = driver.findElement(By.xpath(".//*[@id='advice-required-entry-email']")).getText();
		System.out.println("Text Email Required: " + requiredEmail);

		String requiredPassword = driver.findElement(By.xpath(".//*[@id='advice-required-entry-pass']")).getText();
		System.out.println("Text Password Required: " + requiredEmail);

		Assert.assertEquals(requiredEmail, "This is a required field.");
		Assert.assertEquals(requiredPassword, "This is a required field.");

	}

	/*
	 * TC02 - Login with invalid Email Step 1: Truy cập vào trang
	 * http://live.demoguru99.com/ Step 2: Click vào link "My Account" để tới
	 * trang đăng nhập Step 3: Nhập vào email invalid ababababa@kdad/ password
	 * invalid 123456 Step 4: Click Login Button Step 5: Verify error message
	 * xuất hiện tại: Invalid login or password.
	 */
	@Test
	public void TC02_LoginWithEmailInvalid() {
		WebElement txtEmail = driver.findElement(By.id("email"));
		txtEmail.clear();
		txtEmail.sendKeys("adkjk@gmail.com");

		WebElement txtPassword = driver.findElement(By.id("pass"));
		txtPassword.clear();
		txtPassword.sendKeys("123456");

		driver.findElement(By.xpath("//button[@title='Login']")).click();

		String messageError = driver.findElement(By.xpath("//li[@class='error-msg']/ul/li/span")).getText();
		System.out.println("Error Message: " + messageError);

		Assert.assertEquals(messageError, "Invalid login or password.");

	}

	/*
	 * TC03 - Login with Password < 6 characters Step 1: Truy cập vào trang
	 * http://live.demoguru99.com/ Step 2: Click vào link "My Account" để tới
	 * trang đăng nhập Step 3: Nhập vào email valid automation@gmail.com/
	 * password invalid 1234 Step 4: Click Login Button Step 5: Verify error
	 * message xuất hiện tại: Please
	 */
	@Test
	public void TC03_LoginWithPasswordLess6Characters() {
		WebElement txtEmail = driver.findElement(By.id("email"));
		txtEmail.clear();
		txtEmail.sendKeys("automation@gmail.com");

		WebElement txtPassword = driver.findElement(By.id("pass"));
		txtPassword.clear();
		txtPassword.sendKeys("1234");

		driver.findElement(By.xpath("//button[@title='Login']")).click();

		String messageError = driver.findElement(By.id("advice-validate-password-pass")).getText();
		System.out.println("Error Message: " + messageError);

		Assert.assertEquals(messageError, "Please enter 6 or more characters without leading or trailing spaces.");

	}

	/*
	 * TC04 - Login with incorrect Password Step 1: Truy cập vào trang
	 * http://live.demoguru99.com/ Step 2: Click vào link "My Account" để tới
	 * trang đăng nhập Step 3: Nhập vào email valid automation@gmail.com/
	 * password invalid 12332123 Step 4: Click Login Button Step 5: Verify error
	 * message xuất hiện tại: Please
	 */
	@Test
	public void TC04_LoginWithPasswordIncorrect() {
		WebElement txtEmail = driver.findElement(By.id("email"));
		txtEmail.clear();
		txtEmail.sendKeys("automation@gmail.com");
		WebElement txtPassword = driver.findElement(By.id("pass"));
		txtPassword.clear();
		txtPassword.sendKeys("1234324222");

		driver.findElement(By.xpath("//button[@title='Login']")).click();

		String messageError = driver.findElement(By.xpath("//li[@class='error-msg']/ul/li/span")).getText();
		System.out.println("Error Message: " + messageError);

		Assert.assertEquals(messageError, "Invalid login or password.");
	}

	/*
	 * TC05 - Login with valid Email and Password Step 1: Truy cập vào trang
	 * http://live.demoguru99.com/ Step 2: Click vào link "My Account" để tới
	 * trang đăng nhập Step 3: Nhập vào email valid automation@gmail.com/
	 * password valid 123123 Step 4: Click Login Button Step 5: Verify các thông
	 * tin sau được xuất hiện: My Account, Account Dashboard, Account
	 * Information, My Wishlist, My Applications Step 6: Click vào Account Link
	 * trên header => Click Log Out để Sigout
	 */
	@Test
	public void TC05_LoginWithEmailandPasswordValid() {

		// Step 3: Nhập vào email valid automation@gmail.com/ password valid
		// 123123
		WebElement txtEmail = driver.findElement(By.id("email"));
		txtEmail.clear();
		txtEmail.sendKeys("automation@gmail.com");
		WebElement txtPassword = driver.findElement(By.id("pass"));
		txtPassword.clear();
		txtPassword.sendKeys("123123");
		// 4: Click Login Button
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		// 5: Verify các thông tin sau được xuất hiện: My Account, Account
		// Dashboard, Account, Information, My Wishlist, My Applications
		String textMyDashBoard = driver.findElement(By.xpath("//div[@class='page-title']")).getText();
		System.out.println("My DashBoard: " + textMyDashBoard);
		Assert.assertEquals(textMyDashBoard, "MY DASHBOARD");

		String textHello = driver.findElement(By.xpath("//p[@class='hello']/strong")).getText();
		System.out.println("Text Hello: " + textHello);
		Assert.assertEquals(textHello, "Hello, Automation Testing!");

		String textAutomationTesting = driver.findElement(By.xpath("//div[@class='box-content']/p")).getText();
		System.out.println("Text AutomationTesting: " + textAutomationTesting);

		boolean testAutomationTesting = textAutomationTesting.contains("Automation Testing");
		boolean testEmailAutomationTesting = textAutomationTesting.contains("automation@gmail.com");

		Assert.assertEquals(testAutomationTesting, true);
		Assert.assertEquals(testEmailAutomationTesting, true);

		// Step 6: Click vào Account Link trên header => Click Log Out để Sigout

		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']/a")).click();
		driver.findElement(By.xpath("//div[@id='header-account']/div/ul/li[last()]/a")).click();
	}

	/*
	 * TC06 - Create a new account Step 1: Truy cập vào trang
	 * http://live.demoguru99.com/ Step 2: Click vào link "My Account" để tới
	 * trang đăng nhập Step 3: Create An Account button để tới trang đăng ký tài
	 * khoản Step 4: Nhập thông tin hợp lệ vào tất cả các field:
	 * FirstName/LastName/Email Address/Password/Confirm Password (Lưu ý: tạo
	 * random cho dữ liệu tại fiel Email Address) Step 5: Click Register button
	 * Step 6: Verify message xuất hiện khi đăng ký thành công: Thank you...
	 * Step 7: Verifytạo mới với thông tin FirstName/LastName/Email hiển thị ở
	 * trạng My Dashboard Step 8: Logout khỏi hệ thống Step 9: Kiểm tra hệ thống
	 * navigate về Home page sau khi logout thành công
	 */
	@Test
	public void TC06_LoginWithPasswordIncorrect() {

		// Step 2: Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.linkText("MY ACCOUNT")).click();
		// Step 3: Create An Account button để tới trang đăng ký tài khoản
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		// Step 4: Nhập thông tin hợp lệ vào tất cả các
		// field:FirstName/LastName/Email Address/Password/Confirm Password (Lưu
		// ý: tạo random cho dữ liệu tại fiel Email Address) Step 5: Click
		// Register button
		driver.findElement(By.id("firstname")).sendKeys("KTHT");
		driver.findElement(By.id("middlename")).sendKeys("MIDDLE");
		driver.findElement(By.id("lastname")).sendKeys("Last");

		driver.findElement(By.id("email_address")).sendKeys(randomEmail());

		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		// Step 5: Click Register button
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		// Step 6: Verify message xuất hiện khi đăng ký thành công: Thank you...
		String textSuccesNotify = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
		System.out.println(textSuccesNotify);
		Assert.assertEquals(textSuccesNotify, "Thank you for registering with Main Website Store.");

		// Step 7: Verifytạo mới với thông tin FirstName/LastName/Email hiển thị
		// ở trạng My Dashboard

		//
		// String textSuccesNotify =
		// driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
		// System.out.println(textSuccesNotify);
		// Assert.assertEquals(textSuccesNotify, "Thank you for registering with
		// Main Website Store.");
	}

	public String randomEmail() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		System.out.println(generatedString);
		return generatedString + "@gmail.com";
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
