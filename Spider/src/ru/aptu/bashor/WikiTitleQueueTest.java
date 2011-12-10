package ru.aptu.bashor;

import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/28/11
 * Time: 1:47 AM
 */
public class WikiTitleQueueTest extends TestCase {

    public void testIterator() throws Exception {
        WikiTitleQueue queue = new WikiTitleQueue("a");

        boolean exceptionThrew = false;
        try {
            queue.iterator();
        } catch (UnsupportedOperationException e) {
            exceptionThrew = true;
        }

        assertTrue(exceptionThrew);
    }

    public void testPoolAndOfferAndSize() throws Exception {
        WikiTitleQueue queue = new WikiTitleQueue("a");
        assertEquals(1, queue.size());
        queue.offer("b");
        assertEquals(2, queue.size());
        queue.poll();
        assertEquals(1, queue.size());
        queue.poll();
        assertEquals(0, queue.size());
    }

    public void testProcessedFiltering() throws Exception {
        WikiTitleQueue queue = new WikiTitleQueue("a");
        assertEquals(1, queue.size());
        queue.offer("b");
        assertEquals(2, queue.size());
        queue.poll();
        assertEquals(1, queue.size());
        queue.poll();
        assertEquals(0, queue.size());
        queue.offer("a");
        assertEquals(0, queue.size());
        queue.offer("c");
        assertEquals(1, queue.size());
        queue.offer("b");
        assertEquals(1, queue.size());
    }

    public void testPeek() throws Exception {
        final String TEST_DATA = "testdata";
        WikiTitleQueue queue = new WikiTitleQueue(TEST_DATA);
        assertEquals(TEST_DATA, queue.peek());
    }

    public void testSort() throws Exception {
        final String CATEGORY = "Category:";

        final String first_in = CATEGORY + "second_out";
        final String second_in = "first_out";
        final String third_in = CATEGORY + "third_out";

        WikiTitleQueue queue = new WikiTitleQueue(first_in);
        assertEquals(1, queue.size());
        queue.offer(second_in);
        assertEquals(2, queue.size());
        queue.offer(third_in);
        assertEquals(3, queue.size());
        assertEquals(second_in, queue.poll());
        assertEquals(2, queue.size());
        assertEquals(first_in, queue.poll());
        assertEquals(1, queue.size());
        assertEquals(third_in, queue.poll());
        assertNull(queue.poll());
        assertEquals(0, queue.size());
    }
}
