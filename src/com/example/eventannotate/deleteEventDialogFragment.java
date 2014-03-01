package com.example.eventannotate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class deleteEventDialogFragment extends DialogFragment{
	
	private static final String LOG_DIRECTORY_PATH = "/EventAnnotation";
	private static final String LOG_FILE_NAME = "usage.log";
	File dir;
	File file;
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        dir = new File (Environment.getExternalStorageDirectory().getAbsolutePath() + LOG_DIRECTORY_PATH);
        file = new File(dir, LOG_FILE_NAME);
	    final List<String> lines = new LinkedList<String>();
	    Scanner reader = null;
		try {
			reader = new Scanner(new FileInputStream(file), "UTF-8");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Log.v("file", "cant find");
		}
	    while(reader.hasNextLine())
	        lines.add(reader.nextLine());
	    reader.close();
        Log.e("line number",Integer.toString(lines.size()));
        
        String dialogMessage;
        if (lines.size() > 0)
        {
        	dialogMessage = "Delete **" + lines.get(lines.size() - 1) + "**?";
        }
        else
        {
        	dialogMessage = "Sorry there is no old log";
        }
        
        builder.setMessage(dialogMessage)
        //builder.setMessage(R.string.dialog_fire_missiles)
               .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // FIRE ZE MISSILES!
                	   //File dir = new File (Environment.getExternalStorageDirectory().getAbsolutePath() + LOG_DIRECTORY_PATH);
                	   Context context = getActivity().getApplicationContext();
                	   
                	   int duration = Toast.LENGTH_SHORT;


                	   try 
                	   {
                	        if (lines.size() > 0)
                	        {
                	        	CharSequence text = lines.get(lines.size() - 1) + "  ** GONE!";
                	        	removeLine(file);
                         	   	Toast toast = Toast.makeText(context, text, duration);
                         	   	toast.show();
                	        }
                	        else
                	        {
                	        	CharSequence text = "Did nothing!";
                          	   	Toast toast = Toast.makeText(context, text, duration);
                          	   	toast.show();
                	        }
                		   
                		   
                	   } catch (IOException e) {
                		   // TODO Auto-generated catch block
                		   e.printStackTrace();
                	   }
                   }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
	
	public void removeLine(final File file) throws IOException{
	    final List<String> lines = new LinkedList<String>();
	    final Scanner reader = new Scanner(new FileInputStream(file), "UTF-8");
	    while(reader.hasNextLine())
	        lines.add(reader.nextLine());
	    reader.close();
	    //assert lineIndex >= 0 && lineIndex <= lines.size() - 1;
	    lines.remove(lines.size() - 1);
	    final BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
	    for(final String line : lines)
	        writer.write(line + "\r\n");
	    writer.flush();
	    writer.close();
	}
}
