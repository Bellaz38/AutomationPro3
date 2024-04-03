package Application;

import Utilities.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login2 {
    private WebDriver webDriver = null;

    public Login2(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void loadWebsite() {

        String url = Util.BASE_URL_STRING;
        this.webDriver.get(url);
        this.webDriver.manage().window().maximize();

    }

    private WebElement ClickDismiss(WebDriver webDriver) {
        return webDriver.findElement(By.xpath("/html/body/p[1]/a"));
    }
    public void ClickOnDismiss() {
        ClickDismiss(this.webDriver).click();

    }

    private WebElement MyAccount(WebDriver webDriver) {
        return webDriver.findElement(By.xpath("//*[@id=\"noo-site\"]/header/div[1]/div/ul[2]/li[2]/a"));
    }
    public void ClickMyAccount() {
        MyAccount(this.webDriver).click();

    }
//    public static void username( WebDriver driver, String username) {
//        //driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(username);
//        WebElement userName = driver.findElement(By.id("username"));
//        System.out.println("userName: "+userName);
//        userName.sendKeys("Testing 234");
//    }

    public WebElement username( String username) {
        //driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(username);
        WebElement webElement = this.webDriver.findElement(By.id("username"));
        webElement.sendKeys(username);

        return webElement;
    }

    public void password(String password) {
        this.webDriver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
    }

    public void login() {
        //driver.findElement(By.className("woocommerce-button button woocommerce-form-login__submit")).click();
        this.webDriver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[1]/form/p[3]/button")).click();
    }

    private WebElement LogOut(WebDriver webDriver) {
        return webDriver.findElement(By.xpath("//*[@id=\"post-8\"]/div/div/nav/ul/li[6]/a"));
    }

    public void LogOut() {
        LogOut(this.webDriver).click();
    }
}
