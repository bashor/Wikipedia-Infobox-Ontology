package ru.aptu.bashor;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/27/11
 * Time: 11:54 PM
 */
public class WikiTitleQueue extends AbstractQueue<String> implements IWikiTitleQueue {

    private static final String CATEGORY = "Category:";

    private class TitleComparator implements Comparator<String> {
        public int compare(String x, String y) {
            Boolean xIsCategory = x.startsWith(CATEGORY);
            Boolean yIsCategory = y.startsWith(CATEGORY);
            if (!xIsCategory && yIsCategory)
                return -1;

            if (xIsCategory && !yIsCategory)
                return 1;

            return x.compareTo(y);
        }
    }

    Comparator<String> comparator = new TitleComparator();
    private final Queue<String> _titleQueue = new PriorityQueue<String>(10, comparator);
    private final Set<String> _processedTitle = new HashSet<String>();

    WikiTitleQueue(String startTitle) {
        offer(startTitle);
    }

    @Override
    public Iterator<String> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return _titleQueue.size();
    }

    public boolean offer(String pageTitle) {
        if (!_processedTitle.contains(pageTitle)) {
            return _processedTitle.add(pageTitle) && _titleQueue.offer(pageTitle);
        }
        return true;
    }

    public String poll() {
        return _titleQueue.poll();
    }

    public String peek() {
        return _titleQueue.peek();
    }
}