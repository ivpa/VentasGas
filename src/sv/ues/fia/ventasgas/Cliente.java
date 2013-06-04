package sv.ues.fia.ventasgas;

public class Cliente {
	
	String id_cliente;
	String primer_nombre;
	String segundo_nombre;
	String tercer_nombre;
	String primer_apellido;
	String segundo_apellido;
	
	public Cliente(){
		
	}

	public Cliente(String id_cliente, String primer_nombre,
			String segundo_nombre, String tercer_nombre,
			String primer_apellido, String segundo_apellido) {
		super();
		this.id_cliente = id_cliente;
		this.primer_nombre = primer_nombre;
		this.segundo_nombre = segundo_nombre;
		this.tercer_nombre = tercer_nombre;
		this.primer_apellido = primer_apellido;
		this.segundo_apellido = segundo_apellido;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getPrimer_nombre() {
		return primer_nombre;
	}

	public void setPrimer_nombre(String primer_nombre) {
		this.primer_nombre = primer_nombre;
	}

	public String getSegundo_nombre() {
		return segundo_nombre;
	}

	public void setSegundo_nombre(String segundo_nombre) {
		this.segundo_nombre = segundo_nombre;
	}

	public String getTercer_nombre() {
		return tercer_nombre;
	}

	public void setTercer_nombre(String tercer_nombre) {
		this.tercer_nombre = tercer_nombre;
	}

	public String getPrimer_apellido() {
		return primer_apellido;
	}

	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}

	public String getSegundo_apellido() {
		return segundo_apellido;
	}

	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}
}
