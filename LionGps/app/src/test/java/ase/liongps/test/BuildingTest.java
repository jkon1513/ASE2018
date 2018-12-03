package ase.liongps.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import ase.liongps.utils.Building;

@RunWith(MockitoJUnitRunner.class)
public class BuildingTest {
	private Building bld;
	private String shorthand;
	private String code;
	private String desc;

	@Before
	public void setup() {
		shorthand = "test";
		code = "t";
		desc = "a building meant for teating purposes";

		bld = new Building("test building", 0.0,0.0);
		bld.setShortHand(shorthand);
		bld.setBldngCode(code);
		bld.setDescription(desc);
		bld.setAmmenitity("testing lounge");
	}

	@Test
	public void getCoordinateTest(){
		Assert.assertEquals(0.0, bld.getLat(), 0);
		Assert.assertEquals(0.0, bld.getLng(),0);
	}


	@Test
	public void ShorthandTest(){
        Assert.assertEquals("the shorthand was not set correctly",
				shorthand, bld.getShortHand());

        String newShort = "changed short hand";
        bld.setShortHand(newShort);

		Assert.assertEquals("the shorthand was not set correctly",
				newShort, bld.getShortHand());
	}

	@Test
	public void codeTest(){
		Assert.assertEquals("the code was not set correctly",
				code, bld.getBldngCode());

		String newCode = "nc";
		bld.setBldngCode(newCode);

		Assert.assertEquals("the code was not set correctly",
				newCode, bld.getBldngCode());
	}

	@Test
	public void descriptionTest(){
		Assert.assertEquals("the description was not set correctly",
				desc, bld.getDescription());

		String newDesc = "We are changing the description to something else";
		bld.setDescription(newDesc);

		Assert.assertEquals("the description was not set correctly",
				newDesc, bld.getDescription());
	}

	@Test
	public void AmmenityTest(){
		Assert.assertTrue("the ammenities are not accurate",
				bld.getAmmenitites().contains("testing lounge"));

		bld.setAmmenitity("mockito bar");
		bld.setAmmenitity("chris' gyro stand");

		Assert.assertEquals("new ammenities are not being added correctly",
				3, bld.getAmmenitites().size());

		Assert.assertTrue("new ammenities are not ",
				bld.getAmmenitites().contains("testing lounge"));
	}

}
