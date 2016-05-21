package kernel.frames.simples;

public class InstanceBoxFrame {

}

/*

(omg-defclass instBoxframe (boxframe) ()
   (:documentation "Simple frame for OMBoxinstance boxes. #enddoc#
#seealso# (OMBoxinstance) #seealso#"))


(defmethod add-lock-button ((self instBoxframe) &optional icon )
   "Not lock button for OMBoxinstance."
   (declare (ignore icon)))

(defmethod initialize-instance  ((self instboxframe) &rest initargs)
  (call-next-method))
;;  (om-set-part-color (nameview self) :body *instboxframe-color*))

(defmethod draw-before-box ((self instBoxframe))
   (call-next-method)
   (om-draw-picture self (if (mypathname (reference (object self)))
                                                    *glass-pict-per* *glass-pict*)
                                    ;(om-make-point (- (x (iconview self)) 5) (y (iconview self))) 
                                    ;(om-make-point (+ (w (iconview self)) 5) (- (h self) 19))
                                    (om-make-point 0 0) 
                                    (om-subtract-points (om-view-size self) (om-make-point 0 11))
                                   )
   ;(om-draw-view-outline (nameview self))
   ;(om-with-fg-color self *instboxframe-color*
     ;(om-fill-rect (x (nameview self)) 0 (w (nameview self)) (- (h self) 18))
    ; (om-fill-rect 0 0 (w self) (- (h self) 9))
    ; )
   ;(unless (selected-p (nameview self))
   ;  (om-set-part-color (nameview self) :body *instboxframe-color*))
)

(defmethod draw-after-box ((self instBoxframe)) nil)
;  (om-with-fg-color self *om-dark-gray-color*
;     (om-draw-rect-outline 0 0 (- (w self) 1) (- (h self) 9) 
;                           (if (selected-p (iconview self)) 2 1))))

(defmethod change-name-box ((self instBoxframe))
   "If 'self is a global variable you can not change its name."
   (if (mypathname (reference (object  self)))
       (om-beep-msg "This is a persistant global variable. It can only be renamed from the Globals folder")
     (call-next-method)))

(defmethod omG-rename ((self instBoxframe) new-name)
    (call-next-method)
    (setf (name (reference (object self))) new-name)
    (setf (name (object self)) new-name)
    new-name)
  */