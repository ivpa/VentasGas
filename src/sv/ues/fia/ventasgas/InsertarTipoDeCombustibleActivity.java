package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class InsertarTipoDeCombustibleActivity extends Activity {
	ControlBD helper;
	EditText editIdTipo;
	EditText editNombreGas;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar_tipo_de_combustible);
		helper = new ControlBD(this);
		editIdTipo = (EditText) findViewById(R.id.editText1);
		editNombreGas = (EditText) findViewById(R.id.editText2);
		
	}
	
	public void insertarTipoCombustible(View v) {
		String id = editIdTipo.getText().toString();
		String nombre = editNombreGas.getText().toString();
		
		String regInsertados;
		ClaseTipoDeCombustible TC = new ClaseTipoDeCombustible();
		TC.setID_TIPO(id);
		TC.setNOMBRE_GAS(nombre);
		helper.abrir();
		regInsertados = helper.insertar(TC);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
	}
	
	public void limpiarTexto(View v) {
		editIdTipo.setText("");
		editNombreGas.setText("");
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_tipo_de_combustible, menu);
		return true;
	}

}
