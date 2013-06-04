package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnClickListener;

public class GasolineraActivity extends Activity implements OnItemClickListener{ 

	String values[]={"Ingresar Gasolinera","Eliminar Gasolinera","Modificar datos Gasolinera","Consultar Datos Gasolinera"};
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gasolinera);
		
		ArrayAdapter<String> adaptador =new	ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,values);
				
		ListView listVi =(ListView)findViewById(R.id.listViewGasolinera);
		listVi.setAdapter(adaptador);
		listVi.setOnItemClickListener(this);
		
		Button boton = (Button)findViewById(R.id.botonGestionarSucursales);
				
		boton.setOnClickListener(new OnClickListener() {
	         public void onClick(View v) {
	            Intent intent = new Intent(GasolineraActivity.this, SucursalesActivity.class);
	            startActivity(intent);
	         }
	    });
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		String values2[]={"AgregarGasolineraActivity","EliminarGasolineraActivity","ModificarGasolineraActivity", "ConsultarGasolineraActivity"};
		String nombreValue=values2[position];
		
		try{
			Class<?> clase=Class.forName("sv.ues.fia.ventasgas."+nombreValue);
			Intent inte = new Intent(this,clase);
			this.startActivity(inte);
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	
}

