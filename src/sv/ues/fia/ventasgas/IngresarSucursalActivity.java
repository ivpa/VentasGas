package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IngresarSucursalActivity extends Activity {

	ControlBD helper;
	EditText idSucursal;
	EditText idGasolinera;
	EditText nombre;
	EditText direccion;
	EditText telefono;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ingresar_sucursal);
		
helper = new ControlBD(this);
		
		idSucursal = (EditText) findViewById(R.id.idAgregarSuc);
		idGasolinera = (EditText) findViewById(R.id.idGasolineraAgregarSucursal);
		nombre = (EditText) findViewById(R.id.nombreAgregarSucursal);
		direccion = (EditText) findViewById(R.id.direccionAgregarSucursal);
		telefono = (EditText) findViewById(R.id.telefonoAgregarSuc);
		
	}

	public void agregarSucursal(View v)
	{
		String idSuc = idSucursal.getText().toString();
		String idGas = idGasolinera.getText().toString();
		String nom = nombre.getText().toString();
		String dir = direccion.getText().toString();
		String tel = telefono.getText().toString();
		
		String regInsertados;
		
		Sucursal sucursal = new Sucursal();
		sucursal.setId_sucursal(idSuc);
		sucursal.setId_gasolinera(idGas);
		sucursal.setNombre_sucursal(nom);
		sucursal.setDireccion(dir);
		sucursal.setTelefono(tel);
		
		helper.abrir();
		regInsertados = helper.insertar(sucursal);
		helper.cerrar();
		
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
		
		idSucursal.setText("");
		idGasolinera.setText("");
		nombre.setText("");
		direccion.setText("");
		telefono.setText("");
		
	}

}