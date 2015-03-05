package jumpingalien.model;
import static org.junit.Assert.*;
import OilTank;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestsMazub {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		alien_0_0_sprites1 = new Mazub(0,0,sprites1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	//TODO sprites meegeven, is singlecase genoeg?
	@Test
	public void constructor_SingleCase() {
		Mazub alien = new Mazub(0,0,sprites);
		assertEquals(0, alien.getPositionX());
		assertEquals(0, alien.getPositionY());
		assertEquals(sprites[0], alien.getCurrentSprite());
	}
	
	@Test
	public void setHorizontalVelocity_NegativeValue() {
		alien_0_0_sprites1.setHorizontalVelocity(-5);
		assertEquals(-3,alien_0_0_sprites1.getHorizontalVelocity());
	}
	
	@Test
	public void setHorizontalVelocity_PositiveValue() {
		alien_0_0_sprites1.setHorizontalVelocity(5);
		assertEquals(3,alien_0_0_sprites1.getHorizontalVelocity());
	}

	@Test
	public void setHorizontalVelocity_LegalValue() {
		alien_0_0_sprites1.setHorizontalVelocity(1);
		assertEquals(1,alien_0_0_sprites1.getHorizontalVelocity());
	}
	
	@Test
	public void setMaximumHorizontalVelocity_SingleCase() {
		alien_0_0_sprites1.setMaximumHorizontalVelocity(1);
		assertEquals(1,alien_0_0_sprites1.getMaximumHorizontalVelocity());
	}
	
	@Test
	public void isValidHorizontalVelocity_NegativeValue() {
		assertFalse(alien_0_0_sprites1.isValidHorizontalVelocity(-5));
	}
	@Test
	public void isValidHorizontalVelocity_PositiveValue() {
		assertFalse(alien_0_0_sprites1.isValidHorizontalVelocity(5));
	}
	@Test
	public void isValidHorizontalVelocity_LegalValue() {
		assertTrue(alien_0_0_sprites1.isValidHorizontalVelocity(1));
	}

	@Test
	public void setVerticalVelocity_SingleCase() {
		alien_0_0_sprites1.setVerticalVelocity(5);
		assertEquals(5,alien_0_0_sprites1.getVerticalVelocity());
	}

	@Test
	public void isValidHorizontalVelocity_IllegalValue() {
		assertFalse(alien_0_0_sprites1.isValidHorizontalVelocity(10));
	}
	@Test
	public void isValidHorizontalVelocity_8() {
		assertTrue(alien_0_0_sprites1.isValidHorizontalVelocity(8));
	}
	@Test
	public void isValidHorizontalVelocity_NegativeValue() {
		assertTrue(alien_0_0_sprites1.isValidHorizontalVelocity(-5));
	}
	
	@Test
	public void setCurrentSprite_LegalValue() {
		alien_0_0_sprites1.setCurrentSprite(15);
		assertEquals(sprites1[15], alien_0_0_sprites1.getCurrentSprite());
	}
	//TODO Wat te doen bij assert als verwachte waarde
	@Test
	public void setCurrentSprite_IllegalValue() {
		alien_0_0_sprites1.setCurrentSprite(60);
		assertEquals(sprites1[60], alien_0_0_sprites1.getCurrentSprite());
	}
	
	@Test
	public void setPositionX_LegalValue() {
		alien_0_0_sprites1.setPositionX(506);
		assertEquals(506, alien_0_0_sprites1.getPositionX());
	}
	
	@Test
	public void setPositionX_IllegalValuePositive() {
		alien_0_0_sprites1.setPositionX(1123);
		assertEquals(99, alien_0_0_sprites1.getPositionX());
	}
	
	@Test
	public void setPositionX_IllegalValueNegative() {
		alien_0_0_sprites1.setPositionX(-100);
		assertEquals(924, alien_0_0_sprites1.getPositionX());
	}
	
	@Test
	public void setPositionY_LegalValue() {
		alien_0_0_sprites1.setPositionY(423);
		assertEquals(423, alien_0_0_sprites1.getPositionY());
	}
	
	@Test
	public void setPositionY_IllegalValuePositive() {
		alien_0_0_sprites1.setPositionY(920);
		assertEquals(767, alien_0_0_sprites1.getPositionY());
	}
	
	@Test
	public void setPositionY_IllegalValueNegative() {
		alien_0_0_sprites1.setPositionY(-1023);
		assertEquals(0, alien_0_0_sprites1.getPositionY());
	}
	
	@Test
	public void isValidTime_NegativeValue() {
		assertFalse(alien_0_0_sprites1.isValidTime(-10));
	}
	@Test
	public void isValidTime_PositiveValue() {
		assertFalse(alien_0_0_sprites1.isValidHorizontalVelocity(10));
	}
	@Test
	public void isValidTime_LegalValue() {
		assertTrue(alien_0_0_sprites1.isValidHorizontalVelocity(0.001));
	}

	//TODO Hoe testen we advanceTime(illegalargumentexceptions, juiste waarden)
	@Test
	public void advanceTime_Legal(){
		alien_0_0_sprites1.advanceTime(2,-10,0.1);
	}
	
	@Test
	public void distanceTravelledHorizontal_Maximum(){
		assertEquals(3,alien_0_0_sprites1.distanceTravelledHorizontal(3,1));
	}
	@Test
	public void distanceTravelledHorizontal_Positive(){
		assertEquals(10.45,alien_0_0_sprites1.distanceTravelledHorizontal(10,1));
	}
	@Test
	public void distanceTravelledHorizontal_Negative(){
		assertEquals(-10,45,alien_0_0_sprites1.distanceTravelledHorizontal(-10,1));
	}

	@Test
	public void distanceTravelledVertical_8(){
		assertEquals(8,alien_0_0_sprites1.distanceTravelledVertical(8,1));
	}
	@Test
	public void distanceTravelledVertical_Positive(){
		assertEquals(0,alien_0_0_sprites1.distanceTravelledVertical(10,1));
	}
	@Test
	public void distanceTravelledVertical_Negative(){
		assertEquals(-15,alien_0_0_sprites1.distanceTravelledVertical(-10,1));
	}

	
}
