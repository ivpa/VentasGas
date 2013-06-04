package sv.ues.fia.ventasgas;

public class ClaseListaPrecio {

	private String ID_PRECIO="";
	private String ID_DETALLE="";
	private String FECHA_INICIO="";
	private String FECHA_FIN="";
	private Double PRECIO=0.0;
	public String getID_PRECIO() {
		return ID_PRECIO;
	}
	public void setID_PRECIO(String iD_PRECIO) {
		ID_PRECIO = iD_PRECIO;
	}
	public String getID_DETALLE() {
		return ID_DETALLE;
	}
	public void setID_DETALLE(String iD_DETALLE) {
		ID_DETALLE = iD_DETALLE;
	}
	public String getFECHA_INICIO() {
		return FECHA_INICIO;
	}
	public void setFECHA_INICIO(String fECHA_INICIO) {
		FECHA_INICIO = fECHA_INICIO;
	}
	public String getFECHA_FIN() {
		return FECHA_FIN;
	}
	public void setFECHA_FIN(String fECHA_FIN) {
		FECHA_FIN = fECHA_FIN;
	}
	public Double getPRECIO() {
		return PRECIO;
	}
	public void setPRECIO(Double pRECIO) {
		PRECIO = pRECIO;
	}
	
	
	
}
