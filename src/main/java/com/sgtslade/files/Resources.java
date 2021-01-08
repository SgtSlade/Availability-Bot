package com.sgtslade.files;

import java.net.URI;
import java.nio.file.Paths;

public class Resources {
    public static URI getResourceURI(String resource){
        return Paths.get(Config.getSection("resourcePath").getTextContent().trim()+"/"+resource).toUri();
    }

}
