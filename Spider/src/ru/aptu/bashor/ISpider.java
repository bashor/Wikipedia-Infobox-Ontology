package ru.aptu.bashor;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/14/11
 * Time: 2:12 AM
 */
public interface ISpider {
    void Run(IWikiTitleIterator wikiTitleIterator, IWikiPageProcessor processor) throws IOException;
}
