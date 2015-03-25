package jumpingalien.model;

import jumpingalien.util.Sprite;

public class Plant extends GameObject{

	protected Plant(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		super(pixelLeftX, pixelBottomY, sprites, 1);
	}

	@Override
	public void startMoveLeft() {
		this.setVelocity(INITIAL_VELOCITY);
		//this.setTimeLastLeft(this.getTime());
		
		
	}

	@Override
	public void startMoveRight() {
		this.setVelocity(-INITIAL_VELOCITY);
		//this.setTimeLastRight(this.getTime());
		
	}

	@Override
	public void endMoveLeft() {
		this.setVelocity(0);
		
	}

	@Override
	public void endMoveRight() {
		this.setVelocity(0);
		
	}
	
	private double velocity;
	private static final double INITIAL_VELOCITY = 0.5;

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	@Override
	public Sprite getCurrentSprite() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public void advanceTime(double horizontalVelocity, double verticalVelocity,
//			double deltaT) {
//		if((this.getTimeLastLeft() + 0.5 >= this.getTime())&&(this.getTimeLastRight() + 0.5 <= this.getTime())){
//			endMoveLeft();
//			startMoveRight();
//		}
//		else {
//			endMoveRight();
//			startMoveLeft();
//		}
//		
//	}

}
