package draganddrop;

import gui.I_ContainerView;
import gui.I_SimpleView;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;

public class DragAndDropHandler {
	
	public I_SimpleView dragged_view = null;
	public List<I_SimpleView> dragged_list_objs = new ArrayList<I_SimpleView>();
	public I_ContainerView container_view = null;
	public I_SimpleView target_view = null;
	public I_SimpleView true_dragged_view = null;
	public I_SimpleView true_target_view = null;
	public boolean opt_key_p = false;
	public Point2D initial_mouse_pos = null;
	public Point2D drop_mouse_pos = null;
	public String drop_flavor = "SHEET_FLAVOR";
}
