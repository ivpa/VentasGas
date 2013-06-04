package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class InsertarProductoActivity extends Activity {
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
		setContentView(R.layout.activity_insertar_producto);
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
	
	public void insertarProductos(View v) {
		try{
		String idproducto = editidproducto.getText().toString();
		String idtipo = editidtipo.getText().toString();
		String nombreproducto = editnombreproducto.getText().toString();
		double precioactual = Double.parseDouble(editprecioactual.getText().toString());
		int existencia = Integer.parseInt(editexistencia.getText().toString());
		String tipoproducto = edittipoproducto.getText().toString();
		int cantidad = Integer.parseInt(editcantidad.getText().toString());
		String unidades = editunidades.getText().toString();
				
		String regInsertados;
		ClaseProductos Pro = new ClaseProductos();
		Pro.setID_PRODUCTO(idproducto);
		Pro.setID_TIPO(idtipo);
		Pro.setNOMBRE_PRODUCTO(nombreproducto);
		Pro.setPRECIO_ACTUAL(precioactual);
		Pro.setEXISTENCIA(existencia);
		Pro.setTIPO_PRODUCTO(tipoproducto);
		Pro.setCANTIDAD(cantidad);
		Pro.setUNIDADES(unidades);
		
		helper.abrir();
		regInsertados = helper.insertar(Pro);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
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
		getMenuInflater().inflate(R.menu.insertar_producto, menu);
		return true;
	}

}