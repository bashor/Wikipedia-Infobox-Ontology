package ru.aptu.bashor;

import org.apache.log4j.Logger;
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
    public static final Logger LOG = Logger.getLogger(WikiJavaProxy.class);
    private final Wiki wiki = new Wiki();

    public Collection<String> getCategoryMembers(String name) {
        try {
            return Arrays.asList(wiki.getCategoryMembers(name));
        } catch (Exception e) {
            LOG.error(e.getMessage() + "\n" + e.getStackTrace());
            return null;
        }
    }

    public String getSectionText(String title, int number) {
        try {
            return wiki.getSectionText(title, number);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            LOG.trace(e.getStackTrace());
            return null;
        }
    }
}
