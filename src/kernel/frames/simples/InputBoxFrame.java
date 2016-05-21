package kernel.frames.simples;

import java.lang.reflect.Field;

import gui.FX;
import gui.FXPane;
import gui.dialogitems.OmStaticText;
import gui.dialogitems.OmTextEdit;
import resources.Loader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;
import kernel.metaobjects.BoxInput;
import kernel.metaobjects.Patch.PatchPanel;

public class InputBoxFrame  extends FXPane {
	
	public ImageView imageview;
	BoxInput input;
	Tooltip tp = null;
	OmStaticText miniview = null;
	PatchPanel patch = null;
	
	public InputBoxFrame (BoxInput theinput) {
		input = theinput;
		imageview = new ImageView();	
		Image image = Loader.getIconFromDic("185");
		imageview.setImage(image);
		((Pane) omGetDelegate()).getChildren().add(imageview);
	}
	
	public void omMouseEntered (double x, double y) {
		BoxFrame box = (BoxFrame) this.omViewContainer();
		patch = (PatchPanel) box.omViewContainer();
		if (FX.getCommandKey()) {
			tp = new Tooltip(input.name);
			hackTooltipStartTiming(tp);
			Tooltip.install(this.omGetDelegate(), tp);
		} else {
			miniview = new OmStaticText(input.val.toString());
			miniview.omSetViewPosition(box.x()+x(), Math.max(0,box.y()+y() - 20));
			patch.omAddSubview(miniview);
		}
       }
	
	public void omMouseExited (double x, double y) {
		patch = (PatchPanel) this.omViewContainer().omViewContainer();
		if (tp != null)
			Tooltip.uninstall(this.omGetDelegate(), tp);
		tp = null;
		if (miniview != null) {
			patch.omRemoveSubview(miniview);
		}
		miniview = null;
	}
	
	public void omMousePressed (double x, double y) {
		BoxFrame box = (BoxFrame) this.omViewContainer();
		patch = (PatchPanel) this.omViewContainer().omViewContainer();
		if (tp != null)
			Tooltip.uninstall(this.omGetDelegate(), tp);
		tp = null;
		if (miniview != null) {
			patch.omRemoveSubview(miniview);
		}
		miniview = null;
		patch.addMiniEditor (box.x()+x(), Math.max(0,box.y()+y() - 20),input.val.toString(),
				(e)->{closeEditor(e.omGetText());});
	}
	
	public void closeEditor (String str) {
		BoxFrame box = (BoxFrame) this.omViewContainer();
		patch = (PatchPanel) this.omViewContainer().omViewContainer();
		input.changeVal(str);
	}
	
	public void connectionBind (DoubleProperty x, DoubleProperty y) {
		DoubleProperty x1 = omViewContainer().omGetDelegate().layoutXProperty();
		DoubleProperty y1 = omViewContainer().omGetDelegate().layoutYProperty();
		DoubleBinding dbx = new DoubleBinding() {
		  {super.bind(x, x1);}
            @Override
            protected double computeValue() {
                return x1.get() + x() + 4;
            }
        };
        DoubleBinding dby = new DoubleBinding() {
  		  {super.bind(y, y1);}
              @Override
              protected double computeValue() {
                  return y1.get() + y() ;
              }
          };
          x.bind(dbx);
          y.bind(dby);
    	}
	
	
	public static void hackTooltipStartTiming(Tooltip tooltip) {
	    try {
	        Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
	        fieldBehavior.setAccessible(true);
	        Object objBehavior = fieldBehavior.get(tooltip);

	        Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
	        fieldTimer.setAccessible(true);
	        Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

	        objTimer.getKeyFrames().clear();
	        objTimer.getKeyFrames().add(new KeyFrame(new Duration(50)));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
