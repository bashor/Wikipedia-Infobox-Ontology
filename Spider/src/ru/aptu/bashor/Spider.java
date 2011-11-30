package ru.aptu.bashor; /**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/14/11
 * Time: 2:11 AM
 */

import java.io.IOException;
import java.util.Collections;

public class Spider<T extends IWikimedia> implements ISpider {
    private static final String CATEGORY = "Category:";
    private final IWikimedia wikimedia;

    Spider(IWikimedia wiki) {
        wikimedia = wiki;
    }

    public void run(IWikiTitleQueue wikiTitleQueue, IWikiPageProcessor wikiPageProcessor) throws IOException {

        while (!wikiTitleQueue.isEmpty()) {
            String pageTitle = wikiTitleQueue.remove();

            if (pageTitle.startsWith(CATEGORY)) {
                String[] members = wikimedia.getCategoryMembers(pageTitle.replace(CATEGORY, ""));
                Collections.addAll(wikiTitleQueue, members);
            } else {
                String[] newTitles = wikiPageProcessor.processPage(pageTitle, wikimedia.getSectionText(pageTitle, 0));
                //TODO: drop
                if (newTitles.length == 1 && newTitles[0].equals("STOP")) return;

                Collections.addAll(wikiTitleQueue, newTitles);
            }
        }
    }
}
