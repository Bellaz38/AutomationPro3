import Application.*;
import Utilities.Credential;
import Utilities.Drivers;
import com.poiji.bind.Poiji;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

public class runTests {
    private WebDriver webDriver;


    //    @BeforeTest
//    public void reset() {
//        drivers = new Drivers();
//    }
    @Parameters("browser")
    @BeforeTest
    public void setup() {
        String browser = "chrome";
        System.out.println("Browser: "+browser);
        Drivers drivers = new Drivers();
        if (browser.equals("chrome")) {
            webDriver = drivers.getNewChrome();
        } else if (browser.equals("edge")) {
            webDriver = drivers.getNewEdge();
        }
    }
    @Test
    public void execute() {
        ClassLoader classLoader = runTests.class.getClassLoader();
        URL resource = classLoader.getResource("Data.xlsx");
        assert resource != null;
        List<Credential> Credential = Poiji.fromExcel(new File(resource.getPath()), Credential.class);
        try {
            Login2 login = new Login2(webDriver);
            Cart cart = new Cart(webDriver);
            Products product = new Products(webDriver);
            Confirmation confirmation = new Confirmation(webDriver);
            Checkout checkout = new Checkout(webDriver);


            login.loadWebsite();
            login.ClickOnDismiss();
            login.ClickMyAccount();
            Thread.sleep(3000);

            //Invalid Login
            login.username(Credential.get(0).getUserName());
            login.password(Credential.get(0).getPassword());
            login.login();
            //String errorMessage = "//*[@id=\"post-8\"]/div/div/div[1]/div";
            ///Assert.assertEquals(webDriver.findElement(By.xpath(errorMessage)).isDisplayed(),
            // "Error message displayed: " + errorMessage);

            //Valid Login add to Cart
            login.loadWebsite();
            login.ClickMyAccount();
            WebElement webElement = login.username(Credential.get(1).getUserName());
            Assert.assertEquals(webElement.getAttribute("value"),Credential.get(1).getUserName());

            login.password(Credential.get(1).getPassword());
            login.login();

            cart.ToolsQADemoSite();
            product.Item1();
            product.ColorOption();
            product.SizeOption();
            cart.Add2Cart();
            login.ClickMyAccount();
            login.LogOut();

            //Complete Order
            login.loadWebsite();
            login.ClickMyAccount();
            login.username(Credential.get(1).getUserName());
            login.password(Credential.get(1).getPassword());
            login.login();
            cart.ToolsQADemoSite();
            cart.Search();
            cart.Search2();
            product.ColorOption2();
            product.SizeOption2();
            cart.Add2Cart2();
            confirmation.ViewCart();
            checkout.Proceed2Checkout();
            confirmation.NameField();
            confirmation.SurNameField();
            confirmation.CompanyField();
            confirmation.termsAndConditions();
            confirmation.PlaceOrder();

            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
    @AfterTest
    public void tearDown() {
        if (webDriver != null) {
            //webDriver.quit();

        }
    }

}
