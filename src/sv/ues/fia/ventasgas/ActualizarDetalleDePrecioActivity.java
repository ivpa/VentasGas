package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;


public class ActualizarDetalleDePrecioActivity extends Activity {

	ControlBD helper;
	EditText editiddetalle;
	EditText editidproducto;
	EditText editprecio;
	EditText editfechadetalle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actualizar_detalle_de_precio);
		helper = new ControlBD(this);
		editiddetalle = (EditText) findViewById(R.id.editText1);
	    editidproducto = (EditText) findViewById(R.id.editText2);
		editprecio = (EditText) findViewById(R.id.editText3);
		editfechadetalle = (EditText) findViewById(R.id.editText4);
	}
	
	public void actualizarDetallePrecio(View v) {
		try{
		ClaseDetallePrecio DP = new ClaseDetallePrecio();
		DP.setID_DETALLE(editiddetalle.getText().toString());
		DP.setID_PRODUCTO(editidproducto.getText().toString());
		DP.setPRECIO(Double.parseDouble(editprecio.getText().toString()));
		DP.setFECHA_DETALLE(editfechadetalle.getText().toString());

		helper.abrir();
		String estado = helper.actualizar(DP);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
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
		getMenuInflater().inflate(R.menu.actualizar_detalle_de_precio, menu);
		return true;
	}

}
