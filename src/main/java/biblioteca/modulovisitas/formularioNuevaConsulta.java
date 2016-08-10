package biblioteca.modulovisitas;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

import biblioteca.modulovisitas.validadores.IsApellido;
import biblioteca.modulovisitas.validadores.IsCarne;
import biblioteca.modulovisitas.validadores.IsCedula;
import biblioteca.modulovisitas.validadores.IsEmail;
import biblioteca.modulovisitas.validadores.IsFecha;
import biblioteca.modulovisitas.validadores.IsInstitucion;
import biblioteca.modulovisitas.validadores.IsNombre;
import biblioteca.modulovisitas.validadores.IsTelefono;

public class formularioNuevaConsulta extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private CheckBox checkBoxOTRAS;

	@AutoGenerated
	private CheckBox checkBoxDIALNET;

	@AutoGenerated
	private CheckBox checkBoxSCIJ;

	@AutoGenerated
	private CheckBox checkBoxOPAC;

	@AutoGenerated
	private CheckBox checkBoxMASTERLEX;

	@AutoGenerated
	private CheckBox checkBoxCIJUL;

	@AutoGenerated
	private CheckBox checkBoxCRIJA;

	@AutoGenerated
	private TextField inputTema;

	@AutoGenerated
	private Label labelBasesDatos;

	@AutoGenerated
	private Label labelTema;

	@AutoGenerated
	private Label labelFechaEntrega;

	@AutoGenerated
	private TextField inputFechaEntrega;

	@AutoGenerated
	private TextField inputObservaciones;

	@AutoGenerated
	private Label labelObservaciones;

	@AutoGenerated
	private Label labelSoy;

	@AutoGenerated
	private Button botonGuardar;

	@AutoGenerated
	private Label labelFechaHora;

	@AutoGenerated
	private Label labelEMail;

	@AutoGenerated
	private TextField inputEmail;

	@AutoGenerated
	private TextField inputInstitucion;

	@AutoGenerated
	private Label labelInstitucion;

	@AutoGenerated
	private ComboBox inputTipoConsulta;

	@AutoGenerated
	private ComboBox inputSoy;

	@AutoGenerated
	private TextField inputTelefono;

	@AutoGenerated
	private TextField inputCarne;

	@AutoGenerated
	private Label labelTipoConsulta;

	@AutoGenerated
	private Label labelCarne;

	@AutoGenerated
	private TextField inputCedula;

	@AutoGenerated
	private Label labelCedula;

	@AutoGenerated
	private TextField inputApellidos;

	@AutoGenerated
	private Label labelTelefono;

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
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public formularioNuevaConsulta() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		dbc = new DBConnector("localhost","Mutaclotos","we105769");

		timeStamp = new SimpleDateFormat("dd/MM/YYYY HH:mm").format(Calendar.getInstance().getTime());
		labelFechaHora.setValue(timeStamp);
		
	
		FocusListener fl = new FocusListener(){
			@Override
			public void focus(FocusEvent event) {
				inputApellidos.isValid();
				inputCarne.isValid();
				inputCedula.isValid();
				inputEmail.isValid();
				inputTelefono.isValid();
				inputFechaEntrega.isValid();
				inputInstitucion.isValid();
				inputNombre.isValid();
			}
		};
		
		inputApellidos.addFocusListener(fl);
		inputCarne.addFocusListener(fl);
		inputCedula.addFocusListener(fl);
		inputEmail.addFocusListener(fl);
		inputTelefono.addFocusListener(fl);
		inputFechaEntrega.addFocusListener(fl);
		inputInstitucion.addFocusListener(fl);
		inputNombre.addFocusListener(fl);
		botonGuardar.addFocusListener(fl);
		
		/*this.button_reporte.addClickListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				dbc.reporte();
			}
		});*/
		
		inputSoy.setContainerDataSource(Contenedor.obtenerContenedor("tipoSoy"));
		
		inputTipoConsulta.setContainerDataSource(Contenedor.obtenerContenedor2("tipoConsulta"));
		
		
		botonGuardar.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 47254532L;
			@Override
			public void buttonClick(ClickEvent event) {
				
				inputApellidos.addValidator(new IsApellido());
				inputCarne.addValidator(new IsCarne());
				inputCedula.addValidator(new IsCedula());
				inputEmail.addValidator(new IsEmail());
				inputFechaEntrega.addValidator(new IsFecha());
				inputTelefono.addValidator(new IsTelefono());
				inputInstitucion.addValidator(new IsInstitucion());
				inputNombre.addValidator(new IsNombre());
				
				
				String apellidos;
				String carne;
				String cedula;
				String email;
				String fechaEntrega;
				String telefono;
				String institucion;
				String nombre;
				String tipoUsuario;
				String tipoConsulta;
				String tema;
				String observaciones;
				String fechaEmision;
				int idConsulta = 0;
				boolean noError = (inputApellidos.isValid() && inputCarne.isValid() && inputCedula.isValid()
						&& inputEmail.isValid() && inputTelefono.isValid() && inputFechaEntrega.isValid()
						&& inputInstitucion.isValid() && inputNombre.isValid());
				if(noError){
					apellidos = inputApellidos.getValue();
					carne = inputCarne.getValue();
					cedula = inputCedula.getValue();
					email = inputEmail.getValue();
					if(inputFechaEntrega.getValue().equals(""))
					{
						fechaEntrega = null;
						System.out.println("Fecha entrega: " + fechaEntrega);
					}
					else
					{
						fechaEntrega = (new SimpleDateFormat("YYYY-MM-dd ").format(Calendar.getInstance().getTime())) + inputFechaEntrega.getValue();
						System.out.println("Fecha entrega: " + fechaEntrega);
					}
					fechaEmision = (new SimpleDateFormat("YYYY-MM-dd HH:mm").format(Calendar.getInstance().getTime()));
					System.out.println("Fecha emision: " + fechaEmision);
					telefono = inputTelefono.getValue();
					institucion = inputInstitucion.getValue();
					nombre = inputNombre.getValue();
					tipoUsuario = inputSoy.getValue().toString();
					tipoConsulta = inputTipoConsulta.getValue().toString();
					tema = inputTema.getValue();
					observaciones = inputObservaciones.getValue();
					ResultSet rs = dbc.query("SELECT 1 FROM Usuario WHERE cedula='"+cedula+"'");
					try{
						if(!rs.next()){//Si no exiten regitros con esta cedula
							System.out.println("Insertando nuevo usuario.");
							dbc.insert("Usuario",cedula,carne,apellidos,nombre,email,institucion,tipoUsuario);
							dbc.insert("Telefono",cedula,telefono);
						}
					}catch(Exception sqe){
					}
					
					dbc.insert("Consulta",null,tema,fechaEmision,fechaEntrega,observaciones,cedula,tipoConsulta);
					ResultSet rs2 = dbc.query("SELECT id FROM consulta ORDER BY id");
					
					try{
						rs2.last();
						idConsulta = rs2.getInt(1);
						System.out.println("Id: " + idConsulta);
					}catch(Exception sqe){
					}
					
					
						System.out.println("Consulta insertada: " + Integer.toString(idConsulta) +" " + tema +" " + fechaEmision+" " + fechaEntrega+" " + observaciones+" " + cedula+" " + tipoConsulta);
						if(checkBoxCRIJA.getValue())
						{
							System.out.println("Id Base de datos: " + Integer.toString(1) + " Id Consulta: " + Integer.toString(idConsulta));
							dbc.insert("Consultabase",Integer.toString(1),Integer.toString(idConsulta));
						}
						if(checkBoxCIJUL.getValue())
						{
							System.out.println("Id Base de datos: " + Integer.toString(2) + " Id Consulta: " + Integer.toString(idConsulta));
							dbc.insert("Consultabase",Integer.toString(2),Integer.toString(idConsulta));
						}
						if(checkBoxMASTERLEX.getValue())
						{
							System.out.println("Id Base de datos: " + Integer.toString(3) + " Id Consulta: " + Integer.toString(idConsulta));
							dbc.insert("Consultabase",Integer.toString(3),Integer.toString(idConsulta));
						}
						if(checkBoxOPAC.getValue())
						{
							System.out.println("Id Base de datos: " + Integer.toString(4) + " Id Consulta: " + Integer.toString(idConsulta));
							dbc.insert("Consultabase",Integer.toString(4),Integer.toString(idConsulta));
						}
						if(checkBoxSCIJ.getValue())
						{
							System.out.println("Id Base de datos: " + Integer.toString(5) + " Id Consulta: " + Integer.toString(idConsulta));
							dbc.insert("Consultabase",Integer.toString(5),Integer.toString(idConsulta));
						}
						if(checkBoxDIALNET.getValue())
						{
							System.out.println("Id Base de datos: " + Integer.toString(6) + " Id Consulta: " + Integer.toString(idConsulta));
							dbc.insert("Consultabase",Integer.toString(6),Integer.toString(idConsulta));
						}
						if(checkBoxOTRAS.getValue())
						{
							System.out.println("Id Base de datos: " + Integer.toString(7) + " Id Consulta: " + Integer.toString(idConsulta));
							dbc.insert("Consultabase",Integer.toString(7),Integer.toString(idConsulta));
						}
					
					inputApellidos.setValue("");
					inputCarne.setValue("");
					inputCedula.setValue("");
					inputEmail.setValue("");
					inputFechaEntrega.setValue("");
					inputTelefono.setValue("");
					inputObservaciones.setValue("");
					inputInstitucion.setValue("");
					inputNombre.setValue("");
					inputTema.setValue("");
					checkBoxCRIJA.setValue(false);
					checkBoxCIJUL.setValue(false);
					checkBoxMASTERLEX.setValue(false);
					checkBoxOPAC.setValue(false);
					checkBoxSCIJ.setValue(false);
					checkBoxDIALNET.setValue(false);
					checkBoxOTRAS.setValue(false);
				}
			}
		});
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("623px");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("623px");
		
		// labelTitulo
		labelTitulo = new Label();
		labelTitulo.setImmediate(false);
		labelTitulo.setWidth("-1px");
		labelTitulo.setHeight("-1px");
		labelTitulo.setValue("NUEVA CONSULTA");
		mainLayout.addComponent(labelTitulo, "top:0.0px;left:290.0px;");
		
		// inputNombre
		inputNombre = new TextField();
		inputNombre.setImmediate(false);
		inputNombre.setWidth("-1px");
		inputNombre.setHeight("-1px");
		mainLayout.addComponent(inputNombre, "top:116.0px;left:120.0px;");
		
		// labelNombre
		labelNombre = new Label();
		labelNombre.setImmediate(false);
		labelNombre.setWidth("-1px");
		labelNombre.setHeight("-1px");
		labelNombre.setValue("Nombre:");
		mainLayout.addComponent(labelNombre, "top:120.0px;left:34.0px;");
		
		// labelApellidos
		labelApellidos = new Label();
		labelApellidos.setImmediate(false);
		labelApellidos.setWidth("-1px");
		labelApellidos.setHeight("-1px");
		labelApellidos.setValue("Apellidos:");
		mainLayout.addComponent(labelApellidos, "top:120.0px;left:330.0px;");
		
		// labelTelefono
		labelTelefono = new Label();
		labelTelefono.setImmediate(false);
		labelTelefono.setWidth("-1px");
		labelTelefono.setHeight("-1px");
		labelTelefono.setValue("Telefono:");
		mainLayout.addComponent(labelTelefono, "top:242.0px;left:330.0px;");
		
		// inputApellidos
		inputApellidos = new TextField();
		inputApellidos.setImmediate(false);
		inputApellidos.setWidth("-1px");
		inputApellidos.setHeight("-1px");
		mainLayout.addComponent(inputApellidos, "top:116.0px;left:419.0px;");
		
		// labelCedula
		labelCedula = new Label();
		labelCedula.setImmediate(false);
		labelCedula.setWidth("-1px");
		labelCedula.setHeight("-1px");
		labelCedula.setValue("Cédula:");
		mainLayout.addComponent(labelCedula, "top:60.0px;left:39.0px;");
		
		// inputCedula
		inputCedula = new TextField();
		inputCedula.setImmediate(false);
		inputCedula.setWidth("-1px");
		inputCedula.setHeight("-1px");
		mainLayout.addComponent(inputCedula, "top:60.0px;left:120.0px;");
		
		// labelCarne
		labelCarne = new Label();
		labelCarne.setImmediate(false);
		labelCarne.setWidth("-1px");
		labelCarne.setHeight("-1px");
		labelCarne.setValue("Carné:");
		mainLayout.addComponent(labelCarne, "top:60.0px;left:344.0px;");
		
		// labelTipoConsulta
		labelTipoConsulta = new Label();
		labelTipoConsulta.setImmediate(false);
		labelTipoConsulta.setWidth("-1px");
		labelTipoConsulta.setHeight("-1px");
		labelTipoConsulta.setValue("Tipo de consulta:");
		mainLayout.addComponent(labelTipoConsulta, "top:517.0px;left:323.0px;");
		
		// inputCarne
		inputCarne = new TextField();
		inputCarne.setImmediate(false);
		inputCarne.setWidth("-1px");
		inputCarne.setHeight("-1px");
		mainLayout.addComponent(inputCarne, "top:60.0px;left:419.0px;");
		
		// inputTelefono
		inputTelefono = new TextField();
		inputTelefono.setImmediate(false);
		inputTelefono.setWidth("-1px");
		inputTelefono.setHeight("-1px");
		mainLayout.addComponent(inputTelefono, "top:236.0px;left:420.0px;");
		
		// inputSoy
		inputSoy = new ComboBox();
		inputSoy.setImmediate(false);
		inputSoy.setWidth("141px");
		inputSoy.setHeight("-1px");
		mainLayout.addComponent(inputSoy, "top:240.0px;left:120.0px;");
		
		// inputTipoConsulta
		inputTipoConsulta = new ComboBox();
		inputTipoConsulta.setImmediate(false);
		inputTipoConsulta.setWidth("141px");
		inputTipoConsulta.setHeight("-1px");
		mainLayout.addComponent(inputTipoConsulta, "top:517.0px;left:460.0px;");
		
		// labelInstitucion
		labelInstitucion = new Label();
		labelInstitucion.setImmediate(false);
		labelInstitucion.setWidth("-1px");
		labelInstitucion.setHeight("-1px");
		labelInstitucion.setValue("Institucion:");
		mainLayout.addComponent(labelInstitucion, "top:180.0px;left:323.0px;");
		
		// inputInstitucion
		inputInstitucion = new TextField();
		inputInstitucion.setImmediate(false);
		inputInstitucion.setWidth("-1px");
		inputInstitucion.setHeight("-1px");
		mainLayout.addComponent(inputInstitucion, "top:176.0px;left:419.0px;");
		
		// inputEmail
		inputEmail = new TextField();
		inputEmail.setImmediate(false);
		inputEmail.setWidth("-1px");
		inputEmail.setHeight("-1px");
		mainLayout.addComponent(inputEmail, "top:176.0px;left:120.0px;");
		
		// labelEMail
		labelEMail = new Label();
		labelEMail.setImmediate(false);
		labelEMail.setWidth("-1px");
		labelEMail.setHeight("-1px");
		labelEMail.setValue("E-mail:");
		mainLayout.addComponent(labelEMail, "top:180.0px;left:43.0px;");
		
		// labelFechaHora
		labelFechaHora = new Label();
		labelFechaHora.setImmediate(false);
		labelFechaHora.setWidth("-1px");
		labelFechaHora.setHeight("-1px");
		labelFechaHora.setValue("1/6/2016  14:21");
		mainLayout.addComponent(labelFechaHora, "top:20.0px;left:480.0px;");
		
		// botonGuardar
		botonGuardar = new Button();
		botonGuardar.setCaption("GUARDAR CONSULTA");
		botonGuardar.setImmediate(true);
		botonGuardar.setWidth("240px");
		botonGuardar.setHeight("40px");
		mainLayout.addComponent(botonGuardar, "top:587.0px;left:240.0px;");
		
		// labelSoy
		labelSoy = new Label();
		labelSoy.setImmediate(false);
		labelSoy.setWidth("-1px");
		labelSoy.setHeight("-1px");
		labelSoy.setValue("Soy:");
		mainLayout.addComponent(labelSoy, "top:240.0px;left:56.0px;");
		
		// labelObservaciones
		labelObservaciones = new Label();
		labelObservaciones.setImmediate(false);
		labelObservaciones.setWidth("-1px");
		labelObservaciones.setHeight("-1px");
		labelObservaciones.setValue("Observaciones:");
		mainLayout.addComponent(labelObservaciones, "top:437.0px;left:0.0px;");
		
		// inputObservaciones
		inputObservaciones = new TextField();
		inputObservaciones.setImmediate(false);
		inputObservaciones.setWidth("480px");
		inputObservaciones.setHeight("-1px");
		mainLayout.addComponent(inputObservaciones, "top:437.0px;left:120.0px;");
		
		// inputFechaEntrega
		inputFechaEntrega = new TextField();
		inputFechaEntrega.setImmediate(false);
		inputFechaEntrega.setWidth("-1px");
		inputFechaEntrega.setHeight("-1px");
		mainLayout.addComponent(inputFechaEntrega, "top:517.0px;left:120.0px;");
		
		// labelFechaEntrega
		labelFechaEntrega = new Label();
		labelFechaEntrega.setImmediate(false);
		labelFechaEntrega.setWidth("-1px");
		labelFechaEntrega.setHeight("-1px");
		labelFechaEntrega.setValue("Fecha entrega:");
		mainLayout.addComponent(labelFechaEntrega, "top:517.0px;left:2.0px;");
		
		// labelTema
		labelTema = new Label();
		labelTema.setImmediate(false);
		labelTema.setWidth("-1px");
		labelTema.setHeight("-1px");
		labelTema.setValue("Tema:");
		mainLayout.addComponent(labelTema, "top:300.0px;left:46.0px;");
		
		// labelBasesDatos
		labelBasesDatos = new Label();
		labelBasesDatos.setImmediate(false);
		labelBasesDatos.setWidth("-1px");
		labelBasesDatos.setHeight("-1px");
		labelBasesDatos.setValue("Bases de Datos consultadas:");
		mainLayout.addComponent(labelBasesDatos, "top:342.0px;left:265.0px;");
		
		// inputTema
		inputTema = new TextField();
		inputTema.setImmediate(false);
		inputTema.setWidth("480px");
		inputTema.setHeight("-1px");
		mainLayout.addComponent(inputTema, "top:300.0px;left:120.0px;");
		
		// checkBoxCRIJA
		checkBoxCRIJA = new CheckBox();
		checkBoxCRIJA.setCaption("CRIJA");
		checkBoxCRIJA.setImmediate(false);
		checkBoxCRIJA.setWidth("-1px");
		checkBoxCRIJA.setHeight("-1px");
		mainLayout.addComponent(checkBoxCRIJA, "top:376.0px;left:43.0px;");
		
		// checkBoxCIJUL
		checkBoxCIJUL = new CheckBox();
		checkBoxCIJUL.setCaption("CIJUL");
		checkBoxCIJUL.setImmediate(false);
		checkBoxCIJUL.setWidth("-1px");
		checkBoxCIJUL.setHeight("-1px");
		mainLayout.addComponent(checkBoxCIJUL, "top:376.0px;left:117.0px;");
		
		// checkBoxMASTERLEX
		checkBoxMASTERLEX = new CheckBox();
		checkBoxMASTERLEX.setCaption("MASTERLEX");
		checkBoxMASTERLEX.setImmediate(false);
		checkBoxMASTERLEX.setWidth("-1px");
		checkBoxMASTERLEX.setHeight("-1px");
		mainLayout.addComponent(checkBoxMASTERLEX, "top:376.0px;left:200.0px;");
		
		// checkBoxOPAC
		checkBoxOPAC = new CheckBox();
		checkBoxOPAC.setCaption("OPAC");
		checkBoxOPAC.setImmediate(false);
		checkBoxOPAC.setWidth("-1px");
		checkBoxOPAC.setHeight("-1px");
		mainLayout.addComponent(checkBoxOPAC, "top:376.0px;left:330.0px;");
		
		// checkBoxSCIJ
		checkBoxSCIJ = new CheckBox();
		checkBoxSCIJ.setCaption("SCIJ");
		checkBoxSCIJ.setImmediate(false);
		checkBoxSCIJ.setWidth("-1px");
		checkBoxSCIJ.setHeight("-1px");
		mainLayout.addComponent(checkBoxSCIJ, "top:376.0px;left:420.0px;");
		
		// checkBoxDIALNET
		checkBoxDIALNET = new CheckBox();
		checkBoxDIALNET.setCaption("DIALNET");
		checkBoxDIALNET.setImmediate(false);
		checkBoxDIALNET.setWidth("-1px");
		checkBoxDIALNET.setHeight("-1px");
		mainLayout.addComponent(checkBoxDIALNET, "top:376.0px;left:500.0px;");
		
		// checkBoxOTRAS
		checkBoxOTRAS = new CheckBox();
		checkBoxOTRAS.setCaption("OTRAS");
		checkBoxOTRAS.setImmediate(false);
		checkBoxOTRAS.setWidth("-1px");
		checkBoxOTRAS.setHeight("-1px");
		mainLayout.addComponent(checkBoxOTRAS, "top:376.0px;left:600.0px;");
		
		return mainLayout;
	}

}
