package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ValesActualizarActivity extends Activity {
	ControlBD helper;
	EditText editIdVale;
	EditText editIdEmision;
	EditText editCantidad;
	EditText editValor;
	EditText editPromocion;
	EditText editFecha;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vales_actualizar);
		helper=new ControlBD(this);
		editIdVale=(EditText)findViewById(R.id.IdValeActualizar);
		editIdEmision=(EditText)findViewById(R.id.IdEmisionValeActualizar);
		editCantidad=(EditText)findViewById(R.id.CantidadValesActualizar);
		editValor=(EditText)findViewById(R.id.ValorValesActualizar);
		editPromocion=(EditText)findViewById(R.id.PromocionValesActualizar);
		editFecha=(EditText)findViewById(R.id.FechaValesActualizar);
	}
	public void actualizarVale(View view){
		String idVale=editIdVale.getText().toString();
		String idEmision=editIdEmision.getText().toString();
		String cantidad=editCantidad.getText().toString();
		String valor=editValor.getText().toString();
		String promocion=editPromocion.getText().toString();
		String fecha=editFecha.getText().toString();
		
		Vales v=new Vales();
		v.setId_vales(idVale);
		v.setId_emision_vale(idEmision);
		v.setCantidad(Integer.parseInt(cantidad));
		v.setValor(Double.parseDouble(valor));
		v.setPromocion(promocion);
		v.setFecha_venc(fecha);
		helper.abrir();
		String estado = helper.actualizar(v);
		helper.cerrar();
		
		Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vales_actualizar, menu);
		return true;
	}

}
