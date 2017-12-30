package yeninesilarge.image.editor;

import yeninesilarge.image.ImagePanel;
import javax.swing.*;
import javax.swing.JFileChooser;
import java.awt.*;
import java.awt.image.WritableRaster;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.*;
import java.util.Set;
import java.util.HashSet;
import java.awt.event.*;
import java.util.Stack;

import yeninesilarge.application.*;

class UndoImage{
    WritableRaster R;
    BufferedImage bi;
}

public class ReditorApplication extends SimpleApplication{
    
public ReditorApplication(String n, String c, String t, String e) {
		super(n,c,t,e);
}
 
ImagePanel i = new ImagePanel();
ImagePanel iTemp = new ImagePanel();

//private final Set<Character> pressed = new HashSet<Character>();

JFileChooser fileChooser = new JFileChooser();

Stack<UndoImage> undo = new Stack<UndoImage>();
Stack<UndoImage> redo = new Stack<UndoImage>();

static BufferedImage BI;//current image we are worked on
static BufferedImage frame;//frame file that we will aplly to image

String filePath;

	public void run(File... f) {
		init();

		File file = f[0];
        try{
            filePath = file.getAbsolutePath();
            i.setImage(ImageIO.read(file));
            iTemp.setImage(ImageIO.read(file));
            BI = i.getImage();
            while(!undo.empty()){
                undo.pop();
            }
            while(!redo.empty()){
                redo.pop();
            }
		} catch(Exception ex ) { System.out.println("ex occured") ; }
		this.setTitle(title);
		this.setVisible(true);
	}

	public void init(int x, int y, int width, int height){
		super.init(x, y, width, height);

	}


private void init(){
    //Buttons 
    JButton btn1 = new JButton("GrayScale");
    btn1.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            GrayScaleButton(evt);
        }
    });
    
    JButton btn2 = new JButton("Vignette");
    btn2.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            VinyetButton(evt);
        }
    });
    JButton btn21 = new JButton("VignetteWhite");
    btn21.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            VinyetNegativeButton(evt);
        }
    });
    
    JButton btn3 = new JButton("RotateRigth");
    btn3.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            RotateRigthButton(evt);
        }
    });
    JButton btn5 = new JButton("RotateLeft");
    btn5.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            RotateLeftButton(evt);
        }
    });
    
    JButton btn4 = new JButton("Undo All Changes");
    btn4.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            UndoAllButton(evt);
        }
    });
    JButton btn6 = new JButton("OpenImage");
    btn6.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            OpenImageButton(evt);
        }
    });
    JButton btn7 = new JButton("Apply Frame");
    btn7.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            ApplyFrameButton(evt);
        }
    });
    JButton btn8 = new JButton("Select Frame");
    btn8.setToolTipText("Please note quality of frame that you'll select !");
    btn8.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            SelectFrameButton(evt);
        }
    });
    JButton btn9 = new JButton("BrightUp");
    btn9.setToolTipText("Brigthess up");
    btn9.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            BrigthUpButton(evt);
        }
    });
    JButton btn10 = new JButton("BrightDown");
    btn10.setToolTipText("Brigthness down");
    btn10.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            BrigthDownButton(evt);
        }
    });
    JButton btn11 = new JButton("Undo");
    btn11.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            UndoButton(evt);
        }
    });
    JButton btn12 = new JButton("Redo");
    btn12.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            RedoButton(evt);
        }
    });
    JButton btn13 = new JButton("Invert");
    btn13.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            InvertingColors(evt);
        }
    });
    JButton btn14 = new JButton("ColorFocusRed");
    btn14.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            ColorFocusButton(evt);
        }
    });
    JButton btn15 = new JButton("Save");
    btn15.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt){
            Save(evt);
        }
    });
    //Labels
    JLabel label1 = new JLabel("Brigthness");
    JLabel label2 = new JLabel("Filters");
    
    //Creating panels
    JPanel pane1 = new JPanel();
    pane1.setPreferredSize(new Dimension(800,50));
    JPanel pane3 = new JPanel();
    pane3.setPreferredSize(new Dimension(800,50));
    //
    i.setPreferredSize(new Dimension(1920,1080));
    i.setBackground(Color.white);
    //Create JScrollpane
    final JScrollPane scroll = new JScrollPane(i);
    
    //Creating layout pane1
    GroupLayout gl1 = new GroupLayout(pane1);
    pane1.setLayout(gl1);
    
    gl1.setAutoCreateContainerGaps(true);
    gl1.setAutoCreateGaps(true);
    
    gl1.setHorizontalGroup(gl1.createParallelGroup()
    .addGroup(gl1.createSequentialGroup()
        .addComponent(btn6)
        .addComponent(btn15)
        .addComponent(btn3)
        .addComponent(btn5)
        .addComponent(btn11)
        .addComponent(btn12)
        .addComponent(btn4)
        .addComponent(btn8)
        .addComponent(btn7))
    );
    
    gl1.setVerticalGroup(gl1.createSequentialGroup()
    .addGroup(gl1.createParallelGroup()
        .addComponent(btn6)
        .addComponent(btn15)
        .addComponent(btn3)
        .addComponent(btn5)
        .addComponent(btn11)
        .addComponent(btn12)
        .addComponent(btn4)
        .addComponent(btn8)
        .addComponent(btn7))
    );
    //Creating layout pane3
    GroupLayout gl3 = new GroupLayout(pane3);
    pane3.setLayout(gl3);
    gl3.setAutoCreateContainerGaps(true);
    gl3.setAutoCreateGaps(true);
    
    gl3.setHorizontalGroup(gl3.createParallelGroup()
    .addGroup(gl3.createSequentialGroup()
        .addComponent(label1)
        .addComponent(btn9)
        .addComponent(btn10)
        .addComponent(label2)
        .addComponent(btn1)
        .addComponent(btn2)
        .addComponent(btn21)
        .addComponent(btn13)
        .addComponent(btn14))
    );
    
    gl3.setVerticalGroup(gl3.createSequentialGroup()
    .addGroup(gl3.createParallelGroup()
        .addComponent(label1)
        .addComponent(btn9)
        .addComponent(btn10)
        .addComponent(label2)
        .addComponent(btn1)
        .addComponent(btn2)
        .addComponent(btn21)
        .addComponent(btn13)
        .addComponent(btn14))
    );
    
    //Adding panels to JFrame
    add(pane1,BorderLayout.NORTH);
    add(scroll,BorderLayout.CENTER);
    add(pane3,BorderLayout.SOUTH);
   
    
    setTitle(this.title);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setMinimumSize(new Dimension(300,300));
    setLocationRelativeTo(null);
    setFocusTraversalKeysEnabled(false);
	super.init(0,0,0,0);
	pack();
}

private void GrayScaleButton(java.awt.event.ActionEvent evt){
    BI = grayScaled();
    i.setImage(BI);
}
private void VinyetButton(java.awt.event.ActionEvent evt){
    BI = vinyet();
    i.setImage(BI);
}
private void VinyetNegativeButton(java.awt.event.ActionEvent evt){
    BI = vinyetNegative();
    i.setImage(BI);
}
private void RotateRigthButton(java.awt.event.ActionEvent evt){
    BI = RotateRigth();
    i.setImage(BI);
}
private void UndoAllButton(java.awt.event.ActionEvent evt){
    if(BI != null){
        WritableRaster R = iTemp.getImage().getRaster();
        int h = R.getHeight(),w = R.getWidth();
        BufferedImage image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        image.setData(R);
        BI = image;
        i.setImage(BI);
        while(!undo.empty()){
            undo.pop();
        }
        while(!redo.empty()){
            redo.pop();
        }
    }
}
private void RotateLeftButton(java.awt.event.ActionEvent evt){
    BI = RotateLeft();
    i.setImage(BI);
}
private void OpenImageButton(java.awt.event.ActionEvent evt){
    //Reading file
    int returnVal = fileChooser.showOpenDialog(this);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        if(getFileExt(file).equals("jpg")){
        try{
            filePath = file.getAbsolutePath();
            i.setImage(ImageIO.read(file));
            iTemp.setImage(ImageIO.read(file));
            BI = i.getImage();
            while(!undo.empty()){
                undo.pop();
            }
            while(!redo.empty()){
                redo.pop();
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error while reading file");
        }}else{
        JOptionPane.showMessageDialog(this,"Please select a 'jpg' file.");
        }
    }
    //pack(); // Same job for refreshing image pane
    i.updateUI();
}
private void ApplyFrameButton(java.awt.event.ActionEvent evt){
    //Adding frame
    if(BI!=null&&frame!=null){
        frame = scale();
        WritableRaster R = BI.getRaster();
        UndoImage ui = new UndoImage();
        ui.R=R;ui.bi=BI;
        undo.push(ui);
        WritableRaster RF = frame.getRaster();
        int[] rgb = {0,0,0};
        int w = BI.getWidth(), h = BI.getHeight();
        for(int x=0;x<w;x++){
            for(int y=0;y<h;y++){
                RF.getPixel(x,y,rgb);
                if(rgb[0]<255&&rgb[1]<255&&rgb[2]<255){
                    RF.getPixel(x,y,rgb);
                    R.setPixel(x,y,rgb);
                }
            }
        }
        i.setImage(BI);
    }
}
private void SelectFrameButton(java.awt.event.ActionEvent evt){
    //Select frame
    //Reading file
    JOptionPane.showMessageDialog(this,"Please note quality of frame that you'll select !");
    int returnVal = fileChooser.showOpenDialog(this);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        if(getFileExt(file).equals("jpg")){
        try{
            frame = ImageIO.read(file);
        }catch(Exception e){
            System.out.println("Error while reading file");
        }}else{
        JOptionPane.showMessageDialog(this,"Please select a 'jpg' file.");
        }
    }
}
private void BrigthUpButton(java.awt.event.ActionEvent evt){
    double con = 1.2;
    BI = brightness(con);
    i.setImage(BI);
}
private void BrigthDownButton(java.awt.event.ActionEvent evt){
    double con = 0.8;
    BI = brightness(con);
    i.setImage(BI);
}
private void UndoButton(java.awt.event.ActionEvent evt){
    if(!undo.empty()){
        UndoImage ui = new UndoImage();
        UndoImage ui2 = new UndoImage();
        ui2.bi = BI;
        ui2.R = BI.getRaster();
        redo.push(ui2);
        ui = undo.pop();
        BufferedImage image = new BufferedImage(ui.bi.getWidth(),ui.bi.getHeight(),BufferedImage.TYPE_INT_RGB);
        image.setData(ui.R);
        BI = image;
        i.setImage(BI);
    }else{
        JOptionPane.showMessageDialog(this,"Nothing to undo !");
    }
}
private void RedoButton(java.awt.event.ActionEvent evt){
    if(!redo.empty()){
        UndoImage ui = new UndoImage();
        UndoImage ui2 = new UndoImage();
        ui2.bi = BI;
        ui2.R = BI.getRaster();
        undo.push(ui2);
        ui = redo.pop();
        BufferedImage image = new BufferedImage(ui.bi.getWidth(),ui.bi.getHeight(),BufferedImage.TYPE_INT_RGB);
        image.setData(ui.R);
        BI = image;
        i.setImage(BI);
    }else{
        JOptionPane.showMessageDialog(this,"Nothing to redo");
    }
}
private void InvertingColors(java.awt.event.ActionEvent evt){
    BI = Invert();
    i.setImage(BI);
}
private void ColorFocusButton(java.awt.event.ActionEvent evt){
    if(BI != null){
    WritableRaster R = BI.getRaster();
    UndoImage ui = new UndoImage();
    ui.R=R;ui.bi=BI;
    undo.push(ui);
    int m;
    int[] rgb ={0,0,0};
    int w = BI.getWidth(), h = BI.getHeight();
    for(int x=0;x<w;x++){
        for(int y=0;y<h;y++){
                R.getPixel(x,y,rgb);
                if(!control(rgb)){
                m = (rgb[0]+rgb[1]+rgb[2])/3;
                rgb[0] = m;
                rgb[1] = m;
                rgb[2] = m;
                R.setPixel(x,y,rgb);
                }
            }
        }
    i.setImage(BI);
    }else{
        JOptionPane.showMessageDialog(this,"Please open a 'jpg' file.");
    }
}
private void Save(java.awt.event.ActionEvent evt){
    if(BI != null){
        try{
            ImageIO.write(BI,"jpg",new File(filePath));
            JOptionPane.showMessageDialog(this,"Saved Succesfully !");
        } catch (IOException e){}
    }else{
        JOptionPane.showMessageDialog(this,"Please open a 'jpg' file.");
    }
}
private BufferedImage Invert(){
    if(BI != null){
    WritableRaster WRcur = BI.getRaster();
    UndoImage ui = new UndoImage();
    ui.R=WRcur;ui.bi=BI;
    undo.push(ui);
    int[] rgb ={0,0,0};
    int w = BI.getWidth(), h = BI.getHeight();
    BufferedImage image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
    WritableRaster WRnew = image.getRaster();
    for(int x=0;x<w;x++){
        for(int y=0;y<h;y++){
            WRcur.getPixel(x,y,rgb);
            rgb[0] = 255-rgb[0];
            rgb[1] = 255-rgb[1];
            rgb[2] = 255-rgb[2];
            WRnew.setPixel(x,y,rgb);
        }
    }
    image.setData(WRnew);
    return image;
    }else{
        JOptionPane.showMessageDialog(this,"Please open a 'jpg' file.");
        return null;
    }
}
private BufferedImage grayScaled(){
    if(BI != null){
    WritableRaster WRcur = BI.getRaster();
    UndoImage ui = new UndoImage();
    ui.R=WRcur;ui.bi=BI;
    undo.push(ui);
    int m;
    int[] rgb ={0,0,0};
    int w = BI.getWidth(), h = BI.getHeight();
    BufferedImage image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
    WritableRaster WRnew = image.getRaster();
    for(int x=0;x<w;x++){
        for(int y=0;y<h;y++){
            WRcur.getPixel(x,y,rgb);
            m = (rgb[0]+rgb[1]+rgb[2])/3;
            rgb[0] = m;rgb[1] = m;rgb[2] = m;
            WRnew.setPixel(x,y,rgb);
        }
    }
    image.setData(WRnew);
    return image;
    }else{
        JOptionPane.showMessageDialog(this,"Please open a 'jpg' file.");
        return null;
    }
}
private BufferedImage brightness(double con){
    if(BI != null){
    WritableRaster WRcur = BI.getRaster();
    UndoImage ui = new UndoImage();
    ui.R=WRcur;ui.bi=BI;
    undo.push(ui);
    int[] rgb ={0,0,0};
    int w = BI.getWidth(), h = BI.getHeight();
    BufferedImage image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
    WritableRaster WRnew = image.getRaster();
    for(int x=0;x<w;x++){
        for(int y=0;y<h;y++){
            WRcur.getPixel(x,y,rgb);
            rgb[0] = (int) (rgb[0] * con);
            rgb[1] = (int) (rgb[1] * con);
            rgb[2] = (int) (rgb[2] * con);
            if(rgb[0]>254){rgb[0] = 255;}
            if(rgb[1]>254){rgb[1] = 255;}
            if(rgb[2]>254){rgb[2] = 255;}
            WRnew.setPixel(x,y,rgb);
        }
    }
    image.setData(WRnew);
    return image;
}
return null;
}
private BufferedImage vinyet(){
    if(BI != null){
    WritableRaster WRcur = BI.getRaster();
    UndoImage ui = new UndoImage();
    ui.R=WRcur;ui.bi=BI;
    undo.push(ui);
    int[] rgb ={0,0,0};
    int w = BI.getWidth(), h = BI.getHeight();
    BufferedImage image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
    WritableRaster WRnew = image.getRaster();
    int midx = w/2 , midy = h/2 ;
    double con = 1;
    int dist = 0;
    int ort=0;
    int max = (int) getDist(midx-0,midy-0);
    for(int x = 0; x < w; x++ ){
        for(int y = 0; y < h; y++ ){
            dist = (int) getDist(midx-x,midy-y);
            WRcur.getPixel(x,y,rgb);
            con = 1-((double) dist/max);
            rgb[0] = (int) (rgb[0] * con);
            rgb[1] = (int) (rgb[1] * con);
            rgb[2] = (int) (rgb[2] * con);
            WRnew.setPixel(x,y,rgb);
            }
        }
        image.setData(WRnew);
        return image;
    }else{
        JOptionPane.showMessageDialog(this,"Please open a 'jpg' file.");
        return null;
    }
}
private BufferedImage vinyetNegative(){
    if(BI != null){
    WritableRaster WRcur = BI.getRaster();
    UndoImage ui = new UndoImage();
    ui.R=WRcur;ui.bi=BI;
    undo.push(ui);
    int[] rgb ={0,0,0};
    int w = BI.getWidth(), h = BI.getHeight();
    BufferedImage image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
    WritableRaster WRnew = image.getRaster();
    int midx = w/2 , midy = h/2 ;
    double con = 1;
    int dist = 0;
    int ort=0;
    int max = (int) getDist(midx-0,midy-0);
    for(int x=0;x<w;x++){//inverting colors for negative vignette effect
        for(int y=0;y<h;y++){
            WRcur.getPixel(x,y,rgb);
            rgb[0] = 255-rgb[0];
            rgb[1] = 255-rgb[1];
            rgb[2] = 255-rgb[2];
            WRnew.setPixel(x,y,rgb);
        }
    }
    image.setData(WRnew);
    for(int x = 0; x < w; x++ ){
        for(int y = 0; y < h; y++ ){
            dist = (int) getDist(midx-x,midy-y);
            WRnew.getPixel(x,y,rgb);
            con = 1-((double) dist/max);
            rgb[0] = 255-(int) (rgb[0] * con);
            rgb[1] = 255-(int) (rgb[1] * con);
            rgb[2] = 255-(int) (rgb[2] * con);
            WRnew.setPixel(x,y,rgb);
            }
        }
        image.setData(WRnew);
        return image;
    }else{
        JOptionPane.showMessageDialog(this,"Please open a 'jpg' file.");
        return null;
    }
}
private BufferedImage RotateRigth(){
    if(BI != null){
    WritableRaster WRcur = BI.getRaster();
    UndoImage ui = new UndoImage();
    ui.R=WRcur;ui.bi=BI;
    undo.push(ui);
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
    UndoImage ui = new UndoImage();
    ui.R=WRcur;ui.bi=BI;
    undo.push(ui);
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
private BufferedImage scale(){
    //Scaling the frame for applying valid ratio.
    BufferedImage resized = null;
    if(frame != null){
        resized = new BufferedImage(BI.getWidth(),BI.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resized.createGraphics();
        g.drawImage(frame,0,0,BI.getWidth(),BI.getHeight(),null);
        g.dispose();
    }
    return resized;
}
private boolean bound(int arr[]){
    if(arr[0]>255||arr[1]>255||arr[2]>255){return false;}
    if(arr[0]<0||arr[1]<0||arr[2]<0){return false;}
    return true;
}
private boolean control(int arr[]){
    double BGort = (double) (arr[1]+arr[2])/2;
    if(arr[1]<arr[2]-10&&arr[1]>arr[2]+10){return false;}
    if(arr[0]<BGort*(2.6)){return false;}
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
           // ReditorApplication r = new ReditorApplication();
            //r.setVisible(true);
        }
    });
}
}
