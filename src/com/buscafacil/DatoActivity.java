package com.buscafacil;

import java.util.List;

import com.buscafacil.controls.Common;
import com.buscafacil.domain.Coordenada;
import com.buscafacil.domain.Negocio;
import com.buscafacil.domain.Telefono;
import com.buscafacil.presenter.NegocioPresenter;
import com.buscafacil.view.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DatoActivity extends Activity implements IDatoView {

	protected NegocioPresenter _presenter;
	private TextView tvNegocioTitulo;
	private TextView tvDireccion;
	private TextView tvTelefono;
	private TextView tvDescripcion;
	private TextView tvNegocioNombre;
	private TextView tvHorarios;
	private ImageView imgNegocio;
	private Button btnRegresar;
	private Button btnLlamar;
	private Button btnMapa;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_2);

        _presenter = new NegocioPresenter(this);
        _presenter.initDatoView(getApplicationContext());
	}	

	@Override
	public void setMessage(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();		
	}

	@Override
	public void initView(Negocio negocio) {
		tvNegocioTitulo = (TextView) findViewById(R.id.tvNegocioTitulo);
		tvDireccion = (TextView) findViewById(R.id.tvDireccion);
		tvTelefono = (TextView) findViewById(R.id.tvTelefono);
		tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);
		tvNegocioNombre = (TextView) findViewById(R.id.tvNegocioNombre);
		tvHorarios = (TextView) findViewById(R.id.tvHorarios);
		imgNegocio = (ImageView) findViewById(R.id.imgNegocio);		
		
		tvNegocioTitulo.setText(negocio.nombrecorto);
		tvNegocioTitulo.setTextColor(Common.getColorByName(getResources(), negocio.subcategoria.categoria.color));
		
		tvNegocioNombre.setText(negocio.nombrelargo);
		tvDireccion.setText(negocio.direccion);
		tvTelefono.setText(Utils.normalizarTelefonos(negocio, getApplicationContext()));
		tvDescripcion.setText(negocio.detalle);
		tvHorarios.setText(negocio.horario);
		imgNegocio.setImageDrawable(Common.getImageByName(getResources(), negocio.imagen));
	}

	@Override
	public long getNegocio() {
		//Recupero los datos de la pantalla categorias
        Bundle bundle = this.getIntent().getExtras();
        return bundle.getLong("NegocioId");
	}
	
	public void onRegresarClick(View view) {
		finish();
	}
	
	public void onLlamarClick(View view) {
		_presenter.llamar(getApplicationContext());		
	}

	public void onMapaClick(View view) {
		_presenter.showMapa(getApplicationContext());
	}
	
	@Override
	public void showMapa(String negocioNombre, String direccion, Coordenada coordenada) {
		Bundle bundle = new Bundle();		
		bundle.putString("negocioNombre", negocioNombre);
		bundle.putDouble("latitud", Double.parseDouble(coordenada.latitud));
		bundle.putDouble("longitud", Double.parseDouble(coordenada.longitud));
		bundle.putString("direccion", direccion);
		
		Intent intent = new Intent(this, MapaActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);		
	}

	@Override
	public void llamar(final List<Telefono> telefonos) {
		if(telefonos.size() > 0) {
			if(telefonos.size() == 1) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:"	+ telefonos.get(0).numero.replace("-", "")));
				startActivity(callIntent);
			}
			else if(telefonos.size() == 2) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Elija un número");
				builder.setItems(2,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								if (which == 0) {
									Intent callIntent = new Intent(Intent.ACTION_CALL);
									callIntent.setData(Uri.parse("tel:" + telefonos.get(0).numero.replace("-", "")));
									startActivity(callIntent);
								} else if (which == 1) {
									Intent callIntent = new Intent(Intent.ACTION_CALL);
									callIntent.setData(Uri.parse("tel:" + telefonos.get(1).numero.replace("-", "")));
									startActivity(callIntent);
								}
							}
						});
				builder.show();
			}
			else if(telefonos.size() == 3) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Elija un número");
				builder.setItems(3,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								if (which == 0) {
									Intent callIntent = new Intent(Intent.ACTION_CALL);
									callIntent.setData(Uri.parse("tel:" + telefonos.get(0).numero.replace("-", "")));
									startActivity(callIntent);
								} else if (which == 1) {
									Intent callIntent = new Intent(Intent.ACTION_CALL);
									callIntent.setData(Uri.parse("tel:" + telefonos.get(1).numero.replace("-", "")));
									startActivity(callIntent);
								} else if (which == 2) {
									Intent callIntent = new Intent(Intent.ACTION_CALL);
									callIntent.setData(Uri.parse("tel:" + telefonos.get(3).numero.replace("-", "")));
									startActivity(callIntent);
								}
							}
						});
				builder.show();
			}
		}
		
		
	}

	@Override
	public void agregarImagenEnBotones() {
		btnRegresar = (Button) findViewById(R.id.btnRegresar);
		btnLlamar = (Button) findViewById(R.id.btnLlamar);
		btnMapa = (Button) findViewById(R.id.btnMapa);
		
		btnRegresar.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.botonvolver, 0, 0);
		btnLlamar.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.botonllamar, 0, 0);
		btnMapa.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.botonmapa, 0, 0);
		
	}
}
