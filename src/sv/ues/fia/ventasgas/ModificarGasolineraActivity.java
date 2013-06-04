package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

public class ModificarGasolineraActivity extends Activity {

	ControlBD helper;
	
	EditText idGasolinera;
	EditText empresa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modificar_gasolinera);
		
		helper = new ControlBD(this);
		
		idGasolinera = (EditText) findViewById(R.id.idModificarGas);
		empresa = (EditText) findViewById(R.id.empresaModificarGas);
	}
	
	public void actualizarGasolinera(View v)
	{
		Gasolinera gasolinera = new Gasolinera();
		gasolinera.setId_gasolinera(idGasolinera.getText().toString());
		gasolinera.setEmpresa(empresa.getText().toString());
		
		helper.abrir();
		String estado = helper.actualizar(gasolinera);
		helper.cerrar();
		
		Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
		
		idGasolinera.setText("");
		empresa.setText("");
	}

}
