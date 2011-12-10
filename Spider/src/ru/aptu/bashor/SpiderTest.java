package ru.aptu.bashor;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/21/11
 * Time: 7:16 AM
 */
public class SpiderTest extends TestCase {

    private static final String CATEGORY = "Category:";

    class WikiBaseMock implements IWikimedia {

        public Collection<String> getCategoryMembers(String name) {
            return null;
        }

        public String getSectionText(String title, int number) {
            return null;
        }
    }

    class WikiReturnSomeCatgoriesMock extends WikiBaseMock {
        final private int _maxGetCategoryMembersCallCount;
        private int _getCategoryMembersCallCount;

        WikiReturnSomeCatgoriesMock(int _maxGetCategoryMembersCallCount) {
            this._maxGetCategoryMembersCallCount = _maxGetCategoryMembersCallCount;
        }

        @Override
        public Collection<String> getCategoryMembers(String name) {
            if (_getCategoryMembersCallCount >= _maxGetCategoryMembersCallCount)
                return null;

            _getCategoryMembersCallCount++;
            return Arrays.asList(CATEGORY + "a");
        }
    }

    class WikiReturnSomeTextMock extends WikiBaseMock {
        final private int _maxGetSectionTextCallCount;
        private int _getSectionTextCallCount = 0;

        WikiReturnSomeTextMock(int _maxGetSectionTextCallCount) {
            this._maxGetSectionTextCallCount = _maxGetSectionTextCallCount;
        }

        @Override
        public String getSectionText(String title, int number) {
            if (_getSectionTextCallCount >= _maxGetSectionTextCallCount)
                return null;

            _getSectionTextCallCount++;
            return title;
        }

        int getSectionTextCallCount() {
            return _getSectionTextCallCount;
        }
    }

    class TitleQueueBaseMock extends AbstractQueue<String> implements IWikiTitleQueue {

        private final Queue<String> _titleQueue = new LinkedList<String>();

        private int _offerCallCount;
        private int _pollCallCount;

        TitleQueueBaseMock(String... strings) {
            Collections.addAll(_titleQueue, strings);
        }

        @Override
        public Iterator<String> iterator() {
            return _titleQueue.iterator();
        }

        @Override
        public int size() {
            return _titleQueue.size();
        }

        public boolean offer(String s) {
            _offerCallCount++;
            return _titleQueue.offer(s);
        }

        public String poll() {
            _pollCallCount++;
            return _titleQueue.poll();
        }

        public String peek() {
            return _titleQueue.peek();
        }

        public int getOfferCallCount() {
            return _offerCallCount;
        }

        public int getPollCallCount() {
            return _pollCallCount;
        }
    }

    class WikiPageProcessorMock implements IWikiPageProcessor {
        public Collection<String> processPage(String title, String content) {
            return null;
        }
    }

    boolean spiderRun(IWikimedia wiki, IWikiTitleQueue titleQueue, IWikiPageProcessor wikiPageProcessor) {
        Spider spider = new Spider(wiki);
        try {
            spider.run(titleQueue, wikiPageProcessor);
        } catch (IOException e) {
            //TODO: write to log
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void testFindAllCategoriesWhenReadOnly() throws Exception {
        TitleQueueBaseMock titleQueueMock = new TitleQueueBaseMock(CATEGORY + "a", CATEGORY + "b", "c");
        final int expectedGetPollCallCount = titleQueueMock.size();

        assertTrue(spiderRun(new WikiBaseMock(), titleQueueMock, new WikiPageProcessorMock()));

        assertEquals(0, titleQueueMock.getOfferCallCount());
        assertEquals(expectedGetPollCallCount, titleQueueMock.getPollCallCount());
        assertEquals(0, titleQueueMock.size());
    }

    public void testFindAllCategories() throws Exception {
        TitleQueueBaseMock titleQueueMock = new TitleQueueBaseMock(CATEGORY + "a", CATEGORY + "b", "c");

        final int titleQueueSize = titleQueueMock.size();

        final int MAX_CATEGORY_MEMBORS = 4;
        WikiReturnSomeCatgoriesMock wiki = new WikiReturnSomeCatgoriesMock(MAX_CATEGORY_MEMBORS);

        assertTrue(spiderRun(wiki, titleQueueMock, new WikiPageProcessorMock()));

        assertEquals(MAX_CATEGORY_MEMBORS, titleQueueMock.getOfferCallCount());
        assertEquals(MAX_CATEGORY_MEMBORS + titleQueueSize, titleQueueMock.getPollCallCount());
        assertEquals(0, titleQueueMock.size());
    }

    public void testFindAllPages() throws Exception {
        TitleQueueBaseMock titleQueueMock = new TitleQueueBaseMock("a", "b", "c");

        final int GETSECTIONTEXT_MAX_CALL_CAUNT = 5;
        WikiReturnSomeTextMock wiki = new WikiReturnSomeTextMock(GETSECTIONTEXT_MAX_CALL_CAUNT);

        final int queueSize = titleQueueMock.size();

        assertTrue(spiderRun(wiki, titleQueueMock, new WikiPageProcessorMock()));

        assertEquals(queueSize, wiki.getSectionTextCallCount());

        assertEquals(0, titleQueueMock.getOfferCallCount());
        assertEquals(queueSize, titleQueueMock.getPollCallCount());
        assertEquals(0, titleQueueMock.size());
    }
}
