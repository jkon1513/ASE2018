package ase.liongps.Login;


import static ase.liongps.utils.Constants.EMAIL_NOT_FOUND;
import static ase.liongps.utils.Constants.INVALID_PASSWORD;

public class LoginPresenter implements LoginContract.Presenter, LoginInteractor.AuthenticationListener {

    private LoginContract.View view;
    private LoginInteractor model;

    LoginPresenter(LoginContract.View view){
        this.view = view;
        model = new LoginInteractor();
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    public void authenticate(String username, String password) {
        model.signIn(username, password, this);

    }

    @Override
    public void onFailure(int error) {
        if(error == INVALID_PASSWORD){
            view.showPasswordFailure();
        }

        else if(error == EMAIL_NOT_FOUND){
            view.showEmailFailure();
        }
    }

    @Override
    public void onSuccess() {
        view.loadMap();
    }

}
