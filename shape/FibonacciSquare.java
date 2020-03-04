package shape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author david
 *
 *         Creates a 90 degree arc within a square of a size that is equal to a
 *         number in the Fibonacci sequence multiplied by a factor of ten.
 */
public class FibonacciSquare extends AbstractShape {

	private int arcx;
	private int arcy;
	private int n;
	private int quadrant;
	private int arc;
	
	/**
	 * 
	 * @param x the x-coordinate of the upper left of the shape.
	 * @param y the y-coordinate of the upper-left of the shape.
	 * @param c the color of the shape (always black).
	 * @param quadrant is an integer from one to four, it is used to 
	 * determine how to orient the arc so it displays properly.
	 * 
	 * @param n is the nth number in the Fibonacci sequence.
	 */
	public FibonacciSquare(int x, int y, Color c, int quadrant, int n) {
		super(x, y, fib(n) * 10, c);
		this.quadrant = quadrant;
		this.n = n;
		this.arc = arcDegree(quadrant);
		this.childShapes = new Shape[1];
	}
	
	/**
	 * @param n is the nth number in the Fibonacci sequence.
	 * 
	 * @return an integer corresponding to the value of the 
	 * nth number in the Fibonacci sequence.
	 */
	private static int fib(int n) {
		int f1 = 0;
		int f2 = 1;
		int fib = 0;

		if (n == 1) {
			return 1;
		} else {

			for (int i = 2; i <= n; i++) {
				fib = f1 + f2;
				f1 = f2;
				f2 = fib;
			}
			return fib;
		}
	}

	@Override
	// If the array of "child shapes" in this shape have not yet
	// been initialized, it draws this shape. Otherwise it recursively 
	// travels to the level at which that is the case.
	public void draw(Graphics g) {

		if (this.childShapes[0] != null) {
			childShapes[0].draw(g);
		}
		g.setColor(color);
		g.drawRect(x, y, size, size);
		g.drawArc(arcx, arcy, size * 2, size * 2, arc, 90);
	}
	
	/**
	 * 
	 * @param arcQuad is used to determine how to orient the bounding
	 * box of the arc so that the arc fits properly in the square
	 * that has been drawn
	 * 
	 * @return the value of the start angle of the arc.
	 */
	public int arcDegree(int arcQuad) {
		if (arcQuad == 1) {
			this.arcx = x - size;
			this.arcy = y;
			return 0;
		} else if (arcQuad == 2) {
			this.arcx = x;
			this.arcy = y;
			return 90;
		} else if (arcQuad == 3) {
			this.arcx = x;
			this.arcy = y - size;
			return 180;
		} else if (arcQuad == 4) {
			this.arcx = x - size;
			this.arcy = y - size;
			return 270;
		}
		return 0;
	}
	
	/**
	 * 
	 * @param n is the nth number in the Fib sequence.
	 * 
	 * @return is the value of the quadrant for the 
	 * arc, determined by n.
	 */
	public int getQuadrant(int n) {
		if (n % 4 == 0) {
			return 4;
		} else {
			return n % 4;
		}
	}
	
	/**
	 * 
	 * @return the value of the x-coordinate for the "child shape" 
	 * of this shape, determinded using the quadrant for this shape
	 * along with its x-coordinate.
	 */
	public int getChildX() {

		if (this.quadrant == 1) {
			return this.x - fib((this.n) + 1) * 10;
		} else if (this.quadrant == 2) {
			return this.x;
		} else if (this.quadrant == 3) {
			return this.x + this.size;
		} else {
			return this.x - fib((this.n) - 1) * 10;
		}
	}
	
	/**
	 * 
	 * @return the value of the x-coordinate for the "child shape" 
	 * of this shape, determinded using the quadrant for this shape
	 * along with its y-coordinate.
	 */
	public int getChildY() {
		if (this.quadrant == 1) {
			return this.y;
		} else if (this.quadrant == 2) {
			return this.y + this.size;
		} else if (this.quadrant == 3) {
			return this.y - fib((this.n) - 1) * 10;
		} else {
			return this.y - fib((this.n) + 1) * 10;
		}
	}
	
	// initializes the shape in this shape's childShapes array.
	public boolean createChildren() {
		if (this.level > 8) {
			return false;
		} else {
			this.childShapes[0] = new FibonacciSquare(this.getChildX(), this.getChildY(), Color.BLACK,
					this.getQuadrant(this.quadrant + 1), this.n + 1);
//			System.out.println(this.getClass());
//			System.out.println(this.getLevel());
			return true;
		}
	}
	
	// Sets to null the shape object in this shape's childShapes array.
	public boolean destroyChildren() {
		if (this.level == 1) {
			return false;
		} else {
			this.childShapes[0] = null;
			return true;
		}
	}
}