import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.swing.*;

public class ImageDisplay extends JPanel{
	BufferedImage image;
	
	@Override	
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(image,0,0,null);
	}
	public void setImage(BufferedImage i){
	this.image = i;
	}
}
