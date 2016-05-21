package projects.music.editors.drawables;

import gui.FXCanvas;
import gui.renders.I_Render;

import java.util.List;

import com.sun.javafx.geom.Rectangle;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import projects.music.editors.MusicalParams;
import projects.music.editors.StaffSystem;

public interface I_Drawable {
	public I_Drawable getClickedObject (double x, double y);
	public void drawObject (I_Render g,  FXCanvas panel, Rectangle rect, MusicalParams params, 
			StaffSystem staffsys, List<I_Drawable> selection, double x0, double deltax);
	
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
	public List<I_Drawable> getInside ();
	public void consTimeSpaceCurve (int size, double x, int zoom) ;
}
