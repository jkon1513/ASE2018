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
             "80 claremont",
             "alfred lerner hall",
             "altschul hall",
             "armstrong hall",
             "arthur w. diamond law library",
             "avery hall",
             "barnard hall",
             "broadway hall",
             "buell hall",
             "butler library",
             "carman hall",
             "casa hispanica",
             "chandler laboratories",
             "computer science building",
             "deutsches haus",
             "diana center",
             "dodge hall",
             "dodge physical fitness center",
             "earl hall",
             "east campus",
             "engineering terrace",
             "faculty house",
             "fairchild life sciences building",
             "fayerweather",
             "furnald hall",
             "grace dodge hall",
             "hamilton hall",
             "hartley",
             "havemeyer hall",
             "heyman center for humanities",
             "horace mann hall",
             "international affairs building",
             "international house",
             "jerome greene hall",
             "john jay hall",
             "journalism building",
             "kent hall",
             "knox hall",
             "kraft center",
             "lewishon hall",
             "lowe library",
             "macy hall",
             "mathematics building",
             "milbank hall",
             "milstein center",
             "morris a. schapiro hall",
             "mudd",
             "northwest corner meeting",
             "philosophy hall",
             "prentis hall",
             "pulitzer hall",
             "pupin physics laboratories",
             "riverside church",
             "schapiro center for engineering and physical science research",
             "schermerhorn hall",
             "science and engineering library",
             "school of social work",
             "seely w. mudd building",
             "st. paul's chapel",
             "thompson hall",
             "union theological seminary",
             "uris hall",
             "wallach",
             "watson hall",
             "wien hall",
             "william and june warren hall",
             "william c. warren hall"
     };

     validNames = new ArrayList<>(Arrays.asList(blds));

        LatLng claremont = new LatLng(40.811110,-73.962727);
        LatLng lerner= new LatLng(40.806972, -73.963919);
        LatLng altschul = new LatLng(40.810063,-73.963340);
        LatLng armstrong = new LatLng(40.805398, -73.965326);
        LatLng lawLib = new LatLng(40.806989, -73.960364);
        LatLng avery = new LatLng(40.808293, -73.960972);
        LatLng barnard = new LatLng(40.809214,-73.963948);
        LatLng broadway = new LatLng(40.806395,-73.964328);
        LatLng buell = new LatLng(40.807734,-73.961431);
        LatLng butler = new LatLng(40.806598,-73.963231);
        LatLng carman = new LatLng(40.806657,-73.964172);
        LatLng hispanica = new LatLng(40.808259, -73.965033);
        LatLng chandler = new LatLng(40.809345, -73.961884);
        LatLng compSci = new LatLng(40.809065, -73.959818);
        LatLng deutsches = new LatLng(40.806165, -73.960225);
        LatLng diana = new LatLng(40.809971, -73.962909);
        LatLng dodge = new LatLng(40.807957, -73.963193);
        LatLng dodgeGym = new LatLng(40.809538, -73.961673);
        LatLng earl = new LatLng(40.809538, -73.961673);
        LatLng eastCampus = new LatLng(40.807336, -73.958831);
        LatLng engineering = new LatLng(40.809391, -73.960025);
        LatLng faculty = new LatLng(40.806770, -73.959132);
        LatLng fairchild = new LatLng(40.809215, -73.960312);
        LatLng fayerweather = new LatLng(40.808176, -73.960458);
        LatLng furnald = new LatLng(40.807461, -73.963997);
        LatLng grace = new LatLng(40.810252, -73.959722);
        LatLng hamilton =   new LatLng(40.806766,-73.961652);
        LatLng hartley = new LatLng(40.806434, -73.961617);
        LatLng havemeyer = new LatLng(40.809456, -73.962185);
        LatLng heyman = new LatLng(40.807336, -73.958830);
        LatLng horaceMann = new LatLng(40.810834, -73.961629);
        LatLng iab = new LatLng(40.807750, -73.959721);
        LatLng international = new LatLng(40.813842, -73.961519);
        LatLng greene = new LatLng(40.806892, -73.960630);
        LatLng johnJay = new LatLng(40.805909,-73.962376);
        LatLng journalism = new LatLng(40.807636, -73.963631);
        LatLng kent = new LatLng(40.807217, -73.961358);
        LatLng knox = new LatLng(40.811996, -73.961778);
        LatLng kraft = new LatLng(40.807482, -73.965254);
        LatLng lewishon = new LatLng(40.808356, -73.963264);
        LatLng lowe = new LatLng (40.808167, -73.961839);
        LatLng macy = new LatLng(40.810561, -73.960405);
        LatLng math = new LatLng(40.809150, -73.962675);
        LatLng milbank = new LatLng(40.810583, -73.962689);
        LatLng milstein = new LatLng(40.809691, -73.963627);
        LatLng schapiroHall = new LatLng(40.807844, -73.965103);
        LatLng mudd1 = new LatLng(40.809374, -73.959911);
        LatLng noco2 = new LatLng(40.810114, -73.961963);
        LatLng philosphy = new LatLng(40.807508,-73.961080);
        LatLng prentis = new LatLng(40.816740, -73.959197);
        LatLng pulitzer = new LatLng(40.807575,-73.963425);
        LatLng pupin = new LatLng(40.810069,-73.961420);
        LatLng riversideChurch = new LatLng(40.811881, -73.963115);
        LatLng cepsr = new LatLng(40.809640, -73.960817);
        LatLng schermerhorn = new LatLng(40.808629, -73.960435);
        LatLng noco1 = new LatLng(40.810026,-73.961993);
        LatLng socialWork = new LatLng(40.810262, -73.958363);
        LatLng mudd2 = new LatLng(40.809553,-73.960247);
        LatLng stPaul = new LatLng(40.807846,-73.960976);
        LatLng thompson = new LatLng(40.810326, -73.961506);
        LatLng uts = new LatLng(40.811356, -73.961751);
        LatLng uris = new LatLng(40.808970,-73.961356);
        LatLng wallach = new LatLng(40.806033, -73.961892);
        LatLng watson = new LatLng(40.807611, -73.965569);
        LatLng wien = new LatLng(40.807089, -73.960288);
        LatLng wnj = new LatLng(40.806282, -73.961059);
        LatLng warren = new LatLng(40.806291, -73.960074);
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
        Assert.assertFalse("invalid search returned true", model.isValidSearch(" "));
        Assert.assertFalse("invalid search returned true", model.isValidSearch("\t\n"));
        Assert.assertTrue("ignore case not working", model.isValidSearch("BuTlEr LibRaRy"));
        Assert.assertTrue("ignore case not working", model.isValidSearch("URIS HALL"));
        Assert.assertTrue("trim leading white space not working",
                model.isValidSearch(" \t\nschapiro center for engineering and physical science research"));
        Assert.assertTrue("trim trailing white space not working",
                model.isValidSearch("school of social work \t\n"));
    }




}
