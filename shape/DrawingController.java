package shape;


import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * 
 * @author david
 * 
 * A Class that provides a UI for three modes of input: an
 * incrementer, a decrementer, and a reset button.
 *
 */
public class DrawingController implements ActionListener, MouseListener {

	private DrawingModel model;
	private TextViewer viewer;

	private boolean increase = true;
	

	/**
	 * Constructs a DrawingController given the model it is connected to
	 * 
	 * @param model the DrawingModel of the controller
	 * 
	 * @param viewer the TextViewer of the controller
	 */
	public DrawingController(DrawingModel model, TextViewer viewer) {
		this.model = model;
		this.viewer = viewer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("RESET")) {
			model.reset();
		} else if (e.getActionCommand().equals("Increase")) {
			increase = true;
		} else if (e.getActionCommand().equals("Decrease")) {
			increase = false;
		}
	}
	
	/**
	 * Allows the user to click on one of the three shapes displayed in the
	 * frame in order to increase the shape's level, decrease it, or reset
	 * the level of all three shapes to their original state.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		boolean success;
		if (increase) {
			success = model.addLevel(e.getX());
			if (success) {
				if (e.getX() < 390) { 
					model.fibHighestLevel += 1;
					
				} else if (e.getX() > 390 && e.getX() < 633){
					model.hShapeHighestLevel += 1; 
				} else {
					model.triangleHighestLevel += 1;	
				}
			}
			if (!success) {
				// if the shape has reached its maximum level, the user is given a message
				JOptionPane.showMessageDialog(null, "Message", "Can't add another level", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			success = model.removeLevel(e.getX());
			if (success) {
				if (e.getX() < 390) {
					model.fibHighestLevel -= 1;
				} else if (e.getX() > 390 && e.getX() < 633){
					model.hShapeHighestLevel -= 1; 	
				} else {
					model.triangleHighestLevel -= 1;
				}
			}
			if (!success) {
				// if the shape is at its starting level (level one), the user is given a message
				JOptionPane.showMessageDialog(null, "Message", "Can't remove the base level", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (success && e.getX() < 390) {
			viewer.update(model, 0);
		} else if (success && e.getX() > 390 && e.getX() < 633) {
			viewer.update(model, 1);
		} else if (success){
			viewer.update(model, 2);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
