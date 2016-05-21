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

public class ContainerDrawableS extends ContainerDrawable{

		int clefSpace = 0;
		int measureSpace = 0;
		int signatureSpace = 0;
		int alterationSpace = 0;
		int headsSpace = 0;
		int graceSpace = 0;
		
		/////////
		public void consTimeSpaceCurve (int size, double x) {
			for (SpacedPacket packed : timespacedlist) {
				packed.space = x + ryhtm2pixels(packed.time, size);
				for (SimpleDrawable chord : packed.objectlist) {
					chord.centerX = packed.space;
				}
			}
		}
		
		
	/*	public void alignSpacedList (int size) {
				int count = 0;
				int i = 1;
				int length = timespacedlist.size();
				long nextdur;
				for (SpacedPacket packet : timespacedlist) {
					if (i < length)
						nextdur = timespacedlist.get(i).time - packet.time;
					else
						nextdur = 2000;
					count = packet.spacePacket (count, nextdur, size);
					i++;
				}
			}
			*/
			
}