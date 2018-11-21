package ase.liongps.test;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import ase.liongps.MapOverlay.SearchInteractor;

public class SearchInteractorTests {

    private static ArrayList<String> validNames;
    private static SearchInteractor model = new SearchInteractor();


    @BeforeClass
    public static void setup(){
     String[] blds = {
             "butler library",
             "hamilton hall",
             "lewishon hall",
             "lowe library",
             "mathematics building",
             "mudd",
             "science and engineering library",
             "uris hall"};

     validNames = new ArrayList<>(Arrays.asList(blds));

     LatLng butler = new LatLng(40.806598,-73.963231);
     LatLng hamilton =   new LatLng(40.806757,-73.961656);
     LatLng lewishon = new LatLng(40.808356,	-73.963264);
     LatLng lowe = new LatLng (40.808167,	-73.961839);
     LatLng math = new LatLng(40.809150,	-73.962675);
     LatLng mudd = new LatLng(40.809374,	-73.959911);
     LatLng noco = new LatLng(	40.810026,-73.961993);
     LatLng uris = new LatLng(40.808970	,-73.961356);
    }

    @Test
    public void populateBuildingsTest() {
        ;
    }

    @Test
    public void getBuildingTest() {

    }

    @Test
    public void getValidBuildingsTest() {

    }

    @Test
    public void isValidSearchTest() {
        for(String name : validNames) {
            Assert.assertTrue("all valid building names should return true", model.isValidSearch(name));
        }

        Assert.assertFalse("invalid search returned true", model.isValidSearch("1232"));
        Assert.assertFalse("invalid search returned true", model.isValidSearch("mcdonals"));
        Assert.assertFalse("invalid search returned true", model.isValidSearch("@#$%ddcin9"));
        Assert.assertFalse("invalid search returned true", model.isValidSearch(""));
        Assert.assertFalse("invalid search returned true", model.isValidSearch("\t\n"));
        Assert.assertTrue("ignore case not working", model.isValidSearch("BuTlEr LibRaRy"));
        Assert.assertTrue("ignore case not working", model.isValidSearch("URIS HALL"));
    }




}