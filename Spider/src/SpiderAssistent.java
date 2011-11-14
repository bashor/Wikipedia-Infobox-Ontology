/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/14/11
 * Time: 2:45 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SpiderAssistent implements IUrlIterator, IDataProcessor {
    private Queue<String> _queue = new LinkedList<String>();
    public SpiderAssistent(String url)
    {
        _queue.offer(url);
    }

    public String getUrl(){
        String u = _queue.poll();
        return u != null ? u : "";
    }

    static final Pattern linkPattern = Pattern.compile("\\[\\[([^]|]+?)]|\\|");
    public void processData(String data){
        Matcher m = linkPattern.matcher(data);

        while (m.find()){
            String f = m.group(1);
            if (f != null)
                _queue.offer(f);
        }

        int infboxStart = data.indexOf("{{Infobox ");
        if (infboxStart == -1)
            return;
        int balance = 2;
        int infoboxEnd = infboxStart + 2;
        while (balance > 0 && infoboxEnd < data.length()){
            if (data.charAt(infoboxEnd) == '{')
                balance++;
            else if (data.charAt(infoboxEnd) == '}')
                balance--;

            infoboxEnd++;
        }
        if (balance == 0)
            System.out.println(data.substring(infboxStart, infoboxEnd));
    }

    public void processCategoryMember(String data){
            _queue.offer(data);
    }
}
