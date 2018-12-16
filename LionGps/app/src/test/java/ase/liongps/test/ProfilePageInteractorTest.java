package ase.liongps.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Arrays;

import ase.liongps.ProfilePage.ProfilePageInteractor;

@RunWith(MockitoJUnitRunner.class)
public class ProfilePageInteractorTest {

    private static ProfilePageInteractor profileInteractor;
    private static HashSet<String> mockSchedule;
    private static HashSet<String> nullSchedule;

    @BeforeClass
    public static void setup() {

        profileInteractor = new ProfilePageInteractor();

        String[] scheduleEntries = {
                "History of Ribbon Dancing : Dodge Physical Fitness Center",
                "History of Metal Music : Butler Library",
                "Flea Market Economics : Uris Hall",
                "Cold Fusion Using Potatoes : Pupin Laboratories",
                "Bailing Criminals : William and June Warren Hall"};

        mockSchedule = new HashSet<String>(Arrays.asList(scheduleEntries));

    }

    @Test
    public void addToScheduleTest() {

        for (String mockScheduleEntry : mockSchedule) {
            Assert.assertTrue("Entry \"" +  mockScheduleEntry + "\" not added to schedule",
                    profileInteractor.addToSchedule(mockScheduleEntry));
        }
        Assert.assertFalse("null object added to schedule",
                profileInteractor.addToSchedule(null));

    }

    @Test
    public void getScheduleTest(){

        HashSet<String> storedSchedule = profileInteractor.getSchedule();

        for(String mockScheduleEntry : mockSchedule){
            Assert.assertTrue("Entry \"" + "\" was not present in the schedule",
                    storedSchedule.contains(mockScheduleEntry));
        }
    }

    @Test
    public void loadScheduleTest(){

        nullSchedule = null;

        Assert.assertTrue("courseList not loaded",
                profileInteractor.loadSchedule(mockSchedule));

        Assert.assertFalse("null courseList loaded",
                profileInteractor.loadSchedule(nullSchedule));
    }

}
