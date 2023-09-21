package uniandes.dpoo.taller2.modificacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.taller2.modelo.Combo;
import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.Pedido;
import uniandes.dpoo.taller2.modelo.ProductoMenu;
import uniandes.dpoo.taller2.procesamiento.ControllerRestaurante;
import uniandes.dpoo.taller2.modificacion.Bebidas;




public class ControllerModif {
	
	private List<ProductoMenu> menu;

	private List<Ingrediente> ingredientes;

	private List<Combo> combos;

	private List<Bebidas> bebidas;
	
	private List<Pedido> pedidos;
	
	private Pedido pedidoEnCurso;

	public ControllerModif(List<ProductoMenu> menu, List<Ingrediente> ingredientes, List<Combo> combos, List<Bebidas> bebidas) {
		this.menu = menu;
		this.ingredientes = ingredientes;
		this.combos = combos;
		this.pedidos = new ArrayList<>();
		this.bebidas = bebidas;
	}

	public static double toDouble(String s)
	{
		return Double.parseDouble(s);
	}

	public static ControllerModif cargarArchivo(String nombreArchivoMenu, String nombreArchivoIngredientes, String nombreArchivoCombos, String nombreArchivoBebidas) throws FileNotFoundException, IOException
	{
	//Map<String, ProductoMenu> menu = new HashMap<>();
	// Inicializo Listas
		List<ProductoMenu> menu = new ArrayList<>();
		List<Ingrediente> ingredientes = new ArrayList<>();
		List<Combo> combos = new ArrayList<>();
		List<Bebidas> bebidas = new ArrayList<>();
		
	// Lleno Lista1, el menu con su respectivo archivo
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivoMenu));
		String linea = br.readLine();
		
		
		//linea = br.readLine();
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(";");
			String nombreProducto = partes[0];
			String precioProducto = partes[1];
			String calorias = partes[2];
			// Si no se había encontrado antes este producto, se agrega como uno nuevo
			ProductoMenu producto = buscarProductoMenu(menu, nombreProducto);
			if (producto == null)
			{
				producto = new ProductoMenu(nombreProducto, toDouble(precioProducto));
				producto.setCalorias(toDouble(calorias));
				menu.add(producto);
			}
			linea = br.readLine(); // Leer la siguiente línea
				//System.out.println(menu);
		}
		br.close();
	// Lleno Lista2, la lista ingredientes con su respectivo archivo
			BufferedReader br1 = new BufferedReader(new FileReader(nombreArchivoIngredientes));
			String linea1 = br1.readLine(); // La primera línea del archivo se ignora porque únicamente tiene los títulos de
											// las columnas
			//linea1 = br1.readLine();
			while (linea1 != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
			{
				// Separar los valores que estaban en una línea
				String[] partes1 = linea1.split(";");
				String nombreIngrediente = partes1[0];
				String precioIngrediente = partes1[1];
				String calorias = partes1[2];
				// Si no se había encontrado antes este producto, se agrega como uno nuevo
				Ingrediente ingrediente = buscarIngrediente(ingredientes, nombreIngrediente);
				if (ingrediente == null)
				{
					ingrediente = new Ingrediente(nombreIngrediente, toDouble(precioIngrediente));
					ingrediente.setCalorias(toDouble(calorias));
					ingredientes.add(ingrediente);
				}

				linea1 = br1.readLine(); // Leer la siguiente línea
					//System.out.println(menu);
			}
			br1.close();
	// Lleno Lista3, la lista combos con su respectivo archivo
			BufferedReader br2 = new BufferedReader(new FileReader(nombreArchivoCombos));
			String linea2 = br2.readLine(); // La primera línea del archivo se ignora porque únicamente tiene los títulos de
											// las columnas
			//linea1 = br1.readLine();
			while (linea2 != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
			{
				// Separar los valores que estaban en una línea
				String[] partes2 = linea2.split(";");
				String nombreCombo = partes2[0];
				String descuentoCombo = partes2[1].replace("%", "");
				String producto1Combo = partes2[2];
				String producto2Combo = partes2[3];
				String producto3Combo = partes2[4];
				String calorias = partes2[5];

				// Si no se había encontrado antes este producto, se agrega como uno nuevo
				Combo combo = buscarCombo(combos, nombreCombo);
				if (combo == null)
				{
					ProductoMenu producto1 = buscarProductoMenu(menu, producto1Combo);
					ProductoMenu producto2 = buscarProductoMenu(menu, producto2Combo);
					ProductoMenu producto3 = buscarProductoMenu(menu, producto3Combo);
					combo = new Combo(nombreCombo, toDouble(descuentoCombo) / 100.0, producto1, producto2, producto3);
					combo.setCalorias(toDouble(calorias));
					combos.add(combo);
				}

				linea2 = br2.readLine(); // Leer la siguiente línea
					//System.out.println(menu);
			}
			br2.close();
			
			// Lleno Lista4, el menu con su respectivo archivo
			BufferedReader br3 = new BufferedReader(new FileReader(nombreArchivoMenu));
			String linea3 = br.readLine();
			
			
			//linea = br.readLine();
			while (linea3 != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
			{
				// Separar los valores que estaban en una línea
				String[] partes3 = linea3.split(";");
				String nombreProducto = partes3[0];
				String precioProducto = partes3[1];
				String calorias = partes3[2];
				// Si no se había encontrado antes este producto, se agrega como uno nuevo
				Bebidas bebida = buscarProductoBebidas(bebidas, nombreProducto);
				if (bebida == null)
				{
					bebida = new Bebidas(nombreProducto, toDouble(precioProducto));
					bebida.setCalorias(toDouble(calorias));
					bebidas.add(bebida);
				}
				linea3 = br.readLine(); // Leer la siguiente línea
					//System.out.println(menu);
			}
			br3.close();
		
		ControllerModif controller = new ControllerModif(menu, ingredientes, combos,bebidas );
		return controller;
	}
	
	
	private static Bebidas buscarProductoBebidas(List<Bebidas> bebidas, String nombreProducto) {
		Bebidas producto = null;

		for (int i = bebidas.size() - 1; i >= 0 && producto == null; i--)
		{
			Bebidas unProducto = bebidas.get(i);
			if (unProducto.darNombre().equals(nombreProducto))
			{
				producto = unProducto;
			}
		}

		return producto;
	}

	private static ProductoMenu buscarProductoMenu(List<ProductoMenu> menu, String nombreProducto)
	{
		ProductoMenu producto = null;

		for (int i = menu.size() - 1; i >= 0 && producto == null; i--)
		{
			ProductoMenu unProducto = menu.get(i);
			if (unProducto.darNombre().equals(nombreProducto))
			{
				producto = unProducto;
			}
		}

		return producto;
	}
	
	private static Ingrediente buscarIngrediente(List<Ingrediente> ingredientes, String nombreIngrediente)
	{
		Ingrediente ingrediente = null;

		for (int i = ingredientes.size() - 1; i >= 0 && ingrediente == null; i--)
		{
			Ingrediente unIngrediente = ingredientes.get(i);
			if (unIngrediente.darNombre().equals(nombreIngrediente))
			{
				ingrediente = unIngrediente;
			}
		}

		return ingrediente;
	}
	
	private static Combo buscarCombo(List<Combo> combos, String nombreCombo)
	{
		Combo combo = null;

		for (int i = combos.size() - 1; i >= 0 && combo == null; i--)
		{
			Combo unCombo = combos.get(i);
			if (unCombo.darNombre().equals(nombreCombo))
			{
				combo = unCombo;
			}
		}

		return combo;
	}
}
