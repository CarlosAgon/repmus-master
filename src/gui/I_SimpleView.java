package gui;

import gui.renders.I_Render;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public interface I_SimpleView {
	
	public Node omGetDelegate ();
	
	//Color+Font
	public Color omGetBackground ();
	public void omSetBackground(Color color);
	public Font omGetFont();
	public void omSetFont(Font font);

	public Cursor omGetCursor();
	public void omSetCursor(Cursor cursor);


	//Size+position
	public Point2D omViewSize ();
	public void omSetViewSize (double x, double y);
	public void omSetViewSize(Point2D size);
	
	public Point2D omViewPosition ();
	public void omSetViewPosition (double x, double y);
	public void omSetViewPosition(Point2D pos);
	
	public double h ();
	public double w ();
	public double x ();
	public double y ();

	//
	public I_ContainerView omViewContainer ();
	public void omSetParent(I_ContainerView pere);
	//
	public void omUpdateView (boolean changedObject_p);
	public void omViewDrawContents (I_Render r);
	
	public I_SimpleView getPaneWithPoint (double x, double y);
	
	public ScrollPane omGetScroller ();
	
	}

