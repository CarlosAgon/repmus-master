package projects.music.editors.drawables;

import gui.FXCanvas;
import gui.renders.I_Render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sun.javafx.geom.Rectangle;

import projects.music.classes.abstracts.Compose_L_MO;
import projects.music.editors.MusicalParams;
import projects.music.editors.Scale;
import projects.music.editors.SpacedPacket;
import projects.music.editors.StaffSystem;
import projects.music.editors.StaffSystem.MultipleStaff;

public class ContainerDrawable extends SimpleDrawable{

		public List<I_Drawable> inside = new ArrayList<I_Drawable>();		
		public List<SpacedPacket> timespacedlist;
		
		public List<I_Drawable> getInside() {
			return inside;
		}
		
		//On va dessiner en deux pas
		//d'abord on fait dans le temps avec drawObject
		//apres on fera par hierarchie avec drawContainersObjects
		@Override
		public void drawObject(I_Render g, FXCanvas panel, Rectangle rect,
				MusicalParams params, StaffSystem staffsys, List<I_Drawable> selection, double x0, double deltax) {
			for (SpacedPacket packed : timespacedlist) {
				for (SimpleDrawable obj : packed.objectlist ){
					obj.drawObject (g, panel, rect, params, staffsys, selection, packed.space, deltax);
				}
			}
			drawContainersObjects(g,  panel,  rect,  params, selection);
		}
		

		public void drawContainersObjects (I_Render g, FXCanvas panel, Rectangle rect,
				MusicalParams params, List<I_Drawable> selection) {
			for (I_Drawable obj : getInside()) {
				((SimpleDrawable) obj).drawContainersObjects(g,  panel,  rect, params, selection);
			}
			collectRectangle();
		}
		
		/*public void drawSelection(GraphicsContext g, List<MusicalObject> selection, int size){
			if  (selection.contains(ref))
				drawRectSelection(g);
			for (SimpleDrawable note : getInside()) {
				note.drawSelection(g,  selection,  size);
			}
		}
		*/
		
		public void collectRectangle () {
			double x = Integer.MAX_VALUE;
			double y = Integer.MAX_VALUE;
			double w = 0;
			double h = 0;
			for (I_Drawable obj : getInside()) {
				x = Math.min(x, obj.x());
				y = Math.min(y, obj.y());
				w = Math.max(w, obj.x() + obj.w());
				h = Math.max(h, obj.y() + obj.h());
				}
			setRectangle( x, y,  w-x, h-y);
		}
	
	
		///////////SPACING//////////
		
		public static Comparator<SpacedPacket> packetordered = new Comparator<SpacedPacket>() {

			public int compare(SpacedPacket p1, SpacedPacket p2) {
			   long t1 = p1.time;
			   long t2 = p2.time;
			   int rep;
			   if (t1 > t2) rep = 1;
			   else if (t2 > t1) rep = -1;
			   else rep =0;
			   return  rep;
		   }};
			

		public void collectTemporalObjects(List<SpacedPacket> timelist) {
			for (I_Drawable obj : getInside()) {
					((SimpleDrawable) obj).collectTemporalObjects(timelist);
			}
		}
		
		public void makeSpaceObjectList () {
			timespacedlist = new ArrayList<SpacedPacket>();
			collectTemporalObjects(timespacedlist);
			Collections.sort(timespacedlist, packetordered);
			groupTemporalObjects ();
		}
		
		private void groupTemporalObjects () {
			List<SpacedPacket> rep = new ArrayList<SpacedPacket>();
			SpacedPacket curpacket = null;
			for (SpacedPacket packet : timespacedlist) {
				 if (curpacket == null)
					 curpacket = new SpacedPacket(packet.objectlist.get(0), packet.time);
				 else if (packet.time == curpacket.time)
					 curpacket.objectlist.add(packet.objectlist.get(0));
				 else {
					 rep.add(curpacket);
					 curpacket = new SpacedPacket(packet.objectlist.get(0), packet.time);
				 }
			}
			if (curpacket != null) rep.add(curpacket);
			timespacedlist = rep;
		}
		
		/////////
		public void consTimeSpaceCurve (int size, double x) {
			for (SpacedPacket packed : timespacedlist) {
				packed.space = x + ms2pixel(packed.time, size);
				for (SimpleDrawable chord : packed.objectlist) {
					chord.centerX = packed.space;
				}
			}
		}
		
	
			
}