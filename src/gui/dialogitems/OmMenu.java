package gui.dialogitems;

import java.util.List;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class OmMenu extends Menu{

	
	public OmMenu(String title, OmMenuItem [] list) {
		super(title);
		for (int i = 0; i < list.length; i++)
			getItems().add(list[i]);
	}
	
	public OmMenu(String title, List<OmMenuItem> list) {
		super(title);
		for (OmMenuItem item : list)
			getItems().add(item);
	}
	
	public OmMenu(String title) {
		super(title);
	}
			
			
}
