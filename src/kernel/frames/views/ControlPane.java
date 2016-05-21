package kernel.frames.views;

import java.util.List;

import projects.music.editors.MusicalEditor;
import projects.music.editors.MusicalPanel;
import gui.FXPane;
import gui.I_ContainerView;
import gui.dialogitems.OmMenu;
import javafx.scene.control.MenuBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ControlPane extends FXPane {
	EditorView editor;
	
	public ControlPane () {
		omSetBackground (new Color (0.85, 0.85, 0.85, 1.0));
		delegate.setEffect(new DropShadow());
	}
	
	public void init () {
	}
	
	public void setEditor (EditorView ed) {
		editor = ed;
	}
	
	public EditorView getEditor () {
		return editor;
	}
	
	public I_Panel getPanel () {
		return editor.getPanel();
	}

}

