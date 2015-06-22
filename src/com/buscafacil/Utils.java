package com.buscafacil;

import java.util.List;

import android.content.Context;

import com.buscafacil.dao.TelefonoDao;
import com.buscafacil.domain.Negocio;
import com.buscafacil.domain.Telefono;


public class Utils {

	public final static int[] IMAGENES_PRINCIPAL_IDS = {
		R.drawable.pb_burger, R.drawable.pb_olx, R.drawable.publicidad_horizontal_1, R.drawable.pb_cines,
		R.drawable.pb_dontoribio, R.drawable.pb_marathon
    };
	
	public final static int[] IMAGENES_CATEGORIA_IDS = {
		R.drawable.pb_cinemacenter, R.drawable.pb_havana, R.drawable.pb_pcvirtual, R.drawable.publicidad_vertical_1,
		R.drawable.pb_claro
    };
	
	public final static int[] IMAGENES_SUBCATEGORIA_IDS = {
        R.drawable.pb_falabella, R.drawable.pb_lagaceta, R.drawable.pb_pcvirtual, R.drawable.publicidad_vertical_2
    };
	
	public final static int[] IMAGENES_NEGOCIO_IDS = {
        R.drawable.pb_falabella, R.drawable.pb_havana, R.drawable.publicidad_vertical_3, R.drawable.pb_claro,
        R.drawable.pb_fibertel
    };
	
	/**
	 * 
	 * @param negocio
	 * @return
	 */
	public static String normalizarTelefonos(Negocio negocio, Context context) {
		StringBuilder result = new StringBuilder();
		
		TelefonoDao dao = new TelefonoDao(context);
		List<Telefono> telefonos = null;
		try {
			telefonos = dao.getAll(negocio);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(telefonos.size() > 0) {
			for(Telefono telefono : telefonos) {
				result.append(telefono.numero);
				result.append(" - ");
			}
		}
		
		return result.toString();
	}
	
	@SuppressWarnings("unused")
	private void PersonalizarFuente() {
		/*
		Typeface breeRegularFont = Typeface.createFromAsset(getAssets(),
				"fonts/BreeRegular.otf");

		Button btnRegresar = (Button) findViewById(R.id.btnRegresar);
		Button btnMapa = (Button) findViewById(R.id.btnMapa);
		Button btnFavorito = (Button) findViewById(R.id.btnLlamar);

		btnRegresar.setTypeface(breeRegularFont);
		btnMapa.setTypeface(breeRegularFont);
		btnFavorito.setTypeface(breeRegularFont);
		*/
	}
}
