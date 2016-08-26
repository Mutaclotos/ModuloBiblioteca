package biblioteca.modulovisitas;

import java.sql.ResultSet;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Filterable;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class formularioConsultaNoCompletada extends CustomComponent implements View
{
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private TextField inputBusqueda;

	@AutoGenerated
	private ComboBox inputTipoBusqueda;

	@AutoGenerated
	private Button button_verCompletadas;

	@AutoGenerated
	private Table tablaConsultas;

	@AutoGenerated
	private Button button_volver;

	@AutoGenerated
	private Button button_editar;

	@AutoGenerated
	private Button button_agregar;

	@AutoGenerated
	private Button button_buscar;

	@AutoGenerated
	private Label labelTitulo;
	
	Navigator navigator;
	
	public static final String MAINVIEW = "main";

	public static final long serialVersionUID = 1202;
	
	private String timeStamp;

	private final DBConnector dbc;
	
	private MyUI ui;
	
	public formularioConsultaNoCompletada()
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);
		inputTipoBusqueda.setNullSelectionAllowed(false);
		
		dbc = new DBConnector("localhost","moises","315600");
		inputBusqueda.focus();
		ui = new MyUI();
		
		// Create a navigator to control the views
        //navigator = new Navigator(UI.getCurrent(), mainLayout);

        // Create and register the views
        //navigator.addView("", new formularioConsultaNoCompletada());
        //navigator.addView(formularioEditarConsulta.EDITVIEW, new formularioEditarConsulta());
		
		ClickListener click = new ClickListener(){
			SimpleStringFilter filter = null;
			Filterable filterable = null;
			@Override
			public void buttonClick(ClickEvent event) 
			{
				if(inputTipoBusqueda.getValue().toString().equals("Nombre de usuario"))
				{
					tablaConsultas.setVisibleColumns("Nombre", "Apellidos", "Cédula", "Carné", "Correo", "Institución", "Tipo usuario", "Tema", "Tipo consulta", "Fecha emisión", "Observaciones","Bases de datos");
					
					filterable = (Container.Filterable) tablaConsultas.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Nombre"
					filter = new SimpleStringFilter("Nombre", inputBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				else if(inputTipoBusqueda.getValue().toString().equals("Tema de consulta"))
				{
					tablaConsultas.setVisibleColumns("Tema","Tipo consulta","Observaciones","Bases de datos","Nombre", "Apellidos", "Cédula", "Carné", "Correo", "Institución", "Tipo usuario", "Fecha emisión");
					
					filterable = (Container.Filterable) tablaConsultas.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Tema"
					filter = new SimpleStringFilter("Tema", inputBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				else if(inputTipoBusqueda.getValue().toString().equals("Fecha de emisión"))
				{
					tablaConsultas.setVisibleColumns("Fecha emisión", "Nombre", "Apellidos", "Cédula", "Carné", "Correo", "Institución", "Tipo usuario", "Tema", "Tipo consulta", "Observaciones", "Bases de datos");
					
					filterable = (Container.Filterable) tablaConsultas.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Fecha emisión"
					filter = new SimpleStringFilter("Fecha emisión", inputBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				
			}
		};
		
		this.button_buscar.addClickListener(click);
		
		inputTipoBusqueda.setContainerDataSource(Contenedor.obtenerContenedor3("tipoBusqueda"));
		
		inputTipoBusqueda.select("Nombre de usuario");
		
		
		int i = 0;
		
		tablaConsultas.addContainerProperty("Nombre", String.class, null);
		tablaConsultas.addContainerProperty("Apellidos", String.class, null);
		tablaConsultas.addContainerProperty("Cédula", String.class, null);
		tablaConsultas.addContainerProperty("Carné", String.class, null);
		tablaConsultas.addContainerProperty("Correo", String.class, null);
		tablaConsultas.addContainerProperty("Institución", String.class, null);
		tablaConsultas.addContainerProperty("Tipo usuario", String.class, null);
		tablaConsultas.addContainerProperty("Tema", String.class, null);
		tablaConsultas.addContainerProperty("Tipo consulta", String.class, null);
		tablaConsultas.addContainerProperty("Fecha emisión", String.class, null);
		tablaConsultas.addContainerProperty("Observaciones", String.class, null);
		tablaConsultas.addContainerProperty("Bases de datos", String.class, null);
		
		tablaConsultas.setSelectable(true);
		tablaConsultas.setImmediate(true);
		
		ResultSet rs = dbc.query("SELECT u.nombre, u.apellidos, u.cedula, u.carne, u.email, u.institucion, u.tipo as tipoUsuario, "
				+ "c.tema, c.tipo, c.fechaEmision, c.observaciones, IFNULL((SELECT GROUP_CONCAT(b.nombre) "
				+ "FROM ConsultaBase cb, BasesDatos b "
				+ "WHERE cb.Consulta = c.id "
				+ "AND b.id = cb.basedatos), 'N/A') AS basesDatos "
				+ "FROM Consulta c, Usuario u "
				+ "WHERE c.fechaEntrega IS NULL "
				+ "AND c.usuario = u.cedula "
				+ "GROUP BY c.id");
		try{
			//TODO: datasource de resultSet, tableQuery
			while(rs.next())
			{
				Integer itemId = new Integer(i);
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String cedula = rs.getString("cedula");
				String carne = rs.getString("carne");
				String email = rs.getString("email");
				String institucion = rs.getString("institucion");
				String tipoUsuario = rs.getString("tipoUsuario");
				String tema = rs.getString("tema");
				String tipoConsulta = rs.getString("tipo");
				String fechaEmision = rs.getString("fechaEmision");
				String observaciones = rs.getString("observaciones");
				String basesDatos = rs.getString("basesDatos");
				tablaConsultas.addItem(new Object[]{nombre,apellidos,cedula,carne,email,institucion,tipoUsuario,tema,tipoConsulta,fechaEmision,observaciones,basesDatos}, itemId);
				i++;
			}
		}catch(Exception sqe){
		}
		
		ClickListener click2 = new ClickListener(){
			
			@Override
			public void buttonClick(ClickEvent event) 
			{
				//IndexedContainer ic = new IndexedContainer();
				Object rowId = tablaConsultas.getValue(); 
				if(rowId != null)
				{
					//System.out.println(rowId.toString());

					String nombre = (String)tablaConsultas.getContainerProperty(rowId,"Nombre").getValue();
					String apellidos = (String)tablaConsultas.getContainerProperty(rowId,"Apellidos").getValue();
					String cedula = (String)tablaConsultas.getContainerProperty(rowId,"Cédula").getValue();
					String carne = (String)tablaConsultas.getContainerProperty(rowId,"Carné").getValue();
					String email = (String)tablaConsultas.getContainerProperty(rowId,"Correo").getValue();
					String institucion = (String)tablaConsultas.getContainerProperty(rowId,"Institución").getValue();
					String tipoUsuario = (String)tablaConsultas.getContainerProperty(rowId,"Tipo usuario").getValue();
					String tema = (String)tablaConsultas.getContainerProperty(rowId,"Tema").getValue();
					String tipoConsulta = (String)tablaConsultas.getContainerProperty(rowId,"Tipo consulta").getValue();
					String observaciones = (String)tablaConsultas.getContainerProperty(rowId,"Observaciones").getValue();
					String basesDatos = (String)tablaConsultas.getContainerProperty(rowId,"Bases de datos").getValue();
					
					//formularioEditarConsulta fec = new formularioEditarConsulta();
					//ui.changeLayout(fec);
					//navigator.navigateTo(formularioEditarConsulta.EDITVIEW);
					//setContent(new formularioConsultaNoCompletada());
					UI.getCurrent().setContent(new formularioEditarConsulta(nombre, apellidos, cedula, carne, email, institucion, tipoUsuario, tema, tipoConsulta, observaciones, basesDatos));
					
				}
				else
				{
					System.out.println("Una fila de la tabla debe ser seleccionada para editar la consulta.");
				}
			}
		};
		
		this.button_editar.addClickListener(click2);
	}
	
	@Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Formulario de consultas no completadas");
    }
	
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("440px");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("440px");
		
		// labelTitulo
		labelTitulo = new Label();
		labelTitulo.setImmediate(false);
		labelTitulo.setWidth("-1px");
		labelTitulo.setHeight("-1px");
		labelTitulo.setValue("CONSULTAS PENDIENTES");
		mainLayout.addComponent(labelTitulo, "top:2.0px;left:651.0px;");
		
		// button_buscar
		button_buscar = new Button();
		button_buscar.setCaption("Buscar");
		button_buscar.setImmediate(true);
		button_buscar.setWidth("100px");
		button_buscar.setHeight("40px");
		mainLayout.addComponent(button_buscar, "top:40.0px;left:680.0px;");
		
		// button_agregar
		button_agregar = new Button();
		button_agregar.setCaption("AGREGAR CONSULTA");
		button_agregar.setImmediate(true);
		button_agregar.setWidth("200px");
		button_agregar.setHeight("40px");
		mainLayout.addComponent(button_agregar, "top:395.0px;left:40.0px;");
		
		// button_editar
		button_editar = new Button();
		button_editar.setCaption("EDITAR CONSULTA");
		button_editar.setImmediate(true);
		button_editar.setWidth("180px");
		button_editar.setHeight("40px");
		mainLayout.addComponent(button_editar, "top:395.0px;left:280.0px;");
		
		// button_volver
		button_volver = new Button();
		button_volver.setCaption("VOLVER");
		button_volver.setImmediate(true);
		button_volver.setWidth("100px");
		button_volver.setHeight("40px");
		mainLayout.addComponent(button_volver, "top:395.0px;left:842.0px;");
		
		// tablaConsultas
		tablaConsultas = new Table();
		tablaConsultas.setImmediate(false);
		tablaConsultas.setWidth("90.0%");
		tablaConsultas.setHeight("280px");
		mainLayout.addComponent(tablaConsultas, "top:100.0px;left:40.0px;");
		
		// button_verCompletadas
		button_verCompletadas = new Button();
		button_verCompletadas.setCaption("VER CONSULTAS COMPLETADAS");
		button_verCompletadas.setImmediate(true);
		button_verCompletadas.setWidth("290px");
		button_verCompletadas.setHeight("-1px");
		mainLayout.addComponent(button_verCompletadas, "top:395.0px;left:500.0px;");
		
		// inputTipoBusqueda
		inputTipoBusqueda = new ComboBox();
		inputTipoBusqueda.setCaption("Tipo de búsqueda");
		inputTipoBusqueda.setImmediate(false);
		inputTipoBusqueda.setWidth("240px");
		inputTipoBusqueda.setHeight("-1px");
		mainLayout.addComponent(inputTipoBusqueda, "top:40.0px;left:40.0px;");
		
		// inputBusqueda
		inputBusqueda = new TextField();
		inputBusqueda.setImmediate(false);
		inputBusqueda.setWidth("320px");
		inputBusqueda.setHeight("-1px");
		inputBusqueda.setInputPrompt("Buscar...");
		mainLayout.addComponent(inputBusqueda, "top:40.0px;left:320.0px;");
		
		return mainLayout;
	}
}
