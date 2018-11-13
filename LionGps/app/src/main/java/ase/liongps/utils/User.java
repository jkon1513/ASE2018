package ase.liongps.utils;

import java.util.LinkedList;
import java.util.List;

public class User {
    String name;
    String pw;
    LinkedList<String> searches;

    public User(String userName, String passWord){
        this.name = userName;
        this.pw = passWord;
        searches = new LinkedList<>();
    }

    public void updateHistory(String search){
        int maxEntries = 10;

        if(searches.size() < maxEntries){
            searches.addLast(search);
        } else {
            searches.removeFirst();
            searches.addLast(search);
        }
    }

    public List getSearches(){
        return searches;
    }

}
