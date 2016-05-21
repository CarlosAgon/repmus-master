package gui.dialogitems;

import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;


public class OmPopUp extends FXWidget {
	
	@FunctionalInterface
	public interface ActionFun {
		   void itemAction (OmPopUp e);
		}
	
	ActionFun actionFun;
	
	public OmPopUp (String[] list, ActionFun fun, int font, int x, int y, int w, int h, String val) {
		super(new ComboBox<String>());
		for (String item : list) {
			((ComboBox) delegate).getItems().add(item);
		}
		actionFun = fun;
		((ComboBox) delegate).setFocusTraversable(false);
		((ComboBox) delegate).setPrefWidth(w);
		((ComboBox) delegate).setPrefHeight(h);
		//setMinHeight(h);
		((ComboBox) delegate).setStyle("-fx-font-size: " + font);
		((ComboBox) delegate).relocate (x,y);
		((ComboBox) delegate).setValue(val);
		((ComboBox) delegate).setOnAction((Event ev) -> {actionFun.itemAction(this);});
	}
	
	public String omGetSelectedItem () {
		return ((ComboBox) delegate).getSelectionModel().getSelectedItem().toString();
	}
	 
}
