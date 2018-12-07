package ase.liongps.Registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
		if(TextUtils.isEmpty(email.getText())) {
			email.setError("email is a required field");
		}

		else if (TextUtils.isEmpty(password.getText())) {
			password.setError("Password is a required field");
		}

		else {
			String em = email.getText().toString();
			String pw = password.getText().toString();
			presenter.createNewUser(em, pw);
		}
	}

	@Override
	public void onSuccessSignUp() {
		String un = email.getText().toString();
		String pw = password.getText().toString();

		Toast.makeText(this,"Sign up was a Success!!", Toast.LENGTH_SHORT).show();
		Intent returnData = new Intent();
		returnData.putExtra("username", un);
		returnData.putExtra("password", pw);
		setResult(RESULT_OK,returnData);
		finish();
	}

	@Override
	public void onFailSignUp() {
		Toast.makeText(this, "The email you entered is invalid, or already has an account associated with it. Please try again",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPasswordFail() {
		Toast.makeText(this, R.string.password_failure, Toast.LENGTH_SHORT).show();
	}


}
