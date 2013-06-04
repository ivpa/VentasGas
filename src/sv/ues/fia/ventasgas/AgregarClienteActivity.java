package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarClienteActivity extends Activity {

	ControlBD helper;
	EditText editIdCliente;
	EditText editPrimerNombre;
	EditText editSegundoNombre;
	EditText editTercerNombre;
	EditText editPrimerApellido;
	EditText editSegundoApellido;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregar_cliente);
		
		helper=new ControlBD(this);
		editIdCliente=(EditText)findViewById(R.id.idAgregarCliente);
		editPrimerNombre=(EditText)findViewById(R.id.primerNombreAgregarCliente);
		editSegundoNombre=(EditText)findViewById(R.id.segundoNombreAgregarCliente);
		editTercerNombre=(EditText)findViewById(R.id.tercerNombreAgregarCliente);
		editPrimerApellido=(EditText)findViewById(R.id.primerApellidoAgregarCliente);
		editSegundoApellido=(EditText)findViewById(R.id.segundoApellidoAgregarCliente);
	}

	public void insertarCliente(View v) 
	{
		String idcliente=editIdCliente.getText().toString();
		String primernombre=editPrimerNombre.getText().toString();
		String segundonombre=editSegundoNombre.getText().toString();
		String tercernombre=editTercerNombre.getText().toString();
		String primerapellido=editPrimerApellido.getText().toString();
		String segundoapellido=editSegundoApellido.getText().toString();
		String regInsertados;
		Cliente cliente=new Cliente();
		cliente.setId_cliente(idcliente);
		cliente.setPrimer_nombre(primernombre);
		cliente.setSegundo_nombre(segundonombre);
		cliente.setTercer_nombre(tercernombre);
		cliente.setPrimer_apellido(primerapellido);
		cliente.setSegundo_apellido(segundoapellido);
		helper.abrir();
		regInsertados=helper.insertar(cliente);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
		
		editIdCliente.setText("");
		editPrimerNombre.setText("");
		editSegundoNombre.setText("");
		editTercerNombre.setText("");
		editPrimerApellido.setText("");
		editSegundoApellido.setText("");
	}

}
