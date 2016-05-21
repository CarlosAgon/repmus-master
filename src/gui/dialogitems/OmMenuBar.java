package gui.dialogitems;

import java.util.List;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class OmMenuBar  extends MenuBar  {
	
	public void OmAddMenuItems (List<OmMenu> items) {
		for (OmMenu item : items){
			getMenus().add(item);
		}
	}		
}
