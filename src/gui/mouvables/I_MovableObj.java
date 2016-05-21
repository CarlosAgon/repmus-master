package gui.mouvables;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
	
public interface I_MovableObj {
	public void movableCallback (double x, double y);
	public Node getShape();
}
