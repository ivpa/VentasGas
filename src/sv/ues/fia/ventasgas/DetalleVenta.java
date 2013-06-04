package sv.ues.fia.ventasgas;

public class DetalleVenta {
	
	String id_detalle_venta;
	String id_venta;
	String id_producto;
	double monto;
	String fecha_venta;
	String hora_venta;
	String productos;
	
	public DetalleVenta(){
		
	}

	public DetalleVenta(String id_detalle_venta, String id_venta,
			String id_producto, double monto, String fecha_venta,
			String hora_venta, String productos) {
		super();
		this.id_detalle_venta = id_detalle_venta;
		this.id_venta = id_venta;
		this.id_producto = id_producto;
		this.monto = monto;
		this.fecha_venta = fecha_venta;
		this.hora_venta = hora_venta;
		this.productos = productos;
	}

	public String getId_detalle_venta() {
		return id_detalle_venta;
	}

	public void setId_detalle_venta(String id_detalle_venta) {
		this.id_detalle_venta = id_detalle_venta;
	}

	public String getId_venta() {
		return id_venta;
	}

	public void setId_venta(String id_venta) {
		this.id_venta = id_venta;
	}

	public String getId_producto() {
		return id_producto;
	}

	public void setId_producto(String id_producto) {
		this.id_producto = id_producto;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getFecha_venta() {
		return fecha_venta;
	}

	public void setFecha_venta(String fecha_venta) {
		this.fecha_venta = fecha_venta;
	}

	public String getHora_venta() {
		return hora_venta;
	}

	public void setHora_venta(String hora_venta) {
		this.hora_venta = hora_venta;
	}

	public String getProductos() {
		return productos;
	}

	public void setProductos(String productos) {
		this.productos = productos;
	}
	
}
