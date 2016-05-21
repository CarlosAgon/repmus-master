package projects.music.editors.drawables;

import projects.music.editors.SpacedPacket;

public class ContainerDrawableL extends ContainerDrawable{

		public void consTimeSpaceCurveLisse (int size, double x) {
			for (SpacedPacket packed : timespacedlist) {
				packed.space = x + ms2pixel(packed.time, size);
				for (SimpleDrawable chord : packed.objectlist) {
					chord.centerX = packed.space;
				}
			}
		}
			
}