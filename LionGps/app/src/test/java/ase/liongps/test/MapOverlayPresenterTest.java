package ase.liongps.test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import ase.liongps.MapOverlay.MapOverlayPresenter;

public class MapOverlayPresenterTest {

    @Mock
    private MapOverlayPresenter mockMap;

    @Test(expected=NullPointerException.class) // annotations to handle null point exception
    public void test (){

        /*  constructor of MapOverlayPresenter requires a View object as a parameter. Since the constructor is not invoked, methods should
            incur NullPointerExceptions when they attempt to utilize methods from objects that are initalized by the constructor. Since
            they are never initalized, NullPointerExceptions abound - this is handled by the annotation above this class
        */

        Assert.assertEquals(null,mockMap.getRecentSearches());
        Mockito.verifyNoMoreInteractions(mockMap);

        // verify that method was invoked
        Mockito.verify(mockMap).getRecentSearches();
        // verify no side effects occur after get recent searches is invoked
        Mockito.verifyNoMoreInteractions(mockMap);

        mockMap.initMap();
        Mockito.verify(mockMap).initMap();
        Mockito.verifyNoMoreInteractions(mockMap);

        mockMap.initUser("Gandalf");
        Mockito.verify(mockMap).initUser("Gandalf");
        Mockito.verifyNoMoreInteractions(mockMap);

        mockMap.getLocationData("Ham");
        Mockito.verify(mockMap).getLocationData("Ham");
        Mockito.verifyNoMoreInteractions(mockMap);

    }
}
