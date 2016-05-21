package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Region;
import javafx.scene.layout.Pane;

public class FXContRegion extends FXRegion implements I_ContainerView {
	
	public FXContRegion (Pane pane, boolean h, boolean v) {
		super(pane, h , v);
	}

	List<I_SimpleView> subviews = new ArrayList<I_SimpleView> ();
	
	public FXContRegion(Region theNode) {
		super(theNode);
	}

	@Override
	public void omAddSubview(I_SimpleView child) {
		if (child.omGetScroller() != null)
			((Pane) delegate).getChildren().add(child.omGetScroller());
		((Pane) delegate).getChildren().add(child.omGetDelegate());
		subviews.add(child);
		child.omSetParent (this);
	}

	@Override
	public void omAddSubviews(I_SimpleView... child) {
		for (int i = 0; i < child.length ; i++){
			omAddSubview(child[i]);
		}
	}

	@Override
	public void omRemoveSubview(I_SimpleView child) {
		if (child.omGetScroller() != null)
			((Pane) delegate).getChildren().remove(child.omGetScroller());
		((Pane) delegate).getChildren().remove(child.omGetDelegate());
		
		subviews.remove(child);
	}

	@Override
	public void omRemoveSubviews(I_SimpleView... child) {
		for (int i = 0; i < child.length ; i++){
			omRemoveSubview(child[i]);
		}
	}

	@Override
	public List<I_SimpleView> omSubviews() {
		return subviews;
	}
	
	@Override
	public List<I_SimpleView> getActives() {
		return new ArrayList<I_SimpleView>();
	}
	

//////////////////////////////
	

	
	public I_SimpleView getPaneWithPoint (double x, double y) {
		for (I_SimpleView subview : omSubviews ()) {
			I_SimpleView rep = subview.getPaneWithPoint(x-subview.x(),y-subview.y());
			if (rep != null)
				return rep;
		}
		if (x >= 0 && x <= w() && y >= 0 && y <= h())
			return this;
		else
			return null;
	}


}
