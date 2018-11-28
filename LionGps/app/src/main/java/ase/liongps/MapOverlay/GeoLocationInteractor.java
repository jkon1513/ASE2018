package ase.liongps.MapOverlay;


import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;

import java.util.ArrayList;
import java.util.List;

import ase.liongps.utils.Building;

import static ase.liongps.utils.Constants.API_KEY;

public class GeoLocationInteractor {
    //camera constraints
    private float minZoom;
    private float maxZoom;
    private GeoApiContext geoContext;
    private LatLng myLocation;


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
                    boolean setup = myLocation == null;
                    myLocation = new LatLng(current.getLatitude(), current.getLongitude());
                    if(setup) {
                        listener.initLocationSuccess(myLocation);
                        geoContext = new GeoApiContext.Builder().apiKey(API_KEY).build();
                    }

                    else {
                        listener.onMyLocationSuccess(myLocation);
                    }
                }
            });
        }

        catch(SecurityException e){
            listener.permissionsRevoked();
        }

    }

    public LatLng getMyLocation(){
        return myLocation;
    }

   public void calculateDirections(LatLng start, LatLng finish, final GeoDataListener listener) {
       DirectionsApiRequest request = new DirectionsApiRequest(geoContext);

       request.origin(new com.google.maps.model.LatLng(start.latitude, start.longitude));
       request.mode(TravelMode.WALKING);
       request.destination(new com.google.maps.model.LatLng(finish.latitude, finish.longitude))
               .setCallback(new PendingResult.Callback<DirectionsResult>() {
                   @Override
                   public void onResult(DirectionsResult result) {
                       generatePath(result, listener);
                   }

                   @Override
                   public void onFailure(Throwable e) {
                        listener.onFailure();
                   }
               });

   }

   //look into how to optimize this, and if threading is really needed here
   private void generatePath(final DirectionsResult result, final GeoDataListener listener){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                DirectionsRoute route = result.routes[0];
                List<com.google.maps.model.LatLng> decode =
                        PolylineEncoding.decode(route.overviewPolyline.getEncodedPath());

                ArrayList<LatLng> path = new ArrayList<>();

                for (com.google.maps.model.LatLng geopoint: decode){
                    path.add(new LatLng(geopoint.lat, geopoint.lng));
                }

                listener.onDirectionsSuccess(path);
            }


        });
   }



   public interface GeoDataListener{
        void initLocationSuccess(LatLng position);
        void onMyLocationSuccess(LatLng position);
        void onFailure();
        void permissionsRevoked();
        void onDirectionsSuccess(ArrayList<LatLng> route);
    }




}
