package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ValesEliminarActivity extends Activity {
	EditText editIdVale;
	ControlBD helper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vales_eliminar);
		helper =new ControlBD(this);
		editIdVale=(EditText)findViewById(R.id.IdValesEliminar);
		
	}
	public void eliminarVale(View view){
		Vales vales =new Vales();
		vales.setId_vales(editIdVale.getText().toString());
		String regEliminadas;
		helper.abrir();
		regEliminadas=helper.eliminar(vales) ;
    helper.cerrar();
    
    
    	Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vales_eliminar, menu);
		return true;
	}

}
