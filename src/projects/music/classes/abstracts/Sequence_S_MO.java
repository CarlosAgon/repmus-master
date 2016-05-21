package projects.music.classes.abstracts;

import java.util.ArrayList;
import java.util.List;

import projects.music.classes.music.RChord;

public class Sequence_S_MO extends Compose_S_MO {

	public List<RChord> chords;
	
	
	public void nextChords (int n, RChord defchord) {
		if (chords.size() == 0){
			chords = new ArrayList<RChord>();
			chords.add(defchord);
		}
		else if (chords.size() > 1) {
			if (n >= chords.size()) {
				chords = new ArrayList<RChord>();
				chords.add(defchord);
			}
			else chords = chords.subList(n-1, chords.size() -1);
		}
	}

}
