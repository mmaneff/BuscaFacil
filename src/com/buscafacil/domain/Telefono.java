package com.buscafacil.domain;

public class Telefono {

	public long id;
	public String numero;
    public Negocio negocio;
	
    public Telefono() {
    }
 
    public Telefono(String numero, Negocio negocio) {
        this.numero = numero;
        this.negocio = negocio;
    }
    
    @Override
    public String toString() {
        return numero;
    }
    
}
