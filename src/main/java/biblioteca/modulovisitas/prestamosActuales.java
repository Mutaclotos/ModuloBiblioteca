package biblioteca.modulovisitas;

import java.sql.ResultSet;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Filterable;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

public class prestamosActuales extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Button buttonHistorial;
	@AutoGenerated
	private Button buttonNuevoPrestamo;
	@AutoGenerated
	private ComboBox inputTipoBusqueda;
	@AutoGenerated
	private Button buttonBuscar;
	@AutoGenerated
	private TextField textBusqueda;
	@AutoGenerated
	private Label labelTitulo;
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
		dbc = new DBConnector(MyUI.address,MyUI.user,MyUI.password);
		ui = new MyUI();
		

		ClickListener click = new ClickListener(){
			SimpleStringFilter filter = null;
			Filterable filterable = null;
			@Override
			public void buttonClick(ClickEvent event) 
			{
				if(inputTipoBusqueda.getValue().toString().equals("Documento"))
				{
					tablaDePrestamos.setVisibleColumns("Documento","Fecha de solicitud","Fecha de caducidad","Estado de solicitud");////////////////
					
					filterable = (Container.Filterable) tablaDePrestamos.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Documento"
					filter = new SimpleStringFilter("Documento", textBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				else if(inputTipoBusqueda.getValue().toString().equals("Fecha de solicitud"))
				{
					tablaDePrestamos.setVisibleColumns("Fecha de solicitud","Documento","Fecha de caducidad","Estado de solicitud");
					
					filterable = (Container.Filterable) tablaDePrestamos.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "fecha de solicitud"
					filter = new SimpleStringFilter("Fecha de solicitud", textBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				else if(inputTipoBusqueda.getValue().toString().equals("Fecha de caducidad"))
				{
					tablaDePrestamos.setVisibleColumns("Fecha de caducidad","Documento","Fecha de solicitud","Estado de solicitud");
					
					filterable = (Container.Filterable) tablaDePrestamos.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Fecha emisión"
					filter = new SimpleStringFilter("Fecha de caducidad", textBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				else if(inputTipoBusqueda.getValue().toString().equals("Estado de solicitud"))
				{
					tablaDePrestamos.setVisibleColumns("Estado de solicitud","Documento","Fecha de solicitud","Fecha de caducidad");
					
					filterable = (Container.Filterable) tablaDePrestamos.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Fecha emisión"
					filter = new SimpleStringFilter("Estado de solicitud", textBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				
			}
		};
		
		
		
		this.buttonBuscar.addClickListener(click);
		
		inputTipoBusqueda.setContainerDataSource(Contenedor.obtenerContenedorTipoBusquedaPrestamoActual("tipoBusquedaPrestamoActual"));
		
		inputTipoBusqueda.select("Documento");
		
		tablaDePrestamos.addContainerProperty("Documento", String.class, null);
		tablaDePrestamos.addContainerProperty("Fecha de solicitud", String.class, null);
		tablaDePrestamos.addContainerProperty("Fecha de caducidad", String.class, null);
		tablaDePrestamos.addContainerProperty("Estado de solicitud", String.class, null);
		int i=0;///////////////////////////////////////////////////////////////////////////////////////////////////


		tablaDePrestamos.setSelectable(true);
		tablaDePrestamos.setImmediate(true);
		tablaDePrestamos.setColumnCollapsingAllowed(true);
		
		
		ResultSet rs = dbc.query("SELECT p.Documento, p.fechaSolicitud, p.fechaCaducidad, p.estado from Prestamo p , Usuario u where p.usuario=u.cedula and u.cedula ="+ MyUI.user+" group by  p.Documento ;" );//añadir condicion de la cedula del usuario actual"
		
		try{	
		//TODO: datasource de resultSet, tableQuery	
			while(rs.next())
			{
				Integer itemId = new Integer(i);
				
				String documento = rs.getString("Documento"); 
				String fechaSolicitud = rs.getString("fechaSolicitud");
				String fechaCaducidad = rs.getString("fechaCaducidad");
				String estado = rs.getString("estado");

				tablaDePrestamos.addItem(new Object[]{documento,fechaSolicitud,fechaCaducidad,estado}, itemId);
				
				i++;
				//System.out.println(nombreDocu);
				//System.out.println(fechaRetiro);
			}
					
		
		}catch(Exception sqe){
			//System.out.println("faillllll");
		}
		

		tablaDePrestamos.setPageLength(tablaDePrestamos.size());
		tablaDePrestamos.setSelectable(true);
		tablaDePrestamos.setImmediate(true);
		tablaDePrestamos.setColumnCollapsingAllowed(true);
		
		
		// TODO add user code here
		
		buttonNuevoPrestamo.addClickListener(new ClickListener()
		{
			private static final long serialVersionUID = 47254532L;
			@Override
			public void buttonClick(ClickEvent event)
			{
				MyUI.tabsheet.replaceComponent(MyUI.tabsheet.getSelectedTab(), new formularioNuevoPrestamo());
			}
	 	});
		
		buttonHistorial.addClickListener(new ClickListener()
		{
			private static final long serialVersionUID = 47254532L;
			@Override
			public void buttonClick(ClickEvent event)
			{
				MyUI.tabsheet.replaceComponent(MyUI.tabsheet.getSelectedTab(), new formularioHistorialPrestamos());
			}
	 	});
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("505px");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("505px");
		
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
		tablaDePrestamos.setWidth("100.0%");
		tablaDePrestamos.setHeight("285px");
		mainLayout.addComponent(tablaDePrestamos, "top:140.0px;right:62.0px;left:260.0px;");
		
		// labelTitulo
		labelTitulo = new Label();
		labelTitulo.setImmediate(false);
		labelTitulo.setWidth("100.0%");
		labelTitulo.setHeight("18px");
		labelTitulo.setValue("PRESTAMOS ACTUALES");
		mainLayout.addComponent(labelTitulo, "top:62.0px;right:209.0px;left:440.0px;");
		
		// textBusqueda
		textBusqueda = new TextField();
		textBusqueda.setImmediate(false);
		textBusqueda.setWidth("181px");
		textBusqueda.setHeight("-1px");
		mainLayout.addComponent(textBusqueda, "top:98.0px;left:335.0px;");
		
		// buttonBuscar
		buttonBuscar = new Button();
		buttonBuscar.setCaption("Buscar");
		buttonBuscar.setImmediate(true);
		buttonBuscar.setWidth("-1px");
		buttonBuscar.setHeight("-1px");
		mainLayout.addComponent(buttonBuscar, "top:98.0px;left:569.0px;");
		
		// inputTipoBusqueda
		inputTipoBusqueda = new ComboBox();
		inputTipoBusqueda.setCaption("Tipo de búsqueda");
		inputTipoBusqueda.setImmediate(false);
		inputTipoBusqueda.setWidth("240px");
		inputTipoBusqueda.setHeight("-1px");
		mainLayout.addComponent(inputTipoBusqueda, "top:98.0px;left:40.0px;");
		
		// buttonNuevoPrestamo
		buttonNuevoPrestamo = new Button();
		buttonNuevoPrestamo.setCaption("NUEVO PRESTAMO");
		buttonNuevoPrestamo.setImmediate(true);
		buttonNuevoPrestamo.setWidth("160px");
		buttonNuevoPrestamo.setHeight("-1px");
		mainLayout.addComponent(buttonNuevoPrestamo, "top:460.0px;left:236.0px;");
		
		// buttonHistorial
		buttonHistorial = new Button();
		buttonHistorial.setCaption("HISTORIAL DE PRESTAMOS");
		buttonHistorial.setImmediate(true);
		buttonHistorial.setWidth("240px");
		buttonHistorial.setHeight("-1px");
		mainLayout.addComponent(buttonHistorial, "top:460.0px;left:496.0px;");
		
		return mainLayout;
	}

}
