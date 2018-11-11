package ase.liongps.Login;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private LoginContract.Model model;

    LoginPresenter(LoginContract.View view){
        this.view = view;
        this.model = new LoginInteractor();
    }

    @Override
    public void onDestroy() {
        view = null;
    }

}
