package com.buscafacil.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.buscafacil.data.BFDBHelper;
import com.buscafacil.domain.*;


public class TelefonoDao {

	private BFDBHelper _dbHelper;
	
	//private final static String LOGCAT = "Common";
	public TelefonoDao(Context context) {
		try {
			_dbHelper = new BFDBHelper(context);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * 
	 * @param negocio
	 * @return
	 * @throws Exception 
	 */
	public List<Telefono> getAll(Negocio negocio) throws Exception {
		try {
			List<Telefono> telefonos = new ArrayList<Telefono>();
			
			_dbHelper.openDataBase(); 
        	SQLiteDatabase db = _dbHelper.getDataBase();
        	
        	Cursor cursor = db.rawQuery("SELECT _id, numero, negocioid FROM Telefonos WHERE negocioid = ?", 
        			new String[] { Long.toString(negocio.id) });
			
			cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	Telefono telefono = new Telefono();
		    	telefono.id = cursor.getLong(0);
		    	telefono.numero = cursor.getString(1);
		    	telefono.negocio = new Negocio();
		    	
		    	telefonos.add(telefono);		    	
		    	cursor.moveToNext();
		    }
		    cursor.close();
		    db.close();
		    _dbHelper.close();
		    
		    return telefonos;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
}
