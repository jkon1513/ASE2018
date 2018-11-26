package ase.liongps.Login;
//contract between the model, view and presenter for login activity (main_activity as of now)

public interface LoginContract {

   // we will update this contract when we add authentication

    interface View {
        void loadMap(android.view.View widget);
        void allowAccessToMap(); //temp for v1 implementation, to be replace by login
        boolean hasPrerequisites();
        void showProgressBar();
        void hideProgressBsr();
        void showSignin();
    }

    interface Presenter {

         void onDestroy();
        // void Authenticate();
    }

}
