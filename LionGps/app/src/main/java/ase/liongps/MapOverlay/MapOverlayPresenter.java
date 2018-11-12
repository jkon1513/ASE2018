package ase.liongps.MapOverlay;

public class MapOverlayPresenter implements MapOverlayContract.Presenter{
    SearchInteractor searchModel;
    MapOverlayContract.View view;

    MapOverlayPresenter(MapOverlayContract.View viewLayer){
        view = viewLayer;
        searchModel = new SearchInteractor();
    }

    @Override
    public void handleSearch(String query) {
        if (searchModel.isValidSearch(query)) {
            //mapModel(??)
            //dbModel.save(query);
            System.out.println("Presenter: handle search routed correctly");


        }

        else {
            view.onSearchError();
        }
    }

    @Override
    public void getRecentSearches() {

    }

    @Override
    public void getRouteData() {

    }

    @Override
    public void getMapData() {
        searchModel.populateBuildings();
    }
}
