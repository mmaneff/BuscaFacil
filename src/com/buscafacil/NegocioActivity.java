package com.buscafacil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.buscafacil.controls.Common;
import com.buscafacil.controls.NegocioAdapter;
import com.buscafacil.domain.*;
import com.buscafacil.presenter.*;
import com.buscafacil.view.INegocioView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class NegocioActivity extends Activity implements INegocioView {

	protected NegocioPresenter _presenter;
	
	private final Handler mHandler = new Handler();	
	private ListView lstNegocios;
	private TextView tvTituloSubCategoria;
	private ImageView imgPublicidad;
	
	public int currentImageIndex = 0;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negocio);

        _presenter = new NegocioPresenter(this);
        _presenter.initView(getApplicationContext());       
     
        lstNegocios.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	Object obj = parent.getItemAtPosition(position);

                if(obj instanceof Negocio){
                	Bundle bundle = new Bundle();
                    bundle.putLong("NegocioId", ((Negocio)obj).id);
                	
                    Intent intent = new Intent(NegocioActivity.this, DatoActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else {
                	Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

	@Override
	public void initView(SubCategoria subCategoria, List<Negocio> negocios) {
		tvTituloSubCategoria = (TextView) findViewById(R.id.tvTituloSubCategoria);
		tvTituloSubCategoria.setTextColor(Common.getColorByName(getResources(), subCategoria.categoria.color));
		tvTituloSubCategoria.setText(subCategoria.nombre);
		
		NegocioAdapter negocioAdapter = new NegocioAdapter(this, negocios, getResources());
		
		lstNegocios = (ListView)findViewById(R.id.lstNegocios);
		lstNegocios.setAdapter(negocioAdapter);
	}

	@Override
	public void setMessage(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();		
	}

	@Override
	public long getSubCategoria() {
		//Recupero los datos de la pantalla categorias
        Bundle bundle = this.getIntent().getExtras();
        return bundle.getLong("SubCategoriaId");
	}
	
	/**
     * Helper method to start the animation on the splash screen
     */
    private void AnimateAndSlideShow() {
    	imgPublicidad = (ImageView)findViewById(R.id.imgPublicidad);
    	imgPublicidad.setImageResource(Utils.IMAGENES_NEGOCIO_IDS[currentImageIndex]);
           
    	currentImageIndex++;
    	if(currentImageIndex >= Utils.IMAGENES_NEGOCIO_IDS.length)
    		currentImageIndex = 0;
        
        Animation rotateImage = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        imgPublicidad.startAnimation(rotateImage);        
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
}
