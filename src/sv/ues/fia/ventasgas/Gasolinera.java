package sv.ues.fia.ventasgas;

public class Gasolinera {
	
	String id_gasolinera;
	String empresa;
	
	public Gasolinera(){
		
	}

	public Gasolinera(String id_gasolinera, String empresa) {
		super();
		this.id_gasolinera = id_gasolinera;
		this.empresa = empresa;
	}

	public String getId_gasolinera() {
		return id_gasolinera;
	}

	public void setId_gasolinera(String id_gasolinera) {
		this.id_gasolinera = id_gasolinera;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
}
