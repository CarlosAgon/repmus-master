package gui.dialogitems;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class OmMenuItem extends MenuItem {
	
	public OmMenuItem (String title, EventHandler<ActionEvent> fun){
		super(title);
		setOnAction(fun);
	}
}
