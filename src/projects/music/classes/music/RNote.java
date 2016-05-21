package projects.music.classes.music;

import gui.FX;
import gui.FXCanvas;
import gui.renders.I_Render;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.text.Font;

import com.sun.javafx.geom.Rectangle;

import kernel.annotations.Ombuilder;
import kernel.annotations.Omclass;
import kernel.annotations.Omvariable;
import kernel.tools.Fraction;
import kernel.tools.ST;
import projects.music.classes.abstracts.Simple_S_MO;
import projects.music.classes.abstracts.Strie_MO;
import projects.music.classes.interfaces.I_RT;
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
import projects.music.classes.abstracts.RTree;

@Omclass(icon = "137", editorClass = "projects.music.classes.music.RNote$RNoteEditor")
public class RNote extends Simple_S_MO implements I_RT {
	
	@Omvariable
	public int midic = 0;
	@Omvariable
    public int vel = 0;
	@Omvariable
	public int chan = 0;
	
	@Omvariable
	public Fraction dur = new Fraction (1,4);
	
	public RNote (int themidic, int thevel, Fraction thedur, double tempo, int thechan, long offset) {
		super ();
		setOffset(offset);
		setQDurs(thedur);
		midic = themidic;
		vel = thevel;
		chan = thechan;
		List<Long> tiednotes = decomposeDur(thedur.num);
		List<RTree> proplist = new ArrayList<RTree>();
		tiednotes.forEach((x) -> {proplist.add(new RTree (x,true));});
		tree = new RTree(thedur,proplist);
	}
	 
	@Ombuilder(definputs="6000, 80, 1/4, 60, 1")
	public RNote (int midic, int vel, Fraction dur, double tempo, int chan) {
		this (midic, vel, dur, tempo, chan, 0);
	}
	 
	public RNote() {
	    this (6000,80,new Fraction(1,4),60, 1,0);
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
		return new RNoteDrawable (this, params);
	}

	
//////////////////////////////////////////////////
//////////////////////EDITOR//////////////////////
//////////////////////////////////////////////////
public static class RNoteEditor extends MusicalEditor {


@Override
public String getPanelClass (){
return "projects.music.classes.music.RNote$RNotePanel";
}

@Override
public String getControlsClass (){
return "projects.music.classes.music.RNote$RNoteControl";
}

@Override
public String getTitleBarClass (){
return "projects.music.classes.music.RNote$RNoteTitle";
}

}

//////////////////////PANEL//////////////////////
public static class RNotePanel extends MusicalPanel {

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
public static class RNoteControl extends MusicalControl {

}	

//////////////////////TITLE//////////////////////
public static class RNoteTitle extends MusicalTitle {

}	

//////////////////////DRAWABLE//////////////////////
public static class RNoteDrawable extends SimpleDrawable {
	public String head = MusChars.head_4;
	public int points = 0;
	String altChar = "";
	int posY;
	int auxLines;
	int altSize = 0;
	public int deltaHead = 0;
	public int deltaAlt = 1;
	boolean tied_p;
	int [] auxlines;
	
	final int headSizefactor = 4; 
	final int altSizefactor = 3; 
	
	boolean stem_p;
	int beamsnumber = 0;
	String stemdir = "up";
	
	public double centerX;

public RNoteDrawable (RNote theRef, MusicalParams params) {
	stem_p = true;
	int size = params.fontsize.get();
	StaffSystem staffSystem = StaffSystem.getStaffSystem (params.staff.get(), size);
	MultipleStaff staff = staffSystem.getStaffGroups().get(0).getStaffs().get(0);
	Scale scale = Scale.getScale (params.scale.get());
	ref = theRef;
	altChar = scale.getAlteration(theRef.getMidic());
	altSize = altChar.length();
	auxlines = setAuxLines (staff, scale);
	posY = getPosY(staff, scale);
	setHeadAndPoints(((RNote) ref).getQDurs());
	beamsnumber = getBeamsNum (((RNote) ref).getQDurs());
	System.out.println("beams num : " + beamsnumber + " " + ((RNote) ref).getQDurs());
	System.out.println("RT :  " + ((RNote) ref).tree );
	}

public int getPosY (MultipleStaff staff, Scale scale){
	int midic = ((RNote) ref).getMidic();
	int cents = ST.mod (midic , 1200);
	int index = ST.mod (Math.round (cents / scale.getApprox()), scale.getLines().length);
	int midi = Math.round(midic / 100);
	return staff.getDemiDentsFromTop ( midi, scale, index);
	}

public int []  getAuxLines (){
	return auxlines;
	}

public int []  setAuxLines (MultipleStaff staff, Scale scale){
	int midic = ((RNote) ref).getMidic();
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
	
	//drawPoints(g, size, xPos, yPos);
	
	if (altChar != "") {
		FX.omSetFont(g, new Font("omicron",size));
		FX.omDrawString(g, posAlt, yPos, altChar);
		FX.omSetFont(g, new Font("omheads",size));
	}
	//drawAuxLines (g, size, xPos, yPos, staffsys);
	//if (tied_p) drawTie(g, size, xPos, yPos);
	
	//drawSlot (g, panel, xPos, yPos, params);
	//drawExtras (g, view, size, xPos, yPos, zoom);
	setRectangle((int) posAlt - size/8, (int) yPos - size/8, size/2, size/4);
	if (stem_p && beamsnumber != -1) {
		drawStem(g, x0 + deltax + size * 0.278, y(), w(), h() - (size / 6), size);
	}
}

public void collectTemporalObjects(List<SpacedPacket> timelist) {
timelist.add(new SpacedPacket(this, this.ref.getOnsetMS()));
}


public void setHeadAndPoints(Fraction val){
	long bef = (long) Strie_MO.beforeAndAfteBin(val.getNumerator()).getX();
	if (bef == val.getNumerator()){
		head = noteStrictChar (val);
		points = 0;
		}
	else if (bef * 3/2 == val.getNumerator()) {
		head = noteStrictChar (new Fraction( bef , val.getDenominator()));
		points = 1;
		}
	else if (bef * 7/4 == val.getNumerator()) {
		head = noteStrictChar (new Fraction( bef , val.getDenominator()));
		points = 2;
		}
}

public String noteStrictChar (Fraction val) {
String rep = "";
if (val.value() > 8) {
	bigchord = val;
	return MusChars.head_8;
}
else if (val.equals(Fraction.FOUR)) return MusChars.head_4;
else if (val.equals(Fraction.TWO)) return MusChars.head_2;
else if (val.equals(Fraction.ONE)) return MusChars.head_1;
else if (val.equals(Fraction.f1_2)) return MusChars.head_1_2;
else return MusChars.head_1_4; 
}

public void drawStem (I_Render g, double x, double y, double w, double h, int size) {
	double stemsize =  (size*2.5)/6 ;
	if (beamsnumber == 0)
		FX.omDrawLine (g, x, y+h, x, y - stemsize - size/4);
	else
		FX.omDrawLine (g, x, y+h, x, y - stemsize - (beamsnumber*size/4));
	int i = 0;
	while  (i < beamsnumber) {
		FX.omDrawString (g, x, y - stemsize - (i*size/4), "A"); //MusChars.beam_up);
		i++;
	}
	setRectangle(x(), y() - stemsize - (i*size/4), w(), h()  + stemsize + (i*size/4));
}

}
//////////////////////////////////////////////////////
}
