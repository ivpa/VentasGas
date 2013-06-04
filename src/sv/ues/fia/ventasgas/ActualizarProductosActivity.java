package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ActualizarProductosActivity extends Activity {
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
		setContentView(R.layout.activity_actualizar_productos);
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
	
	public void actualizarProductos(View v) {
		try{
		ClaseProductos Pro = new ClaseProductos();
		Pro.setID_PRODUCTO(editidproducto.getText().toString());
		Pro.setID_TIPO(editidtipo.getText().toString());
		Pro.setNOMBRE_PRODUCTO(editnombreproducto.getText().toString());
		Pro.setPRECIO_ACTUAL(Double.parseDouble(editprecioactual.getText().toString()));
		Pro.setEXISTENCIA(Integer.parseInt(editexistencia.getText().toString()));
		Pro.setTIPO_PRODUCTO(edittipoproducto.getText().toString());
		Pro.setCANTIDAD(Integer.parseInt(editcantidad.getText().toString()));
		Pro.setUNIDADES(editunidades.getText().toString());
		
		helper.abrir();
		String estado = helper.actualizar(Pro);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
		}catch(Exception e){
		Toast.makeText(this, "Error en los datos", Toast.LENGTH_SHORT).show();	
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
		getMenuInflater().inflate(R.menu.actualizar_productos, menu);
		return true;
	}

}