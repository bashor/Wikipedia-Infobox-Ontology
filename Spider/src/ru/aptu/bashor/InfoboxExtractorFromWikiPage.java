package ru.aptu.bashor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/28/11
 * Time: 12:10 AM
 */
public class InfoboxExtractorFromWikiPage implements IWikiPageProcessor, Iterable<String> {
    private static final ArrayList<String> infoboxes = new ArrayList<String>();
    private static final Pattern linkPattern = Pattern.compile("\\[\\[([^]|]+?)]|\\|");

    public String[] processPage(String title, String content) {
        ArrayList<String> titles = new ArrayList<String>();
        Matcher linksMatcher = linkPattern.matcher(content);

        while (linksMatcher.find()) {
            String pageTitle = linksMatcher.group(1);
            if (pageTitle != null)
                titles.add(pageTitle);
        }

        int infoboxStart = content.indexOf("{{Infobox ");
        if (infoboxStart != -1) {
            int bracketBalance = 0;
            int infoboxEnd = infoboxStart;
            do {
                if (content.charAt(infoboxEnd) == '{')
                    bracketBalance++;
                else if (content.charAt(infoboxEnd) == '}')
                    bracketBalance--;

                infoboxEnd++;
            } while (bracketBalance > 0 && infoboxEnd < content.length());

            if (bracketBalance == 0) {
                String infobox = content.substring(infoboxStart, infoboxEnd);
                infoboxes.add(infobox);
                System.out.println(infobox);

                //TODO: drop this
                System.out.println("infoboxes.size()==" + infoboxes.size());
                if (infoboxes.size() == 1) {
                    titles.clear();
                    titles.add("STOP");
                }
            }
        }

        return titles.toArray(new String[0]);
    }

    public Iterator<String> iterator() {
        return infoboxes.iterator();
    }
}
