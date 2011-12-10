package ru.aptu.bashor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
    static public Collection<String> extractAllLinks(String content) {
        ArrayList<String> titles = new ArrayList<String>();

        //TODO: question:
        // что лучше?
        // static private final Pattern linkPattern = Pattern.compile("\\[\\[([^]|]+?)]|\\|");
        // или:
        final Pattern linkPattern = Pattern.compile("(?<=\\[\\[)([^]|]*)");
        Matcher linksMatcher = linkPattern.matcher(content);

        while (linksMatcher.find()) {
            String pageTitle = linksMatcher.group(1);
            if (pageTitle != null)
                titles.add(pageTitle);
        }

        return titles;
    }
}