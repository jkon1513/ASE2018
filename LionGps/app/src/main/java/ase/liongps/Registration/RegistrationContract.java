package ase.liongps.Registration;

public interface RegistrationContract {

	interface View {
		void signUp(android.view.View view);
		void onSuccessSignUp();
		void onFailSignUp();
		void onPasswordFail();
	}

	interface Presenter {
		void createNewUser(String email, String pw);
	}
}
