package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class TabsAndProductControl extends TestBase {

    // TabsAndProductControl Page Locators:

    @FindBy(xpath = "//p[contains(text(),'Sepetim')]")
    WebElement shoppingCart;

    @FindBy(xpath = "//div[@id='navigation-wrapper']//ul[@class='main-nav']/li")
    List<WebElement> tabMenu;

    @FindBy(xpath = "//div[@class='sticky-wrapper']/div[@class='component-list component-big-list']/article")
    List<WebElement> componentList;

    @FindBy(css = ".image-container .p-card-img")
    List<WebElement> productImage;

    @FindBy(xpath = "//i[@class='search-icon']")
    WebElement description;

    // Initializing the Page Objects:
    public TabsAndProductControl(){
        PageFactory.initElements(driver,this);
    }

    // Actions:

    @Step("Menu Bar Hover Over")
    public void menuBarHover(){
        elementVisibilityWait(shoppingCart);
        pageTitleControl("Kadın Butikleri, Yeni Sezon Ürünler - Trendyol");

        int tabMenuSize = tabMenu.size();

        for (int i = 0; i < tabMenuSize; i++) {

            elementVisibilityWait(tabMenu.get(i));
            clickFunction(tabMenu.get(i));
            System.out.println("Tab Name: " + driver.getTitle());

            elementVisibilityWait(componentList.get(0));
            System.out.println("Component Name: " + componentList.get(0).findElement(By.xpath("//span/img")).getAttribute("alt"));

            clickFunction(componentList.get(0));

            //actions.click(description).build().perform();

            for (int j = 0; j < 4; j++) {

                elementVisibilityWait(productImage.get(j));
                System.out.println("Product Image Name: " + productImage.get(j).getAttribute("src"));

                Object result = ((JavascriptExecutor) driver).executeScript(
                        "return arguments[0].complete && "+
                                "typeof arguments[0].naturalWidth != \"undefined\" && "+
                                "arguments[0].naturalWidth > 0", productImage.get(j));

                boolean loaded = false;
                if (result instanceof Boolean) {
                    loaded = (Boolean) result;
                    System.out.println(loaded);
                }
            }

        }



    }



}
