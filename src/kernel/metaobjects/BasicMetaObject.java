package kernel.metaobjects;

import java.util.List;


import kernel.interfaces.I_BasicMetaObject;
import kernel.interfaces.I_MetaObject;

public class BasicMetaObject extends MetaObject implements I_BasicMetaObject {

	String name = "no name";
	int icon = 144;
	boolean selected = false;
	
	
	@Override
	public void setSelected (boolean sel) {
		selected = sel;
		
	}
	
	@Override
	public boolean getSelected () {
		return selected;
		
	}
	
	
	@Override
	public void omRename(String thename) {
		name = thename;
		
	}

	@Override
	public void omChangeContainer(I_MetaObject oldContainer,
			I_MetaObject newContainer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void omChangeIcon(int theicon) {
		icon = theicon;
		
	}

	@Override
	public I_MetaObject omCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void omAddElem(I_MetaObject elem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void omRemoveElem(I_MetaObject elem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<I_MetaObject> omGetElems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void omDelete() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getDocumentation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
