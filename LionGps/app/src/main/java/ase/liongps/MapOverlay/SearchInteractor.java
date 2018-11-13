package ase.liongps.MapOverlay;

import com.google.android.gms.maps.model.LatLng;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SearchInteractor {
    private HashMap<String, LatLng> buildings;

    public SearchInteractor() {
        buildings = new HashMap<>();
        populateBuildings();
    }


    /*
   reads from a text file that stores the buildings and their coordinates. each line
   contains a building name, latitude, and longitude separated by tabs. this method
   adds each entry to the buildings hashmap using names as keys and geo-location points
   as values.
    */
    public void populateBuildings(){
        System.out.println("SearchInteractor: popBuildings called");

        String file = "res/raw/buildings.txt";
        InputStream test = this.getClass().getClassLoader().getResourceAsStream(file);
        Scanner scan = new Scanner(test);
        System.out.println(); //testpoint

        while (scan.hasNextLine()) {
            String[] entry = scan.nextLine().split("\t");
            String name = entry[0];
            double lat = Double.parseDouble(entry[1]);
            double lng = Double.parseDouble(entry[2]);

            System.out.println("name: " + name + " lat: " + lat + " lng: " + lng); //test

            buildings.put(name, new LatLng(lat, lng));
        }
    }

    public boolean isValidSearch(String query){
        return buildings.containsKey(query);
    }

    public ArrayList<String> getBuildingNames() {
        return new ArrayList<String> (buildings.keySet());
    }




}
