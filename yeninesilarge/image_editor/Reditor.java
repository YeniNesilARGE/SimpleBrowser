import javax.swing.*;
import javax.swing.JFileChooser;
import java.awt.*;
import java.awt.image.WritableRaster;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.*;

public class Reditor extends javax.swing.JFrame {
	
public Reditor(){init();}

ImageDisplay i = new ImageDisplay();
ImageDisplay iTemp = new ImageDisplay();
JFileChooser fileChooser = new JFileChooser();
static BufferedImage BI;//current image we are worked on
static BufferedImage FBI;

static int before = 5;
JSlider slider = new JSlider(JSlider.HORIZONTAL,0,10,5);

private void init(){
	//Buttons 
	JButton btn1 = new JButton("GrayScale");
	btn1.addActionListener(new java.awt.event.ActionListener(){
		public void actionPerformed(java.awt.event.ActionEvent evt){
			btn1ActionPerformed(evt);
		}
	});
	
	JButton btn2 = new JButton("Vinyet");
	btn2.addActionListener(new java.awt.event.ActionListener(){
		public void actionPerformed(java.awt.event.ActionEvent evt){
			btn2ActionPerformed(evt);
		}
	});
	
	JButton btn3 = new JButton("RotateRigth");
	btn3.addActionListener(new java.awt.event.ActionListener(){
		public void actionPerformed(java.awt.event.ActionEvent evt){
			btn3ActionPerformed(evt);
		}
	});
	JButton btn5 = new JButton("RotateLeft");
	btn5.addActionListener(new java.awt.event.ActionListener(){
		public void actionPerformed(java.awt.event.ActionEvent evt){
			btn5ActionPerformed(evt);
		}
	});
	
	JButton btn4 = new JButton("Undo All Changes");
	btn4.addActionListener(new java.awt.event.ActionListener(){
		public void actionPerformed(java.awt.event.ActionEvent evt){
			btn4ActionPerformed(evt);
		}
	});
	JButton btn6 = new JButton("OpenImage");
	btn6.addActionListener(new java.awt.event.ActionListener(){
		public void actionPerformed(java.awt.event.ActionEvent evt){
			btn6ActionPerformed(evt);
		}
	});
	//Labels
	JLabel label1 = new JLabel("Brigthness");
	
	//Slider pane
	slider.addMouseListener(new java.awt.event.MouseAdapter(){
		public void mouseExited(java.awt.event.MouseEvent evt){
			sliderMouseExited(evt);
		}
	});
	//Grid layout
	//setLayout(new GridLayout(3,1));
	//setLayout(new BorderLayout());
	//Creating panels
	JPanel pane1 = new JPanel();
	pane1.setPreferredSize(new Dimension(800,50));
	JPanel pane3 = new JPanel();
	pane3.setPreferredSize(new Dimension(800,50));
	//
	i.setPreferredSize(new Dimension(760,600));
	//Create JScrollpane
	final JScrollPane scroll = new JScrollPane(i);
	
	//Creating layout pane1
	GroupLayout gl1 = new GroupLayout(pane1);
	pane1.setLayout(gl1);
	pane1.setToolTipText("Func");
	
	gl1.setAutoCreateContainerGaps(true);
	gl1.setAutoCreateGaps(true);
	
	gl1.setHorizontalGroup(gl1.createParallelGroup()
	.addGroup(gl1.createSequentialGroup()
		.addComponent(btn6)
		.addComponent(btn3)
		.addComponent(btn5)
		.addComponent(btn4))
	);
	
	gl1.setVerticalGroup(gl1.createSequentialGroup()
	.addGroup(gl1.createParallelGroup()
		.addComponent(btn6)
		.addComponent(btn3)
		.addComponent(btn5)
		.addComponent(btn4))
	);
	//Creating layout pane3
	GroupLayout gl3 = new GroupLayout(pane3);
	pane3.setLayout(gl3);
	gl3.setAutoCreateContainerGaps(true);
	gl3.setAutoCreateGaps(true);
	
	gl3.setHorizontalGroup(gl3.createParallelGroup()
	.addGroup(gl3.createSequentialGroup()
		.addComponent(label1)
		.addComponent(slider)
		.addComponent(btn1)
		.addComponent(btn2))
	);
	
	gl3.setVerticalGroup(gl3.createSequentialGroup()
	.addGroup(gl3.createParallelGroup()
		.addComponent(label1)
		.addComponent(slider)
		.addComponent(btn1)
		.addComponent(btn2))
	);
	
	//Adding panels to JFrame
	add(pane1,BorderLayout.NORTH);
	add(scroll,BorderLayout.CENTER);
	add(pane3,BorderLayout.SOUTH);
	pack();
	
	setTitle("rEditor");
	setSize(800,600);
	setMinimumSize(new Dimension(300,300));
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
}

private void btn1ActionPerformed(java.awt.event.ActionEvent evt){
	grayScaled();
	i.setImage(BI);
	i.updateUI();
}
private void btn2ActionPerformed(java.awt.event.ActionEvent evt){
	vinyet();
	i.setImage(BI);
	i.updateUI();
}
private void btn3ActionPerformed(java.awt.event.ActionEvent evt){
	BI = RotateRigth();
	i.setImage(BI);
	i.updateUI();
}
private void btn4ActionPerformed(java.awt.event.ActionEvent evt){
	if(BI != null){
	WritableRaster R = iTemp.image.getRaster();
	int h = R.getHeight(),w = R.getWidth();
	BufferedImage image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	image.setData(R);
	BI = image;
	i.setImage(BI);
	i.updateUI();
	}
}
private void btn5ActionPerformed(java.awt.event.ActionEvent evt){
	BI = RotateLeft();
	i.setImage(BI);
	i.updateUI();
}
private void btn6ActionPerformed(java.awt.event.ActionEvent evt){
	//Reading file
	int returnVal = fileChooser.showOpenDialog(this);
	if(returnVal == JFileChooser.APPROVE_OPTION) {
		File file = fileChooser.getSelectedFile();
		if(getFileExt(file).equals("jpg")){
		try{
			i.setImage(ImageIO.read(file));
			iTemp.setImage(ImageIO.read(file));
			BI = i.image;
		}catch(Exception e){
			System.out.println("Error while reading file");
		}}else{
		JOptionPane.showMessageDialog(this,"Please select a 'jpg' file.");
		}
	}
	//pack(); // Same job for refreshing image pane
	i.updateUI();
}

private void sliderMouseExited(java.awt.event.MouseEvent evt){
	brightness(slider.getValue());
	i.setImage(BI);
	i.updateUI();
	before=slider.getValue();
}
private void grayScaled(){
	if(BI != null){
	WritableRaster R = BI.getRaster();
	int[] rgb ={0,0,0};
	int w = BI.getWidth(), h = BI.getHeight();
	for(int x=0;x<w;x++){
		for(int y=0;y<h;y++){
			R.getPixel(x,y,rgb);
			int m = (rgb[0]+rgb[1]+rgb[2])/3;
			rgb[0] = m;rgb[1] = m;rgb[2] = m;
			R.setPixel(x,y,rgb);
		}
		BI.setData(R);
	}
	}else{
		JOptionPane.showMessageDialog(this,"Please open a 'jpg' file.5");
	}
}
private void brightness(int value){
	if(BI != null){
	WritableRaster R = BI.getRaster();
	int[] rgb ={0,0,0};
	int w = BI.getWidth(), h = BI.getHeight();
	for(int x=0;x<w;x++){
		for(int y=0;y<h;y++){
			R.getPixel(x,y,rgb);
			rgb[0]+=(value-before)*10;
			rgb[1]+=(value-before)*10;
			rgb[2]+=(value-before)*10;
			if(bound(rgb)){R.setPixel(x,y,rgb);}
		}
		BI.setData(R);
	}
	}else{}
}
private void vinyet(){
	if(BI != null){
	WritableRaster R = BI.getRaster();
	int[] rgb ={0,0,0};
	
	int w = BI.getWidth(), h = BI.getHeight();
	int midx = w/2 , midy = h/2 ;
	int dist = 0;
	int ort=0;
	int max = (int) getDist(midx-0,midy-0);
	for(int x = 0; x < 3; x++ ){
		for(int y = 0; y < 3; y++ ){
			dist = (int) getDist(midx-x,midy-y);
			R.getPixel(x,y,rgb);
			ort = (rgb[0]+rgb[1]+rgb[2])/3;
			while(controlBound(ort,max,dist)){
				rgb[0]+=3;
				rgb[1]+=3;
				rgb[2]+=3;
				ort = (rgb[0]+rgb[1]+rgb[2])/3;
			}
			R.setPixel(x,y,rgb);
		}}
	}else{
		JOptionPane.showMessageDialog(this,"Please open a 'jpg' file.");
	}
}
private BufferedImage RotateRigth(){
	if(BI != null){
	WritableRaster WRcur = BI.getRaster();
	int h = WRcur.getHeight(),w = WRcur.getWidth();
	BufferedImage image = new BufferedImage(h,w,BufferedImage.TYPE_INT_RGB);
	
	WritableRaster WRnew = image.getRaster();
	
	int[] rgb ={0,0,0};
	for(int x = 0; x < w; x++ ){
		for(int y = 0; y < h; y++ ){
			WRcur.getPixel(x,y,rgb);
			WRnew.setPixel(h-y-1,x,rgb);
		}
	}
	image.setData(WRnew);
	return image;
	}else{
		JOptionPane.showMessageDialog(this,"Please open a 'jpg' file.");
		return null;
	}
}
private BufferedImage RotateLeft(){
	if(BI != null){
	WritableRaster WRcur = BI.getRaster();
	int h = WRcur.getHeight(),w = WRcur.getWidth();
	BufferedImage image = new BufferedImage(h,w,BufferedImage.TYPE_INT_RGB);
	
	WritableRaster WRnew = image.getRaster();
	
	int[] rgb ={0,0,0};
	for(int x = 0; x < w; x++ ){
		for(int y = 0; y < h; y++ ){
			WRcur.getPixel(x,y,rgb);
			WRnew.setPixel(y,w-x-1,rgb);
		}
	}
	image.setData(WRnew);
	return image;
	}else{
		JOptionPane.showMessageDialog(this,"Please open a 'jpg' file.");
		return null;
	}
}
private boolean bound(int arr[]){
	if(arr[0]>255||arr[1]>255||arr[2]>255){return false;}
	if(arr[0]<0||arr[1]<0||arr[2]<0){return false;}
	return true;
}
private boolean controlBound(int ort,int max,int dist){
	int control = (dist*120)/max;
	if(ort>control-10&&ort<control+10){return false;}
	return true;
}
private double getDist(int x,int y){
	return Math.sqrt((x*x)+(y*y));
}
private String getFileExt(File file){
	String ext = null;
	String s = file.getName();
	int i = s.lastIndexOf('.');
	if(i>0&& i<s.length()-1){ext = s.substring(i+1).toLowerCase();}
	return ext;
}
public static void main(String[] argv){
	EventQueue.invokeLater(new Runnable(){
		public void run(){
			Reditor r = new Reditor();
			r.setVisible(true);
		}
	});
}
}

