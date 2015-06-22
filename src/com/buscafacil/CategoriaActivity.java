package com.buscafacil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.buscafacil.controls.CategoriaAdapter;
import com.buscafacil.domain.Categoria;
import com.buscafacil.presenter.CategoriaPresenter;
import com.buscafacil.view.ICategoriaView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * http://moorandroid.blogspot.com.ar/p/image-slide-show.html
 * @author emaneff
 *
 */
public class CategoriaActivity extends Activity implements ICategoriaView {

	protected CategoriaPresenter _presenter;
	
	private final Handler mHandler = new Handler();	
	private ListView lstCategorias;
	private ImageView imgPublicidad;
	
	public int currentImageIndex = 0;	
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);        
        
        _presenter = new CategoriaPresenter(this);
        _presenter.initView(getApplicationContext());
        
        lstCategorias.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	Object obj = parent.getItemAtPosition(position);

                if(obj instanceof Categoria){
                	
                	//android.os.Process.killProcess(android.os.Process.myPid());
                	
                	Bundle bundle = new Bundle();
                    bundle.putLong("CategoriaId", ((Categoria)obj).id);
                	
                    Intent intent = new Intent(CategoriaActivity.this, SubCategoriaActivity.class);
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
	public void initView(List<Categoria> list) {
		CategoriaAdapter categoriaAdapter = new CategoriaAdapter(this, list, getResources());
		
		lstCategorias = (ListView)findViewById(R.id.lstCategorias);
        lstCategorias.setAdapter(categoriaAdapter);
	}

	@Override
	public void setMessage(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();		
	}

	/**
     * Helper method to start the animation on the splash screen
     */
    private void AnimateAndSlideShow() {
    	try {
	    	imgPublicidad = (ImageView)findViewById(R.id.imgPublicidad);
	    	imgPublicidad.setImageResource(Utils.IMAGENES_CATEGORIA_IDS[currentImageIndex]);
	           
	    	currentImageIndex++;
	    	if(currentImageIndex >= Utils.IMAGENES_CATEGORIA_IDS.length)
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
		/*
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
        }, delay, period);*/
	}   
	
	/*
	@Override
	protected void onDestroy() {	
		super.onDestroy();
		
		unbindDrawables(findViewById(R.id.categoriaView)); 
		System.gc();
	}
	
	private void unbindDrawables(View view) { 
		if (view.getBackground() != null) { 
			view.getBackground().setCallback(null); 
		} 
		if (view instanceof ViewGroup) { 
			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) { 
				unbindDrawables(((ViewGroup) view).getChildAt(i)); 
			} 
			((ViewGroup) view).removeAllViews(); 
		} 
	}
	*/
	
}
