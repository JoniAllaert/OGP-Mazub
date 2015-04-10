package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;
/**
 * A class for dealing with game objects.
 * @version  1.0
 * @author   Anne Wijffels
 * @author   Joni Allaert
 */
public abstract class GameObject {
	/**
	 * Initializes a game object with the given position and given sprite and the number of hitpoints.
	 * @param pixelLeftX
	 * 		  The x-coordinate of the given position.
	 * @param pixelBottomY
	 * 		  The y-coordinate of the given position.
	 * @param sprites
	 * 		  An array of all possible images of the game object.
	 * @param hitpoints
	 * 		  The number of hitpoints the game object maximally possesses.
	 */
	protected GameObject(int pixelLeftX, int pixelBottomY, Sprite[] sprites, int hitpoints){
		this.positionX = pixelLeftX;
		this.positionY = pixelBottomY;
		this.sprites = sprites;
		this.hitPoints = hitpoints;
	}

	public abstract void startMoveLeft();
	public abstract void startMoveRight();
	public abstract void endMoveLeft();
	public abstract void endMoveRight();
	public abstract Sprite getCurrentSprite();
	
	/**
	 * This method gives the width of the current sprite of the game object.
	 */
	@Basic
	public int getWidth(){
		return this.getCurrentSprite().getWidth();
	}

	/**
	 * This method gives the height of the current sprite of the game object.
	 */
	@Basic
	public int getHeight() {
		return this.getCurrentSprite().getHeight();
	}
	/**
	 * Array of all the images to display the game object
	 */
	protected Sprite[] sprites;

	public Sprite[] getSprites() {
		return sprites.clone();
	}

	private void setSprites(Sprite[] sprites) {
		this.sprites = sprites;
	}

	/**
	 * Returns the x-coordinate of the position of game object.
	 */
	@Basic
	public int getPositionX(){
		return this.positionX;
	}

	/**
	 * Set the x-coordinate of the position of game object. 
	 *
	 * @param	position
	 *			The new x-position of the game object.
	 * @post    If the given position is in the range established by the minimum
	 *          and maximum position of the game object, the x-coordinate of the game object is equal to position. 
	 *        	| if ( (position >= this.getMinPositionX()) && (position <= this.getMaxPositionX()) )
	 *        	| 	new this.getPositionX() == position
	 * @post    If the given position is in the range established by the minus of the
	 *          maximum position and the minimum position of the game object, the x-coordinate of the game object 
	 *          is equal to position plus the maximum position + 1. 
	 *        	| if((position >= -this.getMaxPositionX()-1)&&(position < this.getMinPositionX()))
	 * 			| position += this.getMaxPositionX()+1;
	 * @post    If the given position is in the range established by the maximum position
	 *          and two times the maximum position + 1 of the game object, the x-coordinate 
	 *          of the game object is equal to position minus the maximum position + 1.
	 *        	| if((position > this.getMaxPositionX())&&(position <= 2*this.getMaxPositionX()+1))
	 * 			| position -= this.getMaxPositionX()+1;
	 * @throws  IllegalArgumentException
	 * 			The position exceeds 2 times the maximum position plus 1 or is smaller than 
	 * 			minus the maximum position minus 1.
	 * 			| ( position > 2*this.getMaxPositionX() +1) || (position < -this.getMaxPositionX() -1) 			
	 */	
	protected void setPositionX(int position) throws IllegalArgumentException{
		if(!isValidPositionX(position)){
				throw new IllegalArgumentException();
		}
		this.positionX = position;
	}

	/**
	 * A method that checks if the position of X is a valid position.
	 * @param 	position
	 * 			The position to check.
	 * @return	True if and only if the value of the position is larger than the minimum value 
	 * 			for the x-position and is smaller than the maximum value for the x-position.
	 * 			| result = ((position>=this.getMinPositionX())&&(position<=this.getMaxPositionX()))
	 */
	public boolean isValidPositionX(int position){
		return ((position>=this.getMinPositionX())&&(position<=this.getMaxPositionX()));
	}

	/**
	 * Variable that registers the x-coordinate of the game object.
	 */
	private int positionX;

	

	/**
	 * Returns the y-coordinate of the position of the game object.
	 */
	@Basic
	public int getPositionY(){
		return this.positionY;
	}

	/**
	 * Set the y-coordinate of the position of the game object. 
	 *
	 * @param	position
	 *			The new y-position of the game object.
	 * @post    If the given position is in the range established by the minimum and maximum position of the game object, 
	 *          the y-coordinate of the game object is equal to the given position. 
	 *        	| if ( (position >= this.getMinPositionY()) && (position <= this.getMaxPositionY()) )
	 *        	|   then new.getPositionY() = position
	 * @post    If the given position exceeds the the maximum position of the game object the new 
	 * 			y-coordinate of the game object is equal to the maximum position of the game object.
	 *        	| if (position > this.getMaxPositionY())
	 *        	|  		new.getPositionY() = this.getMaxPositionY()
	 * @post	If the given position is smaller than the minimum position of the game object, the
	 * 			new y-coordinate of the game object is equal to the minimum position of the game object.
	 * 			| if (position< this.getMinPositionY())
	 * 			| 		new.getPositionY() = this.getMinPositionY()
	 */
	protected void setPositionY(int position) throws IllegalArgumentException{
		if(!isValidPositionY(position)){
			throw new IllegalArgumentException();
		}
		this.positionY = position;
	}

	/**
	 * A method that checks if the position of Y is a valid position.
	 * @param 	position
	 * 			The position to check.
	 * @return	True if and only if the value of the position is larger than the minimum value for 
	 * 			the y-position and is smaller than the maximum value for the y-position.
	 * 			| result = ((position>=this.getMinPositionY())&&(position<=this.getMaxPositionY()))
	 */
	public boolean isValidPositionY(int position){
		return ((position>=this.getMinPositionY())&&(position<=this.getMaxPositionY()));
	}

	/**
	 * Variable registering the y-coordinate of the game object.
	 */
	private int positionY;
	
	
	/**
	 * This method gives you the minimal x-position of the game object.
	 */
	public int getMinPositionX(){
		return MIN_POSITIONX;
	}

	/**
	 * Variable that registers the minimal x-position of the game object.
	 */
	private static final int MIN_POSITIONX = 0;

	/**
	 * This method gives you the maximal x-position of the game object.
	 */
	public int getMaxPositionX(){
		return MAX_POSITIONX;
	}
	/**
	 * Variable that registers the maximal x-position of the game object.
	 */
	private static final int MAX_POSITIONX = 1023;
	
	/**
	 * This method gives you the minimal y-position of the game object.
	 */
	public int getMinPositionY(){
		return MIN_POSITIONY;
	}

	/**
	 * Variable registering the minimal y-coordinate of the game object.
	 */
	private static final int MIN_POSITIONY = 0;

	/**
	 * This method gives you the maximal y-position of the game object.
	 */
	public int getMaxPositionY(){
		return MAX_POSITIONY;
	}

	/**
	 * Variable registering the maximal y-coordinate of the game object.
	 */
	private static final int MAX_POSITIONY = 767;
	
	/**
	 * This method gives you the current time duration of the game.
	 */
	@Basic
	public double getTime(){
		return this.time;
	}

	/**
	 * Sets the current time duration of the game.
	 * @param time
	 * 			The new current time.
	 * @post	The new current time is equal to the given time.
	 * 			| new this.getTime = time
	 */
	protected void setTime(double time){
		this.time = time;
	}

	/**
	 * A variable that keeps track of the time that has passed.
	 */
	private double time;	
	
	/**
	 * This method gives you the time when the game object last finished moving left.
	 */
	@Basic
	public double getTimeLastLeft(){
		return this.timeLastLeft;
	}

	/**
	 * Sets the time when the game object last stops moving to the left.
	 * @param time
	 * 			The new time when the game object last stopped moving to the left.
	 * @post	The new time when the game object last stopped moving to the left is equal to the given time
	 * 			| new this.getTimeLastLeft = time
	 */
	protected void setTimeLastLeft(double time){
		this.timeLastLeft = time;
	}

	/**
	 * Variable registering when the game object stops moving to the left. 
	 * This variable is initialized to -2 because the game object has not yet moved to the left.
	 */
	private double timeLastLeft=-2;
	
	/**
	 * This method gives you the time when the game object last finished moving right.
	 */
	@Basic
	public double getTimeLastRight(){
		return this.timeLastRight;
	}

	/**
	 * Sets the time when the game object last stops moving to the right.
	 * @param time
	 * 			The new time when the game object last stopped moving to the right.
	 * @post	The new time when the game object last stopped moving to the right is equal to the given time
	 * 			| new this.getTimeLastRight = time
	 */
	protected void setTimeLastRight(double time){
		this.timeLastRight = time;
	}

	/**
	 * Variable registering when the game object stops moving to the right. 
	 * This variable is initialized to -2 because the game object has not yet moved to the right.
	 */
	private double timeLastRight=-2;
	
	/**
	 * Variable registering the number of hitpoints of the game object.
	 */
	private int hitPoints;

	/**
	 * Returns the current number of hitpoints that the game object possesses.
	 */
	public int getHitPoints() {
		return hitPoints;
	}
	/**
	 * Sets the number of hitpoints to the given integer.
	 * @param hitPoints
	 * 		  The new number of hitpoints the game object possesses.
	 */
	protected void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}
	/**
	 * This method changes the status of the game object to terminated.
	 */
	protected void terminates(){
		this.dead = true;
	}
	/**
	 * Variable registering if a game object is terminated or not.
	 */
	private boolean dead;
	
	public boolean isTerminated(){
		return dead;
	}
	
//	public abstract void advanceTime(double deltaT);
}
