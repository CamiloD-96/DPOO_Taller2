package uniandes.dpoo.taller2.modelo;

import java.util.ArrayList;
import java.util.List;

public class Combo implements Interface {

	private String nombre;
	private double descuento;
	private ProductoMenu producto1Combo;
	private ProductoMenu producto2Combo;
	private ProductoMenu producto3Combo;
	private double calorias;
	
	public Combo(String elNombre, double elDescuento, ProductoMenu producto1, ProductoMenu producto2,
			ProductoMenu producto3) {
		this.nombre = elNombre;
		this.descuento = elDescuento;
		this.producto1Combo = producto1;
		this.producto2Combo = producto2;
		this.producto3Combo = producto3;
		this.calorias = 0;
	}
	
	public String darNombre()
	{
		return nombre;
	}

	public double darDescuento()
	{
		return descuento;
	}
	@Override
	public double darPrecio() {
		// TODO Auto-generated method stub
		double precio = 0;
		double descuento = darDescuento();
		precio = precio + producto1Combo.darPrecio() + producto2Combo.darPrecio() + producto3Combo.darPrecio();
		double precioFinal = precio*(1-descuento/100);
		return precioFinal;
	}

	@Override
	public String generarTextoFactura() {
		String texto;
		texto = "\n Productos: " + darNombre() + ": " + darPrecio();
		return texto;
	}

	public ProductoMenu darProducto1Combo(){return producto1Combo;}
	public ProductoMenu darProducto2Combo(){return producto2Combo;}
	public ProductoMenu darProducto3Combo(){return producto3Combo;}

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
