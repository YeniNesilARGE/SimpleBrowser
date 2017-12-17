package yeninesilarge.image;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImagePanel extends JPanel{
	private BufferedImage image;
	
	@Override	
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(image,0,0,null);
	}
	public void setImage(File f) throws IOException {
		setImage(ImageIO.read(f));
	}

	public void setImage(BufferedImage i){
		this.image = i;
		this.updateUI();
	}
	public BufferedImage getImage(){
		return this.image;
	}
}
