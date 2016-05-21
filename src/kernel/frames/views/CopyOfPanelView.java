package kernel.frames.views;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import gui.FXCanvas;
import gui.FXPane;
import gui.dialogitems.OmMenu;
import gui.dialogitems.OmMenuItem;



public class CopyOfPanelView extends FXPane implements I_Panel{
	

	public void init (){
		omGetDelegate().setStyle("-fx-border-color: blue;");
		delegate.requestFocus();
	}
	

	public EditorView getEditor (){
		return(EditorView)  omViewContainer();
	}
	
	public CopyOfPanelView getPanel (){
		return this;
	}
	
	public Point2D PanelPosition (){
		return new Point2D(0,0);
	}

	public void setPanelColor () {
		omSetBackground(Color.WHITE);
	}
	
	public void updatePanel (boolean changed) {
	}
	
	////////////////////////////////////////////////
	
}



