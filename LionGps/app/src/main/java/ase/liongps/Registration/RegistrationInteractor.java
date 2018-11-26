package ase.liongps.Registration;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
					//call presenter.onSuccess to let user know it was saved
				}
				else {
					//this will automatically occur if the username already exists
				}

			}
		});
	}

	public boolean isValidEmail(String username) {
		return username.matches(VALID_EMAIL);

	}

	public boolean isValidPassword(String pw) {
		boolean validLength = pw.length() >= 6;
		boolean hasUppercase = false;
		boolean hasSymbol = false;
		List<Character> symbols;

		Character[] x = {
				'+', '-', '&', '|', '!', '(',')', '{', '}', '[', ']', '^',
				'~', '*', '?', ':','@'};

		symbols = Arrays.asList(x);


		for(int i = 0; i<pw.length(); i++ ){
			Character current = pw.charAt(i);

			if(!Character.isLetterOrDigit(current) && !symbols.contains(current)) { return false; }
			else if (Character.isUpperCase(current)) { hasUppercase = true; }
			else if(symbols.contains(current)) { hasSymbol = true; }
		}

		return (validLength && hasUppercase && hasSymbol);
	}
}
