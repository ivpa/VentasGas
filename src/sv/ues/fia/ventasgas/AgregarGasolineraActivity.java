package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarGasolineraActivity extends Activity {


	ControlBD helper;
	EditText idGasolinera;
	EditText empresa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregar_gasolinera);
		
		helper = new ControlBD(this);
		
		idGasolinera = (EditText) findViewById(R.id.idAgregarGasolinera);
		empresa = (EditText) findViewById(R.id.empresaAgregarGasolinera);
	
	}

	public void agregarGasolinera(View v)
	{
		String idGas = idGasolinera.getText().toString();
		String empre = empresa.getText().toString();
		
		String regInsertados;
		
		Gasolinera gas = new Gasolinera();
		gas.setId_gasolinera(idGas);
		gas.setEmpresa(empre);
		
		helper.abrir();
		regInsertados = helper.insertar(gas);
		helper.cerrar();
		
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
		
		idGasolinera.setText("");
		empresa.setText("");
	}

}

