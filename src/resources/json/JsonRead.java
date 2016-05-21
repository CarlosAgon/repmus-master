package resources.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kernel.tools.Fraction;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import projects.music.classes.abstracts.MusicalObject;
import projects.music.classes.abstracts.RTree;
import projects.music.classes.music.Chord;
import projects.music.classes.music.ChordSeq;
import projects.music.classes.music.Note;
import projects.music.classes.music.RChord;
import projects.music.classes.music.Voice;


public class JsonRead {
	
	
	public static List<Integer> getintlist (JSONArray obj) {
		List<Integer> rep = new ArrayList<Integer>();
		Iterator<Long> iterator = obj.iterator();
		while (iterator.hasNext()) 
			rep.add( ((Long) (iterator.next())).intValue());
		return rep;
	}
	
	public static List<List<Integer>> getintlistlist (JSONArray obj) {
		List<List<Integer>> rep = new ArrayList<List<Integer>>();
		Iterator<JSONArray> iterator = obj.iterator();
		while (iterator.hasNext()) 
			rep.add( getintlist (iterator.next()));
		return rep;
	}
	
	public static List<Long> getlonglist (JSONArray obj) {
		List<Long> rep = new ArrayList<Long>();
		Iterator<Long> iterator = obj.iterator();
		while (iterator.hasNext()) 
			rep.add((Long) (iterator.next()));
		return rep;
	}
	
	public static List<List<Long>> getlonglistlist (JSONArray obj) {
		List<List<Long>> rep = new ArrayList<List<Long>>();
		Iterator<JSONArray> iterator = obj.iterator();
		while (iterator.hasNext()) 
			rep.add( getlonglist (iterator.next()));
		return rep;
	}
	
	public static List<Chord> getchordlist (JSONArray obj) {
		List<Chord> rep = new ArrayList<Chord>();
		for (Object item : obj)
			rep.add(getchord ((JSONObject) item));
		return rep;
	}
	
	public static List<RChord> getrchordlist (JSONArray obj) {
		List<RChord> rep = new ArrayList<RChord>();
		for (Object item : obj)
			rep.add(getrchord ((JSONObject) item));
		return rep;
	}
	
	public static RChord getrchord (JSONObject jsonObject) {
		JSONArray lmidic = (JSONArray) jsonObject.get("lmidic");
		JSONArray lvel = (JSONArray) jsonObject.get("lvel");
		JSONArray loffset = (JSONArray) jsonObject.get("loffset");
		JSONArray ldur = (JSONArray) jsonObject.get("ldur");
		JSONArray lchan = (JSONArray) jsonObject.get("lchan");
		return new RChord (getintlist(lmidic), getintlist(lvel), getlonglist(loffset),new Fraction(1),getintlist(lchan));
	}
	
	
	public static Chord getchord (JSONObject jsonObject) {
		JSONArray lmidic = (JSONArray) jsonObject.get("lmidic");
		JSONArray lvel = (JSONArray) jsonObject.get("lvel");
		JSONArray loffset = (JSONArray) jsonObject.get("loffset");
		JSONArray ldur = (JSONArray) jsonObject.get("ldur");
		JSONArray lchan = (JSONArray) jsonObject.get("lchan");
		return new Chord (getintlist(lmidic), getintlist(lvel), getlonglist(loffset),getlonglist(ldur),getintlist(lchan));
	}
	
	public static ChordSeq getchordseq (JSONObject jsonObject) {
		JSONArray lmidic = (JSONArray) jsonObject.get("lmidic");
		JSONArray lonset = (JSONArray) jsonObject.get("lonset");
		JSONArray lvel = (JSONArray) jsonObject.get("lvel");
		JSONArray loffset = (JSONArray) jsonObject.get("loffset");
		JSONArray ldur = (JSONArray) jsonObject.get("ldur");
		JSONArray lchan = (JSONArray) jsonObject.get("lchan");
		int legato = ((Long) (jsonObject.get("legato"))).intValue();
		return new ChordSeq (getintlistlist(lmidic), getlonglist(lonset), getlonglistlist(ldur),
						getintlistlist(lvel),getlonglistlist(loffset),getintlistlist(lchan), legato);
	}
	
	public static Note getnote (JSONObject jsonObject) {
		int midic = ((Long)(jsonObject.get("midic"))).intValue();
		int vel = ((Long)( jsonObject.get("vel"))).intValue();
		int dur = ((Long) (jsonObject.get("dur"))).intValue();
		int chan = ((Long) (jsonObject.get("chan"))).intValue();
		return new Note (midic, vel, dur,chan);
		}
	
	
	public static List<RTree> getpropslist (JSONArray obj) {
		List<RTree> rep = new ArrayList<RTree>();
		for (Object item : obj)
			rep.add(getRtree ((JSONObject) item));
		return rep;
	}
	
	public static List<RTree> getLRtree (JSONObject jsonObject) {
		List<RTree> props=null;
		String name = (String) jsonObject.get("class");
		if (name.equals("RTree")) {
			long dur = (Long)(jsonObject.get("dur"));
			JSONArray prop = (JSONArray) jsonObject.get("props");
			props=  getpropslist (prop);
		}
		return props;
	}
		
	public static RTree getRtree (JSONObject jsonObject) {
		String name = (String) jsonObject.get("class");
		if (name.equals("RTree")) {
			long dur = (Long)(jsonObject.get("dur"));
			JSONArray prop = (JSONArray) jsonObject.get("props");
			List<RTree> props=  getpropslist (prop);
			return new RTree (dur,props);
		}
		else if (name.equals("SRTree")) {
			JSONObject dur = (JSONObject) jsonObject.get("dur");
			JSONArray ratio = (JSONArray) dur.get("numdenum");
			long den =  (Long) (ratio.get(0));
			long num =  (Long) (ratio.get(1));
			Fraction rdur = new Fraction(den,num);
			JSONArray prop = (JSONArray) jsonObject.get("props");
			List<RTree> props=  getpropslist (prop);
			return new RTree (rdur,props);
		}
		else {
			long prop = (Long)(jsonObject.get("dur"));
			long cont = (Long)(jsonObject.get("cont"));
			if (cont == 0)
				return new RTree (prop, false);
			else return new RTree (prop, true);
		}
	}
	
	///////////////////////////////////////////////////
    public static MusicalObject getFile (String file) {
	JSONParser parser = new JSONParser();
	try {
		Object obj = parser.parse(new FileReader(file));
		JSONObject jsonObject = (JSONObject) obj;
		String name = (String) jsonObject.get("class");
		if (name.equals("Note")) {
			return (Note) getnote(jsonObject);
		}
		else if (name.equals("Chord")) {
			return (Chord) getchord(jsonObject);
		}
		else if (name.equals("ChordSeq")) {
			return (ChordSeq) getchordseq(jsonObject);
		}
		else if (name.equals("Voice")) {
			
			JSONArray chords = (JSONArray) jsonObject.get("chords");
			List<RChord> chordlists=  getrchordlist (chords);
			JSONObject rtree = (JSONObject) jsonObject.get("tree");
			List<RTree> thetreelist =  getLRtree (rtree);
			System.out.println("chords " + chordlists);
			Voice rep =  new Voice (thetreelist, chordlists, 60);
			System.out.println("parsed1 " + rep.treelist);
			return rep;
		}
		else return null;
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}
	return null;
    }
    
	///////////////////////////////////////////////////
    public static MusicalObject getStr (String jsonString) {

	JSONParser parser = new JSONParser();
	try {
		Object obj = parser.parse(jsonString);
		JSONObject jsonObject = (JSONObject) obj;
		String name = (String) jsonObject.get("class");
		if (name.equals("Note")) {
			return (Note) getnote(jsonObject);
		}
		else if (name.equals("Chord")) {
			return (Chord) getchord(jsonObject);
		}
		else if (name.equals("ChordSeq")) {
			return (ChordSeq) getchordseq(jsonObject);
		}
		else if (name.equals("Voice")) {
			
			JSONArray chords = (JSONArray) jsonObject.get("chords");
			List<RChord> chordlists=  getrchordlist (chords);
			JSONObject rtree = (JSONObject) jsonObject.get("tree");
			List<RTree> thetreelist =  getLRtree (rtree);
			System.out.println("chords " + chordlists);
			Voice rep =  new Voice (thetreelist, chordlists, 60);
			System.out.println("parsed1 " + rep.treelist);
			return rep;
		}
		else return null;
	} catch (ParseException e) {
		e.printStackTrace();
	}
	return null;
    }

}