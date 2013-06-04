package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ConsultarProductosActivity extends Activity {
	ControlBD helper;
	EditText editidproducto;
	EditText editidtipo;
	EditText editnombreproducto;
	EditText editprecioactual;
	EditText editexistencia;
	EditText edittipoproducto;
	EditText editcantidad;
	EditText editunidades;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultar_productos);
		helper = new ControlBD(this);
		editidproducto = (EditText) findViewById(R.id.editText1);
		editidtipo = (EditText) findViewById(R.id.editText2);
		editnombreproducto = (EditText) findViewById(R.id.editText3);
		editprecioactual = (EditText) findViewById(R.id.editText4);
		editexistencia = (EditText) findViewById(R.id.editText5);
		edittipoproducto = (EditText) findViewById(R.id.editText6);
		editcantidad = (EditText) findViewById(R.id.editText7);
		editunidades = (EditText) findViewById(R.id.editText8);
	}
	
	public void consultarP(View v) {
		helper.abrir();
		ClaseProductos Pro =
		helper.consultarProd(editidproducto.getText().toString());
		helper.cerrar();
		if(Pro == null)
		Toast.makeText(this, "Producto con ID " +
		editidproducto.getText().toString() +
		" no encontrado", Toast.LENGTH_LONG).show();
		else{
		editidtipo.setText(Pro.getID_TIPO());
		editnombreproducto.setText(Pro.getNOMBRE_PRODUCTO());
		editprecioactual.setText(Double.toString(Pro.getPRECIO_ACTUAL()));
		editexistencia.setText(Integer.toString(Pro.getEXISTENCIA()));
		edittipoproducto.setText(Pro.getTIPO_PRODUCTO());
		editcantidad.setText(Integer.toString(Pro.getCANTIDAD()));
		editunidades.setText(Pro.getUNIDADES());
		}
		}
		
	public void limpiarTexto(View v) {
		editidproducto.setText("");
		editidtipo.setText("");
		editnombreproducto.setText("");
		editprecioactual.setText("");
		editexistencia.setText("");
		edittipoproducto.setText("");
		editcantidad.setText("");
		editunidades.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consultar_productos, menu);
		return true;
	}

}
