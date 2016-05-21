package projects.music.editors;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import kernel.frames.views.ControlPane;
import gui.dialogitems.OmPopUp;
import gui.dialogitems.OmStaticText;


public class MusicalControl extends ControlPane {
	
	final String [] sizeList = {"8","16","24", "32","40", "48","56","72"};
	final String [] staffList = {"G" , "F4", "F3" ,"U1", "U2", "U3" ,"U4", "GF" ,"GG", "FF", "GFF", "GGF", "GGFF", "P" , "L" , "EMPTY"};
	final String [] approxList = {"1","1#","1/2","1/3", "1/3#","1/4","1/5","1/5#","1/6", 
			"1/7","1/7#","1/8","1/10", "1/12" , "1/14" , "1/16"};
	
	public MusicalControl () {
		super ();
		omGetDelegate().setStyle("-fx-border-color: red;");
	}
	
	public void init () {
		MusicalPanel panel = getPanel();
		String [] slotList = panel.getSlotList();
		omSetBackground (new Color (0.85, 0.85, 0.85, 1.0));
		omGetDelegate().setEffect(new DropShadow());
		
		int y1 = 2;
		int y2 = 24;
		int x1 = 30;
		int deltax = 60;
		
		OmPopUp slotbut = new OmPopUp(slotList,
		(e) -> {changeEditorSlot (e.omGetSelectedItem());}, 10, 2, y1, 70, 20, "midic");
		omAddSubview(slotbut);
		
		OmStaticText sizeitem = new OmStaticText ("Font size",
					e -> {System.out.println("running");},10, x1 + (1 * deltax), y1, 70, 20);
		omAddSubview(sizeitem);
		
		OmPopUp sizebut = new OmPopUp (sizeList, 
				(e) -> {changeEditorSize (Integer.parseInt((String) e.omGetSelectedItem()));},
				10, x1 + (1 * deltax), y2, 50, 20, "24");
		omAddSubview(sizebut);
		
		OmStaticText staffitem = new OmStaticText ("Staff", 
				e -> {System.out.println("running1");},10, x1 + (2 * deltax), y1, 70, 20);

		omAddSubview(staffitem);
	
		OmPopUp staffbut = new OmPopUp (staffList,
				(e) -> {changeEditorStaff ((String) e.omGetSelectedItem());},
				10, x1 + (2 * deltax), y2, 50, 20, "G");

		omAddSubview(staffbut);
		
		OmStaticText approxitem = new OmStaticText ("Approx", 
				e -> {System.out.println("running1");},10, x1 + (3 * deltax), y1, 70, 20);

		omAddSubview(approxitem);
	
		OmPopUp approxbut = new OmPopUp (approxList,
				(e) -> {changeEditorApprox ((String) e.omGetSelectedItem());},
				10, x1 + (3 * deltax), y2, 50, 20, "1/2");

		omAddSubview(approxbut);
		
		OmStaticText zoomitem = new OmStaticText ("Zoom", 
				e -> {System.out.println("running1");},10, x1 + (4 * deltax), y1, 70, 20);

		omAddSubview(zoomitem);
		
	/*	NumBox zoomText = new NumBox ("Zoom", e -> {},
				10, x1 + (4 * deltax), y1, 70, 20,
				(item) -> {changeEditorZoomAfter (item.getValue());},
				(item) -> {changeEditorZoom (item.getValue());},
				100, 1000, 1);
		omAddSubview(zoombox);*/
		
		/*NumBox zoomItem = new NumBox ("100", 
				new Point(l1+4,c1), 
				new Dimension (55,18),
				(item) -> {changeEditorZoomAfter (item.getValue());},
				(item) -> {changeEditorZoom (item.getValue());},
				100, 1000, 1);
		zoomItem.omSetFont(FX.default_font1);
		this.omAddSubview(zoomItem);
		*/

	}
	
	public MusicalEditor getEditor () {
		return (MusicalEditor) omViewContainer();
	}
	
	public MusicalPanel getPanel () {
		return (MusicalPanel) getEditor().getPanel();
	}
	
	/////ACTIONS
	
	public void changeEditorZoomAfter (int zoom){
		
	}
	
	public void changeEditorZoom (int zoom){
		
	}
	
	public void changeEditorSize (int newSize){
		getEditor().changeSize(newSize);
		
	}
	
	public void changeEditorStaff (String newStaff){
		getEditor().changeStaff(newStaff);
		
	}
	
	public void changeEditorSlot (String slot) {
		getEditor().changeSlot(slot);
	}
	
	public void changeEditorApprox (String approx) {
		getEditor().changeApprox(approx);
	}
}

/*
(defmethod initialize-instance :after ((self omcontrols-view) &rest l 
        &key (tone "1/2") (staff "ffgg") (font-size "24") (zoom 1) (mode 0))
(declare (ignore l))

(let* ((bgcol *controls-color*)


;;; Slot
(minied (om-make-dialog-item 'edit-numbox (om-make-point 96 (+ c1 2)) (om-make-point 50 18) " "
       :value nil
       :font *om-default-font1*
       :bg-color *om-white-color*
       :help-spec ""
       ))

;;; Font Size
)


(setf (slotedit self) minied)
(om-add-subviews self  staffitem staffbut sizeitem slotbut toneitem tonebut minied  sizebut)

;;(additional-port-menu (title-bar (om-view-container self)) :pos (om-make-point 300 4) :color *editor-bar-color*)
(add-zoom2control self zoom (om-make-point l1 c1))

(om-set-bg-color self *controls-color*)

)
)

*/