package ru.aptu.bashor;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/21/11
 * Time: 4:18 AM
 */
public interface IWikimedia {
    Collection<String> getCategoryMembers(String name);

    String getSectionText(String title, int number);

}
