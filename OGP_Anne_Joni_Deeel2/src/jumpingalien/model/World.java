package jumpingalien.model;

import java.awt.*;
import java.util.ArrayList;

import jumpingalien.model.Tile;
import jumpingalien.model.GameObject;


public class World {

	public World (int tileSize, int nbTilesX, int nbTilesY,int visibleWindowWidth, int visibleWindowHeight, 
			int targetTileX, int targetTileY){
		for (int i = 1; i<= nbTilesX*nbTilesY; i++){
			tiles = new Tile(((i-1) % nbTilesX)*tileSize, (((i-1)%nbTilesX)*tileSize)/nbTilesX, tileSize, 0);
		}
	}
	
	//hier moeten we denk ik clonen.
	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}

	private ArrayList<Tile> tiles= new ArrayList<Tile>();
	
	
	// canHaveAsTile
	// changeTypeOfTile ( soort van remove and add tile tegelijkertijd, omdat je geen nieuwe tiles kunt toevoegen.)
	
	
	//hier moeten we denk ik clonen.
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(ArrayList<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}
	
	private ArrayList<GameObject> gameObjects= new ArrayList<GameObject>();
	
	// canHaveAsGameObject (mag maar een mazub hebben,..)
	// addGameObject

}
