package ase.liongps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /* The desgin decision currently will be to get all the required permissions for GPS and
        location services, and check for the required versions of Google Play Services
        upon launch of the app, rather than launch of the map
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* this might need to be replaced once login is implemented. for now it loads the
    map activity once the continue button is clicked. */
    public void loadMap(View view) {
        //if all checks pass
        Intent map = new Intent(this, mapOverlay.class);
        startActivity(map);
    }
}
