package uniandes.dpoo.taller2.modificacion;

import uniandes.dpoo.taller2.modelo.Interface;

public class Bebidas implements Interface {

	private String nombre;

	private double precio;
	
	private double calorias;
	
	public Bebidas(String elNombre, double elPrecio)
	{
		this.nombre = elNombre;
		this.precio = elPrecio;
		this.calorias = 0;
	}
	
	@Override
	public double darPrecio() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String darNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPrecio() {
		// TODO Auto-generated method stub
		
	}


	public double darCalorias() {return calorias;}
	
	public void setCalorias(double calorias)
	{
		this.calorias = calorias;
	}
}
