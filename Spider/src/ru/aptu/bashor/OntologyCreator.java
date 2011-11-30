package ru.aptu.bashor;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/30/11
 * Time: 1:21 AM
 */
public class OntologyCreator implements IInfoboxParserResultProcessor {

    public void add(Map<String, String> newObject) {
        for (Map.Entry<String, String> prop : newObject.entrySet()) {
            System.out.println(prop.getKey() + " : " + prop.getValue());
        }
    }
}
