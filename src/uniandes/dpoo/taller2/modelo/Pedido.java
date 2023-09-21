package uniandes.dpoo.taller2.modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import uniandes.dpoo.taller2.modificacion.Bebidas;

public class Pedido {

	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Interface> productosPedido;
	private static int numeroPedidos = 0;
	private ArrayList<Ingrediente> ingredientesPedido;
	private ArrayList<Ingrediente> ingredientesEliminadosPedido;
	private ArrayList<Combo> combosPedido;
	private ArrayList<Bebidas> bebidasPedido;
	private double calorias;
	
	public Pedido(String nom, String dir)
	{
		this.nombreCliente = nom;
		this.direccionCliente = dir;
		idPedido = numeroPedidos;
		productosPedido = new ArrayList<>();
		ingredientesPedido = new ArrayList<>();
		ingredientesEliminadosPedido = new ArrayList<>();
		combosPedido = new ArrayList<>();
		calorias = 0;
	}
	
	public static void incrementarNumeroPedidos() 
	{ 
		numeroPedidos += 1; 
	}

	public int darIdPedido()
	{
		return idPedido;
	}
	
	public String darNombreCliente()
	{
		return nombreCliente;
	}
	
	public String darDireccionCliente()
	{
		return direccionCliente;
	}
	
	public void agregarComboAPedido(Combo combo)
	{
		combosPedido.add(combo);
	}
	
	public void agregarProductoAPedido(Interface producto)
	{
		productosPedido.add(producto);
	}
	
	public void agregarIngredienteAPedido(Ingrediente ingrediente)
	{
		ingredientesPedido.add(ingrediente);
	}
	
	public void agregarIngredienteEliminado(Ingrediente ingrediente)
	{
		ingredientesEliminadosPedido.add(ingrediente);
	}
	
	public ArrayList<Interface> darProductosPedido()
	{
		return productosPedido;
	}
	
	public double darPrecioPedidoSinIva()
	{
		double precio = 0;
		for (Combo h: combosPedido)
		{
			precio += h.darPrecio();
		}
		for ( Interface i : productosPedido)
		{	
			precio += i.darPrecio();
		}
		
		for(Ingrediente j: ingredientesPedido)
		{
			precio += j.darCostoAdicional();
		}
		return precio;
	}
	
	public double darIva()
	{
		return darPrecioPedidoSinIva()*0.19;
	}
	
	public double darPrecioFinal()
	{
		return darPrecioPedidoSinIva() + darIva();
	}
	
	public String crearTextoFactura() { 
		
		String texto = ""; 
		texto += "HAMBURGUESAS EL NORRAL\nFactura NumPedido: " + this.idPedido + "\nNombre: " + this.nombreCliente + "\nDirección: " + this.direccionCliente;
		
		for (Combo h: combosPedido)
		{
			texto += "\n Combos: " + h.darNombre() + ": " + h.darPrecio();
		}
		
		for (Interface i : productosPedido)
			texto += i.generarTextoFactura(); 
		
		for (Ingrediente a: ingredientesPedido)
			{
				texto += "\n Ingrediente adicional: " + a.darNombre() + ": " + a.darCostoAdicional();
			}
		
		for (Ingrediente a: ingredientesEliminadosPedido)
		{
			texto += "\n Ingrediente eliminado: " + a.darNombre();
		}
		texto += "\n\nPrecio sin Iva: $" + this.darPrecioPedidoSinIva(); 
		texto += "\nIVA: $" + this.darIva(); 
		texto += "\nPrecio Total: $" + this.darPrecioFinal(); 
		return texto; 
		} 
	
	public String validadorIguales() { 
		
		String texto = ""; 
		texto += "\nNombre: " + this.nombreCliente + "\nDirección: " + this.direccionCliente;
		
		for (Combo h: combosPedido)
		{
			texto += "\n Combos: " + h.darNombre() + ": " + h.darPrecio();
		}
		
		for (Interface i : productosPedido)
			texto += i.generarTextoFactura(); 
		
		for (Ingrediente a: ingredientesPedido)
			{
				texto += "\n Ingrediente adicional: " + a.darNombre() + ": " + a.darCostoAdicional();
			}
		
		for (Ingrediente a: ingredientesEliminadosPedido)
		{
			texto += "\n Ingrediente eliminado: " + a.darNombre();
		}
		texto += "\n\nPrecio sin Iva: $" + this.darPrecioPedidoSinIva(); 
		texto += "\nIVA: $" + this.darIva(); 
		texto += "\nPrecio Total: $" + this.darPrecioFinal(); 
		return texto; 
		} 
	
	public void guardarTextoEnArchivo() {
        try {
        	String nombreArchivo = "./data/facturas/" + this.idPedido + ".txt";    
            BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
            writer.write(crearTextoFactura());
            writer.close();
            System.out.println("La factura se ha guardado en " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar la factura en el archivo.");
            e.printStackTrace();
        }
	}
	
	public void agregarBebidasAPedido(Bebidas bebida)
	{
		bebidasPedido.add(bebida);
	}
}
