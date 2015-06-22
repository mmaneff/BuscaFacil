package com.buscafacil.view;

import java.util.List;

import com.buscafacil.domain.*;

public interface INegocioView extends IBaseView {

	/**
	 * 
	 * @param subCategoria
	 * @param negocio
	 */
	void initView(SubCategoria subCategoria, List<Negocio> negocios);
	/**
	 * 
	 * @return
	 */
	long getSubCategoria();
	/**
	 * 
	 */
	void ShowPublicity();
}
