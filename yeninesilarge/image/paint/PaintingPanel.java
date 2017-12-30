package yeninesilarge.image.paint;

import yeninesilarge.application.ApplicationManager;
import yeninesilarge.image.ImagePanel;

import java.awt.image.BufferedImage;
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
import java.util.HashMap;

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
			String tag = params.get("tag").toString();
			Tool t = ToolFactory.getInstance(tag);
			t.operate(g, params);
		}
	
		if( params != null ){
			String tag = params.get("tag").toString();
			Tool t = ToolFactory.getInstance(tag);
			t.operate(g, params);
		}

	}

	public void newPainting(){
		// filling panel by using clearRect. Passing panels width and height.
		Map<String, Object> clearParams = Tool.buildParams(Tool.RECTANGLE, 0, 0, 2000, 2000, //2000,2000 should be gets from screens resolution.
															Color.WHITE,
															null, null, true);
		completeDraw(clearParams);
		params = null;
		repaint();
	}

	public void beginDraw(Map p) {
		params = p;
		repaint();
	}

	public void completeDraw(Map params){
		shapes.push(params);
		params = null;
		redo.clear();
	}

	public void removeShapes(){
		shapes.clear();
		redo.clear();
		params = null;
		repaint();
	}	

	public Map<String, Object> peekShape(){
		if (!shapes.isEmpty())
			return shapes.peek();
		return null;
	}

	public String peekShapeTag(){
		String s = null;		
		if (!shapes.isEmpty())
			s = (String) shapes.peek().get("tag");
		return s;
	}


	public Map<String, Object> popShape(){
		Map<String, Object> s= null;
		if (!shapes.isEmpty()) {
			s = shapes.pop();
			repaint();	
		}
		return s;
	}

	public Map<String, Object> peekRedo(){
		if (!redo.isEmpty())
			return redo.peek();
		return null;
	}

	public Map<String, Object> popRedo(){
		Map<String, Object> s= null;
		if (!redo.isEmpty()) {
			s = redo.pop();
		}
		return s;
	}

	public void undo() {
		if ( shapes.isEmpty() ) return;

		// the drawing on top of stack's 'completed' property always true.
		// uses do-while to identify if a drawed a shape or 
		Map<String, Object> temp = peekShape();

		do {
			redo.add(popShape());
			temp = peekShape();
			params = null; // i dont know why, somehow assigned last drawing into params. I couldnt find the reason of this. completeDraw is already
							// asssign null into params but it still gets last drawing from somewhere, odd.
		} while( temp != null && !((Boolean)temp.get("completed")) ) ;

		repaint();
	}
	
	public void redo() {
		if ( redo.isEmpty() ) return;

		Map<String, Object> temp = peekRedo();
		
		do {
			shapes.push( popRedo() );
			temp = peekRedo() ; 
			params = null;
		} while ( temp != null && !((Boolean)temp.get("completed")) );

		repaint();
	}

	public void write(String imageName) {
		
		try { 

			BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = image.createGraphics();
			paint(g2);

			for( Map<String, Object> params : shapes ) {
				String tag = params.get("tag").toString();
				Tool t = ToolFactory.getInstance(tag);
				t.operate(g2, params);
			}

			File yna = new File(ApplicationManager.USER_HOME, ApplicationManager.DIR);
			if( !yna.exists() ) yna.mkdir();

			File imageFile = new File(yna, imageName+ ".jpg");

			ImageIO.write(image, "jpg", imageFile); 

			shapes.clear();
			redo.clear();
		}catch (Exception x){
	
		}
	}

}




