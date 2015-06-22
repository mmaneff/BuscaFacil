package com.buscafacil.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.buscafacil.data.BFDBHelper;
import com.buscafacil.domain.*;

public class SubCategoriaDao {

	private BFDBHelper _dbHelper;
	
	//private final static String LOGCAT = "Common";
	public SubCategoriaDao(Context context) {
		try {
			_dbHelper = new BFDBHelper(context);
		} catch (IOException e) {
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
	public SubCategoria get(long id) throws Exception {
		try {
			SubCategoria subcategoria = new SubCategoria();
			
			_dbHelper.openDataBase(); 
        	SQLiteDatabase db = _dbHelper.getDataBase();
        	
        	Cursor cursor = db.rawQuery("SELECT _id, Nombre, CategoriaId FROM SubCategorias WHERE _id = ? ", 
        			new String[] { Long.toString(id) });
			
			cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	subcategoria.id = cursor.getLong(0);
		    	subcategoria.nombre = cursor.getString(1);
		    	subcategoria.categoria = null;

		    	cursor.moveToNext();
		    }
		    cursor.close();
		    db.close();
		    _dbHelper.close();
		    
		    return subcategoria;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * 
	 * @param categoria
	 * @return
	 * @throws Exception 
	 */
	public List<SubCategoria> getAll(Categoria categoria) throws Exception {
		try {
			List<SubCategoria> subcategorias = new ArrayList<SubCategoria>();
			
			_dbHelper.openDataBase(); 
        	SQLiteDatabase db = _dbHelper.getDataBase();
        	
        	Cursor cursor = db.rawQuery("SELECT _id, Nombre, CategoriaId FROM SubCategorias WHERE categoriaid = ? ORDER BY nombre", 
        			new String[] { Long.toString(categoria.id) });
			
			cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	SubCategoria subcategoria = new SubCategoria();
		    	subcategoria.id = cursor.getLong(0);
		    	subcategoria.nombre = cursor.getString(1);
		    	subcategoria.categoria = null;
		    	
		    	subcategorias.add(subcategoria);		    	
		    	cursor.moveToNext();
		    }
		    cursor.close();
		    db.close();
		    _dbHelper.close();
		    
		    return subcategorias;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
}
