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
    }

    interface Presenter {
        void handleSearch(String query);
        List getRecentSearches();
        void getRouteData(Building dest);
        LatLng getLocationData(String name);
    }

}
