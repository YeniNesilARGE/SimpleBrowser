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

public class Browser{

	static String getExtension(File f) {
		if ( f.isDirectory() ) return null;

		String fileName = f.getName();
		
		return getExtension(fileName);
	}

	static String getExtension(String f) {
		String extension = "";
		int i = f.lastIndexOf('.');
		if (i >= 0) {
			extension = f.substring(i+1);
		}
		return extension.toLowerCase();
	}

	static String getFileName(String fileName) { // input with extension A.txt -> A
		int i = fileName.lastIndexOf('.');
		if (i >= 0) {
			fileName = fileName.substring(0, i);
		}
		return fileName;
	}

	static void loadApplications() {
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
			if (!file.isDirectory() && ApplicationManager.EXT.contains(getExtension(file)) ) { 
				String fileName = file.getName();
				String applicationName = getFileName(fileName);
				SimpleApplication app = null;
				try { 
					app = appManager.load(applicationName);
				} catch (Exception ex) {
					continue;
				} finally {
					if ( app == null ) continue;
				}
				
				appManager.registerApplication(app);
				System.out.println("loaded - " + app.name);
			}
		}
	}

}


