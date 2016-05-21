package gui.dialogitems;

import gui.I_SimpleView;

public interface I_Widget  extends I_SimpleView {
	 public void omSetText (String str);
	 public String omGetText ();
	 public void omSetEnabled(boolean bool);
	 public boolean omGetEnabled();
	 public void callAction();

}
