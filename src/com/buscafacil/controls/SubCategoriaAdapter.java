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
//import android.widget.ImageView;
//import android.widget.LinearLayout;
import android.widget.TextView;

public class SubCategoriaAdapter extends BaseAdapter {

	private final static String LOGCAT = "SubCategoriaAdapter";
	
	protected Activity activity;
	protected ArrayList<SubCategoria> arrayList;
	protected List<SubCategoria> list;
	protected Resources resources;
	
	public SubCategoriaAdapter(Activity activity, ArrayList<SubCategoria> list) {
		this.activity = activity;
	    this.arrayList = list;
	}
	
	public SubCategoriaAdapter(Activity activity, List<SubCategoria> list, Resources resources) {
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
	      view = inflater.inflate(R.layout.lista_subcategoria, null);
	    }
	    
	    try {
		    //Recupero la categoria
		    SubCategoria subCategoria = list.get(position);
		    
		    //Actualizo el color de fondo en base a cada categoria
		    //LinearLayout fondo = (LinearLayout) view.findViewById(R.id.id_lista_subcategoria);
		    //fondo.setBackgroundDrawable(Common.getColorByName(resources, subCategoria.categoria.color));
		    
		    //Muestro el texto de cada categoria
		    TextView nombre = (TextView) view.findViewById(R.id.tvNombreSubCategoria);
		    nombre.setText(subCategoria.nombre);
	    }
	    catch(Exception ex) {
	    	Log.e(LOGCAT, ex.getMessage());
	    }
		    
	    return view;
	}

}
