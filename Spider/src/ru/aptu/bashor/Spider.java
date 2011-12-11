package ru.aptu.bashor; /**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/14/11
 * Time: 2:11 AM
 */

import java.io.IOException;
import java.util.Collection;

public class Spider implements ISpider {
    private static final String CATEGORY = "Category:";
    private final IWikimedia wikimedia;

    Spider(IWikimedia wiki) {
        wikimedia = wiki;
    }

    //TODO: question: move parameters to constructor?
    public void run(IWikiTitleQueue wikiTitleQueue, IWikiPageProcessor wikiPageProcessor) throws IOException {

        while (!wikiTitleQueue.isEmpty()) {
            String pageTitle = wikiTitleQueue.remove();

            if (pageTitle.startsWith(CATEGORY)) {
                Collection<String> members = wikimedia.getCategoryMembers(pageTitle.replace(CATEGORY, ""));
                if (members == null)
                    continue;
                wikiTitleQueue.addAll(members);
            } else {
                Collection<String> newTitles = wikiPageProcessor.processPage(pageTitle, wikimedia.getSectionText(pageTitle, 0));
                if (newTitles == null)
                    continue;

                wikiTitleQueue.addAll(newTitles);
            }
        }
    }
}
