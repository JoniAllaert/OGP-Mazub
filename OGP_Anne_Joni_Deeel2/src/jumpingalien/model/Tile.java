package jumpingalien.model;
/**
 * @invar	The length must be a divisor of both the maximum length 
 * 			in the x-direction as well as the one of the y-direction.
 * @invar	The tileX and tileY should always lie in the screen.
 * @author Joni
 *
 */
public class Tile {
	// feature is dit ook in ons deel een int? JA.
	/**
	 * 
	 * @param tileX
	 * @param tileY
	 * @param length
	 * @param feature
	 */
	public Tile(int tileX, int tileY, int length, int feature){
		this.tileX = tileX;
		this.tileY = tileY;
		this.length = length;
		this.geologicalFeature = feature;
	}
	
	private int tileX;
	public int getTileX() {
		return tileX;
	}
	public void setTileX(int tileX) {
		this.tileX = tileX;
	}
	public int getTileY() {
		return tileY;
	}
	public void setTileY(int tileY) {
		this.tileY = tileY;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getGeologicalFeature() {
		return geologicalFeature;
	}
	public void setGeologicalFeature(int geologicalFeature) {
		this.geologicalFeature = geologicalFeature;
	}

	private int tileY;
	private int length;
	private int geologicalFeature;
}
