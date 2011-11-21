package ru.aptu.bashor; /**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/14/11
 * Time: 2:11 AM
 */

import org.wikipedia.Wiki;

import java.io.IOException;

public class Spider implements ISpider {
    private static final String CATEGORY = "Category:";
    private final Wiki wiki = new Wiki();

    public void Run(IWikiTitleIterator wikiTitleIterator, IWikiPageProcessor wikiPageProcessor) throws IOException {

        while (wikiTitleIterator.hasNext()) {
            String pageTitle = wikiTitleIterator.next();

            if (pageTitle.startsWith(CATEGORY)) {
                String[] members = wiki.getCategoryMembers(pageTitle.replace(CATEGORY, ""));
                for (String str : members)
                    wikiPageProcessor.processCategoryMember(str);
            } else {
                wikiPageProcessor.processPage(pageTitle, wiki.getSectionText(pageTitle, 0));
            }
        }
    }
}
