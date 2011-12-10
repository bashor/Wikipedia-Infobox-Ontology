package ru.aptu.bashor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/28/11
 * Time: 12:10 AM
 */
public class InfoboxExtractorFromWikiPage implements IWikiPageProcessor, Iterable<String> {
    private static final ArrayList<String> infoboxes = new ArrayList<String>();

    public Collection<String> processPage(String title, String content) {
        System.out.println(content);
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
            }
        }

        return Utilities.extractAllLinks(content);
    }

    public Iterator<String> iterator() {
        return infoboxes.iterator();
    }
}
