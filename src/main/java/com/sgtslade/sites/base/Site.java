package com.sgtslade.sites.base;
import com.sgtslade.Main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class Site implements Automated {
    private String name;
    private String adress;
    private String icon;
    private Autofiller autofiller = new Autofiller();

    public Site(String name, String adress) {
        this.name = name;
        this.adress = adress;
        icon = name + ".jpg";
        setAutofiller();
    }

    public Document getDoc(String url){
        Main.driver.get(url);
        return Jsoup.parse(Main.driver.getPageSource());
    }

    @Override
    public void buyProduct(String productURL) {
        Main.driver.get(productURL);
        addToCart();
        finalizePurchase();
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Autofiller getAutofiller() {
        return autofiller;
    }

    public void setAutofiller(Autofiller autofiller) {
        this.autofiller = autofiller;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
