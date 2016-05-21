package gui.dialogitems;

import javafx.geometry.Point2D;
import javafx.scene.control.Control;
import gui.FXRegion;;

public class FXWidget extends FXRegion implements I_Widget  {
	
	DIFun  mainaction;
	
	public FXWidget (Control widget) {
		super(widget);
	}
	
	@Override
	public void callAction() {
		mainaction.DIAction(this);
	}

	@Override
	public void omSetText(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String omGetText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	 public void omSetEnabled(boolean bool){
		 delegate.setDisable (bool);
	 }
	 
	@Override
	 public boolean omGetEnabled(){
		 return delegate.isDisabled ();
	 }
	
	@Override
	public Point2D omViewSize() {
		return new Point2D (delegate.prefWidth(-1), delegate.prefHeight(-1));
	}

	@Override
	public void omSetViewSize(double w, double h) {
		delegate.setPrefWidth(w);
		delegate.setPrefHeight(h);
	}
	
	@Override
	public void omSetViewSize(Point2D size) {
		delegate.setPrefWidth(size.getX());
		delegate.setPrefHeight(size.getY());
	}

	@Override
	public Point2D omViewPosition() {
		return new Point2D (delegate.getLayoutX(), delegate.getLayoutY());
	}
	
	
	@Override
	public void omSetViewPosition(double x, double y) {
		delegate.relocate (x,y);
		
	}
	
	public void omSetViewPosition(Point2D pos) {
		delegate.relocate (pos.getX(),pos.getY());
		
	}

	@Override
	public double h() {
		return delegate.prefHeight(-1);
	}

	@Override
	public double w() {
		return delegate.prefWidth(-1);
	}

	@Override
	public double x() {
		return delegate.getLayoutX();
	}

	@Override//
	public double y() {
		return  delegate.getLayoutY();
	}


	
	
	
}
