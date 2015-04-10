package jumpingalien.model;

import jumpingalien.util.Sprite;

/**
 * A class for dealing with game objects called plants.
 * @version  1.0
 * @author   Anne Wijffels
 * @author   Joni Allaert
 */
public class Plant extends GameObject{

	/**
	 * Initializes a plant with the given position and given sprite.
	 * @param pixelLeftX
	 * 		  The x-coordinate of the given position.
	 * @param pixelBottomY
	 * 		  The y-coordinate of the given position.
	 * @param sprites
	 * 		  An array of all possible images of the plant.
	 */
	public Plant(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		super(pixelLeftX, pixelBottomY, sprites, 1);
	}

	/**
	 * The plant starts moving horizontally to the left.
	 * @pre		Plant is not moving.		
	 * @effect 	Plant starts moving with an initial horizontal velocity to the left (negative).
	 * 		 	| 
//	 * @effect	The variable that registers if the plant is moving is set to true.
//	 * 			| setMove(true)
//	 * @effect 	The variable that registers the last time the plant started moving to the left is set 
//	 * 			to the current game time.
//	 * 			| setTimeStartLeft(this.getTime())
	 */
	@Override
	public void startMoveLeft() {
		this.setVelocity(INITIAL_VELOCITY);
		//this.setTimeLastLeft(this.getTime());
		
		
	}

	/**
	 * The plant starts moving horizontally to the right.
	 * @pre		The plant is not moving.
	 * @effect	The plant starts moving with an initial horizontal velocity to the right (positive).
	 * 		 	| 
//	 * @effect	The variable that registers if the plant is moving is set to true.
//	 * 			| setMove(true)
//	 * @effect 	The variable that registers the last time the plant started moving to the right is set 
//	 *			to the current game time.
//	 * 			| setTimeStartRight(this.getTime())
	 */
	@Override
	public void startMoveRight() {
		this.setVelocity(-INITIAL_VELOCITY);
		//this.setTimeLastRight(this.getTime());
		
	}
	/**
	 * The plant stops moving to the left.
	 * @pre		The plant is moving horizontally to the left.
	 * @effect 	The plant's horizontal velocity equals 0 m/s.
	 * 			| 
//	 * @effect	The variable that registers if the plant is moving is set to false.
//	 * 			| setMove(false)
//	 * @effect 	The variable that registers the last time the plant moved to the left is set to the current game time.
//	 * 			| setTimeLastLeft(this.getTime())
	 */
	@Override
	public void endMoveLeft() {
		this.setVelocity(0);
		
	}

	/**
	 * The plant stops moving to the right.
	 * @pre		The plant is moving horizontally to the right.
	 * @effect 	The plant's horizontal velocity equals 0 m/s.
	 * 			| 
//	 * @effect	The variable that registers if the plant is moving is set to false.
//	 * 			| setMove(false)
//	 * @effect 	The variable that registers the last time the plant moved to the right is set to the current game time.
//	 * 			| setTimeLastRight(this.getTime())
	 */	
	@Override
	public void endMoveRight() {
		this.setVelocity(0);
		
	}
	/**
	 * Variable registering the velocity of the plant.
	 */
	private double velocity;
	
	/**
	 * Variable registering the initial velocity of the plant.
	 */
	private static final double INITIAL_VELOCITY = 0.5;

	/**
	 * Gives the velocity of the plant.
	 */
	public double getVelocity() {
		return velocity;
	}

	/**
	 * Sets the velocity of the plant to the given velocity.
	 * @param velocity
	 * 			The new velocity of the plant.
	 */
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	/**
	 * This method returns the current sprite of the plant.
	 */
	@Override
	public Sprite getCurrentSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	public void advanceTime(double horizontalVelocity,
			double deltaT) {
//		if((this.getTimeLastLeft() + 0.5 >= this.getTime())&&(this.getTimeLastRight() + 0.5 <= this.getTime())){
//			endMoveLeft();
//			startMoveRight();
//		}
//		else {
//			endMoveRight();
//			startMoveLeft();
		}
		
}

