package ase.liongps.Registration;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;


import ase.liongps.utils.User;

import static ase.liongps.utils.Constants.VALID_EMAIL;

public class RegistrationInteractor {

	private FirebaseAuth auth;
	private FirebaseFirestore db;
	private User theUser;

	public RegistrationInteractor() {
		this.auth = FirebaseAuth.getInstance();
		this.db = FirebaseFirestore.getInstance();
		this.theUser = new User();
	}

	public void registerUser(final String email, final String password){
		auth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				if(task.isSuccessful()){
					saveNewUser(email, password);
				}
				else {
					//this will automatically occur if the username already exists
					//TODO add failure handler here that will display toast in view
				}

			}
		});
	}

	public void saveNewUser(String username, String password) {
		theUser.setName(username);
		theUser.setPw(password);
		theUser.initSearches();
		db.collection("TestData")
				.document(username).set(theUser); // add onFailure listener
	}

	public boolean isValidEmail(String username) {
		return username.matches(VALID_EMAIL);

	}

	public boolean isValidPassword(String pw) {
		boolean validLength = pw.length() >= 6;
		boolean hasUppercase = false;
		boolean hasSymbol = false;
		List<Character> symbols;

		Character[] specialChars = {
				'+', '-', '&', '|', '!', '(',')', '{', '}', '[', ']', '^',
				'~', '*', '?', ':','@'};

		symbols = Arrays.asList(specialChars);


		for(int i = 0; i<pw.length(); i++ ){
			Character current = pw.charAt(i);

			if(!Character.isLetterOrDigit(current) && !symbols.contains(current)) { return false; }
			else if (Character.isUpperCase(current)) { hasUppercase = true; }
			else if(symbols.contains(current)) { hasSymbol = true; }
		}

		return (validLength && hasUppercase && hasSymbol);
	}
}
