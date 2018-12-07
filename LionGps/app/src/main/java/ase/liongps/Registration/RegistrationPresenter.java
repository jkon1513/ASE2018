package ase.liongps.Registration;

public class RegistrationPresenter implements RegistrationContract.Presenter, RegistrationInteractor.RegistrationListener {
	RegistrationContract.View view;
	RegistrationInteractor model;

	public RegistrationPresenter(RegistrationContract.View viewLayer){
		view = viewLayer;
		model = new RegistrationInteractor();
	}

	@Override
	public void createNewUser(String email, String pw) {
		if(!model.isValidEmail(email)){ view.onFailSignUp(); }
		else if(!model.isValidPassword(pw)) {view.onPasswordFail();}
		else {
			model.registerUser(email, pw, this);
		}
	}

	@Override
	public void onSuccess() {
		view.onSuccessSignUp();
	}

	@Override
	public void onFailure() {
        view.onFailSignUp();
	}
}
