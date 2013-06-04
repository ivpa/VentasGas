package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ProductosActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.productos, menu);
		return true;
	}
	public void insertar(View V){
		Intent intento = new Intent(this, InsertarProductoActivity.class);
		startActivity(intento);
	}
	
	public void actualizar(View V){
		Intent intento = new Intent(this, ActualizarProductosActivity.class);
		startActivity(intento);
	}
	
		
	public void consultar(View V){
		Intent intento = new Intent(this, ConsultarProductosActivity.class);
		startActivity(intento);
	}
	
	public void eliminar(View V){
		Intent intento = new Intent(this, EliminarProductosActivity.class);
		startActivity(intento);
	}

}
