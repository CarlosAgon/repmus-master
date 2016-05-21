package projects.music.classes.interfaces;

import projects.music.classes.abstracts.MusicalObject;
import kernel.I_OMObject;

public interface I_MusicalObject extends I_OMObject{

	public long getOffset ();
	public void setOffset(long offset);
	
	public long getDuration ();
	public void setDuration(long dur);
	
	public MusicalObject getFather();
	public void setFather(MusicalObject container);
	
	public long getOnsetMS ();
	
}
