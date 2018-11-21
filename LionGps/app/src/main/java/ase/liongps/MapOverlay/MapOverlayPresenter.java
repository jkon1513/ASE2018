package ase.liongps.MapOverlay;


import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import ase.liongps.utils.Building;

public class MapOverlayPresenter implements MapOverlayContract.Presenter{
    SearchInteractor searchModel;
    GeoLocationInteractor geoModel;
    DatabaseInteractor dbModel;
    MapOverlayContract.View view;

    MapOverlayPresenter(MapOverlayContract.View viewLayer){
        view = viewLayer;
        searchModel = new SearchInteractor();
        geoModel = new GeoLocationInteractor();
        //TODO : data base interactor
    }

    @Override
    public void initMap() {
        //place map markers
        //TODO: make unique markers for building locations
        for(String bldngName: searchModel.getValidBuildings()){

            view.placeMarker(getLocationData(bldngName), bldngName);
        }

        //center the camera on start
        view.centerCamera(getLocationData("butler library"), 18.0f);
    }

    public void initSearch(){
        searchModel.readBuildingData();
    }

    @Override
    public void handleSearch(String query) {
        //TODO: 1.save search once db implemented properly

        //2. if valid return result otherwise handle error
        if (searchModel.isValidSearch(query)) {
            Building result = searchModel.getBuilding(query);
            view.showRoute(geoModel.getLocation(result));
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
    public LatLng getLocationData(String name) {
        Building dest = searchModel.getBuilding(name);
        return geoModel.getLocation(dest);
    }
}
