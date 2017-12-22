package yeninesilarge.image.paint;

import yeninesilarge.image.ImagePanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.util.Stack;

import javax.imageio.*;
import java.io.*;
import java.awt.image.RenderedImage;

public class PaintingPanel extends ImagePanel{
	private Tool tool; //currently drawing image, mouse not realesed
	private Stack<Tool> shapes; // undo  
	private Stack<Tool> redo; // redo

	PaintingPanel() {
		shapes = new Stack<>();
		redo = new Stack<>();
	}

	@Override	
	public void paint(Graphics g){
		super.paint(g);

		for( Tool t : shapes ) {
			t.draw(g);
		}
	
		if( tool != null ){
			tool.draw(g);
		}

	}

	public void addTool(Tool t){
		shapes.push(t);
		tool = null;
		redo.clear();
		repaint();
	}
	
	public void setTool(Tool t) {
		tool = t;
		repaint();
	}

	public void removeShapes(){
 Graphics2D g2d = getImage().createGraphics();

    g2d.setColor(Color.black);
    g2d.fillOval(0, 0, 100, 100);

    g2d.dispose();

try   { RenderedImage rendImage = getImage();

    File file = new File("newimage.png");
    ImageIO.write(rendImage, "png", file); } catch (Exception x){}

/*
		shapes.clear();
		redo.clear();
		tool = null;*/
		repaint();
	}	

}
