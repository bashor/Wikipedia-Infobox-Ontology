package ru.aptu.bashor;

import junit.framework.TestCase;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/28/11
 * Time: 1:45 AM
 */
public class InfoboxExtractorFromWikiPageTest extends TestCase {

    public void testProcessPage() {
        InfoboxExtractorFromWikiPage infoboxExtractorFromWikiPage = new InfoboxExtractorFromWikiPage();

        ArrayList<String> result = (ArrayList<String>) infoboxExtractorFromWikiPage.processPage("title", CONTENT);
        assertNotNull(result);

        assertTrue(infoboxExtractorFromWikiPage.iterator().hasNext());
        assertEquals(INFOBOX, infoboxExtractorFromWikiPage.iterator().next());

        List<String> expectedTitles = Arrays.asList(TITLES);

        Collections.sort(expectedTitles);
        Collections.sort(result);

        Iterator<String> resultIterator = result.iterator();
        Iterator<String> expectedTitlesIterator = expectedTitles.iterator();
        while (resultIterator.hasNext() && expectedTitlesIterator.hasNext()) {
            assertEquals(expectedTitlesIterator.next(), resultIterator.next());
        }

        assertFalse(expectedTitlesIterator.hasNext());
        assertFalse(resultIterator.hasNext());
    }

    static public final String CONTENT = "{{Other people}}\n" +
            "&lt;!--\n" +
            "This page is monitored regularly for nonsense and vandalism. People who vandalize biographies about living people will be blocked from editing.\n" +
            "\n" +
            "If you would like to experiment with Wikipedia, please copy the following address into your browser's address bar:\n" +
            "http://en.wikipedia.org/wiki/Wikipedia:Sandbox\n" +
            "\n" +
            "It will take you to a page where new users can try out the editing features!\n" +
            "--&gt;\n" +
            "{{Pp-semi-blp|small=yes}}\n" +
            "{{Pp-move-indef}}\n" +
            "{{Infobox person\n" +
            "|name         = Bill Gates\n" +
            "|image        = Bill Gates in WEF ,2007.jpg\n" +
            "|image_size   =\n" +
            "|caption      = Gates at the [[World Economic Forum]] in 2007.\n" +
            "|birth_name   = William Henry Gates III\n" +
            "|birth_date   = {{Birth date and age|1955|10|28|mf=yes}}\n" +
            "|birth_place  = [[Seattle]], [[Washington (state)|Washington]], U.S.\n" +
            "|residence    = [[Medina, Washington|Medina]], Washington, U.S.\n" +
            "|nationality  = American\n" +
            "|alma_mater   = [[Harvard University]] (dropped out)\n" +
            "|occupation   = Chairman of [[Microsoft]]&lt;br /&gt;Co-Chair of the [[Bill &amp; Melinda Gates Foundation]]&lt;br /&gt;CEO of [[Cascade Investment]]&lt;br /&gt;Chairman of [[Corbis]]\n" +
            "|years_active = 1975&amp;ndash;present\n" +
            "|net_worth    = {{Increase}} US$ 59 [[1000000000 (number)|billion]] (2011)&lt;ref&gt;[http://www.forbes.com/profile/bill-gates Bill Gates topic page.] Forbes.com. Retrieved September 2010.&lt;/ref&gt;\n" +
            "|boards       = &lt;br /&gt;[[Berkshire Hathaway]]\n" +
            "|religion     = Agnostic&lt;ref&gt;{{cite web|url=http://www.nndb.com/people/435/000022369/ |title=Bill Gates |publisher=Nndb.com |accessdate=May 11, 2011}}&lt;/ref&gt;&lt;ref&gt;{{cite web|url=http://archive.theamericanview.com/index.php?id=649 |title=Warren Buffett “Agnostic,” Bill Gates Rejects Sermon On The Mount, Not “Huge Believer” In “Specific Elements” Of Christianity |publisher=Archive.theamericanview.com |date=1996-01-13 |accessdate=2011-10-24}}&lt;/ref&gt;\n" +
            "|spouse       = {{Marriage|[[Melinda Gates]]|1994}}\n" +
            "|children     = 3\n" +
            "|parents      = [[William H. Gates, Sr.]]&lt;br /&gt;[[Mary Maxwell Gates]]\n" +
            "|signature    = BillGates Signature.svg\n" +
            "|website      = {{URL|http://www.microsoft.com/presspass/exec/billg/bio.mspx|Bill Gates}}\n" +
            "}}\n" +
            "'''William Henry''' &quot;'''Bill'''&quot; '''Gates III''' (born October 28, 1955)&lt;ref&gt;{{harv|Manes|1994|p=11}}&lt;/ref&gt; is an American [[business magnate]], [[investor]], philanthropist, and author. Gates is the former CEO and current chairman of [[Microsoft]], the software company he founded with [[Paul Allen]]. He is consistently ranked among the [[Forbes list of billionaires|world's wealthiest people]]&lt;ref&gt;{{cite news\n" +
            "|url=http://www.reuters.com/article/rbssTechMediaTelecomNews/idUSN1748882920080917|title=Bill Gates tops U.S. wealth list 15 years in a row |first=Phil |last=Wahba |date=September 17, 2008 |accessdate=November 6, 2008 |agency=Reuters}}&lt;/ref&gt; and was the wealthiest overall from 1995 to 2009, excluding 2008, when he was ranked third.&lt;ref&gt;[http://www.forbes.com/profile/bill-gates] Forbes.com. Retrieved April 2010.&lt;/ref&gt;\n" +
            "During his career at Microsoft, Gates held the positions of CEO and [[software architect|chief software architect]], and remains the largest individual shareholder, with 6.4 percent of the [[common stock]].&lt;ref&gt;Gates regularly documents his share ownership through public U.S. Securities and Exchange Commission [[form 4]] filings. [http://www.nasdaq.com/asp/holdings.asp?symbol=MSFT&amp;selected=MSFT&amp;FormType=form4][http://www.nasdaq.com/symbol/msft]&lt;/ref&gt; He has also authored or co-authored several books.\n" +
            "\n" +
            "Gates is one of the best-known entrepreneurs of the personal computer revolution. Gates has been [[Criticism of Microsoft|criticized for his business tactics]], which have been considered anti-competitive, an opinion which has in some cases been upheld by the courts.&lt;ref&gt;{{harv|Manes|1994|p=459}}&lt;/ref&gt;&lt;ref&gt;{{harv|Lesinski|2006|p=96}}&lt;/ref&gt; In the later stages of his career, Gates has pursued a number of philanthropic endeavors, donating large amounts of money to various charitable organizations and scientific research programs through the [[Bill &amp; Melinda Gates Foundation]], established in 2000.\n" +
            "\n" +
            "Gates stepped down as chief executive officer of Microsoft in January 2000. He remained as chairman and created the position of chief software architect. In June 2006, Gates announced that he would be transitioning from full-time work at Microsoft to part-time work, and full-time work at the Bill &amp; Melinda Gates Foundation. He gradually transferred his duties to [[Ray Ozzie]], chief software architect, and [[Craig Mundie]], chief research and strategy officer. Gates' last full-time day at Microsoft was June 27, 2008. He remains at Microsoft as non-executive chairman.";

    static public final String INFOBOX = "{{Infobox person\n" +
            "|name         = Bill Gates\n" +
            "|image        = Bill Gates in WEF ,2007.jpg\n" +
            "|image_size   =\n" +
            "|caption      = Gates at the [[World Economic Forum]] in 2007.\n" +
            "|birth_name   = William Henry Gates III\n" +
            "|birth_date   = {{Birth date and age|1955|10|28|mf=yes}}\n" +
            "|birth_place  = [[Seattle]], [[Washington (state)|Washington]], U.S.\n" +
            "|residence    = [[Medina, Washington|Medina]], Washington, U.S.\n" +
            "|nationality  = American\n" +
            "|alma_mater   = [[Harvard University]] (dropped out)\n" +
            "|occupation   = Chairman of [[Microsoft]]&lt;br /&gt;Co-Chair of the [[Bill &amp; Melinda Gates Foundation]]&lt;br /&gt;CEO of [[Cascade Investment]]&lt;br /&gt;Chairman of [[Corbis]]\n" +
            "|years_active = 1975&amp;ndash;present\n" +
            "|net_worth    = {{Increase}} US$ 59 [[1000000000 (number)|billion]] (2011)&lt;ref&gt;[http://www.forbes.com/profile/bill-gates Bill Gates topic page.] Forbes.com. Retrieved September 2010.&lt;/ref&gt;\n" +
            "|boards       = &lt;br /&gt;[[Berkshire Hathaway]]\n" +
            "|religion     = Agnostic&lt;ref&gt;{{cite web|url=http://www.nndb.com/people/435/000022369/ |title=Bill Gates |publisher=Nndb.com |accessdate=May 11, 2011}}&lt;/ref&gt;&lt;ref&gt;{{cite web|url=http://archive.theamericanview.com/index.php?id=649 |title=Warren Buffett “Agnostic,” Bill Gates Rejects Sermon On The Mount, Not “Huge Believer” In “Specific Elements” Of Christianity |publisher=Archive.theamericanview.com |date=1996-01-13 |accessdate=2011-10-24}}&lt;/ref&gt;\n" +
            "|spouse       = {{Marriage|[[Melinda Gates]]|1994}}\n" +
            "|children     = 3\n" +
            "|parents      = [[William H. Gates, Sr.]]&lt;br /&gt;[[Mary Maxwell Gates]]\n" +
            "|signature    = BillGates Signature.svg\n" +
            "|website      = {{URL|http://www.microsoft.com/presspass/exec/billg/bio.mspx|Bill Gates}}\n" +
            "}}";

    static public final String[] TITLES = {"World Economic Forum", "Seattle", "Washington (state)",
            "Medina, Washington", "Harvard University", "Microsoft", "Bill &amp; Melinda Gates Foundation",
            "Cascade Investment", "Corbis", "1000000000 (number)", "Berkshire Hathaway", "Melinda Gates",
            "William H. Gates, Sr.", "Mary Maxwell Gates", "business magnate", "investor", "Microsoft", "Paul Allen",
            "Forbes list of billionaires", "software architect", "common stock", "form 4", "Criticism of Microsoft",
            "Bill &amp; Melinda Gates Foundation", "Ray Ozzie", "Craig Mundie"};
}
