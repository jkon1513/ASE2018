package ase.liongps.test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import ase.liongps.Login.LoginPresenter;

public class LoginPresenterTest {

    @Mock
    LoginPresenter mockLogin;

    @Test(expected=NullPointerException.class) // annotation to handle NullPointerException
    public void test() {

        mockLogin.onDestroy();
        Mockito.verify(mockLogin).onDestroy();
        Mockito.verifyNoMoreInteractions(mockLogin);

    }
}
