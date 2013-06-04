package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ValesConsultarActivity extends Activity {
	ControlBD helper;
	EditText editIdVale;
	EditText editIdEmision;
	EditText editCantidad;
	EditText editValor;
	EditText editPromocion;
	EditText editFecha;
	Spinner spiner1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vales_consultar);
helper=new ControlBD(ValesConsultarActivity.this);
		
		
		editIdVale=(EditText)findViewById(R.id.idEmisionValesConsultar);
		editIdEmision=(EditText)findViewById(R.id.idEmisionValesConsultar);
		editCantidad=(EditText)findViewById(R.id.CantidadValesConsultar);
		editValor=(EditText)findViewById(R.id.ValorValesConsultar);
		editPromocion=(EditText)findViewById(R.id.PromocionValesConsultar);
		editFecha=(EditText)findViewById(R.id.fehcaValesConsultar);
	}
public void consultarVale(View v){
		
		
		// TODO Auto-generated method stub
				 helper.abrir();	 
					Vales vales = helper.consultarVales(editIdVale.getText().toString());
					 
					 helper.cerrar();
					 if(vales == null)
						 Toast.makeText(this, "vale con ID " + editIdVale.getText().toString() + 
								 " no encontrado", Toast.LENGTH_LONG).show();
					 else{
						 editIdEmision.setText(vales.getId_emision_vale());
						editCantidad.setText(String.valueOf(vales.getCantidad()));
						 editValor.setText(String.valueOf(vales.getValor()));
						 editPromocion.setText(vales.getPromocion());
						 editFecha.setText(vales.getFecha_venc());
						 
					 }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vales_consultar, menu);
		return true;
	}

}
