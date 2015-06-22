package com.buscafacil.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.androidrepository.util.MetaDataHelper;
import com.androidrepository.util.MetaDataHelper.MetaDataName;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class BFDBHelper extends SQLiteOpenHelper {

    private final String DB_NAME;
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private final String connectionString;
 
    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     * @throws IOException 
     */
    public BFDBHelper(Context context) throws IOException {    	
    	super(context, getMetaDataDatabaseNameOrDefault(context), null, getMetaDataDatabaseVersionOrDefault(context));
    	this.myContext = context;
    	connectionString = getMetaDataDatabasePathOrDefault(context) + getMetaDataDatabaseNameOrDefault(context);
    	DB_NAME = getMetaDataDatabaseNameOrDefault(context);
    	
    	try {
			createDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	
 
  /**
     * Creates a empty database on the system and rewrites it with your own database.
 * @throws Exception 
     * */
    public void createDataBase() throws Exception{ 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		//do nothing - database already exist
    	}
    	else{ 
    		//By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
 
        	try { 
    			copyDataBase(); 
    		} 
        	catch (IOException e) { 
        		throw new Error("Error copying database"); 
        	}
    	} 
    }
 
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){ 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		checkDB = SQLiteDatabase.openDatabase(connectionString, null, SQLiteDatabase.OPEN_READONLY); 
    	}
    	catch(SQLiteException e){ 
    		//database does't exist yet. 
    	}
 
    	if(checkDB != null){ 
    		checkDB.close(); 
    	}
 
    	return checkDB != null ? true : false;
    }
 
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * @throws Exception 
     * */
    private void copyDataBase() throws Exception{ 
    	try {
    		//Open your local db as the input stream
        	InputStream myInput = myContext.getAssets().open(DB_NAME);
     
        	// Path to the just created empty db
        	//Open the empty db as the output stream
        	OutputStream myOutput = new FileOutputStream(connectionString);
     
        	//transfer bytes from the inputfile to the outputfile
        	byte[] buffer = new byte[1024];
        	int length;
        	while ((length = myInput.read(buffer))>0){
        		myOutput.write(buffer, 0, length);
        	}
     
        	//Close the streams
        	myOutput.flush();
        	myOutput.close();
        	myInput.close();
    	}
    	catch(Exception ex) {
    		throw ex;
    	}    	 
    }
 
    public void openDataBase() throws SQLException{ 
    	//Open the database
    	myDataBase = SQLiteDatabase.openDatabase(connectionString, null, SQLiteDatabase.OPEN_READONLY); 
    }
    
    public SQLiteDatabase getDataBase() {
    	return myDataBase;
    }
 
    @Override
	public synchronized void close() { 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    super.close(); 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}
 
        // Add your public helper methods to access and get content from the database.
       // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
       // to you to create adapters for your views.
	
	//===============================================================================
	// PRIVATE METHODS
	//===============================================================================
	
	// Meta-data methods
	/**
	 * 
	 * @return
	 */
	private static String getMetaDataDatabaseNameOrDefault(Context context) {
		String aaName = MetaDataHelper.getMetaData(context, MetaDataName.DATABASE_NAME);
		if (aaName == null) {
			aaName = "Data.db";
		}
	
		return aaName;
	}
	/**
	 * 
	 * @param context
	 * @return
	 */
	private static String getMetaDataDatabasePathOrDefault(Context context) {
		String databasePath = MetaDataHelper.getMetaData(context, MetaDataName.DATABASE_PATH);
		if (databasePath == null) {
			databasePath = "/data/data/com.buscafacil/databases/";
		}
	
		return databasePath;
	}
	/**
	 * 
	 * @return
	 */
	private static int getMetaDataDatabaseVersionOrDefault(Context context) {
		Integer aaVersion = MetaDataHelper.getMetaData(context, MetaDataName.DATABASE_VERSION);
		if (aaVersion == null || aaVersion == 0) {
			aaVersion = 1;
		}
	
		return aaVersion;
	}
}