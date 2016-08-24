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

public class formularioConsultaCompletada extends CustomComponent
{
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private TextField inputBusqueda;

	@AutoGenerated
	private ComboBox inputTipoBusqueda;

	@AutoGenerated
	private Button button_verPendientes;

	@AutoGenerated
	private Table tablaConsultas;

	@AutoGenerated
	private Button button_volver;

	@AutoGenerated
	private Button button_agregar;

	@AutoGenerated
	private Button button_buscar;

	@AutoGenerated
	private Label labelTitulo;

	public static final long serialVersionUID = 1202;
	
	private String timeStamp;

	private final DBConnector dbc;
	
	public formularioConsultaCompletada()
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);
		inputTipoBusqueda.setNullSelectionAllowed(false);
		
		dbc = new DBConnector("localhost","Mutaclotos","we105769");
		
		inputBusqueda.focus();
		
		

		ClickListener click = new ClickListener(){
			SimpleStringFilter filter = null;
			Filterable filterable = null;
			@Override
			public void buttonClick(ClickEvent event) 
			{
				if(inputTipoBusqueda.getValue().toString().equals("Nombre de usuario"))
				{
					tablaConsultas.setVisibleColumns("Nombre", "Apellidos", "Cédula", "Carné", "Correo", "Institución", "Tipo usuario", "Tema", "Tipo consulta", "Fecha emisión", "Fecha entrega", "Observaciones","Bases de datos");
					
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
					tablaConsultas.setVisibleColumns("Tema","Tipo consulta","Observaciones","Bases de datos","Nombre", "Apellidos", "Cédula", "Carné", "Correo", "Institución", "Tipo usuario", "Fecha emisión", "Fecha entrega");
					
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
					tablaConsultas.setVisibleColumns("Fecha emisión", "Fecha entrega", "Nombre", "Apellidos", "Cédula", "Carné", "Correo", "Institución", "Tipo usuario", "Tema", "Tipo consulta", "Observaciones", "Bases de datos");
					
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
				else if(inputTipoBusqueda.getValue().toString().equals("Fecha de entrega"))
				{
					tablaConsultas.setVisibleColumns("Fecha entrega", "Fecha emisión", "Nombre", "Apellidos", "Cédula", "Carné", "Correo", "Institución", "Tipo usuario", "Tema", "Tipo consulta", "Observaciones", "Bases de datos");
					
					filterable = (Container.Filterable) tablaConsultas.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Fecha emisión"
					filter = new SimpleStringFilter("Fecha entrega", inputBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				
			}
		};
		
		this.button_buscar.addClickListener(click);
		
		inputTipoBusqueda.setContainerDataSource(Contenedor.obtenerContenedorTipoBusqueda2("tipoBusqueda"));
		
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
		tablaConsultas.addContainerProperty("Fecha entrega", String.class, null);
		tablaConsultas.addContainerProperty("Observaciones", String.class, null);
		tablaConsultas.addContainerProperty("Bases de datos", String.class, null);
		
		tablaConsultas.setSelectable(true);
		tablaConsultas.setImmediate(true);
		
		ResultSet rs = dbc.query("SELECT u.nombre, u.apellidos, u.cedula, u.carne, u.email, u.institucion, u.tipo as tipoUsuario, "
				+ "c.tema, c.tipo, c.fechaEmision, c.fechaEntrega, c.observaciones, IFNULL((SELECT GROUP_CONCAT(b.nombre) "
				+ "FROM consultabase cb, basesdatos b "
				+ "WHERE cb.Consulta = c.id "
				+ "AND b.id = cb.basedatos), 'N/A') AS basesDatos "
				+ "FROM consulta c, usuario u "
				+ "WHERE c.fechaEntrega IS NOT NULL "
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
				String fechaEntrega = rs.getString("fechaEntrega");
				String observaciones = rs.getString("observaciones");
				String basesDatos = rs.getString("basesDatos");
				tablaConsultas.addItem(new Object[]{nombre,apellidos,cedula,carne,email,institucion,tipoUsuario,tema,tipoConsulta,fechaEmision,fechaEntrega,observaciones,basesDatos}, itemId);
				i++;
			}
		}catch(Exception sqe){
		}
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
		labelTitulo.setValue("CONSULTAS COMPLETADAS");
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
		
		// button_verPendientes
		button_verPendientes = new Button();
		button_verPendientes.setCaption("VER CONSULTAS PENDIENTES");
		button_verPendientes.setImmediate(true);
		button_verPendientes.setWidth("290px");
		button_verPendientes.setHeight("-1px");
		mainLayout.addComponent(button_verPendientes, "top:395.0px;left:500.0px;");
		
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
