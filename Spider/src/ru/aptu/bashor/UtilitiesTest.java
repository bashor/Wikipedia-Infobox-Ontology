package ru.aptu.bashor;

import junit.framework.TestCase;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 12/10/11
 * Time: 3:02 AM
 */
public class UtilitiesTest extends TestCase {
    final static String SOME_DATA = "SOME data";
    final static String LINK_BEGIN = "[[";
    final static String LINK_END = "]]";
    final static String DELIMITER = "|";

    private static String createLink(String link, String title) {
        return SOME_DATA + LINK_BEGIN + link + DELIMITER + title + LINK_END + SOME_DATA;
    }

    private static String createLink(String link) {
        return SOME_DATA + LINK_BEGIN + link + LINK_END + SOME_DATA;
    }

    public void testExtractTitleWithNumbers() throws Exception {
        final String link = "1000000000 (number)";
        final String title = "billion";

        Collection<String> result = Utilities.extractAllLinks(createLink(link, title));

        assertEquals(1, result.size());
        assertTrue(result.contains(link));
    }

    public void testExtractSimpleTitle() throws Exception {
        final String link = "Seattle";

        Collection<String> result = Utilities.extractAllLinks(createLink(link));

        assertEquals(1, result.size());
        assertTrue(result.contains(link));
    }

    public void testExtractLinkAndSkipTitle() throws Exception {
        final String link = "Washington (state)";
        final String title = "Washington";

        Collection<String> result = Utilities.extractAllLinks(createLink(link, title));

        assertEquals(1, result.size());
        assertTrue(result.contains(link));
    }

    public void testExtractLinkFromTitle() throws Exception {
        final String link1 = "Washington (state)";
        final String link2 = "USA";
        final String title = "Washington ";

        Collection<String> result = Utilities.extractAllLinks(createLink(link1, title + createLink(link2)));

        assertEquals(2, result.size());
        assertTrue(result.contains(link1));
        assertTrue(result.contains(link2));
    }
}
