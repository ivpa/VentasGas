package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;


public class EliminarTipoDeCombustibleActivity extends Activity {
	EditText editId;
	ControlBD controlhelper;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_tipo_de_combustible);
		controlhelper=new ControlBD (this);
		editId=(EditText)findViewById(R.id.editText1);
	}

	//aqui tengo que eliminar todos los registros asociados a este tambien
	public void eliminarTipoCombustible(View v){
		String regEliminadas;
		ClaseTipoDeCombustible TC=new ClaseTipoDeCombustible();
		TC.setID_TIPO(editId.getText().toString());
		controlhelper.abrir();
		regEliminadas=controlhelper.eliminar(TC);
		controlhelper.cerrar();
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eliminar_tipo_de_combustible, menu);
		return true;
	}

}
