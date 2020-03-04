package shape;

/**
 * @author david
 * 
 * An abstract class inherited by three sublclasses. Has four fieldsj
 * common to all subclasses and a constructor.
 */

import java.awt.Color;

public abstract class AbstractShape implements Shape {

	protected Shape[] childShapes;

	protected int level = 1;

	protected int x;
	protected int y;
	protected Color color;
	protected int size;
	
	/**
	 * 
	 * @param x (x coordinate, top left)
	 * @param y (y coordinate, top left)
	 * @param size is the size of the bounding box of 
	 * the shape
	 * @param color is the shape's color
	 */
	public AbstractShape(int x, int y, int size, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.size = size;
	}
	
	// Increases the shape's level by 1.
	public boolean addLevel() {
		boolean success = false;
		if (this.childShapes[0] != null) {
			for (Shape s : childShapes) {
				success = s.addLevel();
			}
		} else {
			success = createChildren();
		}
		if (success) {
			level++;
		}
		return success;
	}
	
	/**
	 * @param n is the variable that is incremented until it is equal to 
	 * one less that the level variable so as to get to the right place in 
	 * the nested shape array field within each shape.
	 * 
	 * @param level is the current level of the shape.
	 */
	public boolean removeLevel(int n, int level) {
		boolean success = false;
		if (this.childShapes[0] != null) {
			if (n != level - 1) {
				for (Shape s : childShapes) {
					success = s.removeLevel(n + 1, level);
				}
			} else {
				success = destroyChildren();
			}
		}
		if (success) {
			level--;
		}
		return success;
	}
//	public boolean removeLevel() {
//		boolean success = false;
//		if (this.childShapes[0] != null) {
//			Shape s = childShapes[0];
//		}
//	}
	
	@Override
	public String toString() {
		String s = 	"Shape: " + this.getClass() + ",   coordinates: " + 
					 this.x + ", " + this.y + ",   color: " + color.toString() + 
					 ",   level: " + this.level;
		return s;
	}
	public int getLevel() {
		return this.level;
	}
	

	public void increaseLevel() {
		this.level = this.level + 1;
	}

	public void decreaseLevel() {
		this.level = this.level - 1;
	}
}
