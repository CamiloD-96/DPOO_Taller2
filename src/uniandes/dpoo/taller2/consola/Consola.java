package uniandes.dpoo.taller2.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.taller2.modelo.Combo;
import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.Pedido;
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
				{
					mostrarMenu();
				}
				else if (opcion_seleccionada == 1)
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
				else if (opcion_seleccionada == 7)
				{
					pedidoRepetido();
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
		if (controllerRestaurante.estaActivo())
		{
			System.out.println("Cierre el pedido anterior para poder iniciar un nuevo pedido");
			return;
		} 
		System.out.println("Para iniciar su pedido digita la siguiente información: \n\n");
		String nombre = input("Digite su nombre: ");
		String direccion = input("Digite su dirección: ");
		controllerRestaurante.iniciarPedido(nombre, direccion);
		pedirProductosDelPedido();
		System.out.println("\nPedido registrado");
	}
	
	private void pedirProductosDelPedido()
	{
		System.out.println("\nEscoja los productos que quiere en su pedido: ");
		String combo = input("\nQuiere un combo? (SI/NO): ");
		
		if (combo.toLowerCase().equals("si"))
		{
			int comboEscogido = Integer.parseInt(input("\nDigita el código del combo escogido: "));
			boolean revision = revisionCodigo(0,comboEscogido); // 0 representa combos
			if (revision == false) {System.out.println("\nOpción de combo no existente, vuelva a elegir");pedirProductosDelPedido();};
			Combo producto = controllerRestaurante.darCombos().get(comboEscogido-1);
			controllerRestaurante.darPedidoEnCurso().agregarComboAPedido(producto);
		}
		
		else if (combo.toLowerCase().equals("no"))
		{
			int productoEscogido = Integer.parseInt(input("\nDigita el código del producto escogido: "));
			boolean revision = revisionCodigo(1,productoEscogido); // 1 representa producto
			if (revision == false) {System.out.println("\nOpción de producto no existente, vuelva a elegir");pedirProductosDelPedido();};		
			ProductoMenu producto =  controllerRestaurante.darMenu().get(productoEscogido - 1);
			controllerRestaurante.darPedidoEnCurso().agregarProductoAPedido(producto);
		}
		
		else {
			System.out.println("\nOpción no válida, vuelva a elegir");
			pedirProductosDelPedido();
		}
		
		String adiciones = input("\nDesea agregar algún producto adicional a su pedido?(SI/NO): ");
		if (adiciones.toLowerCase().equals("no")){ return ;}
		else if(adiciones.toLowerCase().equals("si")) {adicionarIngredientes();}
		
	}
	private void adicionarIngredientes() 
	{
		int productoEscogido = Integer.parseInt(input("\nDigita el código del producto escogido: "));
		boolean revision = revisionCodigo(2,productoEscogido); // 2 representa ingrediente
		if (revision == false) {System.out.println("\nOpción de producto no existente, vuelva a elegir");adicionarIngredientes();};		
		Ingrediente ingrediente = controllerRestaurante.darIngredientes().get(productoEscogido - 1);
		controllerRestaurante.darPedidoEnCurso().agregarIngredienteAPedido(ingrediente);	
		
	//Agregar más ingredientes
		String adiciones = input("\nDesea agregar algún otro producto adicional a su pedido?(SI/NO): ");
		if (adiciones.toLowerCase().equals("no")){ return ;}
		else if(adiciones.toLowerCase().equals("si")) {adicionarIngredientes();}
	}

	private void eliminarIngredientes(){
		int productoEscogido = Integer.parseInt(input("\nDigita el código del producto escogido a eliminar: "));
		boolean revision = revisionCodigo(2,productoEscogido); // 2 representa ingrediente
		if (revision == false) {System.out.println("\nOpción de producto no existente, vuelva a elegir");eliminarIngredientes();};		
		Ingrediente ingrediente = controllerRestaurante.darIngredientes().get(productoEscogido - 1);
		controllerRestaurante.darPedidoEnCurso().agregarIngredienteEliminado(ingrediente);	
		
	//Eliminar más ingredientes
		String adiciones = input("\nDesea eliminar algún otro producto adicional a su pedido?(SI/NO): ");
		if (adiciones.toLowerCase().equals("no")){ return ;}
		else if(adiciones.toLowerCase().equals("si")) {eliminarIngredientes();}
	}



	private void cerrarYGuardarPedido() {
		if(controllerRestaurante.estaActivo())
		{
		controllerRestaurante.cerrarPedido();
		System.out.println("\nPedido guardado y cerrado");
		}
		else {System.out.println("\nNo hay pedidos activos para cerrar");}
		
		
	}
	
	private boolean revisionCodigo(int tipo, int escogido) {
		boolean bool = false;
		if (tipo == 0)
		{
			if(escogido > 0 && escogido <= controllerRestaurante.darCombos().size()){bool = true;};      
		}
		else if (tipo == 1)
		{
			if(escogido > 0 && escogido <= controllerRestaurante.darMenu().size()){bool = true;};      
		}
		else 
		{
			if(escogido >= 0 && escogido <= controllerRestaurante.darIngredientes().size()){bool = true;};      
		}
		return bool;
	}



	private void consultarPedido() {
		int idPedido = Integer.parseInt(input("\nDigita el id del pedido a buscar: "));
		Pedido buscado = controllerRestaurante.darInfoPedido(idPedido);
		if (buscado != null)
		{
			System.out.println("\nEl pedido buscado tiene la siguientes características: " + buscado.crearTextoFactura());
		}
		

	}

	private void eliminarIngredientePedido() {
		if(controllerRestaurante.estaActivo())
		{
		eliminarIngredientes();
		System.out.println("\nIngrediente eliminado");
		}
		else {System.out.println("\nNo hay pedidos activos para eliminar ingredientes");}
	}

	private void agregarIngredientePedido() {
		if(controllerRestaurante.estaActivo())
		{
		adicionarIngredientes();
		System.out.println("\nIngrediente adicionado");
		}
		else {System.out.println("\nNo hay pedidos activos para agregar ingredientes");}
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
		System.out.println("6. Salir de la aplicación");
		System.out.println("7. Validar sin un pedido esta repetido\n");
		System.out.print("8. Agregar bebida al pedido (Solo usar al correr el modif)\n");
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
	
	public void ejecutarCargarData()
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

	public void pedidoRepetido()
	{
		int opcionSeleccionada = Integer.parseInt(input("Por favor ingrese el id del pedido a revisar"));
		controllerRestaurante.repetidoPedido(opcionSeleccionada);
	}
	public static void main(String[] args)
	{
		Consola consola = new Consola();
		consola.ejecutarCargarData();
		consola.ejecutarAplicacion();
		
	}
}