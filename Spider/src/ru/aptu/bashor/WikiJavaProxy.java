package ru.aptu.bashor;

import org.wikipedia.Wiki;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/21/11
 * Time: 3:12 PM
 */
public class WikiJavaProxy implements IWikimedia {
    private final Wiki wiki = new Wiki();


    public Collection<String> getCategoryMembers(String name) {
        try {
            return Arrays.asList(wiki.getCategoryMembers(name));
        } catch (Exception e) {
            //TODO: write to log
            e.printStackTrace();
            return null;
        }
    }

    public String getSectionText(String title, int number) {
        try {
            return wiki.getSectionText(title, number);
        } catch (Exception e) {
            //TODO: write to log
            e.printStackTrace();
            return null;
        }
    }
}
