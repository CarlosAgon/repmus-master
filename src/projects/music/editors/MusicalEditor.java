package projects.music.editors;

import gui.dialogitems.OmMenu;
import gui.dialogitems.OmMenuItem;

import java.util.ArrayList;
import java.util.List;

import projects.music.classes.interfaces.I_MusicalObject;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import kernel.frames.views.EditorView;


public class MusicalEditor extends EditorView {
	public  MusicalEditor () {
		super ();
	}

	public String getPanelClass (){
		return "musical_objects.MusicalPanel";
	}
	
	public String getControlClass (){
		return "musical_objects.MusicalControl";
	}
	
	public String getTitleClass (){
		return "musical_objects.MusicalTitle";
	}
	

	///////////////////////////////////////////////////////////
	//ACTIONS
	///////////////////////////////////////////////////////////
	
	public void  changeSize (int newsize){
		MusicalParams params = (MusicalParams) getParams ();
		MusicalPanel panel = (MusicalPanel) getPanel ();
		params.fontsize.set(newsize);
		panel.init();
		}
	
	public void  changeStaff (String newStaff){
		MusicalParams params = (MusicalParams) getParams ();
		MusicalPanel panel = (MusicalPanel) getPanel ();
		params.staff.set(newStaff);
		panel.init();
		//repaint();
		}
	
	public void  changeSlot (String newslot){
		MusicalParams params = (MusicalParams) getParams ();
		MusicalPanel panel = (MusicalPanel) getPanel ();
		params.slotmode.set(newslot);
		panel.init();
		//repaint();
		}
	
	public void  changeApprox (String newapprox){
		MusicalParams params = (MusicalParams) getParams ();
		MusicalPanel panel = (MusicalPanel) getPanel ();
		params.scale.set(newapprox);
		panel.init();
		//repaint();
		}
	
	public void  changeChordMode (String newmode){
		MusicalParams params = (MusicalParams) getParams ();
		MusicalPanel panel = (MusicalPanel) getPanel ();
		params.chordmode.set(newmode);
		panel.init();
		}
	
	
	public MenuItem  importMenu (){
		return null;
	}
	
	public MenuItem  exportMenu (){
		return null;
	}
	
	public List<OmMenu> getMenu () {
		/*List<OmMenuItem> rep = new ArrayList<OmMenuItem>();
		OmMenuItem [] list = {new OmMenuItem("Close", e-> {} ),
							new SeparatorMenuItem(),	
							importMenu(),
							exportMenu(),
							new SeparatorMenuItem(),
							new OmMenuItem ("Page Setup", e-> {}),
							new OmMenuItem ("Print", e-> {})};
		rep.add (new OmMenu ("File", list));
		
		MenuItem [] list1 = {new OmMenuItem("Undo", e-> {} ),
							new SeparatorMenuItem(),
							new OmMenuItem("Cut", e-> {} ),
							new OmMenuItem("Copy", e-> {} ),
							new OmMenuItem("Paste", e-> {} ),
							new SeparatorMenuItem(),
							new OmMenuItem("Select All", e-> {} )};
		rep.add (new OmMenu ("Edit", list1));
		*/
		return null;
	}
	
}

       



