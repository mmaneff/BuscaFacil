package com.buscafacil.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.buscafacil.data.BFDBHelper;
import com.buscafacil.domain.*;


public class CategoriaDao  {

	private BFDBHelper _dbHelper;
	
	//private final static String LOGCAT = "Common";
	public CategoriaDao(Context context) {
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
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public Categoria get(long id) throws Exception {
		try {
			Categoria categoria = new Categoria();
			
			_dbHelper.openDataBase(); 
        	SQLiteDatabase db = _dbHelper.getDataBase();
        	
        	Cursor cursor = db.rawQuery("SELECT _id, Nombre, Imagen, Color FROM Categorias WHERE _id = ?", 
        			new String[] { Long.toString(id) });
			
			cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	categoria.id = cursor.getLong(0);
		    	categoria.nombre = cursor.getString(1);
		    	categoria.imagen = cursor.getString(2);
		    	categoria.color = cursor.getString(3);

		    	cursor.moveToNext();
		    }
		    cursor.close();
		    db.close();
		    _dbHelper.close();
		    
		    return categoria;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception 
	 */
	public List<Categoria> getAll() throws Exception {
		try {
			List<Categoria> categorias = new ArrayList<Categoria>();
			
			_dbHelper.openDataBase(); 
        	SQLiteDatabase db = _dbHelper.getDataBase();
        	
        	Cursor cursor = db.rawQuery("SELECT _id, Nombre, Imagen, Color FROM Categorias ORDER BY nombre", null);
			
			cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	Categoria categoria = new Categoria();
		    	categoria.id = cursor.getLong(0);
		    	categoria.nombre = cursor.getString(1);
		    	categoria.imagen = cursor.getString(2);
		    	categoria.color = cursor.getString(3);
		    	
		    	categorias.add(categoria);		    	
		    	cursor.moveToNext();
		    }
		    cursor.close();
		    db.close();
		    _dbHelper.close();
		    
		    return categorias;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
}
