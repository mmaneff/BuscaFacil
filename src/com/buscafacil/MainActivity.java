package com.buscafacil;

import java.util.Timer;
import java.util.TimerTask;

import com.buscafacil.presenter.*;
import com.buscafacil.view.IMainView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class MainActivity extends Activity implements IMainView {
	
	protected MainPresenter _presenter;
	
	private final Handler mHandler = new Handler();	
	private ImageView imgPublicidad;
	
	public int currentImageIndex = 0;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);       
        
        _presenter = new MainPresenter(this);
        _presenter.initView();
	}	
	
		
	public void onCapitalClick(View v) {
		Intent intent = new Intent(this, CategoriaActivity.class);
		startActivity(intent);
	}
	
	public void onYerbaBuenaClick(View v) {
		Intent intent = new Intent(this, CategoriaActivity.class);
		startActivity(intent);
	}


	@Override
	public void ShowPublicity() {
		// TODO Auto-generated method stub
		
	}
	
	/*
	
	private void saveConstruccion() {
		Categoria categoria = new Categoria();
		categoria.nombre = "CONSTRUCCION";
		categoria.color = "orange";
		categoria.imagen = "iconoconstruccion";
		categoria.save();
	}
	
	private void saveDecoracion() {
		Categoria categoria = new Categoria();
		categoria.nombre = "DECORACION Y HOGAR";
		categoria.color = "teal";
		categoria.imagen = "iconohogar";
		categoria.save();
		
	}
	
	private void saveEducacion() {
		Categoria categoria = new Categoria();	
		categoria.nombre = "EDUCACION";
		categoria.color = "red";
		categoria.imagen = "iconoeducacion";
		categoria.save();
	}
	
	private void saveEmergencias() {
		Categoria categoria = new Categoria();
		categoria.nombre = "EMERGENCIA";
		categoria.color = "pink";
		categoria.imagen = "iconoemergencia";
		categoria.save();
		
	}
	
	private void saveEventos() {
		Categoria categoria = new Categoria();
		categoria.nombre = "EVENTOS Y FIESTAS";
		categoria.color = "indigo";
		categoria.imagen = "iconoeventosyfiestas";
		categoria.save();
		
	}
	
	
	
	
	

	
	
	private void saveTelefonos() {
		Negocio negocio = NegocioDao.get(1);
		
		Telefono telefono;
		
		telefono = new Telefono();
		telefono.numero = "4231596";
		telefono.negocio = negocio;
		telefono.save();
		
		telefono = new Telefono();
		telefono.numero = "4332569";
		telefono.negocio = negocio;
		telefono.save();
		
	}*/
	
	/**
     * Helper method to start the animation on the splash screen
     */
	/*
    private void AnimateAndSlideShow() {
    	try {
	    	imgPublicidad = (ImageView)findViewById(R.id.imgPublicidad);
	    	imgPublicidad.setImageResource(Utils.IMAGENES_PRINCIPAL_IDS[currentImageIndex]);
	           
	    	currentImageIndex++;
	    	if(currentImageIndex >= Utils.IMAGENES_PRINCIPAL_IDS.length)
	    		currentImageIndex = 0;
	        
	        Animation rotateImage = AnimationUtils.loadAnimation(this, R.anim.fade_in);
	        imgPublicidad.startAnimation(rotateImage);
	    }
		catch(OutOfMemoryError ex) {
			ex.printStackTrace();
		}
    }

	@Override
	public void ShowPublicity() {
		// Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {                
                AnimateAndSlideShow();
            }
        };
        
        int delay = 1000; // delay for 1 sec.
        int period = 10000; // repeat every 4 sec.

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
        	public void run() {
        		mHandler.post(mUpdateResults);
        	}
        }, delay, period);

	} 
	*/
}
