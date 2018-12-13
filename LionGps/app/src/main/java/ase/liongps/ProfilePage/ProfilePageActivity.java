package ase.liongps.ProfilePage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ase.liongps.R;

public class ProfilePageActivity extends AppCompatActivity implements ProfilePageContract.theView {
	private LinearLayout root;
	private LinearLayout classEntry;
	private TextView username;
	private EditText courseField;
	private EditText buildingfield;
	private Button addEntryButton;
	private Button saveButton;

	SharedPreferences classes;
	ProfilePageContract.thePresenter presenter;
	Intent incoming;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_page);

		root = findViewById(R.id.profile_layout);
		username = (TextView) findViewById(R.id.username_display);
		addEntryButton = (Button) findViewById(R.id.add_course);

		classEntry = (LinearLayout) findViewById(R.id.new_class);
		courseField = (EditText) classEntry.findViewById(R.id.course_name);
		buildingfield = (EditText) classEntry.findViewById(R.id.course_location);
		saveButton = (Button) classEntry.findViewById(R.id.save_course);

		incoming = getIntent();
		classes = getPreferences(MODE_PRIVATE);

		presenter = new ProfilePagePresenter(this);
		presenter.initSchedule(incoming.getStringExtra("username"));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		presenter.saveSchedule(incoming.getStringExtra("username"));
	}

	public void showUsername(String user){
		username.setText(user + "'s profile page");
	}

	public void addClassListener(View view){
		presenter.initNewClass();
	}

	//Todo: convert to dialog or ui inflater
	@Override
	public void showAddClassUI() {
		courseField.getText().clear();
		buildingfield.getText().clear();

		addEntryButton.setVisibility(View.GONE);
		courseField.setVisibility(View.VISIBLE);
		buildingfield.setVisibility(View.VISIBLE);
		saveButton.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideAddClassUI() {
		courseField.setVisibility(View.GONE);
		buildingfield.setVisibility(View.GONE);
		saveButton.setVisibility(View.GONE);
		addEntryButton.setVisibility(View.VISIBLE);
	}

	public void saveClass(View view){
		if(TextUtils.isEmpty(courseField.getText())) {
			courseField.setError(" Class name is a required field");
		}

		else if (TextUtils.isEmpty(buildingfield.getText())) {
			buildingfield.setError("Location is a required field");
		}

		else {

			presenter.addToSchedule(courseField.getText().toString(), buildingfield.getText().toString());
		}
	}

	public void showNewClass(String entry){
		TextView course = new TextView(this);
		course.setText(entry);
		course.setTextColor(Color.parseColor("#000000"));

		root.addView(course);
	}

	@Override
	public void showSuccess() {
		Toast.makeText(this,"Your class was added!!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showFailure() {
		Toast.makeText(this,"Something went wrong adding your class", Toast.LENGTH_SHORT).show();
	}

	public void saveScheduleState(String username){
		SharedPreferences.Editor schedule = classes.edit();
		schedule.putStringSet(username , presenter.getScheduleData());
		schedule.apply();
	}

	public void loadScheduleState(String username) {
        presenter.loadIntoSchedule(classes.getStringSet(username , null));
	}
}
