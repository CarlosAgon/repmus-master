package gui.dialogitems;

import gui.FXRegion;
import javafx.geometry.Orientation;
import javafx.scene.control.Slider;

public class OmSlider extends FXWidget {
	
	public OmSlider (int dir, double min, double max, double value) {
		super(new Slider (min, max, value));
		if (dir == 0)
			((Slider) omGetDelegate()).setOrientation(Orientation.HORIZONTAL);
		else ((Slider) omGetDelegate()).setOrientation(Orientation.VERTICAL);
		((Slider) omGetDelegate()).setShowTickMarks(true);
		((Slider) omGetDelegate()).setShowTickLabels(true);
		((Slider) omGetDelegate()).setBlockIncrement(1);
	}

}