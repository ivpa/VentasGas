package sv.ues.fia.ventasgas;

public class ListaPrecio {
	
	String id_precio;
	String id_detalle;
	String fecha_inicio;
	String fecha_fin;
	double precio;
	
	public ListaPrecio(){
		
	}

	public ListaPrecio(String id_precio, String id_detalle,
			String fecha_inicio, String fecha_fin, double precio) {
		super();
		this.id_precio = id_precio;
		this.id_detalle = id_detalle;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.precio = precio;
	}

	public String getId_precio() {
		return id_precio;
	}

	public void setId_precio(String id_precio) {
		this.id_precio = id_precio;
	}

	public String getId_detalle() {
		return id_detalle;
	}

	public void setId_detalle(String id_detalle) {
		this.id_detalle = id_detalle;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
