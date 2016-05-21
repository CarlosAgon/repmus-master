package projects.music.classes.music;

import gui.FX;
import gui.FXCanvas;
import gui.renders.I_Render;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import com.sun.javafx.geom.Rectangle;

import kernel.annotations.Ombuilder;
import kernel.annotations.Omclass;
import kernel.annotations.Omvariable;
import kernel.tools.ST;
import projects.music.classes.abstracts.Simple_L_MO;
import projects.music.editors.MusChars;
import projects.music.editors.MusicalControl;
import projects.music.editors.MusicalEditor;
import projects.music.editors.MusicalPanel;
import projects.music.editors.MusicalParams;
import projects.music.editors.MusicalTitle;
import projects.music.editors.Scale;
import projects.music.editors.SpacedPacket;
import projects.music.editors.StaffSystem;
import projects.music.editors.StaffSystem.MultipleStaff;
import projects.music.editors.drawables.I_Drawable;
import projects.music.editors.drawables.SimpleDrawable;

@Omclass(icon = "137", editorClass = "projects.music.classes.music.Note$NoteEditor")
public class Note extends Simple_L_MO  {

	 @Omvariable
	public int midic = 0;
	 @Omvariable
	public int vel = 0;
	 @Omvariable
	public int chan = 0;
	
/*	@JsonCreator
	public Note (int themidic, int thevel, long dur, int thechan) {
		setOffset(0);
		setDuration(dur);
		midic = themidic;
		vel = thevel;
		chan = thechan;
	}
	*/
	

	public Note (int themidic, int thevel, long dur, int thechan, long offset) {
		super ();
		setOffset(offset);
		setDuration(dur);
		midic = themidic;
		vel = thevel;
		chan = thechan;
	}
	 
	 @Ombuilder
	public Note (int themidic, int thevel, long dur, int thechan) {
		this (themidic,thevel, dur, thechan, 0);
	}
	 
	 public Note() {
	    this (6000,80,1000,1,0);
	 }
	 
	//////////////////////////////////////
	
	public int getMidic () {
		return midic;
	}
	
	public void setMidic (int themidic) {
		 midic = themidic;
	}
	
	public int getChan () {
		return chan;
	}
	
	public void setChan (int thechan) {
		 chan = thechan;
	}
	
	public int getVel () {
		return vel;
	}
	
	public void setVel (int thevel) {
		 vel = thevel;
	}
	
	
	public I_Drawable makeDrawable (MusicalParams params) {
		return new NoteDrawable (this, params);
	}
	
	
	//////////////////////////////////////////////////
	//////////////////////EDITOR//////////////////////
	//////////////////////////////////////////////////
    public static class NoteEditor extends MusicalEditor {
    	
    	
        @Override
    	public String getPanelClass (){
    		return "projects.music.classes.music.Note$NotePanel";
    	}
        
        @Override
    	public String getControlsClass (){
    		return "projects.music.classes.music.Note$NoteControl";
    	}
        
        @Override
    	public String getTitleBarClass (){
    		return "projects.music.classes.music.Note$NoteTitle";
    	}
        
    }
    
    //////////////////////PANEL//////////////////////
    public static class NotePanel extends MusicalPanel {
    	
    	public void KeyHandler(String car){
   		 switch (car) {
			 case "h" : takeSnapShot ();
	 					break;
			 case "c": delegate.setScaleX( delegate.getScaleX() * 1.1);
			 		   delegate.setScaleY( delegate.getScaleY() * 1.1);
			 		   break;
			 case "C": delegate.setScaleX( delegate.getScaleX() * 0.9);
			 		   delegate.setScaleY( delegate.getScaleY() * 0.9);
			 		   break;
   		 }
    	}
    	
    	@Override
    	public int getZeroPosition () {
    		return 2;
    	}
    	
    }	
    
    
    //////////////////////CONTROL//////////////////////
    public static class NoteControl extends MusicalControl {
    	
    }	
    
    //////////////////////TITLE//////////////////////
    public static class NoteTitle extends MusicalTitle {
    	
    }	
    
    /////////////////////DRAWABLE///////////////////
    public static class NoteDrawable extends SimpleDrawable {
    	public String head = MusChars.head_1_4;
    	String altChar = "";
    	int posY;
    	int auxLines;
    	int altSize = 0;
    	public int deltaHead = 0;
    	public int deltaAlt = 1;
    	boolean selected;
    	boolean stem_p;
    	boolean tied_p;
    	int [] auxlines;

    	final int headSizefactor = 4; 
    	final int altSizefactor = 3;
    	
    	public double centerX;

    public NoteDrawable (Note theRef, MusicalParams params) {
    	int size = params.fontsize.get();
    	StaffSystem staffSystem = StaffSystem.getStaffSystem (params.staff.get(), size);
    	MultipleStaff staff = staffSystem.getStaffGroups().get(0).getStaffs().get(0);
    	Scale scale = Scale.getScale (params.scale.get());
    	ref = theRef;
    	altChar = scale.getAlteration(theRef.midic);
    	altSize = altChar.length();
    	auxlines = setAuxLines (staff, scale);
    	posY = setPosY(staff, scale);
    	}	


    public int setPosY (MultipleStaff staff, Scale scale){
    	int midic = ((Note) ref).getMidic();
    	int cents = ST.mod (midic , 1200);
    	int index = ST.mod (Math.round (cents / scale.getApprox()), scale.getLines().length);
    	int midi = Math.round(midic / 100);
    	return staff.getDemiDentsFromTop ( midi, scale, index);
    	}

    public int []  getAuxLines (){
    	return auxlines;
    	}

    public int []  setAuxLines (MultipleStaff staff, Scale scale){
    	int midic = ((Note) ref).getMidic();
    	int index = ST.mod(Math.round(ST.mod(midic , 1200) / scale.getApprox()), scale.getLines().length);
    	int midi = Math.round(midic / 100);
    	int [] rep = {-1, -1, -1};
    	int top = (int) staff.getRange().getX();
    	int bottom = (int) staff.getRange().getY();
    	if (midi > top){
    		rep[0] = 0;
    		rep [1] = staff.getDemiDentsFromTop ( midi, scale, index);
    		rep [2] = 0;
    		}
    	else if (midi < bottom) {
    		rep[0] = 1;
    		rep[2] = staff.getDemiDentsFromTop ( bottom, scale, index);
    		rep[1] = staff.getDemiDentsFromTop ( midi, scale, index) - rep[2];
    		}
    	return rep;
    	}


    /*double spaceFromDo (int x, Scale scale) {
    	int[] lines = scale.getLines();
    	return lines[x] * 0.5;
    }
    */

    public void drawObject(I_Render g, FXCanvas panel, Rectangle rect, 
    		MusicalParams params, StaffSystem staffsys, List<I_Drawable> selection, double x0, double deltax) {
    	int size = params.fontsize.get();
    	int dent = size/4;
    	double x = x0 + deltax;
    	double stafftoppixels = staffsys.getTopPixel();
    	int xPos = (int) Math.round (x + ((deltaHead * size) / headSizefactor));
    	long posAlt = xPos;
    	if (altChar != "") posAlt = Math.round (x - ((deltaAlt * size * 3) / 10));
    	double yPos = stafftoppixels + (posY * dent/2);
    	
    	FX.omDrawString(g, xPos, yPos, head);
    	if (altChar != "") {
    		FX.omSetFont(g, new Font("omicron",size));
    		FX.omDrawString(g, posAlt, yPos, altChar);
    		FX.omSetFont(g, new Font("omheads",size));
    	}
    	//drawAuxLines (g, size, xPos, yPos, staffsys);
    	drawSlot (g, panel, xPos, yPos, params);
    	//drawExtras (g, view, size, xPos, yPos, zoom);
    	setRectangle((int) posAlt - size/8, (int) yPos - size/8, size/2, size/4);
    }

    public void drawSelection(I_Render g, List<I_Drawable> selection){
    	if  (selection.contains(ref))
    		drawRectSelection(g);
    }

    		                  
    public void drawAuxLines (I_Render g, int size, double xPos, double yPos, MultipleStaff staff) {
    	int [] auxlines = getAuxLines();
    	if (auxlines[0] != -1) {
    		int dent = size/4;
    		int dir = auxlines [0];
    		int demidents = Math.abs(auxlines [1]);
    		double top = staff.getTopPixel();
    		if (dir == 0) { //up
    			int i = 2;
    			while (i <= demidents) {
    				FX.omDrawLine(g, xPos, top - dent, xPos+size , top - dent);
    				top = top - dent;
    				i = i + 2;
    			}
    		}
    		else {
    			int i = 2;
    			top = top + (auxlines [2] * dent/2);
    			while (i <= demidents) {
    				FX.omDrawLine(g, xPos, top + dent, xPos+size , top + dent);
    				top = top + dent;
    				i = i + 2;
    			}	
    		}
    	}
    }


    public String  getDynFromVel (int vel) {
    	if (vel <= 0)
    		return MusChars.dynamicsArray[0];
    	else for (int i = 1; i < MusChars.dynamicsMidiArray.length; i++) {
    			if (MusChars.dynamicsMidiArray [i] > vel)
    				return MusChars.dynamicsArray[i-1];
    			}
    	return MusChars.dynamicsArray[MusChars.dynamicsMidiArray.length - 1];
    }

    public void drawSlot (I_Render g, FXCanvas view, double xPos, double yPos, MusicalParams params) {
    	Color oldcolor = FX.omGetColorFill(g);
    	String slot = params.slotmode.get();
    	int size = params.fontsize.get();
    	int zoom = params.zoom.get();
    	Note thenote = ((Note) ref);
    	int chan = thenote.getChan();
    	Color thecolor = FX.SixtheenColors [(chan - 1) % 16];
    	FX.omSetColorFill(g, thecolor);
    	if (slot.equals("channel")){
    		Font oldFont = FX.omGetFont(g);
    		FX.omSetFont(g, params.getFont("sing4/5Size"));
    		FX.omDrawString (g, xPos + 3 + size / headSizefactor , yPos, "" +  thenote.getChan());
    		FX.omSetFont(g,oldFont);
    	}
    	else if (slot.equals("dur")){
    		FX.omFillRect (g, xPos + size / headSizefactor, yPos, ms2pixel(thenote.getDuration(), size), size/12);
    	}
    	else if (slot.equals("dyn")){
    		Font oldFont = FX.omGetFont(g);
    		FX.omSetFont(g, params.getFont("extrasSize"));
    		FX.omDrawString (g, xPos + 3 + size / headSizefactor , yPos, "" +  getDynFromVel(thenote.getVel()));
    		FX.omSetFont(g,oldFont);
    	}
    	else if (slot.equals("port")){
    		Font oldFont = FX.omGetFont(g);
    		FX.omSetFont(g, params.getFont("sing4/5Size"));
    	//	FX.omDrawString (g, xPos + 3 + size / headSizefactor , yPos, "" +  thenote.getPort());
    		FX.omSetFont(g,oldFont);
    	}
    	FX.omSetColorFill(g, oldcolor);
    }

    public void drawExtras (I_Render g, FXCanvas view, int size, double xPos, double yPos, int zoom) {
    	
    }

    @Override
    public I_Drawable getClickedObject(double x, double y) {
    	I_Drawable rep = null;
    	Rectangle rect = new Rectangle ((int) x(), (int) y(), (int) w(), (int) h() );
    	if (rect.contains((int) x,(int) y))
    		rep = this;
    	return rep;
    }

    }

    /*(defmethod om-get-menu-context ((object grap-note))
    		  (alteration-menucontext object))

    		(defun alteration-menucontext (object)
    		  (let* ((alt (find-alt-list (midic (reference object))))
    		        (item1 (first alt)) (item2 (second alt)) (item3 (third alt))
    		        (menu1 (om-new-leafmenu (string+ (get-note-name (car item1)) (case (second item1) (-2 "bb") (-1 "b") (0 ".") (1 "#") (2 "##") (t "")))
    		                                #'(lambda () 
    		                                    (set-note-tonalite (reference object) (car item1) (second item1))
    		                                    (update-panel (panel (editor (om-front-window)))))
    		                                ))
    		        (menu2 (om-new-leafmenu (string+ (get-note-name (car item2)) (case (second item2) (-2 "bb") (-1 "b") (0 ".") (1 "#") (2 "##") (t "")))
    		                           #'(lambda () 
    		                               (set-note-tonalite (reference object) (car item2) (second item2))
    		                               (update-panel (panel (editor (om-front-window)))))
    		                           ))
    		        (menu3 (when (car item3) (om-new-leafmenu (string+ (get-note-name (car item3)) (case (second item3) (-2 "bb") (-1 "b") (0 ".") (1 "#") (2 "##") (t "")))
    		                                                  #'(lambda () 
    		                                                      (set-note-tonalite (reference object) (car item3) (second item3))
    		                                                      (update-panel (panel (editor (om-front-window)))))
    		                                                  )))
    		        (menureset (om-new-leafmenu (string+ "init.")
    		                                #'(lambda () 
    		                                    (set-tonalite (reference object) nil)
    		                                    (update-panel (panel (editor (om-front-window)))))
    		                                )))
    		    (remove nil (list menu1 menu2 menu3 menureset))))*/

}
