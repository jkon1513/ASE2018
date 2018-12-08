package ase.liongps.BuildingPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ase.liongps.R;

public class BuildingPageActivity extends AppCompatActivity implements BuildingPageContract.theView {

	private TextView name;
	private ImageView pic;
	private TextView description;
	private Button nav;
	private BuildingPagePresenter presenter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_building_page);

		Intent incoming = getIntent();
		presenter = new BuildingPagePresenter(this);

		name = findViewById(R.id.name);
		pic = findViewById(R.id.photo);
		description = findViewById(R.id.description);
		nav = findViewById(R.id.nav_button);

		//presenter.setBuilding(incoming.getStringExtra("building_name"));
		showBuildingDescription("login_placeholder");
		showBuildingImage("butler library");
	}

	@Override
	public void showBuildingImage(String bldName){
		String file = bldName.replace(" ", "");
		int id = getResources().getIdentifier(file, "drawable", getPackageName());
		pic.setImageResource(id);
	}

	@Override
	public void showBuildingDescription(String bldName) {
		int id = getResources().getIdentifier(bldName, "string", getPackageName());
		description.setText(id);
	}

	@Override
	public void showBuildingName(String bldName) {
		name.setText(bldName);
	}
}
