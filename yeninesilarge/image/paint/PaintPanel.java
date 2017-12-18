package yeninesilarge.image.paint;

import yeninesilarge.image.ImagePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;


public class PaintPanel extends JPanel implements ToolButtonGroup.ToolButtonListener{
	ImagePanel pnlImage;
	Tool selectedTool;
	
	public static JFrame buildFrame(String title, JPanel panel, int x, int y, int width, int height){
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		frame.setContentPane(panel);
		frame.setBounds(x,y,width,height);
		//frame.pack();
		frame.setVisible(true);
		return frame;
	}

	PaintPanel(){
		init();
	}

	public static void main(String... argv){
		
		PaintPanel pp = new PaintPanel();

		buildFrame("Simple Paint", pp, 500, 50, 900, 900);
	
		//testing
		Tool t = new Triangle("line");
		Graphics g = pp.pnlImage.getImage().getGraphics();
		
		Map<String, Object> params = new HashMap<>();
		params.put("text","dog");
		params.put("xPoints", new int[]{501, 700, 850});
		params.put("yPoints", new int[]{60, 200, 200});
		params.put("fill", true); 
		params.put("color", Color.YELLOW);
		
		t.draw(g,params);
	}

	private void init(){
		setLayout(new GridBagLayout());

		// --------- TOOLS ---------------
		GridBagConstraints constTools = new GridBagConstraints();
		constTools.gridx = 0;
        constTools.gridy = 0;
        constTools.weightx = 0.1;
        constTools.weighty = 0.7;
        constTools.anchor = GridBagConstraints.NORTHWEST;
        constTools.fill = GridBagConstraints.BOTH;

		JPanel pnlTools = new JPanel();
		pnlTools.setBackground(Color.WHITE);
		pnlTools.setOpaque(true);
		add(pnlTools,constTools);

		pnlTools.setLayout(new BoxLayout(pnlTools, BoxLayout.PAGE_AXIS));

		ButtonGroup groupDrawing = new ToolButtonGroup(this);
		makeTool(pnlTools, groupDrawing, "Select");
		makeTool(pnlTools, groupDrawing, "Line");
		makeTool(pnlTools, groupDrawing, "Triangle");
		makeTool(pnlTools, groupDrawing, "Rectangle");
		makeTool(pnlTools, groupDrawing, "Circle");

		// --------- IMAGE ---------------
		GridBagConstraints constImage= new GridBagConstraints();
		constImage.gridx = 3;
        constImage.gridy = 0;
        constImage.weightx = 0.9;
        constImage.gridheight = 9;
        constImage.fill = GridBagConstraints.BOTH;

		pnlImage = new ImagePanel();
		File sampleFile = new File("yeninesilarge/images","dog.jpg");
		try { pnlImage.setImage(sampleFile); } 
		catch(IOException e) { e.printStackTrace(); };
		add(pnlImage, constImage);

		// --------- COLOR ---------------
		GridBagConstraints constColor = new GridBagConstraints();
		constColor.gridx = 0;
        constColor.gridy = 8;
        constColor.weightx = 0.1;
        constColor.weighty = 0.3;
        constColor.anchor = GridBagConstraints.SOUTHWEST;
		constColor.fill = GridBagConstraints.BOTH;

		JPanel pnlColor = new JPanel();
		pnlColor.setForeground(java.awt.Color.YELLOW);
		pnlColor.setBackground(java.awt.Color.YELLOW);
		pnlColor.setOpaque(true);
		add(pnlColor, constColor);

	}
	
	@Override
	public void onSelection(String toolTitle){
		selectedTool = new Line(toolTitle);
		System.out.println("listened-"+toolTitle);
	}

	private JToggleButton makeTool(JPanel tools, ButtonGroup group, String name){
		tools.add(Box.createRigidArea(new Dimension(5,5)));
		JToggleButton tool = new JToggleButton(name);
		tool.setAlignmentX(Component.CENTER_ALIGNMENT);	
		tool.setActionCommand(name); //stackoverflow.com/questions/27916896/what-is-an-action-command-that-is-set-by-setactioncommand
		tools.add(tool);
		group.add(tool);
		return tool;
	}
	
}
//should draw(Graphics,Map) throws NullPointerException? -Properties can be null-
abstract class Tool {
	String name;
	void draw(Graphics g, Map<String,Object> params) {
		Color color = (Color) params.get("color");
		if( color != null ) {
			g.setColor(color);
		}
	}
	
	Tool(String name){
		this.name = name;
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

class Text extends Tool {

	Text(String name){
		super(name);
	}
	
	void draw(Graphics g, Map<String,Object> params) {
		super.draw(g, params);
		String str = (String) params.get("text");
		int x = (Integer) params.get("x");
		int y = (Integer) params.get("y");

		Font font = (Font) params.get("font");
		if( font != null) g.setFont(font);
		
		g.drawString(str, x, y);
	}
}

class Line extends Tool {
	
	Line(String name){
		super(name);
	}
	
	void draw(Graphics g, Map<String, Object> params) {
		super.draw(g,params);
		int x1 = (Integer) params.get("x1");
		int y1 = (Integer) params.get("y1");
		int x2 = (Integer) params.get("x2");
		int y2 = (Integer) params.get("y2");
		
		g.drawLine(x1,y1,x2,y2);
	}
}

class Triangle extends Tool {
	
	Triangle(String name){
		super(name);
	}
	
	void draw(Graphics g, Map<String, Object> params) {
		super.draw(g,params);
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

class Rectangle extends Tool {
	
	Rectangle(String name){
		super(name);
	}
	
	void draw(Graphics g, Map<String, Object> params) {
		super.draw(g,params);
		int x1 = (Integer) params.get("x1");
		int y1 = (Integer) params.get("y1");
		int x2 = (Integer) params.get("x2");
		int y2 = (Integer) params.get("y2");
		
		Boolean fill = (Boolean) params.get("fill");
		
		if( fill != null && fill ) 
			g.fillRect(x1,y1,x2,y2);
		else 
			g.drawRect(x1,y1,x2,y2);
	}
}

class Oval extends Tool {
	
	Oval(String name){
		super(name);
	}
	
	void draw(Graphics g, Map<String, Object> params) {
		super.draw(g,params);
		int x1 = (Integer) params.get("x1");
		int y1 = (Integer) params.get("y1");
		int x2 = (Integer) params.get("x2");
		int y2 = (Integer) params.get("y2");
		
		Boolean fill = (Boolean) params.get("fill");
		
		if( fill != null && fill ) 
			g.fillOval(x1,y1,x2,y2);
		else 
			g.drawOval(x1,y1,x2,y2);
	}
}

//src : dzone.com/articles/unselect-all-toggle-buttons
class ToolButtonGroup extends ButtonGroup {

	private	ToolButtonListener toolListener;

	ToolButtonGroup(ToolButtonListener listener){
		toolListener = listener;
	}

	@Override
	public void setSelected(ButtonModel model, boolean selected) {
	System.out.println("setSelected-");
		if (selected) {
			super.setSelected(model, selected);
			String actionCommand = model.getActionCommand();
			toolListener.onSelection(actionCommand);
		} else {
			clearSelection();
			toolListener.onSelection(null);
		}
	}

	interface ToolButtonListener{
		void onSelection(String toolTitle);
	}
}


