package ru.aptu.bashor;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/14/11
 * Time: 2:17 AM
 */
public interface IWikiPageProcessor {
    //    void processCategoryMember(String data);
    String[] processPage(String title, String content);
}
