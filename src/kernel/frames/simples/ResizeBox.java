package kernel.frames.simples;

import javafx.scene.Cursor;
import javafx.scene.shape.Rectangle;
import resources.Loader;
import gui.FXPane;
import gui.mouvables.DrawGrow;

public class ResizeBox extends FXPane {
	double minx;
	double miny;
	
	public ResizeBox (double theminx, double theminy) {
		minx = theminx;
		miny = theminy;
		Cursor cur = Loader.getCursorFromDic("resize-cursor");
		omSetCursor(cur);
		//omGetDelegate().setStyle("-fx-border-color: red;");
	}
	
	public boolean draggable (){
		return false;
	}
	
	public BoxFrame getBoxFrame () {
		return (BoxFrame)  omViewContainer(); 
	}
	
	public void omMouseDragged (double x, double y) {
		BoxFrame box = getBoxFrame();
		if (getMouvable () == null)
			addMouvable (new DrawGrow(10 - box.w(),10 - box.h(), box.w(), box.h(), minx, miny));
		else getMouvable ().movableCallback (x,y);
		}
	
	public void omMouseReleased (double x, double y) {
		if (getMouvable () != null){
			BoxFrame box = getBoxFrame();
			Rectangle r = ((DrawGrow) getMouvable ()).getShape();
			box.setNewSize(r.getWidth(), r.getHeight());
			removeMouvable();
			
			}
		}
}
/*

(defmethod om-view-click-handler ((self c-resize-box) where)
   (declare (ignore where))
   (let* ((boxframe (get-box-frame self))
          (theeditor (editor (om-view-container boxframe)))
          (panel (om-view-container (get-box-frame self)))
          (rx (x boxframe)) 
          (ry (y  boxframe)))
     (when (text-view theeditor)
       (exit-from-dialog (text-view theeditor) 
                                       (om-dialog-item-text (text-view theeditor))))
     (setf *init-resize-pos* where)
     (om-new-movable-object panel rx ry (w boxframe) (h boxframe) 'om-movable-rectangle)
     (om-init-motion-functions self 'resize-box-motion 'resize-box-release)
    ))
      

(defmethod resize-box-motion ((self c-resize-box) pos)
  (when *init-resize-pos*
  (let ((panel (om-view-container (get-box-frame self))))
    (when panel
      (let* ((initpoint (om-convert-coordinates pos self panel))
            (initsize (om-add-points (om-add-points (om-view-size (get-box-frame self)) (om-view-position (get-box-frame self)))
                                     (om-subtract-points pos *init-resize-pos*)))
            (rx (om-point-h initsize))
            (ry (om-point-v initsize))
            (rect  (om-init-point-movable-object panel)))
        (om-update-movable-object panel (first rect) (second rect) (max 4  (- rx (first rect))) (max 4 (- ry (second rect) )))
        )))))

(defmethod resize-box-release ((self c-resize-box) pos) 
  (let* ((boxframe (get-box-frame self))
         (panel (om-view-container boxframe)))
    (when (and boxframe panel)
      (let* ((initpoint (om-convert-coordinates pos self panel ))
             (initsize (om-add-points (om-view-size boxframe) (om-view-position boxframe)))
             (initsizepos (om-add-points initsize (om-subtract-points pos *init-resize-pos*)))         
             (rx (om-point-h initsizepos))
             (ry (om-point-v initsizepos))
             (rect  (om-init-point-movable-object panel)))
     (om-erase-movable-object panel)
     (change-boxframe-size boxframe (om-make-point (- rx (first rect) ) (- ry (second rect) )))
     (om-invalidate-rectangle panel (x boxframe) (y boxframe) (w boxframe) (h boxframe)) 
     (setf *init-resize-pos* nil)
     ))))

*/

