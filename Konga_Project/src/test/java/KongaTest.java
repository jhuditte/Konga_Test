import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KongaTest {
    //Import selenium WebDriver
    private WebDriver driver;
    @BeforeTest
    public void start() throws InterruptedException {
        //Locate the chromedriver
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver-win64/chromedriver.exe");
    }
    @Test (priority = 0)
    public void OpenBrowser() throws InterruptedException {
        //Open Chrome browser
        driver = new ChromeDriver();
        // Input page Url; https://www.konga.com/
        driver.get("https://www.konga.com/");
        Thread.sleep(10000);
        //Maximize the page
        driver.manage().window().maximize();
        //Verify that user is on the correct WebPage
        if (driver.getCurrentUrl().contains("https://www.konga.com/"))
            //pass
            System.out.println("CorrectWebpage");
        else
            //fail
            System.out.println("WrongWebpage");
    }


    @Test (priority = 1)
    public void PositiveLogin() throws InterruptedException {
        //Click on the log-in/sign-up button
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
        //Enter email address in the email field
        driver.findElement(By.id("username")).sendKeys("dimokechukwujudith@gmail.com");
        //Enter password in the password field
        driver.findElement(By.id("password")).sendKeys("JAYdee123");
        //Click on the log-in button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        String expectedUrl = "https://www.konga.com/";
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.contains(expectedUrl))
            //Pass
            System.out.println("SuccessfulLogin");
        else
            //Fail
            System.out.println("UnsuccessfulLogin");
        Thread.sleep(10000);
    }

    @Test (priority = 2)
    public void AddItem() throws InterruptedException {
        //Click on computers and accessories
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();
        Thread.sleep(15000);
        //Click on the Laptop category
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[4]/a")).click();
        Thread.sleep(10000);
        //Click in Apple MacBooks
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[4]/a/ul/li[1]/a/label/span")).click();
        Thread.sleep(10000);
        //Add an Item to cart
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[1]/div/div/div[2]/form/div[3]/button")).click();
        String expectedUrl = "https://www.konga.com/category/accessories-computing-5227";
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.contains(expectedUrl))
            //Pass
            System.out.println("AddedToCart");
        else
            //Fail
            System.out.println("FailledOrder");
        Thread.sleep(10000);
    }

    @Test (priority = 3)
    public void Checkout() throws InterruptedException {
        //Click on my cart
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]/span[1]")).click();
        Thread.sleep(15000);
        //Click on checkout
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")).click();
        Thread.sleep(10000);
        //Verify that user is in the right webpage
        String expectedUrl = "https://www.konga.com/checkout/complete-order";
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.contains(expectedUrl))
            //Pass
            System.out.println("CheckoutOrder");
        else
            //Fail
            System.out.println("InvalidOrder");
    }
    @Test (priority = 4)
    public void InputAddress() throws InterruptedException {
        //Click on change address
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[1]/div[2]/div/button")).click();
        //Click on add delivery address button
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[2]/div[1]/div/button")).click();
        Thread.sleep(10000);
        //click on button to add pre-existing address
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div/form/button")).click();
        Thread.sleep(5000);
        //Click on use this address
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")).click();
        Thread.sleep(5000);
        System.out.println("AddedAddress");
    }

    @Test (priority = 5)
    public void PaymentMethod() throws InterruptedException {
        //Click on pay now button
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input")).click();
        Thread.sleep(5000);
        //Click on continue to payment
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
        Thread.sleep(15000);
        //Locate Web element
        WebElement paymethod = driver.findElement(By.tagName("iframe"));
        //Switch to iframe
        driver.switchTo().frame("kpg-frame-component");
        Thread.sleep(10000);
        //select Card payment method
        WebElement cardpayment = driver.findElement(By.className("Card"));
        cardpayment.click();
        System.out.println("AddPaymentMethod");
        Thread.sleep(10000);
    }
    @Test (priority = 6)
    public void InvalidDetails() throws InterruptedException {
        //Enter invalid card number in card number field
        WebElement carddigit = driver.findElement(By.id("card-number"));
        carddigit.sendKeys("5366123434565678");
        Thread.sleep(3000);
        //Enter expiry date
        WebElement datedigit = driver.findElement(By.id("expiry"));
        datedigit.sendKeys("1223");
        Thread.sleep(3000);
        //Enter invalid cvv
        WebElement cvvdigit = driver.findElement(By.id("cvv"));
        cvvdigit.sendKeys("234");
        Thread.sleep(3000);
        //Confirm that User cannot make payment with invalid card details
        WebElement error = driver.findElement(By.id("card-number_unhappy"));
        System.out.println("IvalidDetails");
    }

    @Test (priority = 7)
    public void Exit() throws InterruptedException {
        //Close iframe
        WebElement exitframe = driver.findElement(By.className("data-card__close"));
        exitframe.click();
        System.out.println("SuccessfulExit");
        Thread.sleep(3000);
    }

    @AfterTest
    public void QuitBrowser(){
        //Quit Browser
        driver.quit();

    }


}
