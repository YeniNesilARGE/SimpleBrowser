package yeninesilarge.image.paint;

import yeninesilarge.util.Reflect;
import java.lang.reflect.Constructor;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.HashMap;

//should make(Graphics,Map) throws NullPointerException? -Properties can be null-
public abstract class Tool {
	String name;

	public static final String TRIANGLE = "Triangle", 
								LINE = "Line", 
								TEXT = "Text", 
								OVAL = "Oval", 
								RECTANGLE = "Rectangle", 
								SELECT = "Select",
								DELETE = "Delete", 
								RUBBER = "Rubber",
								PENCIL = "Pencil" ;
	
	protected Tool(String tool){ // reflection will instantinate Tool objects
		name = tool;
	}

	
	void operate(Graphics g, Map<String,Object> params) {
		Graphics2D g2 = (Graphics2D) g;
		Color color = (Color) params.get("color");
		if( color != null ) {
			g2.setColor(color);
		}

		Stroke stroke = (Stroke) params.get("stroke");
		if( stroke != null ) {
			g2.setStroke(stroke);
		}
	}

	void operate(Component comp, Map<String, Object> params) {
		Graphics g = comp.getGraphics();
		operate(g, params);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}

class Select extends Tool {
	
	private Select(String tool) {
		super(tool);
	}
	
	void operate(Graphics g, Map<String, Object> params) {
		super.operate(g,params);
		int x1 = (Integer) params.get("x1");
		int y1 = (Integer) params.get("y1");
		int x2 = (Integer) params.get("x2");
		int y2 = (Integer) params.get("y2");

		if ( x2 < x1 ) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}	

		if ( y2 < y1 ) {
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}	

		int width = x2 - x1;
		int height = y2 -y1;
	

		g.drawRect(x1,y1,width,height);
	}
}

class Delete extends Tool {
	
	private Delete(String tool) {
		super(tool);
	}
	
	void operate(Graphics g, Map<String, Object> params) {
		super.operate(g,params);
		int x1 = (Integer) params.get("x1");
		int y1 = (Integer) params.get("y1");
		int x2 = (Integer) params.get("x2");
		int y2 = (Integer) params.get("y2");

		if ( x2 < x1 ) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}	

		if ( y2 < y1 ) {
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}	

		int width = x2 - x1;
		int height = y2 - y1;
		g.clearRect(x1,y1,width,height);
	}
}

class Rubber extends Tool {
	
	private Rubber(String tool) {
		super(tool);
	}
	
	void operate(Graphics g, Map<String, Object> params) {
		super.operate(g,params);
		int x1 = (Integer) params.get("x1");
		int y1 = (Integer) params.get("y1");
		int x2 = (Integer) params.get("x2");
		int y2 = (Integer) params.get("y2");

		int width = 20;
		int height = 20;

		g.clearRect(x1,y1,width,height);
	}
}

class Text extends Tool {

	private Text(String tool) {
		super(tool);
	}
	
	void operate(Graphics g, Map<String,Object> params) {
		super.operate(g, params);
		String str = (String) params.get("text");
		int x = (Integer) params.get("x");
		int y = (Integer) params.get("y");

		Font font = (Font) params.get("font");
		if( font != null) g.setFont(font);
		
		g.drawString(str, x, y);
	}
}

class Line extends Tool {
	
	private Line(String tool) {
		super(tool);
	}
	
	void operate(Graphics g, Map<String, Object> params) {
		super.operate(g,params);
		int x1 = (Integer) params.get("x1");
		int y1 = (Integer) params.get("y1");
		int x2 = (Integer) params.get("x2");
		int y2 = (Integer) params.get("y2");
		
		g.drawLine(x1,y1,x2,y2);
	}
}

class Rectangle extends Tool {
	
	private Rectangle(String tool) {
		super(tool);
	}
	
	void operate(Graphics g, Map<String, Object> params) {
		super.operate(g,params);
		int x1 = (Integer) params.get("x1");
		int y1 = (Integer) params.get("y1");
		int x2 = (Integer) params.get("x2");
		int y2 = (Integer) params.get("y2");

		if ( x2 < x1 ) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}	

		if ( y2 < y1 ) {
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}	

		int width = x2 - x1 ;
		int height = y2 -y1 ;
		
		Boolean fill = (Boolean) params.get("fill");
		
		if( fill != null && fill ) {
			g.fillRect(x1,y1,width,height);
		}
		else 
			g.drawRect(x1,y1,width,height);
	}
}

class Oval extends Tool {
	
	private Oval(String tool) {
		super(tool);
	}
	
	void operate(Graphics g, Map<String, Object> params) {
		Graphics t = g.create();
		super.operate(g,params);
		int x1 = (Integer) params.get("x1");
		int y1 = (Integer) params.get("y1");
		int x2 = (Integer) params.get("x2");
		int y2 = (Integer) params.get("y2");

		if ( x2 < x1 ) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}	

		if ( y2 < y1 ) {
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}	

		int width = x2 - x1;
		int height = y2 -y1;
		
		Boolean fill = (Boolean) params.get("fill");
		
		if( fill != null && fill ) 
			g.fillOval(x1,y1, width, height);
		else 
			g.drawOval(x1,y1, width, height);
	}
}

class Pencil extends Tool {
	
	private Pencil(String tool) {
		super(tool);
	}
	
	void operate(Graphics g, Map<String, Object> params) {
		Graphics t = g.create();
		super.operate(g,params);
		int x1 = (Integer) params.get("x1");
		int y1 = (Integer) params.get("y1");
		int x2 = (Integer) params.get("x2");
		int y2 = (Integer) params.get("y2");

		
		g.drawLine(x1,y1,x2,y2); 
	}
}

//stackoverflow.com/questions/26890747/java-abstract-factory-singleton
class ToolFactory {
	static Map<String,Tool> map = new HashMap<>(); //singleton

	//it might not be available to java 10 and newer, illegal access.
	public static Tool getInstance(String tool){
		Tool t = map.get(tool);
		if( t == null ) {
			String packageName = Tool.class.getPackage().getName(); //all tools are in same package.
		
			Class c = Reflect.getClass(packageName, tool);
			Constructor constructor = Reflect.getConstructor(c, String.class);
			constructor.setAccessible(true); 
			t = (Tool) Reflect.newInstance(constructor, tool);
			map.put(tool, t);
		}
		return t;
	}
}

/*

class Triangle extends Tool {
	
	private Triangle(String tool) {
		super(tool);
	}
	
	void operate(Graphics g, Map<String, Object> params) {
		super.operate(g,params);
		int[] xPoints = (int[]) params.get("xPoints");
		int[] yPoints = (int[]) params.get("yPoints");
		
		if( xPoints.length != yPoints.length) throw new RuntimeException();

		Boolean fill = (Boolean) params.get("fill");
		
		if( fill != null && fill ) 
			g.fillPolygon(xPoints,yPoints, xPoints.length);
		else 
			g.drawPolygon(xPoints,yPoints, xPoints.length);
	}
}

*/
