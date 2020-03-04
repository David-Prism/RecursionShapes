package shape;

/**
 * David Prismantas
 * 
 * Basic interface for Shape objects.
 * 
 */
import java.awt.Graphics;

public interface Shape {
	void draw(Graphics g);

	boolean addLevel();
	boolean removeLevel(int n, int level);
	boolean createChildren();
	boolean destroyChildren();
	void increaseLevel();
	void decreaseLevel();
	int getLevel();
}