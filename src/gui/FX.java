package gui;

import gui.renders.I_Render;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import resources.Loader;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import draganddrop.DragAndDropHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;


public class FX {

	/////////////////////////////RENDERING	/////////////////////////////
	public static void drawImage(I_Render r, Image img, double x, double y, double w, double h) {
		r.drawImage(img,  x,  y,  w,  h);
	}

	public static void drawImage(I_Render r, Image img, double x, double y) {
		r.drawImage(img, x , y);
	}

	public static void drawImage(I_Render r, Image img, double sx, double sy, double sw, double sh,
								double dx, double dy, double dw, double dh) {
		r.drawImage(img, sx, sy, sw, sh, dx, dy, dw, dh);
	}

	public static double omgetHeightImage (Image image) {
		return image.getHeight();
	}

	public static double omgetWidthImage (Image image) {
		return image.getWidth();
	}

	// GRAPHICS
	/*
	 public static Shape omGetClip (Graphics g){
	    	return g.getClip();
	    }

	 public static void omSetClip (Graphics g, Shape s){
//	    Rectangle clip = new Rectangle(100, 100);
//	    clip.setLayoutX(25);
	    	 g.setClip(s);
	    }

	 public static void omSetClipRect (Graphics g, int x, int y, int w, int h){
    	 g.setClip(x, y, w, h);
    }
    */



	public static void omSetColorStroke (I_Render r, Color color) {
	       r.omSetColorStroke(color);
	}

	public static Color omGetColorStroke (I_Render r) {
	       return r.omGetColorStroke();
	}

	public static void omSetColorFill (I_Render r, Color color) {
			r.omSetColorFill(color);
	}

	public static Color omGetColorFill (I_Render r) {
		return r.omGetColorFill();
}


	public static void omDrawLine (I_Render r, double x, double y, double x1, double y1) {
        r.omDrawLine(x, y, x1, y1);
	}

	public static void omDrawArrow(I_Render r, double x1, double y1, double x2, double y2, int arrsize){
			double ANGLE = Math.PI / 8;
			double x = x2 - x1;
			double y = y2 - y1;
			double length = Math.sqrt(x * x + y * y);
			double arrowlength = arrsize;
			double unghi = Math.acos(x / length);

			// Arrow Line
			r.omDrawLine(x1, y1, x2, y2);

			// Arrowhead
			if (y < 0) {
				double angle = Math.PI - ANGLE - unghi;
				r.omDrawLine(x1 + x, y1 + y,
						x1 + (x + arrowlength * Math.cos(angle)),
						y1 + (y + arrowlength * Math.sin(angle)));
				angle += 2 * ANGLE;
				r.omDrawLine(x1 + x, y1 + y,
						x1 + (x + arrowlength * Math.cos(angle)),
						y1 + (y + arrowlength * Math.sin(angle)));
			} else {
				double angle = Math.PI - ANGLE + unghi;
				r.omDrawLine(x1 + x, y1 + y,
						x1 + (x + arrowlength * Math.cos(angle)),
						y1 + (y + arrowlength * Math.sin(angle)));
				angle += 2 * ANGLE;
				r.omDrawLine(x1 + x, y1 + y,
						x1 + (x + arrowlength * Math.cos(angle)),
						y1 + (y + arrowlength * Math.sin(angle)));
			}
		}

	public static void omDrawString (I_Render r, double x, double y, String str) {
        r.omDrawString(x, y, str);
	}

	public static void omFillEllipse (I_Render r, double x, double y, double w, double h) {
        r.omFillEllipse(x, y, w, h);
	}

    public static void omDrawEllipse (I_Render r, double x, double y, double w, double h) {
         r.omDrawEllipse(x, y, w, h);
	}

    public static void omFillCercle (I_Render r, double cx, double cy, double rad) {
        r.omFillCercle(cx, cy, rad);
	}

    public static void omDrawCercle (I_Render r, double cx, double cy, double rad) {
         r.omDrawCercle(cx, cy, rad);
	}


	public static void omFillRect (I_Render r, double x, double y, double w, double h) {
        r.omFillRect(x, y, w, h);
	}

    public static void omDrawRect (I_Render r, double x, double y, double w, double h) {
         r.omDrawRect(x, y, w, h);
	}

    public static void omEraseRectContent (I_Render r, double x, double y, double w, double h) {
        r.omEraseRectContent(x, y, w, h);
	}

    public static void omDrawEllipseArc (I_Render r, int x, int y, int width, int height, int start_angle, int sweep_angle) {
        r.omDrawEllipseArc(x,  y,  width,  height,  start_angle,  sweep_angle);

	}


    public static void omSetFont (I_Render r, Font font) {
    	r.omSetFont(font);
    }

    public static Font omGetFont (I_Render r) {
    	return r.omGetFont();
    }

    public static void omSetLineWidth (I_Render r, double size) {
    	 r.omSetLineWidth(size);
    }



    final public static Font default_font1 = new Font("Courier",12);

	/////////////////////////////END RENDERING	/////////////////////////////

	public static DragAndDropHandler dragHandler = new DragAndDropHandler ();

	public static List<I_SimpleView> draggingListObj = new ArrayList<I_SimpleView>();

	public static ImageCursor omMakeCursor (Image im, Double x, Double y){
		ImageCursor rep = new ImageCursor(im, x, y);
		return rep;
	}

	public static Cursor resizeCursor = Loader.getCursorFromDic("resize-cursor", 5, 5);

	/*
	public static Cursor omGetTextCursor (){
		return Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR);
	}

	public static Cursor omGetArrowCursor (){
		return Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	}
	*/

	public static Rectangle2D omScreenSize (){
		return Screen.getPrimary().getVisualBounds();
	}

	public static Image omLoadImage (String path){
		Image image = new Image(path, true);
		return image;
	}




    //Dialogs
    public static void omMessageDialog (String msg) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information Dialog");
    	alert.setContentText(msg);

    	alert.showAndWait();
       }
 /*
    public static int omYorNotDialog (String msg) {
    	  return JOptionPane.showConfirmDialog((Frame) null,
    			  msg, "choose one", JOptionPane.YES_NO_OPTION);

       }

    public static int omYorNotCancelDialog (String msg) {
  	  return JOptionPane.showConfirmDialog((Frame) null,
  			  msg, "choose one", JOptionPane.YES_NO_CANCEL_OPTION);

     }



    public static String omGetUserString (String str, String title, String init) {
     return JOptionPane.showInputDialog( (Frame) null , str , init);
    }

    public static String omChooseFileDialog (String prompt, String btext) {
    	JFileChooser chooser = new JFileChooser();
    	chooser.setDialogTitle(prompt);
    	chooser.setApproveButtonText(btext);
    	int returnVal = chooser.showOpenDialog((Frame) null);
        if(returnVal == JFileChooser.APPROVE_OPTION)
           return chooser.getSelectedFile().getAbsolutePath();
        else
        	return "";
       }

    public static String omChooseDirectoryDialog (String prompt) {
    	JFileChooser chooser = new JFileChooser();
    	chooser.setDialogTitle(prompt);
    	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	int returnVal = chooser.showOpenDialog((Frame) null);
        if(returnVal == JFileChooser.APPROVE_OPTION)
           return chooser.getSelectedFile().getAbsolutePath();
        else
        	return "";
       }

    public static String omChooseNewFileDialog (String prompt, String btext) {
    	JFileChooser chooser = new JFileChooser();
    	chooser.setDialogTitle(prompt);
    	chooser.setApproveButtonText(btext);
    	int returnVal = chooser.showSaveDialog((Frame) null);
        if(returnVal == JFileChooser.APPROVE_OPTION)
           return chooser.getSelectedFile().getAbsolutePath();
        else
        	return "";
       }

    public static String omChooseNewDirectoryDialog (String prompt) {
    	JFileChooser chooser = new JFileChooser();
    	chooser.setDialogTitle(prompt);
    	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	int returnVal = chooser.showSaveDialog((Frame) null);
        if(returnVal == JFileChooser.APPROVE_OPTION)
           return chooser.getSelectedFile().getAbsolutePath();
        else
        	return "";
       }

     public static void omBeep () {
        Toolkit.getToolkit().beep();
       }


    */


    //Color

    public static Color omChooseColorDialog (Color init) {
    	ColorPicker colorPicker = new ColorPicker();
    	return colorPicker.getValue();
       }


    //Date

    /*
    public static String omGetDate () {

 	   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
 	   //get current date time with Date()
 	   Date date = new Date();
 	   return dateFormat.format(date);
    }

    public static String omCmdLine (String str) throws IOException {
      InputStream is = Runtime.getRuntime().exec(str).getInputStream();
      java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
      return s.hasNext() ? s.next() : "";
    }

    */

    public static float omStringSize (String str, Font font){
    	FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
    	float size = fontLoader.computeStringWidth(str, font);
    	return size;
    }




 ////////////// MODIFIER KEYS
    static boolean commandKey =false;
    static boolean shiftKey =false;
    static boolean ControlKey =false;
    static boolean altKey =false;

    public static void setCommandKey (boolean val) {
    	commandKey = val;
    }

    public static boolean getCommandKey () {
    	return commandKey;
    }

    public static void setShiftKey (boolean val) {
    	shiftKey = val;
    }

    public static boolean getShiftKey () {
    	return shiftKey;
    }

    public static void setControlKey (boolean val) {
    	shiftKey = val;
    }

    public static boolean getControlKey () {
    	return ControlKey;
    }

    public static void setAltKey (boolean val) {
    	altKey = val;
    }

    public static boolean getAltKey () {
    	return altKey;
    }

    public static Color transp (Color c, double op ) {
     return new Color(c.getRed(), c.getGreen(), c.getBlue(), op);
    }

    public static Color [] SixtheenColors ={
    	transp(Color.BLUE, 0.7), transp(Color.CRIMSON, 0.7), transp(Color.DARKORANGE, 0.7), transp(Color.DARKVIOLET, 0.7),
    	transp(Color.FIREBRICK, 0.7), transp(Color.GOLD, 0.7), transp(Color.HOTPINK, 0.7), transp(Color.LINEN, 0.7),
    	transp(Color.MAGENTA, 0.7), transp(Color.NAVY, 0.7), transp(Color.ORANGERED, 0.7), transp(Color.YELLOW, 0.7),
    	transp(Color.SADDLEBROWN, 0.7), transp(Color.ROYALBLUE, 0.7), transp(Color.PLUM, 0.7), transp(Color.DODGERBLUE, 0.7)};



    ////////////////////////////////////////////
    public static class AnchorRect {
    	double cx;
    	double cy;
    	double w;
    	double h;
    	String name;

    	public AnchorRect (double x, double y, double wid, double hei, String thename) {
    		cx =x;
        	cy = y;
        	w = wid ;
        	h = hei ;
        	name = thename;
    	}

    	public Point2D getBorderPoint (int side) {
    		Point2D rep = new Point2D (cx,cy);
    		double sizex = w/2;
    		double sizey = h/2;
    		switch(side) {
    		 case 0 : rep = new Point2D ( cx -sizex, cy - sizey); break;
    		 case 1 : rep = new Point2D ( cx , cy - sizey); break;
    		 case 2 : rep = new Point2D ( cx + sizex, cy - sizey); break;
    		 case 3 : rep = new Point2D ( cx + sizex, cy ); break;
    		 case 4 : rep = new Point2D ( cx + sizex, cy + sizey); break;
    		 case 5 : rep = new Point2D ( cx , cy + sizey); break;
    		 case 6 : rep = new Point2D ( cx - sizex, cy +sizey ); break;
    		 case 7 : rep = new Point2D ( cx -sizex, cy ); break;
    		}
    		return rep;
    	}

    	public void Draw (I_Render r) {
    		double sizex = w/2;
    		omDrawString (r, cx , cy + h/4, name);
    	}


    }

}

