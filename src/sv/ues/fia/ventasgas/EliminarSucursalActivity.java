package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarSucursalActivity extends Activity {

	ControlBD controlhelper;
	EditText editIdSucursal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_sucursal);
		
		controlhelper=new ControlBD (this);
		editIdSucursal=(EditText)findViewById(R.id.idEliminarSuc);
	}

	public void eliminarSucursal(View v){
		String regEliminadas;
		
		Sucursal sucursal=new Sucursal();
		sucursal.setId_sucursal(editIdSucursal.getText().toString());
		
		controlhelper.abrir();
		regEliminadas=controlhelper.eliminar(sucursal);
		controlhelper.cerrar();
		
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		
		editIdSucursal.setText("");
	}

}