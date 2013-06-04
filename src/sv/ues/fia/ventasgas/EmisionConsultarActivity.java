package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmisionConsultarActivity extends Activity {
	ControlBD helper;
	EditText editIdEmision;
	EditText editCantidad;
	EditText editValor;
	EditText editFechaEmision;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emision_consultar);
		helper=new ControlBD(this);
		editIdEmision=(EditText)findViewById(R.id.IdEmisionConsultar);
		editCantidad=(EditText)findViewById(R.id.CantidadEmisionConsultarl);
		editValor=(EditText)findViewById(R.id.ValorEmisionConsultar);
		editFechaEmision=(EditText)findViewById(R.id.FechaEmisionConsultar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.emision_consultar, menu);
		return true;
	}
	public void consultarEmision(View v){
		 helper.abrir();	 
			EmisionVales Evales = helper.consultarEmisionVales(editIdEmision.getText().toString());
			 
			 helper.cerrar();
			 if(Evales == null)
				 Toast.makeText(this, "vale con ID " + editIdEmision.getText().toString() + 
						 " no encontrado", Toast.LENGTH_LONG).show();
			 else{
				 
				editCantidad.setText(String.valueOf(Evales.getCantidad_emitida()));
				 editValor.setText(String.valueOf(Evales.getValor()));
				 
				 editFechaEmision.setText(Evales.getFecha_emision());
				 
			 }
		
	}
}
