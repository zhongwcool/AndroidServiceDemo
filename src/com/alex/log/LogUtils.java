/**
 * 
 */
package com.alex.log;

/**
 * @author alex
 *
 */
public abstract class LogUtils {
	
	public final static String makeLogTag(Class<?> cls){
		return "a_" + cls.getSimpleName();
	}

}
