package gui.dialogitems;

import gui.FXRegion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;


public class OmStaticText extends FXWidget  {
	
	
	public EventHandler<ActionEvent> action;
	
	public OmStaticText (String text, EventHandler<ActionEvent> fun, int font, int x, int y, int w, int h) {
		super(new Label (text));
		action = fun;
		omSetViewSize(w,h);
		omGetDelegate().setStyle("-fx-font-size: " + font);
		omSetViewPosition (x,y);
	}
	
	public OmStaticText (String text) {
		super(new Label (text));
	}

	@Override
	public void omSetText(String text) {
		((Label) delegate).setText(text);	
	}

	@Override
	public String omGetText() {
		return ((Label) delegate).getText();
	}

}
