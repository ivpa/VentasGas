package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ConsultarDetalleDePrecioActivity extends Activity {
	ControlBD helper;
	EditText editiddetalle;
	EditText editidproducto;
	EditText editprecio;
	EditText editfechadetalle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultar_detalle_de_precio);
		helper = new ControlBD(this);
		editiddetalle = (EditText) findViewById(R.id.editText1);
	    editidproducto = (EditText) findViewById(R.id.editText2);
		editprecio = (EditText) findViewById(R.id.editText3);
		editfechadetalle = (EditText) findViewById(R.id.editText4);
	}
	
	public void consultarDetallePrecio(View v) {
		helper.abrir();
		ClaseDetallePrecio DP =
		helper.consultarDP(editiddetalle.getText().toString());
		helper.cerrar();
		if(DP == null)
		Toast.makeText(this, "Registro con ID " +
		editiddetalle.getText().toString() +
		" no encontrado", Toast.LENGTH_LONG).show();
		else{
			
		editidproducto.setText(DP.getID_PRODUCTO());
		editprecio.setText(Double.toString(DP.getPRECIO()));
		editfechadetalle.setText(DP.getFECHA_DETALLE());
		
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
		getMenuInflater().inflate(R.menu.consultar_detalle_de_precio, menu);
		return true;
	}

}
