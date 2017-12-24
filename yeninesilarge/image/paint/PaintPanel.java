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
													KeyListener {
	PaintingPanel pnlImage;
	JPanel pnlColor;
	static String selectedTool;
	static boolean isFill;
	static int strokeLevel;
	static boolean dash;
	static Color color;

	private static final String DASH = "Dash";
	private static final String FILL = "Fill";

	public static JFrame buildFrame(String title, JPanel panel, int x, int y, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		frame.setContentPane(panel);
		frame.setBounds(x, y, width, height);
		//frame.pack();
		frame.setFocusable(true); // needs by key listener
		frame.setFocusTraversalKeysEnabled(false);
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
		makeTool(pnlTools, groupDrawing, Tool.RECTANGLE);
		makeTool(pnlTools, groupDrawing, Tool.OVAL); 
		makeTool(pnlTools, groupDrawing, Tool.RUBBER); 
		makeTool(pnlTools, groupDrawing, Tool.PENCIL);

		makeButton(pnlTools, null, "Clear").addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pnlImage.removeShapes();
			}
		});

		JComboBox<Integer> cmbStrokeLevel = new JComboBox(); // stroke level	
		for(int i = 1; i < 5; i++) {
			cmbStrokeLevel.addItem(i);
		}
		makeComponent(pnlTools, cmbStrokeLevel, null);
		//stackoverflow.com/questions/18405660/how-to-set-component-size-inside-container-with-boxlayout
		cmbStrokeLevel.setMaximumSize(new Dimension(Integer.MAX_VALUE, cmbStrokeLevel.getMinimumSize().height)); 
	

		JCheckBox checkFill = new JCheckBox(FILL); //fill
		makeComponent(pnlTools, checkFill, FILL);

		JCheckBox checkDash = new JCheckBox(DASH); 		//dash
		makeComponent(pnlTools, checkDash, DASH);

		cmbStrokeLevel.addItemListener(strokeLevelListener);
		checkFill.addItemListener(checkFillListener);
		checkDash.addItemListener(checkFillListener);


		makeButton(pnlTools, null, "Undo").addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pnlImage.undo();
			}
		});

		makeButton(pnlTools, null, "Redo").addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pnlImage.redo();
			}
		});

		makeButton(pnlTools, null, "New").addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pnlImage.newPainting();
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

		pnlColor = new JPanel();
		pnlColor.setOpaque(true);
		add(pnlColor, constColor);
	
		color = Color.BLACK; // default color
		pnlColor.setForeground(color);
		pnlColor.setBackground(color);

		pnlColor.addMouseListener(colorListener);

	}
	
	private JToggleButton makeTool(JPanel panel, ButtonGroup group, String name){
		panel.add(Box.createRigidArea(new Dimension(5,5)));
		JToggleButton tool = new JToggleButton(name);
		tool.setAlignmentX(Component.CENTER_ALIGNMENT);
		tool.setActionCommand(name); // stackoverflow.com/questions/27916896/what-is-an-action-command-that-is-set-by-setactioncommand
		panel.add(tool);
		tool.setFocusable(false);
		if(group != null)
			group.add(tool);
		return tool;
	}

	private JButton makeButton(JPanel panel, ButtonGroup group, String name){
		panel.add(Box.createRigidArea(new Dimension(5,5)));
		JButton btn = new JButton(name);
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);	
		panel.add(btn);
		btn.setFocusable(false);
		if(group != null)
			group.add(btn);
		return btn;
	}

	private JComponent makeComponent(JPanel panel, JComponent component, String name) {
		panel.add(Box.createRigidArea(new Dimension(5,5)));
		component.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(component);
		component.setFocusable(false);
		if ( component instanceof JButton && name != null) {
			JButton b = (JButton) component;
			b.setActionCommand(name);
		}
		return component;
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
			params = Tool.buildParams(selectedTool, x1, y1, x1, y1, 
								color,
								null,
								Tool.createStroke(selectedTool, strokeLevel, dash),
								isFill);

			pnlImage.beginDraw(params);

			/* when marked an area with Select tool, if user tries to reselect different area
			   it should be popped and added new one. While doing this process in mouseRealesed()
			   old one could be deleted and it will be shown on GUI. To make it happen invokes 
				repaint() 
			*/
			String s = pnlImage.peekShapeTag(); 
			if ( s != null && s.equals(Tool.SELECT) )
				pnlImage.popShape();  
								
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

			if( selectedTool.equals(Tool.PENCIL) || selectedTool.equals(Tool.RUBBER) ) {
				System.out.println("pencil");
				pnlImage.completeDraw(params);
				params = Tool.buildParams(selectedTool, x2, y2, x2, y2, 
								color,
								null,
								null, // test
								isFill);
				
			}			

			pnlImage.beginDraw(params);

			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if( selectedTool == null ) return;
	
			//we know that x2 and y2 is updating from mouseDragged event so 
			//there is no meaning to catching those 2 points.

			params.put("completed", true);
			pnlImage.completeDraw(params);
			pnlImage.repaint();
			
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

		// KeyListener events
	@Override
	public void keyPressed(KeyEvent e) {
		
		if( e.getKeyChar() == 127){
			Map<String, Object> params = pnlImage.peekShape();
			if ( params != null && params.get("tag").equals(Tool.SELECT) ) {			
				params.put("tag", Tool.DELETE);
				pnlImage.repaint();
			}

		}
	}
	
	public void keyReleased(KeyEvent e) { }
	public void keyTyped(KeyEvent e) { }
	
	// Combobox's ItemListener
	ItemListener strokeLevelListener = new ItemListener() {
		@Override	
		public void itemStateChanged(ItemEvent e) {
			if( e.getStateChange() == ItemEvent.SELECTED) {
				strokeLevel = (Integer) e.getItem();
			}
		}
	};

	// Checkbox's Listener
	ItemListener checkFillListener = new ItemListener() {
		@Override	
		public void itemStateChanged(ItemEvent e) {
			JCheckBox cb = (JCheckBox) e.getItem();
			
			if (cb.getActionCommand().equals(DASH)) {
				dash = !dash;
			} else { // if its not dash its fill
				isFill = !isFill;
			}

		}
	};

	//Color Listener 		//riyafa.wordpress.com/2014/07/22/jcolorchooser-hide-all-default-panels-and-show-rgb-and-swatches-panels-only/
	MouseListener colorListener = new MouseAdapter() {
		@Override	
		public void mouseClicked(MouseEvent e) {
			color = JColorChooser.showDialog(
					 		PaintPanel.this,
					 "Choose a color",
					 ((JPanel) e.getComponent()).getBackground());
			pnlColor.setForeground(color);
			pnlColor.setBackground(color);
		}
	};
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

