package ase.liongps.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import ase.liongps.Registration.RegistrationInteractor;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationInteractorTest {
    private static RegistrationInteractor reg;
    private static ArrayList<String> validEmails;
	private static ArrayList<String> invalidEmails;
	private static ArrayList<String> validPasswords;
	private static ArrayList<String> invalidPasswords;

    @BeforeClass
	public static void setup() {
		reg = new RegistrationInteractor("testing");

		String[] emails = {
				"suckersRus@google.com",
				"ASE4ME@columbia.edu",
				"ho!ySm0k3$@yahoo.org",};

		String[] emailErrors = {
				"jk1234@google",
				" ",
				"\t\n",
				"@google.com",
				"my name is jason",
				"1234gmail.com",
				".invalid@apple.org"
		};

		String[] passwords = {
				"BigDawg!123",
				"{Mightymouse}",
				"H@rryP0tter"
		};

		String[] passwordErrors = {
			"short",
			"no-uppercase",
			"noSymbol",
			"@$!%@#!"
		};

		validEmails = new ArrayList<>(Arrays.asList(emails));
		validPasswords = new ArrayList<>(Arrays.asList(passwords));
		invalidEmails = new ArrayList<>(Arrays.asList(emailErrors));
		invalidPasswords = new ArrayList<>(Arrays.asList(passwordErrors));
	}

	@Test
    public void isValidEmailTest(){
		for(String email : validEmails){
			Assert.assertTrue("A valid email has been rejected from registration",
					reg.isValidEmail(email));
		}

		for(String email: invalidEmails){
			Assert.assertFalse("an invalid email was registered successfully. the email was: "
					+ email, reg.isValidEmail(email) );
		}

	}

	@Test
	public void isValidPassword(){
    	for (String password : validPasswords) {
    		Assert.assertTrue("a valid password was rejected. tes password: " + password,
					reg.isValidPassword(password));
		}

		for(String password: invalidPasswords) {
    		Assert.assertFalse("an invalid password was accepted. the password was : "
					+ password, reg.isValidPassword(password));
		}
	}
}
