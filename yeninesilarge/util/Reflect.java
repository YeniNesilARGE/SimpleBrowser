package yeninesilarge.util;

import java.lang.reflect.Constructor;


public class Reflect {

	public static Class getClass(String pack, String name){
		Class c=null;
		try{
			c = Class.forName(pack + "." + name);
		} catch(ClassNotFoundException ex) {
			
		}
		return c;
	}

	public static Package getPackage(Object o){
		Package p = o.getClass().getPackage();
		return p;
	}

	public static Constructor getConstructor(Class c, Class... parameterTypes) {
		Constructor constructor = null;
		try{
			constructor = c.getDeclaredConstructor(parameterTypes);
		} catch(NoSuchMethodException ex) {

		}
		return constructor;
	}

	public static Object newInstance(Constructor c, Object... initArgs) {
		Object o = null;
		try{
			o = c.newInstance(initArgs);
		} catch(Exception ex) {
			/*	
				throws InstantiationException,
				IllegalAccessException,
				IllegalArgumentException,
				InvocationTargetException
			*/
			ex.printStackTrace();
		} 
		
		return o;
	}
}
