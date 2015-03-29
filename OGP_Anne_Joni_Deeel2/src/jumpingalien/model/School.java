package jumpingalien.model;

import jumpingalien.util.Sprite;
// TODO Ik denk eigenlijk dat dit niet echt een game object meer is na de uitleg van de assistent?
public class School extends GameObject{

	/**
	 * Initializes a school with the given position and given sprite.
	 * @param pixelLeftX
	 * 		  The x-coordinate of the given position.
	 * @param pixelBottomY
	 * 		  The y-coordinate of the given position.
	 * @param sprites
	 * 		  An array of all possible images of the school.
	 */
	protected School(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		super(pixelLeftX, pixelBottomY, sprites, 1);
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

}
