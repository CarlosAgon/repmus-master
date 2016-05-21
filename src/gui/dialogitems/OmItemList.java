package gui.dialogitems;

import javafx.scene.control.ListView;



public class OmItemList extends FXWidget {
	@FunctionalInterface
	public interface ActionFun {
		   void itemAction (OmItemList e);
		}
	
	ActionFun actionFun;
	
	public OmItemList (String[] list, ActionFun fun) {
		super(new ListView());
		for (String item : list) {
			((ListView) delegate).getItems().add(item);
		}
		actionFun = fun;
	//	setOnAction((Event ev) -> {actionFun.itemAction(this);});
	}

	//regarder lis organizer jewels
}
