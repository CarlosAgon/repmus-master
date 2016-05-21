package resources;

import gui.FX;

import java.net.URL;
import java.util.Hashtable;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

public class Loader {
	
	static Hashtable<String, Image> pict_dicctionaire
    = new Hashtable<String, Image>();
	
	static void addImageToDic (String name, Image img) {
		pict_dicctionaire.put(name, img);
	}
	
	public static Image getImageFromDic (String name){
		Image rep = pict_dicctionaire.get(name);
		   if (rep == null) {
			  URL location = Loader.class.getResource("/pict/" + name + ".png");
			  rep = new Image (location.toString());
			  if (rep != null) addImageToDic(name, rep);
		   }
		return rep;
	}
	
	static Hashtable<String, Image> icon_dicctionaire
    = new Hashtable<String, Image>();
	
	static void addIconToDic (String name, Image img) {
		icon_dicctionaire.put(name, img);
	}
	public static Image getIconFromDic (String name){
		Image rep = icon_dicctionaire.get(name);
		   if (rep == null) {
			  URL location = Loader.class.getResource("/icon/" + name + ".png");
			  rep = new Image (location.toString());
			  if (rep != null) addImageToDic(name, rep);
		   }
		return rep;
	}
	
	static Hashtable<String, ImageCursor> cursor_dicctionaire
    = new Hashtable<String, ImageCursor>();
	
	static void addCursorToDic (String name, ImageCursor cur) {
		cursor_dicctionaire.put(name, cur);
	}
	public static ImageCursor getCursorFromDic (String name){
		return getCursorFromDic(name, 0.0, 0.0);
	}
	
	public static ImageCursor getCursorFromDic (String name, double x, double y){
		ImageCursor rep = cursor_dicctionaire.get(name);
		if (rep == null) {
			  URL location = Loader.class.getResource("/curs/" + name + ".png");
			  Image img = new Image (location.toString());
			  rep = FX.omMakeCursor(img, x, y);
			  if (rep != null) addCursorToDic(name, rep);
		   }
		return rep;
	}
}
