package kernel.metaobjects;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import kernel.frames.views.EditorMaker;
import kernel.frames.views.EditorView;
import kernel.interfaces.I_MetaObject;

public class MetaObject implements I_MetaObject
{
	public EditorView editor = null;
	EditorMaker edmaker = new EditorMaker ();
	
	
	@Override
	public boolean omIseditorOpen() {
		return editor != null;
	}
	
	@Override
	public EditorView omGetEditor () {
		return editor;		
	}
	
	@Override
	public EditorView omOpenEditor(Scene scene) {
		if (omIseditorOpen())
			return omGetEditor();
		else {
		editor = edmaker.makeEditorView (this, this, editorClass(), new Point2D(400, 400), new Point2D(0,0), null );
		return omGetEditor();
		}
	}

	@Override
	public void omRename(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void omChangeContainer(I_MetaObject oldContainer,
			I_MetaObject newContainer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void omChangeIcon(int icon) {
		// TODO Auto-generated method stub
		
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


	@Override
	public String editorClass() {
		return "kernel.frames.views.EditorView";
	}

}
