package com.buscafacil.domain;

public class SubCategoria {

	public long id;
    public String nombre;
    public Categoria categoria;
	   
    public SubCategoria() {
    }
 
    public SubCategoria(String nombre, Categoria categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
