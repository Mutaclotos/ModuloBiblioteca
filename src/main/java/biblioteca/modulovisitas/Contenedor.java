package biblioteca.modulovisitas;

import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;

public class Contenedor {
	public static Container obtenerContenedorTipoUsuario(String datos){
		Container c = new IndexedContainer();
		c.addContainerProperty("TipoUsuario", String.class, "");
		c.addItem("Estudiante");
		c.addItem("Funcionario");
		c.addItem("Asociado");
		c.addItem("Publico");
		return c;
	}
	
	
	public static Container obtenerContenedorTipoConsulta(String datos){
		Container c = new IndexedContainer();
		c.addContainerProperty("TipoConsulta", String.class, "");
		c.addItem("Telefonica");
		c.addItem("Presencial");
		return c;
	}
	
	public static Container obtenerContenedorTipoBusqueda(String datos){
		Container c = new IndexedContainer();
		c.addContainerProperty("TipoBusqueda", String.class, "");
		c.addItem("Nombre de usuario");
		c.addItem("Tema de consulta");
		c.addItem("Fecha de emisión");
		return c;
	}
	
	public static Container obtenerContenedorTipoBusqueda2(String datos){
		Container c = new IndexedContainer();
		c.addContainerProperty("TipoBusqueda", String.class, "");
		c.addItem("Nombre de usuario");
		c.addItem("Tema de consulta");
		c.addItem("Fecha de emisión");
		c.addItem("Fecha de entrega");
		return c;
	}
	
	public static Container obtenerContenedorTipoDocumento(String datos){
		Container c = new IndexedContainer();
		c.addContainerProperty("TipoDocumento", String.class, "");
		c.addItem("Libro");
		c.addItem("Revista");
		c.addItem("Tesis");
		return c;
	}
}
