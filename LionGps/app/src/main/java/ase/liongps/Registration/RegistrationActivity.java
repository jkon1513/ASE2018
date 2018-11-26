package ase.liongps.Registration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ase.liongps.R;

public class RegistrationActivity extends AppCompatActivity implements RegistrationContract.View {

	private EditText email;
	private EditText password;
	private RegistrationContract.Presenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		presenter = new RegistrationPresenter();

		email = findViewById(R.id.reg_email);
		password = findViewById(R.id.reg_pw);
	}

	@Override
	public void signUp(View view){
		presenter.createNewUser(email.getText().toString().trim()
				, password.getText().toString().trim());
	}
}
