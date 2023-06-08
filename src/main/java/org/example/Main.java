package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Main {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void testMethod() throws InterruptedException {
        // 1. Go to the sign in section and use the previously created username/ password to log in to the system.

        String url = "https://magento.softwaretestingboard.com/";
        driver.get(url);

        WebElement signIn = driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div/ul/li[2]/a"));
        signIn.click();

        WebElement username = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        username.sendKeys("srinathdanindu@gmail.com");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"pass\"]"));
        password.sendKeys("Luma-123");

        WebElement signinBtn = driver.findElement(By.xpath("//*[@id=\"send2\"]/span"));
        signinBtn.click();

        // 2. Go to a preferred section (Women/Men/Gear/Training) Click on any product of your choice

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Gear")));

        Actions action = new Actions(driver);

        WebElement mainMenu = driver.findElement(By.linkText("Gear"));
        action.moveToElement(mainMenu).build().perform();

        WebElement subMenu = driver.findElement(By.linkText("Bags"));
        action.moveToElement(subMenu);
        action.click().build().perform();

        WebElement item = driver.findElement(By.className("product-image-photo"));
        item.click();

        // 3. Verify that the product name and price are similar to the selected product, Click on add to cart

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(45));
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/main/div[2]/div/div[1]/div[1]/h1/span")));

        WebElement itemName = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[1]/div[1]/h1/span"));
        String item_Name = itemName.getText();
        Assert.assertEquals(item_Name, "Push It Messenger Bag");

        WebElement itemPrice = driver.findElement(By.className("price"));
        String item_Price = itemPrice.getText();
        Assert.assertEquals(item_Price, "$45.00");

        WebElement cartBtn = driver.findElement(By.id("product-addtocart-button"));
        cartBtn.click();

        // 4. Click on the Continue Shopping button

        // There is no button named 'Continue Shopping' in the current window. So I'm selecting another product by previous method.//

        // 5. Add another product to the cart

        WebElement mainMenu1 = driver.findElement(By.linkText("Women"));
        action.moveToElement(mainMenu1).build().perform();

        WebElement subMenu0 = driver.findElement(By.linkText("Bottoms"));
        action.moveToElement(subMenu0).build().perform();

        WebElement subMenu1 = driver.findElement(By.linkText("Shorts"));
        action.moveToElement(subMenu1);
        action.click().build().perform();

//        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-item-link")));

        WebElement item1 = driver.findElement(By.className("product-item-link"));
        item1.click();

//        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/main/div[2]/div/div[1]/div[4]/form/div[1]/div/div/div[1]/div/div[1]")));

        WebElement size = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[1]/div[4]/form/div[1]/div/div/div[1]/div/div[1]"));
        size.click();
        WebElement color = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[1]/div[4]/form/div[1]/div/div/div[2]/div/div[2]"));
        color.click();
        WebElement cartBtn1 = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[1]/div[4]/form/div[2]/div/div/div[2]/button/span"));
        cartBtn1.click();

        // 6. Go to the Cart and Verify if the value of the Total Products amount equals the sum of the product prices that are added to the cart

//        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("shopping cart")));

        WebElement cart = driver.findElement(By.linkText("shopping cart"));
        cart.click();

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/main/div[3]/div/div[2]/div[1]/div[2]/div/table/tbody/tr[1]/td/span")));

        WebElement total = driver.findElement(By.xpath("/html/body/div[1]/main/div[3]/div/div[2]/div[1]/div[2]/div/table/tbody/tr[1]/td/span"));
        String Total = total.getText();
        Assert.assertEquals(Total, "$90.00");

        // 7. Click on the trash can icon next to the products and remove one from the cart , Verify if the total is updated after the removal of products

        WebElement delete = driver.findElement(By.xpath("/html/body/div[1]/main/div[3]/div/div[2]/form/div[1]/table/tbody[1]/tr[2]/td/div/a[3]"));
        delete.click();

//        WebDriverWait wait6 = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/main/div[3]/div/div[2]/div[1]/div[2]/div/table/tbody/tr[1]/td/span")));

        WebElement total1 = driver.findElement(By.xpath("/html/body/div[1]/main/div[3]/div/div[2]/div[1]/div[2]/div/table/tbody/tr[1]/td/span"));
        String Total1 = total1.getText();
        Assert.assertEquals(Total1, "$45.00");

        // 8. Click on proceed to checkout

        WebElement proceed = driver.findElement(By.xpath("/html/body/div[1]/main/div[3]/div/div[2]/div[1]/ul/li[1]/button/span"));
        proceed.click();

        // 9. Fill in the necessary information and proceed

        try {

            wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form/div/fieldset/div/div[1]/div/input")));

            WebElement address = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form/div/fieldset/div/div[1]/div/input"));
            address.sendKeys("No.111");
            WebElement address1 = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form/div/fieldset/div/div[2]/div/input"));
            address1.sendKeys("Victoria Park");
            WebElement address2 = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form/div/fieldset/div/div[3]/div/input"));
            address2.sendKeys("Kamburugamuwa");

            WebElement city = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form/div/div[4]/div/input"));
            city.sendKeys("Matara");
            WebElement zip = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form/div/div[7]/div/input"));
            zip.sendKeys("81000");
            WebElement ph = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form/div/div[9]/div/input"));
            ph.sendKeys("0112112112");

            WebElement radiobutton = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[4]/ol/li[2]/div/div[3]/form/div[1]/table/tbody/tr/td[1]/input"));
            radiobutton.click();

            WebElement countryDropdown = driver.findElement(By.name("country_id"));
            Select country = new Select(countryDropdown);

            country.selectByValue("LK");

            wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[4]/ol/li[2]/div/div[3]/form/div[1]/table/tbody/tr/td[1]/input")));

            WebElement next = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[4]/ol/li[2]/div/div[3]/form/div[3]/div/button/span"));
            next.click();

        }
        catch (Exception e) {
            wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[4]/ol/li[2]/div/div[3]/form/div[1]/table/tbody/tr/td[1]/input")));

            WebElement next = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[4]/ol/li[2]/div/div[3]/form/div[3]/div/button/span"));
            next.click();
        }

        // 10. Verify if the URL is equal to https://magento.softwaretestingboard.com/checkout/#payment

        wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[3]/div/form/fieldset/div[1]/div/div/div[2]/div[2]/div[4]/div/button")));
        Thread.sleep(3000);

        String currentUrl = driver.getCurrentUrl();
        String givenUrl = "https://magento.softwaretestingboard.com/checkout/#payment";
        Assert.assertEquals(currentUrl,givenUrl);

        // 11. Click on Place Order

        WebElement placeOrderBtn = driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[3]/div/form/fieldset/div[1]/div/div/div[2]/div[2]/div[4]/div/button/span"));
        placeOrderBtn.click();

        // 12. Finally, Verify the Thank you for your purchase! message

        wait1.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("/html/body/div[1]/main/div[1]/h1/span"),"Thank you for your purchase!"));

        WebElement tyText = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/h1/span"));
        Assert.assertEquals(tyText.getText(),"Thank you for your purchase!");

    }
}
