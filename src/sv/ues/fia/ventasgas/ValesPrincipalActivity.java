package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ValesPrincipalActivity extends Activity implements OnItemClickListener {
	String values[]={"Insertar","Modificar","Eliminar","Consultar"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vales_principal);
		ArrayAdapter<String> adaptador =new
				ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,values);
		ListView listVi =(ListView)findViewById(R.id.listViewValesPrincipal);
		listVi.setAdapter(adaptador);
		listVi.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vales_principal, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		String values2[]={"ValesInsertarActivity","ValesActualizarActivity",
				"ValesEliminarActivity","ValesConsultarActivity"};
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

