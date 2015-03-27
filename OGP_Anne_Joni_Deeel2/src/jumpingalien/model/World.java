package jumpingalien.model;

import java.awt.*;
import java.util.ArrayList;

import jumpingalien.model.Tile;


public class World {

	public World (int tileSize, int nbTilesX, int nbTilesY,int visibleWindowWidth, int visibleWindowHeight, 
			int targetTileX, int targetTileY){
		for (int i = 1; i<= nbTilesX*nbTilesY; i++){
			tiles = new Tile(((i-1)%nbTilesX)*tileSize, (((i-1)%nbTilesX)*tileSize)/nbTilesX, tileSize, 0);
		}
	}
	private ArrayList<Tile> tiles= new ArrayList<Tile>();
}
