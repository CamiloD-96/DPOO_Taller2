package uniandes.dpoo.taller2.modelo;

public class Ingrediente {
	
	private String nombre;
	
	private double precio;
	
	private double calorias;

	public Ingrediente(String elNombre, double elPrecio) {
		this.nombre = elNombre;
		this.precio = elPrecio;
		this.calorias = 0;
	}
	
	public String darNombre()
	{
		return nombre;
	}
	
	public double darCostoAdicional() 
	{
		return precio;
	}

	public double darCalorias() {return calorias;}
	
	public void setCalorias(double calorias)
	{
		this.calorias = calorias;
	}
}
