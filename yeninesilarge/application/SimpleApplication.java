package yeninesilarge.application;

import javax.swing.JFrame;
import java.util.Set;
import java.util.HashSet;
import java.io.File;

public abstract class SimpleApplication extends JFrame{

	public String name; // application name
	public String classpath; //package name
	public String title; // title
	public Set<String> extensions; // extensions

	//contract
	public SimpleApplication(String n, String c, String t, String e) {
		name = n;
		classpath = c;
		title = t;

		extensions = new HashSet<>();
		String[] exts = e.split(",");
		for(String extension : exts){
			extensions.add(extension);
		}
	}

	abstract public void run(File... f);
	
	public void init(int x, int y, int width, int height) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		this.setBounds(x, y, width, height);
	}

	public String setToStringExtensions(){
		if(extensions == null || extensions.isEmpty()) return null;
		String ext="";
		for( String e : extensions ) {
			ext = e + "," + ext;
		}
		if ( ext.charAt( ext.length() - 1) == ',' ) //if last char is comma, remove it.
			ext = ext.substring(0, ext.length() - 1);
		return ext;
	}

	@Override
	public String toString(){
		return name;
	}
}
