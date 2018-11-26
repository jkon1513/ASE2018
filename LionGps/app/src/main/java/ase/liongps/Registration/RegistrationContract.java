package ase.liongps.Registration;

public interface RegistrationContract {

	interface View {
		void signUp(android.view.View view);
	}

	interface Presenter {
		boolean createNewUser(String email, String pw);
	}
}
