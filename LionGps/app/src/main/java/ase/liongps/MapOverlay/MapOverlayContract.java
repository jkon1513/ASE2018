package ase.liongps.MapOverlay;


import com.google.android.gms.maps.model.LatLng;
import java.util.List;

import ase.liongps.utils.Building;

public interface MapOverlayContract {
    interface View {
        void showSearchBar();
        void onSearchError();
        void showRoute(LatLng geoLocation);
        void launchProfilePage();
        void showRecentSearches();
        void centerCamera(LatLng geoLocation, float zoom);
        void placeMarker(LatLng geoLocation, String markerName);
    }

    interface Presenter {
        void initMap();
        void initSearch();
        void handleSearch(String query);
        List getRecentSearches();
        void getRouteData(Building dest);
        LatLng getLocationData(String name);
    }

}
