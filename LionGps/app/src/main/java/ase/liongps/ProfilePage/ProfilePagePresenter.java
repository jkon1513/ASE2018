package ase.liongps.ProfilePage;

import java.util.Set;

public class ProfilePagePresenter implements ProfilePageContract.thePresenter {
    ProfilePageContract.theView view;
    ProfilePageInteractor model;

	public ProfilePagePresenter(ProfilePageContract.theView viewLayer) {
        view = viewLayer;
        model = new ProfilePageInteractor();
	}

	public void initSchedule(String username){
		view.showUsername(username);
		view.loadScheduleState(username);
	}

	public void initNewClass() {
		view.showAddClassUI();
	}

	public void addToSchedule(String course, String location) {
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

	@Override
	public void saveSchedule(String username) {
		view.saveScheduleState(username);
	}

	public void loadIntoSchedule(Set<String> courseList) {
		boolean success = model.loadSchedule(courseList);
		if (success) {
			for (String course : courseList) {
				view.showNewClass(course);
			}
		}
	}

	public Set<String> getScheduleData() {
		return model.getSchedule();
	}






}
