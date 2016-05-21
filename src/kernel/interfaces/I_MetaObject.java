package kernel.interfaces;

import java.util.List;

import javafx.scene.Scene;
import kernel.I_OMObject;
import kernel.frames.views.EditorView;

public interface I_MetaObject extends I_OMObject{
	
	public String editorClass();
	public boolean omIseditorOpen();
	public EditorView omGetEditor ();
	public EditorView omOpenEditor (Scene scene);
	
			public void omRename (String name);
			public void omChangeContainer (I_MetaObject oldContainer, I_MetaObject newContainer);
			public void omChangeIcon (int icon);
			public I_MetaObject omCopy ();
			public void omAddElem (I_MetaObject elem);
			public void omRemoveElem (I_MetaObject elem);
			public List<I_MetaObject> omGetElems ();
			public void omDelete ();

			
			public String getDocumentation ();
			public String getIcon ();
			public String getName ();
}