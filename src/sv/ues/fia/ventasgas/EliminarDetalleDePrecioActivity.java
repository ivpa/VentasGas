package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;


public class EliminarDetalleDePrecioActivity extends Activity {
	EditText editId;
	ControlBD controlhelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_detalle_de_precio);
		controlhelper=new ControlBD (this);
		editId=(EditText)findViewById(R.id.editText1);
	}
	
	public void eliminarDetallePrecio(View v){
		String regEliminadas;
		ClaseDetallePrecio DP=new ClaseDetallePrecio();
		DP.setID_DETALLE(editId.getText().toString());
		controlhelper.abrir();
		regEliminadas=controlhelper.eliminar(DP);
		controlhelper.cerrar();
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eliminar_detalle_de_precio, menu);
		return true;
	}

}
