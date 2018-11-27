package ase.liongps.Login;

import android.support.annotation.NonNull;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import static ase.liongps.utils.Constants.EMAIL_NOT_FOUND;
import static ase.liongps.utils.Constants.INVALID_PASSWORD;

public class LoginInteractor {
	public FirebaseAuth auth;

	public LoginInteractor(){
	    auth = FirebaseAuth.getInstance();
	}

	public void signIn(String email, String pw, final AuthenticationListener presenter) {
    	auth.signInWithEmailAndPassword(email,pw).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
			@Override
			public void onSuccess(AuthResult authResult) {
				presenter.onSuccess();
			}
		}).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure(@NonNull Exception e) {
				if(e instanceof FirebaseAuthInvalidUserException){
					presenter.onFailure(EMAIL_NOT_FOUND);
				}

				else if(e instanceof FirebaseAuthInvalidCredentialsException){
					presenter.onFailure(INVALID_PASSWORD);
				}
			}
		});
	}

	public interface AuthenticationListener {
		 void onFailure(int errorCode);
		 void onSuccess();
	}





}
