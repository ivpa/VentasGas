package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
;

public class EliminarListaDePrecioActivity extends Activity {

	EditText editId;
	ControlBD controlhelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_lista_de_precio);
		controlhelper=new ControlBD (this);
		editId=(EditText)findViewById(R.id.editText1);
	}
	
	public void eliminarListaPrecio(View v){
		String regEliminadas;
		ClaseListaPrecio LP=new ClaseListaPrecio();
		LP.setID_PRECIO(editId.getText().toString());
		controlhelper.abrir();
		regEliminadas=controlhelper.eliminar(LP);
		controlhelper.cerrar();
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		}

}

