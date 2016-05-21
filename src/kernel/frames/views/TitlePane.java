package kernel.frames.views;

import java.util.List;

import gui.FXPane;
import gui.dialogitems.OmMenu;
import javafx.scene.control.MenuBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

public class TitlePane extends FXPane {
	
	EditorView editor;
	MenuBar menuBar = null;
	
	public TitlePane () {
		omGetDelegate().setStyle("-fx-border-color: red;");
		omSetBackground (new Color (0.85, 0.85, 0.85, 1.0));
		delegate.setEffect(new DropShadow());
	}
	
	public void setEditor (EditorView ed) {
		editor = ed;
		List<OmMenu> menuitems = editor.getMenu();
		if (menuitems != null) {
			menuBar = new MenuBar ();
			menuBar.getMenus().addAll(menuitems);
			((Pane) delegate).getChildren().addAll(menuBar);
		}   
	}
	
	public void init () {
	}

}

