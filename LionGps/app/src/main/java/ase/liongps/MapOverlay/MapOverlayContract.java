package ase.liongps.MapOverlay;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import ase.liongps.utils.Building;

public interface MapOverlayContract {
    interface View {
        void showSearchBar();
        void onSearchError();
        void showRoute(ArrayList<LatLng> path);
        void launchProfilePage();
        void showRecentSearches(String search);
        void centerCamera(LatLng geoLocation, float zoom);
        void placeMarker(LatLng geoLocation, String markerName);
        void onPermissionDenied();
        void onGeoLocationFailure();
        FusedLocationProviderClient getMyLocator();
        void launchBuildingPage(String buildingName);
    }

    interface Presenter {
        void initMap();
        void initSearch();
        void initUser(String username); //will look into moving this into login contract instead
        void handleSearch(String query);
        void getRecentSearches();
        void getRouteData(String destination);
        LatLng getLocationData(String name);
    }

}
