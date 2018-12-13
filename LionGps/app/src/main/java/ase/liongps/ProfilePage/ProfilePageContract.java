package ase.liongps.ProfilePage;

public interface ProfilePageContract {

	interface theView {
		void showAddClassUI();
		void hideAddClassUI();
		void showNewClass(String entry);
		void showSuccess();
		void showFailure();

	}

	interface thePresenter {
		void initNewClass();
		void saveToSchedule(String course, String location);
	}
}
