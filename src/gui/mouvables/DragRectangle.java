package gui.mouvables;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DragRectangle implements I_MovableObj{
	double firstX;
    double firstY;
    java.awt.Rectangle repons;
    public Rectangle r;
	
    public  DragRectangle(double x, double y) {
        r = new Rectangle(x,y, 1 , 1);
        r.setFill(Color.web("blue", 0.1));
        r.setStroke(Color.BLUE);
        firstX = x;
        firstY = y;
    }
    
    public java.awt.Rectangle getFinalRect () {
    	return repons;
   }
    
    public void movableCallback (double x, double y) {
    	r.setX(Math.min(x, firstX));
    	r.setY(Math.min(y, firstY));
    	r.setWidth(Math.abs(x - firstX));
    	r.setHeight(Math.abs(y- firstY));
    	
    	/*repons.setBounds((int) ((Rectangle) rect).getX(), 
    			(int) ((Rectangle) rect).getY(), 
    			(int) ((Rectangle) rect).getWidth(), 
    			(int) ((Rectangle) rect).getHeight());*/
    }


	@Override
	public Rectangle getShape() {
		return r;
	}

}
