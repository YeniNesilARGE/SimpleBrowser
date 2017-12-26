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
import java.io.FilenameFilter;

import javax.swing.*;

public class Browser extends JFrame {

	public static void main(String... args){

		ApplicationManager appM = ApplicationManager.getInstance();
		Browser browser = new Browser();


		File f = new File("/home/bilalekrem/Desktop/SimpleBrowser/yeninesilarge/images/dog.jpg");


		browser.loadApplications();
/*		String extension = browser.getExtension(f);		
		app = appManager.getApplications(extension).get(0);
		app.init(50, 50, 900, 900);
		app.run(f);*/


	}

	String getExtension(File f) {
		if ( f.isDirectory() ) return null;

		String extension = null;;
		String fileName = f.getName();
		int i = fileName.lastIndexOf('.');
		if (i >= 0) {
			cdextension = fileName.substring(i+1);
		}
		return extension;
	}

	void loadApplications() {
		int counter = 0;
		File yna = new File(ApplicationManager.USER_HOME, ApplicationManager.DIR );
		if( !yna.exists() )  return;

		FilenameFilter textFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(ApplicationManager.EXT)) {
					return true;
				} else {
					return false;
				}
			}
		};

		ApplicationManager appManager = ApplicationManager.getInstance();
		for (File file : yna.listFiles() ) {
			if (!file.isDirectory()) { 
				String applicationName = file.getName();
				SimpleApplication app = appManager.load(applicationName);
				appManager.registerApplication(app);
			}
		}

		
				

	}

}


