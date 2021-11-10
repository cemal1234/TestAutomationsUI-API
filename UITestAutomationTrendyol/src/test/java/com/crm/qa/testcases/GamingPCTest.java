package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.GamingPC;
import com.crm.qa.pages.LoginPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.*;

public class GamingPCTest extends TestBase {

    LoginPage loginPage;
    GamingPC gamingPC;

    public GamingPCTest(){
        super();
    }


    @BeforeClass
    public void setUp(){
        initialization();
        loginPage = new LoginPage();
        loginPage.validateLoginPageTitle();
        loginPage.hoverOnLoginButtonAndClick();
        loginPage.login(prop.getProperty("username"),prop.getProperty("password"));

        gamingPC = new GamingPC();
    }

    @Test(priority = 0,description = "'Gaming PC' is written in the search field")
    @Severity(SeverityLevel.NORMAL)
    public void enterTextSearchFieldTest(){
        gamingPC.enterTextSearchFieldForPC();
    }

    @Test(priority = 1,description = "'Monster' is selected in brand filtering")
    @Severity(SeverityLevel.NORMAL)
    public void brandFieldSelectTest(){
        gamingPC.brandFieldSelect();
    }

    @Test(priority = 2,description = "Min and Max prices are entered")
    @Severity(SeverityLevel.NORMAL)
    public void pricesFieldSelectTest(){
        gamingPC.pricesFieldSelect();
    }

    @Test(priority = 3,description = "The selected product is added to the cart")
    @Severity(SeverityLevel.CRITICAL)
    public void addToCartTest(){
        gamingPC.addToCart();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
