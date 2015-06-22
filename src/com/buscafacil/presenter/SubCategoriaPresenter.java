package com.buscafacil.presenter;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.buscafacil.dao.CategoriaDao;
import com.buscafacil.dao.SubCategoriaDao;
import com.buscafacil.domain.*;
import com.buscafacil.view.ISubCategoriaView;

public class SubCategoriaPresenter {

	private final static String LOGCAT = "SubCategoriaPresenter";
	private ISubCategoriaView _view;
	
	
	/**
	 * Constructor
	 * @param view
	 */
	public SubCategoriaPresenter(ISubCategoriaView view) {
		_view = view;		
	}
	
	/**
	 * Inicia los datos en la vista
	 */
	public void initView(Context context) {
		try {
			long categoriaId = _view.getCategoria();
			
			CategoriaDao dao = new CategoriaDao(context);
			Categoria categoria = dao.get(categoriaId);			
			_view.setCategoria(categoria);
			
			_view.ShowPublicity();
			
			SubCategoriaDao subdao = new SubCategoriaDao(context);
			List<SubCategoria> list = subdao.getAll(categoria);
	        _view.initView(list);			
		}
		catch(Exception ex) {
			Log.e(LOGCAT, ex.getMessage());
			_view.setMessage(ex.getMessage());
		}
	}	
	
}
