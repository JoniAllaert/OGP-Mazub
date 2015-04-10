package jumpingalien.model;

import java.util.ArrayList;

import jumpingalien.util.Sprite;
// TODO Ik denk eigenlijk dat dit niet echt een game object meer is na de uitleg van de assistent?
public class School{
	
	
	
	private ArrayList<Slime> slimes = new ArrayList<Slime>();

	public void advanceTime(double deltaT){
			for(Slime slime: slimes)
				slime.advanceTime(slime.getHorizontalVelocity(), deltaT);
	}
	
}
