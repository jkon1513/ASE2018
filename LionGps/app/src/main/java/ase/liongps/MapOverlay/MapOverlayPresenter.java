package ase.liongps.MapOverlay;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import ase.liongps.utils.Building;

public class MapOverlayPresenter implements MapOverlayContract.Presenter, GeoLocationInteractor.GeoDataListener{
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
        //place map markers
        //TODO: make unique markers for building locations
//        for(String bldngName: searchModel.getValidBuildings()){
//
//            view.placeMarker(getLocationData(bldngName), bldngName);
//        }

        //center the camera on start
       getMyLocation();
    }

    @Override
    public void initUser(String username) {
            dbModel.loadUserData(username);
    }

    @Override
    public void initSearch(){
        searchModel.readBuildingData();
    }

    @Override
    public void handleSearch(String query) {
        if (searchModel.isValidSearch(query)) {
            dbModel.updateHistory(query);
            Building result = searchModel.getBuilding(query);
            view.showRoute(geoModel.getBlngLocation(result));
        } else {
            view.onSearchError();
        }
    }

    @Override
    public List getRecentSearches() {
        return null;
    }

    @Override
    public void getRouteData(Building dest) {
        //TODO once routes api in place
    }

    @Override
    public void getMyLocation() {
        geoModel.getCurrentPosition(view.getMyLocator(), this);
    }

    @Override
    public LatLng getLocationData(String name) {
        Building dest = searchModel.getBuilding(name);
        return geoModel.getBlngLocation(dest);
    }

    @Override
    public void myLocationSuccess(LatLng position) {
        view.centerCamera(position, 18.0f);
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void permissionsRevoked() {
        view.onPermissionDenied();
    }
}
