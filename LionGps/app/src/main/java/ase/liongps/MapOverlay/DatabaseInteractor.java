package ase.liongps.MapOverlay;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;



import ase.liongps.utils.User;

//TODO: possible we will need to add check for existing username here for new registrations
//TODO: look into whether saves need to occur on each search or if local version can be cached
//TODO: handle case of search already existing in history (or perhaps in user class)

public class DatabaseInteractor {
	private static User theUser;
	private FirebaseFirestore db;
	private DocumentReference userData;


	public DatabaseInteractor() {
		theUser = new User();
	}

	public void initFireBase() {
		db = FirebaseFirestore.getInstance();
	}


	public void saveUser(User u){
		if(db == null) { initFireBase();}

		userData = db.collection("TestData").document(u.getName());

		userData.set(u)
				.addOnSuccessListener(new OnSuccessListener<Void>() {
					@Override
					public void onSuccess(Void aVoid) {
						//what would make sense here since data cant be returned synchronously?
						System.out.println("placeholder");
					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						System.out.println(e);
					}
				});

	}

	public User getUser() {
		return theUser;
	}


	public void loadUserData(final String username, final dbListener listener){
			if(db == null) {initFireBase();}

			// will replace TestData with a "Users" collection
			userData = db.collection("TestData").document(username);

			userData.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
				@Override
				public void onSuccess(DocumentSnapshot documentSnapshot) {
					theUser = documentSnapshot.toObject(User.class);
					listener.onUserLoadSuccess();
				}
			});
	}

	public void updateHistory(String query) {
		theUser.updateHistory(query);
		saveUser(theUser);
	}

	public interface dbListener {
		void onUserLoadSuccess();
	}

}
