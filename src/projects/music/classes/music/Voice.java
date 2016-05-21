package projects.music.classes.music;

import java.util.ArrayList;
import java.util.List;

import kernel.annotations.Ombuilder;
import kernel.annotations.Omclass;
import kernel.tools.Fraction;
import projects.music.classes.abstracts.MusicalObject;
import projects.music.classes.abstracts.RTree;
import projects.music.classes.abstracts.Sequence_S_MO;
import projects.music.classes.interfaces.I_RT;
import projects.music.classes.music.Group.GroupDrawable;
import projects.music.classes.music.Measure.MeasureDrawable;
import projects.music.classes.music.RChord.RChordDrawable;
import projects.music.classes.music.Rest.RestDrawable;
import projects.music.editors.MusicalControl;
import projects.music.editors.MusicalEditor;
import projects.music.editors.MusicalPanel;
import projects.music.editors.MusicalParams;
import projects.music.editors.MusicalTitle;
import projects.music.editors.drawables.ContainerDrawableS;
import projects.music.editors.drawables.I_Drawable;
import resources.json.JsonRead;


@Omclass(icon = "223", editorClass = "projects.music.classes.music.Voice$VoiceEditor")
public class Voice extends Sequence_S_MO implements I_RT {
	List<RChord> originalchords;
	public List<RTree> treelist;
	@Ombuilder
	public Voice (List<RTree> thetree, List<RChord> thechords, int tempo) {
		treelist = thetree;
		chords = thechords;
		originalchords = chords;
		RChord lastchord = chords.get(0);
		setTempo(tempo);
		fillVoice(lastchord, tempo);
	}
	
	public RTree defrt () {
		List<RTree> group = new ArrayList<RTree>();
		group.add(new RTree(1,null));
		group.add(new RTree(-1,null));
		group.add(new RTree(1,null));
		RTree gt = new RTree(1,group);
		List<RTree> rep = new ArrayList<RTree>();
		rep.add(new RTree(1,null));
		rep.add(new RTree(-1,null));
		rep.add(gt);
		rep.add(new RTree(1,null));
		return new RTree(new Fraction (2,4),rep);
	}
	
	/*public Voice () {
		List<RTree> mes = new ArrayList<RTree>();
		for (int i=0 ; i<5;i++){
			mes.add(defrt());
		}
		treelist = mes;
		chords = new ArrayList<RChord>();
		chords.add(new RChord());
		RChord lastchord = chords.get(0);
		setTempo(60);
		fillVoice(lastchord, 60);
		
	}*/
	
	public Voice () {
		Voice copie = (Voice) JsonRead.getFile("/Users/agonc/JAVAWS/organum/invoice.json");
		treelist = copie.treelist;
		chords = copie.originalchords;
		originalchords=copie.originalchords; 
		System.out.println("chords " + chords);
		setTempo(copie.getTempo());
		RChord lastchord = chords.get(0);
		fillVoice(lastchord, 60);
	}
	
	public void fillVoice (RChord lastchord, int tempo) {
		Fraction mesonset = new Fraction (0);
		for (RTree item : treelist) {
			int hmchords = item.countChords ();
			Measure mes = new Measure (item , chords, tempo, lastchord);
			mes.setQoffset(mesonset);
			mesonset = Fraction.addition(mesonset, item.dur.abs());
			addElement(mes);
			if (hmchords > 0) {
				nextChords(hmchords, lastchord);
				lastchord = chords.get(0);
			}
		}
		setQDurs(mesonset);
	}
	
//////////////////////////////////////////////////
public I_Drawable makeDrawable (MusicalParams params) {
return new VoiceDrawable (this, params);
}


//////////////////////EDITOR//////////////////////
public static class VoiceEditor extends MusicalEditor {


@Override
public String getPanelClass (){
return "projects.music.classes.music.Voice$VoicePanel";
}

@Override
public String getControlsClass (){
return "projects.music.classes.music.Voice$VoiceControl";
}

@Override
public String getTitleBarClass (){
return "projects.music.classes.music.Voice$VoiceTitle";
}

}

//////////////////////PANEL//////////////////////
public static class VoicePanel extends MusicalPanel {

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
public static class VoiceControl extends MusicalControl {

}	

//////////////////////TITLE//////////////////////
public static class VoiceTitle extends MusicalTitle {

}	

//////////////////////DRAWABLE//////////////////////

public static class VoiceDrawable extends ContainerDrawableS {


public VoiceDrawable (Voice theRef, MusicalParams params) {
	ref = theRef;
	initVoiceDrawable(theRef,  params);
	makeSpaceObjectList();
	System.out.println("elementos : " + theRef.getElements());
}	

public void initVoiceDrawable (Voice theRef, MusicalParams params){
	ref = theRef;
	for (MusicalObject obj : theRef.getElements()) {
		MeasureDrawable gchord = new MeasureDrawable ((Measure) obj, params);
		inside.add(gchord);
		gchord.setFather(this);
	}	
  }	
}
//////////////////////////////////////////////////////////////////////

}

