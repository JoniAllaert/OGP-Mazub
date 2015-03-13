package jumpingalien.model;
import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
//TODO:import be.kuleuven.cs.som.annotate.Raw;
//TODO: nog eens kijken naar start move left en right, wat willen we hier precies mee doen?

/**
 * A class for dealing with aliens called Mazub.
 * @invar	 Mazub's bottom left pixel must always stay on the screen.
 * 			 | (this.getPositionX() <= this.MAX_POSITIONX) && (this.getPositionX() >= this.MIN_POSITIONX)&&
 * 			 | (this.getPositionY() <= this.MAX_POSITIONY) && (this.getPositionY() >= this.MIN_POSITIONY)
 * @invar	 Mazub's horizontal velocity lies between minus the maximum horizontal velocity and the maximum 
 * 			 horizontal velocity.
 * 			 | (this.getHorizontalVelocity() <= this.getMaximumHorizontalVelocity) && 
 * 			 (this.getHorizontalVelocity() >= -this.getMaximumHorizontalVelocity)
 * @invar	 Mazub's vertical velocity is always smaller than its initial vertical velocity.
 * 			 | this.getVerticalVelocity <= this.INITIAL_VERTICAL_VELOCITY
 * @version  1.0
 * @author   Anne Wijffels
 * @author   Joni Allaert
 */
public class Mazub {
	
	/**
	 * Initializes an alien with the given position and given sprite.
	 * @param pixelLeftX
	 * 		  The x-coordinate of the given position.
	 * @param pixelBottomY
	 * 		  The y-coordinate of the given position.
	 * @param sprites
	 * 		  An array of all possible images of the alien.
	 * @post  The current sprite is the image of the alien when he is not moving horizontally,
	 * 		  has not moved horizontally in the last second of in-game-time and is not ducking.
	 * 		  | new.getCurrentSprite() = sprites[0]
	 */
	public Mazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		this.positionX = pixelLeftX;
		this.positionY = pixelBottomY;
		this.sprites = sprites;
	}

	/**
	 * Mazub starts moving horizontally to the left.
	 * @pre		Mazub is not moving horizontally to the left.		
	 * @effect 	Mazub starts moving with an initial horizontal velocity to the left (negative).
	 * 		 	| setHorzontalAcceleration( - this.getInitialHorizontalVelocity())
	 * @effect	The variable that registers if Mazub is moving is set to true.
	 * 			| setMove(true)
	 * @effect 	The variable that registers the last time Mazub started moving to the left is set to the current game time.
	 * 			| setTimeStartLeft(this.getTime())
	 */
	public void startMoveLeft(){
		assert (
				//(!this.getMove()) && 
				(this.getHorizontalVelocity() >= 0));
		this.setHorizontalVelocity(-this.getInitialHorizontalVelocity());
		this.setMove(true);
		this.setTimeStartLeft(this.getTime());
	}

	/**
	 * This method gives you the time when Mazub last started moving left.
	 */
	@Basic
	public double getTimeStartLeft(){
		return this.timeStartLeft;
	}

	/**
	 * Sets the time when Mazub last started moving to the left.
	 * @param time
	 * 			The new time when Mazub last started moving to the left.
	 * @post	The new time when Mazub last started moving to the left is equal to the given time.
	 * 			| new this.getTimeStartLeft = time
	 */
	private void setTimeStartLeft(double time){
		this.timeStartLeft = time;
	}

	/**
	 * Variable registering the time when Mazub starts moving to the left.
	 */
	private double timeStartLeft;

	/**
	 * Mazub starts moving horizontally to the right.
	 * @pre		Mazub is not moving horizontally to the right.
	 * @effect	Mazub starts moving with an initial horizontal velocity to the right (positive).
	 * 		 	| setHorzontalAcceleration(this.getInitialHorizontalVelocity())
	 * @effect	The variable that registers if Mazub is moving is set to true.
	 * 			| setMove(true)
	 * @effect 	The variable that registers the last time Mazub started moving to the right is set to the current game time.
	 * 			| setTimeStartRight(this.getTime())
	 */
	public void startMoveRight(){
		assert (
				//(!this.getMove()) && 
				(this.getHorizontalVelocity() <= 0));
		this.setHorizontalVelocity(this.getInitialHorizontalVelocity());
		this.setMove(true);
		this.setTimeStartRight(this.getTime());
	}

	/**
	 * This method gives you the time when Mazub last started moving right.
	 */
	@Basic
	public double getTimeStartRight(){
		return this.timeStartRight;
	}

	/**
	 * Sets the time when Mazub last started moving to the right.
	 * @param time
	 * 			The new time when Mazub last started moving to the right.
	 * @post	The new time when Mazub last started moving to the right is equal to the given time.
	 * 			| new this.getTimeStartRight = time
	 */
	private void setTimeStartRight(double time){
		this.timeStartRight = time;
	}

	/**
	 * Variable registering the time when Mazub starts moving to the right. 
	 */
	private double timeStartRight;

	/**
	 * Mazub stops moving to the left.
	 * @pre		Mazub is moving horizontally.
	 * @effect 	Mazub's horizontal velocity equals 0 m/s.
	 * 			| setHorizontalVelocity(0)
	 * @effect	The variable that registers if Mazub is moving is set to false.
	 * 			| setMove(false)
	 * @effect 	The variable that registers the last time Mazub moved to the left is set to the current game time.
	 * 			| setTimeLastLeft(this.getTime())
	 */
	public void endMoveLeft(){
		assert this.getMove();
		this.setHorizontalVelocity(0);
		this.setMove(false);
		this.setTimeLastLeft(this.getTime());
	}

	/**
	 * This method gives you the time when Mazub last finished moving left.
	 */
	@Basic
	public double getTimeLastLeft(){
		return this.timeLastLeft;
	}

	/**
	 * Sets the time when Mazub last stops moving to the left.
	 * @param time
	 * 			The new time when Mazub last stopped moving to the left.
	 * @post	The new time when Mazub last stopped moving to the left is equal to the given time
	 * 			| new this.getTimeLastLeft = time
	 */
	private void setTimeLastLeft(double time){
		this.timeLastLeft = time;
	}

	/**
	 * Variable registering when Mazub stops moving to the left. 
	 * This variable is initialized to -2 because Mazub has not yet moved to the left.
	 */
	private double timeLastLeft=-2;

	/**
	 * Mazub stops moving to the right.
	 * @pre		Mazub is moving horizontally.
	 * @effect  Mazub's horizontal velocity equals 0 m/s.
	 * 			| setHorizontalVelocity(0)
	 * @effect	The variable that registers if Mazub is moving is set to false.
	 * 			| setMove(false)
	 * @effect 	The variable that registers the last time Mazub moved to the right is set to the current game time.
	 * 			| setTimeLastRight(this.getTime())
	 */
	public void endMoveRight(){
		assert this.getMove();
		this.setHorizontalVelocity(0);
		this.setMove(false);
		this.setTimeLastRight(this.getTime());
	}

	/**
	 * This method gives you the time when Mazub last finished moving right.
	 */
	@Basic
	public double getTimeLastRight(){
		return this.timeLastRight;
	}

	/**
	 * Sets the time when Mazub last stops moving to the right.
	 * @param time
	 * 			The new time when Mazub last stopped moving to the right.
	 * @post	The new time when Mazub last stopped moving to the right is equal to the given time
	 * 			| new this.getTimeLastRight = time
	 */
	private void setTimeLastRight(double time){
		this.timeLastRight = time;
	}

	/**
	 * Variable registering when Mazub stops moving to the right. 
	 * This variable is initialized to -2 because Mazub has not yet moved to the right.
	 */
	private double timeLastRight=-2;

	/**
	 * This method gives you the current state of the boolean variable move.
	 */
	public boolean getMove(){
		return this.move;
	}

	/**
	 * Sets the boolean that registers if Mazub is moving.
	 * @param flag
	 * 			The new state.
	 * @post	The new state variable that registers if Mazub is moving.
	 * 			| new this.getMove() = flag
	 */
	private void setMove(boolean flag){
		this.move = flag;
	}

	/**
	 * Variable registering if Mazub is moving (true) or is not moving (false).
	 */
	private boolean move;

	/**
	 * This method gives you the current horizontal velocity of Mazub.
	 */
	@Basic
	public double getHorizontalVelocity(){
		return this.horizontalVelocity;
	}

	/**
	 * Set the horizontal velocity of Mazub to a given velocity.
	 * @param velocity
	 * 			The new horizontal velocity.
	 * @post	If the given velocity is bigger than the maximum horizontal velocity,
	 * 			the new velocity is equal to the maximum velocity.
	 * 			| if(velocity > this.getMaximumHorizontalVelocity())
	 * 			| new.getHorizontalVelocity() = this.getMaximumHorizontalVelocity()
	 * @post	if the given velocity is smaller than minus the maximum horizontal velocity,
	 * 			the new velocity is equal to minus the maximum horizontal velocity.
	 * 			| if(velocity < - this.getMaximumHorizontalVelocity()) 
	 * 			| new.getHorizontalVelocity() = -this.getMaximumHorizontalVelocity()
	 * @post	if the given velocity is between minus the maximum velocity, and the
	 * 			maximum velocity, the new velocity is equal to the given velocity.
	 * 			| if ((velocity < -this.getMaximumHorizontalVelocity())&&
	 * 			| 		(velocity> this.getMaximumHorizontalVelocity()))
	 * 			| new.getHorizontalVelocity() = velocity
	 */
	private void setHorizontalVelocity(double velocity){
		if(velocity > this.getMaximumHorizontalVelocity()) 
			this.horizontalVelocity = this.getMaximumHorizontalVelocity();
		if(velocity < -(this.getMaximumHorizontalVelocity())) 
			this.horizontalVelocity = -(this.getMaximumHorizontalVelocity());
		else
			this.horizontalVelocity = velocity;
	}

	/**
	 * Gives the initial horizontal velocity of Mazub.
	 * @return 	The initial value for the horizontal velocity of Mazub 
	 * 		   	is never below 1 m/s.
	 *       	| result >= 1 m/s
	 */
	@Basic @Immutable
	public double getInitialHorizontalVelocity(){
		return this.INITIAL_HORIZONTAL_VELOCITY;
	}

	/**
	 * This variable registers the initial horizontal velocity.
	 */
	private final double INITIAL_HORIZONTAL_VELOCITY=1;

	/**
	 * Return the maximal value for the horizontal velocity of Mazub.
	 * @return 	The maximal value for the horizontal velocity of Mazub 
	 * 		   	is bigger than or equal to the initial horizontal velocity.
	 *       	| result >= this.getInitialHorizontalVelocity.
	 */
	@Basic 
	public double getMaximumHorizontalVelocity(){
		return maximumHorizontalVelocity;	
	}

	/**
	 * Set the maximum horizontal velocity of Mazub.
	 * @param 	velocity
	 * 			The new maximum horizontal velocity.
	 * @post 	If the given velocity is bigger than the initial horizontal velocity,
	 * 			then the maximum horizontal velocity is set to the given value.
	 * 			| if(velocity > this.getInitialHorizontalVelocity)
	 * 			| 	new this.getMaximumHorizontalVelocity = velocity
	 * @post 	If the given velocity is smaller than the initial horizontal velocity,
	 * 			then the maximum horizontal velocity is set to the initial horizontal velocity.
	 * 	 		| if(velocity <= this.getInitialHorizontalVelocity)
	 * 			| 	new this.getMaximumHorizontalVelocity = this.getInitialHorizontalVelocity
	 */
	private void setMaximumHorizontalVelocity(double velocity){
		if(velocity <= this.getInitialHorizontalVelocity())
			this.maximumHorizontalVelocity = this.getInitialHorizontalVelocity();
		else this.maximumHorizontalVelocity = velocity;
	}

	/**
	 * Variable registering the maximum horizontal velocity.
	 */
	private double maximumHorizontalVelocity = getInitialMaximalHorizontalVelocity();
	
	/**
	 * Gives the initial maximal horizontal velocity of Mazub.
	 * @return 	The initial value for the horizontal velocity of Mazub 
	 * 		   	is never below the initial horizontal velocity.
	 *       	| result >= this.getInitialHorizontalVelocity()
	 */
	@Basic
	public double getInitialMaximalHorizontalVelocity(){
		return INITIAL_MAXIMAL_HORIZONTAL_VELOCITY;
	}
	
	/**
	 * Variable that registers the initial maximal horizontal velocity.
	 */
	private final double INITIAL_MAXIMAL_HORIZONTAL_VELOCITY = 3;
	

	/**
	 * A method that checks if the current horizontal velocity is smaller than the maximum
	 * horizontal velocity of Mazub.
	 * @param 	velocity
	 * 			The velocity to check.
	 * @return	True if and only if the absolute value of the velocity is smaller than the 
	 * 			maximum horizontal velocity.
	 * 			| result == (Math.abs(velocity) <= this.getMaximumHorizontalVelocity())
	 */
	public boolean isValidHorizontalVelocity(double velocity){
		return(Math.abs(velocity) <= this.getMaximumHorizontalVelocity());
	}

	/**
	 * Variable registering the horizontal velocity.
	 */
	private double horizontalVelocity;

	/**
	 * Return the value for the horizontal acceleration of Mazub.
	 * 
	 * @return 	the value for the horizontal acceleration of Mazub 
	 * 		   	is never below zero.
	 *       	| result >= 0
	 */
	@Basic @Immutable
	public double getHorizontalAccelaration(){
		return HORIZONTAL_ACCELERATION;
	}

	/**
	 * Variable registering the horizontal acceleration.
	 */
	private static final double HORIZONTAL_ACCELERATION = 0.9;

	/**
	 * Mazub starts jumping.
	 * @effect	The variable that registers if Mazub is jumping is set to true.
	 * 			| setJump(true)
	 * @effect 	Mazub starts moving upwards with a specific vertical velocity.
	 * 		 	| setVerticalVelocity(this.getInitialVerticalVelocity())
	 * @throws	IllegalStateException
	 * 			Mazub can not jump while jumping, nor jump while ducking.
	 * 			| this.getJump()||this.getDuck()
	 */
	public void startJump()throws IllegalStateException{
		if(this.getJump() || this.getDuck())
			throw new IllegalStateException();
		this.setJump(true);
		setVerticalVelocity(this.getInitialVerticalVelocity());
	}

	/**
	 * Mazub stops jumping.
	 * This methode prevents Mazub to stop jumping, when he is falling.
	 * @effect 	Mazub's vertical velocity equals 0 m/s.
	 * 			| setVerticalVelocity(0)
	 * @throws 	IllegalStateException
	 * 			Vertical velocity can not be smaller then zero when Mazub ends jumping.
	 * 			| this.getVerticalVelocity() < 0
	 * 			
	 */
	public void endJump()throws IllegalStateException{
		if(this.getVerticalVelocity() < 0)
			throw new IllegalStateException();
		setVerticalVelocity(0);
	}

	/**
	 * This method gives you the current state of the boolean variable jump.
	 */
	@Basic
	public boolean getJump(){
		return this.jump;
	}

	/**
	 * Sets the boolean that registers if Mazub is jumping.
	 * @param flag
	 * 			The new state.
	 * @post	The new state variable that registers if Mazub is jumping.
	 * 			| new this.getJump() = flag
	 */
	private void setJump(boolean flag){
		this.jump = flag;
	}

	/**
	 * Variable registering if Mazub is jumping (true) or is not jumping (false).
	 */
	private boolean jump;

	/**
	 * This method gives you the current vertical velocity of Mazub.
	 */
	@Basic
	public double getVerticalVelocity(){
		return this.verticalVelocity;
	}

	/**
	 * Set the Vertical velocity of Mazub to a given velocity.
	 * @param velocity
	 * 			The new vertical velocity.
	 * @post	The new velocity is equal to the given velocity.
	 * 			| new.getVerticalVelocity() = velocity
	 */
	private void setVerticalVelocity(double velocity){
		this.verticalVelocity = velocity;
	}

	/**
	 * Variable registering the vertical velocity.
	 */
	private double verticalVelocity;

	/**
	 * Gives the initial vertical velocity of Mazub.
	 * @return The initial value for the vertical velocity of Mazub 
	 * 		   is equal to 8 m/s.
	 *         | result = 8
	 */
	@Basic @Immutable
	public double getInitialVerticalVelocity(){
		return INITIAL_VERTICAL_VELOCITY;
	}

	/**
	 * Variable registering the initial vertical velocity.
	 */
	private static final double INITIAL_VERTICAL_VELOCITY = 8;

	/**
	 * Gives the vertical acceleration of Mazub.
	 * @return The value for the vertical acceleration of Mazub is equal to -10 m/s^2.
	 *         | result = -10
	 */
	@Basic @Immutable
	public double getVerticalAcceleration(){
		return VERTICAL_ACCELERATION;
	}

	/**
	 * Variable registering the vertical acceleration.
	 */
	public static final double VERTICAL_ACCELERATION = -10;

	/**
	 * Mazub starts ducking.
	 * @effect 	Mazub moves at a maximum velocity of 1m/s.
	 * 			|setMaximumHorizontalVelocity(1)
	 * @effect	The variable that registers if Mazub is ducking is set to true.
	 * 			|setDuck(true)
	 * @throws	IllegalStateException
	 * 			Mazub can not start ducking when he's jumping.
	 * 			|this.getJump() == true
	 */
	public void startDuck()throws IllegalStateException{
		if(this.getJump() == true)
			throw new IllegalStateException();
		this.setMaximumHorizontalVelocity(1);
		this.setDuck(true);
	}

	/**
	 * Mazubs stops ducking.
	 * @effect 	The maximum horizontal velocity of Mazub is set to the initial maximum horizontal velocity.
	 * 			|setMaximumHorizontalVelocity(getInitialMaximalHorizontalVelocity())
	 * @effect	The variable that registers if Mazub is ducking is set to false.
	 * 			|setDuck(false)
	 * @throws 	IllegalStateException
	 * 			Mazub can't stop ducking when he's not ducking.
	 * 			| this.getDuck() == false
	 */
	public void endDuck() throws IllegalStateException{
		if(this.getDuck() == false)
			throw new IllegalStateException();
		this.setMaximumHorizontalVelocity(getInitialMaximalHorizontalVelocity());
		this.setDuck(false);
	}

	/**
	 * This method gives you the current state of the boolean variable duck.
	 */
	@Basic
	public boolean getDuck(){
		return this.duck;
	}

	/**
	 * Sets the boolean that registers if Mazub is ducking.
	 * @param flag
	 * 			The new state.
	 * @post	The new state variable that registers if Mazub is ducking.
	 * 			| new this.getDuck() = flag
	 */
	private void setDuck(boolean flag){
		this.duck = flag;
	}

	/**
	 * Variable registering if Mazub is ducking (true) or is not ducking (false).
	 */
	private boolean duck;

	/**
	 * This method returns the current sprite of Mazub.
	 * @pre	 Mazub can not jump and duck at the same time. 
	 * @post if Mazub is not moving horizontally, has not moved horizontally within the last
	 * 		 second of in-game-time and is not ducking, then images_0 is displayed.
	 * @post if Mazub is not moving horizontally, has not moved horizontally within the last
	 * 		 second of in-game-time and is ducking, then images_1 is displayed.
	 * @post if Mazub is not moving horizontally but its last horizontal movement was to the right (within 1s) and is not ducking, 
	 * 		 then images_2 is displayed.
	 * @post if Mazub is not moving horizontally but its last horizontal movement was to the left (within 1s) and is not ducking, 
	 * 		 then images_3 is displayed.
	 * @post if Mazub is moving to the right and is jumping and not ducking, 
	 * 		 then images_4 is displayed.
	 * @post if Mazub is moving to the left and is jumping and not ducking, 
	 * 		 then images_5 is displayed.
	 * @post if Mazub is moving to the right or was moving to the right (within 1s) and is ducking, 
	 * 		 then images_6 is displayed.
	 * @post if Mazub is moving to the left or was moving to the left (within 1s) and is ducking, 
	 * 		 then images_7 is displayed.
	 * @post if Mazub is moving to the right or was moving to the right (within 1s) and is not ducking nor jumping, 
	 * 		 then images_8 until 8+ M are displayed.
	 * @post if Mazub is moving to the left or was moving to the left (within 1s)and is not ducking nor jumping, 
	 * 		 then images_9+M until 9+2*M are displayed.
	 * @return
	 */
	public Sprite getCurrentSprite(){
		assert (this.getJump()==false)||(this.getDuck()==false);
		if((this.getMove() == false)&&(this.getTime() > this.getTimeLastLeft()+1)&&
				(this.getTime() <= this.getTimeLastRight()+1)&&(this.getDuck() == false))
			return this.sprites[2];
		else if((this.getMove() == false)&&(this.getTime() <= this.getTimeLastLeft()+1)&&
				(this.getTime() > this.getTimeLastRight()+1)&&(this.getDuck() == false))
			return this.sprites[3];
		else if((this.getMove() == true)&&(this.getHorizontalVelocity() > 0)&&
				(this.getDuck() == false)&&(this.getJump() == true))
			return this.sprites[4];
		else if((this.getMove() == true)&&(this.getHorizontalVelocity() < 0)&&
				(this.getDuck() == false)&&(this.getJump() == true))
			return this.sprites[5];
		else if((((this.getMove() == true)&&(this.getHorizontalVelocity() > 0))||
				((this.getMove() == false)&&(this.getTime() <= this.getTimeLastRight()+1)))&&
				(this.getDuck() == true))
			return this.sprites[6];
		else if((((this.getMove() == true)&&(this.getHorizontalVelocity() < 0))||
				((this.getMove() == false)&&(this.getTime() <= this.getTimeLastLeft()+1)))&&
				(this.getDuck() == true))
			return this.sprites[7];
		else if((this.getMove() == true)&&(this.getHorizontalVelocity() > 0)&&
				(this.getDuck() == false)&&(this.getJump() == false))
			return this.sprites[8 + (int)(((this.getTime()-this.getTimeStartRight())/0.075)%(this.getM()+1))];
		else if((this.getMove() == true)&&(this.getHorizontalVelocity() < 0)&&
				(this.getDuck() == false)&&(this.getJump() == false))
			return this.sprites[9 + this.getM() + 
			                    (int)(((this.getTime()-this.getTimeStartLeft())/0.075)%(this.getM()+1))];
		else if((this.getMove() == false)&&(this.getDuck() == true)&&(this.getJump() == false))
			return this.sprites[1];
		return this.sprites[0];
	}

	/**
	 * This method gives you the number of images to display a walking Mazub.
	 */
	public int getM(){
		return this.M;
	}

	/**
	 * Variable that determines the number of images to display a walking Mazub.
	 */
	private final int M = 10;

	/**
	 * Array of all the images to display Mazub
	 */
	private Sprite[] sprites;

	/**
	 * This method gives the width of the current sprite of Mazub.
	 */
	@Basic
	public int getSizeX(){
		return this.getCurrentSprite().getWidth();
	}

	/**
	 * This method gives the height of the current sprite of Mazub.
	 */
	@Basic
	public int getSizeY() {
		return this.getCurrentSprite().getHeight();
	}

	/**
	 * Returns the x-coordinate of the position of Mazub.
	 */
	@Basic
	public int getPositionX(){
		return this.positionX;
	}

	/**
	 * Set the x-coordinate of the position of Mazub. 
	 *
	 * @param	position
	 *			The new x-position of Mazub.
	 * @post    If the given position is in the range established by the minimum
	 *          and maximum position of Mazub, the x-coordinate of mazub is equal to position. 
	 *        	| if ( (position >= this.getMinPositionX()) && (position <= this.getMaxPositionX()) )
	 *        	| 	new this.getPositionX() == position
	 * @post    If the given position is in the range established by the minus of the
	 *          maximum position and the minimum position of Mazub, the x-coordinate of mazub is equal to position
	 *          plus the maximum position + 1. 
	 *        	| if((position >= -this.getMaxPositionX()-1)&&(position < this.getMinPositionX()))
	 * 			| position += this.getMaxPositionX()+1;
	 * @post    If the given position is in the range established by the maximum position
	 *          and two times the maximum position + 1 of Mazub, the x-coordinate of mazub is equal to position
	 *          minus the maximum position + 1.
	 *        	| if((position > this.getMaxPositionX())&&(position <= 2*this.getMaxPositionX()+1))
	 * 			| position -= this.getMaxPositionX()+1;
	 * @throws  IllegalArgumentException
	 * 			The position exceeds 2 times the maximum position plus 1 or is smaller than 
	 * 			minus the maximum position minus 1.
	 * 			| ( position > 2*this.getMaxPositionX() +1) || (position < -this.getMaxPositionX() -1) 			
	 */	
	private void setPositionX(int position) throws IllegalArgumentException{
		if(!isValidPositionX(position)){
			if((position >= -this.getMaxPositionX()-1)&&(position < this.getMinPositionX()))
				position += this.getMaxPositionX()+1;
			else if((position >this.getMaxPositionX())&&(position <= 2*this.getMaxPositionX()+1))
				position -= this.getMaxPositionX()+1;
			else 
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
	 * Variable that registers the x-coordinate of Mazub.
	 */
	private int positionX;

	/**
	 * This method gives you the minimal x-position of Mazub.
	 */
	public int getMinPositionX(){
		return MIN_POSITIONX;
	}

	/**
	 * Variable that registers the minimal x-position of Mazub.
	 */
	private static final int MIN_POSITIONX = 0;

	/**
	 * This method gives you the maximal x-position of Mazub.
	 */
	public int getMaxPositionX(){
		return MAX_POSITIONX;
	}
	/**
	 * Variable that registers the maximal x-position of Mazub.
	 */
	private static final int MAX_POSITIONX = 1023;

	/**
	 * Returns the y-coordinate of the position of Mazub.
	 */
	@Basic
	public int getPositionY(){
		return this.positionY;
	}

	/**
	 * Set the y-coordinate of the position of Mazub. 
	 *
	 * @param	position
	 *			The new y-position of Mazub.
	 * @post    If the given position is in the range established by the minimum and maximum position of Mazub, 
	 *          the y-coordinate of mazub is equal to the given position. 
	 *        	| if ( (position >= this.getMinPositionY()) && (position <= this.getMaxPositionY()) )
	 *        	|   then new.getPositionY() = position
	 * @post    If the given position exceeds the the maximum position of Mazub the new 
	 * 			y-coordinate of Mazub is equal to the maximum position of Mazub.
	 *        	| if (position > this.getMaxPositionY())
	 *        	|  		new.getPositionY() = this.getMaxPositionY()
	 * @post	If the given postion is smaller than the minimum position of Mazub, the
	 * 			new y-coordinate of Mazub is equal to the minimum position of Mazub.
	 * 			| if (position< this.getMinPositionY())
	 * 			| 		new.getPositionY() = this.getMinPositionY()
	 */
	private void setPositionY(int position){
		if(!isValidPositionY(position)){
			if(position < this.getMinPositionY())
				position = this.getMinPositionY();
			else position = this.getMaxPositionY();
		}
		this.positionY = position;
	}

	/**
	 * A method that checks if the position of Y is a valid position.
	 * @param 	position
	 * 			The position to check.
	 * @return	True if and only if the value of the position is larger than the minimum value for the y-position and is smaller than the 
	 * 			maximum value for the y-position.
	 * 			| result = ((position>=this.getMinPositionY())&&(position<=this.getMaxPositionY()))
	 */
	public boolean isValidPositionY(int position){
		return ((position>=this.getMinPositionY())&&(position<=this.getMaxPositionY()));
	}

	/**
	 * Variable registering the y-coordinate of Mazub.
	 */
	private int positionY;

	/**
	 * This method gives you the minimal y-position of Mazub.
	 */
	public int getMinPositionY(){
		return MIN_POSITIONY;
	}

	/**
	 * Variable registering the minimal y-coordinate of Mazub.
	 */
	private static final int MIN_POSITIONY = 0;

	/**
	 * This method gives you the maximal y-position of Mazub.
	 */
	public int getMaxPositionY(){
		return MAX_POSITIONY;
	}

	/**
	 * Variable registering the maximal y-coordinate of Mazub.
	 */
	private static final int MAX_POSITIONY = 767;

	/**
	 * A method that checks if the time duration is valid.
	 * @param 	deltaT
	 * 			a time duration in seconds.
	 * @return 	True if deltaT is bigger than zero and smaller than 0.2.
	 * 			result == ((deltaT >= 0) && (deltaT<= 0.2))
	 */
	public boolean isValidTime(double deltaT){
		return((deltaT >=0)&&(deltaT <= 0.2));
	}

	/**
	 * This method updates the position and velocity of Mazub in both directions.
	 * @param horizontalVelocity
	 * 					The current horizontal velocity of Mazub.
	 * @param verticalVelocity
	 * 					The current vertical velocity of Mazub.
	 * @param deltaT
	 * 					The time duration in seconds.
	 * @effect	The variable time of the class Mazub gets updated.
	 * 			| this.addTime(deltaT)
	 * @effect	The current sprite of Mazub gets updated.
	 * 			|this.getCurrentSprite()
	 * @effect 	if Mazub is moving horizontally, the position and velocity get updated in the horizontal direction.
	 * 			|if(this.move == true)
	 * 			|	setPositionX((int)(this.getPositionX() + distanceTraveledHorizontal(horizontalVelocity, deltaT)))
	 * 			|	setHorizontalVelocity(advancedHorizontalVelocity(horizontalVelocity,deltaT))
	 * @effect 	if Mazub is moving vertically, the position and velocity get updated in the vertical direction.
	 * 			|if(this.jump == true)
	 * 			|	setPositionY((int)(this.getPositionY() + distanceTraveledVertical(verticalVelocity, deltaT)))
	 * 			|	setVerticalVelocity(advancedVerticalVelocity(verticalVelocity, deltaT))
	 * @post	If Mazub is moving horizontally and if Mazub is located at the bottom of the screen, Mazub stops falling.
	 * 			| if((this.getPositionY() == 0)&&(this.jump==true)) this.jump = false
	 * @throws 	IllegalArgumentException
	 * 			If the given time does not equal a valid value.
	 * 			|! isValidTime(deltaT)
	 */
	public void advanceTime( double horizontalVelocity, double verticalVelocity, double deltaT) 
			throws IllegalArgumentException{
		if (! isValidTime(deltaT))
			throw new IllegalArgumentException();
		if(!isValidHorizontalVelocity(horizontalVelocity)){
			if(horizontalVelocity< -this.getMaximumHorizontalVelocity())
				horizontalVelocity=-this.getMaximumHorizontalVelocity();
			else horizontalVelocity = this.getMaximumHorizontalVelocity();
		}
		this.addTime(deltaT);
		this.getCurrentSprite();
		if(this.getMove() == true){
			setPositionX((int)(this.getPositionX() + distanceTraveledHorizontal(horizontalVelocity, deltaT)));
			setHorizontalVelocity(advancedHorizontalVelocity(horizontalVelocity,deltaT));
		}
		if(this.getJump() == true){
			setPositionY((int)(this.getPositionY() + distanceTraveledVertical(verticalVelocity, deltaT)));
			setVerticalVelocity(advancedVerticalVelocity(verticalVelocity, deltaT));
			if(this.getPositionY() == 0)
				this.setJump(false);
		}

	}

	/**
	 * This method calculates the distance travelled vertically based on the given velocity and over a certain time interval.
	 * @param velocity
	 * 			The current vertical velocity of Mazub
	 * @param deltaT
	 * 			The time interval in seconds.
	 * @return  if the absolute value of the given velocity equals the maximum horizontal velocity,
	 * 			then the result is smaller than the velocity times deltaT times 100. We multiply by 100
	 * 			because position is in centimeters, but velocity is in meter per second.
	 * 			| if(Math.abs(velocity) == this.getMaximumHorizontalVelocity())
	 * 			| result <= (velocity *deltaT)*100
	 * @return  if the given velocity is bigger than zero,
	 * 			then the result is smaller than the velocity times deltaT times 100 
	 * 			plus 0.5 times the horizontal accelaration times deltaT squared times 100. We multiply by 100
	 * 			 because position is in centimeters, but velocity is in meter per second.
	 * 			| if(velocity > 0)
	 * 			| result <= (velocity * deltaT + 0.5 * this.getHorizontalAccelaration()*deltaT*deltaT)*100
	 * @return	if the given velocity is smaller than zero,
	 * 			then the result is smaller than the velocity times deltaT times 100 
	 * 			minus 0.5 times the horizontal accelaration times deltaT squared times 100. We multiply by 100
	 * 			because position is in centimeters, but velocity is in meter per second.
	 * 			| if(velocity <= 0)
	 * 			| result <= (velocity * deltaT - 0.5 * this.getHorizontalAccelaration()*deltaT*deltaT)*100
	 */
	private double distanceTraveledHorizontal(double velocity, double deltaT){
		if (Math.abs(velocity) == this.getMaximumHorizontalVelocity())
			return (velocity *deltaT)*100;
		if(velocity>0)
			return (velocity * deltaT + 0.5 * this.getHorizontalAccelaration()*deltaT*deltaT)*100;
		return (velocity * deltaT - 0.5 * this.getHorizontalAccelaration()*deltaT*deltaT)*100;
	}

	/**
	 * This method calculates the distance travelled vertically based on the given velocity and over a certain time interval.
	 * @param velocity
	 * 			The current vertical velocity of Mazub
	 * @param deltaT
	 * 			The time interval in seconds.
	 * @return  The result is smaller than the velocity times deltaT times 100 
	 * 			plus 0.5 times the vertical accelaration times deltaT squared times 100. We multiply by 100
	 * 			because position is in centimeters, but velocity is in meter per second.
	 * 			| result <= (velocity * deltaT + 0.5 * this.VERTICAL_ACCELARATION*deltaT*deltaT)*100
	 */
	private double distanceTraveledVertical(double velocity, double deltaT){
		return (velocity * deltaT + 0.5 * (this.getVerticalAcceleration())*deltaT*deltaT)*100;
	}

	/**
	 * This method returns the new horizontal velocity after a certain time duration.
	 * @param velocity
	 * 			The current horizontal velocity of Mazub (the velocity before this method is invoked) in m/s.
	 * @param deltaT
	 * 			The time duration in seconds
	 * @return  If the given velocity is bigger than zero, then the result is smaller than the velocity plus 
	 * 			the horizontal accelaration times deltaT.
	 * 			| if(velocity > 0)
	 * 			| result <= velocity + this.getHorizontalAccelaration()*deltaT
	 * @return  If the given velocity is smaller than zero, then the result is smaller than the velocity minus
	 * 			the horizontal accelaration times deltaT.
	 * 			| if(velocity <= 0)
	 * 			| result <= velocity - this.getHorizontalAccelaration()*deltaT
	 */
	private double advancedHorizontalVelocity(double velocity, double deltaT){
		if(velocity > 0)
			return velocity + this.getHorizontalAccelaration()*deltaT;
		return velocity - this.getHorizontalAccelaration()*deltaT;
	}

	/**
	 * This method returns the new vertical velocity after a certain time duration.
	 * @param velocity
	 * 			The current vertical velocity of Mazub (the velocity before this method is invoked) in m/s.
	 * @param deltaT
	 * 			The time duration in seconds
	 * @return  The result is smaller than the velocity plus
	 * 			the vertical accelaration times deltaT.
	 * 			| result <= velocity + (this.getVerticalAcceleration())*deltaT
	 */
	private double advancedVerticalVelocity(double velocity, double deltaT){
		return velocity + (this.getVerticalAcceleration())*deltaT;
	}
	
	/**
	 * This method adds the given time to the variable this.time
	 * @param time
	 * 			the given time that needs to be added.
	 * @post	The variable that registers the current game time is updated with the given time.
	 * 			| new this.time = this.time + time
	 */
	private void addTime(double time){
		this.setTime(this.getTime() + time);
	}

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
	private void setTime(double time){
		this.time = time;
	}

	/**
	 * A variable that keeps track of the time that has passed.
	 */
	private double time;	
}
