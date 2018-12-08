package ase.liongps.BuildingPage;

public class BuildingPagePresenter implements BuildingPageContract.thePresenter{

	private BuildingPageContract.theView view;

	public BuildingPagePresenter(BuildingPageContract.theView viewLayer) {
		view = viewLayer;
	}


	@Override
	public void loadBuilding(String bldname) {
		String resource = bldname.replace(" ", "");

		view.showBuildingName(bldname);
		view.showBuildingDescription(resource);
		view.showBuildingImage(resource);
	}
}
