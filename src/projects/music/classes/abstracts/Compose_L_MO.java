package projects.music.classes.abstracts;

import java.util.ArrayList;
import java.util.List;

public class Compose_L_MO extends Lisse_MO {
	public List<MusicalObject> elements = new ArrayList<MusicalObject>();
	
	
	////////////////////////////////////////////////////////
	////////////////INTERFACE
	////////////////////////////////////////////////////////


	public List<MusicalObject> getElements() {
		return elements;
	}

	public void addElement(MusicalObject elem) {
		elements.add(elem);
		elem.setFather(this);
	}
	
	public void addElement(List<MusicalObject> elems) {
		for (MusicalObject cont : elems) {
			elements.add(cont);
		}
	}

	public void  removeElement (MusicalObject elem) {
		elements.remove(elem);
	}

	public void removeElement(List<MusicalObject> elems) {
		for (MusicalObject cont : elems) {
			elements.remove(cont);
		}
	}


	public void removeAllelements() {
		elements = new ArrayList<MusicalObject>();;
	}
	
	public void setDurSeq () {
		long dur = 0;
		for (MusicalObject obj : getElements())
			dur = dur + obj.getDuration ();
		setDuration(dur);
		}

		public void setDurPar () {
		long max = 0;
		for (MusicalObject obj : getElements())
			max = Math.max(max, obj.getOffset() + obj.getDuration ());
		setDuration(max);
		}
	
}
