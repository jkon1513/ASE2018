package ase.liongps.BuildingPage;

public interface BuildingPageContract {

	interface theView {
		void showBuildingImage(String bldName);
		void showBuildingDescription(String bldName);
		void showBuildingName(String bldName);
	}

	interface thePresenter {
		void loadBuilding();
	}
}
