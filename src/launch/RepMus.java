package launch;

import kernel.frames.views.EditorMaker;
import kernel.frames.views.EditorView;
import kernel.metaobjects.Patch;
import kernel.tools.Fraction;
import projects.music.classes.abstracts.Debug_MO;
import projects.music.classes.abstracts.Strie_MO;
import projects.music.classes.music.ChordSeq;
import projects.music.classes.music.Measure;
import projects.music.midi.MidiSetUp;
import resources.json.JsonRead;
import gui.WorkSpaceTree;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RepMus extends Application {

	//////////////////////////////////////////////////////
	public class OmTab extends Tab {

		EditorView ed = null;
		OmTabPane pere = null;

		public OmTab (EditorView theed) {
			super();
			ed = theed;
			setOnCloseRequest (e -> {
				ed.EditorClosed();
				} );
			setOnClosed (e -> {

				} );
		}
	}

	//////////////////////////////////////////////////////
	public class OmTabPane extends TabPane {

		public OmTabPane () {
			super();
			setOnKeyPressed(e->{keyPressed(e);});
			setOnKeyReleased(e->{keyReleased(e);});
			setOnKeyTyped(e->{keyTyped(e);});
			requestFocus();
		}

		public void keyTyped(KeyEvent e) {
			OmTab sel = (OmTab) this.selectionModelProperty().getValue().getSelectedItem();
			if (sel.ed != null) sel.ed.keyTyped(e);
		}

		public void keyReleased(KeyEvent e) {
			OmTab sel = (OmTab) this.selectionModelProperty().getValue().getSelectedItem();
			if (sel.ed != null) sel.ed.keyReleased(e);
		}

		public void keyPressed (KeyEvent e) {
			OmTab sel = (OmTab) this.selectionModelProperty().getValue().getSelectedItem();
			if (sel.ed != null) sel.ed.keyPressed(e);
		}
	}

	//////////////////////////////////////////////////////
	public class OmScene extends Scene {

		public SplitPane spgeneral;
		public SplitPane sptop;
		public OmTabPane edWS0;
		public OmTabPane edWS1;
		public OmTabPane edWS2;

		public OmScene(Parent root, double w, double h) {
			super(root, w, h);
			spgeneral = (SplitPane) root;
		}

		public void addEditor (EditorView ed, int where) {
			OmTabPane edWhere;
			if (where == 1) {
				edWhere = edWS1;
			} else {
				edWhere = edWS2;
				if (! sptop.getItems().contains(edWS2)) {
					sptop.getItems().add(edWS2);
				}
			}

			OmTab tab = new OmTab(ed);
			tab.setText("editor circulo");
			tab.setContent(ed.omGetDelegate());
			edWhere.getTabs().add(tab);
			SingleSelectionModel<Tab> selectionModel =  edWhere.getSelectionModel();
			selectionModel.select(tab);
			edWhere.requestFocus();
		}


		public void searchEditor (EditorView ed) {
			OmTab tab = null;
			ObservableList<Tab> tabs = edWS1.getTabs();
			for (Tab item :  tabs) {
				if (((OmTab) item).ed == ed) {
					SingleSelectionModel<Tab> selectionModel =  edWS1.getSelectionModel();
					tab = (OmTab) item;
					selectionModel.select(tab);
					edWS1.requestFocus();
					break;
				}
			}
			tabs = edWS2.getTabs();
			for (Tab item :  tabs) {
				if (((OmTab) item).ed == ed) {
					SingleSelectionModel<Tab> selectionModel =  edWS2.getSelectionModel();
					tab = (OmTab) item;
					selectionModel.select(tab);
					edWS2.requestFocus();
					break;
				}
			}
		}
	}

	//////////////////////////////////////////////////////

	@Override
	public void start(Stage primaryStage){

		Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);

		Patch thepatch = new Patch();
		EditorView patcheditor =  thepatch.omOpenEditor(null);
		WorkSpaceTree workspace =  new WorkSpaceTree ();

		SplitPane spgeneral = new SplitPane();
		spgeneral.setOrientation(Orientation.VERTICAL);

		SplitPane sptop = new SplitPane();
		sptop.setOrientation(Orientation.HORIZONTAL);

		final OmTabPane edWSTabPane0 = new OmTabPane();


		OmTab tab = new OmTab(null);
		tab.setText("workspace");
		tab.setContent(workspace);
		edWSTabPane0.getTabs().add(tab);

		OmTabPane edWSTabPane1 = new OmTabPane();
		OmTabPane edWSTabPane2 = new OmTabPane();

		sptop.getItems().addAll(edWSTabPane0, edWSTabPane1);
		sptop.setDividerPositions(0.2f, 0.8f);

		SplitPane spbotom = new SplitPane();
		spbotom.setOrientation(Orientation.HORIZONTAL);

		final OmTabPane consTabPane = new OmTabPane();
		OmTab tab2 = new OmTab(null);
		tab2.setText("console");
		tab2.setContent(new Button("Button One"));
		consTabPane.getTabs().add(tab2);

		final StackPane inspectPane = new StackPane();

		//EditorMaker edmaker = new EditorMaker ();
		//EditorView editor = edmaker.makeEditorView (new Measure(), null, "projects.music.classes.abstracts.Debug_MO"
		//		, new Point2D(400, 400), new Point2D(0,0), null );
		inspectPane.getChildren().add(new Button("Button One"));

		spbotom.getItems().addAll(consTabPane, inspectPane);
		spbotom.setDividerPositions(0.6f, 0.4f);


		spgeneral.getItems().addAll(sptop, spbotom);
		spgeneral.setDividerPositions(0.8f, 0.2f);

		/////////////////////////////////////////////////
		// second editor
		/////////////////////////////////////////////////
		SplitPane edBOXPane = new SplitPane();
		spgeneral.setOrientation(Orientation.VERTICAL);

		OmScene scene = new OmScene(spgeneral,800,600);
		scene.edWS0 = edWSTabPane0;
		scene.edWS1 = edWSTabPane1;
		scene.edWS2 = edWSTabPane2;
		scene.sptop = sptop;
		scene.addEditor(patcheditor, 1);
		primaryStage.setScene(scene);
		primaryStage.show();
		MidiSetUp ms = new MidiSetUp();
		ms.open();
		System.out.println("decompose 5 " +Strie_MO.decomposeDur(5));
		System.out.println("pour 1 " +Fraction.parse("1"));
		System.out.println("pour 1/2 " +Fraction.parse("1/2"));

		System.out.println("pour 1 / 2 " +Fraction.parse(" 1 / 2 "));

		System.out.println("pour 1 / 2 " +Fraction.parse(" 1 / 2.0 "));

		System.out.println("pour 1 / 2 " +Fraction.parse(" 1 / A "));
		System.out.println("pour 1 / 2 " +Fraction.parse(" 1 / 0 "));

		}

	public static void main(String[] args) {
		launch(args);
	}
	//TOOLTIP HACK
/*	public static void hackTooltipStartTiming(Tooltip tooltip) {
	    try {
	        Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
	        fieldBehavior.setAccessible(true);
	        Object objBehavior = fieldBehavior.get(tooltip);

	        Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
	        fieldTimer.setAccessible(true);
	        Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

	        objTimer.getKeyFrames().clear();
	        objTimer.getKeyFrames().add(new KeyFrame(new Duration(250)));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}*/

}
