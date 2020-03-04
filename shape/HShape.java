package shape;

/**
 * @author david
 * 
 * An "H shape" is drawn on the panel of a size provided by the client. The
 * H is formed by evenly dividing a rect Shape into 9 uniform squares, and 
 * coloring 7 of them (the remaining two are the color of the background).
 */

import java.awt.Color;
import java.awt.Graphics;

public class HShape extends AbstractShape {
	
	/**
	 * Creates an H shape
	 * @param x the x coordinate of the top left corner
	 * @param y the y coordinate of the top left corner 
	 * @param c the color of the shape (always green)
	 * @param size the size of the bounding box of the shape
	 */
	public HShape(int x, int y, Color c, int size) {
		super(x, y, size, c);
		this.childShapes = new Shape[7];
	}
	
	@Override
	// Draws the state of the Shape in the view panel.
	// As opposed to FibonacciSquare, it only draws the highest 
	// level rather than all levels.
	public void draw(Graphics g) {
		
		if (this.childShapes[0] != null) {
			for (int i = 0; i < 7; i++) {
				childShapes[i].draw(g);
			}
		}
		else {
		
		int x = this.x;
		int y = this.y;
		
		g.setColor(color);
		
		int line  = this.size /3;
		
		g.setColor(Color.GREEN);
		
		g.fillRect(x, y, line, line);
		g.fillRect(x, y + line, line, line);
		g.fillRect(x, y + line * 2, line, line);
		
		g.fillRect(x + line, y + line, line, line);
		
		g.fillRect(x + line * 2, y, line, line);
		g.fillRect(x + line * 2, y + line, line, line);
		g.fillRect(x + line * 2, y + line * 2, line, line);
		}
	}
	
	/**
	 * Initializes the seven shapes in this shape's childShapes array, 
	 * if the level is lower than 5. 
	 */
	public boolean createChildren() {
		
		if  (this.level > 5) {
			return false;
		} else {
			int x = this.x;
			int y = this.y;
			int line = this.size / 3;
		
			this.childShapes[0] = new HShape(x, y, Color.GREEN, line);
			this.childShapes[1] = new HShape(x, y + line, Color.GREEN, line);
			this.childShapes[2] = new HShape(x, y + line * 2, Color.GREEN, line);
		
			this.childShapes[3] = new HShape(x + line, y + line, Color.GREEN, line);
		
			this.childShapes[4] = new HShape(x + line * 2, y, Color.GREEN, line);
			this.childShapes[5] = new HShape(x + line * 2, y + line, Color.GREEN, line);
			this.childShapes[6] = new HShape(x + line * 2, y + line * 2, Color.GREEN, line);
			return true;
		}
	}
	
	
	// sets to null all the shapes in this shape's 
	// childShapes array.
	public boolean destroyChildren() {
		if (this.level == 1) {
			return false;
		} else {
			this.childShapes[0] = null;
			this.childShapes[1] = null;
			this.childShapes[2] = null;
			this.childShapes[3] = null;
			this.childShapes[4] = null;
			this.childShapes[5] = null;
			this.childShapes[6] = null;
			return true;
		}
	}
	

}