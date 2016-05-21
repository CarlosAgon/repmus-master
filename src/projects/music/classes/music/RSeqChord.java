package projects.music.classes.music;

import java.util.ArrayList;
import java.util.List;

import kernel.annotations.Ombuilder;
import kernel.annotations.Omclass;
import kernel.tools.Fraction;
import projects.music.classes.abstracts.MusicalObject;
import projects.music.classes.abstracts.Sequence_S_MO;
import projects.music.classes.interfaces.I_RT;
import projects.music.classes.music.Group.GroupDrawable;
import projects.music.classes.music.RChord.RChordDrawable;
import projects.music.classes.music.Rest.RestDrawable;
import projects.music.editors.MusicalControl;
import projects.music.editors.MusicalEditor;
import projects.music.editors.MusicalPanel;
import projects.music.editors.MusicalParams;
import projects.music.editors.MusicalTitle;
import projects.music.editors.drawables.ContainerDrawableS;
import projects.music.editors.drawables.I_Drawable;

@Omclass(icon = "226", editorClass = "projects.music.classes.music.RSeqChord$RSeqChordEditor")
public class RSeqChord extends Sequence_S_MO implements I_RT {
	
	@Ombuilder
	public RSeqChord (List<I_RT> objs) {
		fillSeq(objs);
	}		
	
	public void fillSeq (List<I_RT> objs) {
		Fraction objonset = new Fraction(0);
		for (I_RT item : objs) {
			if (item instanceof RChord) {
				addElement((RChord) item);
				((RChord) item).setQoffset(objonset);
				objonset = Fraction.addition (objonset, ((RChord) item).getQDurs());
			}
			else if (item instanceof Rest){
				addElement((Rest) item);
				((Rest) item).setQoffset(objonset);
				objonset = Fraction.addition (objonset, ((Rest) item).getQDurs());
			} 
			else if (item instanceof Group){
				addElement((Group) item);
				((Group) item).setQoffset(objonset);
				objonset = Fraction.addition (objonset, ((Group) item).getQDurs());
			} 		
		}
	}	
	
	public RSeqChord () {
		List<I_RT> objs = new ArrayList<I_RT>();
		objs.add(new RChord());
		objs.add(new Rest());
		objs.add(new Group());
		fillSeq(objs);
	}
	
//////////////////////////////////////////////////
public I_Drawable makeDrawable (MusicalParams params) {
return new RSeqChordDrawable (this, params);
}


//////////////////////EDITOR//////////////////////
public static class RSeqChordEditor extends MusicalEditor {


@Override
public String getPanelClass (){
return "projects.music.classes.music.RSeqChord$RSeqChordPanel";
}

@Override
public String getControlsClass (){
return "projects.music.classes.music.RSeqChord$RSeqChordControl";
}

@Override
public String getTitleBarClass (){
return "projects.music.classes.music.Group$GroupTitle";
}

}

//////////////////////PANEL//////////////////////
public static class RSeqChordPanel extends MusicalPanel {

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
public static class RSeqChordControl extends MusicalControl {

}	

//////////////////////TITLE//////////////////////
public static class RSeqChordTitle extends MusicalTitle {

}	

//////////////////////DRAWABLE//////////////////////

public static class RSeqChordDrawable extends ContainerDrawableS {


public RSeqChordDrawable (RSeqChord theRef, MusicalParams params) {
ref = theRef;
initGroup(theRef,  params);
makeSpaceObjectList();
}	

public void initGroup (RSeqChord theRef, MusicalParams params){
ref = theRef;
for (MusicalObject obj : theRef.getElements()) {

if (obj instanceof RChord) {
RChordDrawable gchord = new RChordDrawable ((RChord) obj, params);
gchord.stem_p = false;
inside.add(gchord);
gchord.setFather(this);
}
else if (obj instanceof Rest)  {
RestDrawable grest = new RestDrawable ((Rest) obj, params);
inside.add(grest);
grest.setFather(this);
}
else if (obj instanceof Group)  {
GroupDrawable ggroup = new GroupDrawable ((Group) obj, params);
inside.add(ggroup);
ggroup.setFather(this);
}
}
}	

}
//////////////////////////////////////////////////////////////////////

}