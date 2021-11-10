package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

    // Login Page Locators:

    @FindBy(className = "modal-close")
    WebElement popUpSelect;

    @FindBy(xpath = "//div[@class='account-nav-item user-login-container']")
    WebElement myAccount;

    @FindBy(className = "login-button")
    WebElement login;

    @FindBy(xpath = "//div[@class='lr-title']/h3")
    WebElement loginPageTextControl;

    @FindBy(id = "login-email")
    WebElement username;

    @FindBy(id = "login-password-input")
    WebElement password;

    @FindBy(xpath = "(//span[contains(text(),'Giriş Yap')])[2]")
    WebElement logOn;

    By ivisibleLogOn = By.xpath("(//span[contains(text(),'Giriş Yap')])[2]");


    // Initializing the Page Objects:
    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    // Actions:

    @Step("verifying login page title test")
    public void validateLoginPageTitle(){
        clickFunction(popUpSelect);
        pageTitleControl("En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da");
    }

    @Step("login button hover")
    public void hoverOnLoginButtonAndClick(){
        elementHoverOver(myAccount);
        clickFunction(login);
    }

    @Step("login with username: {0} and password: {1} step...")
    public void login(String un, String pass){
        ObjectTextControl(loginPageTextControl,"Trendyol’a giriş yap veya hesap oluştur, indirimleri kaçırma!");
        sendKeysFunction(username,un);
        sendKeysFunction(password,pass);
        clickFunction(logOn);

        elementInvisibilityWait(ivisibleLogOn);
        pageTitleControl("Kadın Butikleri, Yeni Sezon Ürünler - Trendyol");

    }

}
