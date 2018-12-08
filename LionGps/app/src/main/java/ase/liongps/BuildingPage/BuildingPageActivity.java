package ase.liongps.BuildingPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ase.liongps.R;

public class BuildingPageActivity extends AppCompatActivity implements BuildingPageContract.theView {

	private TextView name;
	private ImageView pic;
	private TextView description;

	private BuildingPagePresenter presenter;
	private Intent incoming;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_building_page);

		incoming = getIntent();
		presenter = new BuildingPagePresenter(this);

		name = findViewById(R.id.name);
		pic = findViewById(R.id.photo);
		description = findViewById(R.id.description);

		presenter.loadBuilding(incoming.getStringExtra("building"));
	}

	@Override
	public void showBuildingImage(String bldName){
		int id = getResources().getIdentifier(bldName, "drawable", getPackageName());
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

	public void navigate(View view){
		Intent result = new Intent();
		result.putExtra("destination", incoming.getStringExtra("building"));
		setResult(RESULT_OK, result);
		finish();
	}
}
