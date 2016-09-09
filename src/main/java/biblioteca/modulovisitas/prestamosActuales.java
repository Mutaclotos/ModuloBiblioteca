package biblioteca.modulovisitas;

import java.sql.ResultSet;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

public class prestamosActuales extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Label label_2;
	@AutoGenerated
	private Accordion dropPantallas;
	@AutoGenerated
	private Table tablaDePrestamos;
	@AutoGenerated
	private Label etiquetaNombreBiblio;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */

	private final DBConnector dbc;
	
	private MyUI ui;

	public prestamosActuales()  {
		
		buildMainLayout();
		setCompositionRoot(mainLayout);
		dbc = new DBConnector("localhost","moises","315600");
		ui = new MyUI();
		
		
		tablaDePrestamos.addContainerProperty("fecha de retiro", String.class, null);
		tablaDePrestamos.addContainerProperty("nombre de libro", String.class, null);
		tablaDePrestamos.addContainerProperty("fecha devolucion", String.class, null);
		int i=2;
		
		
		ResultSet rs = dbc.query("SELECT p.Documento, p.fechaEntrega, p.fechaSalida from Prestamo p , Usuario u where p.usuario=u.cedula group by  p.Documento ;" );//añadir condicion de la cedula del usuario actual"
		
		
		
		
		
		try{	
		//TODO: datasource de resultSet, tableQuery	
			while(rs.next())
			{
				Integer itemId = new Integer(i);
				
				String fechaRetiro = rs.getString("fechaSalida"); 
				String nombreDocu = rs.getString("Documento");
				String fechaDevolucion = rs.getString("fechaEntrega");

				tablaDePrestamos.addItem(new Object[]{fechaRetiro,nombreDocu,fechaDevolucion}, itemId);
				
				i++;
				System.out.println(nombreDocu);
				System.out.println(fechaRetiro);
			}
					
		
		}catch(Exception sqe){
			System.out.println("faillllll");
		}
		

		tablaDePrestamos.setPageLength(tablaDePrestamos.size());
		
		
		
		// TODO add user code here
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// etiquetaNombreBiblio
		etiquetaNombreBiblio = new Label();
		etiquetaNombreBiblio.setImmediate(false);
		etiquetaNombreBiblio.setWidth("280px");
		etiquetaNombreBiblio.setHeight("40px");
		etiquetaNombreBiblio.setValue("Bibioteca Francisco Echeverria Garcìa");
		mainLayout.addComponent(etiquetaNombreBiblio, "top:20.0px;left:100.0px;");
		
		// tablaDePrestamos
		tablaDePrestamos = new Table();
		tablaDePrestamos.setImmediate(false);
		tablaDePrestamos.setWidth("500px");
		tablaDePrestamos.setHeight("-1px");
		mainLayout.addComponent(tablaDePrestamos, "top:120.0px;left:260.0px;");
		
		// dropPantallas
		dropPantallas = new Accordion();
		dropPantallas.setCaption("pantallas");
		dropPantallas.setImmediate(false);
		dropPantallas.setDescription("prestamos actuales");
		dropPantallas.setWidth("-1px");
		dropPantallas.setHeight("20px");
		mainLayout.addComponent(dropPantallas, "bottom:476.0px;left:760.0px;");
		
		// label_2
		label_2 = new Label();
		label_2.setImmediate(false);
		label_2.setWidth("160px");
		label_2.setHeight("38px");
		label_2.setValue("Prestamos Actuales");
		mainLayout.addComponent(label_2, "top:60.0px;left:420.0px;");
		
		return mainLayout;
	}

}
