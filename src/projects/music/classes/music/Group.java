package projects.music.classes.music;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import kernel.annotations.Ombuilder;
import kernel.annotations.Omclass;
import kernel.tools.Fraction;
import projects.music.classes.abstracts.Compose_S_MO;
import projects.music.classes.abstracts.MusicalObject;
import projects.music.classes.abstracts.RTree;
import projects.music.classes.abstracts.Sequence_S_MO;
import projects.music.classes.interfaces.I_InRSeqChord;
import projects.music.classes.interfaces.I_RT;
import projects.music.classes.music.RChord.RChordDrawable;
import projects.music.classes.music.Rest.RestDrawable;
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
import projects.music.editors.drawables.SimpleDrawable;

@Omclass(icon = "226", editorClass = "projects.music.classes.music.RChord$RChordEditor")
public class Group extends Sequence_S_MO implements I_RT, I_InRSeqChord {
	
	@Ombuilder
	public Group (RTree thetree, List<RChord> thechords, int tempo) {
		tree = thetree;
		chords = thechords;
		setTempo(tempo);
		thetree.setDurChilds();
		setQDurs(tree.dur);
		fillGroup(chords.get(0), tempo);
	}
	
	public Group (RTree thetree, List<RChord> thechords, int tempo, RChord lastchord) {
		tree = thetree;
		chords = thechords;
		setTempo(tempo);
		setRTdur(tree.dur);
		setRTproplist(tree.proplist);
		fillGroup(lastchord, tempo);
	}
	
	public Group () {
		List<RTree> prop = new ArrayList<RTree>();
		prop.add(new RTree(1,null));
		prop.add(new RTree(-1,null));
		prop.add(new RTree(1,null));
		tree = new RTree(new Fraction (1),prop);
		chords = new ArrayList<RChord>();
		chords.add(new RChord());
		setTempo(60);
		setRTdur(tree.dur);
		setRTproplist(tree.proplist);
		fillGroup(chords.get(0), 60);
	}
	
	
	public void fillGroup (RChord lastchord, int tempo) {
		Fraction objonset = new Fraction(0);
		Fraction objdur;
		for (RTree item : tree.getProporsitons()) {
			int hmchords = item.countChords ();
			if (item.isFigure()) {
				if (item.isRest()) {
					Rest newrest = new Rest ();
					newrest.setTempo(tempo);
					newrest.setQoffset(objonset);
					objdur = item.dur.abs();
					newrest.setQDurs(objdur);
					addElement(newrest);
				}
				else {
					RChord newchord;
					if (item.cont_p)
						newchord = new RChord (item.dur, lastchord, tempo, item.cont_p);
					else
						newchord = new RChord (item.dur, chords.get(0), tempo, item.cont_p);
					newchord.setQoffset(objonset);
					objdur = item.dur;
					addElement(newchord);
				}
			}
			else {
				if (item.isGrace()) {
					objdur = new Fraction(0);
				}
				else {
					Group newgroup = new Group (item , chords, tempo, lastchord);
					newgroup.setQoffset(objonset);
					objdur = item.dur;
					addElement(newgroup);
				}
			}
			objonset = Fraction.addition (objonset, objdur.abs());
			if (hmchords > 0) {
				nextChords(hmchords, lastchord);
				lastchord = chords.get(0);
			}
		}
	}
	
	
	/*(defmethod get-group-ratio ((self group))
			   (let* ((tree (tree self))
			          (extent (car tree))
			          (addition (loop for item in (second tree) sum (floor (abs (if (listp item) (car item) item))))))
			     (cond
			      ((= (round (abs addition)) 1) nil)
			      ((integerp (/ extent addition)) addition)
			      ;; never happen
			      ((and (integerp (/ extent addition)) 
			             (or (power-of-two-p (/ extent addition))
			                 (and (integerp (/ addition extent)) 
			                      (power-of-two-p (/ addition extent)))))  nil)
			      (t addition))))
			      */
			      
			      
	//////////////////////////////////////////////////
	public I_Drawable makeDrawable (MusicalParams params) {
		return new GroupDrawable (this, params);
	}
	

//////////////////////EDITOR//////////////////////
public static class GroupEditor extends MusicalEditor {


@Override
public String getPanelClass (){
return "projects.music.classes.music.Group$GroupPanel";
}

@Override
public String getControlsClass (){
return "projects.music.classes.music.Group$GroupControl";
}

@Override
public String getTitleBarClass (){
return "projects.music.classes.music.Group$GroupTitle";
}

}

//////////////////////PANEL//////////////////////
public static class GroupPanel extends MusicalPanel {

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
public static class GroupControl extends MusicalControl {

}	

//////////////////////TITLE//////////////////////
public static class GroupTitle extends MusicalTitle {

}	

//////////////////////DRAWABLE//////////////////////

public static class GroupDrawable extends ContainerDrawableS {
	Fraction numdenom;
	int chiflevel;
	int dirgroup;

	public GroupDrawable (Group theRef, MusicalParams params) {
			ref = theRef;
			initGroupDrawable(theRef,  params);
			makeSpaceObjectList();
			}	
	
	public void initGroupDrawable (Group theRef, MusicalParams params){
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
