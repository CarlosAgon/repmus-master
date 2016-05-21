package projects.music.editors;

import gui.FX;
import gui.dialogitems.OmMenu;
import gui.dialogitems.OmMenuItem;
import gui.renders.GCRender;
import gui.renders.I_Render;

import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.sun.javafx.geom.Rectangle;

import projects.music.classes.abstracts.MusicalObject;
import projects.music.editors.StaffSystem.MultipleStaff;
import projects.music.editors.drawables.I_Drawable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.effect.Light.Distant;
import javafx.scene.image.Image;
import kernel.frames.views.PanelCanvas;

public class MusicalPanel extends PanelCanvas implements I_MusicalPanel{
	
	public int size;
	public StaffSystem staffSystem;
	public Scale scale;
	public Font theFont;
	List<I_Drawable> selection = new ArrayList<I_Drawable>();
	public I_Drawable graphObj;
	
	public MusicalPanel () {
		super();
	}
	
	public void init () {
		super.init();
		MusicalEditor ed = (MusicalEditor) getEditor();
		MusicalParams params = (MusicalParams) ed.getParams();
		size = params.fontsize.get();
		staffSystem = StaffSystem.getStaffSystem (params.staff.get(), size);
		scale = Scale.getScale (params.scale.get());
		updatePanel(true);
	}
	
	public String [] getSlotList () {
		String [] rep = {"midic","channel","dur","dyn","port"};
		return rep;
	}
			 	
	@Override
	public void omSetViewSize(double w, double h) {
		super.omSetViewSize(w,h);
		updatePanel(false);
	}

	
	public void setGraphObject (I_Drawable Obj) {
		graphObj  = Obj;
	}
	
	public I_Drawable getGraphObject () {
		return graphObj ; 
	}
	
	public StaffSystem  getStaffSystem () {
		return staffSystem ; 
	}

	@Override
	public void updatePanel (boolean changedObject_p){

		MusicalEditor ed = (MusicalEditor) getEditor();

		System.out.println("entro aqui : " + (MusicalObject) ed.getObject() );
		if (ed != null) {
			MusicalParams params = (MusicalParams) ed.getParams();
			MusicalObject object = (MusicalObject) ed.getObject();
			if (changedObject_p){
				setGraphObject(object.makeDrawable(params));
		}
		GCRender gc = getGCRender();
        omViewDrawContents(gc);
		}
	}

	
	public int getZeroPosition  () {
		return 1;
	}
	
	public boolean showTempo_p () {
		return false;
	}
	
	public void omViewDrawContents (I_Render g) {
		super.omViewDrawContents(g);
		MusicalEditor ed = (MusicalEditor) getEditor();
		MusicalParams params = (MusicalParams) ed.getParams();
		g.omSetFont(params.getFont("headSize"));
		if (params.pagemode.get())
		 System.out.println ("en mode page");
		else{
			viewDrawLinear(g);
			}
		}
	
	public void viewDrawLinear (I_Render g) {
		MusicalEditor ed = (MusicalEditor) getEditor();
		MusicalParams params = (MusicalParams) ed.getParams();
		int size = params.fontsize.get();
		double deltax = this.getZeroPosition()*size;
		FX.omEraseRectContent(g, 0,0, w(), h());
		drawLineSystem(g, params);
		Rectangle rect = new Rectangle (0, 0 ,(int) w() ,(int) h());
		graphObj.consTimeSpaceCurve (size,  deltax,  params.zoom.get());
		graphObj.drawObject (g,  this, rect, params, staffSystem, selection, 0 , deltax);
	}

	
	
	public void drawLineSystem (I_Render g, MusicalParams params) {
		boolean tempo = showTempo_p ();
		int size = params.fontsize.get();
		Font oldFont = g.omGetFont();
		g.omSetFont(params.getFont("singSize"));
		staffSystem.draw(g, 0, size*4, w() , size/4, tempo, false);
		g.omSetFont(oldFont);
	}

	
	//////////////////MenuContext////////////////////
	@Override
	public List<MenuItem> getTheContextMenu (){
		List<MenuItem> rep = new ArrayList<MenuItem>();
		OmMenuItem [] modelist = {new OmMenuItem("Normal", e-> {changeScoreMode (0);} ),
				new OmMenuItem("Page", e-> {changeScoreMode (0);} )};
		rep.add ((OmMenu) new OmMenu ("Mode", modelist));
		rep.add ((MenuItem) new OmMenuItem ("Set Score Margins", e-> {changeScoreMode (0);}));
		rep.add ((MenuItem) new OmMenuItem ("Eval", e-> {changeScoreMode (0);}));
		return rep;
	}
	
	public void changeScoreMode (int mode){
	}
	

	/*(defun simple-menu-context (object)
			  (remove nil (append 
			               (list  
			                (list 
			               
			                (if (analysis-mode? (panel object))
			                    (analysis-menu-items object)
			                  (list 
			                   (list 
			                    (om-new-leafmenu "Set Score Margins"
			                                     #'(lambda () (editor-page-setup (panel object)))))
			                   (om-new-leafmenu "Eval"
			                                #'(lambda () (eval-score (panel object)))))))))))


			(defmethod om-get-menu-context ((self scorepanel))
			  (let ((selection (get-click-in-obj self (graphic-obj self) 
			                                 'contex ;(grap-class-from-type (obj-mode self))
			                                 (om-mouse-position self))))
			    (if selection
			        (om-get-menu-context selection)
			      (om-get-menu-context (editor self)))))
			      
			      */
	
	////////////////////HANDLE KEY EVENT/////////////
	public void KeyHandler (String car) {
			 switch (car) {
				 case "space" : System.out.println ("(editor-play/stop (editor self)))"); break;
				 case "c": delegate.setScaleX( delegate.getScaleX() * 1.1); break;
				 case "n" : System.out.println ("(set-name-to-mus-obj self))"); break;
				 case "h" : System.out.println ("show-help-window"); break;
				 case "t" : System.out.println ("score-set-tonalite"); break;
				 case "T" : System.out.println ("score-remove-tonalite"); break;
				 case "tab" : System.out.println ("change-obj-mode "); break;
				 case "up" :  moveSelection(0); break;
				 case "down" : moveSelection(1); break;
				 
			 }
	}
	/*
				 		
			           (#\s (create-editor-scale (editor self)))
			         
			           (otherwise (if (selection? self)
			                          (if (char-is-digit char) (do-subdivise self char)
			                            (case char
			                              (:om-key-left (cond ((extra-p (car (selection? self)))
			                                                   (advance-extras self -1))
			                                                  ((equal (slots-mode self) 'dur)
			                                                   (change-dur self -1))
			                                                  (t (change-x self -1))))
			                              (:om-key-right (cond ((extra-p (car (selection? self)))
			                                                    (advance-extras self 1))
			                                                   ((equal (slots-mode self) 'dur)
			                                                    (change-dur self 1))
			                                                   (t (change-x self 1))))
			                              (#\C  (set-color-to-mus-obj self))
			                           
			                              (:om-key-delete (delete-selection self))
			                              (:om-key-esc (toggle-selection self))
			                              (#\+ (do-union self))
			                              (#\* (do-group self))
			                              (#\- (un-group self))
			                           ;(#\= (if (om-option-key-p) (untie-selection self) (tie-selection self)))
			                              (#\= (tie-selection self))
			                              (#\/ (untie-selection self))
			                              (#\o (open-internal-editor self))
			                              ;; (#\/ (subdivise-edit-cursor self)) ;a faire
			                           
			                              (otherwise (om-beep))))
			                        (case char
			                          (:om-key-esc (reset-cursor self)))
			                        ))
			           )
			         (when (editor self) (update-inspector (editor self) 0))
			         )))
}


*/
///////////////////////////////////////////////////////
/////////////////////////////Click/////////////////////
///////////////////////////////////////////////////////
	
	public void offStaffSelection () {
		staffSystem.offSelected ();
	}
	
	public void selectStaffWithShift (MultipleStaff staffSelection) {
		staffSelection.setSelected(! staffSelection.getSelected());
        updatePanel(false);
	}
	
	public void offObjSelection () {
		selection = new ArrayList<I_Drawable>();
	}
	
	public void selectObjWithShift (I_Drawable obj) {
		selection.add(obj);
        updatePanel(false);
	}
	
	
		
	//		  (let ((selected-things (get-graph-selection? self (grap-class-from-type (obj-mode self)))))

/*	@Override
	public void omViewClickEvent (double x, double y) {
		MusicalEditor ed = (MusicalEditor) getEditor();
		MusicalParams params = ed.getParams();
		int size = params.getSize();
		MultipleStaff staffSelection = staffSystem.ClickInKey (x,y,size);
		if (staffSelection != null){
			if (FX.getShiftKey())
				selectStaffWithShift(staffSelection);
			else {
				offStaffSelection();
				staffSelection.setSelected(true);
	            updatePanel(false);
			}
		}
		else {
			MusicalObject objselected = graphObj.getClickedObject (x,y);
			if (objselected != null){
				if (FX.getShiftKey())
					selectObjWithShift(objselected);
				else {
					offObjSelection();
					selection = new ArrayList<MusicalObject>();
					selection.add(objselected);
		            updatePanel(false);
					}
			}
			else {
				offStaffSelection();
				offObjSelection();
				updatePanel(false);
				ed.setMouvableObj(new MouvableRectangle () );
				System.out.println("-----> system " + staffSelection);
			}
		}	
	 }

	
	////////////////////ACTIONS//////////////
	@Override
	public void addNewObject(double y) {
		// TODO Auto-generated method stub
		
	}
	
		*/
	
	public void moveSelection (int dir) {
		int delta = 0;
		if (FX.getShiftKey())
			delta = 1200;
		else if (FX.getCommandKey())
			delta = 700;
		else delta = Math.round(scale.getApprox ());
		if (dir == 1)
			delta = delta * -1;
	/*	for (MusicalObject item : selection) {
			((Note) item).transpose (delta);
		}
	*/
		updatePanel (true);
	}

	//////////////////////////////////////////////////////////
	@Override
	public void addNewObject(double x) {
		// TODO Auto-generated method stub
	}

			 
}