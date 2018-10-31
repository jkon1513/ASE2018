package ase.liongps.UI;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import ase.liongps.R;

public class mapOverlay extends AppCompatActivity implements OnMapReadyCallback {

    private HashMap<String, LatLng> buildings = new HashMap<>();
    private final String TAG = mapOverlay.class.getName();

    /* Firestore connection established here */
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_overlay);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        populateBuildings();
        initSearchBar();
    }

    // Map Logic -------------------------------------------------------------------

    @Override
    public void onMapReady(GoogleMap theMap) {
        map = theMap;
        ArrayList<String> bldNames = new ArrayList<>(buildings.keySet());

        // place all markers
        for(String name : bldNames){
            map.addMarker(new MarkerOptions()
            .position(buildings.get(name))
            .title(name));
        }

        //center camera
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(buildings.get("lowe library"), 18.0f));
    }

    /*
    reads from a text file that stores the buildings and their coordinates. each line
    contains a building name, latitude, and longitude separated by tabs. this method
    adds each entry to the buildings hashmap using names as keys and geo-location points
    as values. the code below is written verbosely to make this process clear.
     */
    public void populateBuildings() {
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.buildings));

        while (scan.hasNextLine()) {
            String[] entry = scan.nextLine().split("\t");
            String name = entry[0];
            double lat = Double.parseDouble(entry[1]);
            double lng = Double.parseDouble(entry[2]);

            buildings.put(name, new LatLng(lat,lng));

            // convert to unit test
            Log.d(TAG,"populateBuildings >> buidling name: " + name);
            Log.d(TAG,"populateBuildings >> buidling lat: " + lat);
            Log.d(TAG,"populateBuildings >> buidling lng: " + lng);
            Log.d(TAG,"populateBuildings >> result of map.get: " + buildings.get(name));
        }

    }

    /*
    Routing is not implemented yet. for now this will take a building name and center the camera
    ontop of it
     */
    public void getRoute(String bldngName) {
        Log.d(TAG, "getRoute: " + bldngName);
        map.moveCamera(CameraUpdateFactory.newLatLng(buildings.get(bldngName)));
    }

    // Search logic --------------------------------------------------------------------

    public void initSearchBar() {
        Log.d(TAG, "initSearchBar: initializing");

        EditText searchBar = (EditText) findViewById(R.id.searchText);

        //overides the enter button of keyboard to search and not create new lines
        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == event.ACTION_DOWN
                        || event.getAction() == event.KEYCODE_ENTER){

                    handleSearch();
                }

                return false;
            }
        });
    }

    //returns boolean for unit tests
    public boolean handleSearch() {
        EditText searchBar = (EditText) findViewById(R.id.searchText);
        String query = searchBar.getText().toString().toLowerCase().trim();

        if(!buildings.containsKey(query)){
            Toast.makeText(this, "that building is not in our records just yet", Toast.LENGTH_LONG).show();
            return false;
        }

        //addSearchString(query); will test with Chris
        getRoute(query);
        return true;
    }


        /*
    Takes a string input and stores it in the cloud as a Map<String, String> entry.
    This uses the .add() method, which auto-generates a key for the document we are
    adding to our "Search History" Collection. This is opposed to .set(), which re-
    quires we specify a key
     */

    public void addSearchString(String input) {

        HashMap<String, String> searchValue = new HashMap<>();
        searchValue.put("entry", input);


        db.collection("Search History")
                .add(searchValue)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                })
                .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                });

    }


}
