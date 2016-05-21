package kernel.metaobjects;

import java.io.Serializable;

import kernel.frames.simples.InputBoxFrame;
import kernel.frames.simples.OutputBoxFrame;
import kernel.interfaces.I_Box;

public class Connection  implements Serializable {
	
	transient I_Box source;
	int outnum;
	transient I_Box target;
	int innum;
	
	public Connection (I_Box thesource, int theoutnum, I_Box thetarget, int theinnum) {
		source = thesource;
		outnum = theoutnum;
		target = thetarget;
		innum = theinnum;
	}
	

}
