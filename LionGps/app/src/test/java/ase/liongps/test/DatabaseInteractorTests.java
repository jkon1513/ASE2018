package ase.liongps.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;


import ase.liongps.MapOverlay.DatabaseInteractor;
import ase.liongps.utils.User;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;


@RunWith(MockitoJUnitRunner.class)
public class DatabaseInteractorTests {

    
    @Mock private DatabaseInteractor db = new DatabaseInteractor();
    @Captor private ArgumentCaptor<User> userCaptor;

    @Test
    public void testSaveUser() {
        // we don't want to make a network connection
        doNothing().when(db).saveUser(userCaptor.capture());


        User newUser = new User("jkon", "password");
        db.saveUser(newUser);
        Mockito.verify(db).saveUser(newUser);
        User capturedArg = userCaptor.getValue();

        Assert.assertEquals("username of passed user did not match expected",
                newUser.getName(), capturedArg.getName());

        Assert.assertEquals("the expected user pw does not match actual pw",
                newUser.getPw(), capturedArg.getPw());



        newUser = new User("hulk hogan", "brother");
        db.saveUser(newUser);
        Mockito.verify(db).saveUser(newUser);
        capturedArg = userCaptor.getValue();


        Assert.assertEquals("username of passed user did not match expected",
                newUser.getName(), capturedArg.getName());

        Assert.assertEquals("the expected user pw does not match actual pw",
                newUser.getPw(), capturedArg.getPw());

    }

    @Test
	public void loadUserTest(){
    	final User savedState = new User();
    	final User theUser = new User();

    	Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				User arg = invocation.getArgument(0);
				savedState.setName(arg.getName());
				savedState.setPw(arg.getPw());
				return null;
			}
		}).when(db).saveUser(isA(User.class));

		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				if(invocation.getArgument(0) == savedState.getName()) {
					theUser.setName(savedState.getName());
					theUser.setPw(savedState.getPw());
					return null;
				}

				return null;
			}
		}).when(db).loadUserData(isA(String.class));

		Mockito.doAnswer(new Answer<User>() {
			@Override
			public User answer(InvocationOnMock invocation) throws Throwable {
				return theUser;
			}
		}).when(db).getUser();


		User naruto = new User ("Naruto Izumaki", "rasengan");
		db.saveUser(naruto);
		db.loadUserData("Naruto Izumaki");
		User loaded = db.getUser();

		Assert.assertEquals("loaded username does not match the username that was saved",
				naruto.getName(), loaded.getName());

		Assert.assertEquals("loaded pw does not match the pw that was saved",
				naruto.getPw(), loaded.getPw());



	}
}
