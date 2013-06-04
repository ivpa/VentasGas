package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConsultarClienteActivity extends Activity {

	ControlBD helper;
	EditText editIdcliente;
	TextView textprimernombre;
	TextView textsegundonombre;
	TextView texttercernombre;
	TextView textprimerapellido;
	TextView textsegundoapellido;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultar_cliente);
		
		helper=new ControlBD(this);
		editIdcliente=(EditText)findViewById(R.id.idConsultarCliente);
		textprimernombre=(TextView)findViewById(R.id.primerNombreConsultarCliente);
		textsegundonombre=(TextView)findViewById(R.id.segundoNombreConsultarCliente);
	    texttercernombre=(TextView)findViewById(R.id.tercerNombreConsultarCliente);
		textprimerapellido=(TextView)findViewById(R.id.primerApellidoConsultarCliente);
		textsegundoapellido=(TextView)findViewById(R.id.segundoApellidoConsultarCliente);
	}

public void consultarCliente(View v) {
		
		helper.abrir();
		Cliente cliente=helper.consultar(editIdcliente.getText().toString());
		helper.cerrar();
		
		if(cliente == null){
			Toast.makeText(this, "ID_CLIENTE" +	editIdcliente.getText().toString() +	" no encontrada", Toast.LENGTH_LONG).show();
			editIdcliente.setText("");
			textprimernombre.setText("");
			textsegundonombre.setText("");
			texttercernombre.setText("");
			textprimerapellido.setText("");
			textsegundoapellido.setText("");
		}
		else
		{
			textprimernombre.setText(cliente.getPrimer_nombre());
			textsegundonombre.setText(cliente.getSegundo_nombre());
			texttercernombre.setText(cliente.getTercer_nombre());
			textprimerapellido.setText(cliente.getPrimer_apellido());
			textsegundoapellido.setText(cliente.getSegundo_apellido());
		}
	}

}
