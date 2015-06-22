package com.buscafacil.presenter;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.buscafacil.dao.*;
import com.buscafacil.domain.*;
import com.buscafacil.view.IDatoView;
import com.buscafacil.view.INegocioView;

public class NegocioPresenter {

	private final static String LOGCAT = "NegocioPresenter";
	private INegocioView _view;	
	private IDatoView _datoView;
	
	/**
	 * Constructor
	 * @param view
	 */
	public NegocioPresenter(INegocioView view) {
		_view = view;
	}
	
	/**
	 * 
	 * @param view
	 */
	public NegocioPresenter(IDatoView view) {
		_datoView = view;
	}
	
	/**
	 * Inicia los datos en la vista
	 */
	public void initView(Context context) {
		try {
			long subCategoriaId = _view.getSubCategoria();
			SubCategoriaDao dao = new SubCategoriaDao(context);
			SubCategoria subCategoria = dao.get(subCategoriaId);			
			//_view.setCategoria(categoria);
			
			_view.ShowPublicity();
			
			NegocioDao negDao = new NegocioDao(context);
			List<Negocio> negocios = negDao.getAll(subCategoria);			
	        _view.initView(subCategoria, negocios);			
		}
		catch(Exception ex) {
			Log.e(LOGCAT, ex.getMessage());
			_view.setMessage(ex.getMessage());
		}
	}
	
	/**
	 * 
	 */
	public void initDatoView(Context context) {
		try {
			long negocioId = _datoView.getNegocio();
			NegocioDao dao = new NegocioDao(context);
			Negocio negocio = dao.get(negocioId);			
					
			_datoView.initView(negocio);
			_datoView.agregarImagenEnBotones();
		}
		catch(Exception ex) {
			Log.e(LOGCAT, ex.getMessage());
			_view.setMessage(ex.getMessage());
		}
	}
	
	public void showMapa(Context context) {
		try {
			long negocioId = _datoView.getNegocio();
			NegocioDao dao = new NegocioDao(context);
			Negocio negocio = dao.get(negocioId);		
			
			CoordenadaDao geoDao = new CoordenadaDao(context);
			Coordenada coordenada = geoDao.get(negocio);

			_datoView.showMapa(negocio.nombrelargo, negocio.direccion, coordenada);			
		}
		catch(Exception ex) {
			Log.e(LOGCAT, ex.getMessage());
			_view.setMessage(ex.getMessage());
		}
	}
	
	public void llamar(Context context) {
		try {
			long negocioId = _datoView.getNegocio();
			NegocioDao dao = new NegocioDao(context);
			Negocio negocio = dao.get(negocioId);		
			
			TelefonoDao telDao = new TelefonoDao(context);
			List<Telefono> telefonos = telDao.getAll(negocio);

			_datoView.llamar(telefonos);			
		}
		catch(Exception ex) {
			Log.e(LOGCAT, ex.getMessage());
			_view.setMessage(ex.getMessage());
		}
	}
	
}
