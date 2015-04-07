package jumpingalien.model;

import jumpingalien.util.Sprite;

/**
 * A class for dealing with game objects called sharks.
 * @version  1.0
 * @author   Anne Wijffels
 * @author   Joni Allaert
 */
public class Shark extends GameObject{

<<<<<<< HEAD
	protected Shark(int pixelLeftX, int pixelBottomY, Sprite[] sprites,
			int hitpoints) {
		super(pixelLeftX, pixelBottomY, sprites, hitpoints);
		// TODO Auto-generated constructor stub
	}

=======
	/**
	 * Initializes a shark with the given position and given sprite.
	 * @param pixelLeftX
	 * 		  The x-coordinate of the given position.
	 * @param pixelBottomY
	 * 		  The y-coordinate of the given position.
	 * @param sprites
	 * 		  An array of all possible images of the plant.
	 */
	protected Shark(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		super(pixelLeftX, pixelBottomY, sprites, 100);
	}
	
>>>>>>> origin/master
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

}
