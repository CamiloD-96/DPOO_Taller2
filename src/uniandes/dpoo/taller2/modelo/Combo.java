package uniandes.dpoo.taller2.modelo;

public class Combo {

	private String nombre;
	private double descuento;
	private ProductoMenu producto1Combo;
	private ProductoMenu producto2Combo;
	private ProductoMenu producto3Combo;
	
	public Combo(String elNombre, double elDescuento, ProductoMenu producto1, ProductoMenu producto2,
			ProductoMenu producto3) {
		this.nombre = elNombre;
		this.descuento = elDescuento;
		this.producto1Combo = producto1;
		this.producto2Combo = producto2;
		this.producto3Combo = producto3;
	}
	
	public String darNombre()
	{
		return nombre;
	}

}
