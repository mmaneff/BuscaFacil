package com.buscafacil.view;


import java.util.List;

import com.buscafacil.domain.*;



public interface IDatoView extends IBaseView {

	/**
	 * 
	 * @param negocio
	 */
	void initView(Negocio negocio);
	/**
	 * 
	 * @return
	 */
	long getNegocio();
	/**
	 * 
	 * @param negocioNombre
	 * @param direccion
	 * @param coordenada
	 */
	void showMapa(String negocioNombre, String direccion, Coordenada coordenada);
	/**
	 * 
	 * @param telefonos
	 */
	void llamar(List<Telefono> telefonos);
	/**
	 * 
	 */
	void agregarImagenEnBotones();
}
