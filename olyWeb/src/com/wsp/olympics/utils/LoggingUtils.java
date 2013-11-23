package com.wsp.olympics.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingUtils {
	
	private static Logger logger = Logger.getAnonymousLogger();

	/**
	 * Logs a message to standard output
	 * 
	 * @param level the trace level at which this message should be logged. see {@link java.util.logging.Level} 
	 * @param msg the message to log
	 */
	public static void log(Level level, String msg) {
		logger.log(level, msg);
	}
}
