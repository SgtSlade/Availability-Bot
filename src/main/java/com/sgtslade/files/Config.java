package com.sgtslade.files;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Config {
    private static final String configPath = "src/main/resources/config.xml";

    public static Element getSection(String section){
        Document DOM = XMLHelper.readXML(configPath);
        return (Element)DOM.getElementsByTagName(section).item(0);
    }
}
