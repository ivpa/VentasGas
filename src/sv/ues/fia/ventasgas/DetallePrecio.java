package sv.ues.fia.ventasgas;

public class DetallePrecio {
	
	String id_detalle;
	String id_producto;
	double precio;
	String actual;
	String fecha_detalle;
	
	public DetallePrecio() {
		
	}

	public DetallePrecio(String id_detalle, String id_producto, double precio,
			String fecha_detalle, String actual) {
		super();
		this.id_detalle = id_detalle;
		this.id_producto = id_producto;
		this.precio = precio;
		this.fecha_detalle = fecha_detalle;
		this.actual = actual;
	}

	public String getId_detalle() {
		return id_detalle;
	}

	public void setId_detalle(String id_detalle) {
		this.id_detalle = id_detalle;
	}

	public String getId_producto() {
		return id_producto;
	}

	public void setId_producto(String id_producto) {
		this.id_producto = id_producto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getFecha_detalle() {
		return fecha_detalle;
	}

	public void setFecha_detalle(String fecha_detalle) {
		this.fecha_detalle = fecha_detalle;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}
	
	
}
