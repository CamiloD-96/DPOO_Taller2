package uniandes.dpoo.taller2.modelo;


public class ProductoMenu implements Interface {

private String nombre;

private double precio;

private double calorias;


public ProductoMenu(String elNombre, double elPrecio)
	{
		this.nombre = elNombre;
		this.precio = elPrecio;
		this.calorias = 0;
	}

public String darNombre()
	{
		return nombre;
	}

public double darPrecio()
	{
		return precio;
	}

@Override
public String generarTextoFactura() {
	String texto;
	texto = "\n Productos: " + darNombre() + ": " + darPrecio();
	return texto;
}

public void setPrecio(double descuento)
{
	this.precio = this.precio*descuento;
}

@Override
public void setPrecio() {
	// TODO Auto-generated method stub
	
}

public void setCalorias(double calorias)
{
	this.calorias = calorias;
}

public double darCalorias(){return calorias;}

}
