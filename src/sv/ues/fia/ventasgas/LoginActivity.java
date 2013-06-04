package sv.ues.fia.ventasgas;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class LoginActivity extends Activity {

	ControlBD BDHelper;
	EditText editUser;
	EditText editPassword;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		editUser = (EditText) findViewById(R.id.empresaAgregarGas);
		editPassword = (EditText) findViewById(R.id.nombreAgregarSucursal);
		//Para crear la base de datos
		BDHelper = new ControlBD(this);
	
		BDHelper.abrir();
		BDHelper.llenarVentasGas();
		BDHelper.cerrar();
	}
	
	public void actionButton(View v){
		
		String usuario="admin";
		String clave="admin";
		String actividad = "MenuPrincipalActivity";
		if(usuario.equals(editUser.getText().toString()) && clave.equals(editPassword.getText().toString()) ){
		
			try{
				Toast.makeText(this, "Usuario autorizado", Toast.LENGTH_SHORT).show();
				Class<?> clase=Class.forName("sv.ues.fia.ventasgas."+actividad);
				Intent inte = new Intent(this,clase);
				this.startActivity(inte);
				
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			
		}else{
		Toast.makeText(this, ">> usuario=admin y clave=admin", Toast.LENGTH_SHORT).show();	
		
		editUser.setText("");
		editPassword.setText("");
		}
    }		

}