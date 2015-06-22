package com.buscafacil.domain;

public class Coordenada {

	public long id;
    public String latitud;
    public String longitud;
    public Negocio negocio;
	
    public Coordenada() {
    }
 
    public Coordenada(String latitud, String longitud, Negocio negocio) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.negocio = negocio;
    }
    
    @Override
    public String toString() {
        return "lat:" + latitud + " - long:" + longitud;
    }
    
}
