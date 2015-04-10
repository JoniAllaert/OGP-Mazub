package jumpingalien.model;

import java.awt.*;
import java.util.ArrayList;

import jumpingalien.model.Tile;
import jumpingalien.model.GameObject;
import jumpingalien.util.ModelException;


public class World {
// TODO ik heb het met add gedaan want eigenlijk is onze array leeg en deze methode voegt toe aan het einde van de lijst.
	public World (int tileSize, int nbTilesX, int nbTilesY,int visibleWindowWidth, int visibleWindowHeight, 
			int targetTileX, int targetTileY){
		for (int i = 1; i<= nbTilesX*nbTilesY; i++){
			tiles.add(new Tile(((i-1)%nbTilesX)*tileSize, (((i-1)%nbTilesX)*tileSize)/nbTilesX, 0, this));
		}
		setxMax( nbTilesX*tileSize);
		setyMax( nbTilesY*tileSize);
		setVisibleWindowHeight(visibleWindowHeight);
		setVisibleWindowWidth(visibleWindowWidth);
		setTargetTileX(targetTileX);
		setTargetTileY(targetTileY);
		this.tileSize = tileSize;
		setNbTilesX(nbTilesX);
		setNbTilesY(nbTilesY);
	}
	
	private int nbTilesX;
	public int getNbTilesX() {
		return nbTilesX;
	}

	public void setNbTilesX(int nbTilesX) {
		this.nbTilesX = nbTilesX;
	}

	public int getNbTilesY() {
		return nbTilesY;
	}

	public void setNbTilesY(int nbTilesY) {
		this.nbTilesY = nbTilesY;
	}

	private int nbTilesY;
	
	private final int tileSize;
	
	public final int getTileSize() {
		return tileSize;
	}

	public int targetTileX;
	public int getTargetTileX() {
		return targetTileX;
	}

	public void setTargetTileX(int targetTileX) {
		this.targetTileX = targetTileX;
	}

	public int getTargetTileY() {
		return targetTileY;
	}

	public void setTargetTileY(int targetTileY) {
		this.targetTileY = targetTileY;
	}

	public int targetTileY;
	
	public boolean didPlayerWin(){
		if(mazub.getPositionX() == getTargetTileX() && mazub.getPositionY() == getTargetTileY() && isGameOver() == true)
			return true;
		return false;
	}
	
	public boolean isGameOver(){
		if((mazub.getPositionX() == getTargetTileX() && mazub.getPositionY() == getTargetTileY())||(mazub.isTerminated() == true))
			return true;
		return false;
	}
	
	public int getVisibleWindowWidth() {
		return visibleWindowWidth;
	}

	private void setVisibleWindowWidth(int visibleWindowWidth) throws IllegalArgumentException{
		if(visibleWindowWidth< 400+ mazub.getWidth() || visibleWindowWidth > getxMax())
			throw new IllegalArgumentException();
		this.visibleWindowWidth = visibleWindowWidth;
	}

	public int getVisibleWindowHeight() {
		return visibleWindowHeight;
	}

	private void setVisibleWindowHeight(int visibleWindowHeight) throws IllegalArgumentException{
		if(visibleWindowHeight< 400+ mazub.getHeight() || visibleWindowHeight > getyMax())
			throw new IllegalArgumentException();
		this.visibleWindowHeight = visibleWindowHeight;
	}

	//TODO: misschien niet private
	private int visibleWindowWidth;
	private int visibleWindowHeight;
	
	public int[] getVisibleWindow()throws IllegalStateException{
		int[] array = {getVisibleWindowLeft(), getVisibleWindowBottom(), getVisibleWindowRight(), getVisibleWindowTop()};
		if(!isValidVisibleWindow(array))
			throw new IllegalStateException();
		else if(getVisibleWindowLeft()<0 && getVisibleWindowBottom() < 0){
			array[1] = 0;
			array[2] = 0;
			array[3] = getVisibleWindowRight() -getVisibleWindowLeft();
			array[4] = getVisibleWindowTop() - getVisibleWindowBottom();
			return array;
		}
		else if(getVisibleWindowLeft()<0 && getVisibleWindowTop() > getyMax()){
			array[1] = 0;
			array[2] = getVisibleWindowBottom() - (getVisibleWindowTop() - getyMax());
			array[3] = getVisibleWindowRight() - getVisibleWindowLeft();
			array[4] = getyMax();
			return array;
		}
		else if(getVisibleWindowRight()>getxMax() && getVisibleWindowTop() > getyMax()){
			array[1] = getVisibleWindowLeft() - (getVisibleWindowRight()-getxMax());
			array[2] = getVisibleWindowBottom() - (getVisibleWindowTop() - getyMax());
			array[3] = getxMax();
			array[4] = getyMax();
			return array;
		}
		else if(getVisibleWindowRight()>getxMax() && getVisibleWindowBottom() < 0){
			array[1] = getVisibleWindowLeft() - (getVisibleWindowRight()-getxMax());
			array[2] = 0;
			array[3] = getxMax();
			array[4] = getVisibleWindowTop() - getVisibleWindowBottom();
			return array;
		}
		else if( getVisibleWindowBottom() < 0){
			array[1] = getVisibleWindowLeft() ;
			array[2] = 0;
			array[3] = getVisibleWindowRight();
			array[4] = getVisibleWindowTop() - getVisibleWindowBottom();
			return array;
		}
		else if(getVisibleWindowLeft()<0 ){
			array[1] = 0;
			array[2] = getVisibleWindowBottom();
			array[3] = getVisibleWindowRight() -getVisibleWindowLeft();
			array[4] = getVisibleWindowTop();
			return array;
		}
		
		else if( getVisibleWindowTop() > getyMax()){
			array[1] = getVisibleWindowLeft();
			array[2] = getVisibleWindowBottom() - (getVisibleWindowTop() - getyMax());
			array[3] = getVisibleWindowRight();
			array[4] = getyMax();
			return array;
		}
		else if(getVisibleWindowRight()>getxMax()){
			array[1] = getVisibleWindowLeft() - (getVisibleWindowRight()-getxMax());
			array[2] = getVisibleWindowBottom();
			array[3] = getxMax();
			array[4] = getVisibleWindowTop();
			return array;
		}
		else return array;
		
		
		
	}
	
	
	public int getVisibleWindowLeft()throws IllegalStateException{
		return (int) (mazub.getPositionX() - (getVisibleWindowWidth()-mazub.getWidth())/2);
	}
	
	public int getVisibleWindowBottom(){
		return (int) (mazub.getPositionY() - (getVisibleWindowHeight()-mazub.getHeight())/2);
	}
	public int getVisibleWindowRight(){
		return (int) (mazub.getPositionX() + mazub.getWidth()/2 + getVisibleWindowWidth()/2);
	}
	public int getVisibleWindowTop(){
		return (int) (mazub.getPositionY() + mazub.getHeight()/2 + getVisibleWindowHeight()/2);

	}
	
	public boolean isValidVisibleWindow(int[] array){
		if(array[1] > -getVisibleWindowWidth() && array[2] > -getVisibleWindowHeight() 
				&& array[3]< getxMax()+getVisibleWindowWidth() && array[4]< getyMax() +getVisibleWindowHeight())
			return true;
		else return false;
	}
	
	
	
	private int xMax;
	
	public int getxMax() {
		return xMax;
	}

	private void setxMax(int xMax) {
		this.xMax = xMax;
	}

	public int getyMax() {
		return yMax;
	}

	private void setyMax(int yMax) {
		this.yMax = yMax;
	}

	private int yMax;
	
	//hier moeten we denk ik clonen.
	public ArrayList<Tile> getTiles() {
		return (ArrayList<Tile>) tiles.clone();
	}

	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	
	public int[] getBottomLeftPixelOfTile(int tileX, int tileY){
		int[] array = {tileX, tileY};
		return array;
		//TODO
	}
	
	public int[][] getTilePositions(int pixelLeft, int pixelBottom, int pixelRight, int pixelTop)throws IllegalArgumentException{ 
			if(!tileExistsOn(pixelLeft, pixelBottom))
				throw new IllegalArgumentException();
			int nbTilesXractangle= (pixelRight - pixelLeft)/getTileSize();
			int nbTilesYractangle= (pixelTop - pixelBottom)/getTileSize();
			int [][] array = new int [nbTilesXractangle*nbTilesYractangle][2];
			for (int i = 1; i<= nbTilesXractangle*nbTilesYractangle; i++){
				array[i][1] =  pixelLeft + ((i-1)%nbTilesXractangle)*getTileSize();
				array[i][2] =  pixelBottom + (((i-1)%nbTilesXractangle)*getTileSize())/nbTilesXractangle;
				}			
			return array;
	}
	
	public void setGeologicalFeature(int tileX, int tileY, int tileType){
		for(int i=1 ; i<= tiles.size() ; i++)
			if(tiles.get(i).getTileX() == tileX && tiles.get(i).getTileY()==tileY){
				tiles.remove(i);
				tiles.add(i, new Tile(tileX, tileY, tileType, this));
			}
	}
	
	public int getGeologicalFeature(int pixelX, int pixelY)
			throws IllegalArgumentException{	
			if(!tileExistsOn(pixelX,pixelY))
				throw new IllegalArgumentException();
			else	
			return tiles.get(findTile(pixelX, pixelY)).getGeologicalFeature();
	}
		
	public boolean tileExistsOn(int pixelX, int pixelY){
		if(findTile(pixelX,pixelY) == -1) return false;
		else return true;
	}
	
	public int findTile(int pixelX, int pixelY){
		boolean a =true;
		int index = -1;
		while(a==true){
			for(int i=1 ; i<= tiles.size() ; i++){
				if(tiles.get(i).getTileX() == pixelX && tiles.get(i).getTileY()==pixelY){
					index =i;
					a=false;
				}
			}
		}
		return index;
	}
	

	private ArrayList<Tile> tiles= new ArrayList<Tile>();
	
	
	// canHaveAsTile
	// changeTypeOfTile ( soort van remove and add tile tegelijkertijd, omdat je geen nieuwe tiles kunt toevoegen.)
	
	
	private ArrayList<Plant> plants= new ArrayList<Plant>();
	
	public ArrayList<Plant> getPlants() {
		return (ArrayList<Plant>) plants.clone();
	}


	public ArrayList<Shark> getSharks() {
		return (ArrayList<Shark>) sharks.clone();
	}


	public ArrayList<Slime> getSlimes() {
		return (ArrayList<Slime>) slimes.clone();
	}


	public Mazub getMazub() {
		return new Mazub(mazub.getPositionX(), mazub.getPositionY(), mazub.getSprites());
	}

	public void addPlant(Plant plant){
		if(getNumberOfGameObjects() >= 100)
			return;
		else{ 
			plants.add(plant);
			setNumberOfGameObjects(getNumberOfGameObjects() + 1);}
		
	}
	
	private ArrayList<Shark> sharks = new ArrayList<Shark>();
	
	public void addShark(Shark shark){
		if(getNumberOfGameObjects() >= 100)
			return;
		else{ 
			sharks.add(shark);
			setNumberOfGameObjects(getNumberOfGameObjects() + 1);}
		
	}
	
	private ArrayList<Slime> slimes = new ArrayList<>();
	
	public void addSlime(Slime slime){
		if(getNumberOfGameObjects() >= 100)
			return;
		else{ 
			slimes.add(slime);
			setNumberOfGameObjects(getNumberOfGameObjects() + 1);}
		
	}
	
	private Mazub mazub;
	
	
	
	private int numberOfGameObjects;
	
	
	public int getNumberOfGameObjects() {
		return numberOfGameObjects;
	}

	public void setNumberOfGameObjects(int numberOfGameObjects){
		this.numberOfGameObjects = numberOfGameObjects;
	}

	public void setMazub(Mazub mazub){
		this.mazub = mazub;
	}
	
	// canHaveAsGameObject (mag maar een mazub hebben,..)
	// addGameObject
	
	public void advanceTime(double deltaT){
		for(Plant plant : plants)
			plant.advanceTime(plant.getVelocity(), deltaT);
		for(Shark shark : sharks)
			shark.advanceTime(shark.getHorizontalVelocity(), shark.getVerticalVelocity(),deltaT);
		for(Slime slime: slimes)
			slime.advanceTime(slime.getHorizontalVelocity(), deltaT);
		mazub.advanceTime(mazub.getHorizontalVelocity(), mazub.getVerticalVelocity(), deltaT);
	}
	

}
