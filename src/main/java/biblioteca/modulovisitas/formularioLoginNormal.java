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
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import biblioteca.modulovisitas.validadores.IsCedula;
import biblioteca.modulovisitas.validadores.IsClave;

public class formularioLoginNormal extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private Label labelError;

	@AutoGenerated
	private Label labelNota;

	@AutoGenerated
	private Button buttonRegistrar;

	@AutoGenerated
	private Button botonLoginAdmin;

	@AutoGenerated
	private Button botonLogin;

	@AutoGenerated
	private Label labelFechaHora;

	@AutoGenerated
	private TextField inputCedula;

	@AutoGenerated
	private Label labelCedula;

	@AutoGenerated
	private Label labelClave;

	@AutoGenerated
	private PasswordField inputClave;

	@AutoGenerated
	private Label labelTitulo;

	public static final long serialVersionUID = 1202;
	
	private String timeStamp;
	
	public static String usuario;

	private final DBConnector dbc;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	MyUI m;
	public formularioLoginNormal(MyUI main) 
	{
		this.m = main;
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		dbc = new DBConnector(MyUI.address,MyUI.user,MyUI.password);

		timeStamp = new SimpleDateFormat("dd/MM/YYYY HH:mm").format(Calendar.getInstance().getTime());
		labelFechaHora.setValue(timeStamp);
		
	
		FocusListener fl = new FocusListener(){
			@Override
			public void focus(FocusEvent event) {
				inputCedula.isValid();
				inputClave.isValid();
			}
		};
		
		inputCedula.addFocusListener(fl);
		inputClave.addFocusListener(fl);
		botonLogin.addFocusListener(fl);
		
		
		inputCedula.setImmediate(true);
		labelError.setImmediate(true);
		labelError.setVisible(false);
		labelNota.setImmediate(true);
		
		botonLogin.addClickListener(new ClickListener()
		{
			private static final long serialVersionUID = 47254532L;
			@Override
			public void buttonClick(ClickEvent event) {
				
				inputCedula.addValidator(new IsCedula());
				
				inputClave.addValidator(new IsClave());
				
				
				String cedula;
				
				String clave;
				
				boolean noError = (inputCedula.isValid() && inputClave.isValid());
				if(noError)
				{
					
					cedula = inputCedula.getValue();
					
					if(inputClave.isEmpty())
					{
						clave = null;
					}
					else
					{
						clave = inputClave.getValue();
					}
					
					ResultSet rs;
					
					if(clave == null)
					{
						rs = dbc.query("SELECT 1 FROM usuario WHERE cedula='"+cedula+"'");
						try{
							if(!rs.next())
							{//Si no exiten regitros con esta cedula
								System.out.println("Usuario invalido.");
								labelError.setVisible(true);
								labelNota.setVisible(false);
								inputClave.setValue("");
							}
							else
							{
								//TODO: setContent editUser
								//m.usuarioNormal();
								UI.getCurrent().setContent(new formularioEditarUsuario(m));
							}
						}catch(Exception sqe)
						{
							sqe.printStackTrace();
						}
					}
					else
					{
						rs = dbc.query("SELECT 1 FROM usuario WHERE cedula='"+cedula+"' AND clave = '" + clave + "' ");
						try{
							if(!rs.next())
							{//Si no exiten regitros con esta cedula
								System.out.println("Usuario o clave invalidos.");
								labelError.setVisible(true);
								labelNota.setVisible(false);
								inputClave.setValue("");
							}
							else
							{
								usuario = cedula;
								m.usuarioNormal();
							}
						}catch(Exception sqe)
						{
							sqe.printStackTrace();
						}
					}
					
				}
			}
			
		});
		
		buttonRegistrar.addClickListener(new ClickListener()
		{
			private static final long serialVersionUID = 47254532L;
			@Override
			public void buttonClick(ClickEvent event) 
			{	
				//TODO: setContent to register new user
				//UI.getCurrent().setContent(new prestamosActuales());
			}
			
		});
		
		botonLoginAdmin.addClickListener(new ClickListener()
		{
			private static final long serialVersionUID = 47254532L;
			@Override
			public void buttonClick(ClickEvent event) 
			{	
				UI.getCurrent().setContent(new formularioLoginAdmin(m));
			}
			
		});
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("374px");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("374px");
		
		// labelTitulo
		labelTitulo = new Label();
		labelTitulo.setImmediate(false);
		labelTitulo.setWidth("-1px");
		labelTitulo.setHeight("-1px");
		labelTitulo.setValue("INGRESAR");
		mainLayout.addComponent(labelTitulo, "top:20.0px;left:337.0px;");
		
		// inputClave
		inputClave = new PasswordField();
		inputClave.setImmediate(false);
		inputClave.setWidth("161px");
		inputClave.setHeight("-1px");
		mainLayout.addComponent(inputClave, "top:216.0px;left:279.0px;");
		
		// labelClave
		labelClave = new Label();
		labelClave.setImmediate(false);
		labelClave.setWidth("-1px");
		labelClave.setHeight("-1px");
		labelClave.setValue("Contraseña:");
		mainLayout.addComponent(labelClave, "top:222.0px;left:175.0px;");
		
		// labelCedula
		labelCedula = new Label();
		labelCedula.setImmediate(false);
		labelCedula.setWidth("-1px");
		labelCedula.setHeight("-1px");
		labelCedula.setValue("Cédula de usuario:");
		mainLayout.addComponent(labelCedula, "top:160.0px;left:139.0px;");
		
		// inputCedula
		inputCedula = new TextField();
		inputCedula.setImmediate(false);
		inputCedula.setWidth("160px");
		inputCedula.setHeight("-1px");
		mainLayout.addComponent(inputCedula, "top:156.0px;left:280.0px;");
		
		// labelFechaHora
		labelFechaHora = new Label();
		labelFechaHora.setImmediate(false);
		labelFechaHora.setWidth("-1px");
		labelFechaHora.setHeight("-1px");
		labelFechaHora.setValue("1/6/2016  14:21");
		mainLayout.addComponent(labelFechaHora, "top:62.0px;left:520.0px;");
		
		// botonLogin
		botonLogin = new Button();
		botonLogin.setCaption("INGRESAR");
		botonLogin.setImmediate(true);
		botonLogin.setWidth("160px");
		botonLogin.setHeight("40px");
		mainLayout.addComponent(botonLogin, "top:320.0px;left:120.0px;");
		
		// botonLoginAdmin
		botonLoginAdmin = new Button();
		botonLoginAdmin.setCaption("Login Administrativo");
		botonLoginAdmin.setImmediate(true);
		botonLoginAdmin.setWidth("199px");
		botonLoginAdmin.setHeight("40px");
		mainLayout.addComponent(botonLoginAdmin, "top:60.0px;left:80.0px;");
		
		// buttonRegistrar
		buttonRegistrar = new Button();
		buttonRegistrar.setCaption("REGISTRARSE");
		buttonRegistrar.setImmediate(true);
		buttonRegistrar.setWidth("160px");
		buttonRegistrar.setHeight("40px");
		mainLayout.addComponent(buttonRegistrar, "top:320.0px;left:440.0px;");
		
		// labelNota
		labelNota = new Label();
		labelNota.setImmediate(false);
		labelNota.setWidth("-1px");
		labelNota.setHeight("-1px");
		labelNota.setValue("Nota: dejar espacio en blanco si no posee una contraseña");
		mainLayout.addComponent(labelNota, "top:262.0px;left:280.0px;");
		
		// labelError
		labelError = new Label();
		labelError.setImmediate(false);
		labelError.setWidth("-1px");
		labelError.setHeight("-1px");
		labelError.setValue("Usuario o contraseña no validos.");
		mainLayout.addComponent(labelError, "top:262.0px;left:279.0px;");
		
		return mainLayout;
	}

}
