package kernel.metaobjects;

import gui.dialogitems.OmMenuItem;
import gui.dialogitems.OmStaticText;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.control.MenuItem;
import kernel.annotations.Omclass;
import kernel.annotations.Omvariable;
import kernel.frames.simples.InputBoxFrame;
import kernel.frames.simples.MethodBoxFrame;
import kernel.frames.simples.OutputBoxFrame;
import kernel.interfaces.I_Box;
import kernel.interfaces.I_SimpleFrame;

public class MethodBox extends Box implements I_Box {
	List<BoxInput> inputs;
	List<String> outputs;
	Class theclass;
	
	public MethodBox (Method meth, Point2D pos, String name)  {
		reference = meth;
		theclass = meth.getDeclaringClass();
		frameposition = pos;
		boxname = name;
		setOutputs();
		setInputs();
		Omclass annotation = (Omclass) theclass.getAnnotation(kernel.annotations.Omclass.class); 
		icon = annotation.icon();
	}
	
	@Override
	public List<String> getBoxOutputs() {
		return outputs;
	}
	
	@Override
	public List<BoxInput> getBoxInputs() {
		return inputs;
	}
	
	public void setOutputs() {
		outputs = new ArrayList<String>();
		outputs.add("output");
	} 
	
	
	public void setInputs() {
		inputs = new ArrayList<BoxInput>();
		inputs.add(new BoxInput(theclass.getName(),0,theclass,theclass,null));
		Parameter[] paramlist = ((Method) reference).getParameters();
		for (int i = 0; i < paramlist.length; i++) {
				inputs.add(new BoxInput(paramlist[i].getName(),i,paramlist[i].getType(),paramlist[i].getParameterizedType(), null));
			}
	}
	
	public boolean draggable (){
		return true;
	}
	
	public List<MenuItem> getTheContextMenu (){
		List<MenuItem> rep = new ArrayList<MenuItem>();
		rep.add(new OmMenuItem("constructeur", null));
		return rep;
	}
	
	@Override
	public I_SimpleFrame makeFrame() {
		I_SimpleFrame frame = new MethodBoxFrame (this, frameposition, icon );
		boxframe = frame;
		return frame;
	}
	
	
}
	

