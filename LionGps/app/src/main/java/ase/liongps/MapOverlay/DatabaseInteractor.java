package ase.liongps.MapOverlay;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import ase.liongps.utils.User;

public class DatabaseInteractor {
	private User theUser;
	private FirebaseFirestore db;
	private static boolean success;

	public DatabaseInteractor() {
		db = FirebaseFirestore.getInstance();
		theUser = new User();
	}

	public boolean saveUser(User u){

		db.collection("Users").document(theUser.getName()).set(theUser)
				.addOnSuccessListener(new OnSuccessListener<Void>() {
					@Override
					public void onSuccess(Void aVoid) {
						success = true;
					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						success = false;
					}
				});

		return success;
	}

	public User getUser(String username){
		//no need to re-connecr if were looking for the same user we have loaded
		if(username.equals(theUser.getName())){
			return theUser;
		}

		else {
			//connect to db and set theUser = to the one we want
		}

	}
}
