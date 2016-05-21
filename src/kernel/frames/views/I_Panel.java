package kernel.frames.views;

import gui.I_SimpleView;

public interface I_Panel extends I_SimpleView {
	public void init();
	public void KeyHandler(String car);
	public void updatePanel (boolean changed);

}
