package gui.renders;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public interface I_Render {
	public void drawImage(Image img, double x, double y, double w, double h);
	public void drawImage(Image img, double x, double y);
	public void drawImage(Image img, double sx, double sy, double sw, double sh,
								double dx, double dy, double dw, double dh);

	public Color omGetColorStroke ();
	public void omSetColorStroke (Color color);

	public Color omGetColorFill ();
	public void omSetColorFill (Color color);


	public void omDrawLine (double x, double y, double x1, double y1);
	public void omDrawString (double x, double y, String str);
	public  void omFillEllipse (double x, double y, double w, double h);
    public  void omDrawEllipse (double x, double y, double w, double h);
    public  void omFillCercle (double cx, double cy, double r);
    public  void omDrawCercle (double cx, double cy, double r);
    public  void omFillRect (double x, double y, double w, double h);
    public  void omDrawRect (double x, double y, double w, double h);
    public  void omEraseRectContent (double x, double y, double w, double h);
    public  void omDrawEllipseArc (int x, int y, int width, int height, int start_angle, int sweep_angle);
    public  void omDrawBezierCurve (double x1, double y1, double x2, double y2, double xc1, double yc1, double xc2, double yc2);
    public  void omDrawBezierArrow (double x1, double y1, double x2, double y2, double xc1, double yc1, double xc2, double yc2, double arrsize);


    public  void omSetFont (Font font);
    public  Font omGetFont ();

    public  void omOpen ();
    public  void omClose ();

    public void omSetLineWidth (double size);

}
