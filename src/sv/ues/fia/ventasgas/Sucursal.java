package sv.ues.fia.ventasgas;

public class Sucursal {
	
	String id_sucursal;
	String id_gasolinera;
	String nombre_sucursal;
	String direccion;
	String telefono;
	
	public Sucursal(){
		
	}

	public Sucursal(String id_sucursal, String id_gasolinera,
			String nombre_sucursal, String direccion, String telefono) {
		super();
		this.id_sucursal = id_sucursal;
		this.id_gasolinera = id_gasolinera;
		this.nombre_sucursal = nombre_sucursal;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public String getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(String id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	public String getId_gasolinera() {
		return id_gasolinera;
	}

	public void setId_gasolinera(String id_gasolinera) {
		this.id_gasolinera = id_gasolinera;
	}

	public String getNombre_sucursal() {
		return nombre_sucursal;
	}

	public void setNombre_sucursal(String nombre_sucursal) {
		this.nombre_sucursal = nombre_sucursal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}
