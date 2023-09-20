package uniandes.dpoo.taller2.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import uniandes.dpoo.taller2.modelo.Combo;
import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.ProductoMenu;
import uniandes.dpoo.taller2.procesamiento.ControllerRestaurante;
import uniandes.dpoo.taller2.procesamiento.LoaderRestaurante;
public class Consola {
	
	private ControllerRestaurante controllerRestaurante;


	/**
	 * Ejecuta la aplicación: le muestra el menú al usuario y la pide que ingrese
	 * una opción, y ejecuta la opción seleccionada por el usuario. Este proceso se
	 * repite hasta que el usuario seleccione la opción de abandonar la aplicación.
	 */
	public void ejecutarAplicacion()
	{
		System.out.println("\n\nBienvenido a su Restaurante preferido!, El Norral! \n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarOpciones();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 0)
					mostrarMenu();
					mostrarOpciones();
				if (opcion_seleccionada == 1)
					iniciarPedido();
				else if (opcion_seleccionada == 2)
					cerrarYGuardarPedido();
				else if (opcion_seleccionada == 3)
					agregarIngredientePedido();
				else if (opcion_seleccionada == 4)
					eliminarIngredientePedido();
				else if (opcion_seleccionada == 5)
					consultarPedido();
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}



	private void iniciarPedido() {
		// TODO Auto-generated method stub
		System.out.println("Para iniciar su pedido digita la siguiente información: \n\n");
		String nombre = input("Digite su nombre: ");
		String direccion = input("Digite su dirección: ");
		controllerRestaurante.iniciarPedido(nombre, direccion);
		System.out.println("\nPedido registrado");
	}
	
	private void cerrarYGuardarPedido() {
		// TODO Auto-generated method stub
		
	}
	
	private void consultarPedido() {
		// TODO Auto-generated method stub

	}

	private void eliminarIngredientePedido() {
		// TODO Auto-generated method stub
		
	}

	private void agregarIngredientePedido() {
		// TODO Auto-generated method stub
		
	}



	/**
	 * Muestra al usuario el menú con las opciones para que escoja la siguiente
	 * acción que quiere ejecutar.
	 */
	public void mostrarOpciones()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("0. Mostrar Menú");
		System.out.println("1. Iniciar pedido");
		System.out.println("2. Cerrar y guardar pedido");
		System.out.println("3. Agregar ingrediente al pedido");
		System.out.println("4. Eliminar ingrediente al pedido");
		System.out.println("5. Consultar sobre un pedido, ingresando el id");
		System.out.println("6. Salir de la aplicación\n");
	}
	
	
	public void mostrarMenu()
	{
		System.out.println("\n---- MENÚ ----\n");
		
		// Productos
        System.out.println("Productos:\n");
        List<ProductoMenu> productos = controllerRestaurante.darMenu();
        int j = 1;
        for (ProductoMenu i: productos)
        {
        	System.out.println(j + ".) " +i.darNombre() +" $" + i.darPrecio());
        	j  ++;
        }
        
        System.out.println("\nCombos:\n");
        List<Combo> combos = controllerRestaurante.darCombos();
        j = 1;
        for (Combo i: combos)
        {
        	System.out.println(j + ".) " +i.darNombre() +" $" + i.darPrecio());
        	j  ++;
        }        
        
        // Ingredientes adicionales
        System.out.println("\nIngredientes adicionales:\n");
        List<Ingrediente> ingredientes = controllerRestaurante.darIngredientes();
        j = 1;
        for (Ingrediente i: ingredientes)
        {
        	System.out.println(j + ".) " +i.darNombre() +" $" + i.darCostoAdicional());
        	j  ++;
        }   

	}
	
	private void ejecutarCargarData()
	{
		try
		{
			String archivoCombos = "./data/combos.txt";
			String archivoIngredientes = "./data/ingredientes.txt";
			String archivoMenu = "./data/menu.txt";
			//archivo = "./data/atletas.csv";
			//System.out.println(archivoCombos + archivoIngredientes + archivoMenu);
			controllerRestaurante = LoaderRestaurante.cargarArchivo(archivoMenu,archivoIngredientes,archivoCombos);
			System.out.println("Se cargó el archivo " + archivoCombos + " con información de los combos.");
			System.out.println("Se cargó el archivo " + archivoIngredientes + " con información de los ingredientes.");
			System.out.println("Se cargó el archivo " + archivoMenu + " con información del menu.");

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
	
	/**
	 * Este método sirve para imprimir un mensaje en la consola pidiéndole
	 * información al usuario y luego leer lo que escriba el usuario.
	 * 
	 * @param mensaje El mensaje que se le mostrará al usuario
	 * @return La cadena de caracteres que el usuario escriba como respuesta.
	 */
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	
	public static void main(String[] args)
	{
		Consola consola = new Consola();
		consola.ejecutarCargarData();
		consola.ejecutarAplicacion();
		
	}
}