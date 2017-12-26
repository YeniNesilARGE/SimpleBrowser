package yeninesilarge.application;

import yeninesilarge.util.Reflect;
import java.lang.reflect.*;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Properties;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ApplicationManager extends Properties {

	public static final File USER_HOME = new File(System.getProperty("user.home"));
	public static final String DIR = "yna";
	public static final String EXT = ".app";

	private static final String PROP_NAME = "name", PROP_CLASSPATH = "classpath",
						 		PROP_TITLE = "title", PROP_EXTENSIONS = "extensions";

	public Map<String, List<SimpleApplication>> extManager;
	public Map<String, SimpleApplication> applications;

	private static ApplicationManager singleton = null;

	private ApplicationManager(){
		extManager = new HashMap<>();
		applications = new HashMap<>();
	}

	public static ApplicationManager getInstance() {
		if ( singleton == null ) 
			singleton = new ApplicationManager();
	
		return singleton;
	}

	public List<SimpleApplication> getApplications(String extension) throws ExtensionNotDefinedException {
		List<SimpleApplication> apps = extManager.get(extension);
		if ( apps == null ) {
			throw new ExtensionNotDefinedException("Extension could not find " + extension);
		}
		return apps;
	}

	
	public void createAssociation(String extension, SimpleApplication app){
		extension = extension.toLowerCase();
		List<SimpleApplication> apps = extManager.get(extension);
		if ( apps == null ) {
			apps = new ArrayList<>();
			extManager.put(extension, apps);
		}
		
		apps.add(app);
	}

	public void registerApplication(SimpleApplication app) {
		for ( String extension : app.extensions ) {
			createAssociation(extension, app);
		}

		applications.put(app.name, app);
	}

	public SimpleApplication getApplication(String name) {
		
	}

	// return result
	public boolean store(String name, String classpath, String title, String extensions){
		File yna = new File(USER_HOME, DIR);
		if( !yna.exists() ) yna.mkdir();
		
		File application = new File(yna, name + EXT ); //AppName.app
		
		Properties properties = new Properties();
		properties.setProperty(PROP_NAME, name );
		properties.setProperty(PROP_CLASSPATH, classpath );
		properties.setProperty(PROP_TITLE, title );
		properties.setProperty(PROP_EXTENSIONS, extensions );

		OutputStream out = null;
		boolean result;
		try {
			out = new FileOutputStream(application);
			properties.store(out, "SimpleBrowser Application - " + name);
			out.close();
			result = true;
		} catch (IOException ex ) {
			ex.printStackTrace();
			result = false;
		} 

		return result;
	}

	public SimpleApplication load(String appName){
		SimpleApplication app = null;

		File yna = new File(USER_HOME, DIR);
		if( yna.exists() && yna.isDirectory() ){
			File application = new File(yna, appName + EXT ); //AppName.app
			InputStream in = null;
			Properties properties = null;
			try {
				in = new FileInputStream( application);
				properties = new Properties();
				properties.load(in);
				in.close();
			} catch (FileNotFoundException ex ) {
				System.err.println("Application could'not find " + ex.getMessage() );
			}catch (IOException ex ) {
				ex.printStackTrace();
			} 
			
			if( properties != null ) {
				String name = properties.getProperty(PROP_NAME);
				String title = properties.getProperty(PROP_TITLE);
				String classpath = properties.getProperty(PROP_CLASSPATH);
				String extensions = properties.getProperty(PROP_EXTENSIONS);


				Class c = Reflect.getClass(classpath);
				Constructor constructor = Reflect.getConstructor(c, String.class, String.class, String.class, String.class);
				app = (SimpleApplication) Reflect.newInstance(constructor, name, classpath, classpath, extensions);

			}
		
		}
		return app;
	}

	class ExtensionNotDefinedException extends RuntimeException{
		ExtensionNotDefinedException(String message) {
			super(message);
		}
	}

}
