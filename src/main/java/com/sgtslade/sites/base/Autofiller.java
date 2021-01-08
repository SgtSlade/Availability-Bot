package com.sgtslade.sites.base;

import com.sgtslade.Main;
import com.sgtslade.files.Config;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;

public class Autofiller {
    private Map<String,By> fillPaths = new HashMap<>();

    public void fill(){
        fillPaths.entrySet().forEach(entry->{
            Main.controller.waitAndInputBy(entry.getValue(),getAutofillValue(entry.getKey()));
        });
    }

    public String getAutofillValue(String val){
        return Config.getSection("autofiller").getElementsByTagName(val).item(0).getTextContent().trim();
    }

    public Map<String, By> getFillPaths() {
        return fillPaths;
    }
}
