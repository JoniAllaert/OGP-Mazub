package jumpingalien.model;

import jumpingalien.util.Sprite;
/**
 * A class for dealing with game objects called slimes.
 * @version  1.0
 * @author   Anne Wijffels
 * @author   Joni Allaert
 */
public class Slime extends GameObject{
	/**
	 * Initializes a plant with the given position and given sprite.
	 * @param pixelLeftX
	 * 		  The x-coordinate of the given position.
	 * @param pixelBottomY
	 * 		  The y-coordinate of the given position.
	 * @param sprites
	 * 		  An array of all possible images of the plant.
	 */
	public Slime(int pixelLeftX, int pixelBottomY, Sprite[] sprites, School school) {
		super(pixelLeftX, pixelBottomY, sprites, 100);
		//TODO: doe iets met school.
	}

	@Override
	public void startMoveLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startMoveRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endMoveLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endMoveRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sprite getCurrentSprite() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private double horizontalVelocity;

	public double getHorizontalVelocity() {
		return horizontalVelocity;
	}

	public void setHorizontalVelocity(double horizontalVelocity) {
		this.horizontalVelocity = horizontalVelocity;
	}
	
	public void advanceTime(double horizontalVelocity, double deltaT){
		
	}
}
