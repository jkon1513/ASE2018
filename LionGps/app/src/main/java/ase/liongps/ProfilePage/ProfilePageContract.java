package ase.liongps.ProfilePage;

import java.util.Set;

public interface ProfilePageContract {

	interface theView {
		void showUsername(String user);
		void showAddClassUI();
		void hideAddClassUI();
		void showNewClass(String entry);
		void showSuccess();
		void showFailure();
		void saveScheduleState(String username);
		void loadScheduleState(String username);

	}

	interface thePresenter {
		void initSchedule(String username);
		void initNewClass();
		void addToSchedule(String course, String location);
		void loadIntoSchedule(Set <String> courseList);
		Set <String> getScheduleData();
		void saveSchedule(String username);
	}
}
