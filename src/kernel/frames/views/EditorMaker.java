package kernel.frames.views;

import projects.music.editors.MusicalControl;
import projects.music.editors.MusicalPanel;
import projects.music.editors.MusicalTitle;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import kernel.I_OMObject;
import kernel.metaobjects.MetaObject;

public class EditorMaker {
	
	public EditorView makeEditorView (I_OMObject object, MetaObject metaobject, String editorClass, 
			Point2D size, Point2D pos, I_EditorParams params) {
		Class classe=null;
		
		Class classPa = null;
		Class classCtr = null;
		Class classTi = null;
		
		EditorView theEditor;
		I_Panel thePanel = null;
		ControlPane theControl = null;
		TitlePane theTitle = null;
		
		
		try {
			classe = Class.forName(editorClass);
			try {
				theEditor = (EditorView) classe.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				theEditor = new EditorView ();
			}
		} catch (ClassNotFoundException e) {
			theEditor = new EditorView ();
		}
		try {
			theEditor.setObject(object);
			theEditor.setParams(params);
			classe = Class.forName(theEditor.getPanelClass());
			if (theEditor.getPanelClass() != null) {
				classPa = Class.forName(theEditor.getPanelClass());
			}
			if (theEditor.getControlsClass() != null) {
				classCtr = Class.forName(theEditor.getControlsClass());
			}
			
			if (theEditor.getTitleBarClass() != null) {
				classTi = Class.forName(theEditor.getTitleBarClass());
			}
			
			try {
				thePanel = (I_Panel) classe.newInstance();
				if (classCtr != null){
					theControl = (ControlPane) classCtr.newInstance();
				}
				if (classTi != null){
					theTitle = (TitlePane) classTi.newInstance();
				}
			} catch (InstantiationException | IllegalAccessException e) {
				thePanel = new PanelCanvas ();
			}
		} catch (ClassNotFoundException e) {
			thePanel = new PanelView ();
		}
		
		
		if (theControl != null) {
			theControl.setEditor(theEditor);
			theEditor.omAddSubviews(theControl);
			theEditor.setControls(theControl);
		}
		
		if (theTitle != null) {
			theTitle.setEditor(theEditor);
			theEditor.omAddSubviews(theTitle);
			theEditor.setTitleBar(theTitle);
		}
		theEditor.omAddSubviews(thePanel);
		theEditor.setPanel(thePanel);
		thePanel.omUpdateView(true);
		thePanel.init();
		if (theControl != null) 
			theControl.init();
		if (theTitle != null)
			theTitle.init();
		theEditor.omSetViewSize(size);
		theEditor.omSetViewPosition(pos);
		theEditor.metaobject = metaobject;
		return theEditor;	
	}

}
