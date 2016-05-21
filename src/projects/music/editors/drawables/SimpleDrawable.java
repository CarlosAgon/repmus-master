package projects.music.editors.drawables;

import gui.FX;
import gui.FXCanvas;
import gui.renders.I_Render;

import java.util.List;

import com.sun.javafx.geom.Rectangle;

import projects.music.classes.abstracts.Strie_MO;
import projects.music.classes.interfaces.I_MusicalObject;
import projects.music.editors.MusChars;
import projects.music.editors.MusicalParams;
import projects.music.editors.SpacedPacket;
import projects.music.editors.StaffSystem;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import kernel.tools.Fraction;
import kernel.tools.ST;

public class SimpleDrawable implements I_Drawable{
		public I_MusicalObject ref;
		public ContainerDrawable father;
		double recx;
		double recy;
		double recw;
		double rech;
		public double centerX;
		public double centerY;
		public boolean selected;
		
			
		public Fraction bigchord = null;
		//extras
		
		public static final int onesecond = 3; 
		public static final double rhtymicfactor = 1.5; 
	
		
		public void setRectangle (double x, double y, double w, double h) {
			recx = x ;
			recy = y;
			recw = w;
			rech = h;
		}
		
		public void drawRectSelection(I_Render g) {
			Color color = FX.omGetColorFill(g);
			FX.omSetColorFill(g, new Color(0.2, 0.2, 0.8, 0.3));
			FX.omFillRect(g, x(), y(), w(), h());
			FX.omSetColorFill(g, color);
		}
		
		public boolean getSelected() {
			return selected;
		}
		
		public void setSelected(boolean sel) {
			 selected = sel;
		}
		
		public ContainerDrawable getFather() {
			return father;
		}
		
		public void setFather (ContainerDrawable thefather) {
			father = thefather;
		}
		

		@Override
		public I_Drawable getClickedObject(double x, double y) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		@Override
		public void drawObject(I_Render g, FXCanvas panel, Rectangle rect, 
				MusicalParams params, StaffSystem staffsys, 
				List<I_Drawable> selection, double x0, double deltax) {
			
		}
		

		@Override
		public Color omGetBackground() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void omSetBackground(Color color) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Font omGetFont() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void omSetFont(Font font) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Cursor omGetCursor() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void omSetCursor(Cursor cursor) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Point2D omViewSize() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void omSetViewSize(double x, double y) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void omSetViewSize(Point2D size) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Point2D omViewPosition() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void omSetViewPosition(double x, double y) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void omSetViewPosition(Point2D pos) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public double h() {
			return rech;
		}

		@Override
		public double w() {
			return recw;
		}

		@Override
		public double x() {
			return recx;
		}

		@Override
		public double y() {
			return recy;
		}


		@Override
		public List<I_Drawable> getInside() {
			return null;
		}
		
		@Override
		public void consTimeSpaceCurve (int size, double x, int zoom) {
		}
		
		/////////////////////DRAW
		public void drawSelection(I_Render g, List<I_MusicalObject> selection, int size){
			if  (selection.contains(this))
				drawRectSelection(g);
		}
		
		public void drawContainersObjects (I_Render g, FXCanvas panel, Rectangle rect,
				MusicalParams params, List<I_Drawable> selection) {
		}
		
		////////////////////SPACING
		public double  ms2pixel (long ms, int size) {
			return Math.round((onesecond * size * ms) / 1000);
		}
	

		public double ryhtm2pixels (long ms, int size) {
			return  size * Math.max(0.25,  Math.pow (rhtymicfactor , Math.log(ms/1000.0)/Math.log(2)));
		}
		
		public void collectTemporalObjects(List<SpacedPacket> timelist) {
		}
		
		public void collectRectangle () {
		}
		
		
		////////////////////RYTHMIC TOOLS
		public int getBeamsNum(Fraction val){
			long bef = (long) Strie_MO.beforeAndAfteBin(val.getNumerator()).getX();

			if (bef == val.getNumerator()) return getStrictBeams (val);
			else if (bef * 3/2 == val.getNumerator()) 
				return getStrictBeams (new Fraction( bef , val.getDenominator()));
			else if (bef * 7/4 == val.getNumerator()) 
				return getStrictBeams (new Fraction( bef , val.getDenominator()));
			else return 0;
		}
		
		public int  getStrictBeams (Fraction val) {
			if (val.equals(Fraction.ONE))
				return -1;
			else if (val.equals(Fraction.f1_2) || val.equals(Fraction.f1_4)) return 0;
			else if (val.equals(Fraction.f1_8)) return 1;
			else if (val.equals(Fraction.f1_16)) return 2;
			else if (val.equals(Fraction.f1_32)) return 3;
			else if (val.equals(Fraction.f1_64)) return 4;
			else if (val.equals(Fraction.f1_128)) return 5;
			else if (val.equals(Fraction.f1_256)) return 6;
			else if (val.equals(Fraction.f1_512)) return 7;
			else if (val.isBinaire ())
					return (int) Math.round((Math.log(val.getDenominator())/Math.log(2)) - 2);
			else
					return 8 ; // tres petit
		}
	
}
		
				  
