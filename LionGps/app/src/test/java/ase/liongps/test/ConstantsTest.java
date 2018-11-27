package ase.liongps.test;

import org.junit.Assert;
import org.junit.Test;

import ase.liongps.utils.Constants;

public class ConstantsTest {

    @Test
    public void test() {

        Constants c = new Constants();

        // Test that constants have not be mutated - consider this a final check to ensure that constants are changed purposefully

        Assert.assertEquals(7021,c.ERROR_DIALOG_REQUEST);
        Assert.assertEquals(7022,c.PERMISSIONS_REQUEST_FINE_LOCATION);
        Assert.assertEquals(7023,c.PERMISSIONS_REQUEST_ENABLE_GPS);


    }
}
