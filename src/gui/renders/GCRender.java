package gui.renders;

import gui.FXCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;


public class GCRender implements I_Render {

	GraphicsContext gc = null;

	public GCRender (FXCanvas view) {
		gc = view.omGetGraphicContext();
	}

	@Override
	public void drawImage(Image img, double x, double y, double w, double h) {
		gc.drawImage(img,  x,  y,  w,  h);
	}

	@Override
	public void drawImage(Image img, double x, double y) {
		gc.drawImage(img, x , y);
	}

	@Override
	public void drawImage(Image img, double sx, double sy, double sw,
			double sh, double dx, double dy, double dw, double dh) {
		gc.drawImage(img, sx, sy, sw, sh, dx, dy, dw, dh);
	}

	@Override
	public Color omGetColorStroke() {
		return (Color) gc.getStroke();
	}

	@Override
	public void omSetColorStroke(Color color) {
		gc.setStroke(color);
	}

	@Override
	public Color omGetColorFill() {
		return (Color) gc.getFill();
	}

	@Override
	public void omSetColorFill(Color color) {
		gc.setFill(color);
	}

	@Override
	public void omDrawLine(double x, double y, double x1, double y1) {
        gc.strokeLine(x, y, x1, y1);
	}


	@Override
	public void omDrawString(double x, double y, String str) {
		gc.fillText(str, x, y);
	}

	@Override
	public void omFillEllipse(double x, double y, double w, double h) {
		gc.fillOval(x, y, w, h);
	}

	@Override
	public void omDrawEllipse(double x, double y, double w, double h) {
		gc.strokeOval(x, y, w, h);
	}

	@Override
	public void omFillCercle(double cx, double cy, double r) {
		gc.fillOval(cx-r, cy-r, r*2, r*2);
	}

	@Override
	public void omDrawCercle(double cx, double cy, double r) {
		gc.strokeOval(cx-r, cy-r, r*2, r*2);
	}

	@Override
	public void omFillRect(double x, double y, double w, double h) {
		gc.fillRect(x, y, w, h);
	}

	@Override
	public void omDrawRect(double x, double y, double w, double h) {
		gc.strokeRect(x, y, w, h);
	}

	@Override
	public void omEraseRectContent(double x, double y, double w, double h) {
		gc.clearRect(x, y, w, h);
	}

	@Override
	public void omDrawEllipseArc(int x, int y, int width, int height,
			int start_angle, int sweep_angle) {
		gc.strokeArc(x,  y,  width,  height,  start_angle,  sweep_angle, null);
	}

	@Override
	public void omSetFont(Font font) {
		gc.setFont(font);
	}

	@Override
	public Font omGetFont() {
		return gc.getFont();
	}

	@Override
	public void omOpen() {
	}

	@Override
	public void omClose() {
	}

	@Override
	public void omSetLineWidth(double size) {
		gc.setLineWidth(size);

	}

	@Override
	public void omDrawBezierCurve(double x1, double y1, double x2, double y2, double xc1, double yc1, double xc2,
			double yc2) {
		gc.moveTo(x1, y1);
		gc.bezierCurveTo(xc1, yc1, xc2, yc2, x2, y2);

	}

	@Override
	public void omDrawBezierArrow(double x1, double y1, double x2, double y2, double xc1, double yc1, double xc2,
			double yc2, double arrowsize) {
		gc.moveTo(x1, y1);
		gc.bezierCurveTo(xc1, yc1, xc2, yc2, x2, y2);

	}
}
