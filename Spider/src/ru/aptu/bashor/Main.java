package ru.aptu.bashor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SpiderAssistent spiderAssistent = new SpiderAssistent("Category:People");
        Spider spider = new Spider();
        spider.Run(spiderAssistent, spiderAssistent);
    }
}