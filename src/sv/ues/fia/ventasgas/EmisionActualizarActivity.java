package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmisionActualizarActivity extends Activity {
	ControlBD helper;
	EditText editIdEmision;
	EditText editCantidad;
	EditText editValor;
	EditText editFechaEmision;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emision_actualizar);
		helper=new ControlBD(this);
		editIdEmision=(EditText)findViewById(R.id.idEmisionActualizar);
		editCantidad=(EditText)findViewById(R.id.CantidadEmitidaActualizar);
		editValor=(EditText)findViewById(R.id.valorEmisionActualizar);
		editFechaEmision=(EditText)findViewById(R.id.FechaEmisionActualizar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.emision_actualizar, menu);
		return true;
	}
	
	
	
	
	public void actualizarEmision(View view){
		EmisionVales EV=new EmisionVales();
		String estado;
		EV.setId_emision_vales(editIdEmision.getText().toString());
		EV.setCantidad_emitida(Integer.valueOf(editCantidad.getText().toString()));
		EV.setValor(Double.valueOf(editValor.getText().toString()));
		EV.setFecha_emision(editFechaEmision.getText().toString());
		
		helper.abrir();
		estado=helper.actualizar(EV);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
		
	}	

}