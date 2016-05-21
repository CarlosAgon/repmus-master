package gui;
	
import java.io.File;

import resources.Loader;
import javafx.scene.image.ImageView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeCell;

public class WorkSpaceTree  extends TreeView<File> {
	

	public  WorkSpaceTree(){
		setRoot(createTree (new File (".")));
		setCellFactory((e) -> new TreeCell<File>() { 
			@Override
			protected void updateItem(File item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null) {
					setText(item.getName());
					setGraphic(getTreeItem().getGraphic());
				} else {
					setText("");
					setGraphic(null);
				}
			}
		});
		}

	private TreeItem<File> createTree (File file) {
		TreeItem<File> item = new TreeItem <>(file);
		File [] childs = file.listFiles();
		if (childs != null) {
			for (File child : childs) {
				item.getChildren().add(createTree(child));
			}
			item.setGraphic(new ImageView (Loader.getIconFromDic("186")));
		} else {
			item.setGraphic(new ImageView (Loader.getIconFromDic("125")));
		}
		return item;
	}
		

}
