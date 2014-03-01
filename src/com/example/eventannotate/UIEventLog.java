package com.example.eventannotate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.Environment;
import android.util.Log;

public class UIEventLog {

	private static final String LOG_DIRECTORY_PATH = "/EventAnnotation";
	private static final String LOG_FILE_NAME = "usage.log";
	private static boolean LOG_DEBUG = false;
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS: ");
	
	private static PrintStream log = null;
	
	private static void startSession() {
		File dir = new File (Environment.getExternalStorageDirectory().getAbsolutePath() + LOG_DIRECTORY_PATH);
		Log.v("path",Environment.getExternalStorageDirectory().getAbsolutePath() + LOG_DIRECTORY_PATH);
		dir.mkdirs();
//		File file = new File(dir, "s"+System.currentTimeMillis()+".log");
		File file = new File(dir, LOG_FILE_NAME);

		try {
			log = new PrintStream(new FileOutputStream(file, true));
			debug("***** UIEventLog.startSession *****");
		} catch (FileNotFoundException e) {
			Log.e("UIEventLog", e.getMessage());
			log = null;
		}
	}
	
	public static synchronized void put(String msg) {
		Date now = new Date();
		if(log==null) {
			startSession();
		}
		if(log==null)
			return;
		Log.i("UIEventLog", msg);
		log.print(DATE_FORMAT.format(now));
		log.print(msg);
		log.print("\r\n");
		log.flush();
	}
	
	public static void debug(String msg) {
		if(LOG_DEBUG)
			put("*DEBUG* "+msg);
	}
}