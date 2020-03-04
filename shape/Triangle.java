package shape;

/**
 * @author david
 * 
 * Constructs a Sierpinski triangle.
 */

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Graphics;
import java.lang.Math;

public class Triangle extends AbstractShape {
	
	private int x1;
	private int x2;
	private int x3;
	private int[] xs = new int[3];
	
	private int y1;
	private int y2;
	private int y3;
	private int[] ys = new int[3];
	
	private int height;
	
	private Polygon p;
	
	/**
	 * 
	 * @param x the x-coordinate of the top left
	 * @param y the y-coordinate of the top left
	 * @param c the color of the shape (always blue)
	 * @param size the size of the shape
	 */
	public Triangle(int x, int y, Color c, int size) {
		super(x, y, size, c);
		this.childShapes = new Shape[3];
		
		/*
		 * The height of the triangle is determined using a modified
		 * form of the triangle height formula (the value of the 
		 * square root of 3 is approximated).
		 */
		x1 = x;
		x2 = x + size;
		x3 = x + size / 2;
		y1 = y + size;
		y2 = y + size;
		float f = (float)(0.8660254 * size);
		height = Math.round(f);
		y3 = y + size - height;
		
		xs[0] = x1;
		xs[1] = x2;
		xs[2] = x3;
		
		ys[0] = y1;
		ys[1] = y2;
		ys[2] = y3;
		
		p = new Polygon(xs, ys, 3);
	}
	
	@Override
	// Draws the state of the Shape in the view panel.
	public void draw(Graphics g) {
		
		if (this.childShapes[0] != null) {
			for (int i = 0; i < 3; i++) {
				childShapes[i].draw(g);
			}
		}
		else {
		
		g.setColor(color);
		g.fillPolygon(p);
		}
	}
	
	// initializes the shape in this shape's childShapes array.
	public boolean createChildren() {
		if (this.level > 6) {
			return false;
		} else {
			this.childShapes[0] = new Triangle(x, y  + (size - (height / 2)), Color.BLUE, size / 2);
			
			this.childShapes[1] = new Triangle(x + (size /2), y + (size - (height / 2)), Color.BLUE, size / 2);
			
			this.childShapes[2] = new Triangle(x + (size / 4), y + (size - height), Color.BLUE, size / 2);
			return true;
		}
	}
	
	// Sets to null the shape object in this shape's childShapes array.
	public boolean destroyChildren() {
		if (this.level == 1) {
			return false;
		} else {
			this.childShapes[0] = null;
			this.childShapes[1] = null;
			this.childShapes[2] = null;
			return true;
		}
	}

}