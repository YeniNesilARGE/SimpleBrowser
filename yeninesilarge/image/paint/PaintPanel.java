package yeninesilarge.image.paint;

import javax.swing.*;
import java.awt.*;

public class PaintPanel extends JPanel{

	public static JFrame buildFrame(String title, JPanel panel, int x, int y, int width, int height){
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		frame.setContentPane(panel);
		frame.setBounds(x,y,width,height);
		//frame.pack();
		frame.setVisible(true);
		return frame;
	}

	public static void main(String... argv){
		
		PaintPanel pp = new PaintPanel();
		init(pp);
		buildFrame("Simple Paint", pp, 500, 50, 900, 900);
	
	}

	private static void init(JPanel pp){
		pp.setLayout(new GridBagLayout());
		
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
		pp.add(pnlTools,constTools);

		// --------- IMAGE ---------------
		GridBagConstraints constImage= new GridBagConstraints();
		constImage.gridx = 3;
        constImage.gridy = 0;
        constImage.weightx = 0.9;
        constImage.gridheight = 9;
        constImage.fill = GridBagConstraints.BOTH;

		JPanel pnlImage = new JPanel();
		pnlImage.setForeground(java.awt.Color.GREEN);
		pnlImage.setBackground(java.awt.Color.GREEN);
		pnlImage.setOpaque(true);
		pp.add(pnlImage, constImage);

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
		pp.add(pnlColor, constColor);

	}
	
}
