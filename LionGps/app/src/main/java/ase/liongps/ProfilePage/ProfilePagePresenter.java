package ase.liongps.ProfilePage;

public class ProfilePagePresenter implements ProfilePageContract.thePresenter {
    ProfilePageContract.theView view;
    ProfilePageInteractor model;

	public ProfilePagePresenter(ProfilePageContract.theView viewLayer) {
        view = viewLayer;
        model = new ProfilePageInteractor();
	}

	public void initNewClass() {
		view.showAddClassUI();
	}

	public void saveToSchedule(String course, String location) {
		String entry = course + " : " + location;

		boolean success = model.addToSchedule(entry);
		if(success){
			view.showNewClass(entry);
			view.hideAddClassUI();
			view.showSuccess();
		}
		else {
			view.hideAddClassUI();
			view.showFailure();
		}
	}




}
