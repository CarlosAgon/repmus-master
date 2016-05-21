package kernel.metaobjects;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import kernel.frames.simples.BoxFrame;
import kernel.frames.simples.ClassBoxFrame;
import kernel.interfaces.I_Box;
import kernel.interfaces.I_Frame;
import kernel.interfaces.I_SimpleFrame;

public class Box extends BasicMetaObject implements I_Box {
	
	transient Object reference = null;
	public  String icon = "181";
	public transient boolean selected = false;
	Point2D iconsize;
	Point2D framesize = null;
	Point2D frameposition = null;
	public  String boxname = "no name";
	transient String doc;
	Object value;
	List<BoxInput> inputs;
	List<String> outputs;
	public transient I_SimpleFrame boxframe;
	
	// donne le rapport de la taille de la boite en fonction du nombre d'inputs/outputs
	transient double boxinputssizefactor =  10;
	
	
	@Override
	public Object getReference() {
		return reference;
	}


	@Override
	public Class getFrameClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class getInputClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class getoutputClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFrameName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public I_SimpleFrame makeFrame() {
		return null;
	}
	

	@Override
	public void afterremove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveBox() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getBoxOutputs() {
		return outputs;
	}
	
	@Override
	public List<BoxInput> getBoxInputs() {
		return inputs;
	}
	
	public boolean getSelected() {
		return selected;
	}
	
	public void setSelected(boolean sel) {
		 selected = sel;
	}
	


}
