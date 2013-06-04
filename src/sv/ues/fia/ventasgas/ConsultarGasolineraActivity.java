package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConsultarGasolineraActivity extends Activity {

	ControlBD helper;
	EditText editIdGasolinera;
	TextView textEmpresa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultar_gasolinera);
		
		helper=new ControlBD(this);
		editIdGasolinera = (EditText) findViewById(R.id.idConsultarGas);
		textEmpresa = (TextView) findViewById(R.id.empresaConsultarGas);
	}
	
	public void consultarGasolinera(View v) {
		
		helper.abrir();
		Gasolinera gasolinera = helper.consultarGasolinera(editIdGasolinera.getText().toString());
		helper.cerrar();
		
		if(gasolinera == null)
		{
			Toast.makeText(this, "ID_GASOLINERA " +	editIdGasolinera.getText().toString() +	" no encontrado", Toast.LENGTH_LONG).show();
			
			editIdGasolinera.setText("");
			textEmpresa.setText("");
		}
		else
		{
			
			textEmpresa.setText(gasolinera.getEmpresa());
		}
	}
	
}