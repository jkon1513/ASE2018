package ase.liongps.Login;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import ase.liongps.MapOverlay.MapOverlayActivity;
import ase.liongps.R;

import static ase.liongps.utils.Constants.ERROR_DIALOG_REQUEST;
import static ase.liongps.utils.Constants.PERMISSIONS_REQUEST_ENABLE_GPS;
import static ase.liongps.utils.Constants.PERMISSIONS_REQUEST_FINE_LOCATION;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    /* The design decision currently will be to get all the required permissions for GPS and
        location services, and check for the required versions of Google Play Services
        upon launch of the app, rather than launch of the map

        in order to run our we need to verify the users device has:

        1. google play services
        2. granted us permission to access location data
        3. has gps location enabled

        We will include this in the view since we rely on the activity and visual popups
     */

    private final static String TAG = "loginActivity"; //will replace to loginActivity.class.getName();
    private static boolean locationPermissionGranted = false;
    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(hasPrerequisites()){

            if(locationPermissionGranted){
                allowAccessToMap();
            }
            else{
                getLocationPermission();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    /* this might need to be replaced once login is implemented. for now it loads the
            map activity once the launch button is clicked. */
    @Override
    public void loadMap(View widget) {
        Intent map = new Intent(this, MapOverlayActivity.class);
        startActivity(map);
    }

    // once permissions all pass this makes the launch button clickable
    public void allowAccessToMap() {
        Button launch = (Button) findViewById(R.id.launch);
        launch.setClickable(true);
        Log.d(TAG, "allowAccessToMap: launch button now clickable");
    }










    // PERMISSIONS ------------------------------------------------------------------

    // verifies gps is enabled and google play services installed
    public boolean hasPrerequisites(){
        return (hasGoogleServices() && hasLocationEnabled());
    }


    // devices must have google services enabled. this verifies that fact.
    public boolean hasGoogleServices(){
        Log.d(TAG, "hasGoogleServices: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(LoginActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "hasGoogleServices: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "hasGoogleServices: services not found but can be retrieved");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(LoginActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "Your device is not compatible with LionGPS", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    //checks if the user has gps enabled on their device, if not it calls a method to help them enable
    public boolean hasLocationEnabled(){
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
            return false;
        }
        return true;
    }


    // if GPS is disabled on users device, this walks them through enabling it.
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This application requires GPS to run, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        Intent enableGpsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(enableGpsIntent, PERMISSIONS_REQUEST_ENABLE_GPS);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    /*
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a listener,
     * onRequestPermissionsResult.
     */
    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
            allowAccessToMap();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_FINE_LOCATION);
        }
    }



     // Listeners / Callbacks below --------------------------------------------------------------------------------------

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        locationPermissionGranted = false;

        switch (requestCode) {
            case PERMISSIONS_REQUEST_FINE_LOCATION: {
                // If request is somehow cancelled the array is empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true;
                }
            }
        }
    }

    // this is what is called when an activity returns an intent,
    // we can differentiate based on request code.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: called.");
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ENABLE_GPS: {
                if(locationPermissionGranted){
                    allowAccessToMap();
                }
                else{
                    getLocationPermission();
                }
            }
        }

    }
}
