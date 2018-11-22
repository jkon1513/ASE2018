package ase.liongps.MapOverlay;

import com.google.firebase.firestore.FirebaseFirestore;

import ase.liongps.utils.User;

public class DatabaseInteractor {
	private User theUser;
	private FirebaseFirestore db;

	public DatabaseInteractor() {
		db = FirebaseFirestore.getInstance();
		theUser = new User();
	}

	public boolean saveUser(User u){
		return true || false;
	}

	public User getUser(String username){
		return theUser;
	}
}
