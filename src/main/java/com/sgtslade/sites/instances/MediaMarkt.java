package com.sgtslade.sites.instances;

import com.sgtslade.Main;
import com.sgtslade.sites.base.ProductDoesntExistException;
import com.sgtslade.sites.base.Site;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

public class MediaMarkt extends Site {

    public MediaMarkt() {
        super("Media Markt", "http://mediamarkt.pl");
    }

    @Override
    public String getProductUrl(String product) throws ProductDoesntExistException {
        Main.driver.get(getAdress());
        Main.controller.waitAndInputBy(By.xpath("//*[@placeholder='Czego szukasz?']"),product);
        Main.controller.waitAndClickBy(By.id("js-triggerSearch"));
        try {
            Main.controller.waitAndClickBy(By.className("b-ofr_headDataTitle"));
            return Main.driver.getCurrentUrl();
        }catch (NoSuchElementException| TimeoutException e){
            throw new ProductDoesntExistException("Product " + product +" doesn't exist");
        }
    }

    @Override
    public boolean productAvailable(String productName) {
        try {
            getProductUrl(productName);
            return true;
        }catch (ProductDoesntExistException e){
            return false;
        }
    }

    @Override
    public void addToCart() {
        Main.controller.waitAndClickBy(By.id("js-addToCart"));
        Main.controller.waitAndClickBy(By.xpath("//*[@data-target='/koszyk/lista']"));
    }

    @Override
    public void setAutofiller() {
        getAutofiller().getFillPaths().put("email",By.id("cart_flow_address_step_accountAddress_email"));
        getAutofiller().getFillPaths().put("fname",By.id("cart_flow_address_step_accountAddress_firstName"));
        getAutofiller().getFillPaths().put("lname",By.id("cart_flow_address_step_accountAddress_lastName"));
        getAutofiller().getFillPaths().put("street",By.id("cart_flow_address_step_accountAddress_street"));
        getAutofiller().getFillPaths().put("house",By.id("cart_flow_address_step_accountAddress_houseNumber"));
        getAutofiller().getFillPaths().put("apartment",By.id("cart_flow_address_step_accountAddress_apartmentNumber"));
        getAutofiller().getFillPaths().put("phone",By.id("cart_flow_address_step_accountAddress_phone_number"));
    }

    @Override
    public void finalizePurchase() {
        Main.controller.waitAndInputBy(By.xpath("//*[@placeholder='Tu wpisz kod']"),"02785");
        Main.controller.XPathClickSequence(new String[]{
                "//*[contains(text(),'Zastosuj')]",
                "//*[@data-delivery='dostawa']",
                "//*[@data-payment='Za pobraniem']"});
        Main.controller.waitAndClickBy(By.id("js-cartNext"));
        Main.controller.waitAndClickBy(By.xpath("//*[contains(text(),'Kontynuuj bez logowania')]"));
        getAutofiller().fill();
        Main.controller.waitAndClickBy(By.xpath("//*[contains(text(),'Zaznacz wszystkie')]"));
        Main.controller.waitAndClickBy(By.xpath("//*[contains(text(),'Dalej ›')]"));
    }
}
