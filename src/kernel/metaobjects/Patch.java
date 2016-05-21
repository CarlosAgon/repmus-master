package kernel.metaobjects;


import gui.FX;
import gui.dialogitems.DIFun;
import gui.dialogitems.OmMenu;
import gui.dialogitems.OmMenuItem;
import gui.dialogitems.OmStaticText;
import gui.dialogitems.OmTextEdit;
import gui.mouvables.DragRectangle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.geometry.Point2D;
import kernel.I_OMObject;
import kernel.frames.simples.BoxFrame;
import kernel.frames.simples.ConnectionLine;
import kernel.frames.simples.InputBoxFrame;
import kernel.frames.simples.OutputBoxFrame;
import kernel.frames.views.EditorView;
import kernel.frames.views.PanelView;
import kernel.frames.views.TitlePane;
import kernel.interfaces.I_Box;
import kernel.interfaces.I_ContainerFrame;
import kernel.interfaces.I_Frame;
import kernel.interfaces.I_SimpleFrame;
import launch.RepMus.OmScene;

public class Patch extends BasicMetaObject implements I_OMObject, Serializable {

	private static final long serialVersionUID = 1L;
	public  List<I_Box> boxes = new ArrayList<I_Box>();
	public String code;
	public List<Connection> connections = new ArrayList<Connection>();


	@Override
	public String editorClass() {
		return "kernel.metaobjects.Patch$PatchEditor";
	}

	public void addBoxClass (String clazz, String title, Point2D pos) {
     	I_Box box = null;
		try {
			box = new ClassBox (clazz, new Point2D (50,50), "NCercle");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (box != null) {
     	boxes.add (box);
     	addGraphicBox (box);
      }
 	}

	public void addBoxMethod (Method met, String title, Point2D pos) {
	     	I_Box box = null;
			box = new MethodBox (met, pos, title);
			if (box != null) {
	     	boxes.add (box);
	     	addGraphicBox (box);
	     }
	 }

	public void  addGraphicBox (I_Box box) {
     	if (omIseditorOpen()){
     		I_SimpleFrame frame = box.makeFrame();
    		((Box) box).boxframe = frame;
     		((PanelView) omGetEditor().getPanel()).omAddSubview(frame);
     		frame.omUpdateView(true);
     	}
     }


	 public void addConnection (Connection connection) {
		 connections.add(connection);
		 if (omIseditorOpen()){
	         	I_Frame framesource = ((Box) connection.source).boxframe;
	         	I_Frame frametarget = ((Box) connection.target).boxframe;
	         	InputBoxFrame inball = ((BoxFrame)frametarget).inputballs.get(connection.innum);
	         	OutputBoxFrame outball = ((BoxFrame)framesource).outputballs.get(connection.outnum);

	     		PatchPanel patchpanel = ((PatchPanel) omGetEditor().getPanel());
	     		((Pane) patchpanel.omGetDelegate()).getChildren().add(new ConnectionLine (inball, outball));
	     }
	 }

	 public void save () {
		 ObjectOutputStream oos = null;
		 try {
			 final FileOutputStream fichier = new FileOutputStream("elpatch.ser");
			 oos = new ObjectOutputStream(fichier);
			 oos.writeObject(this);
			 oos.flush();
		 } catch (final java.io.IOException e) {
			e.printStackTrace();
		 } finally {
			 try {
				 if (oos != null) {
					 oos.close();
				 }
			 } catch (final IOException ex) {
				 ex.printStackTrace();
			 }
		 }
	 }

	 public void load (OmScene scene) {
		 ObjectInputStream ois = null;
		 try {
			 final FileInputStream fichier = new FileInputStream("elpatch.ser");
			 ois = new ObjectInputStream(fichier);
			 final Patch elpatch = (Patch) ois.readObject();
			 EditorView patcheditor =  elpatch.omOpenEditor(null);
			 scene.addEditor(patcheditor, 1);
		 } catch (final java.io.IOException e) {
			 e.printStackTrace();
		 } catch (final ClassNotFoundException e) {
			 e.printStackTrace();
		 } finally {
			 try {
				 if (ois != null) {
					 ois.close();
				 }
			 } catch (final IOException ex) {
				 ex.printStackTrace();
			 }
		 }
	 }


	////////////////////////////////////////////////////////////////
	////EDITOR
	///////////////////////////////////////////////////////////////

	 public static class PatchTitle extends TitlePane {

	 }


	 public static class PatchEditor extends EditorView implements I_ContainerFrame {

	    	public  PatchEditor () {
	    		super ();
	    	}

	        @Override
	    	public String getPanelClass (){
	    		return "kernel.metaobjects.Patch$PatchPanel";
	    	}

	        @Override
	    	public String getTitleBarClass (){
	    		return "kernel.metaobjects.Patch$PatchTitle";
	    	}

	        public List<OmMenu> getMenu (){
	    		OmMenuItem zero = new OmMenuItem("Note", evt-> ((Patch) getObject()).addBoxClass ("projects.music.classes.music.Note",
	    				 "Note", new Point2D (150,50)));
	    		OmMenuItem uno = new OmMenuItem("NCercle", evt-> ((Patch) getObject()).addBoxClass ("projects.mathtools.classes.NCercle",
	    				 "NCercle", new Point2D (50,50)));
	    		OmMenuItem dos = new OmMenuItem("Save", evt-> {((Patch) getObject()).save ();});
	    		OmMenuItem tres = new OmMenuItem("Load", evt-> {((Patch) getObject()).load ((OmScene) omGetScene());});
	    		OmMenuItem cuatro = new OmMenuItem("Chord", evt-> ((Patch) getObject()).addBoxClass ("projects.music.classes.music.Chord",
	    				 "Chord", new Point2D (150,50)));
	    		OmMenuItem cinco = new OmMenuItem("ChordSeq", evt-> ((Patch) getObject()).addBoxClass ("projects.music.classes.music.ChordSeq",
	    				 "ChordSeq", new Point2D (150,50)));
	    		OmMenuItem seis = new OmMenuItem("IsoExplorer", evt-> ((Patch) getObject()).addBoxClass ("projects.mathtools.classes.IsographyExplorer",
	    				 "IsoExplorer", new Point2D (150,50)));
	    		OmMenuItem siete = new OmMenuItem("Rest", evt-> ((Patch) getObject()).addBoxClass ("projects.music.classes.music.Rest",
	    				 "Rest", new Point2D (150,50)));
	    		OmMenuItem ocho = new OmMenuItem("RNote", evt-> ((Patch) getObject()).addBoxClass ("projects.music.classes.music.RNote",
	    				 "RNote", new Point2D (150,50)));

	    		OmMenuItem nueve = new OmMenuItem("RChord", evt-> ((Patch) getObject()).addBoxClass ("projects.music.classes.music.RChord",
	    				 "RChord", new Point2D (150,50)));

	    		OmMenuItem diez = new OmMenuItem("Group", evt-> ((Patch) getObject()).addBoxClass ("projects.music.classes.music.Group",
	    				 "Group", new Point2D (150,50)));

	    		OmMenuItem once = new OmMenuItem("RSeqChord", evt-> ((Patch) getObject()).addBoxClass ("projects.music.classes.music.RSeqChord",
	    				 "RSeqChord", new Point2D (150,50)));
	    		OmMenuItem doce = new OmMenuItem("Measure", evt-> ((Patch) getObject()).addBoxClass ("projects.music.classes.music.Measure",
	    				 "Measure", new Point2D (150,50)));
	    		OmMenuItem trece = new OmMenuItem("Voice", evt-> ((Patch) getObject()).addBoxClass ("projects.music.classes.music.Voice",
	    				 "Voice", new Point2D (150,50)));

	    		OmMenu menuFile = new OmMenu("File");
	    		menuFile.getItems().addAll(trece,doce,once,nueve,diez,ocho,zero,uno, cuatro, cinco,seis,siete);
	    		menuFile.getItems().add(dos);
	    		menuFile.getItems().add(tres);
	    		List<OmMenu> rep = new ArrayList<OmMenu>();
	    		rep.add(menuFile);
	   		 	return  rep;
	        }

	        //Events

	        public void omMouseDragged (double x, double y) {
	        	PatchPanel panel = (PatchPanel) getPanel();
	        	panel.omMouseDragged(x - panel.x() ,y - panel.y());
	    	}

	    	public void omMouseReleased (double x, double y) {
	        	PatchPanel panel = (PatchPanel) getPanel();
	        	panel.omMouseReleased(x - panel.x() ,y - panel.y());
	    	}

	    }

	 ////////////////////////////////////////////////////////////////////////
	 //////PANEL
	 ////////////////////////////////////////////////////////////////////////

	    public  static class PatchPanel extends PanelView {


	    	public OmTextEdit minieditor = null;

			public void omViewClickEvent (double x, double y){
	    		omUpdateView(false);
	    	}

			public void addMiniEditor (double x, double y, String str, DIFun action){
				removeMiniEditor();
				minieditor = new OmTextEdit(str, action);
				minieditor.omSetViewPosition(x, y);
				omAddSubview(minieditor);
	    	}

			public void removeMiniEditor (){
				if (minieditor != null) {
					omRemoveSubview(minieditor);
					minieditor.callAction();
				}
				minieditor = null;
	    	}

	    	public void KeyHandler(String car){
	    		List<I_SimpleFrame> selection = getSelectedBoxesFrame ();
	    		System.out.println ("key " + car);
	    		switch (car) {
				 case "del" : System.out.println ("delete"); break;
				 case "m": for (I_SimpleFrame boxframe : selection)
					 		((BoxFrame) boxframe).changePreviewMode();
				 		    break;
				 case "up": for (I_SimpleFrame boxframe : selection)
				 		((BoxFrame) boxframe).moveFrameDelta(0);
			 		    break;
				 case "down": for (I_SimpleFrame boxframe : selection)
				 		((BoxFrame) boxframe).moveFrameDelta(1);
			 		    break;
				 case "left": for (I_SimpleFrame boxframe : selection)
				 		((BoxFrame) boxframe).moveFrameDelta(2);
			 		    break;

				 case "right": for (I_SimpleFrame boxframe : selection)
				 		((BoxFrame) boxframe).moveFrameDelta(3);
			 		    break;
				 case "v": for (I_SimpleFrame boxframe : selection)
				 		((BoxFrame) boxframe).evalBox();
			 		    break;
			 }
	    	}

	    	/*(case char
	    			      (:om-key-delete (delete-general self))
	    			      ;;;(#\f (make-undefined-box self (om-mouse-position self)))
	    			      (#\d  (mapc 'show-big-doc actives))
	    			      (#\D (mapc 'update-doc actives))

	    			      (#\c  (patch-color self))
	    			      (#\v  (om-eval-enqueue
	    			             `(progn
	    			                (setf *cur-eval-panel* ,self)
	    			                (mapc 'eval-box ',actives)
	    			                (clear-ev-once ,self))
	    			             ))
	    			      (#\h  (show-help-window (format nil "Commands for ~A Editor"
	    			                                      (string-upcase (class-name (class-of (object (editor self))))))
	    			                              (get-help-list (editor self))))
	    			      ;;; in the menu
	    			      (#\k (mapc 'add-keywords actives))
	    			      (#\K (mapc 'erase-keywords actives))
	    			      (#\i (mapc 'reinit-size actives)
	    			           (reinit-connections self)
	    			           (reinit-bg-picts self))
	    			      (#\I (mapc 'reinit-contents actives))

	    			      (#\b (mapc 'add-rem-lock-button actives))

	    			      (#\l (mapc 'add-rem-lambda-button actives))
	    			      (#\1 (mapc 'add-rem-evonce-button actives))

	    			      (#\a (mapc 'internalize-patch actives))
	    			      (#\A (mapc 'align-one-boxframe actives)
	    			           (make-move-after self actives))

	    			      (#\p (play-boxes activeboxes) (mapcar 'om-invalidate-view actives))
	    			      (#\s (stop-boxes activeboxes) (mapcar 'om-invalidate-view actives))
	    			      (#\Space (if (idle-p *general-player*)
	    			                   (play-boxes activeboxes)
	    			                 (stop-all-boxes))
	    			               (mapcar 'om-invalidate-view actives))

	    			      (#\t (mapc 'show-online-tutorial actives))

	    			      (#\m (mapc 'change-edit-mode actives))
	    			      (#\n (mapc 'set-show-box-name actives))
	    			      (#\M (change-edit-mode-all (get-subframes self)))

	    			      (:om-key-up
	    			       (mapc #'(lambda (item) (move-frame-delta item 0)) actives)
	    			       (make-move-after self actives))
	    			      (:om-key-down
	    			       (mapc #'(lambda (item) (move-frame-delta item 1)) actives)
	    			       (make-move-after self actives))
	    			      (:om-key-left
	    			       (if (om-option-key-p)
	    			           (mapc #'(lambda (item) (delete-one-input item) t) actives)
	    			         (progn
	    			           (mapc #'(lambda (item) (move-frame-delta item 3)) actives)
	    			           (make-move-after self actives))
	    			         ))
	    			      (:om-key-right
	    			       (if (om-option-key-p)
	    			           (mapc #'(lambda (item) (add-one-input item)) actives)
	    			         (progn (mapc #'(lambda (item) (move-frame-delta item 2)) actives)
	    			           (make-move-after self actives))))

	    			      (#\< (mapc #'(lambda (item) (delete-one-input item)) actives))
	    			      (#\> (mapc #'(lambda (item) (add-all-inputs item)) actives))

	    			      (#\r #+om-reactive(mapc #'(lambda (boxframe)
	    			                                  (set-active (object boxframe) (not (active (object boxframe))))
	    			                                  (om-invalidate-view boxframe))
	    			                              actives))

	    			      (#\E (om-encapsulate self actives))
	    			      (#\U (om-unencapsulate self actives))

	    			      (otherwise (loop for box in activeboxes
	    			                           with hotbox = nil
	    			                           do (when (find-method #'handle-key-event '() (list (class-of box)
	    			                                                                              (find-class t)) nil)
	    			                                (setf hotbox t)
	    			                                (handle-key-event box char))
	    			                           finally
	    			                           do (unless hotbox
	    			                                (om-beep) ;no boxes have specialized handle-key-event methods
	    			                                ))))))
	    			                                */


	    	public void omMousePressed (double x, double y) {
	    		removeMiniEditor();
	    		if ( ! FX.getShiftKey()){
	    			for (I_SimpleFrame boxframe : getSelectedBoxesFrame()) {
		    			((BoxFrame) boxframe).setSelected(false);
		    			}
		    		}
	    		}

	    	public void omMouseDragged (double x, double y) {
	    		if (getMouvable () == null)
	    			addMouvable (new DragRectangle(x ,y));
	    		else getMouvable().movableCallback (x  ,y );
	    	}

	    	public void omMouseReleased (double x, double y) {
	    		if (getMouvable () != null){
	    			removeMouvable();
	    			for (I_SimpleFrame boxframe : getBoxesFrame()) {
		    			if (true) {
		    				((BoxFrame) boxframe).setSelected(true);
		    			}
		    		}
	    		}
	    	}

	    	public List<I_SimpleFrame> getBoxesFrame () {
	    		List<I_SimpleFrame> rep = new ArrayList<I_SimpleFrame>();
	    		for (I_Box box : ((Patch) getEditor().getObject()).boxes) {
	    			rep.add(((Box) box).boxframe);
	    		}
	    		return rep;
	    	}

	    	public List<I_SimpleFrame> getSelectedBoxesFrame () {
	    		List<I_SimpleFrame> rep = new ArrayList<I_SimpleFrame>();
	    		for (I_Box box : ((Patch) getEditor().getObject()).boxes) {
	    			if (box.getSelected())
	    				rep.add(((Box) box).boxframe);
	    		}
	    		return rep;
	    	}

	    }
}
