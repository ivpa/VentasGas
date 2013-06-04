package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuPrincipalActivity extends Activity implements OnItemClickListener{

	String values[]={"Gestionar Venta","Gestionar Productos","Gestionar Vales","Gestionar Clientes","Gestionar Gasolinera y Sucursales"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
		
		ArrayAdapter<String> adaptador =new
		ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,values);
		
		ListView listVi =(ListView)findViewById(R.id.listViewPrincipal);
		listVi.setAdapter(adaptador);
		listVi.setOnItemClickListener(this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		String values2[]={"RegistrarVentaActivity","MenuProductosActivity","ValesActivity",
						  "ClientesActivity","GasolineraActivity"};
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