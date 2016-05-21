package gui.dialogitems;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

public class OmTextEdit extends FXWidget  {
	
	
	public OmTextEdit (String text, DIFun action) {
		super(new TextArea (text));
		mainaction = action;
		((TextArea) omGetDelegate()).setText(text);
	}
	
	@Override
	public void omSetText(String text) {
		((TextArea) delegate).setText(text);	
	}

	@Override
	public String omGetText() {
		return ((TextArea) delegate).getText();
	}
	
}
