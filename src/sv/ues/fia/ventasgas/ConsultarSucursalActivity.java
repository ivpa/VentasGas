package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class ConsultarSucursalActivity extends Activity {

	ControlBD helper;
	
	EditText editIdSucursal;
	TextView textIdGasolinera;
	TextView textNombreSucursal;
	TextView textDireccion;
	TextView textTelefono;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultar_sucursal);
		
		helper = new ControlBD(this);
		editIdSucursal = (EditText)findViewById(R.id.idConsultarSuc);
		textIdGasolinera = (TextView)findViewById(R.id.idGasolineraConsultarSuc);
		textNombreSucursal = (TextView)findViewById(R.id.nombreConsultarSuc);
		textDireccion = (TextView)findViewById(R.id.direccionConsultarSucursal);
		textTelefono = (TextView)findViewById(R.id.telefonoConsultarSuc);
	}
		
	public void consultarSucursal(View v)
	{
			
		helper.abrir();
		Sucursal sucursal=helper.consultarSucursal(editIdSucursal.getText().toString());
		helper.cerrar();
		
		if(sucursal == null)
		{
			Toast.makeText(this, "ID DE SUCURSAL " + editIdSucursal.getText().toString() + " no encontrada", Toast.LENGTH_LONG).show();
			editIdSucursal.setText("");
			textIdGasolinera.setText("");
			textNombreSucursal.setText("");
			textDireccion.setText("");
			textTelefono.setText("");
		}	
		else
		{
			textIdGasolinera.setText(sucursal.getId_gasolinera());
			textNombreSucursal.setText(sucursal.getNombre_sucursal());
			textDireccion.setText(sucursal.getDireccion());
			textTelefono.setText(sucursal.getTelefono());
		}
	}
}
