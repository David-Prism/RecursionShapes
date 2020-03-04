package shape;

/**
 * @author david
 * 
 * Creates a JPanel and interacts with DrawingModel in order to get
 * the Shape objects contained within it and draw them in the panel.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class DrawingView extends JPanel implements View {
	
	/**
	 * The model created in the Main class is passed to this class
	 * so it can act on the shapes in the model.
	 */
	private DrawingModel model;

	public DrawingView() {
		setBackground(Color.WHITE);
	}

	@Override
	public void update(DrawingModel model) {
		this.model = model;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (model != null) {
			List<Shape> shapes = model.shapes;
			for (Shape sh : shapes) {
				sh.draw(g);
			}
		}
	}

}