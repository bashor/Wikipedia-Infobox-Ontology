package ru.aptu.bashor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/30/11
 * Time: 12:47 AM
 */
public class InfoboxParser {
    private static final String InfoboxPrefix = "{{Infobox";

    static public void parseAll(Iterable<String> infoboxes) {

    }

    static public void parseAll(Iterable<String> infoboxes, IInfoboxParserResultProcessor resultProcessor) {
        for (String infobox : infoboxes) {
            if (!infobox.startsWith(InfoboxPrefix)) {
                continue;
            }

            Map<String, String> dic = new HashMap<String, String>();
//            String[] temp = infobox.split("\\n", 2);
//            String header, body;
//            if (temp.length >=2) {
//                header = temp[0];
//                body = temp[1];
//            }

            for (String line : infobox.split("\\n")) {
                String[] l = line.split("=", 2);
                if (l.length >= 2) {
                    String key = l[0].replaceAll("(^\\W*)|(\\s*$)", "");
                    String value = l[1].trim();
                    dic.put(key, value);
                }
            }

            resultProcessor.add(dic);
        }
    }


}
