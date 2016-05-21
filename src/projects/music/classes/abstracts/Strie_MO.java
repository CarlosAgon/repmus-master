package projects.music.classes.abstracts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Point2D;
import kernel.tools.Fraction;
import projects.music.classes.interfaces.I_RT;

public class Strie_MO extends MusicalObject implements I_RT {
	
	double tempo = 60;
	Fraction qdur = new Fraction (1);
	Fraction qoffset = new Fraction (0);
	
	public Fraction rtdur = new Fraction(1);
	List<RTree> rtproplist = null;
	public RTree tree;
	
	@Override
	public double getTempo() {
		return tempo;
	}
	@Override
	public void setTempo (double thetempo) {
		tempo = thetempo;
		setOffset(n2ms(qoffset, getTempo()));
		setDuration(n2ms(qdur, getTempo()));
	}
	@Override
	public Fraction getRTdur() {
		return rtdur;
	}
	@Override
	public void setRTdur(Fraction thertdur) {
		rtdur = thertdur;
	}
	@Override
	public List<RTree> getRTproplist() {
		return rtproplist;
	}
	@Override
	public void setRTproplist(List<RTree> theproplist) {
		rtproplist = theproplist;
		
	}
	@Override
	public Fraction getQDurs() {
		return qdur;
	}
	@Override
	public void setQDurs(Fraction dur) {
		qdur = dur;
		setDuration(n2ms(qdur, getTempo()));
		
	}
	@Override
	public Fraction getQoffset() {
		return qoffset;
	}
	@Override
	public void setQoffset(Fraction offset) {
		qoffset = offset;	
		setOffset(n2ms(qoffset, getTempo()));
	}
	
	public Fraction getOnsetTotal () {
		if (getFather() == null)
			return qoffset ;
		else
			return Fraction.addition(qoffset , ((Strie_MO) getFather()).getOnsetTotal() );
	}
	
	@Override
	public long getOnsetMS () {
		return (long) n2ms(getOnsetTotal(), tempo);
	}
	
	
	///////////////////////////////////TOOLS////////////////////////////////
	
	//Find the symbolic value from mesuare denominateur
	public static Point2D beforeAndAfteBin (double den) {
		double exp = 0;
		while (den >= Math.pow(2, exp)) {
			exp++;
		}
		return new Point2D ( Math.pow(2, exp-1), Math.pow(2, exp));
	}
	
	
	
	//transforme une duree en noirs en ms par rapport a un tempo donnee 
	public static long n2ms (Fraction n, double tempo) {return (long) Math.round(n.value() * (60000/tempo)); };
	
	//Trouve le valeur symbolic pour un denominateur
	public static long findBeatSymbol (long den) {
		long exp = 0;
		while (den >= Math.pow(2, exp)) {
			exp++;
		}
		return Math.round(Math.pow(2, exp-1));
	}
	
	//pour les notes longues
	//////////////////////////////////////
	
	//trouve la puissance de 2 avant  n 
	public static long beforePower2 (long n) {
		long exp = 0;
		while (n >= Math.pow(2, n)) {
			n++;
		}
		return  Math.round(Math.pow(2, exp-1));
	}
	
	
	//decompose un integer en durees avec figures pour faire la liason
	public static List<Long> decomposeDur (long dur) {
		Long [] simples = new Long [] {(long) 1, (long)2, (long)3, (long)4, (long)6, (long)7, (long)8, (long)12, 
				(long)14, (long)16, (long)24, (long)28, (long)32, (long)48, (long)56, (long)64};
		List<Long> fixes = Arrays.asList(simples);
		List<Long> rep;
		if (fixes.contains(dur)) {
			rep = new ArrayList<Long>();
			rep.add(dur);
		}
		else {
			long inf = beforePower2(dur);
			rep = decomposeDur(dur-inf);
			rep.add(inf);
		}
		return rep;
	}
	
	
	

}
