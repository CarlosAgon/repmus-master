package kernel.frames.simples;

import gui.FXPane;
import gui.I_SimpleView;
import gui.mouvables.DragLine;
import resources.Loader;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import kernel.interfaces.I_Box;
import kernel.metaobjects.Connection;
import kernel.metaobjects.Patch;
import kernel.metaobjects.Patch.PatchPanel;


public class OutputBoxFrame extends FXPane {
	
	public ImageView imageview;
	String name;
	int index;
	
	public OutputBoxFrame (String thename, int theindex) {
		name = thename;
		index = theindex;
		imageview = new ImageView();	
		Image image = Loader.getIconFromDic("185");
		imageview.setImage(image);
		((Pane) omGetDelegate()).getChildren().add(imageview);
	}
	
	public boolean draggable (){
		return false;
	}
	
	public void omMouseDragged (double x, double y) {
		if (getMouvable () == null)
			addMouvable (new DragLine(x,y));
		else getMouvable ().movableCallback (x,y);
	}
	
	public void omMouseReleased (double x, double y) {
		if (getMouvable () != null){
			removeMouvable();
			PatchPanel patchpanel = getPatchPanel();
			Patch patch = ((Patch) patchpanel.getEditor().getObject());
			Point2D point = this.omGetDelegate().localToScene(new Point2D(x,y)); 
			point = getPatchPanel().omGetDelegate().sceneToLocal(point);
			I_SimpleView target = getPatchPanel().getPaneWithPoint(point.getX(), point.getY());
			if (target instanceof InputBoxFrame) {
				BoxFrame boxframe = ((BoxFrame) omViewContainer());
				I_Box boxsource = boxframe.object;
				boxframe = ((BoxFrame) target.omViewContainer());
				I_Box targetbox = boxframe.object;
				Connection connection = new Connection(boxsource, index, targetbox, ((InputBoxFrame)target).input.index);
				patch.addConnection(connection);
			}
		}
	}
	
	public PatchPanel getPatchPanel () {
		return (PatchPanel)  omViewContainer().omViewContainer(); 
	}
	
	public BoxFrame getBoxFrame () {
		return (BoxFrame)  omViewContainer(); 
	}
	
	public void connectionBind (DoubleProperty x, DoubleProperty y) {
		DoubleProperty x1 = omViewContainer().omGetDelegate().layoutXProperty();
		DoubleProperty y1 = omViewContainer().omGetDelegate().layoutYProperty();
		DoubleBinding dbx = new DoubleBinding() {
		  {
                super.bind(x, x1);
            }
            @Override
            protected double computeValue() {
                return x1.get() + x() + 4;
            }
        };
        DoubleBinding dby = new DoubleBinding() {
  		  {
                  super.bind(y, y1);
              }
              @Override
              protected double computeValue() {
                  return y1.get() + y() + 8;
              }
          };
          x.bind(dbx);
          y.bind(dby);
    	}
}

/*
 * (defmethod connect-box ((self outfleche) (ctrl input-funboxframe))
  (let* ((boxframe (om-view-container self))
         (boxcall (object boxframe))
         (boxtarget (om-view-container ctrl)))
    (if (not (recursive-connection  boxtarget boxframe))
      (progn
        (when (connected? (object ctrl))
          (remove-connection boxtarget (position ctrl (inputframes boxtarget) :test 'equal))) 
        (connect-ctrl boxcall (object ctrl) (index self))
        (push (new-connection boxtarget (position ctrl (inputframes boxtarget) :test 'equal))
              (connections boxtarget))
        (redraw-connections boxtarget)
        (modify-patch (panel boxtarget)))
      (om-beep-msg "Impossible to connect, this would create a cycle."))))*/

