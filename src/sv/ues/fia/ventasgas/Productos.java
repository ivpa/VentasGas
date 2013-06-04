package sv.ues.fia.ventasgas;

public class Productos {
	
	String id_producto;
	String id_tipo;
	String nombre_producto;
	double precio_actual;
	int existencia;
	String tipo_producto;
	int cantidad;
	String unidades;
	
	public Productos(){
		
	}

	public Productos(String id_producto, String id_tipo,
			String nombre_producto, double precio_actual, int existencia,
			String tipo_producto, int cantidad, String unidades) {
		super();
		this.id_producto = id_producto;
		this.id_tipo = id_tipo;
		this.nombre_producto = nombre_producto;
		this.precio_actual = precio_actual;
		this.existencia = existencia;
		this.tipo_producto = tipo_producto;
		this.cantidad = cantidad;
		this.unidades = unidades;
	}

	public String getId_producto() {
		return id_producto;
	}

	public void setId_producto(String id_producto) {
		this.id_producto = id_producto;
	}

	public String getId_tipo() {
		return id_tipo;
	}

	public void setId_tipo(String id_tipo) {
		this.id_tipo = id_tipo;
	}

	public String getNombre_producto() {
		return nombre_producto;
	}

	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}

	public double getPrecio_actual() {
		return precio_actual;
	}

	public void setPrecio_actual(double precio_actual) {
		this.precio_actual = precio_actual;
	}

	public int getExistencia() {
		return existencia;
	}

	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	public String getTipo_producto() {
		return tipo_producto;
	}

	public void setTipo_producto(String tipo_producto) {
		this.tipo_producto = tipo_producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidades() {
		return unidades;
	}

	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}
}
