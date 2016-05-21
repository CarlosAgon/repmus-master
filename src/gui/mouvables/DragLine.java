package gui.mouvables;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class DragLine implements I_MovableObj{
	double firstX;
    double firstY;
    Line theline;
    java.awt.Rectangle repons;

    public DragLine(double x, double y) {
    	theline = new Line();
    	theline.setStroke(Color.BLUE);
        theline.setStartX(x);
    	theline.setStartY(y);
        firstX = x;
        firstY = y;
    }
    
    
    public void movableCallback (double x, double y) {
    	theline.setEndX(x);
    	theline.setEndY(y);
    }
    
    public java.awt.Rectangle getFinalRect () {
    	return repons;
   }


	@Override
	public Node getShape() {
		return theline;
	}
    
}
