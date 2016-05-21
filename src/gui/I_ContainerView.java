package gui;

import java.util.List;

public interface I_ContainerView extends I_SimpleView {
	
	public void omAddSubview (I_SimpleView child) ;
	public void omAddSubviews (I_SimpleView... child) ;
	public void omRemoveSubview (I_SimpleView child) ;
	public void omRemoveSubviews (I_SimpleView... child) ;
	public List<I_SimpleView> omSubviews ();
	public List<I_SimpleView> getActives ();
	}

