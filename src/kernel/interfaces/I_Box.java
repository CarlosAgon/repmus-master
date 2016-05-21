package kernel.interfaces;

import java.util.List;

import kernel.frames.simples.InputBoxFrame;
import kernel.metaobjects.BoxInput;

public interface I_Box extends I_BasicMetaObject{

Object getReference();
List<BoxInput> getBoxInputs();
List<String> getBoxOutputs();
/*
			    (frame-position :initform nil  :accessor frame-position)
			    (frame-size :initform nil  :accessor frame-size)
*/			    
			    

Class getFrameClass();
Class getInputClass();
Class getoutputClass();

String getFrameName();

I_SimpleFrame makeFrame ();
void afterremove();
void moveBox();
}
