package ase.liongps.MapOverlay;


import android.location.Location;
import android.support.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import ase.liongps.utils.Building;

public class GeoLocationInteractor {
    //camera constraints
    private float minZoom;
    private float maxZoom;

    private FusedLocationProviderClient myLocator;


    public GeoLocationInteractor() {
        // constraints
        minZoom = 18.0f;
        maxZoom = 18.0f;
    }

    public LatLng getBlngLocation(Building target) {
        return new LatLng(target.getLat(), target.getLng());
    }

    public void getCurrentPosition(FusedLocationProviderClient locator, final GeoDataListener listener) {
        try {
            locator.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location current = (Location) task.getResult();
                    listener.myLocationSuccess(new LatLng(current.getLatitude(), current.getLongitude()));
                }
            });
        }

        catch(SecurityException e){
            listener.permissionsRevoked();
        }

    }

    public interface GeoDataListener{
        void myLocationSuccess(LatLng position);
        void onFailure();
        void permissionsRevoked();
    }




}
