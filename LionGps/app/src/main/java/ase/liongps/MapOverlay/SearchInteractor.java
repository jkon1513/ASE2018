package ase.liongps.MapOverlay;

import ase.liongps.utils.Building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SearchInteractor {
    private HashMap<String, Building> buildings;

    public SearchInteractor() {
        buildings = new HashMap<>();
    }

    /* building.txt entry format:
        tab delimited per line
        0: formal name
        1: short hand alias
        2. latitude
        3. longitude
        4: building code (SSOL Code) (if exists!!)
        TODO: think about allowing something like HAM3301 and auto remove digits to get HAM
     */
    public void populateBuildings(String fileEntry) {
        String[] bldngData = fileEntry.split("\t");

        //lets consider making use of constants here
        boolean codeExists = bldngData.length == 5;

        String name = bldngData[0];
        String alias = bldngData[1];
        double lat = Double.parseDouble(bldngData[2]);
        double lng = Double.parseDouble(bldngData[3]);

        Building theBuilding = new Building(name, lat, lng);
        theBuilding.setShortHand(alias);

        buildings.put(name, theBuilding);
        buildings.put(alias, theBuilding);

        //not every building on campus has a building code since not all host classes
        if(codeExists) {
            String code = bldngData[4];
            theBuilding.setBldngCode(code);
            buildings.put(code, theBuilding);
        }
    }

    public boolean isValidSearch(String query) {
        return buildings.containsKey(query.toLowerCase().trim());
    }

    //returns the requested building object, error handling in presenter
    public Building getBuilding(String name) {
        return buildings.get(name);
    }

    public ArrayList<String> getValidBuildings() {
        return new ArrayList<>(buildings.keySet());
    }


    /*
    reads from a text file that stores the buildings and their coordinates. each line
    contains a building name, latitude, and longitude separated by tabs. this method
    adds each entry to the buildings hashmap using names as keys and Building objects as vals.
    */
    protected void readBuildingData() {
        String file = "res/raw/buildings.txt";
        Scanner scan = new Scanner(this.getClass().getClassLoader().getResourceAsStream(file));

        while (scan.hasNextLine()) {
            String entry = scan.nextLine();
            populateBuildings(entry);
        }

    }
}