package com.buscafacil.view;

import java.util.List;

import com.buscafacil.domain.*;

public interface ISubCategoriaView extends IBaseView {

	/**
	 * 
	 * @param list
	 */
	void initView(List<SubCategoria> list);
	/**
	 * 
	 * @param categoria
	 */
	void setCategoria(Categoria categoria);
	/**
	 * 
	 * @return
	 */
	long getCategoria();
	/**
	 * 
	 */
	void ShowPublicity();
}
