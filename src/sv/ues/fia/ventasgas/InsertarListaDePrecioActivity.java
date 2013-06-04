package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class InsertarListaDePrecioActivity extends Activity {
	
	
	ControlBD helper;
	EditText editidprecio;
	EditText editiddetalle;
	EditText editfechainicio;
	EditText editfechafin;
	EditText editprecio;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar_lista_de_precio);
		helper = new ControlBD(this);
		editidprecio = (EditText) findViewById(R.id.editText1);
		editiddetalle = (EditText) findViewById(R.id.editText2);
		editfechainicio = (EditText) findViewById(R.id.editText3);
		editfechafin = (EditText) findViewById(R.id.editText4);
		editprecio = (EditText) findViewById(R.id.editText5);
	}
	
	public void insertarListaPrecio(View v) {
		try{
		String idprecio = editidprecio.getText().toString();
		String iddetalle = editiddetalle.getText().toString();
		String fechainicio = editfechainicio.getText().toString();
		String fechafin = editfechafin.getText().toString();
		Double precio = Double.parseDouble(editprecio.getText().toString());
		
		String regInsertados;
		ClaseListaPrecio LP = new ClaseListaPrecio();
		LP.setID_PRECIO(idprecio);
		LP.setID_DETALLE(iddetalle);
		LP.setFECHA_INICIO(fechainicio);
		LP.setFECHA_FIN(fechafin);
		LP.setPRECIO(precio);
		helper.abrir();
		regInsertados = helper.insertar(LP);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
		}catch(Exception e){
			Toast.makeText(this, "Error en los datos", Toast.LENGTH_SHORT).show();	
		}
	}
	
	public void limpiarTexto(View v) {
		editidprecio.setText("");
		editiddetalle.setText("");
		editfechainicio.setText("");
		editfechafin.setText("");
		editprecio.setText("");
	}

	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_lista_de_precio, menu);
		return true;
	}

}
