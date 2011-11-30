package ru.aptu.bashor;

import org.wikipedia.Wiki;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/21/11
 * Time: 3:12 PM
 */
public class WikiJavaProxy implements IWikimedia {
    Wiki wiki = new Wiki();


    public String[] getCategoryMembers(String name) {
        try {
            return wiki.getCategoryMembers(name);
        } catch (Exception e) {
            return new String[0];
        }
    }

    public String getSectionText(String title, int number) {
        try {
            return wiki.getSectionText(title, number);
        } catch (Exception e) {
            return "";
        }
    }
}
