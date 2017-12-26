package yeninesilarge.brow;

import yeninesilarge.application.*;
import yeninesilarge.util.Reflect;
import java.lang.reflect.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Properties;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

public class Browser extends JFrame {

	public static void main(String... args){

		ApplicationManager appManager = new ApplicationManager();
		Browser browser = new Browser();

		SimpleApplication app = appManager.load("Paint");
		
		appManager.createAssociation(app);

		File f = new File("/home/bilalekrem/Desktop/SimpleBrowser/yeninesilarge/images/dog.jpg");

		String extension = browser.getExtension(f);
		
		app = appManager.getApplications(extension).get(0);
		app.init(50, 50, 900, 900);
		app.run(f);

		//buildFrame(paintPanel.title, panel, 300, 300, 800, 800);

		browser.pack();
		browser.setVisible(true);
	}


	String getExtension(File f) {
		if ( f.isDirectory() ) return null;

		String extension = null;;
		String fileName = f.getName();
		int i = fileName.lastIndexOf('.');
		if (i >= 0) {
			extension = fileName.substring(i+1);
		}
		return extension;
	}

}


