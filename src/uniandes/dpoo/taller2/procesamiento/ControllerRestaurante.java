package uniandes.dpoo.taller2.procesamiento;

import java.util.List;

import uniandes.dpoo.taller2.modelo.Combo;
import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.ProductoMenu;



public class ControllerRestaurante {
	private List<ProductoMenu> menu;

	/**
	 * Una lista con todos los países para los cuales hay al menos un atleta.
	 */
	private List<Ingrediente> ingredientes;

	/**
	 * Una lista con los eventos registrados. En esta lista puede aparecer dos veces
	 * el mismo deporte pero sólo si corresponde a años diferentes.
	 */
	private List<Combo> combos;
	
	public ControllerRestaurante(List<ProductoMenu> menu, List<Ingrediente> ingredientes,List<Combo> combos ){
		this.menu = menu;
		this.ingredientes = ingredientes;
		this.combos = combos;
	}
}
