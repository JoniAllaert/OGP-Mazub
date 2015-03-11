package jumpingalien.model;
import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;


/**
 * A class for dealing with the aliens called Mazub.
 *    In the current version Mazub does not move LOL.
 * 
 * @version  1.0
 * @author   Anne Wijffels
 * @author   Joni Allaert
 */
//TODO invars + @raw
//TODO privates
//TODO duckenn jumpen en lopen. links niet rechts wel juist
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
	 * @pre		Mazub is not moving horizontally.		
	 * @post 	Mazub starts accelerating with a specific horizontal acceleration to the left.
	 * 		 	| new this.getHorizontalAccelaration = -this.getHorizontalAccelaration()
	 * @effect 	Mazub starts moving with an initial horizontal velocity to the left (negative).
	 * 		 	| setHorzontalAcceleration( - this.getInitialHorizontalVelocity())
	 * 
	 */
	public void startMoveLeft(){
		assert !this.move;
		this.setHorizontalVelocity(-this.getInitialHorizontalVelocity());
		this.horizontalAccelaration = -this.getHorizontalAccelaration();
		this.move = true;
		this.timeStartLeft = this.time;
	}
	/**
	 * Variable registering the time when Mazub starts moving to the left.
	 */
	private double timeStartLeft;

	/**
	 * Mazub starts moving horizontally to the right.
	 * @pre		Mazub is not moving horizontally.	
	 * @post 	Mazub starts accelerating with a specific horizontal acceleration to the right.
	 * 		 	| new this.getHorizontalAccelaration = this.getHorizontalAccelaration()
	 * @effect	Mazub starts moving with an initial horizontal velocity to the right (positive).
	 * 		 	| setHorzontalAcceleration(this.getInitialHorizontalVelocity())
	 * 
	 */
	public void startMoveRight(){
		assert !this.move;
		this.setHorizontalVelocity(this.getInitialHorizontalVelocity());
		this.horizontalAccelaration = this.getHorizontalAccelaration();
		this.move = true;
		this.timeStartRight = this.time;
	}

	/**
	 * Variable registering the time when Mazub starts moving to the right. 
	 * Initialized to -2 because initially the last time Mazub moved to the right was never.
	 */
	private double timeStartRight;

	/**
	 * Mazub stops moving to the left.
	 * @pre		Mazub is moving horizontally.
	 * @effect 	Mazub's horizontal velocity equals 0 m/s.
	 * 			| setHorizontalVelocity(0)
	 */
	public void endMoveLeft(){
		assert this.move;
		this.setHorizontalVelocity(0);
		this.move = false;
		this.timeLastLeft = this.time;
	}

	/**
	 * Variable registering when Mazub stops moving to the left. 
	 * Initialized to -2 because initially the last time Mazub moved to the left was never.
	 */
	private double timeLastLeft=-2;

	/**
	 * Mazub stops moving to the right.
	 * @pre		Mazub is moving horizontally.
	 * @effect  Mazub's horizontal velocity equals 0 m/s.
	 * 			| setHorizontalVelocity(0)
	 */
	public void endMoveRight(){
		assert this.move;
		this.setHorizontalVelocity(0);
		this.move = false;
		this.timeLastRight = this.time;
	}

	/**
	 * Variable registering when Mazub stops moving to the right. 
	 */
	private double timeLastRight=-2;

	/**
	 * Variable registering if Mazub is moving (true) or is not moving (false).
	 */
	private boolean move;

	/**
	 * 
	 * This method gives you the current horizontal velocity of Mazub.
	 * 
	 */
	@Basic
	public double getHorizontalVelocity(){
		return this.horizontalVelocity;
	}
	//TODO Private because we do not want others to be able to change the horizontal velocity.
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

	//TODO moeten we hier geen set bij maken, want er staat dat er verschillende Mazubs kunnen
	// zijn met een verschillende initiele snelheden? Of is dit gewoon voor als je de klasse zou aanpassen?
	// ik denk ook dat deze methode een statische methode(methode die voor elke object van de klasse hetzelfde is) moet zijn. 
	// JONI: Misschien heb je daar wel gelijk da zullen we samen is bekijken.

	/**
	 * Gives the initial horizontal velocity of Mazub.
	 * 
	 * @return The initial value for the horizontal velocity of Mazub 
	 * 		   is never below 1 m/s.
	 *       | result >= 1 m/s
	 * @note   because the documentation has not revealed the actual value, it is
	 *         possible to change these values without having to notify
	 *         any clients of the class. At that time, we might have to
	 *         add new constructors by means of which we can initialize
	 *         new Mazubs and this does not break the contract with the
	 *         clients.
	 */
	@Basic @Immutable
	public static double getInitialHorizontalVelocity(){
		return 1;
	}

	/**
	 * Return the maximal value for the horizontal velocity of Mazub.
	 * 
	 * @return 	The maximal value for the horizontal velocity of Mazub 
	 * 		   	is bigger than or equal to the initial horizontal velocity.
	 *       	| result >= this.getInitialHorizontalVelocity.
	 * @note   because the documentation has not revealed the actual value, it is
	 *         possible to change these values without having to notify
	 *         any clients of the class. At that time, we might have to
	 *         add new constructors by means of which we can initialize
	 *         new Mazubs and this does not break the contract with the
	 *         clients.
	 */
	@Basic 
	public double getMaximumHorizontalVelocity(){
		return maximumHorizontalVelocity;	
	}
	//TODO Private because we do not want others to be able to change the maximum horizontal velocity.
	/**
	 * Set the maximum horizontal velocity of Mazub.
	 * @param 	velocity
	 * 			The new maximum horizontal velocity.
	 * @post 	If the given velocity is bigger than the initial horizontal velocity,
	 * 			then the maximum horizontal velocity is set to the given value
	 * 			| if(velocity > this.getInitialHorizontalVelocity)
	 * 			| 	new this.getMaximumHorizontalVelocity = velocity
	 * @post 	If the given velocity is smaller than the initial horzontal velocity,
	 * 			then the maximum horizontal velocity is set to the initial horozontal velocity.
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
	private double maximumHorizontalVelocity = 3;

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
	private double horizontalVelocity=0;

	//TODO ik denk dat dit een statiche methode moet zijn. Joni: ik denk dat ge gelijk hebt of final? die assistent had daar iets over gezegd.
	/**
	 * Return the value for the horizontal acceleration of Mazub.
	 * 
	 * @return 	the value for the horizontal acceleration of Mazub 
	 * 		   	is never below zero.
	 *       	| result >= 0
	 * @note   	because the documentation has not revealed the actual value, it is
	 *         	possible to change these values without having to notify
	 *         	any clients of the class. At that time, we might have to
	 *         	add new constructors by means of which we can initialize
	 *         	new Mazubs and this does not break the contract with the
	 *         	clients.
	 */
	@Basic @Immutable
	public static double getHorizontalAccelaration(){
		return 0.9;
	}

	/**
	 * Variable registering the horizontal acceleration.
	 */
	private double horizontalAccelaration=0;

	//TODO moet die extra voorwaarde een preconditie zijn? en Moet daar een formele versie van zijn.
	/**
	 * Mazub starts jumping.
	 * This methods prevents jumping when Mazub is not on the bottom of the screen.
	 * @post 	Mazub starts moving upwards (vertically) with a specific vertical velocity.
	 * 		 	| new this.getVerticalVelocity() = this.INITIAL_VERTICAL_VELOCITY
	 */
	public void startJump(){
		try{
			if(this.getPositionY() !=0)
				throw new IllegalStateException();
		}
		catch(IllegalStateException exc){
			return;
		}
		this.endDuck();
		this.jump = true;
		setVerticalVelocity(this.INITIAL_VERTICAL_VELOCITY);
	}

	/**
	 * Mazub stops jumping.
	 * This methode prevents Mazub to stop jumping, when he is falling.
	 * @effect 	Mazub's vertical velocity equals 0 m/s.
	 * 			| setVerticalVelocity(0)
	 */
	public void endJump(){
		try{
			if(this.getVerticalVelocity() <= 0)
				throw new IllegalStateException();
		}
		catch(IllegalStateException exc){
			return;
		}
		setVerticalVelocity(0);

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
	//TODO Private because we do not want others to be able to change the vertical velocity.
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
	private double verticalVelocity=0;
	/**
	 * Variable registering the initial vertical velocity.
	 */
	private static final double INITIAL_VERTICAL_VELOCITY=8;
	/**
	 * Variable registering the vertical accelaration.
	 */
	public static final double VERTICAL_ACCELARATION=-10;

	/**
	 * Mazub starts ducking to decrease his size.
	 * This method prevents Mazub to duck, when he is jumping.
	 * @effect 	Mazub moves at a maximum velocity of 1m/s.
	 * 			|setMaximumHorizontalVelocity(1)
	 */
	public void startDuck(){
		try{
			if(this.jump == true)
				throw new IllegalStateException();
		}
		catch(IllegalStateException exc){
			return;
		}
		this.setMaximumHorizontalVelocity(1);
		this.duck = true;
	}
	/**
	 * Mazubs stops ducking and increases his size to the normal size
	 * This method prevents that Mazub stops ducking, when he is not ducking.
	 * @effect 	Mazub moves at a maximum velocity of 3m/s.
	 * 			|setMaximumHorizontalVelocity(3)
	 */
	public void endDuck() throws IllegalStateException{
		try {
			if(this.duck == false)
				throw new IllegalStateException();
		}
		catch(IllegalStateException exc){
			return;
		}
		this.setMaximumHorizontalVelocity(3);
		this.duck = false;
	}
	
	public boolean getDuck(){
		return duck;
	}
	/**
	 * Variable registering if Mazub is ducking (true) or is not ducking (false).
	 */
	private boolean duck;

	//TODO Joni: Hoe nomniaal maken? array sprites mag niet  null zijn (misschien eerder invariant)
	/**
	 * This method returns the current sprite of Mazub.
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
	 * 		 then images_8 until 8+this.M are displayed.
	 * @post if Mazub is moving to the left or was moving to the left (within 1s)and is not ducking nor jumping, 
	 * 		 then images_9+this.M until 9+2*this.M are displayed.
	 * @return
	 */
	public Sprite getCurrentSprite(){


		if((this.move == false)&&(this.time > this.timeLastLeft+1)&&(this.time <= this.timeLastRight+1)&&(this.duck == false))
			return this.sprites[2];
		else if((this.move == false)&&(this.time <= this.timeLastLeft+1)&&(this.time > this.timeLastRight+1)&&(this.duck == false))
			return this.sprites[3];
		else if((this.move == true)&&(this.getHorizontalVelocity() > 0)&&(this.duck == false)&&(this.jump == true))
			return this.sprites[4];
		else if((this.move == true)&&(this.getHorizontalVelocity() < 0)&&(this.duck == false)&&(this.jump == true))
			return this.sprites[5];
		else if((((this.move == true)&&(this.getHorizontalVelocity() > 0))||((this.move == false)&&(this.time <= this.timeLastRight+1)))&&(this.duck == true))
			return this.sprites[6];
		else if((((this.move == true)&&(this.getHorizontalVelocity() < 0))||((this.move == false)&&(this.time <= this.timeLastLeft+1)))&&(this.duck == true))
			return this.sprites[7];
		else if((this.move == true)&&(this.getHorizontalVelocity() > 0)&&(this.duck == false)&&(this.jump == false))
			return this.sprites[8 + (int)(((this.time-this.timeStartRight)/0.075)%(this.M+1))];
		else if((this.move == true)&&(this.getHorizontalVelocity() < 0)&&(this.duck == false)&&(this.jump == false))
			return this.sprites[9 + this.M + (int)(((this.time-this.timeStartLeft)/0.075)%(this.M+1))];
		else if((this.move == false)&&(this.duck == true)&&(this.jump == false))
			return this.sprites[1];
		return this.sprites[0];
	}
	/**
	 * Variable that determines the number of images to display a walking Mazub.
	 */
	private final int M = 10;
	/**
	 * Array of all the images to display Mazub
	 */
	private Sprite[] sprites;


	/*TODO we moeten volgens mij nog een methode hebben om de grootte van Mazub op te vragen.
	 * Joni: Waar zouden we die gebruiken?
	 * zou ook defensief geprogrammeerd moeten worden.
	 * 
	 * public int sizeX(){
	 * 		this.getCurrentSprite().getWeight()
	 * }
	 * public int sizeY() {
	 * 		this.getCurrentSprite().getHeight()
	 * }
	 */

	/**
	 * Returns the x-coordinate of the position of Mazub.
	 */
	@Basic
	public int getPositionX(){
		return this.positionX;
	}
	//TODO Private because we do not want others to be able to change the x-coordinate of Mazub.
	/**
	 * Set the x-coordinate of the position of Mazub. 
	 *
	 * @param	position
	 *			The new x-position of Mazub.
	 * @post    If the given position is in the range established by the minimum
	 *          and maximum position of Mazub, the x-coordinate of mazub is equal to position. 
	 *        	| if ( (position >= MIN_POSITIONX) && (position <= MAX_POSITIONX) )
	 *        	| 	new this.getPositionX() == position
	 * @post    If the given position is in the range established by the minus of the
	 *          maximum position and the minimum position of Mazub, the x-coordinate of mazub is equal to position
	 *          plus the maximum position + 1. 
	 *        	| if((position >= -MAX_POSITIONX-1)&&(position < MIN_POSITIONX))
	 * 			| position += MAX_POSITIONX+1;
	 * @post    If the given position is in the range established by the maximum position
	 *          and two times the maximum position +1 of Mazub, the x-coordinate of mazub is equal to position
	 *          minus the maximum position + 1.
	 *        	| if((position > MAX_POSITIONX)&&(position <= 2*MAX_POSITIONX+1))
	 * 			| position -= MAX_POSITIONX+1;
	 * @throws  IllegalArgumentException
	 * 			The position exceeds 2 times  the maximum position plus 1 or is smaller than minus the maximum position minus 1.
	 * 			| ( position > 2*this.MAX_POSITIONX +1) || (position < -this.MAX_POSITIONX -1) 			
	 */	
	private void setPositionX(int position) throws IllegalArgumentException{
		if(!isValidPositionX(position)){
			if((position >= -MAX_POSITIONX-1)&&(position < MIN_POSITIONX))
				position += MAX_POSITIONX+1;
			else if((position > MAX_POSITIONX)&&(position <= 2*MAX_POSITIONX+1))
				position -= MAX_POSITIONX+1;
			else 
				throw new IllegalArgumentException();
		}
		this.positionX = position;
	}

	/**
	 * A method that checks if the position of X is a valid position.
	 * @param 	position
	 * 			The position to check.
	 * @return	True if and only if the value of the position is larger than the minimum value for the x-position and is smaller than the 
	 * 			maximum value for the x-position.
	 * 			| result == ((position>=MIN_POSITIONX)&&(position<=MAX_POSITIONX))
	 */
	public boolean isValidPositionX(int position){
		return ((position>=MIN_POSITIONX)&&(position<=MAX_POSITIONX));
	}

	/**
	 * Variable that registers the x-coordinate of Mazub.
	 */
	private int positionX;

	/**
	 * Variable that registers the minimal x-position of Mazub.
	 */
	private static final int MIN_POSITIONX = 0;

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
	//TODO Private because we do not want others to be able to change the y-coordinate of Mazub. NOG THIS. ZETTEN
	/**
	 * Set the y-coordinate of the position of Mazub. 
	 *
	 * @param	position
	 *			The new y-position of Mazub.
	 * @post    If the given position is in the range established by the minimum
	 *          and maximum position of Mazub, the y-coordinate of mazub is equal to 
	 *          the given position. 
	 *        	| if ( (position >= MIN_POSITIONY) && (position <= MAX_POSITIONY) )
	 *        	|   then new.getPositionY() = position
	 * @post    If the given position exceed the the maximum position of Mazub the new 
	 * 			y-coordinate of Mazub is equal to the maximum position of Mazub.
	 *        	| if (position > this.MAX_POSTIONY)
	 *        	|  		new.getPositionY() = this.MAX_POSITIONY
	 * @post	If the given postion is smaller than the minimum position of Mazub, the
	 * 			new y-coordinate of Mazub is equal to the minimum position of Mazub.
	 * 			| if (position< this.MIN_POSITIONY)
	 * 			| 		new.getPositionY() = this.MIN_POSITIONY
	 */
	private void setPositionY(int position){
		try{
			if(!isValidPositionY(position))
				throw new IllegalArgumentException();
		}
		catch(IllegalArgumentException exc){
			if(position < this.MIN_POSITIONY)
				position = this.MIN_POSITIONY;
			else position = this.MAX_POSITIONY;
		}
		this.positionY = position;
	}
	/**
	 * A method that checks if the position of Y is a valid position.
	 * @param 	position
	 * 			The position to check.
	 * @return	True if and only if the value of the position is larger than the minimum value for the y-position and is smaller than the 
	 * 			maximum value for the y-position.
	 * 			| result == ((position>=MIN_POSITIONY)&&(position<=MAX_POSITIONY))
	 */
	public boolean isValidPositionY(int position){
		return ((position>=this.MIN_POSITIONY)&&(position<=this.MAX_POSITIONY));
	}

	/**
	 * Variable registering the y-coordinate of Mazub.
	 */
	private int positionY;

	/**
	 * Variable registering the minimal y-coordinate of Mazub.
	 */
	private static final int MIN_POSITIONY = 0;


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
	//TODO documentatie catch vragen.
	/**
	 * This method updates the position and velocity of Mazub in both directions.
	 * If the given parameter horizontal velocity is smaller than minus the maximum horizontal velocity, 
	 * then this parameter is set to minus the maximum horizontal velocity.
	 * If the given parameter horizontal velocity is bigger than the maximum horizontal velocity, 
	 * then this parameter is set to the maximum horizontal velocity.
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
	 * 				setPositionX((int)(this.getPositionX() + distanceTraveledHorizontal(horizontalVelocity, deltaT)))
	 * 				setHorizontalVelocity(advancedHorizontalVelocity(horizontalVelocity,deltaT))
	 * @effect 	if Mazub is moving vertically, the position and velocity get updated in the vertical direction.
	 * 			|if(this.jump == true)
	 * 				setPositionY((int)(this.getPositionY() + distanceTraveledVertical(verticalVelocity, deltaT)))
	 * 				setVerticalVelocity(advancedVerticalVelocity(verticalVelocity, deltaT))
	 * @post	When Mazub is located at the bottom of the screen, Mazub stops falling.
	 * 			| if(this.getPositionY() == 0) this.jump = false
	 * @throws 	IllegalArgumentException
	 * 			If the given time does not equal a valid value.
	 * 			|! isValidTime(deltaT)
	 * 
	 * 				 
	 */
	public void advanceTime( double horizontalVelocity, double verticalVelocity, double deltaT) 
			throws IllegalArgumentException{
		if (! isValidTime(deltaT))
			throw new IllegalArgumentException();
		try{
			if(!isValidHorizontalVelocity(horizontalVelocity))
				throw new IllegalArgumentException();
		}
		catch(IllegalArgumentException exc){
			if(horizontalVelocity< -this.getMaximumHorizontalVelocity())
				horizontalVelocity=-this.getMaximumHorizontalVelocity();
			else horizontalVelocity = this.getMaximumHorizontalVelocity();
		}
		this.addTime(deltaT);
		this.getCurrentSprite();
		if(this.move == true){
			setPositionX((int)(this.getPositionX() + distanceTraveledHorizontal(horizontalVelocity, deltaT)));
			setHorizontalVelocity(advancedHorizontalVelocity(horizontalVelocity,deltaT));
		}
		if(this.jump == true){
			setPositionY((int)(this.getPositionY() + distanceTraveledVertical(verticalVelocity, deltaT)));
			setVerticalVelocity(advancedVerticalVelocity(verticalVelocity, deltaT));
			if(this.getPositionY() == 0)
				this.jump = false;
		}

	}

	/**
	 * This method calculates the distance travelled vertically based on the given velocity and over a certain time interval.
	 * @param velocity
	 * 		The current vertical velocity of Mazub
	 * @param deltaT
	 * 		The time interval in seconds.
	 * @return  if the absolute value of the given velocity equals the maximum horizontal velocity,
	 * 			then the result is smaller than the velocity times deltaT times 100. We multiply by 100
	 * 			 because position is in centimeters, but velocity is in meter per second.
	 * 			| result <= (velocity *deltaT)*100
	 * @return  if the given velocity is bigger than zero,
	 * 			then the result is smaller than the velocity times deltaT times 100 
	 * 			plus 0.5 times the horizontal accelaration times deltaT squared times 100. We multiply by 100
	 * 			 because position is in centimeters, but velocity is in meter per second.
	 * 			| result <= (velocity * deltaT + 0.5 * this.getHorizontalAccelaration()*deltaT*deltaT)*100
	 * @return	if the given velocity is smaller than zero,
	 * 			then the result is smaller than the velocity times deltaT times 100 
	 * 			minus 0.5 times the horizontal accelaration times deltaT squared times 100. We multiply by 100
	 * 			 because position is in centimeters, but velocity is in meter per second.
	 * 			| result <= (velocity * deltaT - 0.5 * this.getHorizontalAccelaration()*deltaT*deltaT)*100
	 * 
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
	 * 		The current vertical velocity of Mazub
	 * @param deltaT
	 * 		The time interval in seconds.
	 * @return  The result is smaller than the velocity times deltaT times 100 
	 * 			plus 0.5 times the vertical accelaration times deltaT squared times 100. We multiply by 100
	 * 			 because position is in centimeters, but velocity is in meter per second.
	 * 			| result <= (velocity * deltaT + 0.5 * this.VERTICAL_ACCELARATION*deltaT*deltaT)*100
	 */
	private double distanceTraveledVertical(double velocity, double deltaT){
		return (velocity * deltaT + 0.5 * (this.VERTICAL_ACCELARATION)*deltaT*deltaT)*100;
	}

	/**
	 * This method returns the new horizontal velocity after a certain time duration.
	 * @param velocity
	 * 			The current horizontal velocity of Mazub (the velocity before this method is invoked) in m/s.
	 * @param deltaT
	 * 			The time duration in seconds
	 * @return  If the given velocity is bigger than zero, then the result is smaller than the velocity plus 
	 * 			the horizontal accelaration times deltaT.
	 * 			| result <= velocity + this.getHorizontalAccelaration()*deltaT
	 * @return  If the given velocity is smaller than zero, then the result is smaller than the velocity minus
	 * 			the horizontal accelaration times deltaT.
	 * 			| result <= velocity - this.getHorizontalAccelaration()*deltaT
	 */
	private double advancedHorizontalVelocity(double velocity, double deltaT){
		if(velocity>0)
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
	 * 			| result <= velocity + (this.VERTICAL_ACCELARATION)*deltaT
	 */
	private double advancedVerticalVelocity(double velocity, double deltaT){
		return velocity + (this.VERTICAL_ACCELARATION)*deltaT;
	}
	/**
	 * This method adds the given time to the variable this.time
	 * @param time
	 * 			the given time that needs to be added.
	 * @post	The variable this.time is been updated with the given time.
	 * 			| new this.time == this.time + time
	 */
	private void addTime(double time){
		this.time += time;
	}
	/**
	 * A variable that keeps track of the time that has passed.
	 */
	private double time;	
}
