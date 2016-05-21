package kernel.frames.simples;


import java.util.List;

import resources.Loader;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import gui.FX;
import gui.FXCanvas;
import gui.renders.GCRender;
import gui.renders.I_Render;
import kernel.interfaces.I_Box;
import kernel.metaobjects.BoxInput;
import kernel.metaobjects.MethodBox;

public class MethodBoxFrame extends BoxFrame {
	
	BoxMethodCanvas canvas;
	String name;
	Image icon;
	
	public MethodBoxFrame (I_Box thebox, Point2D pos, String theicon) {
		super(thebox, pos, new Point2D(0,0), theicon);
		int count = 0;
		object = thebox;
     	String name = ((MethodBox) object).boxname;
     	double namew = FX.omStringSize (name, FX.default_font1);
     	double nameh = 18;
     	icon = Loader.getIconFromDic(theicon);
     	
		omSetViewPosition(pos);

     	int numins = thebox.getBoxInputs().size();
     	int numouts = thebox.getBoxOutputs().size();
     	omSetViewSize(Math.max (Math.max ( (Math.max (numins, numouts) * 9) , icon.getWidth()), namew) + 10 , 
     							icon.getHeight() + 20 + nameh);
     	
     	for (BoxInput inobj : thebox.getBoxInputs()){
     		InputBoxFrame input = new InputBoxFrame(inobj);
     		count++;
     		input.omSetViewPosition ((count * (w() / (numins + 1))) - 4, 1);
     		input.omSetViewSize (8, 8);
     		inputballs.add(input);
     		omAddSubviews(input);
     		
     	}
     	count = 0;
     	for (String outname : thebox.getBoxOutputs()){
     		OutputBoxFrame output = new OutputBoxFrame(outname, count);
     		count++;
     		output.omSetViewPosition ((count * (w() / (numouts + 1))) - 4, h() - 9);
     		output.omSetViewSize (8, 8);
     		outputballs.add(output);
     		omAddSubviews (output);
     	}
     	
     	canvas = new BoxMethodCanvas(icon, name, namew);
     	canvas.omSetViewSize(w(),h() - 18);
     	canvas.omSetViewPosition(0, 9);
     	omAddSubviews(canvas);
     	canvas.omUpdateView(true);

     	addResize (Math.max  (Math.max (9 * Math.max(outputballs.size(), inputballs.size()), namew ), icon.getWidth()) + 10,
     				 icon.getHeight() + nameh);
     	
     	
     	/*final DropShadow shadow = new DropShadow();
        shadow.setColor(Color.FORESTGREEN);
        final Glow glow = new Glow();
        glow.setInput(shadow);
        setEffect(glow);*/
		
	}
	
	public void setNewSize (double w, double h) {
		super.setNewSize(w,icon.getHeight() + 38);
		omSetViewSize(w,icon.getHeight() + 38);
		int count = 0;
		for (InputBoxFrame input : inputballs){
     		count++;
     		input.omSetViewPosition ((count * (w() / (inputballs.size() + 1))) - 4, h() - 9);
     	}
		count = 0;
		for (OutputBoxFrame output : outputballs){
     		count++;
     		output.omSetViewPosition ((count * (w() / (outputballs.size() + 1))) - 4, 1);
     	}
		canvas.omSetViewSize(w(),h() - 18);
		canvas.omUpdateView(true);
		resizebox.omSetViewPosition(w()-10,h()-10);
	}
	
	public class IconFrame  extends ImageView {
		public IconFrame (String icon) {
		Image image = Loader.getIconFromDic(icon);
		setImage(image);
	}

	}

protected class BoxMethodCanvas extends FXCanvas{
	Image icon;
	Point2D iconsize;
	String name;
	double namesize;
	
	public BoxMethodCanvas (Image theicon, String thename, double size) {
		super();
     	icon = theicon;
     	name = thename;
     	iconsize = new Point2D (icon.getWidth(), icon.getHeight());
     	namesize = size;
	}
	
	
	@Override
	public void omUpdateView(boolean changedObject_p) {
		super.omUpdateView(changedObject_p);
		GCRender gc = getGCRender();
		omViewDrawContents(gc);
	}

	public void omviewDrawContents (I_Render g) {
		super.omViewDrawContents(g);
		FX.drawImage(g, icon, w()/2 - iconsize.getX()/2 , (h()-16)/2 - iconsize.getY()/2);
		FX.omDrawString(g, w()/2 - namesize/2 , h() - 4,  name);

	}
	
	public List<MenuItem> getTheContextMenu () {
		return ((MethodBoxFrame) omViewContainer()).getTheContextMenu();
	}
	
	public boolean draggable (){
		return true;
	}
	
	public void omStartDragDrop (double x, double y) {
		BoxFrame bf = (BoxFrame) omViewContainer();
		bf.omStartDragDrop(x+bf.x(), y+bf.y());
	}
}

}
