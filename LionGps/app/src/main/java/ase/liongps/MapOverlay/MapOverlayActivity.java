package ase.liongps.MapOverlay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ase.liongps.R;



public class MapOverlayActivity extends AppCompatActivity
        implements OnMapReadyCallback, MapOverlayContract.View {

    //widgets
    EditText searchBar;
    ListView leftPanel;
    GoogleMap map;


    private MapOverlayContract.Presenter presenter;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_overlay);
        presenter = new MapOverlayPresenter(this);

        //searchBar
        searchBar = (EditText) findViewById(R.id.searchText);
        showSearchBar();

        //Map
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //user panel
        leftPanel = (ListView) findViewById(R.id.left_drawer);

        /*
            when authentication is added to login activity we will instantiate an intent
            with the username of the authenticated user as a param and pass that username to the
            presenter. for now we will use a test constant to assure data flow is being handled
            correctly: it will eventually look as follows:

            String theUser = getIntent().getStringExtra("username");
            presenter.initUser(theUser);
         */
        presenter.initUser("Hill-Billy-Bob");



    }

    // Map Logic -------------------------------------------------------------------

    @Override
    public void onMapReady(GoogleMap theMap) {
        map = theMap;
        presenter.initMap();
    }

    public void centerCamera(LatLng geoLocation, float zoom) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(geoLocation, zoom));
    }

    public void placeMarker(LatLng geoLocation, String markerName) {
        map.addMarker(new MarkerOptions().position(geoLocation).title(markerName));
    }

    /*
    Routing is not implemented yet. for now this will take a geo-location and center the camera
    ontop of it
    */
    @Override
    public void showRoute(LatLng geoLocation) {
        map.moveCamera(CameraUpdateFactory.newLatLng(geoLocation));
    }

    // Search logic --------------------------------------------------------------------

    public void showSearchBar() {
        presenter.initSearch();

        //overides the enter button of keyboard to search and not create new lines
        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == event.ACTION_DOWN
                        || event.getAction() == event.KEYCODE_ENTER) {

                    presenter.handleSearch(searchBar.getText().toString());
                }

                return false;
            }
        });
    }

    @Override
    public void onSearchError() {
        Toast.makeText(this, "that building is not in our records just yet", Toast.LENGTH_LONG).show();
    }


    @Override
    public void launchProfilePage() {
        //TODO: implement
    }

    @Override
    public void showRecentSearches() {
        //TODO: implement
    }

}
