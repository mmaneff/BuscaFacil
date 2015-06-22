package com.buscafacil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.buscafacil.controls.Common;
import com.buscafacil.controls.SubCategoriaAdapter;
import com.buscafacil.domain.*;
import com.buscafacil.presenter.SubCategoriaPresenter;
import com.buscafacil.view.ISubCategoriaView;

public class SubCategoriaActivity extends Activity implements ISubCategoriaView {

	protected SubCategoriaPresenter _presenter;
	
	private final Handler mHandler = new Handler();	
	private ListView lstSubCategorias;
	private TextView tvCategoriaNombre;
	private ImageView imgCategoria;
	private LinearLayout lyColorCategoria;
	private ImageView imgPublicidad;
	
	public int currentImageIndex = 0;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategoria);
      	
        _presenter = new SubCategoriaPresenter(this);
        _presenter.initView(getApplicationContext());
        
        lstSubCategorias.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	Object obj = parent.getItemAtPosition(position);

                if(obj instanceof SubCategoria){
                	Bundle bundle = new Bundle();
                    bundle.putLong("SubCategoriaId", ((SubCategoria)obj).id);
                	
                    Intent intent = new Intent(SubCategoriaActivity.this, NegocioActivity.class);
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
	public void setCategoria(Categoria categoria) {
		//Instancio los controles
		lyColorCategoria = (LinearLayout) findViewById(R.id.lyColorCategoria);
		tvCategoriaNombre = (TextView) findViewById(R.id.tvCategoriaNombre);
		imgCategoria = (ImageView) findViewById(R.id.imgCategoria);
		
		//Seteo el valor de la propiedad correspondiente
		lyColorCategoria.setBackgroundDrawable(Common.getDrawableColorByName(getResources(), categoria.color));
		tvCategoriaNombre.setText(categoria.nombre);
		imgCategoria.setImageDrawable(Common.getImageByName(getResources(), categoria.imagen));
	}
	
	@Override
	public void initView(List<SubCategoria> subCategorias) {
		SubCategoriaAdapter subCategoriaAdapter = new SubCategoriaAdapter(this, subCategorias, getResources());
		
		lstSubCategorias = (ListView)findViewById(R.id.lstSubCategorias);
        lstSubCategorias.setAdapter(subCategoriaAdapter);
	}

	@Override
	public void setMessage(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();		
	}

	@Override
	public long getCategoria() {
		//Recupero los datos de la pantalla categorias
        Bundle bundle = this.getIntent().getExtras();
        return bundle.getLong("CategoriaId");
	}
	
	/**
     * Helper method to start the animation on the splash screen
     */
    private void AnimateAndSlideShow() {
    	imgPublicidad = (ImageView)findViewById(R.id.imgPublicidad);
    	imgPublicidad.setImageResource(Utils.IMAGENES_SUBCATEGORIA_IDS[currentImageIndex]);
           
    	currentImageIndex++;
    	if(currentImageIndex >= Utils.IMAGENES_SUBCATEGORIA_IDS.length)
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
