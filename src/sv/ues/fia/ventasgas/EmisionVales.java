package sv.ues.fia.ventasgas;

public class EmisionVales {
	
	String id_emision_vales;
	int cantidad_emitida;
	double valor;
	String fecha_emision;
	
	public EmisionVales(){
		
	}

	public EmisionVales(String id_emision_vales, int cantidad_emitida,
			double valor, String fecha_emision) {
		super();
		this.id_emision_vales = id_emision_vales;
		this.cantidad_emitida = cantidad_emitida;
		this.valor = valor;
		this.fecha_emision = fecha_emision;
	}

	public String getId_emision_vales() {
		return id_emision_vales;
	}

	public void setId_emision_vales(String id_emision_vales) {
		this.id_emision_vales = id_emision_vales;
	}

	public int getCantidad_emitida() {
		return cantidad_emitida;
	}

	public void setCantidad_emitida(int cantidad_emitida) {
		this.cantidad_emitida = cantidad_emitida;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getFecha_emision() {
		return fecha_emision;
	}

	public void setFecha_emision(String fecha_emision) {
		this.fecha_emision = fecha_emision;
	}

	
}
