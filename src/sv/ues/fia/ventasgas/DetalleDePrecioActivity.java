package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class DetalleDePrecioActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_de_precio);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}
	
	public void insertar(View V){
		Intent intento = new Intent(this, InsertarDetalleDePrecioActivity.class);
		startActivity(intento);
	}
	
	public void actualizar(View V){
		Intent intento = new Intent(this, ActualizarDetalleDePrecioActivity.class);
		startActivity(intento);
	}
	
		
	public void consultar(View V){
		Intent intento = new Intent(this, ConsultarDetalleDePrecioActivity.class);
		startActivity(intento);
	}
	
	public void eliminar(View V){
		Intent intento = new Intent(this, EliminarDetalleDePrecioActivity.class);
		startActivity(intento);
	}


}
