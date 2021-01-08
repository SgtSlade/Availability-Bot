package com.sgtslade.files;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLHelper {
    public static Document readXML(String path){
        File xmlfile = new File(path);
        try {
            Document DOM = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlfile);
            DOM.getDocumentElement().normalize();
            return DOM;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
