package ase.liongps.test;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.Arrays;


import ase.liongps.MapOverlay.SearchInteractor;
import ase.liongps.utils.Building;

@RunWith(MockitoJUnitRunner.class)
public class SearchInteractorTests {

    private static ArrayList<String> validNames;
    private static ArrayList<LatLng> validGeoData;
    private static ArrayList<String> fakeFile;


    @Spy
    private SearchInteractor model = new SearchInteractor();

    @BeforeClass
    public static void setup() {

        //sample data to test the business logic
        String[] sampleBlds = {
                "butler library",
                "hamilton hall",
                "lewishon hall",
                "lowe library",
                "mathematics building",
                "mudd",
                "science and engineering library",
                "uris hall"};

        LatLng[] sampleGeoData = {
                new LatLng(40.806598, -73.963231),
                new LatLng(40.806757, -73.961656),
                new LatLng(40.808356, -73.963264),
                new LatLng(40.808167, -73.961839),
                new LatLng(40.809150, -73.962675),
                new LatLng(40.809374, -73.959911),
                new LatLng(40.810026, -73.961993),
                new LatLng(40.808970, -73.961356)
        };

        validNames = new ArrayList<>(Arrays.asList(sampleBlds));
        validGeoData = new ArrayList<>(Arrays.asList(sampleGeoData));
        fakeFile = new ArrayList<>();

        for (int i = 0; i < sampleBlds.length; i++) {
            String name = sampleBlds[i];
            String lat = Double.toString(sampleGeoData[i].latitude);
            String lng = Double.toString(sampleGeoData[i].longitude);

            fakeFile.add(name + "\t" + lat + "\t" + lng);
        }
    }


    @Before
    public void populateBuildingsMock() {
        for(String entry: fakeFile){
            model.populateBuildings(entry);
        }
    }

    @Test
    public void getBuildingTest() {
        double expected, actual;

        for(int i = 0; i < validNames.size(); i++) {
            String name = validNames.get(i);
            Building b = model.getBuilding(name);

            expected = validGeoData.get(i).latitude;
            actual = b.getLat();
            Assert.assertEquals("latitude of buildings dont match",expected, actual,0);

            expected = validGeoData.get(i).longitude;
            actual = b.getLng();
            Assert.assertEquals("longitude of buildngs dont match", expected,actual,0);
        }
    }

    @Test
    public void getValidBuildingsTest() {
        Assert.assertEquals("validBuildings does not have expected number of entries"
                ,validNames.size(), model.getValidBuildings().size());
        for(String name : validNames){
            Assert.assertTrue("model data is missing a valid name",
                    model.getValidBuildings().contains(name));
        }
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
        Assert.assertFalse("invalid search returned true", model.isValidSearch(" "));
        Assert.assertFalse("invalid search returned true", model.isValidSearch("\t\n"));
        Assert.assertTrue("ignore case not working", model.isValidSearch("BuTlEr LibRaRy"));
        Assert.assertTrue("ignore case not working", model.isValidSearch("URIS HALL"));
        Assert.assertTrue("trim leading white space not working",
                model.isValidSearch(" \t\nuris hall"));
        Assert.assertTrue("trim trailing white space not working",
                model.isValidSearch("butler library \t\n"));
    }




}
