package ase.liongps.utils;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String name;
    private String pw;
    private LinkedList<String> searches;

    public User() {
        //required empty constructor for google firebase
    }

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

    public LinkedList<String> getSearches(){
        return searches;
    }

    public String getName() {
        return name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSearches(List<String> searches) {
        this.searches = new LinkedList<>(searches);
    }

    public void initSearches(){
        this.searches = new LinkedList<>();
    }
}
