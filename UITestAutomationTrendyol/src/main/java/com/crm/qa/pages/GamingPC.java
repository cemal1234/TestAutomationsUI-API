package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

public class GamingPC extends TestBase {

    // GamingPC Page Locators:

    @FindBy(className = "search-box")
    WebElement searchBar;

    @FindBy(xpath = "//b[contains(text(),'Oyuncu Bilgisayarı')]")
    WebElement searchIcon;

    @FindBy(xpath = "//div[@class='fltrs']//div[contains(text(),'Monster')]")
    WebElement brandFilter;

    @FindBy(xpath = "//div[@class='dscrptn']")
    WebElement numberOfProducts;

    @FindBy(xpath = "//div[@class='chckbox chckd']")
    WebElement brandFilterCheckControl;

    @FindBy(xpath = "//input[@class='fltr-srch-prc-rng-input min']")
    WebElement minPricesFilter;

    @FindBy(xpath = "//input[@class='fltr-srch-prc-rng-input max']")
    WebElement maxPricesFilter;

    @FindBy(className = "fltr-srch-prc-rng-srch")
    WebElement pricesSearchButton;

    @FindBy(xpath = "//div[@class='slctd-fltr-item']/span[contains(text(),'3000 TL - 10000 TL')]")
    WebElement pricesFilterCheckControl;

    String productTextNotFound = "\"\n" +
            "Oyuncu Bilgisayarı\n" +
            "\" aramanız için ürün bulunamadı. Aşağıdakiler ilginizi çekebilir.";

    @FindBy(xpath = "//div[@class='prdct-cntnr-wrppr']/div")
    List<WebElement> findProduct;

    @FindBy(xpath = "//div[@class='add-to-basket-button-text'][contains(text(),'Sepete Ekle')]")
    WebElement addCartButton;

    @FindBy(className = "add-to-basket-button-text-success")
    WebElement completeOrder;

    // Initializing the Page Objects:

    public GamingPC() {
        PageFactory.initElements(driver,this);
    }

    // Actions:

    @Step("'Gaming PC' is written in the search field")
    public void enterTextSearchFieldForPC(){

        sendKeysFunction(searchBar,"Oyuncu Bilgisayarı");
        clickFunction(searchIcon);
        elementVisibilityWait(brandFilter);
        pageTitleControl("Tüm Ürünler - Trendyol");
    }

    @Step("'Monster' is selected in brand filtering")
    public void brandFieldSelect(){

        clickFunction(brandFilter);
        elementVisibilityWait(brandFilterCheckControl);
        checkProductVisible(numberOfProducts);
    }

    @Step("Min and Max prices are entered")
    public void pricesFieldSelect(){

        sendKeysFunction(minPricesFilter,"3000");
        sendKeysFunction(maxPricesFilter,"10000");
        clickFunction(pricesSearchButton);

        elementVisibilityWait(pricesFilterCheckControl);
        checkProductVisible(numberOfProducts);


        if(Objects.equals(numberOfProducts.getText(), productTextNotFound)){
            System.out.println("Filtreleme Sonucu: *** İstenilen filtrelere uygun ürün bulunamadı, Rastgele bir ürün seçilecek ***"+"\n");

        }

        elementsVisibilityWait(findProduct);
        randomProductSelect(findProduct);

    }

    @Step("The selected product is added to the cart")
    public void addToCart(){

        /*
            07-11-2021 tarihine kadar sepete eklenen ürün adedi Sepetim alanında yazıyordu. Testi buna göre yazmıştım,
            Ürün eklendikçe eski ve yeni ürün sayısını kıyaslayarak doğrulama yapıyordu.
            Ancak Pazartesi günü yapı değiştiği için test aşağıdaki şekilde revize edildi.
         */

        clickFunction(addCartButton);
        elementVisibilityWait(completeOrder);
        System.out.println("Başarıyla Sepete Eklenen Ürün: " + driver.getTitle());

    }
}
