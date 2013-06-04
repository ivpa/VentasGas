package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class InsertarDetalleDePrecioActivity extends Activity {

	
	ControlBD helper;
	EditText editiddetalle;
	EditText editidproducto;
	EditText editprecio;
	EditText editfechadetalle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar_detalle_de_precio);
		helper = new ControlBD(this);
		editiddetalle = (EditText) findViewById(R.id.editText1);
	    editidproducto = (EditText) findViewById(R.id.editText2);
		editprecio = (EditText) findViewById(R.id.editText3);
		editfechadetalle = (EditText) findViewById(R.id.editText4);
		
	}
	
	public void insertarDetallePrecio(View v) {
		try{
		String iddetalle = editiddetalle.getText().toString();
		String idproducto = editidproducto.getText().toString();
		Double precio = Double.parseDouble(editprecio.getText().toString());
		String fechadetalle = editfechadetalle.getText().toString();
		
		String regInsertados;
		ClaseDetallePrecio DP = new ClaseDetallePrecio();
		DP.setID_DETALLE(iddetalle);
		DP.setID_PRODUCTO(idproducto);
		DP.setPRECIO(precio);
		DP.setFECHA_DETALLE(fechadetalle);
		DP.setACTUAL("SI");
		
		helper.abrir();
		regInsertados = helper.insertar(DP);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
		}catch(Exception e){
			Toast.makeText(this, "Error en los datos", Toast.LENGTH_SHORT).show();	
		}
	}
	
	public void limpiarTexto(View v) {
		editiddetalle.setText("");
		editidproducto.setText("");
		editprecio.setText("");
		editfechadetalle.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_detalle_de_precio, menu);
		return true;
	}

}
