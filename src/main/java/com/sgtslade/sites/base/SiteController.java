package com.sgtslade.sites.base;

import com.sgtslade.Main;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class SiteController {
    private static final WebDriverWait waiter = new WebDriverWait(Main.driver,15);

    public void waitAndClickBy(By by){
        waiter.until(ExpectedConditions.presenceOfElementLocated(by));
        WebElement el = Main.driver.findElement(by);
            try {
                if(el.isDisplayed()){
                    waiter.until(ExpectedConditions.elementToBeClickable(el));
                    el.click();
                }else {
                    int scrollY = el.getLocation().y;
                    Main.driver.executeScript("window.scroll(0," + scrollY + ")");
                    waiter.until(ExpectedConditions.elementToBeClickable(el));
                    el.click();
                }
            }catch (TimeoutException e){
                e.printStackTrace();
            }catch (ElementClickInterceptedException | StaleElementReferenceException e){
                waitAndClickBy(by);
            }
    }

    public boolean elementExists(By by){
        try{
            Main.driver.findElement(by);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void waitAndClickIfExists(By by,int timeout){
        try {
            new WebDriverWait(Main.driver, timeout).until(ExpectedConditions.presenceOfElementLocated(by));
            Main.driver.findElement(by).click();
        }catch (TimeoutException e){
            e.printStackTrace();
        }
    }

    public WebElement waitAndGet(By by,int timeout){
        try{
            new WebDriverWait(Main.driver, timeout).until(ExpectedConditions.presenceOfElementLocated(by));
            return Main.driver.findElement(by);
        }catch (TimeoutException e){
            e.printStackTrace();
            return null;
        }
    }

    public void waitAndInputBy(By by, String input){
        waiter.until(ExpectedConditions.presenceOfElementLocated(by));
        WebElement el = Main.driver.findElement(by);
        try {
            el.sendKeys(input);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void XPathClickSequence(String[] clicks){
        Arrays.stream(clicks).forEach(s->waitAndClickBy(By.xpath(s)));
    }

    public static WebDriverWait getWaiter() {
        return waiter;
    }
}
