package jumpingalien.model;
import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;
//TODO is vliegen ok? NEE

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
	//total programming
	/**
	 * Mazub starts moving horizontally to the left.
	 * @post Mazub starts moving with an initial horizontal velocity.
	 * 		 Mazub's horizontal velocity increases with the constant horizontal acceleration.
	 //(volgens mij moet dit aanpasbaar zijn)*  	 Mazub's horizontal velocity after some time t (in seconds) is v_{xnew} = v_{xcurrent} + a_x t.
	 *  	 When Mazub's horizontal velocity equals the maximal velocity, then the velocity is constant.
	 * @post Mazub's position is updated based on the distance travelled.
	 */
	public void startMoveLeft(){
		this.setHorizontalVelocity(-(this.getInitialHorizontalVelocity()));
		this.horizontalAccelaration = -this.getHorizontalAccelaration();
		this.move = true;
		this.timeStartLeft = this.time;
	}
	private double timeStartLeft;
	
	//Als we documentatie startMoveLeft aanpassen dan ook deze.
	/**
	 * Mazub starts moving horizontally to the right.
	 * @post Mazub starts moving with a horizontal velocity v_x = 1 m/s.
	 * 		 Mazub's horizontal velocity increases with acceleration a_x = 0.9 m/s².
	 *  	 Mazub's horizontal velocity after some time t (in seconds) is v_{xnew} = v_{xcurrent} + a_x t.
	 *  	 When Mazub's horizontal velocity equals v_max = 3 m/s, then the velocity is constant.
	 * @post Mazub's position is updated based on the distance travelled.
	 */
	public void startMoveRight(){
		this.setHorizontalVelocity(this.getInitialHorizontalVelocity());
		this.horizontalAccelaration = this.getHorizontalAccelaration();
		this.move = true;
		this.timeStartRight = this.time;
	}
	private double timeStartRight;
	
	/**
	 * Mazub stops moving to the left.
	 * @post Mazub's horizontal velocity equals v_x = 0 m/s.
	 * 		| new.getHorizontalVelocity() = 0;
	 */
	public void endMoveLeft(){
		this.setHorizontalVelocity(0);
		this.move = false;
		this.timeLastLeft = this.time;
	}
	private double timeLastLeft;
	
	/**
	 * Mazub stops moving to the right.
	 * @post Mazub's horizontal velocity equals v_x = 0 m/s.
	 * 		| new.getHorizontalVelocity() = 0;
	 */
	public void endMoveRight(){
		this.setHorizontalVelocity(0);
		this.move = false;
		this.timeLastRight = this.time;
	}
	private double timeLastRight;
	private boolean move;
	
	//total programming
	/**
	 * This method gives you the current horizontal velocity of the alien.
	 * @return Returns the current horizontal velocity.
	 */
	public double getHorizontalVelocity(){
		return this.horizontalVelocity;
	}
	
	/**
	 * 
	 * @param velocity
	 * 			new velocity
	 * @post	if velocity is smaller than zero set velocity to zero
	 * 			| if(velocity < 0) new.getHorizontalVelocity() = 0
	 * @post	if velocity is bigger than three set velocity to three
	 * 			|if(velocity > 3) new.getHorizontalVelocity() = 3
	 * @post	if velocity is between 0 and 3 set velocity to velocity.
	 * 			| if (0<velocity<3) newgetHorizontalVelocity() = velocity
	 */
	public void setHorizontalVelocity(double velocity){
		if(velocity > this.getMaximumHorizontalVelocity()) 
			this.horizontalVelocity = this.getMaximumHorizontalVelocity();
		if(velocity < -(this.getMaximumHorizontalVelocity())) 
			this.horizontalVelocity = -(this.getMaximumHorizontalVelocity());
		else
			this.horizontalVelocity = velocity;
	}
	
	//TODO Moet de speler zelf de intiële snelheden kunnen aanpassen?
	/**
	 * Return the initial value for the horizontal velocity of Mazub.
	 * 
	 * @return the initial value for the horizontal velocity of Mazub 
	 * 		   is not below 1 m/s.
	 *       | result >= 1
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
	 * @return the maximal value for the horizontal velocity of Mazub 
	 * 		   is not the initial value of the horizontal velocity.
	 *       | result >= getInitialHorizontalVelocity
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
	
	public void setMaximumHorizontalVelocity(double velocity){
		this.maximumHorizontalVelocity = velocity;
	}
		
	private double maximumHorizontalVelocity = 3;
	/**
	 * A method that checks if the velocity is between minimal horizontal velocity 
	 * and maximal horizontal velocity
	 * 
	 * @param velocity
	 * 				velocity in meter per seconds.
	 * @return
	 * 			returns true if it is a valid velocity.
	 * 			| return(velocity <= this.getMaximumHorizontalVelocity())
	 * 			| &&(velocity>= this.getInitialHorizontalVelocity())
	 */
	public boolean isValidHorizontalVelocity(double velocity){
		return(Math.abs(velocity) <= this.getMaximumHorizontalVelocity());
	}
	
	private double horizontalVelocity=0;

	/**
	 * Return the value for the horizontal acceleration of Mazub.
	 * 
	 * @return the value for the horizontal acceleration of Mazub 
	 * 		   is never below zero.
	 *       | result >= 0
	 * @note   because the documentation has not revealed the actual value, it is
	 *         possible to change these values without having to notify
	 *         any clients of the class. At that time, we might have to
	 *         add new constructors by means of which we can initialize
	 *         new Mazubs and this does not break the contract with the
	 *         clients.
	 */
	@Basic
	public double getHorizontalAccelaration(){
		return 0.9;
	}
	private double horizontalAccelaration=0;
	//einde Anne begin Joni
	//defensive programming
	//TODO Moet de velocity afnemen omwille van de zwaartekracht? JA
	public void startJump(){
		this.jump = true;
		setVerticalVelocity(this.getInitialVerticalVelocity());
		
	}
	public void endJump(){
		setVerticalVelocity(0);
		
	}
	
	private boolean jump;
	
	/**
	 * This method gives you the current vertical velocity of the alien.
	 * @return Returns the current vertical velocity.
	 */
	public double getVerticalVelocity(){
		return this.verticalVelocity;
	}
	
	public void setVerticalVelocity(double velocity){
			this.verticalVelocity = velocity;
	}
	
	@Basic
	public double getInitialVerticalVelocity(){
		return 8;
	}
	
	public boolean isValidVerticalVelocity(double velocity){
		return((velocity ==8) ||(velocity <=0));
		
	}
	
	private double verticalVelocity=0;
	
	/**
	 * Return the value for the vertical acceleration of Mazub.
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
	
	/**
	 * Mazub starts ducking.
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
	public void endDuck(){
		this.setMaximumHorizontalVelocity(3);
		this.duck = false;
	}
	private boolean duck;
	//METHODE van MAKEN 
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

	private final int M = 10;
	private Sprite[] sprites;
	private Sprite currentSprite;
	//einde Joni begin Anne
	/**
	 * Returns the x-coordinate of the position of Mazub.
	 */
	public int getPositionX(){
		return this.positionX;
	}
	
	//TODO beweegt het beeld mee met het poppetje, of steeds nieuwe beelden, is dit een goede oplossing? OK
	//TODO total programming?
	/**
	 * Set the x-coordinate of the position of Mazub. 
	 *
	 * @param	position
	 *			The new position of Mazub.
	 * @post    If the given position is in the range established by the minimum
	 *          and maximum position of Mazub, the x-coordinate of mazub is equal to position. 
	 *        | if ( (position >= MIN_POSITIONX) && (position <= MAX_POSITIONX) )
	 *        |   then new.getPositionX() == position
	 * @post    If the given position exceed the the maximum position of Mazub or 
	 * 			if the given position is below the minimum position of Mazub
	 * 			the position of Mazub is equal to the given position,
	 *          modulo the range established by the minimum and maximum position of
	 *          Mazub.
	 *        | if (position > MAX_POSTIONX) || if (position < MIN_POSTIONX)
	 *        |   then new.getPositionX() ==
	 *        |          ((position-MIN_POSITIONX) % (MAX_POSITIONX-MIN_POSITIONX+1)) + MIN_POSITIONX
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
			if(this.positionX<0)
				this.positionX += MAX_POSITIONX+1;
		}
		else
			this.positionX = position;
	}
	
	private int positionX;
	
	private static final int MIN_POSITIONX = 0;
	
	private static final int MAX_POSITIONX = 1023;
	
	/**
	 * Returns the y-coordinate of the position of Mazub.
	 */
	public int getPositionY(){
		return this.positionY;
	}
	
	//TODO Moet ge zijn hoofd zien? (anders size eraf) NEE
	/**
	 * Set the y-coordinate of the position of Mazub. 
	 *
	 * @param	position
	 *			The new position of Mazub.
	 * @post    If the given position is in the range established by the minimum
	 *          and maximum position of Mazub, the y-coordinate of mazub is equal to position. 
	 *        | if ( (position >= MIN_POSITIONY) && (position <= MAX_POSITIONY) )
	 *        |   then new.getPositionY() == position
	 * @post    If the given position exceed the the maximum position of Mazub or 
	 * 			if the given position is below the minimum position of Mazub
	 * 			the position of Mazub is equal to the given position,
	 *          modulo the range established by the minimum and maximum position of
	 *          Mazub.
	 *        | if (position > MAX_POSTIONY) || if (position < MIN_POSTIONY)
	 *        |   then new.getPositionY() ==
	 *        |          ((position-MIN_POSITIONY) % (MAX_POSITIONY-MIN_POSITIONY+1)) + MIN_POSITIONY
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
		if (position >= MAX_POSITIONY){
			this.positionY = MAX_POSITIONY;
		}
		else if(position< MIN_POSITIONY){
			this.positionY = MIN_POSITIONY;
		}
		else
			this.positionY = position;
	}
	
	private int positionY;
	
	private static final int MIN_POSITIONY = 0;
	
	private static final int MAX_POSITIONY = 767;
	
	/**
	 * A method that checks if the time duration is valid.
	 * @param time 
	 * 				time duration in seconds.
	 * @return
	 * 			returns true if it is a valid time.
	 */
	public boolean isValidTime(double deltaT){
		return((deltaT >=0)&&(deltaT <= 0.2));
	}
	//einde Anne begin Joni
	//defensive programming
	/**
	 * This method updates the position and horizontal velocity of Mazub.
	 * @param positionX
	 * 					The x-coordinate of the position of Mazub.
	 * @param positionY
	 * 					The y-coordinate of the position of Mazub.
	 * @param horizontalVelocity
	 * 					The current horizontal velocity of Mazub.
	 * @param time
	 * 					The time duration in seconds.
	 * @post 	if horizontalVelocity == this.getMaximumVelocity()
	 * 			then new position x==
	 * @throws 	IllegalArgumentException
	 * 			...
	 * 			|! isValidTime(time)
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
	
	//TODO Moet dit totaal of deffensief geprogrammeerd worden? TOTAAL
	/**
	 * Calculates the distance traveled based on given velocity and time duration.
	 * 
	 * @param velocity
	 * @param time
	 * @return distance traveled.
	 * @thows IllegalArgumentException
	 * 			| ! isValidVelocity(velocity) || ! isValidTime(time)
	 */
	public double distanceTraveledHorizontal(double velocity, double deltaT){
		if (Math.abs(velocity) == this.getMaximumHorizontalVelocity())
			return (velocity *deltaT);
		if(velocity>0)
			return (velocity * deltaT + 0.5 * this.getHorizontalAccelaration()*deltaT*deltaT);
		return (velocity * deltaT + 0.5 * (-(this.getHorizontalAccelaration()*deltaT*deltaT)));
	}
	//TODO Wat te doen als velocity niet 8 is en niet negatief is?
	public double distanceTraveledVertical(double velocity, double deltaT){
		if (velocity == this.getInitialVerticalVelocity())
			return (velocity *deltaT);
		if(velocity<=0)
			return (velocity * deltaT + 0.5 * this.getVerticalAccelaration()*deltaT*deltaT);
		return 0;
	}
	
	//TODO Moet dit totaal of deffensief geprogrammeerd worden?
	public double advancedHorizontalVelocity(double velocity, double deltaT){
		if(velocity>0)
			return velocity + this.getHorizontalAccelaration()*deltaT;
		return velocity - this.getHorizontalAccelaration()*deltaT;
	}
	public double advancedVerticalVelocity(double velocity, double deltaT){
		if(velocity == 8)
			return velocity;
		return velocity + this.getVerticalAccelaration()*deltaT;
	}
	
	public void addTime(double time){
		this.time += time;
	}
	
	private double time;
	

}
