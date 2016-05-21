package gui.dialogitems;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class OmCheckbox extends FXWidget {


	public OmCheckbox (String text,  boolean ckecked, EventHandler<ActionEvent> fun) {
		super(new CheckBox());
		((CheckBox) omGetDelegate()).setText(text);
		((CheckBox) omGetDelegate()).setSelected(ckecked);
		((CheckBox) omGetDelegate()).setOnAction(fun);
	}

	public void omSetCheck (boolean val) {
		((CheckBox) omGetDelegate()).setSelected(val);
	    }
	 
	public boolean omIsSelected () {
	    	return ((CheckBox) omGetDelegate()).isSelected();
	    }
	 

	public void omSetText (String str) {
		((CheckBox) omGetDelegate()).setText(str);
		    }
		 
	public String omGetText () {
		    	return ((CheckBox) omGetDelegate()).getText();
		    }
		 

}
