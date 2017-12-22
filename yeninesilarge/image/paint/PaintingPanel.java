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
import java.util.Map;

import javax.imageio.*;
import java.io.*;
import java.awt.image.RenderedImage;

public class PaintingPanel extends ImagePanel{
	private Map<String, Object> params; //currently drawing image, mouse not realesed
	private Stack<Map<String,Object>> shapes; // undo  
	private Stack<Map<String,Object>> redo; // redo

	PaintingPanel() {
		shapes = new Stack<>();
		redo = new Stack<>();
	}

	@Override	
	public void paint(Graphics g){
		super.paint(g);

		for( Map<String, Object> params : shapes ) {
			Tool t = ToolFactory.getInstance((String) params.get("tool"));
			t.draw(g, params);
		}
	
		if( params != null ){
			Tool t = ToolFactory.getInstance((String) params.get("tool"));
			t.draw(g, params);
		}

	}

	public void addDraw(Map params){
		shapes.push(params);
		params = null;
		redo.clear();
		repaint();
	}
	
	public void setParams(Map p) {
		params = p;
		repaint();
	}

	public void removeShapes(){
		shapes.clear();
		redo.clear();
		params = null;
		repaint();
	}	

}
