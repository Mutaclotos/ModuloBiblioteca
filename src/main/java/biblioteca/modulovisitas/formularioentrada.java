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
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

import biblioteca.modulovisitas.validadores.IsApellido;
import biblioteca.modulovisitas.validadores.IsCarne;
import biblioteca.modulovisitas.validadores.IsCedula;
import biblioteca.modulovisitas.validadores.IsEmail;
import biblioteca.modulovisitas.validadores.IsHora;
import biblioteca.modulovisitas.validadores.IsInstitucion;
import biblioteca.modulovisitas.validadores.IsNombre;

public class formularioentrada extends CustomComponent {
	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private Button button_reporte;

	@AutoGenerated
	private TextField inputTema;

	@AutoGenerated
	private Label labelTema;

	@AutoGenerated
	private Label labelHoraEntrada;

	@AutoGenerated
	private TextField inputHEntrada;

	@AutoGenerated
	private TextField inputHSalida;

	@AutoGenerated
	private Label labelHoraSalida;

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
	private ComboBox inputSoy;

	@AutoGenerated
	private TextField inputCarne;

	@AutoGenerated
	private Label labelCarne;

	@AutoGenerated
	private TextField inputCedula;

	@AutoGenerated
	private Label labelCedula;

	@AutoGenerated
	private TextField inputApellidos;

	@AutoGenerated
	private Label labelApellidos;

	@AutoGenerated
	private Label labelNombre;

	@AutoGenerated
	private TextField inputNombre;

	@AutoGenerated
	private Label labelTitulo;

	public static final long serialVersionUID = 1202;
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private final DBConnector dbc;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public formularioentrada() {
		
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		dbc = new DBConnector("192.168.56.101","root","GESAVA954");
		
		String timeStamp = new SimpleDateFormat("dd/MM/YYYY HH:mm").format(Calendar.getInstance().getTime());
		labelFechaHora.setValue(timeStamp);
		
	
		FocusListener fl = new FocusListener(){
			@Override
			public void focus(FocusEvent event) {
				inputApellidos.isValid();
				inputCarne.isValid();
				inputCedula.isValid();
				inputEmail.isValid();
				inputHEntrada.isValid();
				inputHSalida.isValid();
				inputInstitucion.isValid();
				inputNombre.isValid();
			}
		};
		
		inputApellidos.addFocusListener(fl);
		inputCarne.addFocusListener(fl);
		inputCedula.addFocusListener(fl);
		inputEmail.addFocusListener(fl);
		inputHEntrada.addFocusListener(fl);
		inputHSalida.addFocusListener(fl);
		inputInstitucion.addFocusListener(fl);
		inputNombre.addFocusListener(fl);
		botonGuardar.addFocusListener(fl);
		
		this.button_reporte.addClickListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				dbc.reporte();
			}
		});
		inputSoy.setContainerDataSource(Contenedor.obtenerContenedorTipoUsuario("tipoSoy"));
		
		botonGuardar.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 47254532L;
			@Override
			public void buttonClick(ClickEvent event) {
				
				inputApellidos.addValidator(new IsApellido());
				inputCarne.addValidator(new IsCarne());
				inputCedula.addValidator(new IsCedula());
				inputEmail.addValidator(new IsEmail());
				inputHEntrada.addValidator(new IsHora());
				inputHSalida.addValidator(new IsHora());
				inputInstitucion.addValidator(new IsInstitucion());
				inputNombre.addValidator(new IsNombre());
				
				
				String apellidos;
				String carne;
				String cedula;
				String email;
				String hEntrada;
				String hSalida;
				String institucion;
				String nombre;
				String tipo;
				String tema;
				boolean noError = (inputApellidos.isValid() && inputCarne.isValid() && inputCedula.isValid()
						&& inputEmail.isValid() && inputHEntrada.isValid() && inputHSalida.isValid()
						&& inputInstitucion.isValid() && inputNombre.isValid());
				if(noError){
					apellidos = inputApellidos.getValue();
					carne = inputCarne.getValue();
					cedula = inputCedula.getValue();
					email = inputEmail.getValue();
					hEntrada = (new SimpleDateFormat("YYYY-MM-dd ").format(Calendar.getInstance().getTime())) + inputHEntrada.getValue() + ":00";
					hSalida = (new SimpleDateFormat("YYYY-MM-dd ").format(Calendar.getInstance().getTime())) + inputHSalida.getValue() + ":00";
					institucion = inputInstitucion.getValue();
					nombre = inputNombre.getValue();
					tipo = inputSoy.getValue().toString();
					tema = inputTema.getValue();
					ResultSet rs = dbc.query("SELECT 1 FROM Usuario WHERE cedula='"+cedula+"'");
					try{
						if(!rs.next()){//Si no exiten regitros con esta cedula
							dbc.insert("Usuario",cedula,carne,apellidos,nombre,email,institucion,tipo);
						}
					}catch(Exception sqe){
					}
					dbc.insert("Visitas",null,tema,hEntrada,hSalida,cedula);
					inputApellidos.setValue("");
					inputCarne.setValue("");
					inputCedula.setValue("");
					inputEmail.setValue("");
					inputHEntrada.setValue("");
					inputHSalida.setValue("");
					inputInstitucion.setValue("");
					inputNombre.setValue("");
					inputTema.setValue("");
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
		mainLayout.setHeight("460px");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("460px");
		
		// labelTitulo
		labelTitulo = new Label();
		labelTitulo.setImmediate(false);
		labelTitulo.setWidth("-1px");
		labelTitulo.setHeight("-1px");
		labelTitulo.setValue("CONTROL VISITAS");
		mainLayout.addComponent(labelTitulo, "top:0.0px;left:256.0px;");
		
		// inputNombre
		inputNombre = new TextField();
		inputNombre.setImmediate(false);
		inputNombre.setWidth("-1px");
		inputNombre.setHeight("-1px");
		mainLayout.addComponent(inputNombre, "top:60.0px;left:120.0px;");
		
		// labelNombre
		labelNombre = new Label();
		labelNombre.setImmediate(false);
		labelNombre.setWidth("-1px");
		labelNombre.setHeight("-1px");
		labelNombre.setValue("Nombre:");
		mainLayout.addComponent(labelNombre, "top:60.0px;left:40.0px;");
		
		// labelApellidos
		labelApellidos = new Label();
		labelApellidos.setImmediate(false);
		labelApellidos.setWidth("-1px");
		labelApellidos.setHeight("-1px");
		labelApellidos.setValue("Apellidos:");
		mainLayout.addComponent(labelApellidos, "top:60.0px;left:328.0px;");
		
		// inputApellidos
		inputApellidos = new TextField();
		inputApellidos.setImmediate(false);
		inputApellidos.setWidth("-1px");
		inputApellidos.setHeight("-1px");
		mainLayout.addComponent(inputApellidos, "top:60.0px;left:419.0px;");
		
		// labelCedula
		labelCedula = new Label();
		labelCedula.setImmediate(false);
		labelCedula.setWidth("-1px");
		labelCedula.setHeight("-1px");
		labelCedula.setValue("Cédula:");
		mainLayout.addComponent(labelCedula, "top:118.0px;left:43.0px;");
		
		// inputCedula
		inputCedula = new TextField();
		inputCedula.setImmediate(false);
		inputCedula.setWidth("-1px");
		inputCedula.setHeight("-1px");
		mainLayout.addComponent(inputCedula, "top:120.0px;left:120.0px;");
		
		// labelCarne
		labelCarne = new Label();
		labelCarne.setImmediate(false);
		labelCarne.setWidth("-1px");
		labelCarne.setHeight("-1px");
		labelCarne.setValue("Carné:");
		mainLayout.addComponent(labelCarne, "top:120.0px;left:344.0px;");
		
		// inputCarne
		inputCarne = new TextField();
		inputCarne.setImmediate(false);
		inputCarne.setWidth("-1px");
		inputCarne.setHeight("-1px");
		mainLayout.addComponent(inputCarne, "top:120.0px;left:419.0px;");
		
		// inputSoy
		inputSoy = new ComboBox();
		inputSoy.setImmediate(false);
		inputSoy.setWidth("141px");
		inputSoy.setHeight("-1px");
		mainLayout.addComponent(inputSoy, "top:180.0px;left:120.0px;");
		
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
		mainLayout.addComponent(inputEmail, "top:238.0px;left:278.0px;");
		
		// labelEMail
		labelEMail = new Label();
		labelEMail.setImmediate(false);
		labelEMail.setWidth("-1px");
		labelEMail.setHeight("-1px");
		labelEMail.setValue("E-mail:");
		mainLayout.addComponent(labelEMail, "top:242.0px;left:203.0px;");
		
		// labelFechaHora
		labelFechaHora = new Label();
		labelFechaHora.setImmediate(false);
		labelFechaHora.setWidth("-1px");
		labelFechaHora.setHeight("-1px");
		labelFechaHora.setValue("1/6/2016  14:21");
		mainLayout.addComponent(labelFechaHora, "top:20.0px;left:480.0px;");
		
		// botonGuardar
		botonGuardar = new Button();
		botonGuardar.setCaption("GUARDAR");
		botonGuardar.setImmediate(true);
		botonGuardar.setWidth("200px");
		botonGuardar.setHeight("40px");
		mainLayout.addComponent(botonGuardar, "top:400.0px;left:256.0px;");
		
		// labelSoy
		labelSoy = new Label();
		labelSoy.setImmediate(false);
		labelSoy.setWidth("-1px");
		labelSoy.setHeight("-1px");
		labelSoy.setValue("Soy:");
		mainLayout.addComponent(labelSoy, "top:180.0px;left:60.0px;");
		
		// labelHoraSalida
		labelHoraSalida = new Label();
		labelHoraSalida.setImmediate(false);
		labelHoraSalida.setWidth("-1px");
		labelHoraSalida.setHeight("-1px");
		labelHoraSalida.setValue("Hora salida:");
		mainLayout.addComponent(labelHoraSalida, "top:362.0px;left:436.0px;");
		
		// inputHSalida
		inputHSalida = new TextField();
		inputHSalida.setImmediate(false);
		inputHSalida.setWidth("-1px");
		inputHSalida.setHeight("-1px");
		mainLayout.addComponent(inputHSalida, "top:360.0px;left:540.0px;");
		
		// inputHEntrada
		inputHEntrada = new TextField();
		inputHEntrada.setImmediate(false);
		inputHEntrada.setWidth("-1px");
		inputHEntrada.setHeight("-1px");
		mainLayout.addComponent(inputHEntrada, "top:360.0px;left:120.0px;");
		
		// labelHoraEntrada
		labelHoraEntrada = new Label();
		labelHoraEntrada.setImmediate(false);
		labelHoraEntrada.setWidth("-1px");
		labelHoraEntrada.setHeight("-1px");
		labelHoraEntrada.setValue("Hora entrada:");
		mainLayout.addComponent(labelHoraEntrada, "top:362.0px;left:7.0px;");
		
		// labelTema
		labelTema = new Label();
		labelTema.setImmediate(false);
		labelTema.setWidth("-1px");
		labelTema.setHeight("-1px");
		labelTema.setValue("Tema:");
		mainLayout.addComponent(labelTema, "top:280.0px;left:203.0px;");
		
		// inputTema
		inputTema = new TextField();
		inputTema.setImmediate(false);
		inputTema.setWidth("-1px");
		inputTema.setHeight("-1px");
		mainLayout.addComponent(inputTema, "top:280.0px;left:278.0px;");
		
		// button_reporte
		button_reporte = new Button();
		button_reporte.setCaption("Genera Reporte");
		button_reporte.setImmediate(true);
		button_reporte.setWidth("-1px");
		button_reporte.setHeight("-1px");
		mainLayout.addComponent(button_reporte, "top:18.0px;left:590.0px;");
		
		return mainLayout;
	}

}
