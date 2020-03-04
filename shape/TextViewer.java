package shape;


/**
 * 
 * @author david
 *
 * A basic class for printing the information about the newest shape(s)
 * added to the model using AbtractShape's toString.
 */
public class TextViewer implements View {
	
	//private DrawingModel model;
	
	public TextViewer() {
		//this.model = model;
	}

	public void update(DrawingModel model, int i) {
		if (model != null) {
			Shape s = model.shapes.get(i);
			System.out.println(s.toString());
		}
	}
	
	public void update(DrawingModel model) {
		
	}
}
