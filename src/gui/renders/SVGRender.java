package gui.renders;

import gui.FX;
import gui.FXCanvas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import resources.Loader;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SVGRender implements I_Render{

public  boolean loadfonts = true;
public  BufferedWriter svgoutput = null;

double linewidth = 1;
Color colorstroke = Color.BLACK;
Color colorfill = null;
Font font = FX.default_font1;


public SVGRender (FXCanvas view){
	try {
		File file = new File("example.svg");
		svgoutput = new BufferedWriter(new FileWriter(file));
		svgoutput.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"" + view.w() +"\" height=\"" +
				view.h() +"\">\n");
		svgoutput.write("<title>Experience SVG</title>\n");
		if (loadfonts)
			copyFonts(svgoutput);

	} catch ( IOException e ) {
		e.printStackTrace();
	}
}

public void copyFonts(BufferedWriter out) {
	File file1 = new File("template.svg");
	BufferedReader br;
	try {
		br = new BufferedReader(new FileReader(file1));
	  int i;
	    do {
	    i = br.read();
		if (i != -1) {
	        svgoutput.write((char) i);
	      }
	    } while (i != -1);
	    br.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Override
public void drawImage(Image img, double x, double y, double w, double h) {
	// TODO Auto-generated method stub

}


@Override
public void drawImage(Image img, double x, double y) {
	// TODO Auto-generated method stub

}


@Override
public void drawImage(Image img, double sx, double sy, double sw, double sh,
		double dx, double dy, double dw, double dh) {
	// TODO Auto-generated method stub

}


@Override
public Color omGetColorStroke() {
	return colorstroke;
}


@Override
public void omSetColorStroke(Color color) {
	colorstroke = color;
}


@Override
public Color omGetColorFill() {
	return colorfill;
}


@Override
public void omSetColorFill(Color color) {
	colorfill =color;
}

public static String c2s (Color color) {
	if (color == null)
		return "none";
	return "rgb(" + (int) color.getRed()*255 + "," + (int) color.getGreen()*255 + "," + (int) color.getBlue()*255 + ")";
}


@Override
public void omDrawLine(double x, double y, double x1, double y1) {
	if (svgoutput != null){
     	try {
     		svgoutput.write("<line x1=\"" + x +"\" y1=\"" + y +
					"\" x2=\"" + x1 + "\" y2=\"" + y1 +
					"\" style=\"stroke:" + c2s(colorstroke)  + ";stroke-width:" + linewidth + "\" />\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
}


@Override
public void omDrawString(double x, double y, String str) {
	 if (svgoutput != null){
	     	try {
	     		String my_new_str = str.replaceAll("&", "&amp;");
	     		svgoutput.write("<text x=\"" + x +"\" y=\"" + y +
	     				"\" font-family=\"" + font.getFamily() +
	     				"\" font-size=\"" + (int) font.getSize() +"\">" + my_new_str +"</text>\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
}

//font-family="omsign" font-size="24"


@Override
public void omFillEllipse(double x, double y, double w, double h) {
	// TODO Auto-generated method stub

}


@Override
public void omDrawEllipse(double x, double y, double w, double h) {
	// TODO Auto-generated method stub

}


@Override
public void omFillCercle(double cx, double cy, double r) {
	if (svgoutput != null){
     	try {
     		svgoutput.write("<circle cx=\"" + cx +"\" cy=\"" + cy +
					"\" r=\"" + r + "\" fill=\"" + c2s(colorfill) + "\" />\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }

}


@Override
public void omDrawCercle(double cx, double cy, double r) {
	if (svgoutput != null){
     	try {
			svgoutput.write("<circle cx=\"" + cx +"\" cy=\"" + cy +
					"\" r=\"" + r + "\" fill=\"none\" stroke=\"" +
					c2s(colorstroke) +
					"\" stroke-width=\"" + linewidth + "\" />\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }

}


@Override
public void omFillRect(double x, double y, double w, double h) {
	// TODO Auto-generated method stub

}


@Override
public void omDrawRect(double x, double y, double w, double h) {
	// TODO Auto-generated method stub

}


@Override
public void omEraseRectContent(double x, double y, double w, double h) {
	// TODO Auto-generated method stub

}


@Override
public void omDrawEllipseArc(int x, int y, int width, int height,
		int start_angle, int sweep_angle) {
	// TODO Auto-generated method stub

}


@Override
public void omSetFont(Font thefont) {
	font = thefont;
}


@Override
public Font omGetFont() {
	return font;
}


@Override
public void omOpen() {

}


@Override
public void omClose() {
	if ( svgoutput != null ) {
		try {
			svgoutput.write("</svg>\n");
			svgoutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		svgoutput = null;
	}
}


@Override
public void omSetLineWidth(double size) {
	linewidth =size;
}

@Override
public void omDrawBezierCurve(double x1, double y1, double x2, double y2, double xc1, double yc1, double xc2,
		double yc2) {
	// TODO Auto-generated method stub

}

@Override
public void omDrawBezierArrow(double x1, double y1, double x2, double y2, double xc1, double yc1, double xc2,
		double yc2, double arrsize) {
	// TODO Auto-generated method stub

}

}
