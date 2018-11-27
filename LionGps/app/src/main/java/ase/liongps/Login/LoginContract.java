package ase.liongps.Login;
//contract between the model, view and presenter for login activity (main_activity as of now)

public interface LoginContract {

   // we will update this contract when we add authentication

    interface View {
        void loadMap();
        void allowAccessToMap();
        boolean hasPrerequisites();
        void showPasswordFailure();
        void showEmailFailure();

    }

    interface Presenter {

         void onDestroy();
         void authenticate(String username, String password);
    }

}
