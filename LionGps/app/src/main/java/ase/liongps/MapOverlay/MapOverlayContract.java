package ase.liongps.MapOverlay;

import com.google.android.gms.maps.GoogleMap;

public interface MapOverlayContract {
    interface View {
        void initSearchBar();
        void onsearchsuccess();
        void onSearchError();
        void showroute();
        void launchProfilePage();
        void showRecentSearches();
    }

    interface Presenter {
        void handleSearch(String query);
        void getRecentSearches();
        void getRouteData();
        void getMapData(GoogleMap map);
    }

}
