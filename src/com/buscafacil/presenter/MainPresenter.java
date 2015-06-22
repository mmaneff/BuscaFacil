package com.buscafacil.presenter;

import android.util.Log;

import com.buscafacil.view.IMainView;

public class MainPresenter {

	private final static String LOGCAT = "MainPresenter";
	private IMainView _view;
	
	
	/**
	 * Constructor
	 * @param view
	 */
	public MainPresenter(IMainView view) {
		_view = view;
	}
	
	/**
	 * Inicia los datos en la vista
	 */
	public void initView() {
		try {
			_view.ShowPublicity();				        
		}
		catch(Exception ex) {
			Log.e(LOGCAT, ex.getMessage());
		}
	}
	
}
