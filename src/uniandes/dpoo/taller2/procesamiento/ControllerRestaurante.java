package uniandes.dpoo.taller2.procesamiento;

import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.taller2.modelo.Combo;
import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.Interface;
import uniandes.dpoo.taller2.modelo.Pedido;
import uniandes.dpoo.taller2.modelo.ProductoMenu;



public class ControllerRestaurante {
	private List<ProductoMenu> menu;

	private List<Ingrediente> ingredientes;

	private List<Combo> combos;
	
	private List<Pedido> pedidos;
	
	private Pedido pedidoEnCurso;
	
	public ControllerRestaurante(List<ProductoMenu> menu, List<Ingrediente> ingredientes,List<Combo> combos){
		this.menu = menu;
		this.ingredientes = ingredientes;
		this.combos = combos;
		this.pedidos = new ArrayList<>();
	}

	public void iniciarPedido(String nombre, String direccion) {
		Pedido.incrementarNumeroPedidos();
		pedidoEnCurso = new Pedido(nombre,direccion);
		pedidos.add(pedidoEnCurso);
		
	}
	
	public List<ProductoMenu> darMenu()
	{
		return menu;
	}
	
	public List<Ingrediente> darIngredientes()
	{
		return ingredientes;
	}
	
	public List<Combo> darCombos()
	{
		return combos;
	}
	
	public Pedido darPedidoEnCurso()
	{
		return pedidoEnCurso;
	}
	
	public Combo buscarCombo(String nombreCombo)
	{
		Combo encontrado = null;
		for(Combo i: combos)
		{
			if(i.darNombre() == nombreCombo) {encontrado = i;};
		}
		return encontrado;
	}
	
	public void cerrarPedido()
	{
		pedidoEnCurso.guardarTextoEnArchivo();
		setPedidoActualToNull();
		
	}
	
	public void repetidoPedido(int id)
	{
		try {
			Pedido revisar = darInfoPedido(id);
			int contador = 0;
			for (Pedido a: pedidos)
			{
				//if (pedidoEnCurso.darNombreCliente().equals(a.darNombreCliente())&& pedidoEnCurso.darDireccionCliente().equals(a.darDireccionCliente())&&pedidoEnCurso.)
					if (revisar.validadorIguales().equals(a.validadorIguales()))	{// && contador >0
						contador += 1;
						}
			}
			if(contador >0) {System.out.println("Pedido repetido ");}
		
		}
		
		catch(NullPointerException e){System.out.println("Pedido no encontrado.");return;}	
	}

	public void setPedidoActualToNull()
	{
		pedidoEnCurso = null;
	}

	public boolean estaActivo()
	{
		if(pedidoEnCurso == null) {return false;}
		return true;
	}
	
	public Pedido darInfoPedido(int id)
	{
		Pedido info = null;
		try {info = pedidos.get(id-1);}
		catch (NullPointerException e)
		{
			System.out.println("Pedido no encontrado.");
		}	
		return info;
	}
	
}
