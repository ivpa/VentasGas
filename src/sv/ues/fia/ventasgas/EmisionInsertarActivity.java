package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmisionInsertarActivity extends Activity {
	ControlBD helper;
	EditText editIdEmisionVale;
	EditText editCantidadEmitida;
	EditText editValor;
	EditText editFechaEmision;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emision_insertar);
		helper=new ControlBD(this);
		editIdEmisionVale=(EditText)findViewById(R.id.IdEmisionInsertar);
		editCantidadEmitida=(EditText)findViewById(R.id.CantidadEmisionInsertar);
		editValor=(EditText)findViewById(R.id.ValorEmisionInsertar);
		editFechaEmision=(EditText)findViewById(R.id.FechaEmisionInsertar);
	}
public void insertarEmisionVale(View view){
		
		String idEmisionVale=editIdEmisionVale.getText().toString();
		String cantidad=editCantidadEmitida.getText().toString();
		String valor=editValor.getText().toString();
		String fechaemision=editFechaEmision.getText().toString();
		String regInsertados;
		EmisionVales ev=new EmisionVales();
		ev.setId_emision_vales(idEmisionVale);
		ev.setCantidad_emitida(Integer.parseInt(cantidad));
		ev.setValor(Double.parseDouble(valor));
		ev.setFecha_emision(fechaemision);
		
		helper.abrir();
		regInsertados=helper.insertar(ev);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.emision_insertar, menu);
		return true;
	}

}
