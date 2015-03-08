package jumpingalien.model;
import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;
//TODO is vliegen ok? NEE
// Gingen we nu ducken en jumpen tegelijk uitschakelen?

/**
 * A class for dealing with the aliens called Mazub.
 *    In the current version Mazub does not move LOL.
 * 
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
	 * 		  | new.getCurrentSprite() = sprites
	 */
	
	public Mazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		this.positionX = pixelLeftX;
		this.positionY = pixelBottomY;
		this.sprites = sprites;
		this.currentSprite = sprites[0];
	}
	// Begin Anne
	// nominal programming Joni: Moet ge dan geen précondities hebben?
	//Joni: Moest die 0.9 ni aanpasbaar zijn?
	/**
	 * Mazub starts moving horizontally to the left.
	 * @post 	Mazub starts accelerating with a specific horizontal acceleration to the left.
	 * 		 	| new this.getHorizontalAccelaration() = - 0.9
	 * @effect 	Mazub starts moving with an initial horizontal velocity to the left (negative).
	 * 		 	| setHorzontalAcceleration( - this.getInitialHorizontalVelocity())
	 * 
	 */
	// TODO post of effect? JONi: op deze manier effect volgens mij.
	// TODO bij de acceleratie hoe moet dit informeel? Joni:Hier snap ik niet goed wat je ermee bedoelt?
	public void startMoveLeft(){
		this.setHorizontalVelocity(-(this.getInitialHorizontalVelocity()));
		this.horizontalAccelaration = -this.getHorizontalAccelaration();
		this.move = true;
		this.timeStartLeft = this.time;
	}
	// Joni: is die "on" ni een beetje raar of ligt da an mij?
	/**
	 * Variable registering the time on when Mazub starts moving to the left.
	 */
	private double timeStartLeft;
	
	// nominal programming	
	/**
	 * Mazub starts moving horizontally to the right.
	 * @post 	Mazub starts accelerating with a specific horizontal acceleration to the right.
	 * 		 	| new this.getHorizontalAccelaration() = 0.9
	 * @effect	Mazub starts moving with an initial horizontal velocity to the right (positive).
	 * 		 	| setHorzontalAcceleration(this.getInitialHorizontalVelocity())
	 * 
	 */
	public void startMoveRight(){
		this.setHorizontalVelocity(this.getInitialHorizontalVelocity());
		this.horizontalAccelaration = this.getHorizontalAccelaration();
		this.move = true;
		this.timeStartRight = this.time;
	}
	
	/**
	 * Variable registering the time on when Mazub starts moving to the right.
	 */
	private double timeStartRight;
	
	//nominal programming	
	/**
	 * Mazub stops moving to the left.
	 * @effect 	Mazub's horizontal velocity equals 0 m/s.
	 * 			| setHorizontalVelocity(0)
	 */
	public void endMoveLeft(){
		this.setHorizontalVelocity(0);
		this.move = false;
		this.timeLastLeft = this.time;
	}
	
	/**
	 * Variable registering when Mazub stops moving to the left. 
	 */
	private double timeLastLeft;
	
	//nominal programming
	/**
	 * Mazub stops moving to the right.
	 * @effect  Mazub's horizontal velocity equals 0 m/s.
	 * 			| setHorizontalVelocity(0)
	 */
	public void endMoveRight(){
		this.setHorizontalVelocity(0);
		this.move = false;
		this.timeLastRight = this.time;
	}
	
	/**
	 * Variable registering when Mazub stops moving to the right. 
	 */
	private double timeLastRight;
	
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
	public void setHorizontalVelocity(double velocity){
		if(velocity > this.getMaximumHorizontalVelocity()) 
			this.horizontalVelocity = this.getMaximumHorizontalVelocity();
		if(velocity < -(this.getMaximumHorizontalVelocity())) 
			this.horizontalVelocity = -(this.getMaximumHorizontalVelocity());
		else
			this.horizontalVelocity = velocity;
	}
	
	//TODO moeten we hier geen set bij maken, want er staat dat er verschillende Mazubs kunnen
	// zijn met een verschillende initiele snelheden? Joni: Dit moeten we dan is bekijken want ik heb da precies gemist.
	// ik denk ook dat deze methode een statische methode moet zijn. JONI: Misschien heb je daar wel gelijk da zullen we samen is bekijken.
	//Joni: klopt u formele specificatie bij @return wel? Moet het niet >= 1 zijn?
	/**
	 * Gives the initial horizontal velocity of Mazub.
	 * 
	 * @return The initial value for the horizontal velocity of Mazub 
	 * 		   is never below 1 m/s.
	 *       | result <= 1 m/s
	 * @note   because the documentation has not revealed the actual value, it is
	 *         possible to change these values without having to notify
	 *         any clients of the class. At that time, we might have to
	 *         add new constructors by means of which we can initialize
	 *         new Mazubs and this does not break the contract with the
	 *         clients.
	 */
	@Basic
	public double getInitialHorizontalVelocity(){
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
	public void setMaximumHorizontalVelocity(double velocity){
		if(velocity <= this.getInitialHorizontalVelocity())
			this.maximumHorizontalVelocity = this.getInitialHorizontalVelocity();
		else this.maximumHorizontalVelocity = velocity;
	}
	
	//TODO moeten we hier ook bij zeggen dat we deze geinitaliseerd hebben op 3 en waarom? 
	// JOni: Ik denk dat we 3 niet in de documentatie mogen zetten omdat dat aanpasbaar moest zijn.

	/**
	 * Variable registering the maximum horizontal velocity.
	 */
	private double maximumHorizontalVelocity = 3;
	
	//TODO ik vraag me af of we deze methode wel echt nodig hebben. Ik denk toch van wel :P 
	// want we gebruiken deze wel bij advancetime :P JOni: Ik denk wel dat die belangrijk is om de defensiviteit erin te krijgen.
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
	@Basic
	public static double getHorizontalAccelaration(){
		return 0.9;
	}
	
	/**
	 * Variable registering the horizontal acceleration.
	 */
	private double horizontalAccelaration=0;
	
	
	//einde Anne begin Joni
	//defensive programming
	//TODO Moet de velocity afnemen omwille van de zwaartekracht? JA
	//TODO Joni: Wat moeten we hier "throwen" als de positie niet nul is en moeten we dit dan catchen?
	/**
	 * Mazub starts jumping.
	 * @pre 	Mazub is loccated at the bottom of the screen.
	 * 			| this.getPositionY() == 0
	 * @post 	Mazub starts moving upwards (vertically) with a specific vertical velocity.
	 * 		 	| new this.getVerticalVelocity() = this.getInitialVerticalVelocity()
	 * @throws 	IllegalArgumentException
	 * 			The current position is not a valid one.
	 * 			|this.positionY !=0
	 * 
	 */
	// public void startJump() throws IllegalArgumenException{}
	public void startJump(){
		this.jump = true;
		setVerticalVelocity(this.getInitialVerticalVelocity());
		
	}
	//TODO gaan we try-catchen als de y-positie al op nul staat, dat hij dan niets doet?
	/**
	 * Mazub stops jumping.
	 * @pre		Mazub's position is not located at the bottom of the screen and Mazub is falling.
	 * 			| this.getPositionY() != 0
	 * @effect 	Mazub's vertical velocity equals 0 m/s.
	 * 			| setVerticalVelocity(0)
	 */
	public void endJump(){
		setVerticalVelocity(0);
		
	}
	/**
	 * Variable registering if Mazub is jumping (true) or is not jumping (false).
	 */
	private boolean jump;
	//Joni: Is die 2e lijn nodig wrs niet?
	/**
	 * This method gives you the current vertical velocity of Mazub.
	 * @return Returns the current vertical velocity.
	 */
	public double getVerticalVelocity(){
		return this.verticalVelocity;
	}
	// Joni: Hier heb ik wel die getallen gebruikt omdat die ook niet in toekomstige versies gaan veranderen.
	//TODO Joni: hier kunnen we best nog eens naar kijken of dit gaat kloppen met de aanpassingen voor jump enzo.
	/**
	 * Set the Vertical velocity of Mazub to a given velocity.
	 * @param velocity
	 * 			The new vertical velocity.
	 * @post	If the given velocity is bigger than 8m/s(of  the initial vertical velocity),
	 * 			the new velocity is equal to 8m/s(of the initial vertical velocity).
	 * 			| if(velocity > 8(of this.getInitialVerticalVelocity()))
	 * 			| new.getVerticalVelocity() = 8 (of this.getInitialVerticalVelocity())
	 * @post	if the given velocity is smaller than 8m/s(of  the initial vertical velocity), the new velocity is equal to the given velocity.
	 * 			| if ((velocity < 8(of this.getInitialVerticalVelocity()))
	 * 			| new.getVerticalVelocity() = velocity
	 */
	public void setVerticalVelocity(double velocity){
			this.verticalVelocity = velocity;
	}
	//Joni: Volgens mij mogen we hier een variabele van maken omdat die nooit verandert. Ma we zullen dat nog is bespreken.
	/**
	 * Gives the initial vertical velocity of Mazub.
	 * 
	 * @return  The initial value for the vertical velocity equals 8 m/s.
	 * 			| result == 8
	 */
	@Basic
	public double getInitialVerticalVelocity(){
		return 8;
	}
	/**
	 * A method that checks if the vertical velocity is smaller than 8m/s.
	 * @param 	velocity
	 * 			The velocity to check.
	 * @return	True if and only if the velocity is smaller than 8m/s.
	 * 			| result == (velocity <= 8)
	 */
//	public boolean isValidVerticalVelocity(double velocity){
//		return(velocity <= 8);
//		
//	}
	public boolean isValidVerticalVelocity(double velocity){
		return((velocity ==8) ||(velocity <=0));
		
	}
	/**
	 * Variable registering the vertical velocity.
	 */
	private double verticalVelocity=0;
	// Joni: Ook dit kan misschien een variabele worden omdat dit in toekomstige versies niet gaat veranderen.
	/**
	 * This method returns the value for the vertical acceleration of Mazub.
	 * 
	 * @return the value for the vertical acceleration of Mazub. 
	 * @note   because the documentation has not revealed the actual value, it is
	 *         possible to change these values without having to notify
	 *         any clients of the class. At that time, we might have to
	 *         add new constructors by means of which we can initialize
	 *         new Mazubs and this does not break the contract with the
	 *         clients.
	 */
	@Basic
	public double getVerticalAccelaration(){
		return -10;
	}
	//TODO Joni: Nog eens goed nakijken. Moet ik jumpen hierbij vermelden?
	// Hoe maak ik dit defensief? 
	/**
	 * Mazub starts ducking to decrease his size.
	 * @post if Mazub is not moving horizontally, has not moved horizontally within the last
	 * 		 second of in-game-time, then images_1 is displayed.
	 * 		 | if(this.getHorizontalVelocity() == 0) new.currentSprite = sprites[1]
	 * @post if Mazub is moving to the right or was moving to the right (within 1s), 
	 * 		 then images_6 is displayed.
	 * 		 | if(this.startMoveRight == true) new.currentSprite = sprites[6]
	 * @post if Mazub is moving to the left or was moving to the left (within 1s), 
	 * 		 then images_7 is displayed.
	 * 		 | if(this.startMoveRight == true) new.currentSprite = sprites[7]
	 */
	public void startDuck(){
		this.setMaximumHorizontalVelocity(1);
		this.duck = true;
	}
	//TODO Joni: Nog eens goed nakijken. Moet ik jumpen hierbij vermelden?
	/**
	 * Mazub stops ducking.
	 * @post if Mazub is not moving horizontally, has not moved horizontally within the last
	 * 		 second of in-game-time, then images_0 is displayed.
	 * 		 | if(this.getHorizontalVelocity() == 0) new.currentSprite = sprites[0]
	 * @post if Mazub is not moving horizontally but its last horizontal movement was to the right (within 1s), 
	 * 		 then images_2 is displayed.
	 * 		 | if((this.move == false)&&(this.time > this.timeLastLeft+1)&&(this.time <= this.timeLastRight+1)) new.currentSprite = sprites[2]
	 * @post if Mazub is not moving horizontally but its last horizontal movement was to the left (within 1s), 
	 * 		 then images_3 is displayed.
	 * 		 | if((this.move == false)&&(this.time <= this.timeLastLeft+1)&&(this.time > this.timeLastRight+1)) new.currentSprite = sprites[2]
	 * @post if Mazub is moving to the right or was moving to the right (within 1s), 
	 * 		 then images_8 is displayed.
	 * 		 | if(this.startMoveRight == true) new.currentSprite = sprites[8]
	 * @post if Mazub is moving to the left or was moving to the left (within 1s), 
	 * 		 then images_9 is displayed.
	 * 		 | if(this.startMoveRight == true) new.currentSprite = sprites[9]
	 */
//	Alternatief
//	/**
//	 * Mazubs stops ducking and increases his size to the normal size
//	 * @effect	The image of Mazub changes accordingly to his movement
//	 * 			| this.currentSprite == this.getCurrentSprite()
//	 */
	public void endDuck(){
		this.setMaximumHorizontalVelocity(3);
		this.duck = false;
	}
	private boolean duck;
	//1 METHODE van MAKEN 
	/**
	 * This method gives you the current sprite of the alien.
	 * @return Returns the current sprite.
	 */
	public Sprite getCurrentSprite(){
		return this.currentSprite;
	}
	//TODO Nominaal?
	public void setCurrentSprite(int numberSprite){
		this.currentSprite = sprites[numberSprite];
	}
	//TODO Joni:hier gaan we dan getCurrentSprite van maken. Geen formele specificaties gevraagd Hoe nomniaal maken?
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
	 * 		 then images_8 until 18 are displayed.
	 * @post if Mazub is moving to the left or was moving to the left (within 1s)and is not ducking nor jumping, 
	 * 		 then images_9+this.M until 9+2*this.M are displayed.
	 * @return
	 */
	public int calculateSprite(){
		if((this.move == false)&&(this.time > this.timeLastLeft+1)&&(this.time > this.timeLastRight+1)&&(this.duck == true)&&(this.jump == true))
			return 1;
		else if((this.move == false)&&(this.time > this.timeLastLeft+1)&&(this.time <= this.timeLastRight+1)&&(this.duck == false)&&(this.jump == true))
			return 2;
		else if((this.move == false)&&(this.time <= this.timeLastLeft+1)&&(this.time > this.timeLastRight+1)&&(this.duck == false)&&(this.jump == true))
			return 3;
		else if((this.move == true)&&(this.getHorizontalVelocity() > 0)&&(this.duck == false)&&(this.jump == true))
			return 4;
		else if((this.move == true)&&(this.getHorizontalVelocity() < 0)&&(this.duck == false)&&(this.jump == true))
			return 5;
		else if((((this.move == true)&&(this.getHorizontalVelocity() > 0))||((this.move == false)&&(this.time > this.timeLastRight+1)))&&(this.duck == true)&&(this.jump == false))
			return 6;
		else if((((this.move == true)&&(this.getHorizontalVelocity() < 0))||((this.move == false)&&(this.time > this.timeLastLeft+1)))&&(this.duck == true)&&(this.jump == false))
			return 7;
		else if((this.move == true)&&(this.getHorizontalVelocity() > 0)&&(this.duck == false)&&(this.jump == false)){
			return 8 + (int)(((this.time-this.timeStartRight)/0.075)%(this.M+1));
		}
		else if((this.move == true)&&(this.getHorizontalVelocity() < 0)&&(this.duck == false)&&(this.jump == false))
			return 9 + this.M + (int)(((this.time-this.timeStartLeft)/0.075)%(this.M+1));
		
		return 0;
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
	 * This variable keeps track of the current displayed image of Mazub.
	 */
	private Sprite currentSprite;
	//einde Joni begin Anne
	
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
	public int getPositionX(){
		return this.positionX;
	}
	//Joni: Hier ben ik niet helemaal me mee.
	//TODO total programming? NEE defensief!! is dit defensief?
	/*
	 * 	/**
	 *   * Set the x-coordinate of Mazub.
	 *   * @ param position
	 *   * 			the new x-position of mazub.
	 *   * @post
	 * public void setPositionX(int position){
	 * 	try{
	 * 		if(!isValidPositionX(position))
	 * 			throw new IllegalArgumentException();
	 * }
	 * catch(IllegalArgumentException exc){
	 * 		if((position >= -MAX_POSITIONX)&&(position < MIN_POSITIONX))
	 * 			position += MAX_POSITIONX;
	 * 		if((position > MAX_POSITIONX)&&(position <= 2*MAX_POSITIONX))
	 * 			position -= MAX_POSITIONX;
	 * }
	 *  this.positionX = position;
	 * 
	 *   method that checks if the position of X is a valid position.
	 * public boolean isValidPositionX(int position){
	 * 		return ((position>=MIN_POSITIONX)&&(position<=MAX_POSITIONX))
	 * }
	 */	
	/**
	 * Set the x-coordinate of the position of Mazub. 
	 *
	 * @param	position
	 *			The new position of Mazub.
	 * @post    If the given position is in the range established by the minimum
	 *          and maximum position of Mazub, the x-coordinate of mazub is equal to position. 
	 *        	| if ( (position >= MIN_POSITIONX) && (position <= MAX_POSITIONX) )
	 *        	| 	new this.getPositionX() == position
	 * @post    If the given position exceeds the the maximum position of Mazub then the new
	 * 			position of Mazub is equal to the given position modulo the range established 
	 * 			by the minimum and maximum position of Mazub. 
	 * 			If the given position is below the minimum position of Mazub then the new 
	 * 			position of Mazub is equal to the given position modulo the range established
	 *			by the minimum and maximum position of Mazub, plus the maximum position 
	 *			of Mazub.
	 *        	| if (position > MAX_POSTIONX) || if (position < MIN_POSTIONX)
	 *        	|   new.getPositionX() = ((position-MIN_POSITIONX) % 
	 *        	|		(MAX_POSITIONX-MIN_POSITIONX+1)) + MIN_POSITIONX
	 *        	| 	if(position < MIN_POSITION)
	 *        	| 		new.getPositionX() = position + MAX_POSITION	
	 * @note   This method, as well as others in this class, illustrates the
	 *         principles of total programming. Exceptional cases, i.e.
	 *         exceptional values for the hours of a clock are handled in
	 *         postconditions. Obviously, other options are open in handling
	 *         these exceptional cases. As an example, we could have chosen
	 *         to leave the hours untouched in case the given value exceeded
	 *         the highest possible value for the hours. The important thing
	 *         is that the documentation of the method reveals what happens
	 *         in those exceptional cases.
	 * @note   The last postcondition is not really needed. Because of the inertia
	 *         axiom, everything that is not touched upon in the specification
	 *         of a method keeps its value.
	 */
	public void setPositionX(int position){
		if ((position >= MAX_POSITIONX)||(position<= MIN_POSITIONX)){
			this.positionX = ((position - MIN_POSITIONX) % (MAX_POSITIONX-MIN_POSITIONX+1)) 
			+ MIN_POSITIONX;
			if(this.positionX < MIN_POSITIONX)
				this.positionX += MAX_POSITIONX+1;
		}
		else this.positionX = position;
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
	public int getPositionY(){
		return this.positionY;
	}
	
	/* TODO ook hier defensief?
	 *
	 * 	/**
	 *   * Set the y-coordinate of Mazub.
	 *   * @ param position
	 *   * 			the new y-position of mazub.
	 *   * @post ...
	 * public void setPositionY(int position){
	 * 	try{
	 * 		if(!isValidPositionY(position))
	 * 			throw new IllegalArgumentException();
	 * }
	 * catch(IllegalArgumentException exc){
	 * 		if(position < MIN_POSITION)
	 * 			position = MIN_POSITION;
	 * 		if(position > MAX_POSITION)
	 * 			position = MAX_POSITION;
	 * }
	 *  this.positionY = position;
	 * 
	 *   method that checks if the position of X is a valid position.
	 * public boolean isValidPositionY(int position){
	 * 		return ((position>=MIN_POSITIONY)&&(position<=MAX_POSITIONY))
	 * }
	 */
	/**
	 * Set the y-coordinate of the position of Mazub. 
	 *
	 * @param	position
	 *			The new position of Mazub.
	 * @post    If the given position is in the range established by the minimum
	 *          and maximum position of Mazub, the y-coordinate of mazub is equal to 
	 *          the given position. 
	 *        	| if ( (position >= MIN_POSITIONY) && (position <= MAX_POSITIONY) )
	 *        	|   then new.getPositionY() = position
	 * @post    If the given position exceed the the maximum position of Mazub the new 
	 * 			y-coordinate of Mazub is equal to the maximum position of Mazub.
	 *        	| if (position > MAX_POSTIONY)
	 *        	|  		new.getPositionY() = MAX_POSITIONY
	 * @post	If the given postion is smaller than the minimum position of Mazub, the
	 * 			new y-coordinate of Mazub is equal to the minimum position of Mazub.
	 * 			| if (position< MIN_POSITIONY)
	 * 			| 		new.getPositionY() = MIN_POSITIONY
	 * @note   This method, as well as others in this class, illustrates the
	 *         principles of total programming. Exceptional cases, i.e.
	 *         exceptional values for the hours of a clock are handled in
	 *         postconditions. Obviously, other options are open in handling
	 *         these exceptional cases. As an example, we could have chosen
	 *         to leave the hours untouched in case the given value exceeded
	 *         the highest possible value for the hours. The important thing
	 *         is that the documentation of the method reveals what happens
	 *         in those exceptional cases.
	 * @note   The last postcondition is not really needed. Because of the interia
	 *         axiom, everything that is not touched upon in the specification
	 *         of a method keeps its value.
	 */
	public void setPositionY(int position){
		if (position > MAX_POSITIONY){
			this.positionY = MAX_POSITIONY;
		}
		else if(position < MIN_POSITIONY){
			this.positionY = MIN_POSITIONY;
		}
		else
			this.positionY = position;
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
	
	//einde Anne begin Joni
	//defensive programming
	/**
	 * This method updates the position and velocity of Mazub in both directions.
	 * @param positionX
	 * 					The x-coordinate of the position of Mazub.
	 * @param positionY
	 * 					The y-coordinate of the position of Mazub.
	 * @param horizontalVelocity
	 * 					The current horizontal velocity of Mazub.
	 * @param verticalVelocity
	 * 					The current vertical velocity of Mazub.
	 * @param deltaT
	 * 					The time duration in seconds.
	 * @effect 	if Mazub is moving horizontally, the position and velocity get updated in the horizontal direction.
	 * 			|if(this.move == true)
	 * 				new this.getPositionX() = this.getPositionX() + distanceTraveledHorizontal(horizontalVelocity, deltaT)
	 * 				new this.getHorizontalVelocity() = this.advancedHorizontalVelocity(horizontalVelocity, deltaT)
	 * @effect 	if Mazub is moving vertically, the position and velocity get updated in the vertical direction.
	 * 			|if(this.jump == true)
	 * 				new this.getPositionY() = this.getPositionY() + distanceTraveledVertical(verticalVelocity, deltaT)
	 * 				new this.getVerticalVelocity() = this.advancedVerticalVelocity(verticalVelocity, deltaT)
	 * @throws 	IllegalArgumentException
	 * 			If the given time, the given horizontal velocity or the given vertical velocity does not equal a valid value.
	 * 			|! isValidTime(deltaT) || !isValidVerticalVelocity(verticalVelocity) || !isValidHorizontalVelocity(horizontalVelocity)
	 * 
	 * 				 
	 */
	public void advanceTime( double horizontalVelocity, double verticalVelocity, double deltaT) 
			throws IllegalArgumentException{
		if (! isValidTime(deltaT) || ! isValidVerticalVelocity(verticalVelocity))
			throw new IllegalArgumentException();
		try{
			if(!isValidHorizontalVelocity(horizontalVelocity))
				throw new IllegalArgumentException();
		}
		catch(IllegalArgumentException exc){
			if(horizontalVelocity<= -this.getMaximumHorizontalVelocity())
				horizontalVelocity=-this.getMaximumHorizontalVelocity();
			if(horizontalVelocity>=this.getMaximumHorizontalVelocity())
				horizontalVelocity = this.getMaximumHorizontalVelocity();
		}
		this.addTime(deltaT);
		this.setCurrentSprite(this.calculateSprite());
		if(this.move == true){
			setPositionX((int)(this.getPositionX() + (distanceTraveledHorizontal(horizontalVelocity, deltaT))*100));
			setHorizontalVelocity(advancedHorizontalVelocity(horizontalVelocity,deltaT));
			}
		if(this.jump == true){
			setPositionY((int)(this.getPositionY() + (distanceTraveledVertical(verticalVelocity, deltaT))*100));
			setVerticalVelocity(advancedVerticalVelocity(verticalVelocity, deltaT));
			if(this.getPositionY() == 0)
				this.jump = false;
		}
		
	}
	
	//TODO total programming
	// JOni: De formules in deze methode moeten aanpasbaar zijn, volgens mij mag ik deze dan niet in de documentatie vermelden maar ho eschrijf ik dan de documentatie?
	//TODO Joni:De implementatie moet ook nog eens herbekeken worden nu het totaal moet.
	/**
	 * This method calculates the distance travelled vertically based on the given velocity and over a certain time interval.
	 * @param velocity
	 * 		The current vertical velocity of Mazub
	 * @param deltaT
	 * 		The time interval in seconds.
	 * @return if(!isValidTime || !isValidHorizontalVelocity)
	 * 				result == 0
	 * 
	 */
	public double distanceTraveledHorizontal(double velocity, double deltaT){
		if (Math.abs(velocity) == this.getMaximumHorizontalVelocity())
			return (velocity *deltaT);
		if(velocity>0)
			return (velocity * deltaT + 0.5 * this.getHorizontalAccelaration()*deltaT*deltaT);
		return (velocity * deltaT + 0.5 * (-(this.getHorizontalAccelaration()*deltaT*deltaT)));
	}
	//TODO Wat te doen als velocity niet 8 is en niet negatief is?
	// Joni: De formules in deze methode moeten aanpasbaar zijn, volgens mij mag ik deze dan niet in de documentatie vermelden maar ho eschrijf ik dan de documentatie?
	/**
	 * This method calculates the distance travelled vertically based on the given velocity and over a certain time interval.
	 * @param velocity
	 * 		The current vertical velocity of Mazub
	 * @param deltaT
	 * 		The time interval in seconds.
	 * @return if(!isValidTime || !isValidVerticalVelocity)
	 * 				result == 0 
	 */
	public double distanceTraveledVertical(double velocity, double deltaT){
		if (velocity == this.getInitialVerticalVelocity())
			return (velocity *deltaT);
		if(velocity<=0)
			return (velocity * deltaT + 0.5 * this.getVerticalAccelaration()*deltaT*deltaT);
		return 0;
	}
	
	//TODO Moet dit totaal of deffensief geprogrammeerd worden?
	//TODO Joni: ik denk dat getHorizontalAccelaration niet static moet zijn maar final.
	// Joni: De formules in deze methode moeten aanpasbaar zijn, volgens mij mag ik deze dan niet in de documentatie vermelden maar ho eschrijf ik dan de documentatie?
	/**
	 * This method returns the new horizontal velocity after a certain time duration.
	 * @param velocity
	 * 			The current horizontal velocity of Mazub (the velocity before this method is invoked) in m/s.
	 * @param deltaT
	 * 			The time duration in seconds
	 * @return	
	 */
	public double advancedHorizontalVelocity(double velocity, double deltaT){
		if(velocity>0)
			return velocity + this.getHorizontalAccelaration()*deltaT;
		return velocity - this.getHorizontalAccelaration()*deltaT;
	}
	// Joni: De formules in deze methode moeten aanpasbaar zijn, volgens mij mag ik deze dan niet in de documentatie vermelden maar ho eschrijf ik dan de documentatie?
		/**
		 * This method returns the new vertical velocity after a certain time duration.
		 * @param velocity
		 * 			The current vertical velocity of Mazub (the velocity before this method is invoked) in m/s.
		 * @param deltaT
		 * 			The time duration in seconds
		 * @return	
		 */
	public double advancedVerticalVelocity(double velocity, double deltaT){
		if(velocity == 8)
			return velocity;
		return velocity + this.getVerticalAccelaration()*deltaT;
	}
	/**
	 * This method adds the given time to the variable this.time
	 * @param time
	 * 			the given time that needs to be added.
	 * @post	The variable this.time is been updated with the given time.
	 * 			| new this.time == this.time + time
	 */
	public void addTime(double time){
		this.time += time;
	}
	/**
	 * A variable that keeps track of the time that has passed.
	 */
	private double time;
	

}
