package sv.ues.fia.ventasgas;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;

@SuppressLint("SimpleDateFormat")
public class RegistrarVentaActivity extends Activity  implements OnItemSelectedListener{
	
	
	
	//Para crear la base de datos
	ControlBD BDHelper;
	
	//Atributos a utilizar
	double precioGas  =0;
	double precioLubN =0;
	double precioLubP =0;
	double total      =0;
	double total_gas  =0;
	double total_lub  =0;
	double cantgas    =0;
	double cantln     =0;
	double cantlp     =0;
	
	//constantes por default
	int gasActivado   = 0;
	int lubNActivado  = 0;
	int lubPActivado  = 0;
	int val10Activado = 0;
	int val20Activado = 0;
	
	//Para los vales y los tipos de pago
	String idval20      = "N/A";
	String idval10      = "N/A";
	String productos    = "";
	String tipo_pago    = "EFE";
	String id_vale_apli = "N/A";
	
	//Para tener la hora y fecha del sistema
	String fecha;
	String hora;
	
	//Para id's
	String id_venta;
	String id_vale = "N/A";
	int acu = 1;
	String id_detalle_venta;
	String idp = "";
	
	//String para llenar los Spinners
	String selectEmpleados    = "SELECT id_empleado AS _id, id_empleado FROM EMPLEADO";
	String selectClientes     = "SELECT id_cliente AS _id, id_cliente FROM CLIENTE";
	String selectSucursal     = "SELECT id_sucursal AS _id, id_sucursal FROM SUCURSAL";
	String selectTipoGasolina = "SELECT nombre_gas AS _id, nombre_gas FROM TIPO_COMBUSTIBLE";
	
	String from1 [] = new String[]{"_id"};
	int[] to = new int[]{android.R.id.text1};
	
	//String para sacar los datos de los Spinners
	String idemp;
	String idcli;
	String idsuc;
	String tipog;
	
	//Edit para sacar los datos
	EditText edtgalon;
	EditText edtlubn;
	EditText edtlubp;
	EditText edtv20;
	EditText edtv10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrar_venta);
		
		//Para abrir la base de datos
		BDHelper = new ControlBD(this);			
		BDHelper.abrir();
		
		//Paso 1 Cargar todos los spiners
		//Aqui Cargara los Spinners
		Spinner spinnerIDempleados = (Spinner)findViewById(R.id.spinner1);
		Spinner spinnerIDclientes = (Spinner)findViewById(R.id.spinner2);
		Spinner spinnerSucursal = (Spinner)findViewById(R.id.spinner3);
		Spinner spinnerTipogasolina= (Spinner)findViewById(R.id.spinner4);
		
		//Llenando el Spinner de los id_empleados
		Cursor c = BDHelper.llenarSpinner(selectEmpleados);
		@SuppressWarnings("deprecation")
		SimpleCursorAdapter Adapter1 = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item, c, from1, to);
	    Adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinnerIDempleados.setAdapter(Adapter1);
	    
	    spinnerIDempleados.setOnItemSelectedListener(this);
	    
	    //Llenando el Spinner de los id_clientes
	  	c = BDHelper.llenarSpinner(selectClientes);
	  	@SuppressWarnings("deprecation")
	  	SimpleCursorAdapter Adapter2 = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item, c, from1, to);
	  	Adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	  	spinnerIDclientes.setAdapter(Adapter2);
	  	spinnerIDclientes.setOnItemSelectedListener(this);
	  	
	    //Llenando el Spinner de los id_sucursal
	  	c = BDHelper.llenarSpinner(selectSucursal);
	  	@SuppressWarnings("deprecation")
	  	SimpleCursorAdapter Adapter3 = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item, c, from1, to);
	  	Adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	  	spinnerSucursal.setAdapter(Adapter3);
	  	spinnerSucursal.setOnItemSelectedListener(this);
	  	
	    //Llenando el Spinner de los tipo_gasolina
	  	c = BDHelper.llenarSpinner(selectTipoGasolina);
	  	@SuppressWarnings("deprecation")
	  	SimpleCursorAdapter Adapter4 = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item, c, from1, to);
	  	Adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	  	spinnerTipogasolina.setAdapter(Adapter4);
	  	spinnerTipogasolina.setOnItemSelectedListener(this);
	  	
	  	EditText edtg = (EditText)findViewById(R.id.edtCantGal);
	  	EditText edtln = (EditText)findViewById(R.id.edtCantLubN);
	  	EditText edtlp = (EditText)findViewById(R.id.edtCantLubP);
	  	EditText edtv1 = (EditText)findViewById(R.id.edtIDval10);
	  	EditText edtv2 = (EditText)findViewById(R.id.edtIDval20);
	  	edtg.setEnabled(false);
	  	edtln.setEnabled(false);
	  	edtlp.setEnabled(false);
	  	edtv1.setEnabled(false);
	  	edtv2.setEnabled(false);	  
	}
	
	public void habilitarCantidadGas(View v){
		CheckBox gCheck   = (CheckBox)findViewById(R.id.checkBoxGas);
		EditText edtg = (EditText)findViewById(R.id.edtCantGal);
		
	  	if(gCheck.isChecked())
	  		edtg.setEnabled(true);
	  	else
	  		edtg.setEnabled(false);
	}
	
	public void habilitarCantidadLubN(View v){
		CheckBox lnCheck   = (CheckBox)findViewById(R.id.checkBoxLubNor);
		EditText edtln = (EditText)findViewById(R.id.edtCantLubN);
		
	  	if(lnCheck.isChecked())
	  		edtln.setEnabled(true);
	  	else
	  		edtln.setEnabled(false);
	}
	
	public void habilitarCantidadLubP(View v){
		CheckBox lpCheck   = (CheckBox)findViewById(R.id.checkBoxLubPre);
		EditText edtlp = (EditText)findViewById(R.id.edtCantLubP);
		
	  	if(lpCheck.isChecked())
	  		edtlp.setEnabled(true);
	  	else
	  		edtlp.setEnabled(false);
	}
	
	public void habilitarCantidadVal10(View v){
		CheckBox v1Check   = (CheckBox)findViewById(R.id.checkBoxVale10);
		EditText edtv1 = (EditText)findViewById(R.id.edtIDval10);
		
	  	if(v1Check.isChecked())
	  		edtv1.setEnabled(true);
	  	else
	  		edtv1.setEnabled(false);
	}
	
	public void habilitarCantidadVal20(View v){
		CheckBox v2Check   = (CheckBox)findViewById(R.id.checkBoxVale20);
		EditText edtv2 = (EditText)findViewById(R.id.edtIDval20);
		
	  	if(v2Check.isChecked())
	  		edtv2.setEnabled(true);
	  	else
	  		edtv2.setEnabled(false);
	}
	
	
	
	public void onItemSelected(AdapterView<?> parent, android.view.View view, int pos,long id) {
		if (parent.getId() == R.id.spinner4) 
			tipog = parent.getSelectedItem().toString();		
		else if (parent.getId() == R.id.spinner1) 
			idemp = parent.getSelectedItem().getClass().toString();
		else if (parent.getId() == R.id.spinner2) 
				idcli = parent.getSelectedItem().getClass().toString();
		else if (parent.getId() == R.id.spinner3) 
   		   idsuc = parent.getSelectedItem().getClass().toString();
				
		Toast.makeText(this, "Valor id empleado: " + idemp, Toast.LENGTH_SHORT).show();
		//Toast.makeText(this, "Valor id cliente: " + idcli, Toast.LENGTH_SHORT).show();
		//Toast.makeText(this, "Valor id cucursal: " + idsuc, Toast.LENGTH_SHORT).show();
		//Toast.makeText(this, "Valor gasolina: " + tipog, Toast.LENGTH_SHORT).show();
		}
	
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
			// Do nothing.
	}
	
	
	//Paso 3 Obtencion de precios actuales de productos
	public double precioActual(String producto, String tipo){
		
		double p;
		
		if(producto == "gas")
		{
			if(tipo == "sup")
			   idp = "GAS-01";
			
			if(tipo == "reg")
			   idp = "GAS-02";
			
			if(tipo == "die")
			   idp = "GAS-03";			
		}
		else
		{
			if(tipo == "pre")
			   idp = "LUB-01";
			
			if(tipo == "nor")
			   idp = "LUB-02";
		}
		
		p = BDHelper.precio(idp);
		
		Toast.makeText(this, "Valor id empleado: " + idemp, Toast.LENGTH_LONG).show();
		
		return p;	
	}
		
	public void calculoTotales(String pro, String tip){
		
		if(pro == "gas")
		{
			if(tip == "sup")
				precioGas  = precioActual("gas","sup");
			
			if(tip == "reg")
				precioGas  = precioActual("gas","reg");
			
			if(tip == "die")
				precioGas  = precioActual("gas","die");			
		}
		else
		{
			if(pro == "lub"){
				precioLubN  = precioActual("LUB","nor");
				precioLubP  = precioActual("LUB","pre");				
			}
			
		}
				
		total_gas = precioGas * cantgas;
		total_lub = (precioLubN * cantln) + (precioLubP * cantlp);
		total = total_gas + total_lub;		
	}	
		
	
	public void aplicarValesDescuentos(double t, double v){
		
		double dif;
		
		if(v > t){
			dif = v - t;
			Toast.makeText(this, "La transaccion se ha completado, su cambio es de: $" + dif, Toast.LENGTH_LONG).show();
			Toast.makeText(this, "Gracias por preferirnos", Toast.LENGTH_LONG).show();
			tipo_pago = "VAL";
		}			
		
		if(v < t){
			dif = t - v;
			Toast.makeText(this, "Se necesita abonar: $" + dif + " a la cuenta.", Toast.LENGTH_LONG).show();
			tipo_pago = "MIX";
		}		
		
		if(v == t)
			Toast.makeText(this, "La transaccion se ha completado. Gracias por preferirnos!!!", Toast.LENGTH_LONG).show();		
	}	
	
	public void crearCadena(){
		
		if(gasActivado == 1)
			productos = productos + "gasolina, " + tipog;
		
		if(lubNActivado == 1)
			productos = productos + "lubricante normal, ";
		
		if(lubPActivado == 1)
			productos = productos + "lubricante premiun ";
			
	}	
	
	@SuppressLint("SimpleDateFormat")
	public void actionButton(View v){		
				
		CheckBox gasCheck   = (CheckBox)findViewById(R.id.checkBoxGas);
		CheckBox lubNCheck  = (CheckBox)findViewById(R.id.checkBoxLubNor);
		CheckBox lubPCheck  = (CheckBox)findViewById(R.id.checkBoxLubPre);
		CheckBox val10Check = (CheckBox)findViewById(R.id.checkBoxVale10);
		CheckBox val20Check = (CheckBox)findViewById(R.id.checkBoxVale20);
		
		//Sacando los valores de los Spinners
		Spinner spinnerIDemp = (Spinner)findViewById(R.id.spinner1);
		Spinner spinnerIDcli = (Spinner)findViewById(R.id.spinner2);
		Spinner spinnerIDsuc = (Spinner)findViewById(R.id.spinner3);
		Spinner spinnertipog = (Spinner)findViewById(R.id.spinner4);
		
		idemp = spinnerIDemp.getSelectedItem().toString();
		idcli = spinnerIDcli.getSelectedItem().toString();
		idsuc = spinnerIDsuc.getSelectedItem().toString();
		tipog = spinnertipog.getSelectedItem().toString();
						
		//Paso 4 Obtener valores de la activitys
		//Se hace cuando se da click en el boton 'calcular monto'		
		
		if(gasCheck.isChecked()){
			edtgalon = (EditText)findViewById(R.id.edtCantGal);
			if("".equals(edtgalon.getText().toString()))
				cantgas = 0;
			else
				cantgas = Double.parseDouble(edtgalon.getText().toString());
		}	
		
		if(lubNCheck.isChecked() ){
			edtlubn  = (EditText)findViewById(R.id.edtCantLubN);
			if("".equals(edtlubn.getText().toString()))
				cantln = 0;
			else
				cantln  = Double.parseDouble(edtlubn.getText().toString());
		}		
		
		if(lubPCheck.isChecked()){
			edtlubp  = (EditText)findViewById(R.id.edtCantLubP);
			if("".equals(edtlubp.getText().toString()))
				cantlp  = 0;
			else
				cantlp  = Double.parseDouble(edtlubp.getText().toString());
		}		
		
		if(val20Check.isChecked()){
			edtv20   = (EditText)findViewById(R.id.edtIDval20);
			if("".equals(edtv20.getText().toString()))
				idval20 = "";
			else
				idval20 = edtv20.getText().toString();
		}		
		
		if(val10Check.isChecked()){
			edtv10   = (EditText)findViewById(R.id.edtIDval10);
			if("".equals(edtv10.getText().toString()))
				idval10 = "";
			else
				idval10 = edtv10.getText().toString();
		}
		
		//Paso 5 Calcular montos parciales y monto total de la venta
		if(tipog == "super" && gasCheck.isChecked())
			calculoTotales("gas", "sup");
		
		if(tipog == "regular" && gasCheck.isChecked())
			calculoTotales("gas", "reg");
		
		if(tipog == "diesel" && gasCheck.isChecked())
			calculoTotales("gas", "die");
		
		if(lubPCheck.isChecked())
			calculoTotales("lub", "pre");
		
		if(lubNCheck.isChecked())
			calculoTotales("lub", "nor");
		
		//Paso 6 Verificacion de aplicacion de vales de descuentos
		//Verificacion de vales
		if(val20Check.isChecked()){
			aplicarValesDescuentos( total , 20);
			id_vale_apli = idval20 + "-" +acu;
			id_vale = idval20;
		}	
		
		if(val10Check.isChecked()){
			aplicarValesDescuentos( total , 10);
			id_vale_apli = idval10;
			id_vale_apli = idval10 + "-" +acu;
			id_vale = idval10;
		}
		
		//paso 8 Obtener cadena 'productos'
		//Crear cadena de productos
		crearCadena();
		
		//Paso 7 Obtencion de hora y fecha del sistema	
		
		//Fecha
		Calendar cal = new GregorianCalendar();
		Date date = cal.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd");
		fecha = df.format(date);
		
		//Hora
		Calendar cal1 = new GregorianCalendar();
		Date date1 = cal1.getTime();
		SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
		hora = df1.format(date1);
		
		//Crear clases para su posterior insercion el las tablas
		Venta venta = new Venta();
		DetalleVenta detalle = new DetalleVenta();
		ValesAplicados vale = new ValesAplicados();
		
		//Construyendo id's
		id_venta = fecha + "-" + acu;
		id_detalle_venta = fecha.substring(0, 7) + acu;				
		
		//Insercion de datos en las clases
		venta.setId_cliente(idcli);
		venta.setId_empleado(idemp);
		venta.setId_sucursal(idsuc);
		venta.setMonto(total);
		venta.setId_tipo_venta(tipo_pago);
		venta.setId_vale_apli(id_vale);
		venta.setId_venta(id_venta);
		
		detalle.setId_detalle_venta(id_detalle_venta);
		detalle.setId_venta(id_venta);
		detalle.setId_producto(idp);
		detalle.setMonto(total);
		detalle.setFecha_venta(fecha);
		detalle.setHora_venta(hora);		
		detalle.setProductos(productos);
		
		if(val20Check.isChecked() == true || val10Check.isChecked() == true){
			vale.setId_vale_apli(id_vale_apli);
			vale.setId_vales(id_vale);
			vale.setCantidad_apli(1);
			vale.setFecha_apli(fecha);			
		}
		
		
		//Paso 9 ingresar los datos a las tablas afectadas
		//insertando a la base de datos
		BDHelper.insertar(detalle);
		BDHelper.insertar(vale);
		BDHelper.insertar(venta);
		
		//Aumentando el acumulador que genera coorelativos para los id`s
		acu++;
		
		Toast.makeText(this, "La transaccion se ha completado.", Toast.LENGTH_LONG).show();
		
		Toast.makeText(this, "Valor id empleado: " + idemp, Toast.LENGTH_LONG).show();

	}
}





