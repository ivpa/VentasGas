package sv.ues.fia.ventasgas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlBD {
	
	//Campos de las tablas de la base de datos
	private static final String[] camposTipoDeCombustible = new String[] {"ID_TIPO", "NOMBRE_GAS" };
	private static final String[] camposProductos = new String[] {"ID_PRODUCTO", "ID_TIPO","NOMBRE_PRODUCTO", "PRECIO_ACTUAL","EXISTENCIA", "TIPO_PRODUCTO", "CANTIDAD", "UNIDADES" };
	private static final String[] camposListaPrecio = new String[] {"ID_PRECIO","ID_DETALLE","FECHA_INICIO","FECHA_FIN","PRECIO" };
	private static final String[] camposDetallePrecio = new String[] {"ID_DETALLE","ID_PRODUCTO","PRECIO","FECHA_DETALLE","ACTUAL"};
	private static final String[]camposGasolinera = new String[] {"id_gasolinera","empresa"};
	private static final String[]camposSucursales = new String[] {"id_sucursal","id_gasolinera","nombre_sucursal","direccion","telefono"};
	private static final String[]camposCliente = new String [] {"id_cliente","primer_nombre","segundo_nombre","tercer_nombre","primer_apellido","segundo_apellido"};
	private static final String[]camposEmision_vales = new String[]	{"id_emision_vale","cantidad_emitida","valor","fecha_emision"};
	private static final String[]camposVales = new String[] {"id_vales","id_emision_vale","cantidad","valor","promocion","fecha_venc"};
			
	//Manejadores de la base de datos
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	//Constructor de esta clase con argumentos
	public ControlBD(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}	
	
	//Clase manejadora de la base de datos
	private static class DatabaseHelper extends SQLiteOpenHelper {
		
		//Identificadores de la base de datos
		private static final String BASE_DATOS = "ventasgas.s3db";
		private static final int VERSION = 1;
		
		//Constructor de esta clase con argumentos
		public DatabaseHelper(Context context) {
			super(context, BASE_DATOS, null, VERSION);
		}
		
		//Creando la base de datos (Creando tablas y estableciendo llaves)
				@Override
				public void onCreate(SQLiteDatabase db) {
					
					try{
						//Creacion de la tabla CLIENTE
						db.execSQL("CREATE TABLE CLIENTE("+
								   "ID_CLIENTE           VARCHAR2(7)          not null, "+
								   "PRIMER_NOMBRE        CHAR(15)             not null, "+
								   "SEGUNDO_NOMBRE       CHAR(15), "+
								   "TERCER_NOMBRE        CHAR(15), "+
								   "PRIMER_APELLIDO      CHAR(15)             not null, "+
								   "SEGUNDO_APELLIDO     CHAR(15), "+
								   "constraint PK_CLIENTE primary key (ID_CLIENTE));");  
						
						//Creacion de la tabla DETALLE_PRECIO
		   				db.execSQL("CREATE TABLE [DETALLE_PRECIO]("+
		   						   "[ID_DETALLE]           VARCHAR2(15)         not null, "+
		   						   "[ID_PRODUCTO]          VARCHAR2(6)          not null, "+
		   						   "[PRECIO]               NUMBER(8,2)          not null, "+
		   						   "[ACTUAL]               VARCHAR(2)          not null, "+
		   						   "[FECHA_DETALLE]        DATE DEFAULT CURRENT_DATE NOT NULL, "+
		   						   "constraint PK_DETALLE_PRECIO primary key (ID_DETALLE));");
		   				
		   				//Creacion de la tabla DETALLE_VENTA
						db.execSQL("CREATE TABLE [DETALLE_VENTA]("+
								   "[ID_DETALLE_VENTA]     VARCHAR2(10)         not null, "+
								   "[ID_VENTA]             VARCHAR2(12)         not null, "+
								   "[ID_PRODUCTO]          VARCHAR2(6)          not null, "+
								   "[MONTO]                NUMBER(7,2)          not null, "+
								   "[FECHA_VENTA]          DATE DEFAULT CURRENT_DATE NOT NULL, "+
								   "[HORA_VENTA]           TIME DEFAULT CURRENT_TIME NULL, "+
								   "[PRODUCTOS]            CHAR(1024)           not null, "+
								   "constraint PK_DETALLE_VENTA primary key (ID_DETALLE_VENTA));");
						
						//Creacion de la tabla EMISION_VALES
						db.execSQL("CREATE TABLE [EMISION_VALES]("+
								   "[ID_EMISION_VALE]      VARCHAR2(10)         not null, "+
								   "[CANTIDAD_EMITIDA]     INTEGER              not null, "+
								   "[VALOR]                NUMBER(2,0)          not null, "+
								   "[FECHA_EMISION]        DATE                 not null, "+
								   "constraint PK_EMISION_VALES primary key (ID_EMISION_VALE));");
						
						//Creacion de la tabla EMPLEADO
						db.execSQL("CREATE TABLE EMPLEADO("+
								   "ID_EMPLEADO          VARCHAR2(9)          not null, "+
								   "NOMBRE_EMPLEADO      VARCHAR2(50)         not null, "+
								   "DUI                  VARCHAR2(10)         not null, "+
								   "DIRECCION            VARCHAR2(100)        not null, "+
								   "TELEFONO             VARCHAR2(8)          not null, "+
								   "constraint PK_EMPLEADO primary key (ID_EMPLEADO));");
						
						//Creacion de la tabla GASOLINERA
						db.execSQL("CREATE TABLE GASOLINERA("+
								   "ID_GASOLINERA        VARCHAR2(3)          not null, "+
								   "EMPRESA              CHAR(8)              not null, "+
								   "constraint PK_GASOLINERA primary key (ID_GASOLINERA));");
						
						//Creacion de la tabla LISTA_PRECIO
						db.execSQL("CREATE TABLE LISTA_PRECIO("+
								   "ID_PRECIO            VARCHAR2(11)         not null, "+
								   "ID_DETALLE           VARCHAR2(15)         not null, "+
								   "FECHA_INICIO         DATE                 not null, "+
								   "FECHA_FIN            DATE                 not null, "+
								   "PRECIO               NUMBER(8,2)          not null, "+
								   "constraint PK_LISTA_PRECIO primary key (ID_PRECIO));");
						
						//Creacion de la tabla PRODUCTOS
						db.execSQL("CREATE TABLE PRODUCTOS("+
								   "ID_PRODUCTO          VARCHAR2(6)          not null, "+
								   "ID_TIPO              VARCHAR2(3)          not null, "+
								   "NOMBRE_PRODUCTO      CHAR(25)             not null, "+
								   "PRECIO_ACTUAL        NUMBER(8,2)          not null, "+
								   "EXISTENCIA           SMALLINT             not null, "+
								   "TIPO_PRODUCTO        CHAR(15)             not null, "+
								   "CANTIDAD             INTEGER              not null, "+
								   "UNIDADES             CHAR(15)             not null, "+
								   "constraint PK_PRODUCTOS primary key (ID_PRODUCTO));");
						
						//Creacion de la tabla SUCURSAL
						db.execSQL("CREATE TABLE SUCURSAL("+
								   "ID_SUCURSAL          VARCHAR2(6)          not null, "+
								   "ID_GASOLINERA        VARCHAR2(3)          not null, "+
								   "NOMBRE_SUCURSAL      CHAR(100)            not null, "+
								   "DIRECCION            VARCHAR2(100)        not null, "+
								   "TELEFONO             VARCHAR2(8)          not null, "+
								   "constraint PK_SUCURSAL primary key (ID_SUCURSAL));");
						
						//Creacion de la tabla TIPO_COMBUSTIBLE
						db.execSQL("CREATE TABLE TIPO_COMBUSTIBLE("+
								   "ID_TIPO              VARCHAR2(3)          not null, "+
								   "NOMBRE_GAS           CHAR(12)             not null, "+
								   "constraint PK_TIPO_COMBUSTIBLE primary key (ID_TIPO));");
						
						//Creacion de la tabla TIPO_VENTA
						db.execSQL("CREATE TABLE TIPO_VENTA("+
								   "ID_TIPO_VENTA        VARCHAR2(3)          not null, "+
								   "TIPO                 CHAR(12)             not null, "+
								   "PROMO                SMALLINT             not null, "+
								   "constraint PK_TIPO_VENTA primary key (ID_TIPO_VENTA));");
						
						//Creacion de la tabla VALES				
						db.execSQL("CREATE TABLE VALES("+
								   "ID_VALES             VARCHAR2(13)         not null, "+
								   "ID_EMISION_VALE      VARCHAR2(10)         not null, "+
								   "CANTIDAD             INTEGER              not null, "+
								   "VALOR                NUMBER(2,0)          not null, "+
								   "PROMOCION            CHAR(20)             not null, "+
								   "FECHA_VENC           DATE                 not null, "+
								   "constraint PK_VALES primary key (ID_VALES));");
						
						//Creacion de la tabla VALES APLICADOS				
						db.execSQL("CREATE TABLE [VALES_APLICADOS] ("+
								   "[ID_VALE_APLI]    VARCHAR2(16)   NOT NULL, "+
								   "[ID_VALES]        VARCHAR2(13)   NOT NULL, "+
								   "[FECHA_APLI]      DATE DEFAULT CURRENT_TIME NOT NULL, "+
								   "[CANTIDAD_APLI]   INTEGER        NOT NULL);");
						
						//Creacion de la tabla VENTAS				
						db.execSQL("CREATE TABLE [VENTAS] ("+
								   "[ID_VENTA] 		VARCHAR2(13)   NOT NULL, "+
								   "[ID_VALE_APLI]  VARCHAR2(15)   DEFAULT 'N/A' NOT NULL, "+
								   "[ID_TIPO_VENTA] VARCHAR2(3)    NOT NULL, "+
								   "[ID_SUCURSAL]   VARCHAR2(6)    NOT NULL, "+
								   "[ID_CLIENTE]    VARCHAR2(7)    DEFAULT 'N/A' NOT NULL, "+
								   "[ID_EMPLEADO]   VARCHAR2(9)    NOT NULL, "+
								   "[MONTO]         NUMBER(7,2)    NOT NULL);");
						
						
					}
					catch(SQLException e){
					e.printStackTrace();
					}
				}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
		}
	}//Fin de la claseDatabaseHelper
	
	
	//--------------------- Metodos que controlan la base de datos ---------------------------
	
	//Abrir la base de datos
	public void abrir() throws SQLException{
		db = DBHelper.getWritableDatabase();
		return;
	}
		
	//Cerrar la base de datos
	public void cerrar(){
		DBHelper.close();
	}
	
	// METODOS PARA CONTROLAR TABLA TIPO_COMBUSTIBLE--------------------
			public String insertar(ClaseTipoDeCombustible TC) {
				String regInsertados = "Registro Insertado Nº= ";
				long contador = 0;
				ContentValues contenedor = new ContentValues();
				contenedor.put("ID_TIPO", TC.getID_TIPO());
				contenedor.put("NOMBRE_GAS", TC.getNOMBRE_GAS());
				contador = db.insert("TIPO_COMBUSTIBLE", null, contenedor);
				if (contador == -1 || contador == 0) {
					regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
				} else {
					regInsertados = regInsertados + contador;
				}
				return regInsertados;
			}

			public String actualizar(ClaseTipoDeCombustible TC) {
				if (verificarIntegridad(TC, 100)) {
					String[] id = { TC.getID_TIPO() };
					ContentValues cv = new ContentValues();
					cv.put("NOMBRE_GAS", TC.getNOMBRE_GAS());
					db.update("TIPO_COMBUSTIBLE", cv, "ID_TIPO = ?", id);
					return "Registro Actualizado Correctamente";
				} else {
					return "Registro con ID " + TC.getID_TIPO() + " no existe";
				}
			}

			
			public String eliminar(ClaseTipoDeCombustible TC) {
				String regAfectados = "filas afectadas= ";
				int contador = 0;
				
				
				  if (!verificarIntegridad(TC, 500)) 
				  { 
				    
			      //contador += db.delete("PRODUCTOS", "ID_TIPO='" + TC.getID_TIPO() + "'", null); 
			      
				 
				contador += db.delete("TIPO_COMBUSTIBLE", "ID_TIPO='" + TC.getID_TIPO()
						+ "'", null);
				regAfectados += contador;
				}else {
				regAfectados="Error. El id tipo de combustible esta siendo ocupado en Productos";	
				}
				return regAfectados;
			}

			public ClaseTipoDeCombustible consultarTC(String id) {
				String[] ID = { id };
				Cursor cursor = db.query("TIPO_COMBUSTIBLE", camposTipoDeCombustible,
						"ID_TIPO = ?", ID, null, null, null);
				if (cursor.moveToFirst()) {
					ClaseTipoDeCombustible TC = new ClaseTipoDeCombustible();
					TC.setID_TIPO(cursor.getString(0));
					TC.setNOMBRE_GAS(cursor.getString(1));

					return TC;
				} else {
					return null;
				}
			}

			// ------------------------------------------------------------------
		
			// METODOS PARA CONTROLAR LA TABLA PRODUCTOS-------------------------
			public String insertar(ClaseProductos Pro) {
				String regInsertados = "Registro Insertado Nº= ";
				long contador = 0;
				ContentValues contenedor = new ContentValues();
				contenedor.put("ID_PRODUCTO", Pro.getID_PRODUCTO());
				contenedor.put("ID_TIPO", Pro.getID_TIPO());
				contenedor.put("NOMBRE_PRODUCTO", Pro.getNOMBRE_PRODUCTO());
				contenedor.put("PRECIO_ACTUAL", Pro.getPRECIO_ACTUAL());
				contenedor.put("EXISTENCIA", Pro.getEXISTENCIA());
				contenedor.put("TIPO_PRODUCTO", Pro.getTIPO_PRODUCTO());
				contenedor.put("CANTIDAD", Pro.getCANTIDAD());
				contenedor.put("UNIDADES", Pro.getUNIDADES());
				
				ClaseTipoDeCombustible TC=new ClaseTipoDeCombustible();
				TC.setID_TIPO(Pro.getID_TIPO());
				
				if((verificarIntegridad(TC, 100))){
				
				contador = db.insert("PRODUCTOS", null, contenedor);
				if (contador == -1 || contador == 0) {
					regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción"
							+ contador;
				} else {
					regInsertados = regInsertados + contador;
				}
				
				}else {
					regInsertados="Error. El ID_TIPO no esta creado. insertelo en tipo combustible";
				}
				
				
				return regInsertados;
			}

			public String actualizar(ClaseProductos Pro) {
				
				ClaseTipoDeCombustible TC=new ClaseTipoDeCombustible();
				TC.setID_TIPO(Pro.getID_TIPO());
				
				if((verificarIntegridad(TC, 100))){
				
				if (verificarIntegridad(Pro, 200)) {
					String[] id = { Pro.getID_PRODUCTO() };
					ContentValues cv = new ContentValues();

					cv.put("ID_TIPO", Pro.getID_TIPO());
					cv.put("NOMBRE_PRODUCTO", Pro.getNOMBRE_PRODUCTO());
					cv.put("PRECIO_ACTUAL", Pro.getPRECIO_ACTUAL());
					cv.put("EXISTENCIA", Pro.getEXISTENCIA());
					cv.put("TIPO_PRODUCTO", Pro.getTIPO_PRODUCTO());
					cv.put("CANTIDAD", Pro.getCANTIDAD());
					cv.put("UNIDADES", Pro.getUNIDADES());
						
					db.update("PRODUCTOS", cv, "ID_PRODUCTO = ?", id);
					return "Registro Actualizado Correctamente";
				} else {
					return "Registro con ID " + Pro.getID_PRODUCTO() + " no existe";
				}
				}else{
					return "Error. El ID_TIPO no esta creado. insertelo en tipo combustible";
				}
			}

			public String eliminar(ClaseProductos Pro) {
				String regAfectados = "filas afectadas= ";
				int contador = 0;
				ClaseDetallePrecio DP = new ClaseDetallePrecio();
				DP.setID_PRODUCTO(Pro.getID_PRODUCTO());
				
				
				if (!verificarIntegridad(DP, 600)) 
				 { 
			     //contador += db.delete("nota", "carnet='" + alumno.getCarnet() + "'", null);
				
				

				contador += db.delete("PRODUCTOS",
						"ID_PRODUCTO='" + Pro.getID_PRODUCTO() + "'", null);
				regAfectados += contador;
				
				 }else{
				 regAfectados="Error. Existen detalles precio que ocupan este producto.";	 
				 }
				return regAfectados;
			}

			public ClaseProductos consultarProd(String id) {
				String[] ID = { id };
				Cursor cursor = db.query("PRODUCTOS", camposProductos,
						"ID_PRODUCTO = ?", ID, null, null, null);
				if (cursor.moveToFirst()) {
					ClaseProductos Pro = new ClaseProductos();
					Pro.setID_PRODUCTO(cursor.getString(0));
					Pro.setID_TIPO(cursor.getString(1));
					Pro.setNOMBRE_PRODUCTO(cursor.getString(2));
					Pro.setPRECIO_ACTUAL(Double.parseDouble(cursor.getString(3)));
					Pro.setEXISTENCIA(Integer.parseInt(cursor.getString(4)));
					Pro.setTIPO_PRODUCTO(cursor.getString(5));
					Pro.setCANTIDAD(Integer.parseInt(cursor.getString(6)));
					Pro.setUNIDADES(cursor.getString(7));

					return Pro;
				} else {
					return null;
				}
			}

			// -------------------------------------------------------------------
		
			//METODOS PARA CONTROLAR LA TABLA LISTA_PRECIO------------------------

			public String insertar(ClaseListaPrecio LP) {
				String regInsertados = "Registro Insertado Nº= ";
				long contador = 0;
				
				ClaseDetallePrecio DP = new ClaseDetallePrecio();
				DP.setID_DETALLE(LP.getID_DETALLE());
				
				
				if(verificarIntegridad(DP, 400)){
				
				ContentValues contenedor = new ContentValues();
				contenedor.put("ID_PRECIO", LP.getID_PRECIO());
				contenedor.put("ID_DETALLE", LP.getID_DETALLE());
				contenedor.put("FECHA_INICIO", LP.getFECHA_INICIO());
				contenedor.put("FECHA_FIN", LP.getFECHA_FIN());
				contenedor.put("PRECIO", LP.getPRECIO());
				
				contador = db.insert("LISTA_PRECIO", null, contenedor);
				if (contador == -1 || contador == 0) {
					regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción"+contador;
				} else {
					regInsertados = regInsertados + contador;
				}
				
				}else{
					regInsertados="Error. El ID de Detalle Precio no esta creado aun.";
				}
				
				return regInsertados;
				
				
			}
			
			public String actualizar(ClaseListaPrecio LP) {
				
				ClaseDetallePrecio DP = new ClaseDetallePrecio();
				DP.setID_DETALLE(LP.getID_DETALLE());
				
				
				if(verificarIntegridad(DP, 400)){
				
				if (verificarIntegridad(LP, 300)) {
					String[] id = { LP.getID_PRECIO() };
					ContentValues cv = new ContentValues();
					
					cv.put("ID_PRECIO", LP.getID_PRECIO());
					cv.put("ID_DETALLE", LP.getID_DETALLE());
					cv.put("FECHA_INICIO", LP.getFECHA_INICIO());
					cv.put("FECHA_FIN", LP.getFECHA_FIN());
					cv.put("PRECIO", LP.getPRECIO());
					
					db.update("LISTA_PRECIO", cv, "ID_PRECIO = ?", id);
					return "Registro Actualizado Correctamente";
				} else {
					return "Registro con ID " + LP.getID_PRECIO() + " no existe";
				}
				
				}else{
					return "Error. El ID de Detalle Precio no esta creado aun.";
				}
				
			}

			public String eliminar(ClaseListaPrecio LP) {
				String regAfectados = "filas afectadas= ";
				int contador = 0;
				/*
				 * if (verificarIntegridad(alumno, 3)) { contador += db.delete("nota",
				 * "carnet='" + alumno.getCarnet() + "'", null); }
				 */
				contador += db.delete("LISTA_PRECIO", "ID_PRECIO='" + LP.getID_PRECIO()
						+ "'", null);
				regAfectados += contador;
				return regAfectados;
			}
			
			public ClaseListaPrecio consultarListaPrecio(String id) {
				String[] ID = { id };
				Cursor cursor = db.query("LISTA_PRECIO", camposListaPrecio,
						"ID_PRECIO = ?", ID, null, null, null);
				if (cursor.moveToFirst()) {
					ClaseListaPrecio LP = new ClaseListaPrecio();
					LP.setID_PRECIO(cursor.getString(0));
					LP.setID_DETALLE(cursor.getString(1));
					LP.setFECHA_INICIO(cursor.getString(2));
					LP.setFECHA_FIN(cursor.getString(3));
					LP.setPRECIO(Double.parseDouble(cursor.getString(4)));
					

					return LP;
				} else {
					return null;
				}
			}
			
			//--------------------------------------------------------------------
			
			
			
			
			
			
			//METODOS PARA CONTROLAR LA TABLA DETALLE_PRECIO----------------------
			
			
			
			public String insertar(ClaseDetallePrecio DP) {
				String regInsertados = "Registro Insertado Nº= ";
				long contador = 0;
				
				ClaseProductos Pro = new ClaseProductos();
				Pro.setID_PRODUCTO(DP.getID_PRODUCTO());
				
				if(verificarIntegridad(Pro, 200)){
					
					
				// aqui esta la modificacion del precio actual---->>>>>>>>>	
				Pro = consultarProd(DP.getID_PRODUCTO());
				Pro.setPRECIO_ACTUAL(DP.getPRECIO());
				String respuesta = actualizar(Pro);
				respuesta=respuesta+"";
				//------------------------------------------>>>>>>	
				ContentValues contenedor = new ContentValues();
				contenedor.put("ID_DETALLE", DP.getID_DETALLE());
				contenedor.put("ID_PRODUCTO", DP.getID_PRODUCTO());
				contenedor.put("PRECIO", DP.getPRECIO());
				contenedor.put("FECHA_DETALLE", DP.getFECHA_DETALLE());
				contenedor.put("ACTUAL", DP.getACTUAL());
				
				contador = db.insert("DETALLE_PRECIO", null, contenedor);
				if (contador == -1 || contador == 0) {
					regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción"+contador;
				} else {
					regInsertados = regInsertados + contador;
				}
				
				
				}else {
				regInsertados = "Error. El ID del Producto no esta creado previamente.";	
				}
				
				return regInsertados;
			}

			
			
			public String actualizar(ClaseDetallePrecio DP) {
				ClaseProductos Pro = new ClaseProductos();
				Pro.setID_PRODUCTO(DP.getID_PRODUCTO());
				
				if(verificarIntegridad(Pro, 200)){
				
				
				
				if (verificarIntegridad(DP, 400)) {
					String[] id = { DP.getID_DETALLE() };
					ContentValues cv = new ContentValues();
					
					cv.put("ID_DETALLE", DP.getID_DETALLE());
					cv.put("ID_PRODUCTO", DP.getID_PRODUCTO());
					cv.put("PRECIO", DP.getPRECIO());
					cv.put("FECHA_DETALLE", DP.getFECHA_DETALLE());
					cv.put("ACTUAL", DP.getACTUAL());
					
					db.update("DETALLE_PRECIO", cv, "ID_DETALLE = ?", id);
					return "Registro Actualizado Correctamente";
				} else {
					return "Registro con ID " + DP.getID_DETALLE() + " no existe";
				}
				
				}else{
					return "Error. El ID del Producto no esta creado previamente.";
				}
				
			}

			
			
			public String eliminar(ClaseDetallePrecio DP) {
				String regAfectados = "filas afectadas= ";
				int contador = 0;
				ClaseListaPrecio LP = new ClaseListaPrecio();
				LP.setID_DETALLE(DP.getID_DETALLE());
				
				
				if (!verificarIntegridad(LP, 700)) 
				 {
				
				
				/*if (verificarIntegridad(alumno, 3)) { contador += db.delete("nota",
				 "carnet='" + alumno.getCarnet() + "'", null); }*/
				
				contador += db.delete("DETALLE_PRECIO", "ID_DETALLE='" + DP.getID_DETALLE()
						+ "'", null);
				regAfectados += contador;
				 }else{
				 regAfectados = "Error. El detalle precio esta siendo ocupado en lista precio";	 
				 }
				
				return regAfectados;
			}
			
			

			public ClaseDetallePrecio consultarDP(String id) {
				String[] ID = { id };
				Cursor cursor = db.query("DETALLE_PRECIO", camposDetallePrecio,
						"ID_DETALLE = ?", ID, null, null, null);
				if (cursor.moveToFirst()) {
					ClaseDetallePrecio DP = new ClaseDetallePrecio();
					DP.setID_DETALLE(cursor.getString(0));
					DP.setID_PRODUCTO(cursor.getString(1));
					DP.setPRECIO(Double.parseDouble(cursor.getString(2)));
					DP.setFECHA_DETALLE(cursor.getString(3));
					DP.setACTUAL(cursor.getString(4));
					return DP;
				} else {
					return null;
				}
			}
			
			//--------------------------------------------------------------------
public String insertar(Cliente cliente){
		
		String regInsertados="Registro Insertado Nº= ";
		long contador=0;
		
		ContentValues clien = new ContentValues();
		clien.put("ID_CLIENTE", cliente.getId_cliente());
		clien.put("PRIMER_NOMBRE", cliente.getPrimer_nombre());
		clien.put("SEGUNDO_NOMBRE", cliente.getSegundo_nombre());
		clien.put("TERCER_NOMBRE", cliente.getTercer_nombre());
		clien.put("PRIMER_APELLIDO", cliente.getPrimer_apellido());
		clien.put("SEGUNDO_APELLIDO", cliente.getSegundo_apellido());
		contador=db.insert("CLIENTE", null, clien);
		
		if(contador==-1 || contador==0)
		{
			regInsertados= "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		}
		else {
			regInsertados=regInsertados+contador;
		}
		return regInsertados;
	}	
	
	public String insertar(DetallePrecio detalleprecio){
	
		String regInsertados="Registro Insertado Nº= ";
		long contador=0;
		
		ContentValues detallep = new ContentValues();
		detallep.put("ID_DETALLE", detalleprecio.getId_detalle());
		detallep.put("ID_PRODUCTO", detalleprecio.getId_producto());
		detallep.put("PRECIO", detalleprecio.getPrecio());
		detallep.put("ACTUAL", detalleprecio.getActual());
		detallep.put("FECHA_DETALLE", detalleprecio.getFecha_detalle());
		
		contador=db.insert("DETALLE_PRECIO", null, detallep);
		
		if(contador==-1 || contador==0)
		{
			regInsertados= "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		}
		else {
			regInsertados=regInsertados+contador;
		}
		return regInsertados;		
	}
	
	public String insertar(DetalleVenta detalleventa){
		
		String regInsertados="Registro Insertado Nº= ";
		long contador=0;
			
		ContentValues suc = new ContentValues();
		suc.put("ID_DETALLE_VENTA", detalleventa.getId_detalle_venta());
		suc.put("ID_VENTA", detalleventa.getId_venta());
		suc.put("ID_PRODUCTO", detalleventa.getId_producto());
		suc.put("MONTO", detalleventa.getMonto());
		suc.put("FECHA_VENTA", detalleventa.getFecha_venta());
		suc.put("HORA_VENTA", detalleventa.getHora_venta());
		suc.put("PRODUCTOS", detalleventa.getProductos());
		
		contador=db.insert("DETALLE_VENTA", null, suc);
			
		if(contador==-1 || contador==0)
		{
			regInsertados= "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		}
		else
		{
			regInsertados=regInsertados+contador;
		}
			
		return regInsertados;
	}	
	
	public String insertar(EmisionVales emisionvales){
		
		String regInsertados="Registro Insertado Nº= ";
		long contador=0;
		
		ContentValues emision = new ContentValues();
		emision.put("ID_EMISION_VALE", emisionvales.getId_emision_vales());
		emision.put("CANTIDAD_EMITIDA", emisionvales.getCantidad_emitida());
		emision.put("VALOR", emisionvales.getValor());
		emision.put("FECHA_EMISION", emisionvales.getFecha_emision());
		
		contador=db.insert("EMISION_VALES", null, emision);
		
		if(contador==-1 || contador==0)
		{
			regInsertados= "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		}
		else
		{
			regInsertados=regInsertados+contador;
		}
			
		return regInsertados;
	}
		
	public String insertar(Empleado empleado){
		
		String regInsertados="Registro Insertado Nº= ";
		long contador=0;
		
		ContentValues emple = new ContentValues();
		emple.put("ID_EMPLEADO", empleado.getId_empleado());
		emple.put("NOMBRE_EMPLEADO", empleado.getNombre_empleado());
		emple.put("DUI", empleado.getDui());
		emple.put("DIRECCION", empleado.getDireccion());
		emple.put("TELEFONO", empleado.getTelefono());
		contador=db.insert("EMPLEADO", null, emple);
		
		if(contador==-1 || contador==0)
		{
		regInsertados= "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		}
		else {
		regInsertados=regInsertados+contador;
		}
		return regInsertados;
		
	}
	
	public String insertar(Gasolinera gasolinera){
		String regInsertados="Registro Insertado Nº= ";
		long contador=0;
		
		ContentValues gas = new ContentValues();
		
		gas.put("ID_GASOLINERA", gasolinera.getId_gasolinera());
		gas.put("EMPRESA", gasolinera.getEmpresa());
		
		contador=db.insert("GASOLINERA", null, gas);
			
		if(contador==-1 || contador==0)
		{
			regInsertados= "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		}
		else
		{
			regInsertados=regInsertados+contador;
		}
		
		return regInsertados;		
	}
	
	public String insertar(ListaPrecio listaprecio){
		
		String regInsertados="Registro Insertado Nº= ";
		long contador=0;
		
		ContentValues listp = new ContentValues();
		
		listp.put("ID_PRECIO", listaprecio.getId_precio());
		listp.put("ID_DETALLE", listaprecio.getId_detalle());
		listp.put("FECHA_INICIO", listaprecio.getFecha_inicio());
		listp.put("FECHA_FIN", listaprecio.getFecha_fin());
		listp.put("PRECIO", listaprecio.getPrecio());
		
		contador=db.insert("LISTA_PRECIO", null, listp);
			
		if(contador==-1 || contador==0)
		{
			regInsertados= "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		}
		else
		{
			regInsertados=regInsertados+contador;
		}
		
		return regInsertados;
	}
	
	public String insertar(Productos productos){
		
		String regInsertados="Registro Insertado Nº= ";
		long contador=0;
		
		ContentValues prod = new ContentValues();
		
		prod.put("ID_PRODUCTO", productos.getId_producto());
		prod.put("ID_TIPO", productos.getId_tipo());
		prod.put("NOMBRE_PRODUCTO", productos.getNombre_producto());
		prod.put("PRECIO_ACTUAL", productos.getPrecio_actual());
		prod.put("EXISTENCIA", productos.getExistencia());
		prod.put("TIPO_PRODUCTO", productos.getTipo_producto());
		prod.put("CANTIDAD", productos.getCantidad());
		prod.put("UNIDADES", productos.getUnidades());
		
		contador=db.insert("PRODUCTOS", null, prod);
			
		if(contador==-1 || contador==0)
		{
			regInsertados= "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		}
		else
		{
			regInsertados=regInsertados+contador;
		}
		
		return regInsertados;
	}
	
	public String insertar(Sucursal sucursal){
	
		String regInsertados="Registro Insertado Nº= ";
		long contador=0;
			
		ContentValues suc = new ContentValues();
		suc.put("ID_SUCURSAL", sucursal.getId_sucursal());
		suc.put("ID_GASOLINERA", sucursal.getId_gasolinera());
		suc.put("NOMBRE_SUCURSAL", sucursal.getNombre_sucursal());
		suc.put("DIRECCION", sucursal.getDireccion());
		suc.put("TELEFONO", sucursal.getTelefono());
		contador=db.insert("SUCURSAL", null, suc);
			
		if(contador==-1 || contador==0)
		{
			regInsertados= "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		}
		else
		{
			regInsertados=regInsertados+contador;
		}
			
		return regInsertados;
	}	
	
	public String insertar(TipoCombustible tipocombustible){
		
		String regInsertados="Registro Insertado Nº= ";
		long contador=0;
			
		ContentValues tipoc = new ContentValues();
		tipoc.put("ID_TIPO", tipocombustible.getId_tipo());
		tipoc.put("NOMBRE_GAS",tipocombustible.getNombre_gas());		
		contador=db.insert("TIPO_COMBUSTIBLE", null, tipoc);
			
		if(contador==-1 || contador==0)
		{
			regInsertados= "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		}
		else
		{
			regInsertados=regInsertados+contador;
		}
			
		return regInsertados;
	}	
	
	public String insertar(TipoVenta tipoventa){
	
		String regInsertados="Registro Insertado Nº= ";
		long contador=0;
		
		ContentValues tipoven = new ContentValues();
		tipoven.put("ID_TIPO_VENTA", tipoventa.getId_tipo_venta());
		tipoven.put("TIPO", tipoventa.getTipo());
		tipoven.put("PROMO", tipoventa.getPromo());
		contador=db.insert("TIPO_VENTA", null, tipoven);
		
		if(contador==-1 || contador==0)
		{
		regInsertados= "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		}
		else {
		regInsertados=regInsertados+contador;
		}
		return regInsertados;
	}	
	
	public String insertar(Vales vales){
		
		String regInsertados="Registro Insertado Nº= ";
		long contador=0;
		
		ContentValues val = new ContentValues();
		val.put("ID_VALES", vales.getId_vales());
		val.put("ID_EMISION_VALE", vales.getId_emision_vale());
		val.put("CANTIDAD", vales.getCantidad());
		val.put("VALOR", vales.getValor());
		val.put("PROMOCION", vales.getPromocion());
		val.put("FECHA_VENC", vales.getFecha_venc());
		
		contador=db.insert("VALES", null, val);
		
		if(contador==-1 || contador==0)
		{
			regInsertados= "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		}
		else
		{
			regInsertados=regInsertados+contador;
		}
			
		return regInsertados;
	}	
	
	public String insertar(ValesAplicados valesaplicados){
		
		String regInsertados="Registro Insertado Nº= ";
		long contador=0;
		
		ContentValues valeA = new ContentValues();
		valeA.put("ID_VALE_APLI", valesaplicados.getId_vale_apli());
		valeA.put("ID_VALES", valesaplicados.getId_vales());
		valeA.put("FECHA_APLI", valesaplicados.getFecha_apli());
		valeA.put("CANTIDAD_APLI", valesaplicados.getCantidad_apli());
		
		
		contador=db.insert("VALES_APLICADOS", null, valeA);
		
		if(contador==-1 || contador==0)
		{
			regInsertados= "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		}
		else
		{
			regInsertados=regInsertados+contador;
		}
			
		return regInsertados;
	}
	
	public String insertar(Venta venta){
		
		String regInsertados="Registro Insertado Nº= ";
		long contador=0;
			
		ContentValues suc = new ContentValues();
		suc.put("ID_VENTA", venta.getId_venta());
		suc.put("ID_VALE_APLI", venta.getId_vale_apli());
		suc.put("ID_TIPO_VENTA", venta.getId_tipo_venta());
		suc.put("ID_SUCURSAL", venta.getId_sucursal());
		suc.put("ID_CLIENTE", venta.getId_cliente());
		suc.put("ID_EMPLEADO", venta.getId_empleado());
		suc.put("MONTO", venta.getMonto());
		
		contador=db.insert("VENTAS", null, suc);
			
		if(contador==-1 || contador==0)
		{
			regInsertados= "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		}
		else
		{
			regInsertados=regInsertados+contador;
		}
			
		return regInsertados;
	}
	
	// --------------- Metodos para eliminar ------------------
	
	public String eliminar(Cliente cliente){
		
		String regAfectados="filas afectadas= ";
		int contador=0;
		
		contador+=db.delete("CLIENTE", "ID_CLIENTE='"+cliente.getId_cliente()+"'", null);
		
		regAfectados+=contador;
		return regAfectados;
		
	}
	
	
	public String eliminar(DetalleVenta detalleventa){
		return null;
	}
	
	public String eliminar(EmisionVales emisionvales){
		String regAfectados="filas afectadas= ";
		int contador=0;
		
			contador+=db.delete("EMISION_VALES", "id_emision_vale	='"+emisionvales.getId_emision_vales()+"'", null); 	
		
		
		regAfectados+=contador;
		return regAfectados;
	}
	
	public String eliminar(Empleado empleado){
		return null;		
	}
	
	public String eliminar(Gasolinera gasolinera){
		
		String regAfectados="filas afectadas= ";
		int contador=0;
		
		contador+=db.delete("GASOLINERA", "ID_GASOLINERA='"+gasolinera.getId_gasolinera()+"'", null);
		
		regAfectados+=contador;
		return regAfectados;
	}
	
	
	
	
	
	public String eliminar(Sucursal sucursal){
		
		String regAfectados="filas afectadas= ";
		int contador=0;
		
		contador+=db.delete("SUCURSAL", "ID_SUCURSAL='"+sucursal.getId_sucursal()+"'", null);
		
		regAfectados+=contador;
		return regAfectados;
	
	}
		
	
	
	public String eliminar(TipoVenta tipoventa){
		return null;
	}
	
	public String eliminar(Vales vales){
		String regAfectados="filas afectadas= ";
		int contador=0;
		
	contador+=db.delete("vales", "id_vales='"+vales.getId_vales()+"'", null); 	
		
		
		regAfectados+=contador;
		return regAfectados;
	}
	
	public String eliminar(ValesAplicados valesaplicados){
		return null;
	}
	
	public String eliminar(Venta venta){
		return null;
	}
	
		// -------------- Metodos para actualizar --------------------
	
	public String actualizar(Cliente cliente){
		
		String[] id = {cliente.getId_cliente()};
		
		ContentValues clien = new ContentValues();
		clien.put("ID_CLIENTE", cliente.getId_cliente());
		clien.put("PRIMER_NOMBRE", cliente.getPrimer_nombre());
		clien.put("SEGUNDO_NOMBRE",cliente.getSegundo_nombre());
		clien.put("TERCER_NOMBRE", cliente.getTercer_nombre());
		clien.put("PRIMER_APELLIDO", cliente.getPrimer_apellido());
		clien.put("SEGUNDO_APELLIDO",cliente.getSegundo_apellido());
		
		db.update("CLIENTE", clien, "ID_CLIENTE = ?", id);
		return "Registro Actualizado Correctamente";
	}
		
	
		
	public String actualizar(DetalleVenta detalleventa){
		return null;
	}
		
	public String actualizar(EmisionVales emisionvales){
		String[] id = {emisionvales.getId_emision_vales()};
		ContentValues cv = new ContentValues();
		cv.put("cantidad_emitida", emisionvales.getCantidad_emitida());
		cv.put("valor", emisionvales.getValor());
		cv.put("fecha_emision", emisionvales.getFecha_emision());
	
		db.update("emision_vales", cv, "id_emision_vale = ?", id);
		return "Registro Actualizado Correctamente";
	}
		
		
		
	public String actualizar(Sucursal sucursal){

		String[] id = {sucursal.getId_sucursal()};
		
		ContentValues sucur = new ContentValues();
		sucur.put("ID_SUCURSAL", sucursal.getId_sucursal());
		sucur.put("ID_GASOLINERA", sucursal.getId_gasolinera());
		sucur.put("NOMBRE_SUCURSAL", sucursal.getNombre_sucursal());
		sucur.put("DIRECCION", sucursal.getDireccion());
		sucur.put("TELEFONO", sucursal.getTelefono());
		
		db.update("SUCURSAL", sucur, "ID_SUCURSAL = ?", id);
		
		return "Registro Actualizado Correctamente";
	}
	
	public String actualizar(TipoVenta tipoventa){
		return null;
	}
		
	public String actualizar(Vales vales){
		String[] id = {vales.getId_vales()};
		ContentValues cv = new ContentValues();
		cv.put("id_emision_vale", vales.getId_emision_vale());
		cv.put("cantidad", vales.getCantidad());
		cv.put("valor",vales.getValor());
		cv.put("promocion", vales.getPromocion());
		cv.put("fecha_venc", vales.getFecha_venc());
		db.update("vales", cv, "id_vales = ?", id);
		return "Registro Actualizado Correctamente";
	}
		
	public String actualizar(ValesAplicados valesaplicados){
		return null;
	}
		
	public String actualizar(Venta venta){
		return null;
	}
	
	public String actualizar(Gasolinera gasolinera)
	{
		
		String[] id = {gasolinera.getId_gasolinera()};
		
		ContentValues gaso = new ContentValues();
		gaso.put("ID_GASOLINERA", gasolinera.getId_gasolinera());
		gaso.put("EMPRESA", gasolinera.getEmpresa());
		
		db.update("GASOLINERA", gaso, "ID_GASOLINERA = ?", id);
		
		return "Registro Actualizado Correctamente";
	}
		
	// ----------------- Metodos para consultar ---------------------------
	
	public Cliente consultar(String idCliente){
		
		String[] id = {idCliente};
		Cursor cursor = db.query("CLIENTE", camposCliente, "ID_CLIENTE = ?", id, null, null, null);
		
		if(cursor.moveToFirst())
		{
			Cliente cliente = new Cliente();
			cliente.setId_cliente(cursor.getString(0));
			cliente.setPrimer_nombre(cursor.getString(1));
			cliente.setSegundo_nombre(cursor.getString(2));
			cliente.setTercer_nombre(cursor.getString(3));
			cliente.setPrimer_apellido(cursor.getString(4));
			cliente.setSegundo_apellido(cursor.getString(5));
			
			return cliente;
		}
		else
		{
			return null;
		}
	}
		
	
	
	
		
	public DetalleVenta consultarDetalleVenta(String idDetalleVenta){
		return null;
	}
		
	public EmisionVales consultarEmisionVales(String idEmisionVales){
		
		String[] id = {idEmisionVales};
		Cursor cursor = db.query("Emision_vales", camposEmision_vales, "id_emision_vale = ?", id, null, null, null);
		
		if(cursor.moveToFirst()){
			EmisionVales Evales = new EmisionVales();
			Evales.setId_emision_vales(cursor.getString(0));
			
			Evales.setCantidad_emitida(cursor.getInt(1));
			Evales.setValor(cursor.getDouble(2));
			
			Evales.setFecha_emision(cursor.getString(3));
			return Evales;
		}else{
			return null;
		}
		
	}
		
	public Empleado consultarEmpleado(String idEmpleado){
		return null;
	}
	
	
	
	public Sucursal consultarSucursal(String idSucursal){
		
		String[] id = {idSucursal};
		Cursor cursor = db.query("SUCURSAL", camposSucursales, "ID_SUCURSAL = ?", id, null, null, null);
		
		if(cursor.moveToFirst())
		{
			Sucursal sucursal = new Sucursal();
			sucursal.setId_sucursal(cursor.getString(0));
			sucursal.setId_gasolinera(cursor.getString(1));
			sucursal.setNombre_sucursal(cursor.getString(2));
			sucursal.setDireccion(cursor.getString(3));
			sucursal.setTelefono(cursor.getString(4));
			return sucursal;
		}
		else
		{
			return null;
		}
		
	}
		
	public Gasolinera consultarGasolinera(String idGasolinera){
		
		String[] id = {idGasolinera};
		
		Cursor cursor = db.query("GASOLINERA", camposGasolinera, "ID_GASOLINERA = ?", id, null, null, null);
		
		if(cursor.moveToFirst()){
			Gasolinera gasolinera = new Gasolinera();
			gasolinera.setId_gasolinera(cursor.getString(0));
			gasolinera.setEmpresa(cursor.getString(1));
		
			return gasolinera;
		}
		else
		{
			return null;
		}
	}
	
	
		
	public TipoVenta consultarTipoVenta(String idTipoventa){
		return null;
	}
		
	public Vales consultarVales(String idVales){
		String[] id = {idVales};
		Cursor cursor = db.query("vales", camposVales, "id_vales = ?", id, null, null, null);
		if(cursor.moveToFirst()){
			Vales vales = new Vales();
			vales.setId_vales(cursor.getString(0));
			vales.setId_emision_vale(cursor.getString(1));
			vales.setCantidad(cursor.getInt(2));
			vales.setValor(cursor.getDouble(3));
			vales.setPromocion(cursor.getString(4));
			vales.setFecha_venc(cursor.getString(5));
			return vales;
		}else{
			return null;
		}
	}
		
	public ValesAplicados consultarValesAplicados(String idValesAplicados){
		return null;
	}
		
	public Venta consultarVenta(String venta){
		return null;
	}
	
	
	
	//++++++++++++++ Fin de metodos de eliminar, actualizar, insertar, modificar +++++++++++++++++
	
	/////-------------------------------------------------------------////
	///// ACA IRIAN LOS METODOS PARA VERIFICAR LA INTEGRIDAD DE LA BD ////
	@SuppressWarnings("unused")
	private boolean verificarIntegridad(Object dato, int relacion)
			throws SQLException {
		switch (relacion) {
		
		case 100: {
			// verificar que existe tipo de combustible
			ClaseTipoDeCombustible TC2 = (ClaseTipoDeCombustible) dato;
			String[] id = { TC2.getID_TIPO() };
			abrir();
			// posible error
			Cursor c2 = db.query("TIPO_COMBUSTIBLE", null, "ID_TIPO = ?", id,
					null, null, null);
			if (c2.moveToFirst()) {
				// Se encontro Alumno
				return true;
			}
			return false;
		}
		case 200: {
			// verificar que existe producto
			ClaseProductos Producto2 = (ClaseProductos) dato;
			String[] id = { Producto2.getID_PRODUCTO() };
			abrir();
			// posible error
			Cursor c2 = db.query("PRODUCTOS", null, "ID_PRODUCTO = ?", id,
					null, null, null);
			if (c2.moveToFirst()) {
				return true;
			}
			return false;
		}
		case 300: {
			// verificar que existe lista precio
			ClaseListaPrecio LP2 = (ClaseListaPrecio) dato;
			String[] id = { LP2.getID_PRECIO() };
			abrir();
			// posible error
			Cursor c2 = db.query("LISTA_PRECIO", null, "ID_PRECIO = ?", id,
					null, null, null);
			if (c2.moveToFirst()) {
				return true;
			}
			return false;
		}
		case 400: {
			// verificar que existe detalle precio
			ClaseDetallePrecio DP2 = (ClaseDetallePrecio) dato;
			String[] id = { DP2.getID_DETALLE() };
			abrir();
			// posible error
			Cursor c2 = db.query("DETALLE_PRECIO", null, "ID_DETALLE = ?", id,
					null, null, null);
			if (c2.moveToFirst()) {
				return true;
			}
			return false;
		}
		
		//al ELIMINAR tipo combustible verificar que no este ocupado en prodcuto
		case 500:
		{
		ClaseTipoDeCombustible TC = (ClaseTipoDeCombustible) dato;		
		//tabla a verificar PRODUCTOS
		Cursor c=db.query(true, "PRODUCTOS", new String[] {
		"ID_TIPO" }, "ID_TIPO='"+TC.getID_TIPO()+"'",null,
		null, null, null, null);
		if(c.moveToFirst())
		return true;
		else
		return false;
		}
		
		case 600: {
			// verificar que existe detalle precio por su ID PRODUCTO
			ClaseDetallePrecio DP2 = (ClaseDetallePrecio) dato;
			String[] id = { DP2.getID_PRODUCTO() };
			abrir();
			// posible error
			Cursor c2 = db.query("DETALLE_PRECIO", null, "ID_PRODUCTO = ?", id,
					null, null, null);
			if (c2.moveToFirst()) {
				// Se encontro Alumno
				return true;
			}
			return false;
		}
		
		case 700: {
			// verificar que existe detalle precio por su ID PRODUCTO
			ClaseListaPrecio LP2 = (ClaseListaPrecio) dato;
			String[] id = { LP2.getID_DETALLE() };
			abrir();
			// posible error
			Cursor c2 = db.query("LISTA_PRECIO", null, "ID_DETALLE = ?", id,
					null, null, null);
			if (c2.moveToFirst()) {
				// Se encontro Alumno
				return true;
			}
			return false;
		}
		
		//---demas metodos
		
		case 1: {
			
		}	
		
		default:
			return false;
		}
	}
	/////-------------------------------------------------------------////
	
	
	///////////////////////////////////////////////////////
	////Metodo para llenar la base de datos por default////
	///////////////////////////////////////////////////////
	public String llenarVentasGas(){
		
		//Datos de la tabla CLIENTE
		final String[] VCid_cliente = {"TEX-001","ALB-001","PUM-001","ESO-001","UNO-001","UNO-002"};
		final String[] VCprimer_nombre = {"jose","nestor","jose","iris","jose","isaias"};
		final String[] VCsegundo_nombre = {"antonio","giovanny","luis","yamileth","alexis",""};
		final String[] VCtercer_nombre = {"","","","","",""};
		final String[] VCprimer_apellido = {"blanco","perez","rivera","garcia","beltran","palacios"};
		final String[] VCsegundo_apellido = {"","castaneda","hernandez","","",""};
		
		//Datos de la tabla DETALLE_PRECIO
		final String[] VDPid_detalle = {"13-01-01-GAS-01","13-01-01-GAS-02","13-01-01-GAS-03","13-01-01-LUB-01","13-01-01-LUB-02"};
		final String[] VDPid_producto = {"GAS-01","GAS-02","GAS-03","LUB-01","LUB-02"};
		final double[] VDPprecio = {4.25,4.20,4.15,3.75,3.00};
		final String[] VDPactual = {"SI","SI","SI","SI","SI"};
		final String[] VDPfecha_detalle = {"2013-01-01","2013-01-01","2013-01-01","2013-01-01","2013-01-01"};		
		
		//Datos de la tabla DETALLE_VENTA
		final String[] VDVid_detalle_venta = {"01-01-01-01"};
		final String[] VDVid_venta = {"01-01"};
		final String[] VDVid_producto = {"00-00"};
		final double[] VDVmonto = {25.00};
		final String[] VDVfecha_venta = {"2013-01-01"};
		final String[] VDVhora_venta = {"08:00:00"};
		final String[] VDVproductos = {"muchos productos"};		
		
		//Datos de la tabla EMISION_VALES
		final String[] VEVid_emision_vales = {"2013-01-01","2013-01-02","2013-01-03","2013-01-04","2013-02-01"};
		final int[] VEVcantidad_emitida = {1000,500,250,100,150};
		final double[] VEVvalor = {10,20,10,20,10};
		final String[] VEVfecha_emision = {"2013-01-01","2013-01-01","2013-01-05","2013-01-06","2013-02-08"};
		
		//Datos de  la tabla EMPLEADO
		final String[] VEid_empleado = {"TEX-01-01","TEX-02-01","ALB-01-01","UNO-01-01","ESO-01-01","PUM-01-01"};
		final String[] VEnombre_empleado = {"juan perez","maria juana cruz","amlicar aparicio quijano","antonio jose marroquin",
											"carlos alfonzo sayas","milton daniel solorzano castro"};
		final String[] VEdui = {"12345678-9","73794258-5","33456128-9","23160096-4","34425678-3","30090506-5"};
		final String[] VEdireccion = {"colonia 5 de ocubre, casa 2, mejicanos","canton el matazano, pasaje 3, bloc b, casa 14, ayutuxtepeque",
									  "edificio 399, apartamento 14, colonia zacamil, mejicanos","calle bambu, casa 53-r, mejicanos",
									  "colonia monte bello, pasaje h, casa 7-a, san salvador","colonia san luis, casa 45b san salvador"};
		final String[] VEtelefono = {"76312358","78932210","22723789","23232544","76831200","78664315"};
		
		//Datos de  la tabla GASOLINERA
		final String[] VGid_gasolinera = {"TEX","SHE","ALB","PUM","UNO","ESO"};
		final String[] VGempresa = {"texaco","shell","alba","puma","uno","esso"};

		//Datos de la tabla LISTA_PRECIO
		final String[] VLPid_precio = {"GAS-13-01-1","GAS-13-01-2","GAS-13-01-3"};
		final String[] VLPid_detalle = {"13-01-01-GAS-01","13-01-01-GAS-02","13-01-01-GAS-03"};
		final String[] VLPfecha_inicio = {"2013-01-01","2013-01-01","2013-01-01"};
		final String[] VLPfecha_fin = {"2013-01-15","2013-01-15","2013-01-15"};
		final double[] VLPprecio = {4.25,4.20,4.15};
		
		//Datos de la tabla PRODUCTOS
		final String[] VPid_producto = {"GAS-01","LUB-01","GAS-02","GAS-03","LUB-02"};
		final String[] VPid_tipo = {"SUP","LUB","REG","DIE","LUB"};
		final String[] VPnombre_producto = {"gasolina super","lubricante premiun","gasolina regular","gasolina diesel","lubricante normal"};
		final double[] VPprecio_actual = {4.25,3.75,4.20,4.15,3.00};
		final int[] VPexistencia = {1,1,1,1,1};
		final String[] VPtipo_producto = {"gasolina","lubricante","gasolina","gasolina","lubricante"};
		final int[] VPcantidad = {5000,50,4700,7000,70};
		final String[] VPunidades = {"galones","docenas","galones","galones","docenas"};
		
		//Datos de la tabla SUCURSAL
		final String[] VSid_sucursal = {"TEX-01","TEX-02","ALB-01","UNO-01","ESO-01","PUM-01"};
		final String[] VSid_gasolinera = {"TEX","TEX","ALB","UNO","ESO","PUM"};
		final String[] VSnombre_sucursal = {"Los proceres","Escalon","San Antonio Abad","Troncal Del Norte","Boulevard de los heroes","Satelite"};
		final String[] VSdireccion = {"Prolongación calle los proceres, san salvador","avenida principal juan pablo II ",
									  "75 avenida norte, san antonio abad","calle principal, km 23","boulevard de los heroes, san salvador",
									  "Principal calle berna, 22 avenida norte, san salvador"};
		final String[] VStelefono = {"23455432","22334455","22446688","21436587","25130000","22734444"};
		
		//Datos de la tabla TIPO_COMBUSTIBLE
		final String[] VTCid_tipo = {"SUP","REG","DIE"};
		final String[] VTCnombre_gas = {"super","regular","diesel"};
		
		//Datos de la tabla TIPO_VENTA
		final String[] VTVid_tipo_venta = {"EFE","TAR","VAL","MIX"};
		final String[] VTVtipo = {"efectivo","credito","vales","mixto"};
		final int[] VTVpromo = {0,0,0,0};	
		
		//Datos de la tabla VALES
		final String[] VVid_vales = {"2013-01-01-10","2013-01-02-20","2013-01-03-10","2013-01-04-20","2013-02-01-10"};
		final String[] VVid_emision_vales = {"2013-01-01","2013-01-02","2013-01-03","2013-01-04","2013-02-01"};
		final int[] VVcantidad = {1000,500,250,100,150};
		final double[] VVvalor = {10,20,20,20,10};
		final String[] VVpromocion = {"inicio año","inicio año","inicio año","inicio año","14 febrero"};
		final String[] VVfecha_venc = {"2013-01-31","2013-01-31","2013-01-31","2013-01-31","2013-02-28"};
		
		//Datos de la tabla VALES_APLICADOS
		final String[] VVAid_vale_apli = {"2013-01-01-10-01","2013-01-01-20-01"};
		final String[] VVAid_vales = {"2013-01-01-10","2013-01-01-20"};
		final String[] VVAfecha_apli = {"2013-01-05","2013-01-06"};
		final int[] VVAcantidad_apli = {1,2};
		
		/*
		//Datos de la tabla VENTAS
		final String[] VVid_venta = {};
		final String[] VVid_vale_apli = {};
		final String[] VVid_tipo_venta = {};
		final String[] VVid_sucursal = {};
		final String[] VVid_cliente = {};
		final String[] VVid_empleado = {};
		final double[] VVmonto = {};
		
		*/
		
		abrir();
			
		//Eliminando valores de alguna corrida pasada
		db.execSQL("DELETE FROM cliente");
		db.execSQL("DELETE FROM detalle_precio");
		db.execSQL("DELETE FROM detalle_venta");
		db.execSQL("DELETE FROM emision_vales");
		db.execSQL("DELETE FROM empleado");
		db.execSQL("DELETE FROM gasolinera");
		db.execSQL("DELETE FROM lista_precio");
		db.execSQL("DELETE FROM productos");
		db.execSQL("DELETE FROM sucursal");
		db.execSQL("DELETE FROM tipo_combustible");
		db.execSQL("DELETE FROM tipo_venta");
		db.execSQL("DELETE FROM vales");
		db.execSQL("DELETE FROM vales_aplicados");
		db.execSQL("DELETE FROM ventas");
		
		//Llenando la tabla CLIENTE
		Cliente cliente = new Cliente();
			for(int i=0;i<6;i++){				
				cliente.setId_cliente(VCid_cliente[i]);
				cliente.setPrimer_nombre(VCprimer_nombre[i]);
				cliente.setSegundo_nombre(VCsegundo_nombre[i]);
				cliente.setTercer_nombre(VCtercer_nombre[i]);
				cliente.setPrimer_apellido(VCprimer_apellido[i]);
				cliente.setSegundo_apellido(VCsegundo_apellido[i]);
				insertar(cliente);
		    }
			
		
		//Llenado de la tabla DETALLE_PRECIO 	
		DetallePrecio detalleprecio = new DetallePrecio();
			for(int i=0;i<5;i++){				
				detalleprecio.setId_detalle(VDPid_detalle[i]);
				detalleprecio.setId_producto(VDPid_producto[i]);
				detalleprecio.setPrecio(VDPprecio[i]);
				detalleprecio.setActual(VDPactual[i]);
				detalleprecio.setFecha_detalle(VDPfecha_detalle[i]);
				insertar(detalleprecio);
			}
			
		
		
		//Llenado de la tabla DETALLE_VENTA
		DetalleVenta detalleventa = new DetalleVenta();
			for(int i=0;i<1;i++){
				detalleventa.setId_detalle_venta(VDVid_detalle_venta[i]);
				detalleventa.setId_venta(VDVid_venta[i]);
				detalleventa.setId_producto(VDVid_producto[i]);
				detalleventa.setFecha_venta(VDVfecha_venta[i]);
				detalleventa.setHora_venta(VDVhora_venta[i]);
				detalleventa.setMonto(VDVmonto[i]);
				detalleventa.setProductos(VDVproductos[i]);
				insertar(detalleventa);
			}
		
			
		//Llenado de la tabla EMISION_VALES
		EmisionVales emisionvales = new EmisionVales();
			for(int i=0;i<5;i++){				
				emisionvales.setId_emision_vales(VEVid_emision_vales[i]);
				emisionvales.setCantidad_emitida(VEVcantidad_emitida[i]);
				emisionvales.setValor(VEVvalor[i]);
				emisionvales.setFecha_emision(VEVfecha_emision[i]);
				insertar(emisionvales);				
			}
			
		//Llenado de la tabla EMPLEADO
		Empleado empleado = new Empleado();
			for(int i=0;i<6;i++){				
				empleado.setId_empleado(VEid_empleado[i]);
				empleado.setNombre_empleado(VEnombre_empleado[i]);
				empleado.setDui(VEdui[i]);
				empleado.setDireccion(VEdireccion[i]);
				empleado.setTelefono(VEtelefono[i]);				
				insertar(empleado);				
			}
			
		//Llenado de la tabla GASOLINERA
		Gasolinera gasolinera = new Gasolinera();
			for(int i=0;i<6;i++){				
				gasolinera.setId_gasolinera(VGid_gasolinera[i]);
				gasolinera.setEmpresa(VGempresa[i]);
				insertar(gasolinera);
			}
			
		
			
		//Llenado de la tabla LISTA_PRECIO
		ListaPrecio listaprecio = new ListaPrecio();
			for(int i=0;i<3;i++){				
				listaprecio.setId_precio(VLPid_precio[i]);
				listaprecio.setId_detalle(VLPid_detalle[i]);
				listaprecio.setFecha_inicio(VLPfecha_inicio[i]);
				listaprecio.setFecha_fin(VLPfecha_fin[i]);
				listaprecio.setPrecio(VLPprecio[i]);
				insertar(listaprecio);				
			}
			
		//Llenado de la tabla PRODUCTOS
		Productos productos = new Productos();
			for(int i=0;i<5;i++){
				productos.setId_producto(VPid_producto[i]);
				productos.setId_tipo(VPid_tipo[i]);
				productos.setNombre_producto(VPnombre_producto[i]);
				productos.setPrecio_actual(VPprecio_actual[i]);
				productos.setExistencia(VPexistencia[i]);
				productos.setTipo_producto(VPtipo_producto[i]);
				productos.setCantidad(VPcantidad[i]);
				productos.setUnidades(VPunidades[i]);
				insertar(productos);
			}
			
	
		
		//Llenado de la tabla SUCURSAL
		Sucursal sucursal = new Sucursal();
			for(int i=0;i<6;i++){
				sucursal.setId_sucursal(VSid_sucursal[i]);
				sucursal.setId_gasolinera(VSid_gasolinera[i]);
				sucursal.setNombre_sucursal(VSnombre_sucursal[i]);
				sucursal.setDireccion(VSdireccion[i]);
				sucursal.setTelefono(VStelefono[i]);
				insertar(sucursal);				
			}
			
		
			
		//Llenado de la tabla TIPO_COMBUSTIBLE
		TipoCombustible tipocombustible = new TipoCombustible();
			for(int i=0;i<3;i++){
				tipocombustible.setId_tipo(VTCid_tipo[i]);
				tipocombustible.setNombre_gas(VTCnombre_gas[i]);
				insertar(tipocombustible);				
			}
			
		
		
		//Llenado de la tabla TIPO_VENTA
		TipoVenta tipoventa = new TipoVenta();
			for(int i=0;i<4;i++){
				tipoventa.setId_tipo_venta(VTVid_tipo_venta[i]);
				tipoventa.setTipo(VTVtipo[i]);
				tipoventa.setPromo(VTVpromo[i]);
				insertar(tipoventa);
			}
			
		
		
		//Llenado de la tabla VALES
		Vales vales = new Vales();
			for(int i=0;i<5;i++){
				vales.setId_vales(VVid_vales[i]);
				vales.setId_emision_vale(VVid_emision_vales[i]);
				vales.setCantidad(VVcantidad[i]);
				vales.setValor(VVvalor[i]);
				vales.setPromocion(VVpromocion[i]);
				vales.setFecha_venc(VVfecha_venc[i]);				
				insertar(vales);				
			}
		
		
			
		//Llenado de la tabla VALES_APLICADOS
		ValesAplicados valesaplicados = new ValesAplicados();
			for(int i=0;i<2;i++){
				valesaplicados.setId_vale_apli(VVAid_vale_apli[i]);
				valesaplicados.setId_vales(VVAid_vales[i]);
				valesaplicados.setFecha_apli(VVAfecha_apli[i]);
				valesaplicados.setCantidad_apli(VVAcantidad_apli[i]);
				insertar(valesaplicados);				
			}
			
		/*
			
		//Llenado de la tabla VENTA
		Venta venta = new Venta();
			for(int i=0;i<1;i++){
				venta.setId_venta(VVid_venta[i]);
				venta.setId_vale_apli(VVid_vale_apli[i]);
				venta.setId_tipo_venta(VVid_tipo_venta[i]);
				venta.setId_sucursal(VVid_sucursal[i]);
				venta.setId_cliente(VVid_cliente[i]);
				venta.setId_empleado(VVid_empleado[i]);
				venta.setMonto(VVmonto[i]);
				insertar(venta);
			}
			
		*/
		
		cerrar();
		return "Guardo Correctamente";	
	}

	//Metodo para llenar Spinners
	public Cursor llenarSpinner(String cadena){
		
		Cursor c = db.rawQuery(cadena, null);
				
		return c;
	}
	
	public double precio(String idProducto){
		
		String[] arg = {idProducto};
		
		double pre = -1;
			
		Cursor cursor = db.rawQuery( "SELECT precio FROM detalle_precio WHERE id_producto = ? AND actual = 'SI' ",arg);
		
		if(cursor.moveToFirst())
		{
			pre = Double.parseDouble(cursor.getString(0));
					
			return pre;
		}
		else
		{
			return pre;
		}
		
	}

}
