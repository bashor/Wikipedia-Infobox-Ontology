/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/14/11
 * Time: 2:11 AM
 * To change this template use File | Settings | File Templates.
 */

import org.wikipedia.*;

import java.io.IOException;

public class Spider implements ISpider{
    public static final String CATEGORY = "Category:";

    public void Run(IUrlIterator urlIterator, IDataProcessor dataProcessor) throws IOException {

        while (true){
            String url = urlIterator.getUrl();
            if (url.isEmpty())
                break;

            Wiki wiki = new Wiki();
            String data;
            if (url.startsWith(CATEGORY)) {
                String[] l = wiki.getCategoryMembers(url.replace(CATEGORY, ""));
                for (String str : l)
                   dataProcessor.processCategoryMember(str);
            }
            else {
                dataProcessor.processData(wiki.getSectionText(url, 0));
            }
        }
    }
}
