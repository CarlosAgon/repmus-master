package projects.mathtools.classes;

import gui.FX;
import gui.FXCanvas;
import gui.dialogitems.OmMenuItem;
import gui.renders.GCRender;
import gui.renders.I_Render;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kernel.I_OMObject;
import kernel.annotations.Ombuilder;
import kernel.annotations.Omclass;
import kernel.annotations.Ommethod;
import kernel.annotations.Omvariable;
import kernel.frames.views.EditorView;
import kernel.frames.views.I_EditorParams;
import kernel.frames.views.PanelCanvas;
import kernel.metaobjects.Patch;

@Omclass(icon = "421", editorClass = "projects.mathtools.classes.NCercle$NCercleEditor")	
public class NCercle implements I_OMObject, Serializable { 
	

	
    @Omvariable
    public int n = 12;
    
    @Omvariable
    public List<List<Integer>> points = null;
    public List<List<Integer>> modulo_points = null;
    
    @Ombuilder
    public NCercle(int period, List<List<Integer>> thepoints) {
       super();
       n = period;
       points = thepoints;
    }
    
    @Ombuilder
    public NCercle() {
    	super ();
    	List<Integer> first = new ArrayList<Integer> ();
		first.add(0); first.add(5); first.add(7);
		List<List<Integer>> input = new ArrayList<List<Integer>> ();
		input.add(first);
		n =12;
		points = input;
    }
    
    
	@Ommethod
	public void play () {
		System.out.println("beep");
	}
	
	public  void DrawContentsInRect (I_Render g, double x, double x1, double y, double y1,boolean polygon, boolean hideback) {
		drawCercle (g, x + ((x1 - x) / 2),  y + ((y1 - y)/ 2), Math.min ( (x1 - x)/ 2.2,  ( y1 - y) / 2.2), 2, 5, 0, polygon, hideback);
	}
	
	/*public  Image drawPreview (GraphicsContext g, double x, double x1, double y, double y1) {
		final Canvas canvas = new Canvas((x1 - x),( y1 - y));
		GraphicsContext gc = canvas.getGraphicsContext2D();
		drawCercle (gc, x + ((x1 - x) / 2),  y + ((y1 - y)/ 2), Math.min ( (x1 - x)/ 2.2,  ( y1 - y) / 2.2), 1, 3, 0, true, false);
		return canvas.snapshot(null,null);
	}*/
	
	public  void drawPreview (I_Render g, FXCanvas canvas, double x, double x1, double y, double y1, I_EditorParams params) {
		drawCercle (g, x + ((x1 - x) / 2),  y + ((y1 - y)/ 2), Math.min ( (x1 - x)/ 2.2,  ( y1 - y) / 2.2), 1, 3, 0, true, false);
	}
	
	public void drawCercle (I_Render g, double centrex, double centrey, double radio, double rnormal, 
			double rsel,int cur, boolean polygon, boolean hideback ) {
		double step = Math.PI * 2 / n;
		FX.omSetLineWidth(g,3);
		FX.omSetColorStroke(g, Color.BLACK);
		FX.omDrawCercle (g,centrex,centrey,radio);
		FX.omDrawString (g,centrex-5,centrey,n+"");
		for (int i = 0; i < n; i++){
			double pointx = radio * Math.cos(Math.PI/2 + step*i);
			double pointy = radio * Math.sin(Math.PI/2 + step*i);
			FX.omDrawCercle (g,centrex-pointx,centrey-pointy,rnormal);
		}
		if (!hideback){
			int j = 0;
			for (List<Integer> lpoints : points){
				if (j != cur) 
					drawOneList (g,n,lpoints,centrex,centrey,radio,true,step,rsel, FX.SixtheenColors[j%16], polygon);
				j++;
			}
		}
		drawOneList (g,n,points.get(cur),centrex,centrey,radio,false,step,rsel, FX.SixtheenColors[cur%16], polygon);
		}
	
	public void drawOneList (I_Render g, int n, List<Integer> lpoints, double centrex, double centrey, 
			double radio, boolean dash, double step,double rsel, Color color, boolean polygone ) {
		if (dash) FX.omSetLineWidth(g,1); else FX.omSetLineWidth(g,2);
		FX.omSetColorStroke(g, color);
		double lastpointx = -1;
		double firstpointx = -1;
		double lastpointy = -1;
		double firstpointy = -1;
		
		for (int elem : lpoints){
			double pointx = radio * Math.cos(Math.PI/2 + step*elem);
			double pointy = radio * Math.sin(Math.PI/2 + step*elem);
			FX.omFillCercle (g,centrex-pointx,centrey-pointy,rsel);
			if (polygone) {
				if (lastpointx != -1){
					FX.omDrawLine(g,centrex-lastpointx, centrey-lastpointy,centrex-pointx, centrey-pointy );
				} else {
					firstpointx = pointx; firstpointy = pointy;
				}
				lastpointx = pointx; lastpointy = pointy;
				}
			if (polygone && firstpointx != -1) {
				FX.omDrawLine(g,centrex-lastpointx, centrey-lastpointy,centrex-firstpointx, centrey-firstpointy );
			}
		}
	}	
	
	//////////////////////EDITOR//////////////////////
    public static class NCercleEditor extends EditorView {

    	public  NCercleEditor () {
    		super ();
    	}
    	
        @Override
    	public String getPanelClass (){
    		return "projects.mathtools.classes.NCercle$NCerclePanel";
    	}
        
    }
    
    //////////////////////PANEL//////////////////////
    public static class NCerclePanel extends PanelCanvas {
    	
    	boolean hideback = false;
    	boolean polygone = true;
    	
    	public void init () {
    		omUpdateView(true);
    	}
    	
    	public void omMousePressed (double x, double y) {
    		System.out.println ("click en " + this);
    	}
    	
    	public void KeyHandler(String car){
    		 switch (car) {
			 case "h" : takeSnapShot ();
			 			break;
			 case "c": System.out.println ("(note-chan-color self))"); break;
			 case "b" : System.out.println ("(set-name-to-mus-obj self))"); break;
			 case "tab" : System.out.println ("show-help-window"); break;
			 case "p" : System.out.println ("score-set-tonalite"); break;
			 case "r" : System.out.println ("score-remove-tonalite"); break;
			 case "i" : System.out.println ("change-obj-mode "); break;
			 case "m" :  System.out.println ("change-obj-mode "); break;
		//	 otherwise "down" : beep; break;
			 
		 }
    	}
    	
/*    	(defmethod handle-key-event ((self cerclePanel) char)
    			  (case char
    			    (#\h (show-help-window "Commands for N-CERCLE Editor"
    			                           (get-help-list (editor self))))
    			    (#\c (complement-cercle self))
    			    (#\b (masque-others self))
    			    (:om-key-tab (change-current self))
    			    (#\p (provisoir-play self))
    			    (#\r (set-rotation-mode self))
    			    (#\i (inversion-cercle self))
    			    (#\m (change-interval-cercle self))
    			    (otherwise (om-beep))))
    			    */
  
    	
    	@Override
    	public void omUpdateView(boolean changedObject_p) {
    		super.omUpdateView(changedObject_p);
    		GCRender gc = getGCRender();
    		omViewDrawContents(gc);
    	}

    	public void omViewDrawContents (I_Render g) {
    		super.omViewDrawContents(g);
    		NCercle obj = (NCercle) getEditor().getObject();
    		obj.DrawContentsInRect(g, 0.0, w(), 0.0, h(), polygone, hideback);
    	}
    }	
    
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////

}

    
		
