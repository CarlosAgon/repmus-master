package gui.renders;

import gui.FX;
import gui.FXCanvas;

import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.FontFactory;

import javafx.scene.image.Image;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
import resources.Loader;

public class PDFRender implements I_Render{

	Document doc = null;
	PdfWriter writer;
	PdfContentByte cb;
	float pgw;
	float pgh;
	Font fontsign;
	Font fontheads;
	Font fontextras;
	Font fontomicron;
	Font fontdefault;
	javafx.scene.text.Font curfont;

	javafx.scene.paint.Color colorstroke = javafx.scene.paint.Color.BLACK;
	javafx.scene.paint.Color colorfill = null;

	public PDFRender (FXCanvas view){
		String filesign = "/fonts/mac/omsign.ttf";
		String fileextra = "/fonts/mac/omextras.ttf";
		String fileomicron = "/fonts/mac/omicron.ttf";
		String fileheads = "/fonts/mac/omheads.ttf";

		FontFactory.register(fileheads, fileheads);
		fontheads = FontFactory.getFont(fileheads, BaseFont.CP1252, BaseFont.EMBEDDED);
		FontFactory.register(filesign, filesign);
		fontsign = FontFactory.getFont(filesign, BaseFont.CP1252, BaseFont.EMBEDDED);
		FontFactory.register(fileomicron, fileomicron);
		fontomicron = FontFactory.getFont(fileomicron, BaseFont.CP1252, BaseFont.EMBEDDED);
		FontFactory.register(fileextra, fileextra);
		fontextras = FontFactory.getFont(fileextra, BaseFont.CP1252, BaseFont.EMBEDDED);

		pgw = Math.max(72, (float) view.w());
		pgh = Math.max(72, (float) view.h());
		Rectangle pageSize = new Rectangle(pgw, pgh);
		doc = new Document(pageSize);
		try {
			writer = PdfWriter.getInstance(doc,
				new FileOutputStream("ejemplo.pdf"));
			doc.open();
			cb = writer.getDirectContent();
			cb.setLineWidth(1f);
			curfont = FX.default_font1;
			fontdefault = new Font(Font.FontFamily.COURIER, Font.DEFAULTSIZE, Font.NORMAL);
			BaseFont bf = fontdefault.getCalculatedBaseFont(false);
			cb.setFontAndSize(bf, 24f);
			cb.setColorStroke(new GrayColor(0.2f));

		} catch (Exception e) {
		// handle exception
		}

	}

	@Override
	public void drawImage(Image img, double x, double y, double w, double h) {

	}

	@Override
	public void drawImage(Image img, double x, double y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawImage(Image img, double sx, double sy, double sw,
			double sh, double dx, double dy, double dw, double dh) {
		// TODO Auto-generated method stub
	}

	@Override
	public javafx.scene.paint.Color omGetColorStroke() {
		return colorstroke;
	}

	@Override
	public void omSetColorStroke(javafx.scene.paint.Color color) {
		colorstroke = color;
		cb.setRGBColorStroke((int) color.getRed()*255, (int) color.getGreen()*255, (int)color.getBlue()*255);
	}

	@Override
	public javafx.scene.paint.Color omGetColorFill() {
		return colorfill;
	}

	@Override
	public void omSetColorFill(javafx.scene.paint.Color color) {
		if (color != null)
			cb.setRGBColorFill((int) color.getRed()*255, (int) color.getGreen()*255, (int)color.getBlue()*255);
		colorfill = color;

	}

	@Override
	public void omDrawLine(double x, double y, double x1, double y1) {
		cb.moveTo(x, (pgh - y));
		cb.lineTo(x1, (pgh - y1));
		cb.stroke();
	}

	@Override
	public void omDrawString(double x, double y, String str) {
		cb.saveState();
		cb.beginText();
		cb.moveText((float) x, (float) (pgh - y ));
		cb.showText(str);
		cb.endText();
		cb.restoreState();
	}

	@Override
	public void omFillEllipse(double x, double y, double w, double h) {
		cb.ellipse(x, pgh - y, x+w, pgh - y + w);
		cb.fill();
	}

	@Override
	public void omDrawEllipse(double x, double y, double w, double h) {
		cb.ellipse(x, pgh - y, x+w, pgh - y + w);
		cb.stroke();
	}

	@Override
	public void omFillCercle(double cx, double cy, double r) {
		cb.circle(cx, pgh - cy, r);
		cb.fill();
	}

	@Override
	public void omDrawCercle(double cx, double cy, double r) {
		cb.circle(cx, pgh - cy, r);
		cb.stroke();
	}


	@Override
	public void omFillRect(double x, double y, double w, double h) {
		cb.rectangle(x, pgh-y, w, h);
		cb.fill();
	}

	@Override
	public void omDrawRect(double x, double y, double w, double h) {
		cb.rectangle(x, pgh-y, w, h);
		cb.stroke();
	}

	@Override
	public void omEraseRectContent(double x, double y, double w, double h) {

	}

	@Override
	public void omDrawEllipseArc(int x, int y, int w, int h,
			int start_angle, int sweep_angle) {// angle en degrees
		cb.arc(x, pgh-y, x+w, pgh-y+h, start_angle, sweep_angle);

	}


	public Font getPDFfont(javafx.scene.text.Font font) {
		Font rep = fontdefault;
		switch(font.getName()) {
		case "omheads" : rep = fontheads; break;
		case "omicron" : rep = fontomicron; break;
		case "omsign" : rep = fontsign; break;
		case "omextras" : rep = fontextras; break;
		}
		return rep;
	}

	public void omSetFont(javafx.scene.text.Font font) {
		curfont = font;
		Font pdffont = getPDFfont(font);
		BaseFont bf = pdffont.getCalculatedBaseFont(false);
		cb.setFontAndSize(bf, (float) font.getSize());
	}

	@Override
	public javafx.scene.text.Font omGetFont() {
		// TODO Auto-generated method stub
		return curfont;
	}

	@Override
	public void omOpen() {
		// TODO Auto-generated method stub
	}

	@Override
	public void omClose() {
		if (doc != null)
			doc.close();

	}

	@Override
	public void omSetLineWidth(double size) {
		cb.setLineWidth(size);
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
