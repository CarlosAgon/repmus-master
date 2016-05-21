package gui;

import javafx.scene.layout.Pane;

public class FXPane extends FXContRegion  {
	
	public FXPane () {
		super(new Pane());
	}
	
	public FXPane (boolean h, boolean v) {
		super(new Pane(), h , v);
	}
	
	
}
