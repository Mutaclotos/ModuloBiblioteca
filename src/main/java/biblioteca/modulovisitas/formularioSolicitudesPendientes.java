package biblioteca.modulovisitas;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Filterable;
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

public class formularioSolicitudesPendientes extends CustomComponent implements View
{
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private Label labelAdvertencia;

	@AutoGenerated
	private TextField inputBusqueda;

	@AutoGenerated
	private ComboBox inputTipoBusqueda;

	@AutoGenerated
	private Table tablaSolicitudes;

	@AutoGenerated
	private Button button_editar;

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
	
	public formularioSolicitudesPendientes()
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);
		inputTipoBusqueda.setNullSelectionAllowed(false);
		
		dbc = new DBConnector("localhost","Mutaclotos","we105769");
		inputBusqueda.focus();
		ui = new MyUI();
		
		labelAdvertencia.setVisible(false);
		
		ClickListener click = new ClickListener(){
			SimpleStringFilter filter = null;
			Filterable filterable = null;
			@Override
			public void buttonClick(ClickEvent event) 
			{
				if(inputTipoBusqueda.getValue().toString().equals("Signatura"))
				{
					tablaSolicitudes.setVisibleColumns("Signatura", "Título", "Autor", "Fecha de solicitud", "Fecha de caducidad", "Cédula de solicitante", "Nombre", "Apellidos", "Teléfono", "Estado de solicitud");
					
					filterable = (Container.Filterable) tablaSolicitudes.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Nombre"
					filter = new SimpleStringFilter("Signatura", inputBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				else if(inputTipoBusqueda.getValue().toString().equals("Título"))
				{
					tablaSolicitudes.setVisibleColumns("Título", "Signatura",  "Autor", "Fecha de solicitud", "Fecha de caducidad", "Cédula de solicitante", "Nombre", "Apellidos", "Teléfono", "Estado de solicitud");
					
					filterable = (Container.Filterable) tablaSolicitudes.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Tema"
					filter = new SimpleStringFilter("Título", inputBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				else if(inputTipoBusqueda.getValue().toString().equals("Cédula de solicitante"))
				{
					tablaSolicitudes.setVisibleColumns("Cédula de solicitante", "Nombre", "Apellidos", "Teléfono", "Fecha de solicitud", "Fecha de caducidad", "Signatura", "Título", "Autor", "Estado de solicitud" );
					
					filterable = (Container.Filterable) tablaSolicitudes.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Fecha emisión"
					filter = new SimpleStringFilter("Cédula de solicitante", inputBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				else if(inputTipoBusqueda.getValue().toString().equals("Estado de solicitud"))
				{
					tablaSolicitudes.setVisibleColumns("Estado de solicitud", "Fecha de solicitud", "Fecha de caducidad", "Signatura", "Título", "Autor", "Cédula de solicitante", "Nombre", "Apellidos", "Teléfono");
					
					filterable = (Container.Filterable) tablaSolicitudes.getContainerDataSource();
					
					//Remover el filtro anterior
					if(filter != null)
					{
						filterable.removeContainerFilter(filter);
					}
					//Nuevo filtro es creado para la columna "Fecha emisión"
					filter = new SimpleStringFilter("Estado de solicitud", inputBusqueda.getValue(),true,false);
					filterable.addContainerFilter(filter);
				}
				
			}
		};
		
		this.button_buscar.addClickListener(click);
		
		inputTipoBusqueda.setContainerDataSource(Contenedor.obtenerContenedorTipoBusquedaSolicitud("tipoBusquedaSolicitud"));
		
		inputTipoBusqueda.select("Signatura");
		
		
		int i = 0;
		
		tablaSolicitudes.addContainerProperty("ID", String.class, null);
		tablaSolicitudes.addContainerProperty("Signatura", String.class, null);
		tablaSolicitudes.addContainerProperty("Título", String.class, null);
		tablaSolicitudes.addContainerProperty("Autor", String.class, null);
		tablaSolicitudes.addContainerProperty("Volumen", String.class, null);
		tablaSolicitudes.addContainerProperty("Número", String.class, null);
		tablaSolicitudes.addContainerProperty("Año", String.class, null);
		tablaSolicitudes.addContainerProperty("Institución", String.class, null);
		tablaSolicitudes.addContainerProperty("Cédula de solicitante", String.class, null);
		tablaSolicitudes.addContainerProperty("Nombre", String.class, null);
		tablaSolicitudes.addContainerProperty("Apellidos", String.class, null);
		tablaSolicitudes.addContainerProperty("Teléfono", String.class, null);
		tablaSolicitudes.addContainerProperty("Fecha de solicitud", String.class, null);
		tablaSolicitudes.addContainerProperty("Fecha de caducidad", String.class, null);
		tablaSolicitudes.addContainerProperty("Estado de solicitud", String.class, null);
		
		tablaSolicitudes.setSelectable(true);
		tablaSolicitudes.setImmediate(true);
		tablaSolicitudes.setColumnCollapsingAllowed(true);
		
		
		ResultSet rs = dbc.query("SELECT p.id, p.documento as signatura, d.titulo, "
																				+ "(SELECT GROUP_CONCAT(a.nombre) "
																				+ "FROM autor a, documentoautor da "
																				+ "WHERE d.signatura = da.documento "
																				+ "AND da.autor = a.id ) AS autor, "
																				+ "d.volumen, d.numero, d.anio, d.institucion, "
																				+ "p.usuario, u.nombre, u.apellidos, t.numero as telefono, p.fechaSolicitud, IFNULL(p.fechaCaducidad, 'FALTANTE') as fechaCaducidad, IFNULL(p.estado, 'PENDIENTE') as estado "
							   + "FROM prestamo p, usuario u, documento d, telefono t "
							   + "WHERE p.documento = d.signatura "
							   + "AND (p.estado IS NULL OR p.estado = 'PRESTADO' OR p.estado = 'MOROSO') "
							   + "AND p.usuario = u.cedula "
							   + "AND t.cedula = p.usuario");
		try{
			
			while(rs.next())
			{
				Integer itemId = new Integer(i);
				String solicitudID = rs.getString("id"); 
				String signatura = rs.getString("signatura");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String volumen = rs.getString("volumen");
				String numero = rs.getString("numero");
				String anio = rs.getString("anio");
				String institucion = rs.getString("institucion");
				String usuario = rs.getString("usuario");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String telefono = rs.getString("telefono");
				String fechaSolicitud = rs.getString("fechaSolicitud");
				String fechaCaducidad = rs.getString("fechaCaducidad");
				String estado = rs.getString("estado");
				
				tablaSolicitudes.addItem(new Object[]{solicitudID,signatura,titulo,autor,volumen,numero,anio,institucion,usuario,nombre,apellidos,telefono,fechaSolicitud,fechaCaducidad,estado}, itemId);
				i++;
			}
		}catch(Exception sqe){
		}
		
		tablaSolicitudes.setVisibleColumns("Signatura", "Título", "Autor", "Cédula de solicitante", "Nombre", "Apellidos", "Teléfono", "Fecha de solicitud", "Fecha de caducidad", "Estado de solicitud");
		
		
		ClickListener clickEditarSolicitud = new ClickListener(){
			
			@Override
			public void buttonClick(ClickEvent event) 
			{
				//IndexedContainer ic = new IndexedContainer();
				
				
				Object rowId = tablaSolicitudes.getValue(); 
				buscarMorosos();
				
				if(rowId != null)
				{
					labelAdvertencia.setVisible(false);
					//System.out.println(rowId.toString());
					String idSolicitud = (String)tablaSolicitudes.getContainerProperty(rowId,"ID").getValue();
					String signatura = (String)tablaSolicitudes.getContainerProperty(rowId,"Signatura").getValue();
					String titulo = (String)tablaSolicitudes.getContainerProperty(rowId,"Título").getValue();
					String autor = (String)tablaSolicitudes.getContainerProperty(rowId,"Autor").getValue();
					String volumen = (String)tablaSolicitudes.getContainerProperty(rowId,"Volumen").getValue();
					String numero = (String)tablaSolicitudes.getContainerProperty(rowId,"Número").getValue();
					String anio = (String)tablaSolicitudes.getContainerProperty(rowId,"Año").getValue();
					String institucion = (String)tablaSolicitudes.getContainerProperty(rowId,"Institución").getValue();
					String usuario = (String)tablaSolicitudes.getContainerProperty(rowId,"Cédula de solicitante").getValue();
					String nombre = (String)tablaSolicitudes.getContainerProperty(rowId,"Nombre").getValue();
					String apellidos = (String)tablaSolicitudes.getContainerProperty(rowId,"Apellidos").getValue();
					String telefono = (String)tablaSolicitudes.getContainerProperty(rowId,"Teléfono").getValue();
					String fechaSolicitud = (String)tablaSolicitudes.getContainerProperty(rowId,"Fecha de solicitud").getValue();
					String fechaCaducidad = (String)tablaSolicitudes.getContainerProperty(rowId,"Fecha de caducidad").getValue();
					String aprobado = (String)tablaSolicitudes.getContainerProperty(rowId,"Estado de solicitud").getValue();
					
					
					UI.getCurrent().setContent(new formularioEditarSolicitud(idSolicitud, signatura, titulo, autor, volumen, numero, anio, institucion, usuario, nombre, apellidos, telefono, fechaSolicitud, fechaCaducidad, aprobado));
					
				}
				else
				{
					labelAdvertencia.setVisible(true);
					System.out.println("Una fila de la tabla debe ser seleccionada para editar la consulta.");
				}
			}
		};
		
		this.button_editar.addClickListener(clickEditarSolicitud);
	}
	
	public void buscarMorosos()
	{
		
		Iterator iter = tablaSolicitudes.getItemIds().iterator();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		while(iter.hasNext())
		{
			tablaSolicitudes.setValue(iter.next());
			
			Object rowId = tablaSolicitudes.getValue(); 
			String idSolicitud = (String)tablaSolicitudes.getContainerProperty(rowId,"ID").getValue();
			String fechaCaducidad = (String)tablaSolicitudes.getContainerProperty(rowId,"Fecha de caducidad").getValue();
			String estado = (String)tablaSolicitudes.getContainerProperty(rowId,"Estado de solicitud").getValue();
			String fechaActual = (new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime()));
			Date fechaCaduca;
			Date fechaAct;
			String nuevoEstado = "MOROSO";
			
			try
			{
				if(!fechaCaducidad.equals("FALTANTE") && !estado.equals("MOROSO"))
				{
					fechaCaduca = formatter.parse(fechaCaducidad);
					fechaAct = formatter.parse(fechaActual);
					
					if(fechaCaduca.before(fechaAct))
					{
						System.out.println("Moroso detectado.");
						dbc.update("Prestamo", "estado='"+nuevoEstado+"'","id='"+idSolicitud+"'");
						
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
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
		labelTitulo.setValue("SOLICITUDES PENDIENTES");
		mainLayout.addComponent(labelTitulo, "top:2.0px;left:651.0px;");
		
		// button_buscar
		button_buscar = new Button();
		button_buscar.setCaption("Buscar");
		button_buscar.setImmediate(true);
		button_buscar.setWidth("100px");
		button_buscar.setHeight("40px");
		mainLayout.addComponent(button_buscar, "top:40.0px;left:680.0px;");
		
		// button_editar
		button_editar = new Button();
		button_editar.setCaption("EDITAR SOLICITUD");
		button_editar.setImmediate(true);
		button_editar.setWidth("180px");
		button_editar.setHeight("40px");
		mainLayout.addComponent(button_editar, "top:395.0px;left:280.0px;");
		
		// tablaSolicitudes
		tablaSolicitudes = new Table();
		tablaSolicitudes.setImmediate(true);
		tablaSolicitudes.setWidth("90.0%");
		tablaSolicitudes.setHeight("280px");
		mainLayout.addComponent(tablaSolicitudes, "top:100.0px;left:40.0px;");
		
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
		
		// labelAdvertencia
		labelAdvertencia = new Label();
		labelAdvertencia.setImmediate(false);
		labelAdvertencia.setWidth("-1px");
		labelAdvertencia.setHeight("-1px");
		labelAdvertencia.setValue("Una fila debe ser seleccionada para editar");
		mainLayout.addComponent(labelAdvertencia, "top:400.0px;left:530.0px;");
		
		return mainLayout;
	}
}
