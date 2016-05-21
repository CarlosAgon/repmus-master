package kernel.frames.views;


import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import gui.FXPane;
import gui.dialogitems.OmMenu;
import kernel.I_OMObject;
import kernel.metaobjects.MetaObject;

public class EditorView extends FXPane {

	I_Panel panel=null;
	List<EditorView> attachedEditors = null;
	I_OMObject object;
	MetaObject metaobject;
	Node shape = null;
	TitlePane titlebar = null;
	ControlPane controls = null;
	I_EditorParams params = null;
	
	public EditorView () {
		super();
		delegate.widthProperty().addListener(evt -> updateSubviews());
		delegate.heightProperty().addListener(evt -> updateSubviews());
	}
	
	//////////////////////////////////////////////////////////////
	public I_OMObject getObject (){
		return object;
	}
	
	public void setObject (I_OMObject obj){
		object = obj;
	}
	
	
	public EditorView getEditor (){
		return this;
	}
	
	public I_Panel getPanel (){
		return panel;
	}
	
	public void setPanel (I_Panel thePanel){
		 panel = thePanel;
	}
	
	public ControlPane getControls (){
		return controls;
	}
	
	public void setControls (ControlPane thecontrols){
		controls = thecontrols;
	}
	
	public TitlePane getTitleBar (){
		return titlebar;
	}
	
	public void setTitleBar (TitlePane thetitlebar){
		titlebar = thetitlebar;
	}
	
	public I_EditorParams getParams (){
		return params;
	}
	
	public void setParams (I_EditorParams theparams){
		params = theparams;
	}

	//////////////////////////////////////////////////////////////
	public String getPanelClass (){
		return "editors.PanelView";
	}
	
	public String getControlsClass (){
		return null;
	}
	
	public String getTitleBarClass (){
		return null;
	}
	
	public List<OmMenu> getMenu (){
		return null;
	}
	
	//////////////////////////////////////////////////////////////
	
	public void save (){
		
	}
	
	public void saveAs (){
		
	}
	
	public void copy (){
		
	}
	
	public void paste (){
		
	}
	

	public Point2D panelPosition (){
		return new Point2D(0,0);
	}
	
	public Point2D panelSize (){
		return omViewSize();
	}
	
	public void updateSubviews () {
		double deltatitle = 30;
		double deltacontrol = 50;
		double w = delegate.getWidth();
		double h = delegate.getHeight();
		double deltah = 0;
		panel.omSetViewPosition (0,0);
		if (titlebar != null) {
			deltah = deltah + deltatitle;
			titlebar.omSetViewPosition (0,0);
			titlebar.omSetViewSize (w,deltatitle);
			panel.omSetViewPosition (0,deltatitle);
		}
		if (controls != null) {
			deltah = deltah + deltacontrol;
			controls.omSetViewPosition (0,h-deltacontrol);
			controls.omSetViewSize (w,deltacontrol);
		}
		panel.omSetViewSize (w,h-deltah);
		panel.omUpdateView(false);
	}
	
	///////////
	public void KeyHandler (String car) {
		((I_Panel) getPanel()).KeyHandler(car);
	}
	
	public void EditorClosed () {
		metaobject. editor = null;
	}
		
}


