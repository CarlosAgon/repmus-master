package projects.mathtools.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import gui.FX;
import gui.FX.AnchorRect;
import gui.FXCanvas;
import gui.renders.GCRender;
import gui.renders.I_Render;
import projects.music.classes.music.Chord;
import kernel.I_OMObject;
import kernel.annotations.Ombuilder;
import kernel.annotations.Omclass;
import kernel.frames.views.EditorView;
import kernel.frames.views.I_EditorParams;
import kernel.frames.views.PanelCanvas;
import kernel.tools.ST;


@Omclass(icon = "421", editorClass = "projects.mathtools.classes.IsographyExplorer$IsoExpEditor")
public class IsographyExplorer  implements I_OMObject, Serializable {

	List<List<Integer>> chordList;
	List<List<List<Integer>>> permutList = new ArrayList<List<List<Integer>>>();
	List<Double> posList = new ArrayList<Double>();
	List<Integer> curFunList;
	List<Integer> curIsoList;
	List<Integer> curPerList;
	List<List<CompleteIsographyCol>> completeList = new ArrayList<List<CompleteIsographyCol>>();
	List<List<LocalIsographyCol>> localList  =new ArrayList<List<LocalIsographyCol>>();
	double size = 24;

	public IsographyExplorer (List<Chord> thechordList) {
		//length doit etre + rand que 2
		List<List<Integer>> chords = new ArrayList<List<Integer>>();
		for (Chord chord :thechordList)
			chords.add(chord.getLMidic());
		chordList= MT.mc2Z12(chords);
		initSlots ();
	}

	@Ombuilder
	public IsographyExplorer (Chord chord1, Chord chord2) {
		this(MT.mc2Z12(chord1.getLMidic()), MT.mc2Z12(chord2.getLMidic()));
	}

	public IsographyExplorer (List<Integer> chord1, List<Integer> chord2) {
		//length doit etre + rand que 2
		List<List<Integer>> chords = new ArrayList<List<Integer>>();
		chords.add(chord1);
		chords.add(chord2);
		chordList= chords;
		initSlots ();
	}

	public IsographyExplorer () {
		List<Integer> input1 = new ArrayList <Integer>();
		input1.add(4);
		input1.add(0);
		input1.add(7);
		List<Integer> input2 = new ArrayList <Integer>();
		input2.add(3);
		input2.add(7);
		input2.add(0);
		List<List<Integer>> chords = new ArrayList<List<Integer>>();
		chords.add(input1);
		chords.add(input2);
		chordList= chords;
		initSlots ();
	}

	public void initSlots () {
			int length = chordList.size();
			for (int i=0; i< length; i++){
				List<List<Integer>> perm = MT.generatePerm(chordList.get(i));
				permutList.add(perm);
				posList.add(i * size * 10);
			}
			for (int i=0; i< length-1; i++){
				completeList.add(getCompleteColumn(i,i+1));
				localList.add(getlocalColumn(i,i+1));
			}
			curFunList = ST.createList(length, 0);
			curIsoList = ST.createList(length, 0);
			curPerList = ST.createList(length, 0);
		}

	////////////////////////TOOLS///////////////////////////////

	public static List<Functor> getFunctorF (List<Integer> ch) {
		int x = ch.get(0);
		int y = ch.get(1);
		int z = ch.get(2);

		List<Functor> rep =new ArrayList<Functor> ();
		List<Transition> temp = new ArrayList<Transition> ();
		temp.add( new Transition (new int[]{1, MT.mod(y-x, 12)}));
		temp.add( new Transition (new int[]{1, MT.mod(z-y, 12)} ));
		temp.add( new Transition (new int[]{1, MT.mod(z-x, 12)} ));
		rep.add(new Functor (temp));

		temp = new ArrayList<Transition> ();
		temp.add( new Transition (new int[]{1, MT.mod(y-x, 12)} ));
		temp.add( new Transition (new int[]{11, MT.mod(z+y, 12)} ));
		temp.add( new Transition (new int[]{11, MT.mod(z+x, 12)} ));
		rep.add(new Functor (temp));

		temp = new ArrayList<Transition> ();
		temp.add( new Transition (new int[]{11, MT.mod(y+x, 12)} ));
		temp.add( new Transition (new int[]{1, MT.mod(z-y, 12)} ));
		temp.add( new Transition (new int[]{11, MT.mod(z+x, 12)} ));
		rep.add(new Functor (temp));

		temp = new ArrayList<Transition> ();
		temp.add( new Transition (new int[]{11, MT.mod(y+x, 12)} ));
		temp.add( new Transition (new int[]{11, MT.mod(z+y, 12)} ));
		temp.add( new Transition (new int[]{1, MT.mod(z-x, 12)} ));
		rep.add(new Functor (temp));

		return rep;
	}

	///////////////////////////////////////////////////////////////////////

	public List<CompleteIsographyCol> getCompleteColumn (int index1, int index2) {
		List<List<Integer>> perm1 = permutList.get(index1);
		List<List<Integer>> perm2 = permutList.get(index2);
		List<CompleteIsographyCol> rep = new ArrayList<CompleteIsographyCol>();
		for (int i = 0; i < perm1.size() ; i++) {
			List<Integer> curperm1 = perm1.get(i);
			for (int j = 0; j < perm2.size() ; j++) {
				List<Integer> curperm2 = perm2.get(j);
				CompleteIsography complete_iso = getCompleteIso (curperm1,curperm2);
				if (complete_iso != null){
					rep.add(new CompleteIsographyCol(i,j,complete_iso));
					break;
				}
			}
		}
		return rep;
	}


	public static boolean isIsography (List<Integer> ch1, List<Integer> ch2, int k, int p, int l) {
		boolean rep = true;
		for (int i = 0; i < ch1.size() ; i++)
			if (! ((ch2.get(i) ==  MT.mod ((k *  ch1.get(i)) + (l/2) + p, 12)))) {
				rep=false;
				break;
			}
		return rep;
	}

	//Return the complete isographies between two ordered sets of pitch classes
	//NUll is it does not exist
	public static CompleteIsography getCompleteIso (List<Integer> ch1, List<Integer> ch2) {
		int [] listK = new int[] { 1,5,7,11 };
		int[] listL = new int[] { 0,2,4,6,8,10 };
		int[] listP = new int[] { 0,6 };

		CompleteIsography rep = null;
		outerloop:
		for (int k = 0; k < listK.length ; k++)
			for (int l = 0; l < listL.length ; l++)
				for (int p = 0; p < listP.length ; p++){
					if (isIsography(ch1,ch2,listK[k],listP[p],listL[l])) {
						rep = new CompleteIsography(new int [] { listK[k],listL[l],listP[p] });
						break outerloop;
					}
				}
		return rep;
	}

	///////////////////////////////////////////////////////////////////////
	//"For an ordered set of 3 pitch classes, return the possible functors F"


	public List<LocalIsographyCol> getlocalColumn (int index1, int index2) {
		List<List<Integer>> perm1 = permutList.get(index1);
		List<List<Integer>> perm2 = permutList.get(index2);
		List<LocalIsographyCol> rep = new ArrayList<LocalIsographyCol>();
		for (int i = 0; i < perm1.size() ; i++) {
			List<Integer> curperm1 = perm1.get(i);
			for (int j = 0; j < perm2.size() ; j++) {
				List<Integer> curperm2 = perm2.get(j);
				int k = 0;
				for (Functor f1 : getFunctorF (curperm1)) {
					int l = 0;
					for (Functor f2 : getFunctorF (curperm2)) {
						List<Isography> iso = checkIsography(f1,f2);
						if (iso != null) {
							LocalIsographyCol lic = new LocalIsographyCol(new int [] { i, k}, new int [] { j, l}, iso );
							rep.add(lic);
							lic.generatelocals (curperm1,curperm2,f2);
						}
					}
					l++;
					}
					k++;
				}

		}
		return rep;
	}


	public static List<Isography> checkIsography (Functor f1, Functor f2) {
		int [] listK = new int[] { 1,5,7,11 };

		List<Isography> rep = new ArrayList<Isography>();
		for (int k = 0; k < listK.length ; k++){
			for (int l = 0; l < 12 ; l++) {
				boolean flag = true;
				for (int m = 0; m < f1.value.size(); m++) {
					Transition trans1 = f1.value.get(m);
					Transition trans2 = f2.value.get(m);
					if (! (trans1.value[0] == trans2.value[0]))
						flag = false;
					else {
						if (trans1.value[0] == 1 && (! (trans2.value[1] == MT.mod(listK[k] * trans1.value[1], 12))))
							{flag = false;}
						if (trans1.value[0] == 11 && (! (trans2.value[1] == MT.mod( (listK[k]*trans1.value[1]) + l, 12))))
							{flag = false;}
						}
				}
				if (flag)
					rep.add(new Isography(new int [] {listK[k] , l}));
			}
		}
		if (rep.size() == 0)
			return null;
		else
			return rep;
	}



///////////////////////////////////////////////////////////////////////

protected static class  Transition {
	int[] value;
	//type + index

	public Transition (int[] val) {
		value = val;
	}

	public String toString() {
		   if (value[0] == 1)
			   return "T" + value[1];
		   else return "I" + value[1];
	}

	public void Draw (I_Render g, double x, double y) {
		if (value[0] == 1)
			   FX.omDrawString(g, x, y, "T");
		   else FX.omDrawString(g, x, y, "I");
		FX.omSetFont(g, new Font("Courier",14));
		FX.omDrawString(g, x + 10, y + 4, value[1]+"");
		FX.omSetFont(g, new Font("Courier",18));
	}
}

protected static class Functor {
	List<Transition> value;

	public Functor (List<Transition> val) {
		value = val;
	}

	public String toString() {
		   return value.toString();
	}
}

protected static class Isography {
	int[] value;
	//type + index

	public Isography (int[] val) {
		value = val;
	}

	public String toString() {
		   return Arrays.toString(value);
	}
}

protected static class CompleteIsography {
	int[] value = new int[3];
	//type + index + lambda

	public CompleteIsography (int[] val) {
		value = val;
	}

	public String toString() {
		   return Arrays.toString(value);
	}

	public void drawLine (I_Render g,double x0, double y,double x1, double size ) {
		System.out.println("Drawing iso " + x0 + " " + x1);
		FX.omDrawArrow(g, x0, y, x1, y,8);
		FX.omDrawString(g, x0, y - size/2, "v(x)= x - 8"+ (char) 363);
	}

	public void drawCurve (I_Render g,double x0, double y,double x1, double size ) {
		System.out.println("Drawing iso " + x0 + " " + x1);
		FX.omDrawArrow(g, x0, y, x1, y,8);
		FX.omDrawString(g, x0, y - size/2, "v(x)= x - 8"+ (char) 363);
	}

}

protected static class LocalIsography {
	List<Transition> value;

	public LocalIsography (List<Transition> val) {
		value = val;
	}
}

protected static class LocalTransformation {
	CompleteIsography isoc;
	List<Transition> LocalIsography;

	public LocalTransformation (CompleteIsography theisoc, List<Transition> trans) {
		isoc = theisoc;
		LocalIsography = trans;
	}

}

protected static class LocalIsographyCol {
	int[] source;
	int[] target;
	List<Isography> value;
	List<LocalTransformation> localtrans;

	public LocalIsographyCol (int[] thesource, int[] thetarget, List<Isography> thevalue) {
		value = thevalue;
		source = thesource;
		target = thetarget;
		}

	public boolean existeiso (int k, int l, int m) {
		return false;
		}

	public boolean xx (int k, int l, int m) {
		return false;
		}

	public void generatelocals (List<Integer> curperm1, List<Integer> curperm2, Functor f2) {
		localtrans =  new ArrayList<LocalTransformation> ();
		for (Isography iso : value){
			int k = iso.value[0];
			int l = iso.value[1];
			if ((l%2)==0) {
				if (! existeiso (k,l,0)) {
					CompleteIsography isocomp = new CompleteIsography (new int[] {k,l,0});
					List<Transition> trans=null;
					localtrans.add( new LocalTransformation (isocomp, trans) );
				}

			} else {

			}
		}
	}


	public String toString() {
		  return "s:" + Arrays.toString(source) + " t:" + Arrays.toString(target) + " " + value;
	}
	}



protected static class CompleteIsographyCol {
	CompleteIsography iso;
	int per_source;
	int per_target;

	public CompleteIsographyCol (int source, int target, CompleteIsography theiso) {
		iso = theiso;
		per_source = source;
		per_target = target;
		}

	public String toString() {
		   return "s:" + per_source + " t:" + per_target + " " + iso;
	}
	}

////////////////////////////////////////////////

public static CompleteIsography lookForCompInCol(List<CompleteIsographyCol> list, int source, int target){
	CompleteIsography rep = null;
	for (CompleteIsographyCol col : list) {
		if (col.per_source == source && col.per_target == target) {
			rep = col.iso;
			break;
	}
	}
	return rep;
	}

public static List<Isography> lookForLocalInCol(List<LocalIsographyCol> list, int persource, int funcsource,
		int pertarget, int functarget){
	List<Isography> rep = null;
	for (LocalIsographyCol col : list) {
		if (col.source[0] == persource && col.source[1] == funcsource &&
				col.target[0] == funcsource && col.target[1] == functarget) {
			rep=col.value;
			break;
	}
	}
	return rep;

}

public  void DrawContentsInRect (I_Render g, double left, double right, double top, double bottom, double deltay) {
	int length = posList.size() ;
	for (int i = 0; i < length - 1 ; i++) {
		int j = i + 1;
		double posi = posList.get(i);
		double posj = posList.get(j);
		List<Integer> chordi = permutList.get(i).get(curPerList.get(i));
		List<Integer> chordj = permutList.get(j).get(curPerList.get(j));
		double deltatriangle = posj - posi;
		double x0 = posi;
		double y0 = deltay;
		double x1 = x0 + deltatriangle;
		double y1 = y0;
		double x2 = x0+ deltatriangle; //on va les definir si necessaire
		double y2 = y0 + (size*12); //on va les definir si necessaire
		Functor func1 = getFunctorF(chordi).get(curFunList.get(i));
		Functor func2 = getFunctorF(chordj).get(curFunList.get(j));

		if (i == 0)
			drawTriangle(g,x0,y0, chordi,func1,i);
		drawTriangle(g,x1,y1, chordj,func2,j);
		List<CompleteIsographyCol> isoCompleteCol = completeList.get(i);
		CompleteIsography compIso = lookForCompInCol(isoCompleteCol, curPerList.get(i),curPerList.get(j));
		System.out.println("chordi " + chordi + "chordj " + chordj + "funci " + func1 +"funcj " + func2 );
		System.out.println("complete " + compIso);
		compIso = null; //provisoir a effacer
		if (compIso != null)
			compIso.drawLine (g, x0+ size*5.2, y0 + size*4, x0 + size*5.2 + deltatriangle*0.55, size );
		else {
			List<LocalIsographyCol> isolocalCol = localList.get(i);
			List<Isography> localIsos = lookForLocalInCol(isolocalCol, curPerList.get(i),curPerList.get(j),curFunList.get(i),curFunList.get(j));

			System.out.println("local " + localIsos);
		}

	}
}

public  void drawTriangle (I_Render g, double x0, double y0, List<Integer> chord, Functor func, int i) {
	double ysize = size * 7;
	double deltax = size * 2;
	double y = y0 + size/2;
	double x = x0 + size*2;
	FX.omSetColorFill(g, Color.BLACK);
	FX.omSetFont(g, new Font("Courier",18));
	AnchorRect p1 = new AnchorRect (x,y, size,size,MT.num2name(chord.get(0)));
	AnchorRect p2 = new AnchorRect (x+deltax, y+ysize/2, size, size, MT.num2name(chord.get(1)));
	AnchorRect p3 = new AnchorRect (x,y+ysize, size, size, MT.num2name(chord.get(2)));
	FX.omSetColorFill(g, Color.ROYALBLUE);
	p1.Draw(g); p2.Draw(g); p3.Draw(g);

	Point2D p15 = p1.getBorderPoint(5);
	Point2D p14 = p1.getBorderPoint(4);
	Point2D p25 = p2.getBorderPoint(5);
	Point2D p21 = p2.getBorderPoint(1);
	Point2D p31 = p3.getBorderPoint(1);
	Point2D p32 = p3.getBorderPoint(2);


	FX.omDrawArrow(g, p15.getX(), p15.getY(), p31.getX(), p31.getY(),8);
	FX.omDrawArrow(g, p14.getX(), p14.getY(), p21.getX(), p21.getY(),8);
	FX.omDrawArrow(g, p25.getX(), p25.getY(), p32.getX(), p32.getY(),8);

	Point2D p16 = p1.getBorderPoint(6);
	Point2D p36 = p3.getBorderPoint(6);

	FX.omSetColorFill(g, Color.DARKVIOLET);

	FX.omDrawString(g, x + size*0.15, y+ size*1.8, chord.get(0)+"");
	FX.omDrawString(g, x + deltax - size, y+ysize/2+ size*0.3, chord.get(1)+"");
	FX.omDrawString(g, x + size*0.15, y+ysize - 1.5*size, chord.get(2)+"");

	FX.omSetColorFill(g, Color.FIREBRICK);

	func.value.get(0).Draw(g,x+ deltax/1.5, y+ ysize/4);
	func.value.get(1).Draw(g,x+ (deltax+size)/1.8, y+ (ysize*3)/4);
	func.value.get(2).Draw(g,x- size, y + ysize/1.7);

}

/*
 *
    (when selected
      (om-with-fg-color self oa::*om-select-color-alpha*
        (om-fill-polygon (list (om-make-point x y) (om-make-point (+ x deltax)   (+ y (round ysize 2))) (om-make-point x   (+ y ysize))))
        ))
    (when i
      (if (< i 0)
          (setf (nth (abs i) (triangles1 gobj)) (list p1 p2 p3))
        (setf (nth i (triangles gobj)) (list p1 p2 p3))))))
 *
 *
 *

          (let* (
                 (isographies (nth i (isographies-list grap-obj)))
                 )

            (if isographies
                (let* ((isoc (nth i (complet-iso-list grap-obj)))
                       (alltransform (nth i (transformations-list grap-obj)))
                       (index (nth i (cur-transformation-list grap-obj)))
                       (transformation (nth index alltransform)))
                  (print (list "complete" isoc))
                  (if (and (= index 0) isoc)
                      (draw-comp-isography view (+ (* 6 size) x0) (+ (* 5 size) y0) (+ deltatriangles x0) (nth i (complet-iso-list grap-obj)) size lfont)
                    (let* ((iso0 (third transformation))
                           (isoc1 (car transformation))
                           (iso1 (list (car isoc1) (second isoc1)))
                           (trans (nth (nth  i (n1orn2-list grap-obj))  (second transformation)))
                           (func (applyIsoToFunc iso1 func1))
                           (newiso (calculate-second-iso iso0 iso1)))
                      (draw-triangle x2 y2 (apply_isoc chi isoc1) func size lfont view grap-obj (selected-i? grap-obj (* -1 j)) (* -1 j))
                      (draw-locals x2 y2 x1 y1 size view trans lfont newiso)
                      (draw-angled-complete x0 y0 x2 y2  size view isoc1 lfont)
                      (draw-original-isography view (+ (* 6 size) x0) (+ (* 5 size) y0) (+ deltatriangles x0) iso0 size lfont)

                      )))
              (draw-rien-du-tout view (+ (* 6 size) x0) (+ (* 5 size) y0) size))))))
*/

public  void drawPreview (I_Render g, FXCanvas canvas, double x, double x1, double y, double y1, I_EditorParams params) {
	DrawContentsInRect (g, x,x1,y,y1, 0);
}


//////////////////////EDITOR//////////////////////
public static class IsoExpEditor extends EditorView {

	public  IsoExpEditor () {
		super ();
	}

    @Override
	public String getPanelClass (){
		return "projects.mathtools.classes.IsographyExplorer$IsoExpPanel";
	}

}

//////////////////////PANEL//////////////////////
public static class IsoExpPanel extends PanelCanvas {

	@Override
	public void omUpdateView(boolean changedObject_p) {
		super.omUpdateView(changedObject_p);
		GCRender gc = getGCRender();
		omViewDrawContents(gc);
	}

	public void KeyHandler(String car){
		 switch (car) {
		 case "h" : takeSnapShot ();
		 			break;
		 case "c": System.out.println ("(note-chan-color self))"); break;
		 case "b" : System.out.println ("(set-name-to-mus-obj self))"); break;
		 case "tab" : System.out.println ("show-help-window"); break;
		 case "p" : System.out.println ("score-set-tonalite"); break;
		 case "r" : System.out.println ("score-remove-tonalite"); break;
		 case "i" : System.out.println ("change-obj-mode "); break;
		 case "m" :  System.out.println ("change-obj-mode "); break;
	//	 otherwise "down" : beep; break;

	 }
	}

	public void omViewDrawContents (I_Render g) {
		super.omViewDrawContents(g);
		IsographyExplorer obj = (IsographyExplorer) getEditor().getObject();
		obj.DrawContentsInRect(g, 0.0, w(), 0.0, h(), 0);
	}
}

///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////

}


