package ru.aptu.bashor;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/14/11
 * Time: 2:17 AM
 */
public interface IWikiPageProcessor {
    //    void processCategoryMember(String data);
    Collection<String> processPage(String title, String content);
}
