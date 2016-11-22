package biblioteca.modulovisitas;

import java.sql.ResultSet;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Filterable;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;



public class formularioHistorialPrestamos extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Button buttonPrestamosActuales;
	@AutoGenerated
	private Button buttonNuevoPrestamo;
	@AutoGenerated
	private TextField textBusqueda;
	@AutoGenerated
	private ComboBox inputTipoBusqueda;
	@AutoGenerated
	private Button textButton;
	@AutoGenerated
	private Table tablaHistorial;
	@AutoGenerated
	private Label label_1;
	@AutoGenerated
	private Label labelBiblioteca;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	private final DBConnector dbc;
	
	private MyUI ui;
	String user;
	
	public formularioHistorialPrestamos(String usuario) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		dbc = new DBConnector(MyUI.address,MyUI.user,MyUI.password);
		ui = new MyUI();
		this.user = usuario;

		// TODO add user code here
		ClickListener click = new ClickListener(){
			SimpleStringFilter filter = null;
			Filterable filterable = null;
			@Override
			public void buttonClick(ClickEvent event) 
			{
				if(inputTipoBusqueda.getValue().toString().equals("Fecha"))
				{
					tablaHistorial.setVisibleColumns("Fecha","Nombre de libro","Tipo","Autor","Asignatura","Año","Editorial");////////////////
					
					filterable = (Container.Filterable) tablaHistorial.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Documento"
					filter = new SimpleStringFilter("Fecha", textBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				else if(inputTipoBusqueda.getValue().toString().equals("Nombre de libro"))
				{
					tablaHistorial.setVisibleColumns("Nombre de libro","Tipo","Fecha","Autor","Asignatura","Año","Editorial");
					
					filterable = (Container.Filterable) tablaHistorial.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "fecha de solicitud"
					filter = new SimpleStringFilter("Nombre de libro", textBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				else if(inputTipoBusqueda.getValue().toString().equals("Autor"))
				{
					tablaHistorial.setVisibleColumns("Autor","Nombre de libro","Tipo","Fecha","Asignatura","Año","Editorial");
					
					filterable = (Container.Filterable) tablaHistorial.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Fecha emisión"
					filter = new SimpleStringFilter("Autor", textBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				else if(inputTipoBusqueda.getValue().toString().equals("Asignatura"))
				{
					tablaHistorial.setVisibleColumns("Asignatura","Nombre de libro","Tipo","Fecha","Autor","Año","Editorial");
					
					filterable = (Container.Filterable) tablaHistorial.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Fecha emisión"
					filter = new SimpleStringFilter("Asignatura", textBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				else if(inputTipoBusqueda.getValue().toString().equals("Año"))
				{
					tablaHistorial.setVisibleColumns("Año","Nombre de libro","Tipo","Fecha","Autor","Asignatura","Editorial");
					
					filterable = (Container.Filterable) tablaHistorial.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Fecha emisión"
					filter = new SimpleStringFilter("Año", textBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				else if(inputTipoBusqueda.getValue().toString().equals("Editorial"))
				{
					tablaHistorial.setVisibleColumns("Editorial","Nombre de libro","Tipo","Fecha","Autor","Asignatura","Año");
					
					filterable = (Container.Filterable) tablaHistorial.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Fecha emisión"
					filter = new SimpleStringFilter("Editorial", textBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				else if(inputTipoBusqueda.getValue().toString().equals("Tipo"))
				{
					tablaHistorial.setVisibleColumns("Tipo","Nombre de libro","Editorial","Fecha","Autor","Asignatura","Año");
					
					filterable = (Container.Filterable) tablaHistorial.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Fecha emisión"
					filter = new SimpleStringFilter("Tipo", textBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
			}
		};
		
		
		
		this.textButton.addClickListener(click);
		
		inputTipoBusqueda.setContainerDataSource(Contenedor.obtenerContenedorTipoBusquedaHistorial("tipoBusquedaHistorial"));
		
		inputTipoBusqueda.select("Fecha");
		
		
		
		
		
		
		
		//mostrar del documento: nombre fecha de retiro, signatura, autor, tipo, anio, editorial
		tablaHistorial.addContainerProperty("Fecha", String.class, null);
		tablaHistorial.addContainerProperty("Nombre de libro", String.class, null);
		tablaHistorial.addContainerProperty("Autor", String.class, null);
		tablaHistorial.addContainerProperty("Asignatura", String.class, null);
		tablaHistorial.addContainerProperty("Tipo", String.class, null);
		tablaHistorial.addContainerProperty("Año", String.class, null);
		tablaHistorial.addContainerProperty("Editorial", String.class, null);
		
		
		tablaHistorial.setSelectable(true);
		tablaHistorial.setImmediate(true);
		tablaHistorial.setColumnCollapsingAllowed(true);
		int i=0;
		
		//añadir condicion de la cedula del usuario actual"
		ResultSet rs = dbc.query("select p.fechaEntrega, d.titulo , a.nombre , d.signatura , d.tipoDocumento, d.anio, d.editorial from Documento d, prestamo p, usuario u,documentoautor da,autor a	where p.usuario=u.cedula and u.cedula= "+user+" and p.documento= d.signatura and da.documento= d.signatura and da.autor = a.id	group by p.Documento;" );
		
	
		
		
		
		try{	
			//TODO: datasource de resultSet, tableQuery	
				while(rs.next())
				{
					Integer itemId = new Integer(i);
					
					
					String fechaDevolucion = rs.getString("fechaEntrega");
					String nombreA = rs.getString("nombre");
					String titulo = rs.getString("titulo"); 
					String signatura = rs.getString("signatura");
					String tipoDocumento = rs.getString("tipoDocumento"); 
					String anio = rs.getString("anio");
					String editorial = rs.getString("editorial"); 

					tablaHistorial.addItem(new Object[]{fechaDevolucion,titulo,nombreA,signatura,tipoDocumento,anio,editorial}, itemId);
					
					i++;
					System.out.println("jajajaja"+editorial+"jejeje");
					System.out.println(fechaDevolucion);
				}
						
			
			}catch(Exception sqe){
			
			}
			

			tablaHistorial.setPageLength(tablaHistorial.size());		
		
		
			buttonPrestamosActuales.addClickListener(new ClickListener()
			{
				private static final long serialVersionUID = 47254532L;
				@Override
				public void buttonClick(ClickEvent event)
				{
					MyUI.tabsheet.replaceComponent(MyUI.tabsheet.getSelectedTab(), new prestamosActuales(user));
				}
		 	});
		
			buttonNuevoPrestamo.addClickListener(new ClickListener()
			{
				private static final long serialVersionUID = 47254532L;
				@Override
				public void buttonClick(ClickEvent event)
				{
					MyUI.tabsheet.replaceComponent(MyUI.tabsheet.getSelectedTab(), new formularioNuevoPrestamo(user));
				}
		 	});
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100.0%");
		mainLayout.setHeight("460px");
		
		// top-level component properties
		setWidth("848px");
		setHeight("460px");
		
		// labelBiblioteca
		labelBiblioteca = new Label();
		labelBiblioteca.setImmediate(false);
		labelBiblioteca.setWidth("-1px");
		labelBiblioteca.setHeight("-1px");
		labelBiblioteca.setValue("Biblioteca Francisco Echeverria García");
		mainLayout.addComponent(labelBiblioteca, "top:20.0px;left:53.0px;");
		
		// label_1
		label_1 = new Label();
		label_1.setImmediate(false);
		label_1.setWidth("90.0%");
		label_1.setHeight("-1px");
		label_1.setValue("Historial de Préstamos de Libros");
		mainLayout.addComponent(label_1, "top:60.0px;left:459.0px;");
		
		// tablaHistorial
		tablaHistorial = new Table();
		tablaHistorial.setImmediate(false);
		tablaHistorial.setWidth("100.0%");
		tablaHistorial.setHeight("225px");
		mainLayout.addComponent(tablaHistorial, "top:140.0px;right:79.0px;left:240.0px;");
		
		// textButton
		textButton = new Button();
		textButton.setCaption("Buscar");
		textButton.setImmediate(true);
		textButton.setWidth("-1px");
		textButton.setHeight("-1px");
		mainLayout.addComponent(textButton, "top:94.0px;left:760.0px;");
		
		// inputTipoBusqueda
		inputTipoBusqueda = new ComboBox();
		inputTipoBusqueda.setImmediate(false);
		inputTipoBusqueda.setWidth("-1px");
		inputTipoBusqueda.setHeight("-1px");
		mainLayout.addComponent(inputTipoBusqueda, "top:96.0px;left:120.0px;");
		
		// textBusqueda
		textBusqueda = new TextField();
		textBusqueda.setImmediate(false);
		textBusqueda.setWidth("-1px");
		textBusqueda.setHeight("-1px");
		mainLayout.addComponent(textBusqueda, "top:100.0px;left:480.0px;");
		
		// buttonNuevoPrestamo
		buttonNuevoPrestamo = new Button();
		buttonNuevoPrestamo.setCaption("NUEVO PRESTAMO");
		buttonNuevoPrestamo.setImmediate(true);
		buttonNuevoPrestamo.setWidth("156px");
		buttonNuevoPrestamo.setHeight("-1px");
		mainLayout.addComponent(buttonNuevoPrestamo, "top:399.0px;left:240.0px;");
		
		// buttonPrestamosActuales
		buttonPrestamosActuales = new Button();
		buttonPrestamosActuales.setCaption("PRESTAMOS ACTUALES");
		buttonPrestamosActuales.setImmediate(true);
		buttonPrestamosActuales.setWidth("196px");
		buttonPrestamosActuales.setHeight("-1px");
		mainLayout.addComponent(buttonPrestamosActuales, "top:399.0px;left:540.0px;");
		
		return mainLayout;
	}

}
