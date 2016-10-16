
package biblioteca.modulovisitas;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import biblioteca.modulovisitas.validadores.IsCedula;
import biblioteca.modulovisitas.validadores.IsClaveAdmin;

public class formularioLoginAdmin extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private Label labelError;

	@AutoGenerated
	private Button botonLoginNormal;

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
	private TextField inputClave;

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
	public formularioLoginAdmin() 
	{
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		dbc = new DBConnector("localhost","Mutaclotos","we105769");

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
		
		botonLogin.addClickListener(new ClickListener()
		{
			private static final long serialVersionUID = 47254532L;
			@Override
			public void buttonClick(ClickEvent event) {
				
				inputCedula.addValidator(new IsCedula());
				
				inputClave.addValidator(new IsClaveAdmin());
				
				
				String cedula;
				
				String clave;
				
				int idConsulta = 0;
				boolean noError = (inputCedula.isValid() && inputClave.isValid());
				if(noError)
				{
					
					cedula = inputCedula.getValue();
					
					clave = inputClave.getValue();
					
					//if(clave.equals(null))
					{
						ResultSet rs = dbc.query("SELECT 1 FROM Usuario WHERE cedula='"+cedula+"' AND clave = '" + clave + "'");
						try{
							if(!rs.next())
							{//Si no exiten regitros con esta cedula
								System.out.println("Usuario o clave invalidos.");
								labelError.setVisible(true);
								inputClave.setValue("");
							}
							else
							{
								usuario = cedula;
								UI.getCurrent().setContent(new formularioConsultaNoCompletada());
							}
						}catch(Exception sqe)
						{
							sqe.printStackTrace();
						}
					}
					
				}
			}
			
		});
		
		botonLoginNormal.addClickListener(new ClickListener()
		{
			private static final long serialVersionUID = 47254532L;
			@Override
			public void buttonClick(ClickEvent event) 
			{	
				UI.getCurrent().setContent(new formularioLoginNormal());

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
		mainLayout.addComponent(labelTitulo, "top:20.0px;left:317.0px;");
		
		// inputClave
		inputClave = new TextField();
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
		labelCedula.setValue("Cédula de administrador:");
		mainLayout.addComponent(labelCedula, "top:160.0px;left:106.0px;");
		
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
		mainLayout.addComponent(labelFechaHora, "top:42.0px;left:500.0px;");
		
		// botonLogin
		botonLogin = new Button();
		botonLogin.setCaption("INGRESAR");
		botonLogin.setImmediate(true);
		botonLogin.setWidth("160px");
		botonLogin.setHeight("40px");
		mainLayout.addComponent(botonLogin, "top:320.0px;left:280.0px;");
		
		// botonLoginNormal
		botonLoginNormal = new Button();
		botonLoginNormal.setCaption("Login Normal");
		botonLoginNormal.setImmediate(true);
		botonLoginNormal.setWidth("140px");
		botonLoginNormal.setHeight("40px");
		mainLayout.addComponent(botonLoginNormal, "top:42.0px;left:100.0px;");
		
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
