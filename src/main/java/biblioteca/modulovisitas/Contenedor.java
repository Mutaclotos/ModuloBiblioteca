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
}
