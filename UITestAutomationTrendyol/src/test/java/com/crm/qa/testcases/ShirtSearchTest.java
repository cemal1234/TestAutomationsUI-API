package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.ShirtSearch;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ShirtSearchTest extends TestBase {

    LoginPage loginPage;
    ShirtSearch shirtSearch;

    public ShirtSearchTest(){
        super();
    }

    @BeforeClass
    public void setUp(){
        initialization();
        loginPage = new LoginPage();
        loginPage.validateLoginPageTitle();
        loginPage.hoverOnLoginButtonAndClick();
        loginPage.login(prop.getProperty("username"),prop.getProperty("password"));

        shirtSearch = new ShirtSearch();
    }

    @Test(priority = 0,description = "'Gömlek' is written in the search field")
    @Severity(SeverityLevel.NORMAL)
    public void enterTextSearchFieldForShirtTest(){
        shirtSearch.enterTextSearchFieldForShirt();
    }

    @Test(priority = 1,description = "Random product is added to favorites")
    @Severity(SeverityLevel.CRITICAL)
    public void randomProductAddFavoriteTest(){
        shirtSearch.randomProductAddFavorite();
    }

    @Test(priority = 2,description = "Go to favorites and add to cart")
    @Severity(SeverityLevel.CRITICAL)
    public void goToFavoriteAndAddToCartTest(){
        shirtSearch.goToFavoriteAndAddToCart();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
