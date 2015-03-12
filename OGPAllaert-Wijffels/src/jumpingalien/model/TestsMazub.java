package jumpingalien.model;
import static org.junit.Assert.*;
import jumpingalien.util.Sprite;
import tests.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import jumpingalien.util.*;


public class TestsMazub {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.alien_0_0_sprites1 = new Mazub(0,0,sprites1);
	}
	private Mazub alien_0_0_sprites1;
	private Sprite[] sprites1 = TestUtils.spriteArrayForSize(2, 2, 10 + 20);
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constructor_SingleCase() {
		Mazub alien = new Mazub(0,0,sprites1);
		assertEquals(0, alien.getPositionX());
		assertEquals(0, alien.getPositionY());
		assertEquals(sprites1[0], alien.getCurrentSprite());
	}

	@Test
	public void startMoveLeft_SingleCase(){
		alien_0_0_sprites1.startMoveLeft();
		Util.fuzzyEquals(alien_0_0_sprites1.getHorizontalVelocity(),-1);
		Util.fuzzyEquals(alien_0_0_sprites1.getTime(), alien_0_0_sprites1.getTimeStartLeft());
		assertTrue(alien_0_0_sprites1.getMove());
	}

	@Test
	public void startMoveRight_SingleCase(){
		alien_0_0_sprites1.startMoveRight();
		Util.fuzzyEquals(alien_0_0_sprites1.getHorizontalVelocity(),1);
		Util.fuzzyEquals(alien_0_0_sprites1.getTime(), alien_0_0_sprites1.getTimeStartRight());		
		assertTrue(alien_0_0_sprites1.getMove());
	}

	@Test
	public void endMoveLeft_SingleCase(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.endMoveLeft();
		Util.fuzzyEquals(0, alien_0_0_sprites1.getHorizontalVelocity());
		Util.fuzzyEquals(alien_0_0_sprites1.getTime(), alien_0_0_sprites1.getTimeLastLeft());		
		assertFalse(alien_0_0_sprites1.getMove());
	}

	@Test
	public void endMoveRight_SingleCase(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.endMoveRight();
		Util.fuzzyEquals(0, alien_0_0_sprites1.getHorizontalVelocity());
		Util.fuzzyEquals(alien_0_0_sprites1.getTime(), alien_0_0_sprites1.getTimeLastRight());		
		assertFalse(alien_0_0_sprites1.getMove());
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

	@Test(expected=IllegalStateException.class)
	public void startJump_IllegalStateException1(){
		alien_0_0_sprites1.startJump();
		alien_0_0_sprites1.startJump();
	}
	
	@Test(expected=IllegalStateException.class)
	public void startJump_IllegalStateException2(){
		alien_0_0_sprites1.startDuck();
		alien_0_0_sprites1.startJump();
	}
	
	@Test
	public void startJump_Legal(){
		alien_0_0_sprites1.startJump();
		Util.fuzzyEquals(8,alien_0_0_sprites1.getVerticalVelocity());
		assertTrue(alien_0_0_sprites1.getJump());
	}

	@Test(expected=IllegalStateException.class)
	public void endJump_IllegalStateException(){
		alien_0_0_sprites1.startJump();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.endJump();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.01);
		alien_0_0_sprites1.endJump();
		Util.fuzzyEquals(0,alien_0_0_sprites1.getVerticalVelocity());
	}
	
	@Test
	public void endJump_Legal(){
		alien_0_0_sprites1.startJump();
		alien_0_0_sprites1.endJump();
		Util.fuzzyEquals(0,alien_0_0_sprites1.getVerticalVelocity());
	}
	
	@Test(expected=IllegalStateException.class)
	public void startDuck_IllegalStateException(){
		alien_0_0_sprites1.startJump();
		alien_0_0_sprites1.startDuck();
	}

	@Test
	public void startDuck_Legal(){
		alien_0_0_sprites1.startDuck();
		Util.fuzzyEquals(1,alien_0_0_sprites1.getMaximumHorizontalVelocity());
		assertTrue(alien_0_0_sprites1.getDuck());
	}

	@Test(expected=IllegalStateException.class)
	public void endDuck_IllegalStateException(){
		alien_0_0_sprites1.startDuck();
		alien_0_0_sprites1.endDuck();
		alien_0_0_sprites1.endDuck();
	}
	
	@Test
	public void endDuck_Legal(){
		alien_0_0_sprites1.startDuck();
		alien_0_0_sprites1.endDuck();
		Util.fuzzyEquals(3,alien_0_0_sprites1.getMaximumHorizontalVelocity());
		assertFalse(alien_0_0_sprites1.getDuck());
	}

	@Test
	public void getCurrentSprite_Case0(){
		assertEquals(sprites1[0],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case1(){
		alien_0_0_sprites1.startDuck();
		assertEquals(sprites1[1],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case2(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.endMoveRight();
		assertEquals(sprites1[2],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case3(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.endMoveLeft();
		assertEquals(sprites1[3],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case4(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.startJump();
		assertEquals(sprites1[4],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case5(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.startJump();
		assertEquals(sprites1[5],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case6_1(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.startDuck();
		assertEquals(sprites1[6],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case6_2(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.endMoveRight();
		alien_0_0_sprites1.startDuck();
		assertEquals(sprites1[6],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case7_1(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.startDuck();
		assertEquals(sprites1[7],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case7_2(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.endMoveLeft();
		alien_0_0_sprites1.startDuck();
		assertEquals(sprites1[7],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case8_1(){
		alien_0_0_sprites1.startMoveRight();
		assertEquals(sprites1[8],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case8_2(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.075);
		assertEquals(sprites1[9],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case8_3(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.150);
		assertEquals(sprites1[10],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case8_4(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.025);
		assertEquals(sprites1[11],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case8_5(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.1);
		assertEquals(sprites1[12],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case8_6(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.175);
		assertEquals(sprites1[13],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case8_7(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.05);
		assertEquals(sprites1[14],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case8_8(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.125);
		assertEquals(sprites1[15],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case8_9(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		assertEquals(sprites1[16],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case8_10(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.075);
		assertEquals(sprites1[17],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case8_11(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.15);
		assertEquals(sprites1[18],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case9_1(){
		alien_0_0_sprites1.startMoveLeft();
		assertEquals(sprites1[19],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case9_2(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.075);
		assertEquals(sprites1[20],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case9_3(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.150);
		assertEquals(sprites1[21],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case9_4(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.025);
		assertEquals(sprites1[22],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case9_5(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.1);
		assertEquals(sprites1[23],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case9_6(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.175);
		assertEquals(sprites1[24],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case9_7(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.05);
		assertEquals(sprites1[25],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case9_8(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.125);
		assertEquals(sprites1[26],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case9_9(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		assertEquals(sprites1[27],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case9_10(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.075);
		assertEquals(sprites1[28],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void getCurrentSprite_Case9_11(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.2);
		alien_0_0_sprites1.advanceTime(alien_0_0_sprites1.getHorizontalVelocity(), alien_0_0_sprites1.getVerticalVelocity(), 0.15);
		assertEquals(sprites1[29],alien_0_0_sprites1.getCurrentSprite());
	}

	@Test
	public void isValidPositionX_NegativeValue(){
		assertFalse(alien_0_0_sprites1.isValidPositionX(-10));
	}
	
	@Test
	public void isValidPositionX_PositiveValue() {
		assertFalse(alien_0_0_sprites1.isValidPositionX(1040));
	}
	
	@Test
	public void isValidPositionX_LegalValue() {
		assertTrue(alien_0_0_sprites1.isValidPositionX(5));
	}

	@Test
	public void isValidPositionY_NegativeValue(){
		assertFalse(alien_0_0_sprites1.isValidPositionY(-10));
	}
	
	@Test
	public void isValidPositionY_PositiveValue() {
		assertFalse(alien_0_0_sprites1.isValidPositionY(1040));
	}
	
	@Test
	public void isValidPositionY_LegalValue() {
		assertTrue(alien_0_0_sprites1.isValidPositionY(5));
	}

	@Test
	public void isValidTime_NegativeValue() {
		assertFalse(alien_0_0_sprites1.isValidTime(-10));
	}
	
	@Test
	public void isValidTime_PositiveValue() {
		assertFalse(alien_0_0_sprites1.isValidTime(10));
	}
	
	@Test
	public void isValidTime_LegalValue() {
		assertTrue(alien_0_0_sprites1.isValidTime(0.001));
	}

	@Test
	public void advanceTime_FalseMoveFalseJump(){
		alien_0_0_sprites1.advanceTime(2,-10,0.1);
		Util.fuzzyEquals(0.1, alien_0_0_sprites1.getTime());
		Util.fuzzyEquals(0, alien_0_0_sprites1.getHorizontalVelocity());
		Util.fuzzyEquals(0, alien_0_0_sprites1.getVerticalVelocity());
		assertEquals(0, alien_0_0_sprites1.getPositionX());
		assertEquals(0, alien_0_0_sprites1.getPositionY());
	}

	@Test
	public void advanceTime_TrueMoveFalseJumpMaximumHorizontalVelocity(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.advanceTime(10,-10,0.1);
		Util.fuzzyEquals(0.1, alien_0_0_sprites1.getTime());
		Util.fuzzyEquals(3.09, alien_0_0_sprites1.getHorizontalVelocity());
		Util.fuzzyEquals(0, alien_0_0_sprites1.getVerticalVelocity());
		assertEquals(30, alien_0_0_sprites1.getPositionX());
		assertEquals(0, alien_0_0_sprites1.getPositionY());
	
	}

	@Test
	public void advanceTime_TrueMoveFalseJumpMinusMaximumHorizontalVelocity(){
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.advanceTime(-10,-10,0.1);
		Util.fuzzyEquals(0.1, alien_0_0_sprites1.getTime());
		Util.fuzzyEquals(-3.09, alien_0_0_sprites1.getHorizontalVelocity());
		Util.fuzzyEquals(0, alien_0_0_sprites1.getVerticalVelocity());
		assertEquals(994, alien_0_0_sprites1.getPositionX());
		assertEquals(0, alien_0_0_sprites1.getPositionY());
	}

	@Test
	public void advanceTime_TrueMoveFalseJumpLegalHorizontalVelocity(){
		alien_0_0_sprites1.startMoveRight();
		alien_0_0_sprites1.advanceTime(2,-10,0.1);
		Util.fuzzyEquals(0.1, alien_0_0_sprites1.getTime());
		Util.fuzzyEquals(2.09, alien_0_0_sprites1.getHorizontalVelocity());
		Util.fuzzyEquals(0, alien_0_0_sprites1.getVerticalVelocity());
		assertEquals(20, alien_0_0_sprites1.getPositionX());
		assertEquals(0, alien_0_0_sprites1.getPositionY());
	}

	@Test
	public void advanceTime_FalseMoveTrueJump(){
		alien_0_0_sprites1.startJump();
		alien_0_0_sprites1.advanceTime(2, 2, 0.1);
		Util.fuzzyEquals(0.1, alien_0_0_sprites1.getTime());
		Util.fuzzyEquals(0, alien_0_0_sprites1.getHorizontalVelocity());
		Util.fuzzyEquals(1, alien_0_0_sprites1.getVerticalVelocity());
		assertEquals(0, alien_0_0_sprites1.getPositionX());
		assertEquals(15, alien_0_0_sprites1.getPositionY());
	}

	@Test
	public void advanceTime_TrueMoveTrueJump(){
		alien_0_0_sprites1.startJump();
		alien_0_0_sprites1.startMoveLeft();
		alien_0_0_sprites1.advanceTime(-2, 2, 0.1);
		Util.fuzzyEquals(0.1, alien_0_0_sprites1.getTime());
		Util.fuzzyEquals(-3, alien_0_0_sprites1.getHorizontalVelocity());
		Util.fuzzyEquals(1, alien_0_0_sprites1.getVerticalVelocity());
		assertEquals(1004, alien_0_0_sprites1.getPositionX());
		assertEquals(15, alien_0_0_sprites1.getPositionY());
	}

	@Test(expected=IllegalArgumentException.class)
	public void advanceTime_IllegalArgumentException() {
		alien_0_0_sprites1.advanceTime(2, -10, -3);
	}

}
