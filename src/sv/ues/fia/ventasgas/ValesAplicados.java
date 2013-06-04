package sv.ues.fia.ventasgas;

public class ValesAplicados {
	
	String id_vale_apli;
	String id_vales;
	String fecha_apli;
	int cantidad_apli;
	
	public ValesAplicados(){
		
	}

	public ValesAplicados(String id_vale_apli, String id_vales,
			String fecha_apli, int cantidad_apli) {
		super();
		this.id_vale_apli = id_vale_apli;
		this.id_vales = id_vales;
		this.fecha_apli = fecha_apli;
		this.cantidad_apli = cantidad_apli;
	}

	public String getId_vale_apli() {
		return id_vale_apli;
	}

	public void setId_vale_apli(String id_vale_apli) {
		this.id_vale_apli = id_vale_apli;
	}

	public String getId_vales() {
		return id_vales;
	}

	public void setId_vales(String id_vales) {
		this.id_vales = id_vales;
	}

	public String getFecha_apli() {
		return fecha_apli;
	}

	public void setFecha_apli(String fecha_apli) {
		this.fecha_apli = fecha_apli;
	}

	public int getCantidad_apli() {
		return cantidad_apli;
	}

	public void setCantidad_apli(int cantidad_apli) {
		this.cantidad_apli = cantidad_apli;
	}
}
