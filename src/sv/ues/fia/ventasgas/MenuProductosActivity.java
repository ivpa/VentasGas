package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MenuProductosActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_productos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}
	
	public void Productos(View V){
		Intent intento = new Intent(this, ProductosActivity.class);
		startActivity(intento);
	}
	
	public void DetalleDePrecio(View V){
		Intent intento = new Intent(this, DetalleDePrecioActivity.class);
		startActivity(intento);
	}
	
	public void ListaDePrecio(View V){
		Intent intento = new Intent(this, ListaDePrecioActivity.class);
		startActivity(intento);
	}
	
	public void TipoCombustible(View V){
		Intent intento = new Intent(this, TipoDeCombustibleActivity.class);
		startActivity(intento);
	}

}
