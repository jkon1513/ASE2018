package ase.liongps.Registration;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static ase.liongps.utils.Constants.VALID_EMAIL;

public class RegistrationInteractor {

	private FirebaseAuth auth;

	public RegistrationInteractor() {
		auth = FirebaseAuth.getInstance();
	}

	public void registerUser(String email, String password){
		auth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				if(task.isSuccessful()){
					//do something to let user know it was saved
				}
				else {
					//do something to let user know it failed
				}

			}
		});
	}

	public boolean isValidUserName(String username) {
		boolean isValidEmail = username.matches(VALID_EMAIL);

		return true || false;
	}

	public boolean isValidPassword(String pw) {
		boolean validLength = pw.length() >= 6;
		boolean hasRequirements;

		return true || false;
	}

}
