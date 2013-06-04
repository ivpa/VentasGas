package sv.ues.fia.ventasgas;

public class Vales {
	
	String id_vales;
	String id_emision_vale;
	int cantidad;
	double valor;
	String promocion;
	String fecha_venc;
	
	public Vales(){
		
	}

	public Vales(String id_vales, String id_emision_vale, int cantidad,
			double valor, String promocion, String fecha_venc) {
		super();
		this.id_vales = id_vales;
		this.id_emision_vale = id_emision_vale;
		this.cantidad = cantidad;
		this.valor = valor;
		this.promocion = promocion;
		this.fecha_venc = fecha_venc;
	}

	public String getId_vales() {
		return id_vales;
	}

	public void setId_vales(String id_vales) {
		this.id_vales = id_vales;
	}

	public String getId_emision_vale() {
		return id_emision_vale;
	}

	public void setId_emision_vale(String id_emision_vale) {
		this.id_emision_vale = id_emision_vale;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getPromocion() {
		return promocion;
	}

	public void setPromocion(String promocion) {
		this.promocion = promocion;
	}

	public String getFecha_venc() {
		return fecha_venc;
	}

	public void setFecha_venc(String fecha_venc) {
		this.fecha_venc = fecha_venc;
	}
}
