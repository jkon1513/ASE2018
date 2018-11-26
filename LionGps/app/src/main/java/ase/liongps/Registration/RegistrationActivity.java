package ase.liongps.Registration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ase.liongps.R;

public class RegistrationActivity extends AppCompatActivity implements RegistrationContract.View {

	private EditText email;
	private EditText password;
	private RegistrationContract.Presenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		presenter = new RegistrationPresenter(this);

		email = findViewById(R.id.reg_email);
		password = findViewById(R.id.reg_pw);
	}

	@Override
	public void signUp(View view){
		Log.w("signUP:","made it to signUp");
		presenter.createNewUser(email.getText().toString(), password.getText().toString());
	}

	@Override
	public void onSuccessSignUp() {
		Toast.makeText(this,"Sign up was a Success!!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onFailSignUp() {
		Toast.makeText(this, "That email already has an account associated with it",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPasswordFail() {
		Toast.makeText(this, R.string.password_failure, Toast.LENGTH_SHORT).show();
	}


}
