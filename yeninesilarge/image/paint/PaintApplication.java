package yeninesilarge.image.paint;

import yeninesilarge.application.SimpleApplication;

import javax.swing.JFrame;
import java.util.Set;
import java.io.File;

import java.awt.event.KeyListener;

public class PaintApplication extends SimpleApplication {

	public String name; // application name
	public String classpath; //package name
	public String title; // title
	public Set<String> extensions; // extensions

	public PaintApplication(String n, String c, String t, String e) {
		super(n,c,t,e);
	} 

	public void run(File... f) {
		PaintPanel panel = new PaintPanel(f[0]); // only a picture at a time.

		this.setContentPane(panel);

		this.setFocusable(true); // needs by key listener
		this.setFocusTraversalKeysEnabled(false);
		this.addKeyListener( (KeyListener) panel); // key listener

		this.setVisible(true);
	}

	public void init(int x, int y, int width, int height){
		super.init(x, y, width, height);

	}

	@Override
	public String toString(){
		return name;
	}
}
