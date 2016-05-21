package kernel.metaobjects;

import java.io.Serializable;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

import kernel.annotations.StringParser;


public class BoxInput implements Serializable{

	public String name;
	public int index;
	transient public Class<?> type;
	transient public Type ptype;
	public Object val;
	
	public BoxInput (String thename, int theindex, Class<?> thetype, Type theptype, Object defval) {
		name = thename;
		index = theindex;
		ptype = theptype;
		type = thetype;
		val = defval;
	}
	
	
	public void changeVal (String newval) {
		System.out.println ("voila el tipo " + type);
		Object rep = StringParser.str2Object (newval,type,ptype); 
		System.out.println ("nuevo val " + rep);
		if (rep != null)
			val  = rep;
	}
}
