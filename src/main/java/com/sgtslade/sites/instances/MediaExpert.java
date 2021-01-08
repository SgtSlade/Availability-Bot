package com.sgtslade.sites.instances;

import com.sgtslade.Main;
import com.sgtslade.sites.base.ProductDoesntExistException;
import com.sgtslade.sites.base.Site;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class MediaExpert extends Site {
    public MediaExpert() {
        super("Media Expert", "https://www.mediaexpert.pl");
    }
    @Override
    public String getProductUrl(String product) throws  ProductDoesntExistException{
        Main.driver.get(getAdress());
        Main.controller.waitAndInputBy(By.xpath("//*[@name='search']"),product);
        Main.controller.waitAndClickBy(By.className("c-search_submitIcon"));
        if(Main.controller.elementExists(By.xpath("//*[contains(text(),'\"Nie udało nam się znaleźć\"')]"))){
            throw new ProductDoesntExistException("Product " + product + " doesn't exist.");
        }
        Main.controller.waitAndClickBy(By.xpath("//*[@data-zone='OFFERBOX_NAME' and ancestor-or-self::*[contains(@class,'c-layout_row c-layout_item is-main is-container')]]"));
        return Main.driver.getCurrentUrl();
    }

    @Override
    public boolean productAvailable(String product) {
        try {
            Main.driver.get(getProductUrl(product));
            Main.driver.findElement(By.xpath("//*[@data-event='OFFERBOX_ADD_TO_CART' and ancestor-or-self::*[@data-settings-class='is-activeStickyMobile']]"));
            return true;
        } catch (NoSuchElementException | ProductDoesntExistException e){
            return false;
        }
    }

    @Override
    public void addToCart() {
        Main.controller.waitAndClickBy(By.xpath("//*[@data-event='OFFERBOX_ADD_TO_CART' and ancestor-or-self::*[@data-settings-class='is-activeStickyMobile']]"));
        Main.controller.waitAndClickBy(By.className("c-modal_close"));
    }

    @Override
    public void setAutofiller() {
        getAutofiller().getFillPaths().put("email",By.xpath("//*[@data-uid='cart_flow_address_step_accountAddress_email_']"));
        getAutofiller().getFillPaths().put("fname",By.xpath("//*[@data-uid='cart_flow_address_step_accountAddress_firstName_']"));
        getAutofiller().getFillPaths().put("lname",By.xpath("//*[@data-uid='cart_flow_address_step_accountAddress_lastName_']"));
        getAutofiller().getFillPaths().put("street",By.xpath("//*[@data-uid='cart_flow_address_step_accountAddress_street_']"));
        getAutofiller().getFillPaths().put("house",By.xpath("//*[@data-uid='cart_flow_address_step_accountAddress_houseNumber_']"));
        getAutofiller().getFillPaths().put("apartment",By.xpath("//*[@data-uid='cart_flow_address_step_accountAddress_apartmentNumber_']"));
        getAutofiller().getFillPaths().put("postcode",By.xpath("//*[@data-uid='cart_flow_address_step_accountAddress_postcode_']"));
        getAutofiller().getFillPaths().put("phone",By.xpath("//*[@data-uid='cart_flow_address_step_accountAddress_phone_']"));
        getAutofiller().getFillPaths().put("city",By.xpath("//*[@data-uid='cart_flow_address_step_accountAddress_city_']"));
    }

    public void finalizePurchase(){
        Main.controller.XPathClickSequence(new String[] {
                "//*[contains(text(),'Gotówka przy odbiorze')]",
                "//*[@data-zone='COOKIES_ACCEPT']",
                "//*[@data-label='Dalej']",
                "//*[@data-label='Kontynuuj bez logowania']",
        });

        getAutofiller().fill();


        Main.controller.XPathClickSequence(new String[]{
                "//*[contains(text(),'Zaznacz wszystkie')]",
                "//*[@data-label='Dalej']",
                "//*[contains(text(),'Dalej')]"
        });
    }
}
