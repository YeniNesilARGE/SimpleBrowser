package yeninesilarge.image.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.MouseInputAdapter;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import javax.imageio.*;
import java.awt.image.*;


public class PaintPanel extends JPanel implements ToolButtonGroup.ToolButtonListener,
													KeyListener{
	PaintingPanel pnlImage;
	String selectedTool;

	public static JFrame buildFrame(String title, JPanel panel, int x, int y, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		frame.setContentPane(panel);
		frame.setBounds(x,y,width,height);
		//frame.pack();
		frame.setFocusable(true); // needs by key listener
		frame.addKeyListener( (KeyListener) panel); // key listener
		frame.setVisible(true);
		return frame;
	}

	PaintPanel(){
		init();
	}

	public static void main(String... argv){
		
		PaintPanel pp = new PaintPanel();

		buildFrame("Simple Paint", pp, 500, 50, 900, 900);
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
		makeTool(pnlTools, groupDrawing, Tool.SELECT);
		makeTool(pnlTools, groupDrawing, Tool.LINE);
		makeTool(pnlTools, groupDrawing, Tool.TRIANGLE);
		makeTool(pnlTools, groupDrawing, Tool.RECTANGLE);
		makeTool(pnlTools, groupDrawing, Tool.OVAL); 

		makeButton(pnlTools, null, "Clear").addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pnlImage.removeShapes();
			}
		});

		// --------- IMAGE ---------------
		GridBagConstraints constImage= new GridBagConstraints();
		constImage.gridx = 3;
        constImage.gridy = 0;
        constImage.weightx = 0.9;
        constImage.gridheight = 9;
        constImage.fill = GridBagConstraints.BOTH;

		pnlImage = new PaintingPanel();
		pnlImage.addMouseListener(mouseListener); // listens mouse pressed and released events
		pnlImage.addMouseMotionListener(mouseListener); // listens mouse dragged event
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
	
	private JToggleButton makeTool(JPanel panel, ButtonGroup group, String name){
		panel.add(Box.createRigidArea(new Dimension(5,5)));
		JToggleButton tool = new JToggleButton(name);
		tool.setAlignmentX(Component.CENTER_ALIGNMENT);
		tool.setActionCommand(name); // stackoverflow.com/questions/27916896/what-is-an-action-command-that-is-set-by-setactioncommand
		panel.add(tool);
		if(group != null)
			group.add(tool);
		return tool;
	}

	private JButton makeButton(JPanel panel, ButtonGroup group, String name){
		panel.add(Box.createRigidArea(new Dimension(5,5)));
		JButton btn = new JButton(name);
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);	
		panel.add(btn);
		if(group != null)
			group.add(btn);
		return btn;
	}

	@Override
	public void onSelection(String selectedTool){
		this.selectedTool = selectedTool;
	}

	private MouseInputAdapter mouseListener = new MouseInputAdapter() {
		private int x1,x2,y1,y2;
		private Map<String, Object> params;

		@Override
		public void mousePressed(MouseEvent e) {
			if( selectedTool == null ) return;
			Point coordinates = coordinatesFromPoint(e.getComponent());
			x1 = (int) coordinates.getX();
			y1 = (int) coordinates.getY();						

			// set x1,y1 in place of x2,y2 as default.
			params = buildParams(selectedTool, x1, y1, x1, y1, 
									null,null,null,false); 
 			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if( selectedTool == null ) return;

			Component  c = e.getComponent();
			Graphics g = c.getGraphics();

			Point coordinates = coordinatesFromPoint(e.getComponent());
			x2 = (int) coordinates.getX();
			y2 = (int) coordinates.getY();	
		
			params.put("x2", x2);
			params.put("y2", y2);
			
			pnlImage.setParams(params);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if( selectedTool == null ) return;
	
			//we know that x2 and y2 is updating from mouseDragged event so 
			//there is no meaning to catching those 2 points.

			pnlImage.addDraw(params);
		}

	};

	//stackoverflow.com/questions/23730793/how-to-get-image-pixel-coordinates-
	//in-java-by-pointing-mouse-to-a-specific-poin
	Point coordinatesFromPoint(Component comp){
		PointerInfo pointerInfo = MouseInfo.getPointerInfo();
		Point p = new Point(pointerInfo.getLocation());
		SwingUtilities.convertPointFromScreen(p, comp);
		return p;
	}

	Map<String, Object> buildParams(String tool, 
									int x1, int y1, int x2, int y2,
									Color c, Font font, Stroke s, boolean fill){
		Map<String, Object> params = new HashMap<>();
		params.put("tool", tool); //tool name, i.e. Oval, Line..
		params.put("x1", x1 );
		params.put("y1", y1 );
		params.put("x2", x2 );
		params.put("y2", y2 );
		params.put("color", c);
		params.put("font", font);
		params.put("stroke", s);
		params.put("fill", true); 
		return params;
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if( e.getKeyChar() == 127 ){
			System.out.println("dele basıldı");
		}
	}
	
	public void keyReleased(KeyEvent e) { }
	public void keyTyped(KeyEvent e) { }
	
	
	
}

//src : dzone.com/articles/unselect-all-toggle-buttons
class ToolButtonGroup extends ButtonGroup {

	private	ToolButtonListener toolListener;

	ToolButtonGroup(ToolButtonListener listener){
		toolListener = listener;
	}

	@Override
	public void setSelected(ButtonModel model, boolean selected) {
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
		void onSelection(String selectedTool);
	}
}


