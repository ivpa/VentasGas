package sv.ues.fia.ventasgas;

public class Venta {
	
	String id_venta;
	String id_vale_apli;
	String id_tipo_venta;
	String id_sucursal;
	String id_cliente;
	String id_empleado;
	double monto;
	
	public Venta(){
		
	}

	public Venta(String id_venta, String id_vale_apli, String id_tipo_venta,
			String id_sucursal, String id_cliente, String id_empleado,
			double monto) {
		super();
		this.id_venta = id_venta;
		this.id_vale_apli = id_vale_apli;
		this.id_tipo_venta = id_tipo_venta;
		this.id_sucursal = id_sucursal;
		this.id_cliente = id_cliente;
		this.id_empleado = id_empleado;
		this.monto = monto;
	}

	public String getId_venta() {
		return id_venta;
	}

	public void setId_venta(String id_venta) {
		this.id_venta = id_venta;
	}

	public String getId_vale_apli() {
		return id_vale_apli;
	}

	public void setId_vale_apli(String id_vale_apli) {
		this.id_vale_apli = id_vale_apli;
	}

	public String getId_tipo_venta() {
		return id_tipo_venta;
	}

	public void setId_tipo_venta(String id_tipo_venta) {
		this.id_tipo_venta = id_tipo_venta;
	}

	public String getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(String id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(String id_empleado) {
		this.id_empleado = id_empleado;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}
}
