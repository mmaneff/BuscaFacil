package com.buscafacil.presenter;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.buscafacil.dao.CategoriaDao;
import com.buscafacil.domain.Categoria;
import com.buscafacil.view.ICategoriaView;


public class CategoriaPresenter {

	private final static String LOGCAT = "CategoriaPresenter";
	private ICategoriaView _view;
	
	
	/**
	 * Constructor
	 * @param view
	 */
	public CategoriaPresenter(ICategoriaView view) {
		_view = view;
	}
	
	/**
	 * Inicia los datos en la vista
	 */
	public void initView(Context context) {
		try {
			_view.ShowPublicity();
			
			CategoriaDao dao = new CategoriaDao(context);
			List<Categoria> list = dao.getAll();			
	        _view.initView(list);	        
		}
		catch(Exception ex) {
			Log.e(LOGCAT, ex.getMessage());
			_view.setMessage(ex.getMessage());
		}
	}	


}
