package com.buscafacil.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.buscafacil.data.BFDBHelper;
import com.buscafacil.domain.*;

public class CoordenadaDao {

	private BFDBHelper _dbHelper;
	
	//private final static String LOGCAT = "Common";
	public CoordenadaDao(Context context) {
		try {
			_dbHelper = new BFDBHelper(context);
		} 
		catch (IOException e) {
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
	public List<Coordenada> getAll(Negocio negocio) throws Exception {
		try {
			List<Coordenada> coordenadas = new ArrayList<Coordenada>();
			
			_dbHelper.openDataBase(); 
        	SQLiteDatabase db = _dbHelper.getDataBase();
        	
        	Cursor cursor = db.rawQuery("SELECT _id, latitud, longitud, negocioid FROM Coordenadas WHERE negocioid = ?", 
        			new String[] { Long.toString(negocio.id) });
			
			cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	Coordenada coordenada = new Coordenada();
		    	coordenada.id = cursor.getLong(0);
		    	coordenada.latitud = cursor.getString(1);
		    	coordenada.longitud = cursor.getString(2);
		    	coordenada.negocio = new Negocio();
		    	
		    	coordenadas.add(coordenada);		    	
		    	cursor.moveToNext();
		    }
		    cursor.close();
		    db.close();
		    _dbHelper.close();
		    
		    return coordenadas;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * 
	 * @param negocio
	 * @return
	 * @throws Exception 
	 */
	public Coordenada get(Negocio negocio) throws Exception {
		try {
			Coordenada coordenada = new Coordenada();
			
			_dbHelper.openDataBase(); 
        	SQLiteDatabase db = _dbHelper.getDataBase();
        	
        	Cursor cursor = db.rawQuery("SELECT _id, latitud, longitud, negocioid FROM Coordenadas", null);
			
			cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	coordenada.id = cursor.getLong(0);
		    	coordenada.latitud = cursor.getString(1);
		    	coordenada.longitud = cursor.getString(2);
		    	coordenada.negocio = new Negocio();

		    	cursor.moveToNext();
		    }
		    cursor.close();
		    db.close();
		    _dbHelper.close();
		    
		    return coordenada;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
}
