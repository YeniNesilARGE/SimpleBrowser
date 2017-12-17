package yeninesilarge.image;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.swing.*;

public class ImagePanel extends JPanel{
	private BufferedImage image;
	
	@Override	
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(image,0,0,null);
	}
	public void setImage(BufferedImage i){
		this.image = i;
		this.updateUI();
	}
	public BufferedImage getImage(){
		return this.image;
	}
}
