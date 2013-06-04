package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarSucursalActivity extends Activity {

	ControlBD helper;
	EditText editIdsucursal;
	EditText editIdGasolinera;
	EditText editNombresucursal;
	EditText editdireccion;
	EditText editTelefono;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modificar_sucursal);
		
		helper=new ControlBD(this);
		editIdsucursal=(EditText)findViewById(R.id.idModificarSuc);
		editIdGasolinera=(EditText)findViewById(R.id.idGasolineraModificarDatos);
		editNombresucursal=(EditText)findViewById(R.id.nombreModificarSuc);
		editdireccion=(EditText)findViewById(R.id.direccionModificarSuc);
		editTelefono=(EditText)findViewById(R.id.telefonoModificarSucursal);
	}

	public void ActualizarSucursal(View v) {
		
		Sucursal sucursal = new Sucursal();
		sucursal.setId_sucursal(editIdsucursal.getText().toString());
		sucursal.setId_gasolinera(editIdGasolinera.getText().toString());
		sucursal.setNombre_sucursal(editNombresucursal.getText().toString());
		sucursal.setDireccion(editdireccion.getText().toString());
		sucursal.setTelefono(editTelefono.getText().toString());
		
		helper.abrir();
		String estado = helper.actualizar(sucursal);
		helper.cerrar();
		
		Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
		
		editIdsucursal.setText("");
		editIdGasolinera.setText("");
		editNombresucursal.setText("");
		editdireccion.setText("");
		editTelefono.setText("");
	}
}