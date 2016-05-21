package kernel.metaobjects;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import kernel.annotations.Ombuilder;
import kernel.annotations.Omclass;
import kernel.annotations.Ommethod;
import kernel.annotations.Omvariable;
import kernel.annotations.StringParser;
import kernel.frames.simples.ClassBoxFrame;
import kernel.frames.views.EditorView;
import kernel.frames.views.I_EditorParams;
import kernel.interfaces.I_SimpleFrame;
import kernel.I_OMObject;
import launch.RepMus.OmScene;

public class ClassBox extends Box implements Serializable{

	private static final long serialVersionUID = -2352476226740265796L;
	transient List<Constructor> constructorList = null;
	transient Constructor  constructor = null;
	transient List<Omvariable> sorties = new ArrayList<Omvariable>();

	public Class reference;
	public boolean preview = true;
	public I_OMObject value;
	public String editorclass;
	public I_EditorParams params = null;

	int consInd = 0;

	public ClassBox (String clazz, Point2D pos, String name) throws ClassNotFoundException {
		 reference = Class.forName(clazz);
		 frameposition = pos;
		 setOutputs();
		 setConstructeurs();
		 setInputs();
		 Omclass annotation = (Omclass) reference.getAnnotation(kernel.annotations.Omclass.class);
		 icon = annotation.icon();
		 editorclass = annotation.editorClass();
		 try {
			value = (I_OMObject) reference.newInstance();
			Method paramsmet = getParamsMethod();
			if (paramsmet != null){
				try {
					params = (I_EditorParams) paramsmet.invoke(value);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (InstantiationException e) {
			value = null;
		} catch (IllegalAccessException e) {
			value = null;
		}
	}


	@Override
	public EditorView omOpenEditor(Scene scene) {
		if (omIseditorOpen()) {
			((OmScene) scene).searchEditor(editor);
			return omGetEditor();
		}
		else {
		editor = edmaker.makeEditorView (value, this, editorClass(), new Point2D(400, 400), new Point2D(0,0), params );
		((OmScene) scene).addEditor(editor, 2);
		return omGetEditor();
		}
	}

	@Override
	public I_SimpleFrame makeFrame() {
		I_SimpleFrame frame = new ClassBoxFrame (this, frameposition,new Point2D(100, 60), icon );
		boxframe = frame;
		return frame;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String editorClass() {
		return editorclass;
	}


	public Object makeDefaultInstance () {
		try {
			return  ((Class) reference).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("no fui capaz de hacer una instancia par defaul" + reference);
			return null;
		}
	}


	public void setOutputs() {
		outputs = new ArrayList<String>();
		outputs.add("self");
		Field[] champs = reference.getFields();
		for (int i = 0; i < champs.length; i++) {
			Omvariable annotation = (Omvariable) champs[i].getAnnotation(kernel.annotations.Omvariable.class);
			if (annotation != null){
				sorties.add(annotation);
				outputs.add("sortie i");
			}
		}
	}

	public void setConstructeurs() {
		Constructor [] constructors = reference.getConstructors ();
		constructorList = new ArrayList<Constructor>();
		for (int i = 0; i < constructors.length; i++) {
			Ombuilder annot = (Ombuilder) constructors[i].getAnnotation(kernel.annotations.Ombuilder.class);
			if (annot != null)
				constructorList.add(constructors[i]);
		}
		 if (constructorList.size() > 0){
			 constructor = constructorList.get(consInd);
		 }
	}

	public void setInputs() {
		inputs = new ArrayList<BoxInput>();
		if (constructor != null){
			Ombuilder annot = (Ombuilder) constructor.getAnnotation(kernel.annotations.Ombuilder.class);
			String defval = annot.definputs();
			Object[] defvals = StringParser.getInputsDef(defval,constructor);
			Parameter[] paramlist = constructor.getParameters();
			for (int i = 0; i < paramlist.length; i++) {
				inputs.add(new BoxInput(paramlist[i].getName(),i,paramlist[i].getType(),
						paramlist[i].getParameterizedType(), defvals[i]));
			}
		}
	}

	public List<Method> getTheMethods () {
		Method[] methodes = reference.getMethods();
		List<Method> rep = new ArrayList<Method>();
		for (int i = 0; i < methodes.length; i++) {
			Ommethod annot = (Ommethod) methodes[i].getAnnotation(kernel.annotations.Ommethod.class);
			if (annot != null)
				rep.add(methodes[i]);
		}
		return rep;
	}

	public Method getDrawMethod () {
		Method[] methodes = reference.getMethods();
		for (int i = 0; i < methodes.length; i++) {
			if (methodes[i].getName().equals("drawPreview")) {
				return methodes[i];
			}
		}
		return null;
	}

	public Method getParamsMethod () {
		Method[] methodes = reference.getMethods();
		for (int i = 0; i < methodes.length; i++) {
			if (methodes[i].getName().equals("getParams")) {
				return methodes[i];
			}
		}
		return null;
	}

	////////////////////EVAL
	public I_OMObject eval (int output) {
		System.out.println("si eval ");
		Object [] initargs = new Object [inputs.size()];
		int i = 0;
		for (BoxInput input : inputs){
			initargs[i] = input.val;
			i++;
		}
		try {
			Object rep = constructor.newInstance(initargs);
			value = (I_OMObject) rep;
			if (boxframe != null)
				((ClassBoxFrame) boxframe).reDraw(true);
			if (editor != null){
				editor.setObject(value);
				editor.getPanel().updatePanel (true);
			}
			System.out.println("-> : " + rep);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return null;
	}


}
