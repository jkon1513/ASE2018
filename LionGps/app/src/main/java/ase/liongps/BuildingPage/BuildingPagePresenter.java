package ase.liongps.BuildingPage;

public class BuildingPagePresenter implements BuildingPageContract.thePresenter{

	private BuildingPageContract.theView view;

	public BuildingPagePresenter(BuildingPageContract.theView viewLayer) {
		view = viewLayer;
	}


	@Override
	public void loadBuilding(String bldname) {
		view.showBuildingName(bldname);
		view.showBuildingDescription(bldname);
		view.showBuildingImage(bldname);
	}
}
