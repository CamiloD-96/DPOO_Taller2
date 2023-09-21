package uniandes.dpoo.taller2.modificacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.taller2.consola.Consola;
import uniandes.dpoo.taller2.modelo.Combo;
import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.ProductoMenu;
import uniandes.dpoo.taller2.procesamiento.ControllerRestaurante;
import uniandes.dpoo.taller2.procesamiento.LoaderRestaurante;
import uniandes.dpoo.taller2.modificacion.Bebidas;

public class Modificacion{
	
	private Consola consola;
	
	private ControllerModif controller;
	
	public static void main(String[] args)
	{
		Modificacion modif = new Modificacion();
		modif.cargarData();
		Consola consola = new Consola();
		consola.ejecutarCargarData();
		consola.ejecutarAplicacion();
	}
	
	public void cargarData()
	{
		try
		{
			String archivoCombos = "./data/combos_modif.txt";
			String archivoIngredientes = "./data/ingredientes_modif.txt";
			String archivoMenu = "./data/menu_modif.txt";
			String archivoBebidas = "./data/bebidas_modif.txt";
			//archivo = "./data/atletas.csv";
			//System.out.println(archivoCombos + archivoIngredientes + archivoMenu);
			controller = ControllerModif.cargarArchivo(archivoMenu,archivoIngredientes,archivoCombos, archivoBebidas);
			System.out.println("Se cargó el archivo " + archivoCombos + " con información de los combos.");
			System.out.println("Se cargó el archivo " + archivoIngredientes + " con información de los ingredientes.");
			System.out.println("Se cargó el archivo " + archivoMenu + " con información del menu.");
			System.out.println("Se cargó el archivo " + archivoBebidas + " con información del menu.");

		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontro.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
	}
	
}
