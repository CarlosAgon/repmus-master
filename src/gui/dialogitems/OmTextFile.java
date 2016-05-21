package gui.dialogitems;

import javafx.scene.control.TextField;

public class OmTextFile extends FXWidget  {
	
	public OmTextFile (String text) {
		super(new TextField (text));
		((TextField) omGetDelegate()).setText(text);
	}
	
	@Override
	public void omSetText(String text) {
		((TextField) delegate).setText(text);	
	}

	@Override
	public String omGetText() {
		return ((TextField) delegate).getText();
	}
	
}
