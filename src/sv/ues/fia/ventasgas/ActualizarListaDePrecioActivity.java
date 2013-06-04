package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ActualizarListaDePrecioActivity extends Activity {
	ControlBD helper;
	EditText editidprecio;
	EditText editiddetalle;
	EditText editfechainicio;
	EditText editfechafin;
	EditText editprecio;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actualizar_lista_de_precio);
		helper = new ControlBD(this);
		editidprecio = (EditText) findViewById(R.id.editText1);
		editiddetalle = (EditText) findViewById(R.id.editText2);
		editfechainicio = (EditText) findViewById(R.id.editText3);
		editfechafin = (EditText) findViewById(R.id.editText4);
		editprecio = (EditText) findViewById(R.id.editText5);
	}
	
	
	public void actualizarListaPrecios(View v) {
		try{
		ClaseListaPrecio LP = new ClaseListaPrecio();
		LP.setID_PRECIO(editidprecio.getText().toString());
		LP.setID_DETALLE(editiddetalle.getText().toString());
		LP.setFECHA_INICIO(editfechainicio.getText().toString());
		LP.setFECHA_FIN(editfechafin.getText().toString());
		LP.setPRECIO(Double.parseDouble(editprecio.getText().toString()));
		helper.abrir();
		String estado = helper.actualizar(LP);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
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
		getMenuInflater().inflate(R.menu.actualizar_lista_de_precio, menu);
		return true;
	}

}
