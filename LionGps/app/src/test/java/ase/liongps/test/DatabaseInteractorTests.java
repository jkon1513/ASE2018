package ase.liongps.test;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

import ase.liongps.MapOverlay.DatabaseInteractor;
import ase.liongps.utils.User;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseInteractorTests {

    private User testUser = new User("Gandalf", "The Gray");

    @Mock
    private DatabaseInteractor dbInteractTest;


    @Before
    public void setup() {
        testUser.updateHistory("Mordor");
    }

    @Test
    public void checkUser() {

        // test behavior of method without returning testUser
        dbInteractTest.getUser("Gandalf");
        // test there are no side effects to getUser method
        verify(dbInteractTest).getUser("Gandalf");


        // use mockito to simulate return of User
        when(dbInteractTest.getUser("Gandalf")).thenReturn(testUser);


        // test if user is retrieved
        Assert.assertNotNull(dbInteractTest.getUser("Gandalf"));
        // test that testUser is the same user provided to Database Interactor via constructor
        Assert.assertEquals(testUser, dbInteractTest.getUser("Gandalf"));

        // check if retrieved user has search entries in list
        User test = dbInteractTest.getUser("Gandalf");
        if (test.getSearches().isEmpty()) {
            Assert.fail("Empty testList in DatabaseInteractorTests.java!");
        } else {
            List testlist = test.getSearches();
            Assert.assertEquals("Mordor", testlist.get(0));
        }







    }
}
