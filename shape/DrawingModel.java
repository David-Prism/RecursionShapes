package shape;

/**
 * @author david
 * 
 * Keeps the state of all Shape objects displayed by the viewer in an 
 * ArrayList called "shapes" and of all View objects used by DrawingView
 * in an ArrayList called "views". Also keeps the state of the highest 
 * level of each shape.
 * 
 * Provides several methods utilized by the controller.
 */

import java.util.ArrayList;
import java.util.List;

public class DrawingModel {
	protected List<Shape> shapes = new ArrayList<Shape>();
	protected List<View> views = new ArrayList<View>();

	public int fibHighestLevel = 1;
	public int hShapeHighestLevel = 1;
	public int triangleHighestLevel = 1;
	
	/**
	 * 
	 * @param v is the view object that is added to the "views" array list.
	 */
	public void addView(View v) {
		views.add(v);
		v.update(this);
	}

	// Updates all view objects in "views".
	public void updateAll() {
		for (View v : views) {
			v.update(this);
		}
	}

	/**
	 * 
	 * @param s is added to the "shapes" array list and 
	 * then all view objects in "views" are updated.
	 */
	public void addShape(Shape s) {
		shapes.add(s);
		updateAll();
	}

	/**
	 * This is the function of the reset button. When the button is 
	 * clicked, all shapes are reset to level one, the level at which 
	 * a shape cannot be decreased further and the level that all shapes
	 * start at when the program is run.
	 */
	public void reset() {
		if (shapes.get(0).getLevel() > 1) {
			while (shapes.get(0).getLevel() > 1) {
				shapes.get(0).removeLevel(1, this.fibHighestLevel);
				shapes.get(0).decreaseLevel();
				this.fibHighestLevel = this.fibHighestLevel - 1;
			}
		}
		if (shapes.get(1).getLevel() > 1) {
			while (shapes.get(1).getLevel() > 1) {
				shapes.get(1).removeLevel(1, this.hShapeHighestLevel);
				shapes.get(1).decreaseLevel();
				this.hShapeHighestLevel = this.hShapeHighestLevel - 1;
			}
		}
		if (shapes.get(2).getLevel() > 1) {
			while (shapes.get(2).getLevel() > 1) {
				shapes.get(2).removeLevel(1, this.triangleHighestLevel);
				shapes.get(2).decreaseLevel();
				this.triangleHighestLevel = this.triangleHighestLevel - 1;
			}
		}
		updateAll();
	}
	
	/**
	 * 
	 * @param x provides the x-coordinate of a mouse click to the controller
	 * so it can decide which shape to increase.
	 * 
	 * @return a boolean provided to the controller to let it know whether 
	 * a shape could be increased.
	 */
	public boolean addLevel(int x) {
		if (x < 390) {
			if (shapes.get(0).getLevel() < 8) {
				shapes.get(0).addLevel();
				updateAll();
				return true;
			} else {
				return false;
			}
		} else if (x > 390 && x < 633) {
			if (shapes.get(1).getLevel() < 5) {
				shapes.get(1).addLevel();
				updateAll();
				return true;
			} else {
				return false;
			}
		} else {
			if (shapes.get(2).getLevel() < 6) {
				shapes.get(2).addLevel();
				updateAll();
				return true;
			} else {
				return false;
			}
		}	
	}
	
	/**
	 * 
	 * @param x provides the x-coordinate of a mouse click to the controller
	 * so it can decide which shape to decrease.
	 * 
	 * @return a boolean provided to the controller to let it know whether 
	 * a shape could be decreased.
	 */
	public boolean removeLevel(int x) {
		if (x < 390) {
			if (shapes.get(0).getLevel() > 1) {
				shapes.get(0).removeLevel(1, this.fibHighestLevel);
				shapes.get(0).decreaseLevel();
				updateAll();
				return true;
			} else {
				return false;
			}
		} else if (x > 390 && x < 633) {
			if (shapes.get(1).getLevel() > 1) {
				shapes.get(1).removeLevel(1, this.hShapeHighestLevel);
				shapes.get(1).decreaseLevel();
				updateAll();
				return true;
			} else {
				return false;
			}
		} else {
			if (shapes.get(2).getLevel() > 1) {
				shapes.get(2).removeLevel(1, this.triangleHighestLevel);
				shapes.get(2).decreaseLevel();
				updateAll();
				return true;
			} else {
				return false;
			}
		}
	}
}