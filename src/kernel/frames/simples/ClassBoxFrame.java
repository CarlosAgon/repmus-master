package kernel.frames.simples;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import resources.Loader;
import javafx.geometry.Point2D;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import gui.FX;
import gui.FXCanvas;
import gui.dialogitems.OmMenu;
import gui.dialogitems.OmMenuItem;
import gui.renders.GCRender;
import gui.renders.I_Render;
import kernel.frames.views.EditorView;
import kernel.interfaces.I_Box;
import kernel.metaobjects.BoxInput;
import kernel.metaobjects.ClassBox;
import kernel.metaobjects.Patch.PatchPanel;
import kernel.metaobjects.Patch;

public class ClassBoxFrame extends BoxFrame {

	BoxClassCanvas canvas;
	
	public ClassBoxFrame (I_Box thebox, Point2D pos,  Point2D size, String theicon) {
		super(thebox, pos, size, theicon);
		int count = 0;
		object = thebox;
     	omSetViewPosition(pos);
     	omSetViewSize(size);
     	int numins = thebox.getBoxInputs().size();
     	for (BoxInput inobj : thebox.getBoxInputs()){
     		InputBoxFrame input = new InputBoxFrame(inobj);
     		count++;
     		input.omSetViewPosition ((count * (w() / (numins + 1))) - 4, 1);
     		input.omSetViewSize (8, 8);
     		inputballs.add(input);
     		omAddSubviews(input);
     	}
     	count = 0;
     	int numouts = thebox.getBoxOutputs().size();
     	for (String outname : thebox.getBoxOutputs()){
     		OutputBoxFrame output = new OutputBoxFrame(outname, count);
     		count++;
     		output.omSetViewPosition ((count * (w() / (numouts + 1))) - 4, h() - 9);
     		output.omSetViewSize (8, 8);
     		outputballs.add(output);
     		omAddSubviews (output);
     	}
     	Image icon = Loader.getIconFromDic(theicon);
     	canvas = new BoxClassCanvas(icon, object);
     	canvas.omSetViewSize(w(),h() - 18);
     	canvas.omSetViewPosition(0, 9);
     	omAddSubviews(canvas);
     	canvas.omUpdateView(true);
     	addResize(9 * Math.min(outputballs.size(), inputballs.size()) + 10, 20 + icon.getHeight());
	}
	
	public void setNewSize (double w, double h) {
		super.setNewSize(w,h);
		omSetViewSize(w,h);
		int count = 0;
		for (InputBoxFrame input : inputballs){
     		count++;
     		input.omSetViewPosition ((count * (w() / (inputballs.size() + 1))) - 4, 1);
     	}
		count = 0;
		for (OutputBoxFrame output : outputballs){
     		count++;
     		output.omSetViewPosition ((count * (w() / (outputballs.size() + 1))) - 4, h() - 9);
     	}
		canvas.omSetViewSize(w(),h() - 18);
		canvas.omUpdateView(true);
		resizebox.omSetViewPosition(w()-10,h()-10);
	}
	       
	public boolean draggable (){
		return true;
	}
	
	public List<MenuItem> getTheContextMenu (){
		List<MenuItem> rep = new ArrayList<MenuItem>();
		OmMenu constr = new OmMenu ("Constructeurs");
		rep.add(constr);
		rep.add(new SeparatorMenuItem());
		rep.add(new OmMenuItem("Import", null));
		rep.add(new OmMenuItem("Export", null));
		rep.add(new SeparatorMenuItem());
		OmMenu methods = new OmMenu ("Methods");
		List<Method> metList = ((ClassBox) object).getTheMethods();
		for (Method met : metList) {
			methods.getItems().add(new OmMenuItem(met.getName(), evt-> {addNewMethod(met);}));
		}
		rep.add(methods);
		return rep;
	}
	
	public void omDoubleClick (double x, double y) {
		EditorView valeditor =  object.omOpenEditor(omGetScene());
	}

	public void addNewMethod (Method met){
		Patch patch = (Patch) ((PatchPanel)omViewContainer()).getEditor().getObject();
		patch.addBoxMethod (met, met.getName(), new Point2D (250,250));
	}
	
	public void changePreviewMode (){
		((ClassBox) object).preview = ! ((ClassBox) object).preview;
		canvas.omUpdateView(true);
	}
	//////////////////////SELECTED EVENTS
	public void evalBox (){
		((ClassBox) object).eval(0);
	}
	
	public void reDraw (boolean changed){
		canvas.omUpdateView(changed);
	}
	
	///////////////////////
	protected class BoxClassCanvas extends FXCanvas{
		Image icon;
		Image pict;
		Point2D iconsize;
		I_Box box;
		
		public BoxClassCanvas (Image theicon, I_Box thebox) {
			super();
	     	icon = theicon;
	     	iconsize = new Point2D (icon.getWidth(), icon.getHeight());
	     	pict = Loader.getImageFromDic("editorpict");
	     	box = (ClassBox) thebox;
		}
		
		@Override
		public void omUpdateView(boolean changedObject_p) {
			super.omUpdateView(changedObject_p);
			GCRender gc = getGCRender();
			omViewDrawContents(gc);
		}

		public void omViewDrawContents (I_Render g) {
			super.omViewDrawContents(g);
			FX.omSetColorStroke(g, Color.BLACK);
			FX.omDrawRect(g, 0, 0, w(), h());
			if (((ClassBox) box).preview) {
				Method met = ((ClassBox) box).getDrawMethod();
				Object value = ((ClassBox) box).getValue();
				if (met != null && value != null) {
					try {
						met.invoke (value, g, this, 0, w(), 0, h(), ((ClassBox) box).params);
						
						/*Image rep = (Image) met.invoke (value, g, 0, w(), 0, h());
						FX.drawImage(g, rep, 0, 0, w(), h());*/
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				FX.drawImage(g, pict, 0, 0, w(), h());
				FX.drawImage(g, icon, w()/2 - iconsize.getX()/2 , h()/2 - iconsize.getY()/2);
			}
		}
		
		
		public boolean draggable (){
			return true;
		}
		
		public List<MenuItem> getTheContextMenu (){
			BoxFrame bf = (BoxFrame) omViewContainer();
			return bf.getTheContextMenu();
		}
		
		public void omDoubleClick (double x, double y) {
			BoxFrame bf = (BoxFrame) omViewContainer();
			bf.omDoubleClick(x+x(), y+ y());
		}
		
		public void omStartDragDrop (double x, double y) {
			BoxFrame bf = (BoxFrame) omViewContainer();
			bf.omStartDragDrop(x+bf.x(), y+bf.y());
		}
		
		public void omMousePressed (double x, double y) {
			BoxFrame bf = (BoxFrame) omViewContainer();
			bf.omMousePressed(x+bf.x(), y+bf.y());
    		}
	}
	
}
