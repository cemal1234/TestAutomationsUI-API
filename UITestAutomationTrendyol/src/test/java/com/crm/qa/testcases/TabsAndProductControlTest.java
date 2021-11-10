package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.TabsAndProductControl;
import com.crm.qa.pages.LoginPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.*;

public class TabsAndProductControlTest extends TestBase {

    LoginPage loginPage;
    TabsAndProductControl tabsAndProductControl;

    public TabsAndProductControlTest() {
        super();
    }

    @BeforeClass
    public void setUp(){
        initialization();
        loginPage = new LoginPage();
        loginPage.validateLoginPageTitle();
        loginPage.hoverOnLoginButtonAndClick();
        loginPage.login(prop.getProperty("username"),prop.getProperty("password"));

        tabsAndProductControl = new TabsAndProductControl();
    }

    @Test(priority = 0,description = "Menu Bar Hover Over")
    @Severity(SeverityLevel.NORMAL)
    public void menuBarHoverTest(){
        tabsAndProductControl.menuBarHover();
    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
