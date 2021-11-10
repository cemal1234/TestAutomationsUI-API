package com.crm.qa.base;

import com.crm.qa.util.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Properties prop;
    public static Actions actions;


    public TestBase(){
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(" !!! BURAYA PROJENIN YOLU VERİLMELİ !!! "+
                    "\\UITestAutomationTrendyol\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void initialization(){
        String browserName = prop.getProperty("browser");
        if (browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver"," !!! BURAYA PROJENIN YOLU VERİLMELİ !!! \\UITestAutomationTrendyol\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();

        } else if (browserName.equals("firefox")){
            System.setProperty("webdriver.gecko.driver"," !!! BURAYA PROJENIN YOLU VERİLMELİ !!! \\UITestAutomationTrendyol\\drivers\\geckodriver.exe");
            File pathBinary = new File("C:\\Program Files\\Firefox\\firefox.exe"); // Firefox dizini verilmeli !!!
            FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
            DesiredCapabilities desired = DesiredCapabilities.firefox();
            FirefoxOptions options = new FirefoxOptions();
            desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));

            driver = new FirefoxDriver(options);
        }

        wait = new WebDriverWait(driver,30);
        actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);

        driver.get(prop.getProperty("url"));
    }

    public void clickFunction(WebElement clickElement){
        wait.until(ExpectedConditions.elementToBeClickable(clickElement));

        clickElement.click();
    }

    public void sendKeysFunction(WebElement sendKeysElement, String value){
        wait.until(ExpectedConditions.visibilityOf(sendKeysElement));

        sendKeysElement.sendKeys(value);
    }

    public void checkProductVisible(WebElement productVisible) {
        Assert.assertTrue(productVisible.isDisplayed(),"Product Not Visible");
        System.out.println("Total Product Count: " + productVisible.getText()+"\n");
    }

    public void pageTitleControl(String expected){
        Assert.assertEquals(driver.getTitle(),expected);
        System.out.println("Location : " + driver.getTitle() + "\n");

    }

    public void ObjectTextControl(WebElement actual, String expected){
        wait.until(ExpectedConditions.visibilityOf(actual));
        Assert.assertEquals(actual.getText(),expected);
        System.out.println("Object Text : " + actual.getText());

    }

    /*
    Sepetim'de yer alan ürün adedinin kontrolü için yazıldı,
    Page'lerde bahsi geçen ürün adet kontrolünde kullanmak için.
    Tekrar ihtiyaç olabilir diye saklandı.
     */

    public void ObjectValueControl(int actual, int expected){
        Assert.assertEquals(actual,expected);

    }

    public void elementHoverOver(WebElement hoverElement){
        wait.until(ExpectedConditions.visibilityOf(hoverElement));
        actions.moveToElement(hoverElement).build().perform();

    }

    public void elementVisibilityWait(WebElement elementVisible){
        wait.until(ExpectedConditions.visibilityOf(elementVisible));
    }

    public void elementInvisibilityWait(By Locator){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));
    }

    public void elementsVisibilityWait(List<WebElement> elementList){
        wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
    }

    public void randomProductSelect(List<WebElement> randomProEle){
        System.out.println("Random Urune Gidiliyor."+"\n");

        Random rr = new Random();
        int randomPro = rr.nextInt(randomProEle.size());
        randomProEle.get(randomPro).click();

        driver.getWindowHandles();
        for(String winHandle : driver.getWindowHandles()){

            driver.switchTo().window(winHandle);
            System.out.println("Random Urun Adi : " + driver.getTitle());
        }
    }

    /*
    Ürün varyantlarıyla ilgili popup'lar için yazılan method, muhtemelen
    bu bildirim geri çekildi, test sırasında tekrar karşılaşmadığım için yorum satırına alındı.
    Tekrar ihtiyaç olabilir diye saklandı.
     */

    public void popUpClose(WebElement popUpEle, WebElement emptyAreaClick){
        wait.until(ExpectedConditions.visibilityOf(popUpEle));
        wait.until(ExpectedConditions.elementToBeClickable(emptyAreaClick));
        emptyAreaClick.click();
    }

    public void randomProductAddFavorite(List<WebElement> randomProFav){
        System.out.println("Random Urun Favorilere Ekleniyor."+"\n");

        Random rrr = new Random();
        int randomFav = rrr.nextInt(randomProFav.size());
        randomProFav.get(randomFav).click();

    }

}
