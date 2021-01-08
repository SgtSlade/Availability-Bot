package com.sgtslade;


import com.sgtslade.sites.base.SiteController;
import com.sgtslade.sites.instances.MediaExpert;
import com.sgtslade.sites.instances.MediaMarkt;
import com.sgtslade.sites.instances.Neonet;
import com.sgtslade.sites.monitoring.Monitor;
import com.sgtslade.sites.monitoring.ObservedItem;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Main {

    public static  FirefoxDriver driver;
    public static FirefoxOptions options;
    public static SiteController controller;
    public static Monitor monitor;

    public static void main(String[] args) {

        options = new FirefoxOptions();
        options.addArguments("--headless");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\serge\\Desktop\\AvailabilityBot\\drivers\\firefoxDriver\\geckodriver.exe");
        driver =  new FirefoxDriver(options);
        driver.manage().window().maximize();

        monitor = new Monitor();
        controller = new SiteController();

        MediaExpert mediaExpert = new MediaExpert();
        MediaMarkt mediaMarkt = new MediaMarkt();
        Neonet neonet = new Neonet();

        monitor.observeItem("konsola sony playstation 5",mediaExpert);
        monitor.observeItem("konsola sony playstation 5",mediaMarkt);
        monitor.observeItem("konsola sony playstation 5",neonet);

        monitor.getItem("konsola sony playstation 5").stream().forEach(item->System.out.println(item.getAvailability()));
    }
}
