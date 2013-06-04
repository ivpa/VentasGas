package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarGasolineraActivity extends Activity {

	ControlBD controlhelper;
	EditText editIdGasolinera;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_gasolinera);
		
		controlhelper=new ControlBD (this);
		editIdGasolinera=(EditText)findViewById(R.id.idEliminarGas);
	}

	public void eliminarGasolinera(View v){
		
		String regEliminadas;
		
		Gasolinera gasolinera=new Gasolinera();
		gasolinera.setId_gasolinera(editIdGasolinera.getText().toString());
		
		controlhelper.abrir();
		regEliminadas=controlhelper.eliminar(gasolinera);
		controlhelper.cerrar();
		
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		
		editIdGasolinera.setText("");
	}
	
}
