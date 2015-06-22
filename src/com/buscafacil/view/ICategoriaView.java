package com.buscafacil.view;

import java.util.List;

import com.buscafacil.domain.Categoria;

public interface ICategoriaView extends IBaseView {
	/**
	 * 
	 * @param list
	 */
	void initView(List<Categoria> list);
	/**
	 * 
	 */
	void ShowPublicity();
}
