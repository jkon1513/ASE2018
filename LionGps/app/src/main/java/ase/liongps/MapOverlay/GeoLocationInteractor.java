package ase.liongps.MapOverlay;

import com.google.android.gms.maps.model.LatLng;
import ase.liongps.utils.Building;

public class GeoLocationInteractor {
    //camera constraints
    float minZoom;
    float maxZoom;
    // need to add pan constraints

    public GeoLocationInteractor() {
            minZoom = 18.0f;
            maxZoom = 18.0f; // temp until ironed out
    }

    public LatLng getLocation(Building target) {
        return new LatLng(target.getLat(), target.getLng());
    }
}
