package projects.music.classes.music;

import gui.FX;
import gui.FXCanvas;
import gui.renders.I_Render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.geometry.Point2D;

import com.sun.javafx.geom.Rectangle;

import kernel.annotations.Ombuilder;
import kernel.annotations.Omclass;
import kernel.annotations.Omvariable;
import kernel.tools.Fraction;
import kernel.tools.ST;
import projects.music.classes.abstracts.MusicalObject;
import projects.music.classes.abstracts.Parallel_S_MO;
import projects.music.classes.abstracts.Strie_MO;
import projects.music.classes.interfaces.I_InRSeqChord;
import projects.music.classes.interfaces.I_RT;
import projects.music.classes.music.RNote;
import projects.music.classes.music.RNote.RNoteDrawable;
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
import projects.music.editors.drawables.ContainerDrawableS;
import projects.music.editors.drawables.I_Drawable;

@Omclass(icon = "139", editorClass = "projects.music.classes.music.RChord$RChordEditor")
public class RChord extends Parallel_S_MO implements I_RT, I_InRSeqChord {
	
	@Omvariable
	public List<Integer> lmidic;
	@Omvariable
	public List<Integer> lvel;
	@Omvariable
	public List<Integer> lchan;
	@Omvariable
	public List<Long> loffset;
	@Omvariable
	public Fraction dur;
	
	public boolean cont_p = false;
	Point2D ambitus;
	
	//appele par group ou measure
	public RChord (Fraction dur, RChord chord, int tempo, boolean iscont) {
		cont_p = iscont;
		initChordRythmic (chord.lmidic, chord.lvel, chord.loffset, dur, chord.lchan, tempo);
	}

	@Ombuilder(definputs="(6000), (80), (0), 1/8, (1)")
	public RChord (List<Integer> themidic, List<Integer> thevel, List<Long> theoffset, Fraction thedur, List<Integer> thechan) {
		this (themidic, thevel, theoffset, thedur, thechan, 60);
	}
		
	public RChord (List<Integer> themidic, List<Integer> thevel, List<Long> theoffset, Fraction thedur, List<Integer> thechan, int tempo) {
		setOffset(0);
		initChordRythmic(themidic, thevel, theoffset, thedur, thechan, tempo);
	}
	
	public RChord () {
		List<Integer> themidic = new ArrayList<Integer> ();
		themidic.add(6000);
		themidic.add(6700);
		themidic.add(7200);
		List<Integer> thevel = new ArrayList<Integer> ();
		thevel.add(80);
		List<Long> theoffset = new ArrayList<Long> ();
		theoffset.add((long) 0);
		List<Integer> thechan = new ArrayList<Integer> ();
		thechan.add(1);
		setOffset(0);
		initChordRythmic(themidic, thevel, theoffset, new Fraction(1,2), thechan, 60);
	}
	
	public void initChordRythmic (List<Integer> themidic, List<Integer> thevel, 
			List<Long> theoffset, Fraction thedur, List<Integer> thechan, double tempo) {
		lmidic = themidic;
		lvel = thevel;
		lchan = thechan;
		loffset = theoffset;

		int sizevel = thevel.size();
		int sizechan = thechan.size();
		int sizeoffset = theoffset.size();
		int vel = 60;
		int chan = 0;
		long offset = 0;
		RNote newnote;
		for (int i=0; i< themidic.size(); i++){
			if (i < sizevel) vel = thevel.get(i);
			if (i < sizechan) chan = thechan.get(i);
			if (i < sizeoffset) offset = theoffset.get(i);
			newnote = new RNote(themidic.get(i), vel,thedur,tempo,chan,offset);
			addElement(newnote);
			newnote.setTempo(tempo);
		}
		setSlots();
		setQDurs(thedur);
		setTempo(tempo);
		//setDurPar();
	}

	
	public void setSlots () {
		int min = 128000;
		int max = 0;
		lmidic = new ArrayList<Integer>();
		loffset = new ArrayList<Long>();
		lvel = new ArrayList<Integer>();
		lchan = new ArrayList<Integer>();
		for (MusicalObject note : getElements()) {
			min = Math.min(min, ((RNote) note).midic);
			max = Math.max(max, ((RNote) note).midic);
			lmidic.add(((RNote) note).midic);
			lvel.add(((RNote) note).vel);
			lchan.add(((RNote) note).chan);
			loffset.add(((RNote) note).getOffset());
		}
		ambitus = new Point2D(min,max);
	}

	//////////////////////////////////////////////////////////////////

	public List<Integer> getLMidic () {
		return lmidic;
	}
	
	public void setLMidic (List<Integer> midic) {
		lmidic = midic;
	}
	
	public List<Integer> getLVel () {
		return lmidic;
	}
	
	public void setLVel (List<Integer> midic) {
		lmidic = midic;
	}
	
	public List<Integer> getLChan () {
		return lmidic;
	}
	
	public void setLChan (List<Integer> midic) {
		lmidic = midic;
	}
	

	public Point2D getAmbitus () {
		return ambitus;
	}
	
//////////////////////////////////////////////////
	public I_Drawable makeDrawable (MusicalParams params) {
		return new RChordDrawable (this, params);
	}


	//////////////////////EDITOR//////////////////////
    public static class RChordEditor extends MusicalEditor {
    	
    	
        @Override
    	public String getPanelClass (){
    		return "projects.music.classes.music.RChord$RChordPanel";
    	}
        
        @Override
    	public String getControlsClass (){
    		return "projects.music.classes.music.RChord$RChordControl";
    	}
        
        @Override
    	public String getTitleBarClass (){
    		return "projects.music.classes.music.RChord$RChordTitle";
    	}
        
    }
    
    //////////////////////PANEL//////////////////////
    public static class RChordPanel extends MusicalPanel {
    	
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
    public static class RChordControl extends MusicalControl {
    	
    }	
    
    //////////////////////TITLE//////////////////////
    public static class RChordTitle extends MusicalTitle {
    	
    }	
    
    //////////////////////DRAWABLE//////////////////////
public static class RChordDrawable extends ContainerDrawableS {
	
	public String head = MusChars.head_4;
	public int points = 0;
	String altChar = "";
	int posY;
	int auxLines;
	int altSize = 0;
	public int deltaHead = 0;
	public int deltaAlt = 1;
	boolean selected;
	public  boolean stem_p;
	boolean tied_p;
	int [] auxlines;
	String stemdir;

	final int headSizefactor = 4; 
	final int altSizefactor = 3;
	
	int beamsnumber = 0;

public RChordDrawable (RChord theRef, MusicalParams params) {
	ref = theRef;
	stem_p = true;
	int size = params.fontsize.get();
	StaffSystem staffSystem = StaffSystem.getStaffSystem (params.staff.get(), size);
	MultipleStaff staff = staffSystem.getStaffGroups().get(0).getStaffs().get(0);
	Scale scale = Scale.getScale (params.scale.get());
	
	stem_p = params.showstem.get();
	String chordmode = params.chordmode.get();

	List<MusicalObject> notes = theRef.getElements();
	List<MusicalObject> copie = ((List<MusicalObject>) ((ArrayList<MusicalObject>) notes).clone());
	Collections.sort(copie, pitchUp);
	int i = 0;
	int pos;
	int j;
	boolean found;
	List<Point2D> taked = new ArrayList<Point2D>();
	setStemDir(staff.getMidiCenter());
	for (MusicalObject note : copie) {
		RNoteDrawable gnote = new RNoteDrawable ((RNote) note, params);
		gnote.centerX = 0;
		gnote.stem_p = false;
		pos = DemiDents(((RNote) note).getMidic(), staff,scale);
		j = 0;
		found = false;
		while (found == false) {
		           if (! (taked.contains(new Point2D(j,pos))
		            	//  || taked.contains(new Point2D(j,pos+1)) ||taked.contains(new Point2D(j,pos-1))
		            	)) {
		        	   found = true;
		        	   taked.add(new Point2D(j,pos));
		        	   taked.add(new Point2D(j,pos+1));
		        	   taked.add(new Point2D(j,pos-1));
		        	   gnote.deltaHead = j;
		            }
		            j++;
		        }
		inside.add(gnote);
		gnote.setFather(this);
		i++;
	}
	setAltPositions(getInside(), staff, scale);
	
	setHeadAndPoints(((RChord) ref).getQDurs());
	beamsnumber = getBeamsNum (((RChord) ref).getQDurs());
	}		

public  Comparator<MusicalObject> pitchUp = new Comparator<MusicalObject>() {
    public int compare(MusicalObject n1, MusicalObject n2) {
    		return  ((RNote) n1).midic - ((RNote) n2).midic;
    }
};

public  Comparator<I_Drawable> byHead = new Comparator<I_Drawable>() {
	public int compare(I_Drawable n1, I_Drawable n2) {
		return  ((RNoteDrawable) n2).deltaHead - ((RNoteDrawable) n1).deltaHead;
	}
};

public int DemiDents (int midic, MultipleStaff staff, Scale scale){
	int cents = ST.mod (midic , 1200);
	int index = ST.mod (Math.round (cents / scale.getApprox()), scale.getLines().length);
	int midi = Math.round(midic / 100);
	return staff.getDemiDentsFromTop ( midi, scale, index);
	}


//ATTENTION a la taille de lalteration
public void setAltPositions (List<I_Drawable> notes, MultipleStaff staff, Scale scale) {
	List<Point2D> taked = new ArrayList<Point2D>();
	Collections.sort(notes, byHead);
	int pos;
	int i;
	int midic;
	String alte;
	int altesize;
	boolean found;
	for (I_Drawable note : notes) {
		midic = ((RNote) ((RNoteDrawable) note).ref).getMidic();
		alte = scale.getAlteration(midic);
		altesize = alte.length();
		if (alte != "") {
			pos = DemiDents(midic, staff,scale);
			i = 1;
			found = false;
			while (found == false) {
				if (! (taked.contains(new Point2D(i,pos)) ||
						taked.contains(new Point2D(i,pos+3)) ||
						taked.contains(new Point2D(i,pos-3))  ||
						taked.contains(new Point2D(i,pos+2)) ||
						taked.contains(new Point2D(i,pos-2)) ||
						taked.contains(new Point2D(i,pos+1)) ||
						taked.contains(new Point2D(i,pos-1)))) {
					found = true;
					taked.add(new Point2D(i,pos));
					taked.add(new Point2D(i,pos+1));
					taked.add(new Point2D(i,pos-1));
					taked.add(new Point2D(i,pos+2));
					taked.add(new Point2D(i,pos-2));
					taked.add(new Point2D(i,pos+3));
					taked.add(new Point2D(i,pos-3));
					((RNoteDrawable) note).deltaAlt = i;
				}
				i++;
			}
		}
	}
}

public void setStemDir (double center) {
	Point2D amb = ((RChord) ref).getAmbitus();
	double moyenne = amb.getX() + ((amb.getY() - amb.getX())/2);
	if (moyenne < center * 100)
		stemdir = "up";
	else
		stemdir = "dw";
}

/////////////////////////////////////////////

public void drawObject(I_Render g, FXCanvas panel, Rectangle rect, 
		MusicalParams params, StaffSystem staffsys, List<I_Drawable> selection, double x0, double deltax) {
	int size = params.fontsize.get();
	for (I_Drawable note : getInside()) {
		note.drawObject (g, panel, rect, params, staffsys, selection, x0 + (((RNoteDrawable) note).centerX * size), deltax);
		}
	collectRectangle();
	if (stem_p && beamsnumber != -1) {
		drawStem(g, x0 + deltax + size * 0.278, y(), w(), h() - (size / 6), size);
	}
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


/*@Override
public I_Drawable getClickedObject(double x, double y) {
	SimpleDrawable rep = null;
	for (I_Drawable note : getInside()) {
		if (rep == null)
			rep = ((NoteDrawable) note).getClickedObject(x, y);
		}
	if (rep == null) {
		Rectangle rect = getRectangle();
		if (rect.contains((int) x,(int) y))
			rep = ref;
	}
	return rep;
}
*/

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
public void collectTemporalObjects(List<SpacedPacket> timelist) {
	  timelist.add(new SpacedPacket(this, this.ref.getOnsetMS()));
}

}

}
