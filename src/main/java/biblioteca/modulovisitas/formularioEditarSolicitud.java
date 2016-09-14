package biblioteca.modulovisitas;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import biblioteca.modulovisitas.validadores.IsApellido;
import biblioteca.modulovisitas.validadores.IsFecha;
import biblioteca.modulovisitas.validadores.IsFechaCaducidad;

public class formularioEditarSolicitud extends CustomComponent implements View
{

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private Label labelErrorCaducidad;

	@AutoGenerated
	private Label labelError;

	@AutoGenerated
	private Label labelAdvertencia2;

	@AutoGenerated
	private CheckBox checkBoxDEVUELTO;

	@AutoGenerated
	private Button botonCancelar;

	@AutoGenerated
	private TextField inputInstitucion;

	@AutoGenerated
	private Label labelInstitucion;

	@AutoGenerated
	private Label labelAnio;

	@AutoGenerated
	private TextField inputAnio;

	@AutoGenerated
	private Label labelTipoPrestamo;

	@AutoGenerated
	private ComboBox inputTipoPrestamo;

	@AutoGenerated
	private Label labelFechaCaducidad;

	@AutoGenerated
	private Label labelSolicitudt;

	@AutoGenerated
	private Label labelSolicitante;

	@AutoGenerated
	private TextField inputFechaCaducidad;

	@AutoGenerated
	private TextField inputFechaSolicitud;

	@AutoGenerated
	private Label labelFechaSolicitud;

	@AutoGenerated
	private CheckBox checkBoxRECHAZAR;

	@AutoGenerated
	private CheckBox checkBoxACEPTAR;

	@AutoGenerated
	private TextField inputSignatura;

	@AutoGenerated
	private Label labelSolicitud;

	@AutoGenerated
	private Label labelSignatura;

	@AutoGenerated
	private Label labelAdvertencia;

	@AutoGenerated
	private TextField inputTelefono;

	@AutoGenerated
	private Label labelTelefono;

	@AutoGenerated
	private Button botonGuardar;

	@AutoGenerated
	private Label labelFechaHora;

	@AutoGenerated
	private Label labelVolumen;

	@AutoGenerated
	private TextField inputVolumen;

	@AutoGenerated
	private TextField inputTitulo;

	@AutoGenerated
	private Label labelTituloDoc;

	@AutoGenerated
	private TextField inputAutor;

	@AutoGenerated
	private TextField inputNumero;

	@AutoGenerated
	private Label labelNumero;

	@AutoGenerated
	private TextField inputCedula;

	@AutoGenerated
	private Label labelCedula;

	@AutoGenerated
	private TextField inputApellidos;

	@AutoGenerated
	private Label labelAutor;

	@AutoGenerated
	private Label labelApellidos;

	@AutoGenerated
	private Label labelNombre;

	@AutoGenerated
	private TextField inputNombre;

	@AutoGenerated
	private Label labelTitulo;

	public static final long serialVersionUID = 1202;
	
	private String timeStamp;

	private final DBConnector dbc;
	
	private String IDSolicitud;
	
	public static final String EDITVIEW = "edit";
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public formularioEditarSolicitud(String idSolicitud, String signatura, String titulo, String autor, String volumen, String numero, String anio, String institucion,
	String cedula, String nombre, String apellidos, String telefono, String fechaSolicitud, String fechaCaducidad, String estado) 
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		dbc = new DBConnector("localhost","Mutaclotos","we105769");

		timeStamp = new SimpleDateFormat("dd/MM/YYYY HH:mm").format(Calendar.getInstance().getTime());
		labelFechaHora.setValue(timeStamp);
		
		IDSolicitud = idSolicitud;
		
		//FocusListener para verificar validez de campo modificado
		FocusListener fl = new FocusListener(){
			@Override
			public void focus(FocusEvent event) {
				inputFechaCaducidad.isValid();
				inputTipoPrestamo.isValid();
			}
		};
		
		
		inputFechaCaducidad.addFocusListener(fl);
		inputTipoPrestamo.addFocusListener(fl);
		botonGuardar.addFocusListener(fl);
		
		inputTipoPrestamo.setContainerDataSource(Contenedor.obtenerContenedorTipoPrestamo("tipoPrestamo"));
		inputTipoPrestamo.select("En Sala");
		
		checkBoxACEPTAR.setImmediate(true);
		checkBoxRECHAZAR.setImmediate(true);
		
		//Se insertan los valores de la fila de la tabla de solicitudes en su campo respectivo
		inputNombre.setValue(nombre);
		inputFechaSolicitud.setValue(fechaSolicitud);
		inputApellidos.setValue(apellidos);
		inputCedula.setValue(cedula);
		inputTelefono.setValue(telefono);
		
		inputSignatura.setValue(signatura);
		inputTitulo.setValue(titulo);
		inputAutor.setValue(autor);
		inputVolumen.setValue(volumen);
		inputNumero.setValue(numero);
		inputAnio.setValue(anio);
		inputInstitucion.setValue(institucion);
		
		inputTipoPrestamo.setEnabled(false);
		labelError.setVisible(false);
		labelErrorCaducidad.setVisible(false);
		
		inputNombre.setReadOnly(true);
		inputApellidos.setReadOnly(true);
		inputCedula.setReadOnly(true);
		inputTelefono.setReadOnly(true);
		inputFechaSolicitud.setReadOnly(true);
		labelAdvertencia.setVisible(false);
		inputSignatura.setReadOnly(true);
		inputTitulo.setReadOnly(true);
		inputAutor.setReadOnly(true);
		inputVolumen.setReadOnly(true);
		inputNumero.setReadOnly(true);
		inputAnio.setReadOnly(true);
		inputInstitucion.setReadOnly(true);
		
		switch(estado)
		{
			case "PENDIENTE":
				labelAdvertencia2.setVisible(false);
				inputFechaCaducidad.setEnabled(false);
				inputFechaCaducidad.setReadOnly(false);
				checkBoxDEVUELTO.setEnabled(false);
				checkBoxACEPTAR.setEnabled(true);
				checkBoxRECHAZAR.setEnabled(true);
				break;
				
			case "PRESTADO":
				checkBoxACEPTAR.setValue(true);
				checkBoxACEPTAR.setEnabled(false);
				checkBoxRECHAZAR.setEnabled(false);
				inputFechaCaducidad.setValue(fechaCaducidad);
				inputFechaCaducidad.setReadOnly(true);
				break;
				
			case "MOROSO":
				checkBoxACEPTAR.setValue(true);
				checkBoxACEPTAR.setEnabled(false);
				checkBoxRECHAZAR.setEnabled(false);
				break;
		}
		
		
		if(volumen == null)
		{
			inputVolumen.setVisible(false);
			labelVolumen.setVisible(false);
		}
		if(numero == null)
		{
			inputNumero.setVisible(false);
			labelNumero.setVisible(false);
		}
		if(anio == null)
		{
			inputAnio.setVisible(false);
			labelAnio.setVisible(false);
		}
		if(institucion == null)
		{
			inputInstitucion.setVisible(false);
			labelInstitucion.setVisible(false);
		}
		
		System.out.println("ID: " + IDSolicitud);
		final String estadoAnterior = estado;
		
		botonGuardar.addClickListener(new ClickListener()
		{
			private static final long serialVersionUID = 47254532L;
			@Override
			public void buttonClick(ClickEvent event)
			{
				
				inputFechaCaducidad.addValidator(new IsFechaCaducidad());
				inputTipoPrestamo.addValidator(new IsApellido());
				
				String fechaEntrega;
				String fechaDevolucion;
				String fechaCaducidad;
				String nuevoEstado;
				String tipoPrestamo;
				
				
				if(inputFechaCaducidad.isValid() && inputTipoPrestamo.isValid())
				{
					
					try{
						if(checkBoxACEPTAR.getValue() && !checkBoxRECHAZAR.getValue())
						{
							labelError.setVisible(false);
							SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm");
							
							
							//Date fechaEntregado = formatter.parse(fechaEntrega);
							if(!inputFechaCaducidad.getValue().isEmpty())
							{
								labelErrorCaducidad.setVisible(false);
								if(!checkBoxDEVUELTO.getValue() && estadoAnterior.equals("PENDIENTE"))
								{
									nuevoEstado = "PRESTADO";
									
									tipoPrestamo = inputTipoPrestamo.getValue().toString();
									
									fechaEntrega = (new SimpleDateFormat("YYYY-MM-dd HH:mm").format(Calendar.getInstance().getTime()));
									
									//fechaCaducidad = (new SimpleDateFormat("YYYY-MM-dd ").format(Calendar.getInstance().getTime())) + inputFechaCaducidad.getValue();
									fechaCaducidad = inputFechaCaducidad.getValue();
									
									dbc.update("Prestamo", "estado='"+nuevoEstado+"', fechaEntrega='"+fechaEntrega+"', fechaCaducidad='"+fechaCaducidad+"', tipo='"+tipoPrestamo+"'","id='"+IDSolicitud+"'");
									
									System.out.println("Fila modificada.");
									inputFechaCaducidad.setValue("");
									checkBoxACEPTAR.setValue(false);
									checkBoxRECHAZAR.setValue(false);
									
									UI.getCurrent().setContent(new formularioSolicitudesPendientes());
								}
								else if(checkBoxDEVUELTO.getValue() && (estadoAnterior.equals("PRESTADO") || estadoAnterior.equals("MOROSO")))
								{
									nuevoEstado = "DEVUELTO";
									
									fechaDevolucion = (new SimpleDateFormat("YYYY-MM-dd HH:mm").format(Calendar.getInstance().getTime()));
									
									dbc.update("Prestamo", "estado='"+nuevoEstado+"', fechaDevolucion='"+fechaDevolucion+"'","id='"+IDSolicitud+"'");	
									
									System.out.println("Fila modificada.");
									setValue(inputFechaCaducidad,"");
									checkBoxACEPTAR.setValue(false);
									checkBoxRECHAZAR.setValue(false);
									
									UI.getCurrent().setContent(new formularioSolicitudesPendientes());
								}
							}
							else
							{
								labelErrorCaducidad.setVisible(true);
							}
						}
						else if(checkBoxRECHAZAR.getValue() && !checkBoxACEPTAR.getValue())
						{
							labelError.setVisible(false);
							nuevoEstado = "DENEGADO";
							
							dbc.update("Prestamo", "estado='"+nuevoEstado+"'","id='"+IDSolicitud+"'");	
							
							System.out.println("Fila modificada.");
							inputFechaCaducidad.setValue("");
							checkBoxACEPTAR.setValue(false);
							checkBoxRECHAZAR.setValue(false);
							
							UI.getCurrent().setContent(new formularioSolicitudesPendientes());
						}
						else
						{
							labelError.setVisible(true);
						}
						
					}catch(Exception sqe){
						sqe.printStackTrace();
					}
					
					
				}
				
			}
		});
		
		
		checkBoxRECHAZAR.addValueChangeListener(new ValueChangeListener()
		{
			@Override 
			 public void valueChange(ValueChangeEvent vcEvent)
			 {
				inputFechaCaducidad.setEnabled(false);
				inputTipoPrestamo.setEnabled(false);
				
				if(checkBoxRECHAZAR.getValue())
				{
					checkBoxACEPTAR.setEnabled(false);
					labelAdvertencia.setVisible(true);
					labelError.setVisible(false);
					labelErrorCaducidad.setVisible(false);
				}
				else
				{
					checkBoxACEPTAR.setEnabled(true);
					labelAdvertencia.setVisible(false);
				}
				
			 }
		});
		
		
		checkBoxACEPTAR.addValueChangeListener(new ValueChangeListener()
		{
			@Override 
			 public void valueChange(ValueChangeEvent vcEvent)
			 {
				if(checkBoxACEPTAR.getValue())
				{
					checkBoxRECHAZAR.setEnabled(false);
					labelAdvertencia.setVisible(false);
					inputFechaCaducidad.setEnabled(true);
					inputTipoPrestamo.setEnabled(true);
					checkBoxDEVUELTO.setEnabled(true);
					labelAdvertencia2.setVisible(true);
					labelError.setVisible(false);
					labelErrorCaducidad.setVisible(false);
				}
				else
				{
					checkBoxRECHAZAR.setEnabled(true);
					inputFechaCaducidad.setEnabled(false);
					inputTipoPrestamo.setEnabled(false);
					checkBoxDEVUELTO.setEnabled(false);
					labelAdvertencia2.setVisible(false);
				}
				
			 }
		});
		
		
		botonCancelar.addClickListener(new ClickListener()
		{
			private static final long serialVersionUID = 47254532L;
			@Override
			public void buttonClick(ClickEvent event)
			{
				setValue(inputFechaCaducidad, "");
				checkBoxACEPTAR.setValue(false);
				checkBoxRECHAZAR.setValue(false);
				checkBoxDEVUELTO.setValue(false);
				UI.getCurrent().setContent(new formularioSolicitudesPendientes());
			}
	 	});
	}
	
	public void setValue(TextField textField, String value) 
	{
          if (textField.isReadOnly()) {
              textField.setReadOnly(false);
              textField.setValue(value);
              textField.setReadOnly(true);
          } else {
              textField.setValue(value);
          }
    } 
	
	@Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Formulario de editar consulta.");
    }
	

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("746px");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("746px");
		
		// labelTitulo
		labelTitulo = new Label();
		labelTitulo.setImmediate(false);
		labelTitulo.setWidth("-1px");
		labelTitulo.setHeight("-1px");
		labelTitulo.setValue("EDITAR SOLICITUD");
		mainLayout.addComponent(labelTitulo, "top:0.0px;left:290.0px;");
		
		// inputNombre
		inputNombre = new TextField();
		inputNombre.setImmediate(false);
		inputNombre.setWidth("-1px");
		inputNombre.setHeight("-1px");
		mainLayout.addComponent(inputNombre, "top:138.0px;left:499.0px;");
		
		// labelNombre
		labelNombre = new Label();
		labelNombre.setImmediate(false);
		labelNombre.setWidth("-1px");
		labelNombre.setHeight("-1px");
		labelNombre.setValue("Nombre:");
		mainLayout.addComponent(labelNombre, "top:140.0px;left:414.0px;");
		
		// labelApellidos
		labelApellidos = new Label();
		labelApellidos.setImmediate(false);
		labelApellidos.setWidth("-1px");
		labelApellidos.setHeight("-1px");
		labelApellidos.setValue("Apellidos:");
		mainLayout.addComponent(labelApellidos, "top:202.0px;left:409.0px;");
		
		// labelAutor
		labelAutor = new Label();
		labelAutor.setImmediate(false);
		labelAutor.setWidth("-1px");
		labelAutor.setHeight("-1px");
		labelAutor.setValue("Autor:");
		mainLayout.addComponent(labelAutor, "top:262.0px;left:88.0px;");
		
		// inputApellidos
		inputApellidos = new TextField();
		inputApellidos.setImmediate(false);
		inputApellidos.setWidth("-1px");
		inputApellidos.setHeight("-1px");
		mainLayout.addComponent(inputApellidos, "top:196.0px;left:499.0px;");
		
		// labelCedula
		labelCedula = new Label();
		labelCedula.setImmediate(false);
		labelCedula.setWidth("-1px");
		labelCedula.setHeight("-1px");
		labelCedula.setValue("Cédula:");
		mainLayout.addComponent(labelCedula, "top:82.0px;left:420.0px;");
		
		// inputCedula
		inputCedula = new TextField();
		inputCedula.setImmediate(false);
		inputCedula.setWidth("-1px");
		inputCedula.setHeight("-1px");
		mainLayout.addComponent(inputCedula, "top:78.0px;left:500.0px;");
		
		// labelNumero
		labelNumero = new Label();
		labelNumero.setImmediate(false);
		labelNumero.setWidth("-1px");
		labelNumero.setHeight("-1px");
		labelNumero.setValue("Número:");
		mainLayout.addComponent(labelNumero, "top:382.0px;left:74.0px;");
		
		// inputNumero
		inputNumero = new TextField();
		inputNumero.setImmediate(false);
		inputNumero.setWidth("81px");
		inputNumero.setHeight("-1px");
		mainLayout.addComponent(inputNumero, "top:376.0px;left:160.0px;");
		
		// inputAutor
		inputAutor = new TextField();
		inputAutor.setImmediate(false);
		inputAutor.setWidth("-1px");
		inputAutor.setHeight("-1px");
		mainLayout.addComponent(inputAutor, "top:256.0px;left:159.0px;");
		
		// labelTituloDoc
		labelTituloDoc = new Label();
		labelTituloDoc.setImmediate(false);
		labelTituloDoc.setWidth("-1px");
		labelTituloDoc.setHeight("-1px");
		labelTituloDoc.setValue("Título:");
		mainLayout.addComponent(labelTituloDoc, "top:202.0px;left:87.0px;");
		
		// inputTitulo
		inputTitulo = new TextField();
		inputTitulo.setImmediate(false);
		inputTitulo.setWidth("-1px");
		inputTitulo.setHeight("-1px");
		mainLayout.addComponent(inputTitulo, "top:196.0px;left:160.0px;");
		
		// inputVolumen
		inputVolumen = new TextField();
		inputVolumen.setImmediate(false);
		inputVolumen.setWidth("81px");
		inputVolumen.setHeight("-1px");
		mainLayout.addComponent(inputVolumen, "top:320.0px;left:160.0px;");
		
		// labelVolumen
		labelVolumen = new Label();
		labelVolumen.setImmediate(false);
		labelVolumen.setWidth("-1px");
		labelVolumen.setHeight("-1px");
		labelVolumen.setValue("Volúmen:");
		mainLayout.addComponent(labelVolumen, "top:320.0px;left:69.0px;");
		
		// labelFechaHora
		labelFechaHora = new Label();
		labelFechaHora.setImmediate(false);
		labelFechaHora.setWidth("-1px");
		labelFechaHora.setHeight("-1px");
		labelFechaHora.setValue("1/6/2016  14:21");
		mainLayout.addComponent(labelFechaHora, "top:18.0px;left:640.0px;");
		
		// botonGuardar
		botonGuardar = new Button();
		botonGuardar.setCaption("EDITAR CONSULTA");
		botonGuardar.setImmediate(true);
		botonGuardar.setWidth("240px");
		botonGuardar.setHeight("40px");
		mainLayout.addComponent(botonGuardar, "top:697.0px;left:241.0px;");
		
		// labelTelefono
		labelTelefono = new Label();
		labelTelefono.setImmediate(false);
		labelTelefono.setWidth("-1px");
		labelTelefono.setHeight("-1px");
		labelTelefono.setValue("Teléfono:");
		mainLayout.addComponent(labelTelefono, "top:262.0px;left:411.0px;");
		
		// inputTelefono
		inputTelefono = new TextField();
		inputTelefono.setImmediate(false);
		inputTelefono.setWidth("140px");
		inputTelefono.setHeight("-1px");
		mainLayout.addComponent(inputTelefono, "top:256.0px;left:500.0px;");
		
		// labelAdvertencia
		labelAdvertencia = new Label();
		labelAdvertencia.setImmediate(false);
		labelAdvertencia.setWidth("-1px");
		labelAdvertencia.setHeight("-1px");
		labelAdvertencia.setValue("(la solicitud ya no podrá ser editada)");
		mainLayout.addComponent(labelAdvertencia, "top:522.0px;left:567.0px;");
		
		// labelSignatura
		labelSignatura = new Label();
		labelSignatura.setImmediate(false);
		labelSignatura.setWidth("-1px");
		labelSignatura.setHeight("-1px");
		labelSignatura.setValue("Signatura:");
		mainLayout.addComponent(labelSignatura, "top:140.0px;left:65.0px;");
		
		// labelSolicitud
		labelSolicitud = new Label();
		labelSolicitud.setImmediate(false);
		labelSolicitud.setWidth("-1px");
		labelSolicitud.setHeight("-1px");
		labelSolicitud.setValue("Solicitud de préstamo:");
		mainLayout.addComponent(labelSolicitud, "top:482.0px;left:294.0px;");
		
		// inputSignatura
		inputSignatura = new TextField();
		inputSignatura.setImmediate(false);
		inputSignatura.setWidth("140px");
		inputSignatura.setHeight("-1px");
		mainLayout.addComponent(inputSignatura, "top:136.0px;left:160.0px;");
		
		// checkBoxACEPTAR
		checkBoxACEPTAR = new CheckBox();
		checkBoxACEPTAR.setCaption("ACEPTAR");
		checkBoxACEPTAR.setImmediate(false);
		checkBoxACEPTAR.setWidth("-1px");
		checkBoxACEPTAR.setHeight("-1px");
		mainLayout.addComponent(checkBoxACEPTAR, "top:521.0px;left:200.0px;");
		
		// checkBoxRECHAZAR
		checkBoxRECHAZAR = new CheckBox();
		checkBoxRECHAZAR.setCaption("RECHAZAR");
		checkBoxRECHAZAR.setImmediate(false);
		checkBoxRECHAZAR.setWidth("-1px");
		checkBoxRECHAZAR.setHeight("-1px");
		mainLayout.addComponent(checkBoxRECHAZAR, "top:521.0px;left:420.0px;");
		
		// labelFechaSolicitud
		labelFechaSolicitud = new Label();
		labelFechaSolicitud.setImmediate(false);
		labelFechaSolicitud.setWidth("-1px");
		labelFechaSolicitud.setHeight("-1px");
		labelFechaSolicitud.setValue("Fecha solicitud:");
		mainLayout.addComponent(labelFechaSolicitud, "top:82.0px;left:37.0px;");
		
		// inputFechaSolicitud
		inputFechaSolicitud = new TextField();
		inputFechaSolicitud.setImmediate(false);
		inputFechaSolicitud.setWidth("-1px");
		inputFechaSolicitud.setHeight("-1px");
		mainLayout.addComponent(inputFechaSolicitud, "top:78.0px;left:159.0px;");
		
		// inputFechaCaducidad
		inputFechaCaducidad = new TextField();
		inputFechaCaducidad.setImmediate(false);
		inputFechaCaducidad.setWidth("-1px");
		inputFechaCaducidad.setHeight("-1px");
		mainLayout.addComponent(inputFechaCaducidad, "top:576.0px;left:579.0px;");
		
		// labelSolicitante
		labelSolicitante = new Label();
		labelSolicitante.setImmediate(false);
		labelSolicitante.setWidth("-1px");
		labelSolicitante.setHeight("-1px");
		labelSolicitante.setValue("Solicitante");
		mainLayout.addComponent(labelSolicitante, "top:45.0px;left:545.0px;");
		
		// labelSolicitudt
		labelSolicitudt = new Label();
		labelSolicitudt.setImmediate(false);
		labelSolicitudt.setWidth("-1px");
		labelSolicitudt.setHeight("-1px");
		labelSolicitudt.setValue("Solicitud");
		mainLayout.addComponent(labelSolicitudt, "top:45.0px;left:200.0px;");
		
		// labelFechaCaducidad
		labelFechaCaducidad = new Label();
		labelFechaCaducidad.setImmediate(false);
		labelFechaCaducidad.setWidth("-1px");
		labelFechaCaducidad.setHeight("-1px");
		labelFechaCaducidad.setValue("Fecha de caducidad:");
		mainLayout.addComponent(labelFechaCaducidad, "top:582.0px;left:429.0px;");
		
		// inputTipoPrestamo
		inputTipoPrestamo = new ComboBox();
		inputTipoPrestamo.setImmediate(false);
		inputTipoPrestamo.setWidth("134px");
		inputTipoPrestamo.setHeight("-1px");
		mainLayout.addComponent(inputTipoPrestamo, "top:576.0px;left:160.0px;");
		
		// labelTipoPrestamo
		labelTipoPrestamo = new Label();
		labelTipoPrestamo.setImmediate(false);
		labelTipoPrestamo.setWidth("-1px");
		labelTipoPrestamo.setHeight("-1px");
		labelTipoPrestamo.setValue("Tipo de prestamo:");
		mainLayout.addComponent(labelTipoPrestamo, "top:582.0px;left:21.0px;");
		
		// inputAnio
		inputAnio = new TextField();
		inputAnio.setImmediate(false);
		inputAnio.setWidth("81px");
		inputAnio.setHeight("-1px");
		mainLayout.addComponent(inputAnio, "top:436.0px;left:159.0px;");
		
		// labelAnio
		labelAnio = new Label();
		labelAnio.setImmediate(false);
		labelAnio.setWidth("-1px");
		labelAnio.setHeight("-1px");
		labelAnio.setValue("Año:");
		mainLayout.addComponent(labelAnio, "top:440.0px;left:95.0px;");
		
		// labelInstitucion
		labelInstitucion = new Label();
		labelInstitucion.setImmediate(false);
		labelInstitucion.setWidth("-1px");
		labelInstitucion.setHeight("-1px");
		labelInstitucion.setValue("Institución:");
		mainLayout.addComponent(labelInstitucion, "top:320.0px;left:63.0px;");
		
		// inputInstitucion
		inputInstitucion = new TextField();
		inputInstitucion.setImmediate(false);
		inputInstitucion.setWidth("140px");
		inputInstitucion.setHeight("-1px");
		mainLayout.addComponent(inputInstitucion, "top:320.0px;left:160.0px;");
		
		// botonCancelar
		botonCancelar = new Button();
		botonCancelar.setCaption("CANCELAR");
		botonCancelar.setImmediate(true);
		botonCancelar.setWidth("140px");
		botonCancelar.setHeight("40px");
		mainLayout.addComponent(botonCancelar, "top:697.0px;left:580.0px;");
		
		// checkBoxDEVUELTO
		checkBoxDEVUELTO = new CheckBox();
		checkBoxDEVUELTO.setCaption("DEVUELTO");
		checkBoxDEVUELTO.setImmediate(false);
		checkBoxDEVUELTO.setWidth("-1px");
		checkBoxDEVUELTO.setHeight("-1px");
		mainLayout.addComponent(checkBoxDEVUELTO, "top:638.0px;left:420.0px;");
		
		// labelAdvertencia2
		labelAdvertencia2 = new Label();
		labelAdvertencia2.setImmediate(false);
		labelAdvertencia2.setWidth("-1px");
		labelAdvertencia2.setHeight("-1px");
		labelAdvertencia2.setValue("(la solicitud ya no podrá ser editada)");
		mainLayout.addComponent(labelAdvertencia2, "top:639.0px;left:567.0px;");
		
		// labelError
		labelError = new Label();
		labelError.setImmediate(false);
		labelError.setWidth("-1px");
		labelError.setHeight("-1px");
		labelError.setValue("Se debe aceptar o rechazar la solicitud");
		mainLayout.addComponent(labelError, "top:667.0px;left:268.0px;");
		
		// labelErrorCaducidad
		labelErrorCaducidad = new Label();
		labelErrorCaducidad.setImmediate(false);
		labelErrorCaducidad.setWidth("-1px");
		labelErrorCaducidad.setHeight("-1px");
		labelErrorCaducidad.setValue("Espacio requerido");
		mainLayout.addComponent(labelErrorCaducidad, "top:620.0px;left:580.0px;");
		
		return mainLayout;
	}

}
