package ase.liongps.utils;

import java.util.ArrayList;


public class Building {
    private String name;
    private double lat;
    private double lng;
    private String description;
    private ArrayList<String> ammenitites;

    public Building(String name, double latitude, double longitude) {
        this.lat = latitude;
        this.lng = longitude;
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmmenitity(String ammenity) {
        if(ammenitites == null) {
            ammenitites = new ArrayList<>();
            ammenitites.add(ammenity);
        }
    }

    public double getLat() {

        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getAmmenitites() {
        return ammenitites;
    }
}
