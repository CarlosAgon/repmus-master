package projects.music.classes.abstracts;

import gui.renders.GCRender;
import gui.renders.I_Render;
import projects.mathtools.classes.NCercle;
import projects.music.classes.interfaces.I_MusicalObject;
import projects.music.classes.interfaces.I_RT;
import javafx.geometry.Point2D;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import kernel.frames.views.EditorView;
import kernel.frames.views.PanelCanvas;
import kernel.frames.views.PanelView;
import javafx.scene.Node;

public class Debug_MO extends EditorView {
	
	public  Debug_MO () {
    		super ();
    	}

	@Override
    	public String getPanelClass (){
    		return "projects.music.classes.abstracts.Debug_MO$DebugMOPanel";
    	}
        
    
    //////////////////////PANEL//////////////////////
    public static class DebugMOPanel extends PanelView { //TreeView<String> {
    	TreeView<String> arbre;
    	
    	public DebugMOPanel() {
    		MusicalObject obj = (MusicalObject) getEditor().getObject();
    		
    		TreeItem<String>  root = new TreeItem<String>(obj.getClass().getSimpleName());
			root.setExpanded(true);
			fillTree (root, (I_RT) obj);
			arbre = new TreeView<String> (root);
			arbre.setPrefWidth(500);
			arbre.setPrefHeight(500);
			((Pane) delegate).getChildren().add((Node) arbre);
			
		}
		

	private static void fillTree (TreeItem<String> racine, I_RT obj) {
	     racine.getChildren().add(
	    		  new TreeItem<String>(" e=" + obj.getQDurs() + 
	    				  " o=" + obj.getQoffset() ));
	      if( obj instanceof Compose_S_MO) {
	      for (MusicalObject cont : ((Compose_S_MO) obj).getElements()) {
	    	  TreeItem<String> sub = new TreeItem<String>(cont.getClass().getSimpleName());
		      fillTree (sub, (I_RT) cont);
		      racine.getChildren().add(sub);
			}
	      }
		}
	}
    }
    


