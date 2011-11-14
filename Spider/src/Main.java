import java.io.*;
import java.util.*;
import org.wikipedia.*;
import java.net.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        SpiderAssistent spiderAssistent = new SpiderAssistent("Category:People");
        Spider spider = new Spider();
        spider.Run(spiderAssistent, spiderAssistent);

//        Wiki wiki = new Wiki();
//        String[] l = wiki.getCategoryMembers("People");
//        String s = wiki.getSectionText("Category:People",0);
//        System.out.println("-----------");
//        System.out.println(s);
//        System.out.println("-----------");
//        for (String str : l)
//           System.out.println(str);
    }
}