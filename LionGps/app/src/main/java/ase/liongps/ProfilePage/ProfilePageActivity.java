package ase.liongps.ProfilePage;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import ase.liongps.R;

public class ProfilePageActivity extends AppCompatActivity {
	private LinearLayout root;
	private TextView userText;
	private Button addEntry;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_page);

		root = findViewById(R.id.profile_layout);
		userText = (TextView) findViewById(R.id.username_display);
		addEntry = (Button) findViewById(R.id.add_course);

		Intent incoming = getIntent();
		showUsername(incoming.getStringExtra("username"));

	}

	private void showUsername(String user){
		userText.setText(user + "'s profile page");
	}

	public void addNewClass(View view) {
		addEntry.setVisibility(View.GONE);
		LinearLayout entry = (LinearLayout) findViewById(R.id.new_class);
		EditText course = (EditText)entry.findViewById(R.id.course_name);
		EditText location = (EditText) entry.findViewById(R.id.course_location);
		Button save = (Button) entry.findViewById(R.id.save_course);

		course.setVisibility(View.VISIBLE);
		location.setVisibility(View.VISIBLE);
		save.setVisibility(View.VISIBLE);
	}

	public void saveClass(View view){
		LinearLayout entry = (LinearLayout) findViewById(R.id.new_class);
		EditText cField = (EditText) entry.findViewById(R.id.course_name);
		EditText lField = (EditText) entry.findViewById(R.id.course_location);
		Button save = (Button) entry.findViewById(R.id.save_course);

		TextView course = new TextView(this);
        course.setText(cField.getText().toString() +  " : " + lField.getText().toString());
        course.setTextColor(Color.parseColor("#000000"));

        cField.setVisibility(View.GONE);
        lField.setVisibility(View.GONE);
        save.setVisibility(View.GONE);
        addEntry.setVisibility(View.VISIBLE);

        root.addView(course);
	}
}
