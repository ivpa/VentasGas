package sv.ues.fia.ventasgas;

public class TipoVenta {
	
	String id_tipo_venta;
	String tipo;
	int promo;
	
	public TipoVenta(){
		
	}

	public TipoVenta(String id_tipo_venta, String tipo, int promo) {
		super();
		this.id_tipo_venta = id_tipo_venta;
		this.tipo = tipo;
		this.promo = promo;
	}

	public String getId_tipo_venta() {
		return id_tipo_venta;
	}

	public void setId_tipo_venta(String id_tipo_venta) {
		this.id_tipo_venta = id_tipo_venta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getPromo() {
		return promo;
	}

	public void setPromo(int promo) {
		this.promo = promo;
	}
}
