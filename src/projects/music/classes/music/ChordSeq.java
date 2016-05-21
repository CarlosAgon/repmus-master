package projects.music.classes.music;

import java.util.ArrayList;
import java.util.List;

import kernel.annotations.Ombuilder;
import kernel.annotations.Omclass;
import kernel.annotations.Omvariable;
import kernel.tools.ST;
import projects.music.classes.abstracts.MusicalObject;
import projects.music.classes.abstracts.Parallel_L_MO;
import projects.music.classes.music.Chord;
import projects.music.classes.music.Chord.ChordDrawable;
import projects.music.editors.MusicalControl;
import projects.music.editors.MusicalEditor;
import projects.music.editors.MusicalPanel;
import projects.music.editors.MusicalParams;
import projects.music.editors.MusicalTitle;
import projects.music.editors.drawables.ContainerDrawableL;
import projects.music.editors.drawables.ContainerDrawableS;
import projects.music.editors.drawables.I_Drawable;
import resources.json.JsonRead;


@Omclass(icon = "138", editorClass = "projects.music.classes.music.ChordSeq$ChordSeqEditor")
public class ChordSeq extends Parallel_L_MO {
	@Omvariable
	public List<List<Integer>> lmidic = new ArrayList<List<Integer>>();	
	@Omvariable
	public List<Long> lonset = new ArrayList<Long>();
	@Omvariable
	public List<List<Integer>> lchan = new ArrayList<List<Integer>>();
	@Omvariable
	public List<List<Long>> ldur = new ArrayList<List<Long>>();
	@Omvariable
	public List<List<Integer>> lvel = new ArrayList<List<Integer>>();
	@Omvariable
	public List<List<Long>> loffset = new ArrayList<List<Long>>();
	@Omvariable
	public int legato = 0;
	
	@Ombuilder(definputs="((6000)), (0), ((1000)), ((80)), ((0)), ((1)), 100")
	public ChordSeq(List<List<Integer>> Lmidic, List<Long> Lonset,
			List<List<Long>> Ldur, List<List<Integer>> Lvel, List<List<Long>> Loffset,
			List<List<Integer>> Lchan, int Legato) {
		initChordSeq(Lmidic, Lonset, Ldur, Lvel,  Loffset, Lchan, Legato);
		
	}
	
	public ChordSeq() {
		ChordSeq cs  = (ChordSeq) JsonRead.getFile("chord-seq.json");
		initChordSeq(cs.lmidic, cs.lonset, cs.ldur, cs.lvel,  cs.loffset, cs.lchan, cs.legato);
	}
	
	public void initChordSeq (List<List<Integer>> Lmidic, List<Long> Lonset,
			List<List<Long>> Ldur, List<List<Integer>> Lvel, List<List<Long>> Loffset,
			List<List<Integer>> Lchan, int Legato)  {
		
		int sizevel = Lvel.size();
		int sizedur = Ldur.size();
		int sizechan = Lchan.size();
		int sizeoffset = Loffset.size();
		long [] adur = {1000};
		int [] avel = {60};
		int [] achan = {1};
		long [] aoffset = {1};
		
		List<Long> dur = ST.a2long (adur);
		List<Integer> vel = ST.a2l (avel);
		List<Integer> chan = ST.a2l (achan);
		List<Long> offset = ST.a2long (aoffset);
		for (int i=0; i< Lmidic.size(); i++){
			if (i < sizevel) vel = Lvel.get(i);
			if (i < sizedur) dur = Ldur.get(i);
			if (i < sizechan) chan = Lchan.get(i);
			if (i < sizeoffset) offset = Loffset.get(i);
			addElement(new Chord (Lmidic.get(i), vel,offset,dur,chan));
		}
		int i = 0;
		long onset = 0;
		long delta = 1000;

		int sizeonset = Lonset.size();

		for (MusicalObject chord : getElements()){
			if (i < sizeonset) onset = Lonset.get(i);
			else if (i == 0) onset = 0;
			else onset = onset + delta;
			if (i < sizeonset - 1) delta = Lonset.get(i+1) - onset;
			((Chord) chord).setOffset(onset);
			i++;
		}
		setDurSeq();
		legato = Legato;
		setSlots();
	}
	
	public void setSlots () {
		
		for (MusicalObject chord : getElements()) {
			lmidic.add(((Chord) chord).lmidic);	
			lonset.add(((Chord) chord).getOffset());
			lvel.add(((Chord) chord).lvel);	
			loffset.add(((Chord) chord).loffset);
			lchan.add(((Chord) chord).lchan);
			ldur.add(((Chord) chord).ldur);
		}
	}
	
	public I_Drawable makeDrawable (MusicalParams params) {
		return new ChordSeqDrawable (this, params);
	}
	
//////////////////////////////////////////////////
//////////////////////EDITOR//////////////////////
//////////////////////////////////////////////////
public static class ChordSeqEditor extends MusicalEditor {


@Override
public String getPanelClass (){
return "projects.music.classes.music.ChordSeq$ChordSeqPanel";
}

@Override
public String getControlsClass (){
return "projects.music.classes.music.ChordSeq$ChordSeqControl";
}

@Override
public String getTitleBarClass (){
return "projects.music.classes.music.ChordSeq$ChordSeqTitle";
}

}

//////////////////////PANEL//////////////////////
public static class ChordSeqPanel extends MusicalPanel {

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
public static class ChordSeqControl extends MusicalControl {

}	

//////////////////////TITLE//////////////////////
public static class ChordSeqTitle extends MusicalTitle {

}	

//////////////////////DRAWABLE//////////////////////

public class ChordSeqDrawable extends ContainerDrawableL {

public ChordSeqDrawable (ChordSeq theRef, MusicalParams params) {
	ref = theRef;
	for (MusicalObject chord : theRef.getElements()) {
		ChordDrawable gchord = new ChordDrawable ((Chord) chord, params);
		inside.add(gchord);
		gchord.setFather(this);
	}
	makeSpaceObjectList();
}

}


}
