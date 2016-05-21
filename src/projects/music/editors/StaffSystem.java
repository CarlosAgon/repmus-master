package projects.music.editors;

import gui.FX;
import gui.renders.I_Render;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class StaffSystem {
	
	
	////////////////////////////////////////////
	
	public class Key {

		String key = MusChars.key_g;
		int keyline = 4;
		Rectangle rect;
		
		Key (String theKey, int theKeyline){
			key = theKey;
			keyline = theKeyline;
		}
		
		public void draw (I_Render g, double x, double y, double dent){
			if (key.equals(MusChars.key_f))
					FX.omDrawString(g, x+dent , y+ dent, key);
			else if (key.equals(MusChars.key_g))
				 	FX.omDrawString(g, x+dent , y+ 3*dent, key); 
			else if (key.equals(MusChars.key_c))
			 	FX.omDrawString(g, x+dent , y+ (5 - keyline)*dent, key); 
			else if (key.equals("p")){
				FX.omFillRect(g, x+dent , y+dent, dent/2, 2*dent);
				FX.omFillRect(g, x+dent+dent , y+dent, dent/2, 2*dent);
			}

		}
	}

	////////////////////////////////////////////
	 public class SimpleStaff {
	   Key theKey;
	   int numlines;
	   Point2D range;
	   String strStaff;
	   double pixelStartY =0;
	   double pixelStartX =0;
	   int do4SpacesFromTop;
	   
	   
	   SimpleStaff (String staff){
	    	strStaff = staff;
			theKey = keyFromString();
			range = rangeFromString();
			numlines = linesFromString();
			do4SpacesFromTop = getSpaceFromTop();
		}
	   
	   public double getTopPixel () {
			return pixelStartY;
		}
		
	    
	    public  Key keyFromString (){
			Key rep;
			switch (strStaff) { 
			case "F": 	rep = new StaffSystem.Key(MusChars.key_f, 4); break; 
			case "U1": 	rep = new StaffSystem.Key(MusChars.key_c, 1); break;
			case "U2": 	rep = new StaffSystem.Key(MusChars.key_c, 2); break;
			case "U3": 	rep = new StaffSystem.Key(MusChars.key_c, 3); break;
			case "U4": 	rep = new StaffSystem.Key(MusChars.key_c, 4); break;
			case "G": rep = new StaffSystem.Key(MusChars.key_g, 4); break; 
			case "F2": 	rep =new StaffSystem.Key(MusChars.key_f, 4); break; 
			case "G2": rep = new StaffSystem.Key(MusChars.key_g, 4); break;
			case "P": 	rep = new StaffSystem.Key("p", 4); break; 
			case "L": rep = new StaffSystem.Key("r", 0); break; 
			case "empty": rep = new StaffSystem.Key("r", 0); break;
			default: rep = new StaffSystem.Key("r", 0);
			}
			return  rep;
	   }
	 
	 public  int linesFromString (){
		 	int rep = 5;
			switch (strStaff) { 
				case "L": rep = 1; break;
				case "empty": rep = 0; break;
				default: rep = 5;
			}
			return rep;
		   }
	 
	 public  Point2D rangeFromString (){
		 Point2D rep;
		 switch (strStaff) { 
			case "F": 	rep = new Point2D (43,57); break; 
			case "G": rep = new Point2D (64,77); break; 
			case "F2": rep = new Point2D (19,33); break;
			case "G2": rep = new Point2D (88,101); break;
			case "P" : 	rep = new Point2D (64,77); break; 
			case "U1" : 	rep = new Point2D (60,74); break; 
			case "U2" : 	rep = new Point2D (57,71); break; 
			case "U3" : 	rep = new Point2D (54,67); break; 
			case "U4" : 	rep = new Point2D (50,64); break; 
			default: rep = new Point2D (0 ,0);
			}
		 return rep;
		   }
	 
	 public int getDoFromTop() {
	 	return do4SpacesFromTop;
	 }
	 			
	 
	 public  int getSpaceFromTop (){
		 int rep;
		 switch (strStaff) { 
			case "F": 	rep = -2; break; 
			case "G": rep = 10; break; 
			case "F2": rep = -16; break;
			case "G2": rep = 24; break;
			case "P" : 	rep = 10; break;
			case "U1" : rep = 8; break; 
			case "U2" : rep = 6; break; 
			case "U3" : rep = 4; break; 
			case "U4" : rep = 2; break; 
			default: rep = 0;
			}
		 return rep;
		   }
	    
	    public int getHeight (){
	    	int rep;
			 switch (strStaff) { 
				case "F" : 	rep = 7; break;
				case "U1" : 	rep = 7; break; 
				case "U2" : 	rep = 7; break; 
				case "U3" : 	rep = 7; break; 
				case "U4" : 	rep = 7; break; 
				case "G": rep = 6; break; 
				case "F2": rep = 4; break;
				case "G2": rep = 7; break;
				default: rep = 4;
				}
			 return rep;
			   }
	    
	    public void draw(I_Render g, double x, double y, double w, double dent, boolean tempo, boolean selected_p) {
	    	double posy = y;
			 for (int i = 0; i < numlines ; i++) {
				 FX.omDrawLine (g, x , posy, x+w, posy);
				 posy = posy + dent;
			 }
			 theKey.draw(g,x,y,dent);
			 pixelStartX = x;
		 }
	    
	    public  boolean ClickInKey  (int x, int y, int size){
	    	Rectangle rect = new Rectangle ((int) pixelStartX , 
	    									(int) getTopPixel(), 
	    									size, size);
			return rect.contains(x,y);
		 }
	}
	
	////////////////////////////////////////////

	public class MultipleStaff {
		boolean selected = false;	
		double pixelStartY = 0;
		List<SimpleStaff> staffs = new ArrayList<SimpleStaff>();;
		Point2D range;
		   
		MultipleStaff (String[] theStaffs){
			   double min = 127;
			   double max = 0;
			   for (String staff : theStaffs) {
				  SimpleStaff theStaff = new SimpleStaff(staff);
				  staffs.add(theStaff);
				  min = Math.min(min, theStaff.range.getX());
				  max = Math.max(max, theStaff.range.getY());
				}
			   range = new Point2D(max, min);
			}
		
		public void setSelected (boolean val) {
			selected = val;
		}
		
		public boolean getSelected () {
			return selected;
		}
		   
		   public Point2D getRange () {
				return range;
			}
		   
		   public double getTopPixel () {
				return pixelStartY;
			}
			
		   // il faut changer et dire que le start est le DO 6000
		   public int getMidicFromY (int size, double y) {
			   int dent = size/4;
			   double oneDemiTonePixel = (dent * 3.5) / 12; 
			   double doPixels = getTopPixel() + (getDoFromTop() * (dent / 2));
			   double deltaPixels = doPixels - y;
			   int Octaves = (int) Math.floor (deltaPixels / (dent * 3.5) );
			   double octavePixel =  (doPixels + ( (5 - Octaves) * dent * 3.5));
			   double tonesPixels = octavePixel - (y + getTopPixel()) ;
			   int demiTones = (int) Math.round ( tonesPixels / oneDemiTonePixel);
			   int [] tonearray =  {0, 1, 2, 3, 4, 4, 5, 6, 7, 8, 9, 10, 11, 11}; 
			   return (Octaves * 1200) + (tonearray[demiTones] * 100); 
		   }
		   
		
		   public int getDemiDentsFromTop (int midi, Scale scale, int index) {
			   int doPosition = getDoFromTop(); 
			   int octaves = (int) Math.floor (midi / 12);
			   int octavePosition =  doPosition + ((5 - octaves) * 7);
			   return octavePosition - scale.getLines()[index]; 
		   }
		   
		   
		   public  int getDoFromTop (){
			   return staffs.get(0).getDoFromTop();
		   }
		   
		   public int getHeight (){
			   int rep = 0;
			   for (SimpleStaff staff : staffs) {
				   rep =  rep + staff.getHeight();
				}
			   return rep;
			}
		   
		   public double getMidiCenter (){
			   return range.getX() +  ((range.getY() - range.getX()) / 2);
			}
		   

		   
		   public void draw(I_Render g, double x, double y, double w, double dent, boolean tempo, boolean selected_p) {
			   double posy = y;
			   FX.omSetColorStroke(g, staffcolor);
				if (selected) FX.omSetColorStroke(g, selectedcolor);
				 for (SimpleStaff staff : staffs) {
					 staff.draw(g,x,posy, w, dent, tempo, selected_p || selected);
					 staff.pixelStartY = posy;
					 posy = posy + dent*staff.getHeight();
				 }
				 if (selected) FX.omSetColorStroke(g, selectedcolor);
			 }
		   
		   public  MultipleStaff ClickInKey  (double x, double y, int size){
				 boolean rep = false;
				 MultipleStaff repons =null;
				 for (SimpleStaff staff : staffs) {
					 if (rep == false) {
						 rep = staff.ClickInKey((int) x,(int) y, size);
					 }
				 }
				 if (rep) repons = this;
				 return repons;
			 }
		   
		   public  void offSelected  (){
				 selected = false;
			 }
	}
	
	////////////////////////////////////////////

	public class StaffGroup {
		
		List<MultipleStaff> staffs = new ArrayList<MultipleStaff>();
		double pixelStartY = 0;
		boolean selected = false;
	    
		StaffGroup (String[][] theStaffs){
			for (String[] multistaff : theStaffs) {
				MultipleStaff multi = new MultipleStaff(multistaff);
				staffs.add(multi);
			}
		}
		
		public double getTopPixel () {
			return pixelStartY;
		}
		
		public List<MultipleStaff> getStaffs () {
			return staffs;
		}
		
		
		public int getHeight (){
			   int rep = 0;
			   for (MultipleStaff staff : staffs) {
				   rep =  rep + staff.getHeight()+ 1;// 1 doit etre un params
				}
			   return rep;
			}
		
		public void draw(I_Render g, double x, double y, double w, double dent, boolean tempo, boolean selected_p) {
			double posy = y;
			 pixelStartY = posy;
			 FX.omSetColorStroke(g, staffcolor);
			 if (selected) FX.omSetColorStroke(g, selectedcolor);
			 for (MultipleStaff staff : staffs) {
				 staff.pixelStartY = posy;
				 staff.draw(g,x,posy, w, dent, tempo, selected_p || selected);
				 posy = posy + dent*staff.getHeight();
			 }
			 FX.omSetColorStroke(g, staffcolor);
		 }
		
		 public  MultipleStaff ClickInKey  (double x, double y, int size){
			 MultipleStaff rep = null;
			 for (MultipleStaff staff : staffs) {
				 if (rep == null){
					 rep = staff.ClickInKey(x,y, size);
				 }
			 }
			 return rep;
		 }
		 
		 public  void offSelected  (){
			 selected = false;
			 for (MultipleStaff staff : staffs) {
				 staff.offSelected();
			 }
		 }
	}
	
	////////////////////////////////////////////


	List<StaffGroup> staffGroups = new ArrayList<StaffGroup>();
	double pixelStartY = 0;
	boolean selected = false;
	Color staffcolor = new Color (0,0,0,1);
	Color selectedcolor = new Color (0,0,0,0.5);
		
	
	    
	StaffSystem (String[][][] theStaffGroups) {
			for (String[][] group : theStaffGroups) {
				StaffGroup theGroup = new StaffGroup(group);
				staffGroups.add(theGroup);
			}
	}
	
	public List<StaffGroup> getStaffGroups () {
		return staffGroups;
	}
	
	public double getTopPixel () {
		return pixelStartY;
	}
	
	public static StaffSystem getStaffSystem (String name, int theSize) {
		String[] multi;
		switch (name) { 
		case "F": 	multi = new String[] {"F"}; break; 
		case "G": multi = new String[] {"G"}; break; 
		case "F2": 	multi = new String[] {"F2"}; break; 
		case "G2": multi = new String[] {"G2"}; break;
		case "GF": 	multi = new String[] {"G" , "F"}; break; 
		case "GG": multi = new String[] {"G2" , "G"}; break;
		case "FF": 	multi = new String[] {"F" ,"F2"}; break; 
		case "GFF": multi = new String[] {"G", "F", "F2"}; break; 
		case "GGF": 	multi = new String[] {"G2", "G" , "F"}; break; 
		case "GGFF": multi = new String[] {"G2", "G" , "F", "F2"}; break;
		case "U1": multi = new String[] {"U1"}; break;
		case "U2": multi = new String[] {"U2"}; break;
		case "U3": multi = new String[] {"U3"}; break;
		case "U4": multi = new String[] {"U4"}; break;
		case "P": 	multi = new String[] {"P"}; break; 
		case "L": multi = new String[] {"L"}; break;
		case "empty": multi = new String[] {"empty"}; break;
		default: multi = new String[] {"F"};
		}
		String[][] group = new String[][] {multi};
		String[][][] system = new String[][][] {group};
		return new StaffSystem (system);
	}
	
	public int getHeight (){
		   int rep = 0;
		   for (StaffGroup group : staffGroups) {
			   rep =  rep + group.getHeight()+ 1;// 1 doit etre un params
			}
		   return rep;
		}
	 
	 public void draw(I_Render g, double x, double y, double w, double dent, boolean tempo, boolean selected_p) {
		 FX.omSetColorStroke(g, staffcolor);
		 if (selected) FX.omSetColorStroke(g, selectedcolor);
		 double posy = y;
		 pixelStartY = posy;
		 for (StaffGroup group : staffGroups) {
			 group.draw(g,x,posy, w, dent, tempo, selected);
			 group.pixelStartY = posy;
			 posy = posy + dent*group.getHeight();
		 }
		 FX.omSetColorStroke(g, staffcolor);
	 }
	 
	 public  MultipleStaff ClickInKey  (double x, double y, int size){
		 MultipleStaff rep = null;
		 for (StaffGroup group : staffGroups) {
			 if (rep == null){
				 rep = group.ClickInKey(x,y, size);
			 }
		 }
		 return rep;
	 }
	 
	 public  void offSelected  (){
		 selected = false;
		 for (StaffGroup group : staffGroups) {
			 group.offSelected();
		 }
	 }
		
}



