package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ConsultarTipoDeCombustibleActivity extends Activity {

	ControlBD helper;
	EditText editId;
	EditText editNombre;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultar_tipo_de_combustible);
		helper = new ControlBD(this);
		editId = (EditText) findViewById(R.id.editText1);
		editNombre = (EditText) findViewById(R.id.editText2);
		
	}
	
	public void consultarTCombustible(View v) {
		helper.abrir();
		ClaseTipoDeCombustible TC =
		helper.consultarTC(editId.getText().toString());
		helper.cerrar();
		if(TC == null)
		Toast.makeText(this, "Tipo Combustible con ID " +
		editId.getText().toString() +
		" no encontrado", Toast.LENGTH_LONG).show();
		else{
		editNombre.setText(TC.getNOMBRE_GAS());

		}
		}
		
		
	public void limpiarTexto(View v) {
		editId.setText("");
		editNombre.setText("");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consultar_tipo_de_combustible, menu);
		return true;
	}

}

