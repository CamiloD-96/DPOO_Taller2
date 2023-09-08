package uniandes.dpoo.taller2.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import uniandes.dpoo.taller0.procesamiento.LoaderOlimpicos;
import uniandes.dpoo.taller2.procesamiento.ControllerRestaurante;
import uniandes.dpoo.taller2.modelo.Restaurante;
public class Consola {
	
	private Restaurante restaurante;
	
	private String archivo;


	/**
	 * Ejecuta la aplicación: le muestra el menú al usuario y la pide que ingrese
	 * una opción, y ejecuta la opción seleccionada por el usuario. Este proceso se
	 * repite hasta que el usuario seleccione la opción de abandonar la aplicación.
	 */
	public void ejecutarAplicacion()
	{
		System.out.println("Bienvenido a su Restaurante preferido!, El Norral! \n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					ejecutarCargarAtletas();
				else if (opcion_seleccionada == 2 && calculadora != null)
					ejecutarAtletasPorAnio();
				else if (opcion_seleccionada == 3 && calculadora != null)
					ejecutarMedallasEnRango();
				else if (opcion_seleccionada == 4 && calculadora != null)
					ejecutarAtletasPorPais();
				else if (opcion_seleccionada == 5 && calculadora != null)
					ejecutarPaisConMasMedallistas();
				else if (opcion_seleccionada == 6 && calculadora != null)
					ejecutarMedallistasPorEvento();
				else if (opcion_seleccionada == 7 && calculadora != null)
					ejecutarAtletasConMasMedallasQue();
				else if (opcion_seleccionada == 8 && calculadora != null)
					ejecutarAtletaEstrella();
				else if (opcion_seleccionada == 9 && calculadora != null)
					ejecutarMejorPaisEnUnEvento();
				else if (opcion_seleccionada == 10 && calculadora != null)
					ejecutarTodoterreno();
				else if (opcion_seleccionada == 11 && calculadora != null)
					ejecutarMedallistasPorNacionYGenero();
				else if (opcion_seleccionada == 12 && calculadora != null)
					ejecutarPorcentajeMedallistas();
				else if (opcion_seleccionada == 13 && calculadora != null)
					ejecutarPaisAtlteta();
				else if (opcion_seleccionada == 14)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else if (calculadora == null)
				{
					System.out.println("Para poder ejecutar esta opción primero debe cargar un archivo de atletas.");
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

	/**
	 * Muestra al usuario el menú con las opciones para que escoja la siguiente
	 * acción que quiere ejecutar.
	 */
	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Iniciar pedido");
		System.out.println("2. Cerrar y guardar pedido");
		System.out.println("3. Consultar las medallas de un atleta en un periodo");
		System.out.println("4. Consultar los atletas de un país dado");
		System.out.println("5. Consultar el país con más medallistas");
		System.out.println("6. Consultar todos los medallistas de un evento dado");
		System.out.println("7. Consultar los atletas con un mínimo de medallas");
		System.out.println("8. Consultar el atleta estrella de todos los tiempos");
		System.out.println("9. Consultar mejor país en un evento");
		System.out.println("10. Consultar el atleta todoterreno");
		System.out.println("11. Consultar los medallistas por país y género");
		System.out.println("12. Consultar el porcentaje de atletas que son medallistas");
		System.out.println("13. Consultar el país de un atleta");
		System.out.println("14. Salir de la aplicación\n");
		
	}
	
	private void ejecutarCargarData()
	{
		try
		{
			archivo = "./data/atletas.csv";
			controller = LoaderRestaurante.cargarArchivo(archivo);
			System.out.println("Se cargó el archivo " + archivo + " con información de los Juegos Olímpicos.");
			Collection<String> eventos = calculadora.darNombresDeportes();
			System.out.println("Los deportes para los que se tiene información son:");
			for (String dep : eventos)
			{
				System.out.println(" - " + dep);
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontró.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}

	}
	
	public static void main(String[] args)
	{
		Consola consola = new Consola();
		consola.ejecutarAplicacion();
		consola.ejecutarCargarData();
	}
}