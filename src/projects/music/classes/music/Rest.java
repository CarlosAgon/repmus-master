package projects.music.classes.music;

import gui.FX;
import gui.FXCanvas;
import gui.renders.I_Render;

import java.util.List;

import com.sun.javafx.geom.Rectangle;

import kernel.annotations.Ombuilder;
import kernel.annotations.Omclass;
import kernel.tools.Fraction;
import kernel.tools.ST;
import projects.music.classes.abstracts.Simple_S_MO;
import projects.music.classes.abstracts.Strie_MO;
import projects.music.classes.interfaces.I_InRSeqChord;
import projects.music.classes.interfaces.I_RT;
import projects.music.editors.MusChars;
import projects.music.editors.MusicalControl;
import projects.music.editors.MusicalEditor;
import projects.music.editors.MusicalPanel;
import projects.music.editors.MusicalParams;
import projects.music.editors.MusicalTitle;
import projects.music.editors.SpacedPacket;
import projects.music.editors.StaffSystem;
import projects.music.editors.drawables.I_Drawable;
import projects.music.editors.drawables.SimpleDrawable;

@Omclass(icon = "142", editorClass = "projects.music.classes.music.Rest$RestEditor")
public class Rest extends Simple_S_MO implements I_RT, I_InRSeqChord {
	
	//tester avec 1 5 23 1/2 1/3 1/4 1/12 1/21 1/1000
	
	public Rest () {
		this (new Fraction(1,4));
	}
	 
	@Ombuilder
	public Rest (Fraction  dur) {
		super ();
		setQDurs(dur);
		
	}
	 
	@Ombuilder
	public Rest (Fraction  dur, double tempo) {
		super ();
		setTempo(tempo);
		setQDurs(dur);
		
	}
	
	/////////////////////////////////////////////
		
	public I_Drawable makeDrawable (MusicalParams params) {
		return new RestDrawable (this, params);
	}
	
//////////////////////////////////////////////////
//////////////////////EDITOR//////////////////////
//////////////////////////////////////////////////
public static class RestEditor extends MusicalEditor {


@Override
public String getPanelClass (){
return "projects.music.classes.music.Rest$RestPanel";
}

@Override
public String getControlsClass (){
return "projects.music.classes.music.Rest$RestControl";
}

@Override
public String getTitleBarClass (){
return "projects.music.classes.music.Rest$RestTitle";
}

}

//////////////////////PANEL//////////////////////
public static class RestPanel extends MusicalPanel {

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
public static class RestControl extends MusicalControl {

}	

//////////////////////TITLE//////////////////////
public static class RestTitle extends MusicalTitle {

}	
	
//////////////////////DRAWABLE//////////////////////
public static class RestDrawable extends SimpleDrawable {
	public String head = MusChars.rest_4;
	public int points = 0;
	
	public RestDrawable (Rest theRef, MusicalParams params) {
		ref = theRef;
		setHeadAndPoints(((Rest) ref).getQDurs());
		System.out.println ("este es head " + head);
	}	
	
	public void drawObject(I_Render g, FXCanvas panel, Rectangle rect, 
			MusicalParams params, StaffSystem staffsys, List<I_Drawable> selection, double x0, double deltax) {
		double x = x0 + deltax;
		FX.omDrawString(g, x, 10, head);
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
		if (val.value() > 1) {
			System.out.println ("aqui " );
				bigchord = val;
				rep = MusChars.rest_4;
			} else if (val.equals(Fraction.FOUR)) rep =  MusChars.rest_4;
			else if (val.equals(Fraction.TWO)) rep =  MusChars.rest_2;
			else if (val.equals(Fraction.ONE)) rep =  MusChars.rest_1;
			else if (val.equals(Fraction.f1_2)) rep =  MusChars.rest_1_2;
			else if (val.equals(Fraction.f1_4)) rep =  MusChars.rest_1_4;
			else if (val.equals(Fraction.f1_8)) rep =  MusChars.rest_1_8;
			else if (val.equals(Fraction.f1_16)) rep =  MusChars.rest_1_16;
			else if (val.equals(Fraction.f1_32)) rep =  MusChars.rest_1_32;
			else if (val.equals(Fraction.f1_64)) rep =  MusChars.rest_1_64;
			else if (val.equals(Fraction.f1_128)) rep =  MusChars.rest_1_128;
			else rep =  MusChars.rest_1_128; // a voir
		return rep;
	}
}
//////////////////////////////////////////////////////
}


