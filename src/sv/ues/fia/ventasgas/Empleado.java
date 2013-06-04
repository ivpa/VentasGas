package sv.ues.fia.ventasgas;

public class Empleado {
	
	String id_empleado;
	String nombre_empleado;
	String dui;
	String direccion;
	String telefono;
	
	public Empleado(){
		
	}

	public Empleado(String id_empleado, String nombre_empleado, String dui,
			String direccion, String telefono) {
		super();
		this.id_empleado = id_empleado;
		this.nombre_empleado = nombre_empleado;
		this.dui = dui;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public String getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(String id_empleado) {
		this.id_empleado = id_empleado;
	}

	public String getNombre_empleado() {
		return nombre_empleado;
	}

	public void setNombre_empleado(String nombre_empleado) {
		this.nombre_empleado = nombre_empleado;
	}

	public String getDui() {
		return dui;
	}

	public void setDui(String dui) {
		this.dui = dui;
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
