package ase.liongps.Login;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private LoginInteractor model;

    LoginPresenter(LoginContract.View view){
        this.view = view;

    }

    @Override
    public void onDestroy() {
        view = null;
    }

}
