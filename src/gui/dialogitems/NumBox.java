package gui.dialogitems;


import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class NumBox extends OmStaticText  {

	int value = 0;
	boolean enable  = true ;
	int minVal = 0;
	int maxVal = 127;
	int lastClick = 0;
	NumBoxFun afterFun;
	NumBoxFun  whileFun;
	
	NumBox (String text, EventHandler<ActionEvent> fun,int font, int x, int y, int w, int h) {
		super(text,  fun, font,x,y,w,h);
	}
	
	public NumBox (String text, int font, int x, int y, int w, int h, NumBoxFun afterf, NumBoxFun whilef) {
		super(text,  (e)->{},font,x,y,w,h);
		afterFun = afterf;
		whileFun = whilef;

	}
/*	
	public NumBox (String text, Point pos, Dimension size, NumBoxFun afterf, NumBoxFun whilef, int theValue, int max, int min) {
		super(text,  pos,  size,  (e)->{});
		afterFun = afterf;
		whileFun = whilef;
		value = theValue;
		maxVal = max;
		minVal = min;
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		addMouseMotionListener(this);

	}
	
	public int getValue () {
		  return value;
	} 
	
	public void setValue (int theValue) {
			  omSetText(String.valueOf(theValue));
			  value = theValue;
			  repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		lastClick = y * -1;
	}

	public int mapIncr() {
		return 1;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		setValue(Math.max(minVal, Math.min(maxVal, mapIncr() *(value + (y * -1) - lastClick))));
		lastClick = y;
		whileFun.numBoxAction(this);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		afterFun.numBoxAction(this);
		repaint();
	}
	*/
}

