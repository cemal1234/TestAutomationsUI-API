package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

public class ShirtSearch extends TestBase {

    // ShirtSearch Page Locators:

    @FindBy(className = "search-box")
    WebElement searchBar;

    @FindBy(xpath = "//b[contains(text(),'Gömlek')]")
    WebElement searchIcon;

    @FindBy(xpath = "//div[@class='fltrs']//div[contains(text(),'TRENDYOLMİLLA')]")
    WebElement brandFilter;

    @FindBy(xpath = "//div[@class='prdct-cntnr-wrppr']/div//i[@class='fvrt-btn']")
    List<WebElement> findProductFavorite;

    @FindBy(xpath = "//p[contains(text(),'Favorilerim')]")
    WebElement favoriteButton;

    @FindBy(xpath = "//span[contains(text(),'Tüm Favoriler')]")
    WebElement allFavoriteButton;

    @FindBy(xpath = "(//span[@class='prdct-desc-cntnr-ttl'])[1]")
    WebElement favoriteProductName;

    @FindBy(xpath = "(//span[contains(text(),'Sepete Ekle')])[1]")
    WebElement addCartButtonFav;

    @FindBy(xpath = "(//span[@class='text-multiple-size '][contains(text(),'Beden')])[1]")
    WebElement sizeText;

    By afterAddToCart = By.xpath("//div[@class='basket-button button-success pointer-event-none ']/span[contains(text(),'Sepete Eklendi')]");

    @FindBy(xpath = "//div[@class='lower-dropdown display-block ty-scrollbar']//ul[@class='lower-dropdown-ul']/li")
    List<WebElement> sizeSelectionCombo;

    // Initializing the Page Objects:

    public ShirtSearch() {
        PageFactory.initElements(driver,this);
    }

    // Actions:

    @Step("'Gömlek' is written in the search field")
    public void enterTextSearchFieldForShirt(){

        sendKeysFunction(searchBar,"Gömlek");
        clickFunction(searchIcon);
        elementVisibilityWait(brandFilter);
        pageTitleControl("Tüm Ürünler - Trendyol");
    }

    @Step("Random product is added to favorites")
    public void randomProductAddFavorite(){

        randomProductAddFavorite(findProductFavorite);
    }

    @Step("Go to favorites and add to cart")
    public void goToFavoriteAndAddToCart(){

        clickFunction(favoriteButton);
        elementVisibilityWait(allFavoriteButton);

        System.out.println("Random Urun Adi : " + favoriteProductName.getText());

         /*
            07-11-2021 tarihine kadar sepete eklenen ürün adedi Sepetim alanında yazıyordu. Testi buna göre yazmıştım,
            Ürün eklendikçe eski ve yeni ürün sayısını kıyaslayarak doğrulama yapıyordu.
            Ancak Pazartesi günü yapı değiştiği için test aşağıdaki şekilde revize edildi.
         */

        clickFunction(addCartButtonFav);
        elementVisibilityWait(sizeText);

        for(WebElement ele : sizeSelectionCombo){
            if(!Objects.equals(ele.getText(), "Tükendi")){
                ele.click();
                break;
            }

        }

        elementInvisibilityWait(afterAddToCart);
        System.out.println("Başarıyla Sepete Eklenen Ürün: " + favoriteProductName.getText());
    }
}
