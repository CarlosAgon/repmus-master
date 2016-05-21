package projects.music.editors;

import java.util.ArrayList;
import java.util.List;

import projects.music.editors.drawables.SimpleDrawable;


//A set of objects to draw at the same time
public class SpacedPacket {
	public List<SimpleDrawable> objectlist =new ArrayList<SimpleDrawable>();;
	public long time;
	public double space;
		
	public SpacedPacket (SimpleDrawable obj, long at, double pos) {
		objectlist.add(obj);
		time = at;
		space = pos;
	}
	
	public SpacedPacket (SimpleDrawable obj, long at) {
		objectlist.add(obj);
		time = at;
	}

	public int spacePacket(int count, long nextdur, int size) {
		return 0;
	}
}

/*
clefSpace
measureSpace
signatureSpace
alterationSpace
headsSpace
graceSpace
*/

/*
 breakLine
 breakPage
 changeClef
 changeSystem
 */

/*
 newMultiSystem
 finMultiSystem
 */

