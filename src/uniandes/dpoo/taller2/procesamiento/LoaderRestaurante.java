package uniandes.dpoo.taller2.procesamiento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uniandes.dpoo.taller2.modelo.Combo;
import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.ProductoMenu;

public class LoaderRestaurante {
	
	public static double toDouble(String s)
	{
		return Double.parseDouble(s);
	}

	public static ControllerRestaurante cargarArchivo(String nombreArchivoMenu, String nombreArchivoIngredientes, String nombreArchivoCombos) throws FileNotFoundException, IOException
	{
	//Map<String, ProductoMenu> menu = new HashMap<>();
	// Inicializo Listas
		List<ProductoMenu> menu = new ArrayList<>();
		List<Ingrediente> ingredientes = new ArrayList<>();
		List<Combo> combos = new ArrayList<>();
		
	// Lleno Lista1, el menu con su respectivo archivo
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivoMenu));
		String linea = br.readLine(); // La primera línea del archivo se ignora porque únicamente tiene los títulos de
										// las columnas
		//linea = br.readLine();
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(";");
			String nombreProducto = partes[0];
			String precioProducto = partes[1];

			// Si no se había encontrado antes este producto, se agrega como uno nuevo
			ProductoMenu producto = buscarProductoMenu(menu, nombreProducto);
			if (producto == null)
			{
				producto = new ProductoMenu(nombreProducto, toDouble(precioProducto));
				menu.add(producto);
			}

			linea = br.readLine(); // Leer la siguiente línea
				br.close();
				//System.out.println(menu);
		}
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

				// Si no se había encontrado antes este producto, se agrega como uno nuevo
				Ingrediente ingrediente = buscarIngrediente(ingredientes, nombreIngrediente);
				if (ingrediente == null)
				{
					ingrediente = new Ingrediente(nombreIngrediente, toDouble(precioIngrediente));
					ingredientes.add(ingrediente);
				}

				linea1 = br1.readLine(); // Leer la siguiente línea
					br1.close();
					//System.out.println(menu);
			}		
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
				String descuentoCombo = partes2[1];
				String producto1Combo = partes2[2];
				String producto2Combo = partes2[3];
				String producto3Combo = partes2[4];

				// Si no se había encontrado antes este producto, se agrega como uno nuevo
				Combo combo = buscarCombo(combos, nombreCombo);
				if (combo == null)
				{
					ProductoMenu producto1 = buscarProductoMenu(menu, producto1Combo);
					ProductoMenu producto2 = buscarProductoMenu(menu, producto2Combo);
					ProductoMenu producto3 = buscarProductoMenu(menu, producto3Combo);
					combo = new Combo(nombreCombo, toDouble(descuentoCombo), producto1, producto2, producto3);
					combos.add(combo);
				}

				linea2 = br2.readLine(); // Leer la siguiente línea
					br2.close();
					//System.out.println(menu);
			}
		
		ControllerRestaurante controller = new ControllerRestaurante(menu, ingredientes, combos);
		return controller;
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
