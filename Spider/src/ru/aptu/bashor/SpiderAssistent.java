package ru.aptu.bashor; /**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/14/11
 * Time: 2:45 AM
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SpiderAssistent implements IWikiTitleIterator, IWikiPageProcessor {
    //TODO: two lists is too much - to do drop anyone
    private final Queue<String> _titleQueue = new LinkedList<String>();
    private final Set<String> _prcessedTitle = new HashSet<String>();

    public SpiderAssistent(String url) {
        _titleQueue.offer(url);
    }

    public boolean hasNext() {
        return !_titleQueue.isEmpty();
    }

    public String next() {
        String u = _titleQueue.poll();
        return u != null ? u : "";
    }

    public void remove() {
        //???
    }

    private void add(String pageTitle) {
        if (!_prcessedTitle.contains(pageTitle)) {
            _prcessedTitle.add(pageTitle);
            _titleQueue.offer(pageTitle);
        }
    }

    private static final Pattern linkPattern = Pattern.compile("\\[\\[([^]|]+?)]|\\|");

    public void processPage(String title, String content) {
        Matcher linksMatcher = linkPattern.matcher(content);

        while (linksMatcher.find()) {
            String pageTitle = linksMatcher.group(1);
            if (pageTitle != null)
                add(pageTitle);
        }

        int infboxStart = content.indexOf("{{Infobox ");
        if (infboxStart == -1)
            return;
        int bracketBalance = 0;
        int infoboxEnd = infboxStart;
        do {
            if (content.charAt(infoboxEnd) == '{')
                bracketBalance++;
            else if (content.charAt(infoboxEnd) == '}')
                bracketBalance--;

            infoboxEnd++;
        } while (bracketBalance > 0 && infoboxEnd < content.length());

        if (bracketBalance == 0)
            System.out.println(content.substring(infboxStart, infoboxEnd));
    }

    public void processCategoryMember(String data) {
        add(data);
    }
}
