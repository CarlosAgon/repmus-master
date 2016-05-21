package projects.music.editors;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Hashtable;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.text.Font;
import kernel.frames.views.I_EditorParams;

public class MusicalParams implements I_EditorParams{
	

	Hashtable<String, Font> FontTable = new Hashtable<String, Font>();
	
	public MusicalParams () {
		super();
		initFonts ();	
	}
	
	// true if the editor draw in page mod
	public BooleanProperty pagemode = new SimpleBooleanProperty(false);
	// Font size
	public IntegerProperty fontsize = new SimpleIntegerProperty(24);
	//Staff
	public StringProperty staff = new SimpleStringProperty("G");
	//Scale
	public StringProperty scale = new SimpleStringProperty("1/2");
	// slot mode midic, dur, vel, etc
	public StringProperty slotmode = new SimpleStringProperty("midic");
	// chord mode chord, arpDwn, order, onset
	public StringProperty chordmode = new SimpleStringProperty("chord");
	// chord mode chord, arpDwn, order, onset
	public IntegerProperty zoom = new SimpleIntegerProperty(100);
	// true if the editor show tempo
	public BooleanProperty showtempo = new SimpleBooleanProperty(false);
	// true if the editor show stems
	public BooleanProperty showstem = new SimpleBooleanProperty(true);
	
	Point deltapict = new Point (0,0);
	int outport = 0;
	int inport = 0;
	String player = "internal";

	boolean notechancolor_p = false;
	int grillestep = 1000;
	
	Dimension winsize = new Dimension (370,280);
	Point winpos = new Point(400,20);
	
	int obj_mode = 0;
	String cursor_mode = "normal";


	//Page
	Dimension paper_size = new Dimension (600, 800);
	int top_margin = 2 ;
	int left_margin = 1 ;
	int right_margin = 1 ;
	int bottom_margin = 1 ;
	int orientation = 0 ;
	int scaleFactor = 1;
	//List<Integer> systemSpace = new ArrayList(); //'(1) 
	Color system_color = Color.black;
	int line_space =  1 ;
	//(fdoc :initform (make-instance 'fdoc)  :accessor fdoc :initarg :fdoc)
	String title = "Musica";
	
	public void  initFonts () {
		FontTable.put("headSize", new Font("omheads",fontsize.get()));
		FontTable.put("microSize", new Font("omicron",fontsize.get()));
		FontTable.put("singSize", new Font("omsign",fontsize.get()));
		FontTable.put("sing4/5Size", new Font("omsign",fontsize.get()*0.8));
		FontTable.put("extrasSize", new Font("omextras",fontsize.get()));
		FontTable.put("normal2.3Size", new Font("Courier",fontsize.get()*2.3));
		FontTable.put("normal2.8Size", new Font("Courier",fontsize.get()*2.8));
		FontTable.put("normalSize", new Font("Courier",fontsize.get()));
		FontTable.put("head1.6Size", new Font("omheads",fontsize.get()*1.6));
	}
	
	public  Font getFont (String name){
		Font rep = FontTable.get(name);
		if (rep == null) rep = FontTable.get("headSize");
		return rep;
	}

}
