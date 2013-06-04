package sv.ues.fia.ventasgas;

public class ClaseDetallePrecio {

	private String ID_DETALLE="";
	private String ID_PRODUCTO="";
	private Double PRECIO=0.0;
	private String FECHA_DETALLE="";
	private String ACTUAL="";
	
	
	public String getACTUAL() {
		return ACTUAL;
	}
	public void setACTUAL(String aCTUAL) {
		ACTUAL = aCTUAL;
	}
	public String getID_DETALLE() {
		return ID_DETALLE;
	}
	public void setID_DETALLE(String iD_DETALLE) {
		ID_DETALLE = iD_DETALLE;
	}
	public String getID_PRODUCTO() {
		return ID_PRODUCTO;
	}
	public void setID_PRODUCTO(String iD_PRODUCTO) {
		ID_PRODUCTO = iD_PRODUCTO;
	}
	public Double getPRECIO() {
		return PRECIO;
	}
	public void setPRECIO(Double pRECIO) {
		PRECIO = pRECIO;
	}
	public String getFECHA_DETALLE() {
		return FECHA_DETALLE;
	}
	public void setFECHA_DETALLE(String fECHA_DETALLE) {
		FECHA_DETALLE = fECHA_DETALLE;
	}
	
	
	
}
