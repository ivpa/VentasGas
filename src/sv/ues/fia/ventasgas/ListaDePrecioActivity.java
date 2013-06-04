package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ListaDePrecioActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_de_precio);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}
	
	public void insertar(View V){
		Intent intento = new Intent(this, InsertarListaDePrecioActivity.class);
		startActivity(intento);
	}
	
	public void actualizar(View V){
		Intent intento = new Intent(this, ActualizarListaDePrecioActivity.class);
		startActivity(intento);
	}
	
		
	public void consultar(View V){
		Intent intento = new Intent(this, ConsultarListaDePrecioActivity.class);
		startActivity(intento);
	}
	
	public void eliminar(View V){
		Intent intento = new Intent(this, EliminarListaDePrecioActivity.class);
		startActivity(intento);
	}

}
