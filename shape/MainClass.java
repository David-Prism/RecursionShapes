package shape;

/**
 * @author david
 */

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class MainClass {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			buildGUI();
		});
	}
	
	/**
	 *  Builds the GUI with a JFrame, subcomponents of that class, and
	 *  some of the classes defined in this package.
	 */
	private static void buildGUI() {

		// Builds a JFrame that is 1000 x 400 pixels
		// Stops the program run when the Frame is closed.
		JFrame frame = new JFrame();
		
		frame.setSize(1000, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Builds the panel using an instance of DrawingView.
		DrawingView panel = new DrawingView();
		frame.add(panel);

		frame.setVisible(true);

		// Create the DrawingModel
		DrawingModel model = new DrawingModel();
		model.addView(panel);

		// draws a FibonacciSquare object
		Shape f = new FibonacciSquare(120, 130, Color.BLACK, 1, 1);
		model.addShape(f);

		// Draws an HShape object.
		Shape h = new HShape(390, 40, Color.GREEN, 243);  //size was 594, x was 600
		model.addShape(h);

		// Draws a Triangle object.
		Shape t = new Triangle(680, 30, Color.BLUE, 250);
		model.addShape(t);

		/**
		 *  Places two JRadio buttons at the bottom of the frame to allow the user
		 *  to increase and decrease the level of the shapes, and puts them in 
		 *  a group.
		 */
		JRadioButton inc = new JRadioButton("Increase");
		JRadioButton dec = new JRadioButton("Decrease");
		ButtonGroup group = new ButtonGroup();
		inc.setSelected(true);
		
		group.add(inc);
		group.add(dec);
		
		// Reset button, see the DrawingModel class for the
		// function of the button.
		JButton res = new JButton("RESET");
		JPanel southPanel = new JPanel();
		southPanel.add(inc);
		southPanel.add(dec);

		southPanel.add(res);
		southPanel.setBackground(Color.WHITE);
		frame.add(southPanel, BorderLayout.SOUTH);

		// Create the DrawingController
		TextViewer viewer = new TextViewer();
		DrawingController controller = new DrawingController(model, viewer);
		inc.addActionListener(controller);
		dec.addActionListener(controller);
		res.addActionListener(controller);

		panel.addMouseListener(controller);

	}
}
