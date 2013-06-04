package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class VaplicadosInsertarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vaplicados_insertar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vaplicados_insertar, menu);
		return true;
	}

}
