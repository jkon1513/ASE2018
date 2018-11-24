package ase.liongps.test;

import android.provider.ContactsContract;

import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ase.liongps.MapOverlay.DatabaseInteractor;
import ase.liongps.utils.User;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseInteractorTests {

    private static User testUser = new User("Gandalf", "The Gray");
    private static FirebaseFirestore fbfsMock = Mockito.mock(FirebaseFirestore.class);
    public static DatabaseInteractor dbInteractTest = new DatabaseInteractor(fbfsMock, testUser);



    @BeforeClass
    public static void setup() {

        testUser.updateHistory("Mordor");

    }

    @Test
    public void checkUser() {

        // test if user is retrieved
        Assert.assertNotNull(dbInteractTest.getUser("Gandalf"));
        // test that testUser is the same user provided to Database Interactor via constructor
        Assert.assertEquals(testUser, dbInteractTest.getUser("Gandalf"));

        // used mocked FirebaseFirestore to return user object

        



    }
}
