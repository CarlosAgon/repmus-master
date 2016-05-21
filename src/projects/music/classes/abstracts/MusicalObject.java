package projects.music.classes.abstracts;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.Rectangle;

import gui.FXCanvas;
import gui.renders.I_Render;
import javafx.scene.text.Font;
import kernel.annotations.Omvariable;
import kernel.frames.views.I_EditorParams;
import projects.music.classes.interfaces.I_MusicalObject;
import projects.music.editors.MusicalParams;
import projects.music.editors.StaffSystem;
import projects.music.editors.drawables.I_Drawable;

public class MusicalObject implements I_MusicalObject{
	
	long offset = 0; //offset in ms
	
	@Omvariable
	long dur = 0; //duration in ms
	private MusicalObject father = null;

	@Override
	public long getOffset() {
		return offset;
	}

	@Override
	public void setOffset(long theoffset) {
		offset = theoffset;
	}

	@Override
	public long getDuration() {
		return dur;
	}
	

	@Override
	public void setDuration(long thedur) {
		dur = thedur;
	}
	
	@Override
	public MusicalObject getFather() {
		return father;
	}
	@Override
	public void setFather(MusicalObject container) {
		father = container;
	}
	
	public MusicalParams getParams() {
		return new MusicalParams();
	}
	
	@Override
	public long getOnsetMS () {
		if (getFather() == null)
			return offset ;
		else
			return offset + getFather().getOnsetMS() ;
	}
	
	
	
	/////////////////////////////

	public  void drawPreview (I_Render g, FXCanvas canvas, double x, double x1, double y, double y1, I_EditorParams edparams) {
		MusicalParams params = (MusicalParams) edparams;
		int size = params.fontsize.get();
		g.omSetFont(params.getFont("headSize"));
		List<I_Drawable> selection = new ArrayList<I_Drawable> ();
		StaffSystem staffSystem = StaffSystem.getStaffSystem (params.staff.get(), size);
		boolean tempo = params.showtempo.get();
		Font oldFont = g.omGetFont();
		g.omSetFont(params.getFont("singSize"));
		staffSystem.draw(g, 0, size*1, (x1 -x) , size/4, tempo, false);
		g.omSetFont(oldFont);
		
		double deltax = 2*size; //double deltax = this.getZeroPosition()*size;
		Rectangle rect = new Rectangle ((int) x, (int) y ,(int) (x1 -x) ,(int) (y1 - y));
		I_Drawable graphObj = makeDrawable(params);
		graphObj.drawObject (g,  canvas, rect, params, staffSystem, selection, 0 , deltax);
	}

	public I_Drawable makeDrawable (MusicalParams params) {
		return null;
	}

}
