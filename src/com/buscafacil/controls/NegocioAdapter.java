package com.buscafacil.controls;

import java.util.ArrayList;
import java.util.List;

import com.buscafacil.R;
import com.buscafacil.domain.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NegocioAdapter extends BaseAdapter {

	private final static String LOGCAT = "NegocioAdapter";
	
	protected Activity activity;
	protected ArrayList<Negocio> arrayList;
	protected List<Negocio> list;
	protected Resources resources;
	
	public NegocioAdapter(Activity activity, ArrayList<Negocio> list) {
		this.activity = activity;
	    this.arrayList = list;
	}
	
	public NegocioAdapter(Activity activity, List<Negocio> list, Resources resources) {
		this.activity = activity;
	    this.list = list;
	    this.resources = resources;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return list.get(position).id;
	}
	
	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View contentView, ViewGroup viewGroup) {
		View view = contentView;
		  
	    if(contentView == null) {
	      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      view = inflater.inflate(R.layout.lista_negocio, null);
	    }
	    
	    try {
		    //Recupero la categoria
		    Negocio negocio = list.get(position);
		    
		    ImageView image = (ImageView) view.findViewById(R.id.imgNegocioImagen);		    
		    image.setImageDrawable(Common.getImageByName(resources, negocio.imagen));
		    	    
		    //Muestro el texto del nombre
		    TextView nombre = (TextView) view.findViewById(R.id.tvNegocioNombre);
		    nombre.setText(negocio.nombrecorto);
		    nombre.setTextColor(Common.getColorByName(resources, negocio.subcategoria.categoria.color));
		    
		    TextView domicilio = (TextView) view.findViewById(R.id.tvNegocioDireccion);
		    domicilio.setText(negocio.direccion);
		    
		    TextView cercania = (TextView) view.findViewById(R.id.tvNegocioCercania);
		    cercania.setText(Common.getDetalleCorto(negocio.detalle));
		}
	    catch(Exception ex) {
	    	Log.e(LOGCAT, ex.getMessage());
	    }
	    
	    return view;
	}
	
}
