package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ActualizarTipoDeCombustibleActivity extends Activity {
	ControlBD helper;
	EditText editId;
	EditText editNombre;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actualizar_tipo_de_combustible);
		helper = new ControlBD(this);
		editId = (EditText) findViewById(R.id.editText1);
		editNombre = (EditText) findViewById(R.id.editText2);
		
	}
	
	//aqui tengo que verificar que se actualizen los demas registros asociados a la modificacion
	public void actualizarTipoCombustible(View v) {
		ClaseTipoDeCombustible TC = new ClaseTipoDeCombustible();
		TC.setID_TIPO(editId.getText().toString());
		TC.setNOMBRE_GAS(editNombre.getText().toString());
		helper.abrir();
		String estado = helper.actualizar(TC);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
		}
	
	public void limpiarTexto(View v) {
		editId.setText("");
		editNombre.setText("");
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actualizar_tipo_de_combustible, menu);
		return true;
	}

}

