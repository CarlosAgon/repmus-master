package gui.dialogitems;

import gui.FXRegion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;

public class OmRadioButton extends FXRegion {

	
	public OmRadioButton (String text, boolean sel, OmRadioGroup group, EventHandler<ActionEvent> fun) {
		super(new RadioButton (text));
		((RadioButton) omGetDelegate()).setToggleGroup(group);
		((RadioButton) omGetDelegate()).setSelected(sel);
		((RadioButton) omGetDelegate()).setOnAction(fun);
	}
	

	public void omSetCheck (boolean val) {
		((RadioButton) omGetDelegate()).setSelected(val);
	    }
	 
	public boolean omIsSelected () {
	    	return ((RadioButton) omGetDelegate()).isSelected();
	    }

		
	public void omSetText (String str) {
		((RadioButton) omGetDelegate()).setText(str);
		    }
		 
	public String omGetText () {
		  return ((RadioButton) omGetDelegate()).getText();
	}

}

