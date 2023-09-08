package uniandes.dpoo.taller2.modelo;

public class Ingrediente {
	
	private String nombre;
	
	private double precio;

	public Ingrediente(String elNombre, double elPrecio) {
		this.nombre = elNombre;
		this.precio = elPrecio;
	}
	
	public String darNombre()
	{
		return nombre;
	}
	
	public double darCostoAdicional() 
	{
		return precio;
	}

}
