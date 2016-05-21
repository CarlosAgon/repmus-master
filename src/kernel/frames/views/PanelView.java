package kernel.frames.views;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import gui.FXPane;



public class PanelView extends FXPane implements I_Panel{
	
	public PanelView () {
		super (true,true);
	}
	
	public PanelView (boolean h, boolean v) {
		super (h,v);
	}
	
	public void init (){
		omGetDelegate().setStyle("-fx-border-color: blue;");
		delegate.requestFocus();
	}
	

	public EditorView getEditor (){
		return(EditorView)  omViewContainer();
	}
	
	public PanelView getPanel (){
		return this;
	}
	
	public Point2D PanelPosition (){
		return new Point2D(0,0);
	}

	public void setPanelColor () {
		omSetBackground(Color.WHITE);
	}
	
	@Override
	public void updatePanel (boolean changed) {
	}
	
	////////////////////////////////////////////////
	
}



