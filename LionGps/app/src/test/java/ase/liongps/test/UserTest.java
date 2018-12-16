package ase.liongps.test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.LinkedList;

import ase.liongps.utils.User;

public class UserTest {


    @Test
    public void test() {

        User u = new User("Gandalf", "Manwe");

        // check for successful retrieval
        Assert.assertEquals(u.getName(), "Gandalf");
        Assert.assertEquals(u.getPw(), "Manwe");

        // check for empty list if user has not added Search String
        Assert.assertEquals(0, u.getSearches().size());

        u.updateHistory("Moria");
        Assert.assertEquals(1, u.getSearches().size());

        u.updateHistory("The Shire");
        Assert.assertEquals(2, u.getSearches().size());

        // check if retrieved list has correct element at index 0 (1st but most recent element)
        //note the update history method adds to the FRONT of the list for proper display in UI
        LinkedList<String> testList = u.getSearches();
        Assert.assertEquals("The Shire", testList.get(0));

        u.setName("Frodo");
        u.setPw("Sam");

        Assert.assertEquals("Frodo", u.getName());
        Assert.assertEquals("Sam", u.getPw());

        String[] stringArr = {"Rohan", "Gondor"};

        testList = new LinkedList<>(Arrays.asList(stringArr));

        // test string list setter
        u.setSearches(testList);

        Assert.assertEquals("Gondor", testList.get(1));






    }
}
