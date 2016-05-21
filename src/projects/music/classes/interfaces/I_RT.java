package projects.music.classes.interfaces;

import java.util.List;

import projects.music.classes.abstracts.RTree;
import kernel.tools.Fraction;

public interface I_RT {
	public double getTempo ();
	public void setTempo(double tempo);
	
	public Fraction getRTdur ();
	public void setRTdur(Fraction rtdur);
	
	public Fraction getQDurs ();
	public void setQDurs(Fraction rtdur);
	
	public  List<RTree> getRTproplist ();
	public void setRTproplist(List<RTree> proplist);
	
	public Fraction getQoffset();
	public void setQoffset(Fraction offset);
}
