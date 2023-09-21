package uniandes.dpoo.taller2.modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Interface {
	
	private ProductoMenu productoInicial;
	private ArrayList<Ingrediente> productosAgregados;
	private ArrayList<Ingrediente> productosEliminados;

	public ProductoAjustado(ProductoMenu base, ArrayList<Ingrediente> agregados, ArrayList<Ingrediente> eliminados)
	{
		this.productoInicial = base;
		this.productosAgregados = agregados;
		this.productosEliminados = eliminados;
	}
	@Override
	public double darPrecio() {
		double precio = 0;
		for(int i = 0; i<productosAgregados.size()-1;i++)
			{
				precio += productosAgregados.get(i).darCostoAdicional();
			}
		precio += productoInicial.darPrecio();
		return precio;
	}
	

	@Override
	public String darNombre() {
		// TODO Auto-generated method stub
		return productoInicial.darNombre();
	}

	@Override
	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		String texto;
		texto = "\n-" + darNombre() + ": " + darPrecio();
		//Agregar el producto y precio de los otros productos a la factura
		for(int i = 0; i<productosAgregados.size()-1;i++)
		{
			texto += "\n Producto agregado:" + productosAgregados.get(i).darNombre() + ": " + productosAgregados.get(i).darCostoAdicional();
		}
		
		//Agregar el nombre de los productos eliminados
		for(int i = 0; i<productosAgregados.size()-1;i++)
		{
			texto += "\n Producto eliminado:" + productosEliminados.get(i).darNombre(); 
		}
		return texto;
	}
	@Override
	public void setPrecio() {
		// TODO Auto-generated method stub
		
	}

}
