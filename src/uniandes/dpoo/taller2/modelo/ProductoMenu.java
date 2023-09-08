package uniandes.dpoo.taller2.modelo;


public class ProductoMenu implements Interface {

private String nombre;

private double precio;


public ProductoMenu(String elNombre, double elPrecio)
	{
		this.nombre = elNombre;
		this.precio = elPrecio;
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
	// TODO Auto-generated method stub
	return null;
}
}
