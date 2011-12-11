package ru.aptu.bashor;

import java.io.IOException;

public class Main {
    //final static String START_PAGE = "Bill Gates"
    final static String START_PAGE = "Category:People";

    public static void main(String[] args) throws IOException {
        Spider spider = new Spider(new WikiJavaProxy());
        InfoboxExtractorFromWikiPage infoboxesAsText = new InfoboxExtractorFromWikiPage();

        spider.run(new WikiTitleQueue(START_PAGE), infoboxesAsText);

        InfoboxParser.parseAll(infoboxesAsText, new OntologyCreator());
    }
}