package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ConsultarListaDePrecioActivity extends Activity {
	ControlBD helper;
	EditText editidprecio;
	EditText editiddetalle;
	EditText editfechainicio;
	EditText editfechafin;
	EditText editprecio;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultar_lista_de_precio);
		helper = new ControlBD(this);
		editidprecio = (EditText) findViewById(R.id.editText1);
		editiddetalle = (EditText) findViewById(R.id.editText2);
		editfechainicio = (EditText) findViewById(R.id.editText3);
		editfechafin = (EditText) findViewById(R.id.editText4);
		editprecio = (EditText) findViewById(R.id.editText5);
	}
	
	public void consultarLP(View v) {
		helper.abrir();
		ClaseListaPrecio LP =
		helper.consultarListaPrecio(editidprecio.getText().toString());
		helper.cerrar();
		if(LP == null)
		Toast.makeText(this, "Registro con ID " +
		editidprecio.getText().toString() +
		" no encontrado", Toast.LENGTH_LONG).show();
		else{
		editidprecio.setText(LP.getID_PRECIO());
		editiddetalle.setText(LP.getID_DETALLE());
		editfechainicio.setText(LP.getFECHA_INICIO());
		editfechafin.setText(LP.getFECHA_FIN());
		editprecio.setText(Double.toString(LP.getPRECIO()));
		
		
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
		getMenuInflater().inflate(R.menu.consultar_lista_de_precio, menu);
		return true;
	}

}
