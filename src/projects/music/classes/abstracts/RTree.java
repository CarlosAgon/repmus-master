package projects.music.classes.abstracts;


import java.util.ArrayList;
import java.util.List;

import gui.FX;
import gui.FXCanvas;
import gui.renders.I_Render;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import kernel.I_OMObject;
import kernel.annotations.Ombuilder;
import kernel.annotations.Omclass;
import kernel.frames.views.EditorView;
import kernel.frames.views.I_EditorParams;
import kernel.frames.views.PanelView;
import kernel.tools.Fraction;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

@Omclass(icon = "137", editorClass = "projects.music.classes.abstracts.RTree$RTEditor")
public class RTree implements I_OMObject {
	public Fraction dur = null;
	public List<RTree> proplist = null;
	public long prop;
	public boolean cont_p = false;

	////////////////// Constructors	//////////////////
	public  RTree (long theprop, boolean cont) {
		prop = theprop;
		cont_p = cont;
	}

	@Ombuilder
	public  RTree (Fraction thedur,  List<RTree> theprops) {
		dur = thedur;
		proplist = theprops;
		setDurChilds ();
	}


	public  RTree (long theprop,  List<RTree> theprops) {
		prop = theprop;
		proplist = theprops;
	}

	public RTree() {
	    this (new Fraction(4,4),defaultProps());
	}

	public static List<RTree> defaultProps() {
		List<RTree> rep = new ArrayList<RTree>();
		rep.add(new RTree(1,null));
		rep.add(new RTree(1,null));
		rep.add(new RTree(1,null));
		List<RTree> rep1 = new ArrayList<RTree>();
		rep1.add(new RTree(1,null));
		rep1.add(new RTree(3,null));
		rep1.add(new RTree(1,null));
		rep1.add(new RTree(1,null));
		rep.add(new RTree(1,rep1));
	    return  rep;
	}


	/////////////////////////////////////////////////

	public List<RTree> getProporsitons() {
		return proplist;
	}

	public Fraction getSignature() {
		return dur;
	}


	public long getNodeSum() {
		long rep = 0;
		for (RTree item : proplist) {
			rep = rep + Math.abs(item.prop);
		}
		return rep;
	}

	public void setDurChilds () {
		if (proplist != null) {
		for (RTree item : proplist) {
			item.dur =
					(new Fraction(item.prop).divide(getNodeSum())).multiply(dur);
			item.setDurChilds();
		}
		}
	}

	//////////////////
	public boolean isRest() {
		return prop < 0;
	}

	public boolean isFigure() {
		return proplist == null;
	}

	public boolean isGrace() {
		return prop == 0;
	}

	public  int countChords () {
		int rep = 0;
		if (cont_p) return 0;
		else if (proplist == null && prop > 0) return 1;
		else if (proplist == null && prop < 0) return 0;
		else if (proplist == null && prop == 0) {
			for (RTree item : proplist) {
				rep = rep + item.countChords();
			}
			return rep;
		}
		else {
			for (RTree item : proplist) {
				rep = rep + item.countChords();
			}
		return rep;
		}
	}

	public String toString ( ){
		String rep = "(";
		rep = rep + dur;
		rep = rep+ " (";
		for (RTree item : proplist) {
			rep = rep + item.toProportions();
		}
		rep = rep + "))";
	    return rep;
	}

	public String toProportions ( ){
		if (proplist == null)
			return " " + prop +" ";
		else {
			String rep = "(";
			rep = rep + prop;
			rep = rep+ " (";
			for (RTree item : proplist) {
				rep = rep + item.toProportions();
			}
			rep = rep + "))";
			return rep;
		}
	}

	public String toStringdur ( ){
		if (proplist == null)
			return " " +dur+" ";
		else {
		String rep = "(";
		rep = rep + dur;
		rep = rep+ "(";
		for (RTree item : proplist) {
			rep = rep + item.toStringdur();
		}
		rep = rep + "))";
	    return rep;
	}
	}

	public  void drawPreview (I_Render g, FXCanvas canvas, double x, double x1, double y, double y1, I_EditorParams params) {
		FX.omDrawString (g, x + ((x1 - x) / 2),  y + ((y1 - y)/ 2), this.toString());
	}

	//////////////////////EDITOR//////////////////////
    public static class RTEditor extends EditorView {

        @Override
    	public String getPanelClass (){
    		return "projects.music.classes.abstracts.RTree$RTPanel";
    	}
    }

    //////////////////////PANEL//////////////////////
    public static class RTPanel extends PanelView {

    	/*public void init () {
    		RTree obj = (RTree) getEditor().getObject();
    		RTreeView tree = new RTreeView(obj);
    		((Pane) this.omGetDelegate()).getChildren().add(tree);
    	}*/


    	/*public void init () {
    		RTree obj = (RTree) getEditor().getObject();
    		Pagination pagination = new Pagination(8, 0);
            pagination.setStyle("-fx-border-color:red;");
            pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));
    		((Pane) this.omGetDelegate()).getChildren().add(pagination);
    	}

    	  public VBox createPage(int pageIndex) {
    	        VBox box = new VBox(5);
    	        int page = pageIndex * 8;
    	        for (int i = page; i < page + 8; i++) {
    	            VBox element = new VBox();
    	            Hyperlink link = new Hyperlink("Item " + (i+1));
    	            link.setVisited(true);
    	            Label text = new Label("Search results\nfor "+ link.getText());
    	            element.getChildren().addAll(link, text);
    	            box.getChildren().add(element);
    	        }
    	        return box;
    	    }*/

    	public void init () {
    		Text text = new Text();
    		text.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
    		TextField textField = new TextField();
    		textField.setText("4/4");
    		text.textProperty().bind(textField.textProperty());
    		Blend blend = new Blend();
    		blend.setMode(BlendMode.MULTIPLY);

    		DropShadow ds = new DropShadow();
    		ds.setColor(Color.rgb(254, 235, 66, 0.3));
    		ds.setOffsetX(5);
    		ds.setOffsetY(5);
    		ds.setRadius(5);
    		ds.setSpread(0.2);

    		blend.setBottomInput(ds);

    		DropShadow ds1 = new DropShadow();
    		ds1.setColor(Color.web("#f13a00"));
    		ds1.setRadius(20);
    		ds1.setSpread(0.2);

    		Blend blend2 = new Blend();
    		blend2.setMode(BlendMode.MULTIPLY);

    		InnerShadow is = new InnerShadow();
    		is.setColor(Color.web("#feeb42"));
    		is.setRadius(9);
    		is.setChoke(0.8);
    		blend2.setBottomInput(is);

    		InnerShadow is1 = new InnerShadow();
    		is1.setColor(Color.web("#f13a00"));
    		is1.setRadius(5);
    		is1.setChoke(0.4);
    		blend2.setTopInput(is1);

    		Blend blend1 = new Blend();
    		blend1.setMode(BlendMode.MULTIPLY);
    		blend1.setBottomInput(ds1);
    		blend1.setTopInput(blend2);

    		blend.setTopInput(blend1);

    		text.setEffect(blend);

    		((Pane) this.omGetDelegate()).getChildren().add(text);
    	}
    }

    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////

    public static class RTreeView  extends TreeView<RTree> {
    private RTree obj;

	public RTreeView (RTree theobj) {
		obj = theobj;
		setRoot(createTree (obj));
		setCellFactory((e) -> new TreeCell<RTree>() {
			@Override
			protected void updateItem(RTree item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null) {
					if (item.prop == 0) //grrr
						setText(item.dur + "");
					else
						setText(item.prop + "");
				} else {
					setText("");
				}
			}
		});
	}

	private TreeItem<RTree> createTree (RTree rt) {
		TreeItem<RTree> item = new TreeItem <>(rt);
		List<RTree> proplist = rt.getProporsitons();
		if (proplist != null) {
			for (RTree child : proplist) {
				item.getChildren().add(createTree(child));
			}
		} else {
			//item.setGraphic(new ImageView (Loader.getIconFromDic("125")));
		}
		return item;
	}
    }
}
