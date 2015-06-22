package com.buscafacil.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.buscafacil.data.BFDBHelper;
import com.buscafacil.domain.*;


public class NegocioDao {

	private BFDBHelper _dbHelper;
	
	//private final static String LOGCAT = "Common";
	public NegocioDao(Context context) {
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
	public Negocio get(long id) throws Exception {
		try {
			Negocio negocio = new Negocio();
			
			_dbHelper.openDataBase(); 
        	SQLiteDatabase db = _dbHelper.getDataBase();
        	
        	Cursor cursor = db.rawQuery("SELECT _id, nombrecorto, nombrelargo, imagen, detalle, direccion, horario, subcategoriaid FROM Negocios WHERE _id = ? ", 
        			new String[] { Long.toString(id) });
			
			cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	negocio.id = cursor.getLong(0);
		    	negocio.nombrecorto = cursor.getString(1);
		    	negocio.nombrelargo = cursor.getString(2);
		    	negocio.imagen = cursor.getString(3);
		    	negocio.detalle = cursor.getString(4);
		    	negocio.direccion = cursor.getString(5);
		    	negocio.horario = cursor.getString(6);
		    	negocio.subcategoria = new SubCategoria();

		    	cursor.moveToNext();
		    }
		    cursor.close();
		    db.close();
		    _dbHelper.close();
		    
		    return negocio;
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
	public List<Negocio> getAll(SubCategoria subCategoria) throws Exception {
		try {
			List<Negocio> negocios = new ArrayList<Negocio>();
			
			_dbHelper.openDataBase(); 
        	SQLiteDatabase db = _dbHelper.getDataBase();
        	
        	Cursor cursor = db.rawQuery("SELECT _id, nombrecorto, nombrelargo, imagen, detalle, direccion, horario, subcategoriaid FROM Negocios WHERE subcategoriaid = ? ORDER BY nombrecorto", 
        			new String[] { Long.toString(subCategoria.id) });
			
			cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	Negocio negocio = new Negocio();
		    	negocio.id = cursor.getLong(0);
		    	negocio.nombrecorto = cursor.getString(1);
		    	negocio.nombrelargo = cursor.getString(2);
		    	negocio.imagen = cursor.getString(3);
		    	negocio.detalle = cursor.getString(4);
		    	negocio.direccion = cursor.getString(5);
		    	negocio.horario = cursor.getString(6);
		    	negocio.subcategoria = new SubCategoria();
		    	
		    	negocios.add(negocio);		    	
		    	cursor.moveToNext();
		    }
		    cursor.close();
		    db.close();
		    _dbHelper.close();
		    
		    return negocios;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
}
