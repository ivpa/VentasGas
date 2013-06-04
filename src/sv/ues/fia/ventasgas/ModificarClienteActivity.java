package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarClienteActivity extends Activity {


	ControlBD helper;
	EditText editIdcliente;
	EditText editprimernombre;
	EditText editsegundonombre;
	EditText edittercernombre;
	EditText editprimerapellido;
	EditText editsegundoapellido;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modificar_cliente);
		
		helper=new ControlBD(this);
		editIdcliente=(EditText)findViewById(R.id.idModificarCliente);
	    editprimernombre=(EditText)findViewById(R.id.primerNombreModificarCliente);
	    editsegundonombre=(EditText)findViewById(R.id.segundoNombreModificarCliente);
	    edittercernombre=(EditText)findViewById(R.id.tercerNombreModificarCliente);
	    editprimerapellido=(EditText)findViewById(R.id.primerApellidoModificarCliente);
	    editsegundoapellido=(EditText)findViewById(R.id.segundoApellidoModificarCliente);
	}

	public void actualizarCliente(View v) 
	{
		Cliente cliente = new Cliente();
		cliente.setId_cliente(editIdcliente.getText().toString());
		cliente.setPrimer_nombre(editprimernombre.getText().toString());
		cliente.setSegundo_nombre(editsegundonombre.getText().toString());
		cliente.setTercer_nombre(edittercernombre.getText().toString());
		cliente.setPrimer_apellido(editprimerapellido.getText().toString());
		cliente.setSegundo_apellido(editsegundoapellido.getText().toString());

	
		helper.abrir();
		String estado = helper.actualizar(cliente);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
		
		editIdcliente.setText("");
		editprimernombre.setText("");
		editsegundonombre.setText("");
		edittercernombre.setText("");
		editprimerapellido.setText("");
		editsegundoapellido.setText("");
	}

}
