package ase.liongps.MapOverlay;

public interface MapOverlayContract {
    interface View {
        void initSearchBar();
        void initMap();
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
        void getMapData();
    }

}
