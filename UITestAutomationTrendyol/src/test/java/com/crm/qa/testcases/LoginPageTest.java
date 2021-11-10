package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LoginPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.*;

public class LoginPageTest extends TestBase {

    LoginPage loginPage;


    public LoginPageTest() {
        super();
    }

    @BeforeClass
    public void setUp(){
        initialization();

        loginPage = new LoginPage();
    }

    @Test(priority = 0,description = "verifying login page title test")
    @Severity(SeverityLevel.NORMAL)
    public void loginPageTitleTest(){
        loginPage.validateLoginPageTitle();
    }

    @Test(priority = 1,description = "login button hover")
    @Severity(SeverityLevel.NORMAL)
    public void loginButtonTest(){
        loginPage.hoverOnLoginButtonAndClick();
    }

    @Test(priority = 2,description = "login with username: {0} and password: {1} step...")
    @Severity(SeverityLevel.CRITICAL)
    public void loginTest(){
        loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
