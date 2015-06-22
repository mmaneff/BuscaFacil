package com.buscafacil.domain;

public class Negocio {

	public long id;
    public String nombrecorto;
    public String nombrelargo;
    public String imagen;
    public String detalle;
    public String direccion;
    public String horario;
    public SubCategoria subcategoria;
 
    public Negocio() {
    }
 
    public Negocio(String nombrecorto, String nombrelargo, String imagen,
    		String detalle, String direccion, String horario, 
    		SubCategoria subcategoria) {        
    	this.nombrecorto = nombrecorto;
        this.nombrelargo = nombrelargo;
        this.imagen = imagen;
        this.detalle = detalle;
        this.direccion = direccion;
        this.horario = horario;
        this.subcategoria = subcategoria;
    }
    
    @Override
    public String toString() {
        return nombrecorto;
    }
}
