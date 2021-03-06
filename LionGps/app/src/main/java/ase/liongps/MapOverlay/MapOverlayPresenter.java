package ase.liongps.MapOverlay;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import ase.liongps.utils.Building;

public class MapOverlayPresenter implements MapOverlayContract.Presenter, GeoLocationInteractor.GeoDataListener,
DatabaseInteractor.dbListener{
    SearchInteractor searchModel;
    GeoLocationInteractor geoModel;
    DatabaseInteractor dbModel;
    MapOverlayContract.View view;

    MapOverlayPresenter(MapOverlayContract.View viewLayer){
        view = viewLayer;
        searchModel = new SearchInteractor();
        geoModel = new GeoLocationInteractor();
        dbModel = new DatabaseInteractor();
    }

    @Override
    public void initMap() {
        //center the camera on my location
        geoModel.getCurrentPosition(view.getMyLocator(), this);
    }

    @Override
    public void initUser(String username) {
            dbModel.loadUserData(username, this);
    }

    @Override
    public void initSearch(){
        searchModel.readBuildingData();
        view.initAutoComplete(searchModel.getValidBuildings());
        view.showSearchBar();
    }

    @Override
    public void handleSearch(String query) {
        if (searchModel.isValidSearch(query)) {
            Building target = searchModel.getBuilding(query);

            dbModel.updateHistory(target.getName());
            view.showRecentSearches(target.getName());
            view.launchBuildingPage(target.getName());

        } else {
            view.onSearchError();
        }
    }

    @Override
    public void getRecentSearches() {
        List<String> searches = dbModel.getUser().getSearches();
        for(String search : searches){
            view.showRecentSearches(search);
        }
    }

    @Override
    public void getRouteData(String destination) {
        Building target = searchModel.getBuilding(destination);
        view.placeMarker(getLocationData(destination), destination);
        geoModel.calculateDirections(geoModel.getMyLocation(),geoModel.getBlngLocation(target), this);
    }


    @Override
    public LatLng getLocationData(String name) {
        Building dest = searchModel.getBuilding(name);
        return geoModel.getBlngLocation(dest);
    }

    @Override
    public void onMyLocationSuccess(LatLng position) {

    }

    @Override
    public void initLocationSuccess(LatLng position) {
        view.centerCamera(position, 18.0f);
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void permissionsRevoked() {
        view.onPermissionDenied();
    }

    @Override
    public void onDirectionsSuccess(ArrayList<LatLng> route) {
        view.showRoute(route);
    }

    @Override
    public void onUserLoadSuccess() {
        getRecentSearches();
    }
}
