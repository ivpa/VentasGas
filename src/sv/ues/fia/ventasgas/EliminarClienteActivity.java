package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarClienteActivity extends Activity {

	EditText editIdcliente;
	ControlBD controlhelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_cliente);
		
		controlhelper=new ControlBD (this);
	    editIdcliente=(EditText)findViewById(R.id.idEliminarCliente);
	}

	public void eliminarCliente(View v)
	{
		String regEliminadas;
		Cliente cliente=new Cliente();
	    cliente.setId_cliente(editIdcliente.getText().toString());
		
	    controlhelper.abrir();
		regEliminadas=controlhelper.eliminar(cliente);
		controlhelper.cerrar();
		
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
	}
}
