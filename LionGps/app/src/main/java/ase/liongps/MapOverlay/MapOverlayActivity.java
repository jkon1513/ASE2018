package ase.liongps.MapOverlay;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.GeoApiContext;

import java.util.ArrayList;

import ase.liongps.R;


public class MapOverlayActivity extends AppCompatActivity
        implements OnMapReadyCallback, MapOverlayContract.View {

    //widgets
    private EditText searchBar;
    private ListView leftPanel;

    //map manipulation
    private GoogleMap map;
    private FusedLocationProviderClient myLocator;

    private MapOverlayContract.Presenter presenter;
    private ArrayAdapter adapter;
    private Intent incoming;
    private GeoApiContext geoContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_overlay);
        incoming = getIntent();
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
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        leftPanel.setAdapter(adapter);


        presenter.initUser(incoming.getStringExtra("username"));
        myLocator = LocationServices.getFusedLocationProviderClient(this);
    }

    // Map Logic -------------------------------------------------------------------


    @Override
    public void onMapReady(GoogleMap theMap) {
        map = theMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.w("permissions fail => ", "made it into the if condition");
            return;
        }

        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(false); // search bar covers and cant move
        presenter.initMap();
    }

    public void centerCamera(LatLng geoLocation, float zoom) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(geoLocation, zoom));
    }

    public void placeMarker(LatLng geoLocation, String markerName) {
        map.addMarker(new MarkerOptions().position(geoLocation).title(markerName));
    }


    @Override
    public void showRoute(ArrayList <LatLng> path) {
        Polyline route = map.addPolyline(new PolylineOptions().addAll(path));
    }

    @Override
    public void onPermissionDenied() {
        Toast.makeText(this,R.string.permissions_fail, Toast.LENGTH_SHORT).show();

        //replace when we develop a sign-out method
        Intent login = new Intent(this, ase.liongps.Login.LoginActivity.class);
        startActivity(login);
    }

    @Override
    public void onGeoLocationFailure() {

    }

    public FusedLocationProviderClient getMyLocator(){
        return myLocator;
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

                    map.clear(); // think about better way to handle clearing poly line
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
    public void showRecentSearches(String search) {
        adapter.add(search);
    }


}
