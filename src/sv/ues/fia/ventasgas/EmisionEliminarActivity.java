package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmisionEliminarActivity extends Activity {
	EditText editIdEmision;
	ControlBD helper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emision_eliminar);
		helper=new ControlBD(this);
		editIdEmision=(EditText)findViewById(R.id.IdEmisionEliminar);
	}
	public void eliminarEmision(View view){
		EmisionVales EV=new EmisionVales();
		EV.setId_emision_vales(editIdEmision.getText().toString());
		String regEliminadas;
		helper.abrir();
		regEliminadas=helper.eliminar(EV)  ;
    helper.cerrar();
    
    
    	Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.emision_eliminar, menu);
		return true;
	}

}
