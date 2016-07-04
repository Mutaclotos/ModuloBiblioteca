package biblioteca.modulovisitas;

import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;

public class Contenedor {
	public static Container obtenerContenedor(String datos){
		Container c = new IndexedContainer();
		c.addContainerProperty("TipoUsuario", String.class, "");
		c.addItem("Estudiante");
		c.addItem("Funcionario");
		c.addItem("Asociado");
		c.addItem("Publico");
		return c;
	}
	
	public static Container obtenerContenedor2(String datos){
		Container c = new IndexedContainer();
		c.addContainerProperty("TipoConsulta", String.class, "");
		c.addItem("Telefonica");
		c.addItem("Presencial");
		return c;
	}
	
	public static Container obtenerContenedor3(String datos){
		Container c = new IndexedContainer();
		c.addContainerProperty("TipoBusqueda", String.class, "");
		c.addItem("Usuario");
		c.addItem("Tema");
		c.addItem("Fecha emisión");
		return c;
	}
}
