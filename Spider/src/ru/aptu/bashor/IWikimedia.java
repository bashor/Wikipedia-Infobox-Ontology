package ru.aptu.bashor;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/21/11
 * Time: 4:18 AM
 */
public interface IWikimedia {
    String[] getCategoryMembers(String name);

    String getSectionText(String title, int number);

}
