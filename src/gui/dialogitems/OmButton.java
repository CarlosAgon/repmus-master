package gui.dialogitems;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;



public class OmButton extends FXWidget {
	
	public OmButton (String text, EventHandler<ActionEvent> fun) {
		super(new Button());
		((Button) delegate).setText(text);
		((Button) delegate).setOnAction(fun);
	}


	 public void omSetText (String str) {
		 ((Button) delegate).setText(str);
	    }
	 
	 public String omGetText () {
	    	return ((Button) delegate).getText();
	    }
	
}
