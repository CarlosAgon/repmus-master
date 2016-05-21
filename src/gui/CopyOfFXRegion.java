package gui;

import java.util.List;

import draganddrop.DragAndDropHandler;
import gui.mouvables.I_MovableObj;
import gui.renders.I_Render;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class CopyOfFXRegion implements I_SimpleView{

	public Region delegate;
	public I_ContainerView parent = null;
	Font font;
	I_MovableObj mouvable = null;
	ScrollPane scroller = null;
	
	public CopyOfFXRegion (Region theNode) {
		
		delegate = theNode;
		
		delegate.setOnMousePressed(e->{mousePressed ( e);});
		delegate.setOnMouseReleased(e->{mouseReleased ( e);});
		delegate.setOnMouseDragged(e->{mouseDragged ( e);});
		
		delegate.setOnMouseEntered(e->{mouseEntered(e);});
		delegate.setOnMouseExited(e->{mouseExited(e);});
		delegate.setOnMouseMoved(e->{mouseMoved(e);});
		
		delegate.setOnKeyPressed(e->{keyPressed(e);});
		delegate.setOnKeyReleased(e->{keyReleased(e);});
		delegate.setOnKeyTyped(e->{keyTyped(e);});
		
		delegate.setOnContextMenuRequested(e->{contextMenuRequested (e);});
		
		delegate.setOnDragDetected(e->{dragDetected(e);});
		delegate.setOnDragDone(e->{dragDone(e);});
		delegate.setOnDragDropped((DragEvent e)->{dragDropped(e);});
		delegate.setOnDragEntered(e->{dragEntered(e);});
		delegate.setOnDragExited(e->{dragExited(e);});
		delegate.setOnDragOver((DragEvent e)->{dragOver(e);});
		
		delegate.setOnMouseDragEntered(e->{mouseDragEntered(e);});
		delegate.setOnMouseDragExited(e->{mouseDragExited(e);});
		delegate.setOnMouseDragOver(e -> {mouseDragOver(e);});
		delegate.setOnMouseDragReleased(e->{mouseDragReleased(e);});
	}
	
	public CopyOfFXRegion (Region theNode, boolean h, boolean v) {
		this(theNode);
		if (h || v){
			scroller = new ScrollPane();
			if (!h) {
				scroller.setHbarPolicy(ScrollBarPolicy.NEVER);
			}
			if (!v) {
				scroller.setVbarPolicy(ScrollBarPolicy.NEVER);
			}
			scroller.setContent(this.delegate);
		}
	}
	
	@Override
	public ScrollPane omGetScroller (){
		return scroller;
	}
	
	@Override
	public void omSetParent(I_ContainerView pere) {
		parent = pere;
	}
	
	@Override
	public Node omGetDelegate() {
		return delegate;
	}

	
	@Override
	public Color omGetBackground() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void omSetBackground(Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Font omGetFont() {
		return font;
	}

	@Override
	public void omSetFont(Font thefont) {
		font = thefont;
	}

	@Override
	public Cursor omGetCursor() {
		return delegate.getCursor();
	}

	@Override
	public void omSetCursor(Cursor cursor) {
		delegate.setCursor(cursor);
		
	}

	@Override
	public Point2D omViewSize() {
		return new Point2D (delegate.getPrefWidth(), delegate.getPrefHeight());
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
		return delegate.getPrefHeight();
	}

	@Override
	public double w() {
		return delegate.getPrefWidth();
	}

	@Override
	public double x() {
		return delegate.getLayoutX();
	}

	@Override//
	public double y() {
		return  delegate.getLayoutY();
	}


	@Override
	public I_ContainerView omViewContainer() {
		return parent;
	}

	@Override
	public void omUpdateView(boolean changedObject_p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void omViewDrawContents(I_Render r) {
		// TODO Auto-generated method stub
		
	}
	
/////////////////////////////////////////////////////////////////////
//Events
/////////////////////////////////////////////////////////////////////

///////////////MOUSE
public void omMousePressed (double x, double y) {
}

public void omDoubleClick (double x, double y) {
}

public void mousePressed (MouseEvent e) {
	delegate.requestFocus();
	if (e.getClickCount()==1) 
		omMousePressed(e.getX(), e.getY());
	if(e.getClickCount() == 2)
		omDoubleClick(e.getX(), e.getY());
	e.consume();
}

public void omMouseReleased (double x, double y) {

}

public void mouseReleased (MouseEvent e) {
omMouseReleased(e.getX(), e.getY());
e.consume();
}

public void omMouseDragged (double x, double y) {
}

public I_MovableObj getMouvable() {
	return mouvable;
}

public void addMouvable(I_MovableObj mov) {
	 mouvable = mov;
	 ((Pane) delegate).getChildren().add(mov.getShape());
}

public void removeMouvable() {
	((Pane) delegate).getChildren().remove(mouvable.getShape());
	mouvable = null;
}
////

public void mouseDragged (MouseEvent e) {
	omMouseDragged(e.getX(), e.getY());
	e.consume();
}

public void omMouseEntered (double x, double y) {
}

public void mouseEntered (MouseEvent e) {
omMouseEntered(e.getX(), e.getY());
}

public void omMouseExited (double x, double y) {
}
public void mouseExited (MouseEvent e) {
omMouseExited (e.getX(), e.getY());
}

public void omMouseMoved (double x, double y) {}
public void mouseMoved (MouseEvent e) {
omMouseMoved (e.getX(), e.getY());
e.consume();
}

///////////////KEY
public void KeyHandler (String car) {
}

public void keyTyped(KeyEvent e) {
	KeyHandler(e.getCharacter());
}

public void keyReleased(KeyEvent e) {
	KeyCode keyCode = e.getCode();
	if (keyCode == KeyCode.COMMAND) FX.setCommandKey (false);
	else if (keyCode == KeyCode.SHIFT) FX.setShiftKey(false);
	else if (keyCode == KeyCode.CONTROL) FX.setControlKey(false);
	else if (keyCode == KeyCode.ALT) FX.setAltKey (false);
}

public void keyPressed (KeyEvent e) {
	KeyCode keyCode = e.getCode();
	String str ="";
	if (keyCode == KeyCode.ESCAPE) str = "esc";
	else if (keyCode == KeyCode.DELETE)	 str = "del";
	else if (keyCode == KeyCode.TAB)	 str = "tab";
	else if (keyCode == KeyCode.UP)	 str = "up";
	else if (keyCode == KeyCode.LEFT)	 str = "left";
	else if (keyCode == KeyCode.DOWN)	 str = "down";
	else if (keyCode == KeyCode.RIGHT)	 str = "right";
	else if (keyCode == KeyCode.ENTER)	 str = "enter";
	else if (keyCode == KeyCode.SPACE)	 str = "espace";
	else if (keyCode == KeyCode.COMMAND) FX.setCommandKey (true);
	else if (keyCode == KeyCode.SHIFT) FX.setShiftKey(true);
	else if (keyCode == KeyCode.CONTROL) FX.setControlKey(true);
	else if (keyCode == KeyCode.ALT) FX.setAltKey (true);
	else str = "";
	KeyHandler (str);
}


///////////////MENU CONTEXT

public void contextMenuRequested (ContextMenuEvent e){
List<MenuItem> menus = getTheContextMenu();
if (menus != null) {
ContextMenu themenu = new ContextMenu();
themenu.getItems().addAll(menus);
themenu.show(delegate, e.getScreenX(), e.getScreenY());
}
e.consume();
}

public List<MenuItem> getTheContextMenu (){
return null;
}


///////////////DRAG AND DROP
public boolean draggable (){
	return false;
}

public I_SimpleView getDragged (){
	return this;
}

public Image dragImage () {
	Rectangle r = new Rectangle(0, 0 , w(), h());
	r.setFill(FX.transp(Color.BLUE, 0.08));
    return r.snapshot(null, null) ;
	};


public void omStartDragDrop (double x, double y) {
	DragAndDropHandler DD = FX.dragHandler;
	DD.dragged_view = getDragged();
	DD.container_view = DD.dragged_view.omViewContainer();
	DD.initial_mouse_pos = new Point2D(x,y);
	DD.dragged_list_objs = DD.container_view.getActives();
	DD.true_dragged_view = this;
	Dragboard db = delegate.startDragAndDrop(TransferMode.MOVE);
	db.setDragView(dragImage (), 0,0);
	ClipboardContent content = new ClipboardContent();
	FX.draggingListObj.add(this);
	content.putString("hola");
	db.setContent(content);}

public void dragDetected(MouseEvent e) {
if (draggable())
	omStartDragDrop(e.getX(), e.getY()); 
e.consume(); 
};

public void dragDone (DragEvent e) {
e.consume();
}

public void dragDropped (DragEvent e) {
final Dragboard dragboard = e.getDragboard();
       if (dragboard.hasString() && "SHEET_KEY".equals(dragboard.getString())
    		   && FX.draggingListObj.size() > 0)
{
for (I_SimpleView item : FX.draggingListObj ) {
	item.omSetViewPosition(e.getX(), e.getY());
	e.setDropCompleted(true);		   
}
FX.draggingListObj.clear();
e.consume();
}
}

public void dragEntered (DragEvent e) {
}

public void dragExited (DragEvent e) {
}

public void dragOver(DragEvent e) {
final Dragboard dragboard = e.getDragboard();
 if (dragboard.hasString() && "SHEET_KEY".equals(dragboard.getString())
&& FX.draggingListObj.size() > 0)
{
e.acceptTransferModes(TransferMode.MOVE);
e.consume();
}
}

public void mouseDragEntered (MouseDragEvent e) {
}

public void mouseDragExited (MouseDragEvent e) {
}

public void mouseDragOver (MouseDragEvent e) {
}

public void mouseDragReleased (MouseDragEvent e) {
}


//////////////////////////////
public I_SimpleView getPaneWithPoint (double x, double y) {
	if (x >= 0 && x <= w() && y >= 0 && y <= h())
		return this;
	else
		return null;
}

public Scene omGetScene ()  {
return   delegate.getScene();
}

}
