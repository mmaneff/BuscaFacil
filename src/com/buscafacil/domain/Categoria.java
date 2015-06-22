package com.buscafacil.domain;


public class Categoria {

	public long id;
    public String nombre;
    public String imagen;
    public String color;
	
	public Categoria() {
	}
	
	public Categoria(String nombre, String imagen, String color) {
	        this.nombre = nombre;
	        this.imagen = imagen;
	        this.color = color;
	}
	
	@Override
    public String toString() {
        return nombre;
    }

}
