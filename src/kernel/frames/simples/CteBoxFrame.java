package kernel.frames.simples;

public class CteBoxFrame {

}
/*
;-------------------------------------------------
(omg-defclass boxTypeFrame (nonbuttonboxframe simpleboxframe) ()
   (:documentation "Simple frame for OMBoxTypeCall boxes. #enddoc#
#seealso# (OMBoxTypeCall) #seealso#"))

(defmethod draw-before-box ((self boxTypeFrame)) nil)

(defmethod show-big-doc ((self boxTypeFrame)) nil)

(defmethod make-drag-region ((self boxTypeFrame) region x0 y0 view)
   (declare (ignore view))
   (let* ((icon (iconView self))
          (x (- (x self) x0))
          (y (- (y self) y0)))
     (om-set-rect-region region (+ x (x icon)) (+ y (y icon)) (+ x (x+w icon)) (+ y (y+h icon))))
   region)

;   Change only x-size.
(defmethod allow-new-size ((self boxTypeFrame) new-pos)
   (when (and (> (om-point-h new-pos) 10) (> (om-point-v new-pos) 0))
     (om-make-point (om-point-h new-pos) (max 20 (om-point-v new-pos)))))

(defmethod centre-icon ((self boxTypeFrame))
  (om-set-view-size (iconview self) (om-make-point (- (w self) 2) (- (h self) 11))))

(defmethod draw-after-box ((self boxtypeframe))
   (let ((deltay (if (zerop (numouts (object self))) 1 9)))
    (om-with-focused-view self
      (om-draw-rect 0 0 (- (w self) 1) (- (h self) 1 deltay) :pensize 1)
      )
    t))



(defmethod reinit-size ((box boxtypeframe))
   "Set the size of self to the initial size."
   (let ((goodsize (good-text-box-size (om-dialog-item-text (iconview box)) (om-get-font (iconview box)))))
     (setf (frame-size (object box)) goodsize)
     (box-draw-connections box nil)
     (omG-select (redraw-frame box))   
     ;(setf (frame-size (object box)) (om-view-size box))
     ))

;;; test : pour eviter d'ouvrir un editeur quand on clique juste au bord d'un undefined par ex.
;;; new : rajoute callnextmethod
(defmethod om-view-doubleclick-handler ((self boxTypeFrame) where)
   (declare (ignore where))
   (call-next-method))*/