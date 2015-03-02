package jumpingalien.model;
import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;


public class Mazub {
	
	public Mazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		this.positionX = pixelLeftX;
		this.positionY = pixelBottomY;
		this.sprites = sprites;
		this.currentSprite = sprites[0];
	}
	//JOni
	private Sprite[] sprites;
	private Sprite currentSprite;
	//Joni
	//total programming
	// documentatie herzien
	/**
	 * Mazub starts moving horizontally.
	 * @post Mazub starts moving with a horizontal velocity v_x = 1 m/s.
	 * 		 Mazub's horizontal velocity increases with acceleration a_x = 0.9 m/s².
	 *  	 Mazub's horizontal velocity after some time t (in seconds) is v_{xnew} = v_{xcurrent} + a_x t.
	 *  	 When Mazub's horizontal velocity equals v_max = 3 m/s, then the velocity is constant.
	 *  @effect if Mazub is moving, then every 0.1 second the velocity and position gets updated.
	 *  		| advanceTime(this.getPositionX(), this.getPositionY(), this.getVelocity(), 0.1)
	 */
	public void startMove(){
		this.move = true;
		advanceTime(this.getInitialHorizontalVelocity(),0.1);
		while (this.move == true){
			advanceTime(this.getHorizontalVelocity(), 0.1);
		}
	}
	//extra stuk 1
	public void startMoveLeft(){}
	public void startMoveRight(){}
	public void endMoveLeft(){}
	public void endMoveRight(){}
	public void startDuck(){}
	public void endDuck(){}
	public void startJump(){}
	public void endJump(){}
	public Sprite getCurrentSprite(){
		return this.currentSprite;
	}
	//einde extra stuk 1
	//total programming
	/**
	 * Mazub stops moving.
	 * @post Mazub's horizontal velocity equals v_x = 0 m/s.
	 */
	public void endMove(){
		this.move = false;
		setHorizontalVelocity(0);
	}
	
	private boolean move = false;
	private double horizontalVelocity;
	
	public double getHorizontalVelocity(){
		return this.horizontalVelocity;
	}
	//Extra Stukje Joni 2
	
	private double verticalVelocity;
	
	public double getVerticalVelocity(){
		return this.verticalVelocity;
	}
	
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
	//Einde extra stukje 2
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
		if(velocity < 0) 
			this.horizontalVelocity = 0;
		if(velocity > 3) 
			this.horizontalVelocity = 3;
		this.horizontalVelocity = velocity;
	}
	
	//Moet de speler zelf de intiële snelheden kunnen aanpassen?
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
	 * Return the minimal value for the horizontal velocity of Mazub.
	 * 
	 * @note   because the documentation has not revealed the actual value, it is
	 *         possible to change these values without having to notify
	 *         any clients of the class. At that time, we might have to
	 *         add new constructors by means of which we can initialize
	 *         new Mazubs and this does not break the contract with the
	 *         clients.
	 */
	@Basic
	public double getMinimumHorizontalVelocity(){
		return 0;	
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
		return 3;	
	}
	
	
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
	public boolean isValidVelocity(double velocity){
		return(velocity <= this.getMaximumHorizontalVelocity())
				&&(velocity>= this.getMinimumHorizontalVelocity());
		
	}
	
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
	/**
	 * Returns the x-coordinate of the position of Mazub.
	 */
	public int getPositionX(){
		return this.positionX;
	}
	
//beweegt het beeld mee met het poppetje, of steeds nieuwe beelden, is dit een goede oplossing?
// total programming?
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
	 * @note   The last postcondition is not really needed. Because of the interia
	 *         axiom, everything that is not touched upon in the specification
	 *         of a method keeps its value.
	 */
	public void setPositionX(int position){
		if ((position > MAX_POSITIONX)||(position< MIN_POSITIONX))
			position = ((position - MIN_POSITIONX) % (MAX_POSITIONX-MIN_POSITIONX+1)) 
			+ MIN_POSITIONX;
		this.positionX = position;
	}
	/**
	 * Returns the y-coordinate of the position of Mazub.
	 */
	public int getPositionY(){
		return this.positionY;
	}
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
		if ((position > MAX_POSITIONY)||(position< MIN_POSITIONY))
			position = ((position - MIN_POSITIONY) % (MAX_POSITIONY-MIN_POSITIONY+1)) 
			+ MIN_POSITIONY;
		this.positionY = position;
	}
	
	private int positionX;
	
	private int positionY;
	
	private static final int MIN_POSITIONX = 0;
	
	private static final int MAX_POSITIONX = 1023;
	
	private static final int MIN_POSITIONY = 0;
	
	private static final int MAX_POSITIONY = 767;
	
	/**
	 * A method that checks if the time duration is valid.
	 * @param time 
	 * 				time duration in seconds.
	 * @return
	 * 			returns true if it is a valid time.
	 */
	public boolean isValidTime(double time){
		return((time >=0)&&(time <= 0.2));
	}
	
	/**
	 * Calculates the distance traveled based on given velocity and time duration.
	 * 
	 * @param velocity
	 * @param time
	 * @return distance traveled.
	 * @thows IllegalArgumentException
	 * 			| ! isValidVelocity(velocity) || ! isValidTime(time)
	 */
	public double distanceTraveled(double velocity, double time)throws IllegalArgumentException{
		if(! isValidVelocity(velocity) || ! isValidTime(time))
			throw new IllegalArgumentException();
		if (velocity == this.getMaximumHorizontalVelocity())
			return (velocity *time);
		return (velocity * time + 0.5 * this.getHorizontalAccelaration());
	}
	//defensive programming
	// is dit enkel de horizontale velocity?
	/**
	 * This method updates the position and horizontal velocity of Mazub.
	 * @param positionX
	 * 					The x-coordinate of the position of Mazub.
	 * @param positionY
	 * 					The y-coordinate of the position of Mazub.
	 * @param velocity
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
	public void advanceTime( double velocity, double time) 
			throws IllegalArgumentException{
		if (! isValidTime(time) || ! isValidVelocity(velocity))
			throw new IllegalArgumentException();
		setPositionX((int)(this.getPositionX() + distanceTraveled(velocity, time)));
		setHorizontalVelocity(velocity + this.getHorizontalAccelaration()*time);
	}
	
	

}
