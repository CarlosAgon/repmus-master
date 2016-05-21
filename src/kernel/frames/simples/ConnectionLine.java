package kernel.frames.simples;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

public class ConnectionLine extends Group {
	final CubicCurve curve = createStartingCurve(0,0,0,0);
	
	
	public ConnectionLine (InputBoxFrame in, OutputBoxFrame out) {
		Line controlLine1 = new BoundLine(curve.controlX1Property(), curve.controlY1Property(), curve.startXProperty(), curve.startYProperty());
	    Line controlLine2 = new BoundLine(curve.controlX2Property(), curve.controlY2Property(), curve.endXProperty(), curve.endYProperty());

	    in.connectionBind(curve.startXProperty(), curve.startYProperty());
	    out.connectionBind(curve.endXProperty(), curve.endYProperty());
		Anchor control1 = new Anchor(Color.GOLD, curve.controlX1Property(), curve.controlY1Property());
		Anchor control2 = new Anchor(Color.GOLDENROD, curve.controlX2Property(), curve.controlY2Property());
		this.getChildren().addAll(controlLine1, controlLine2, curve, control1, control2);
       
	}
    private CubicCurve createStartingCurve(double sx, double ex, double sy, double ey ) {
        CubicCurve curve = new CubicCurve();
        curve.setStartX(sx);
        curve.setStartY(ex);
        curve.setControlX1(150);
        curve.setControlY1(300);
        curve.setControlX2(250);
        curve.setControlY2(50);
        curve.setEndX(sy);
        curve.setEndY(ey);
        curve.setStroke(Color.FORESTGREEN);
        curve.setStrokeLineCap(StrokeLineCap.ROUND);
        curve.setFill(null);
        return curve;
    }

    class BoundLine extends Line {
        BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
            startXProperty().bind(startX);
            startYProperty().bind(startY);
            endXProperty().bind(endX);
            endYProperty().bind(endY);
            setStrokeWidth(2);
            setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.5));
            setStrokeLineCap(StrokeLineCap.BUTT);
            getStrokeDashArray().setAll(10.0, 5.0);
        }
    }

    // a draggable anchor displayed around a point.
    class Anchor extends Circle {
        Anchor(Color color, DoubleProperty x, DoubleProperty y) {
            super(x.get(), y.get(), 5);
            setFill(color.deriveColor(1, 1, 1, 0.5));
            setStroke(color);
            setStrokeWidth(2);
            setStrokeType(StrokeType.OUTSIDE);

            x.bind(centerXProperty());
            y.bind(centerYProperty());
            enableDrag();
        }

        // make a node movable by dragging it around with the mouse.
        private void enableDrag() {
            final Delta dragDelta = new Delta();
            setOnMousePressed(mouseEvent->{
            	dragDelta.x = getCenterX() - mouseEvent.getX();
                dragDelta.y = getCenterY() - mouseEvent.getY();
                getScene().setCursor(Cursor.MOVE);
              });
            setOnMouseReleased(mouseEvent->{
            	getScene().setCursor(Cursor.HAND); 
            });
            setOnMouseDragged(mouseEvent-> {
            	double newX = mouseEvent.getX() + dragDelta.x;
                if (newX > 0 && newX < getScene().getWidth()) {
                        setCenterX(newX);
                }
                double newY = mouseEvent.getY() + dragDelta.y;
                    if (newY > 0 && newY < getScene().getHeight()) {
                        setCenterY(newY);
                    }
            });
            setOnMouseEntered(mouseEvent-> {
            	if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.HAND);
                    }
            });
            setOnMouseExited(mouseEvent-> {
            	if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.DEFAULT);
                    }
            });
        }

        // records relative x and y co-ordinates.
        private class Delta {
            double x, y;
        }
    }

    }