package kernel.frames.simples;


import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import kernel.frames.simples.ClassBoxFrame.BoxClassCanvas;
import kernel.interfaces.I_Box;
import kernel.interfaces.I_SimpleFrame;
import kernel.metaobjects.ClassBox;
import gui.FX;
import gui.FXPane;

public class BoxFrame extends FXPane implements I_SimpleFrame{
	boolean inputs = false;
	boolean outputs = true;
	boolean shoxname = false;
	I_Box object;
	Image icon;
	ResizeBox resizebox;
	
	public List<InputBoxFrame> inputballs = new ArrayList<InputBoxFrame>();
	public List<OutputBoxFrame> outputballs  = new ArrayList<OutputBoxFrame>();
	
	public BoxFrame (I_Box thebox, Point2D pos, Point2D size, String theicon) {
		super();
	}
	
	public void addResize (double minx, double miny) {
		resizebox = new ResizeBox (minx,miny);
		resizebox.omSetViewSize(10,10);
		resizebox.omSetViewPosition(w()-10,h()-10);
		omAddSubviews(resizebox);
		
	}
	
	public void setNewSize (double w, double h) { 
		omSetViewSize(w,h);
	}
	
	public boolean draggable (){
		return true;
	}
			
	public void omStartDragDrop (double x, double y) {
		Dragboard db = delegate.startDragAndDrop(TransferMode.MOVE);
		db.setDragView(dragImage (), 0,0);
		ClipboardContent content = new ClipboardContent();
		content.putString("SHEET_KEY");
		FX.draggingListObj.add(this);
		db.setContent(content);
	}
	
	
	public void setSelected (boolean sel){
		object.setSelected(sel);
		if (sel) {
			final DropShadow shadow = new DropShadow();
	        shadow.setColor(Color.FORESTGREEN);
	        final Glow glow = new Glow();
	        glow.setInput(shadow);
	        omGetDelegate().setEffect(glow);
		} else {
			omGetDelegate().setEffect(null);
		}	
	}
	
	public void changePreviewMode () {	
	}
	
	public void moveFrameDelta (int dir) {	
		if (FX.getAltKey())
			moveMiniView(dir);
		else{
			int pixnum;
			if (FX.getShiftKey()) pixnum = 10; else pixnum =1;
			switch (dir) {
			 case 0 : omSetViewPosition(x(), Math.max(y()-pixnum, 0)); break;
			 case 1 : omSetViewPosition(x(), y()+pixnum); break;
			 case 2 : omSetViewPosition(Math.max(x()-pixnum, 0), y()); break;
			 case 3 : omSetViewPosition(Math.max(x()+pixnum, 0), y()); break;
			}
		}
	}
	
	public void moveMiniView (int dir) {	
	}
	
	/* 
			     (let ((pixnum (if (om-shift-key-p) 10 1)) new-position)
			       (setf new-position
			             (borne-position (case dir
			               (0 (om-subtract-points (om-view-position self) (om-make-point 0 pixnum)))
			               (1 (om-add-points (om-view-position self) (om-make-point 0 pixnum)))
			               (2 (om-add-points (om-view-position self) (om-make-point pixnum 0)))
			               (3 (om-subtract-points (om-view-position self) (om-make-point pixnum 0))))))
			       
			       (om-set-view-position self new-position)
			       (setf (frame-position (object self)) new-position))))*/
			       
			       
	public void omMousePressed (double x, double y) {
		if (FX.getShiftKey())
			setSelected(! object.getSelected());
		else
			setSelected(true);
	}
	
	
	public void evalBox (){
		System.out.println("youpii eval " + ((ClassBox) object));
	}
	

}
