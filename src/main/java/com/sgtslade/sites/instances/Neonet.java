package com.sgtslade.sites.instances;

import com.sgtslade.Main;
import com.sgtslade.sites.base.ProductDoesntExistException;
import com.sgtslade.sites.base.Site;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Neonet extends Site {
    public Neonet() {
        super("Neonet", "https://www.neonet.pl");
    }


    @Override
    public String getProductUrl(String product) throws ProductDoesntExistException {
        Main.driver.get(getAdress()+"/search.html?order=score&query="+product);
        try{
            WebElement el = Main.controller.waitAndGet(By.className("listingItemCss-nameLink-1rv"),10);
            Pattern pattern = Pattern.compile(product,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(el.getText());
            if(matcher.find()){
                el.click();
                return Main.driver.getCurrentUrl();
            }else {
                throw new ProductDoesntExistException("Product " + product + " doesn't exist");
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
            throw new ProductDoesntExistException("Product " + product + " doesn't exist.");
        }
    }

    @Override
    public boolean productAvailable(String product) {
        try{
            Main.driver.get(getProductUrl(product));
            Main.driver.findElement(By.xpath("//*[contains(text(),'Do koszyka')]"));
            return true;
        }catch (ProductDoesntExistException | NoSuchElementException e){
            return false;
        }
    }

    @Override
    public void addToCart() {
        Main.controller.XPathClickSequence(new String[]{
                "//*[contains(text(),'Do koszyka')]",
                "//*[contains(text(),'Przejdź do koszyka')]"
        });
    }

    @Override
    public void setAutofiller() {
        getAutofiller().getFillPaths().put("fname",By.xpath("//*[@title='Imię']"));
        getAutofiller().getFillPaths().put("lname",By.xpath("//*[@title='Nazwisko']"));
        getAutofiller().getFillPaths().put("email",By.xpath("//*[@title='Adres e-mail']"));
        getAutofiller().getFillPaths().put("phone",By.xpath("//*[@title='Telefon']"));
        getAutofiller().getFillPaths().put("street",By.xpath("//*[@title='Ulica']"));
        getAutofiller().getFillPaths().put("house",By.xpath("//*[@title='Nr domu/lokalu']"));
        getAutofiller().getFillPaths().put("city",By.xpath("//*[@title='Miasto']"));
        getAutofiller().getFillPaths().put("postcode",By.xpath("//*[@title='Kod pocztowy']"));
    }

    @Override
    public void finalizePurchase() {
        Main.controller.waitAndClickIfExists(By.className("snrs-closeButton"),10);
        Main.controller.XPathClickSequence(new String[]{
                "//div[contains(text(),'Kurier')]",
                "//div[contains(text(),'Płatność gotówką przy odbiorze +10 PLN')]",
                "//*[contains(text(),'Płacę gotówką')]",
                "//*[contains(text(),'Dalej')]",
                "//*[contains(text(),'Kontynuuj bez logowania')]",
        });
        getAutofiller().fill();
        Main.controller.XPathClickSequence(new String[]{
                "//*[@for='agreement-all']",
                "//*[contains(text(),'Dalej')]",
        });
        Main.controller.waitAndClickBy(By.cssSelector("[class='calendarDesktopCss-element-3Zc  ']"));
        Main.controller.waitAndClickBy(By.xpath("//*[contains(text(),'Dalej')]"));
    }
}
