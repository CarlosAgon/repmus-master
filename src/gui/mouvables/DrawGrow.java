package gui.mouvables;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DrawGrow implements I_MovableObj{
	double firstX;
    double firstY;
    public Rectangle r;
    double minx;
    double miny;
	
    public  DrawGrow(double x, double y, double w, double h, double theminx, double theminy) {
    	minx = theminx;
    	miny = theminy;
        r = new Rectangle(x, y, w , h);
        r.setStroke(Color.BLUE);
        r.setFill(null);
        r.getStrokeDashArray().addAll(2d);
        firstX = x;
        firstY = y;
    }
    
    
    public void movableCallback (double x, double y) {
    	if (x > firstX && y > firstY && x-firstX > minx && y-firstY > miny) {
    		r.setWidth(Math.abs(x - firstX));
    		r.setHeight(Math.abs(y- firstY));
    	}
    }


	@Override
	public Rectangle getShape() {
		return r;
	}
}
