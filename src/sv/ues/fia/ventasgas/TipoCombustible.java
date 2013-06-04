package sv.ues.fia.ventasgas;

public class TipoCombustible {
	
	String id_tipo;
	String nombre_gas;
	
	public TipoCombustible() {
		
	}

	public TipoCombustible(String id_tipo, String nombre_gas) {
		super();
		this.id_tipo = id_tipo;
		this.nombre_gas = nombre_gas;
	}

	public String getId_tipo() {
		return id_tipo;
	}

	public void setId_tipo(String id_tipo) {
		this.id_tipo = id_tipo;
	}

	public String getNombre_gas() {
		return nombre_gas;
	}

	public void setNombre_gas(String nombre_gas) {
		this.nombre_gas = nombre_gas;
	}
	
}
