package biblioteca.modulovisitas;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
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

import biblioteca.modulovisitas.validadores.IsSignatura;

public class formularioNuevoPrestamo extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private Button buttonCancelar;

	@AutoGenerated
	private Label labelAdvertencia;

	@AutoGenerated
	private Label labelEditorial;

	@AutoGenerated
	private TextField inputEditorial;

	@AutoGenerated
	private TextField inputVolumen;

	@AutoGenerated
	private Label labelVolumen;

	@AutoGenerated
	private TextField inputAnio;

	@AutoGenerated
	private Label labelAnio;

	@AutoGenerated
	private Label labelFecha;

	@AutoGenerated
	private Button botonGuardar;

	@AutoGenerated
	private Label labelFechaHora;

	@AutoGenerated
	private Label labelTitulo;

	@AutoGenerated
	private TextField inputTitulo;

	@AutoGenerated
	private TextField inputInstitucion;

	@AutoGenerated
	private Label labelInstitucion;

	@AutoGenerated
	private ComboBox inputTipoDocumento;

	@AutoGenerated
	private TextField inputNumero;

	@AutoGenerated
	private TextField inputSignatura;

	@AutoGenerated
	private Label labelSignatura;

	@AutoGenerated
	private Label labelNumero;

	@AutoGenerated
	private Label labelAutor;

	@AutoGenerated
	private TextField inputAutor;

	@AutoGenerated
	private Label labelTituloP;

	public static final long serialVersionUID = 1202;
	
	private String timeStamp;

	private final DBConnector dbc;
	
	boolean bandera = false;
	
	String user;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public formularioNuevoPrestamo(String usuario) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		dbc = new DBConnector(MyUI.address,MyUI.user,MyUI.password);

		timeStamp = new SimpleDateFormat("dd/MM/YYYY HH:mm").format(Calendar.getInstance().getTime());
		labelFechaHora.setValue(timeStamp);
		
	
		FocusListener fl = new FocusListener(){
			@Override
			public void focus(FocusEvent event) {
				inputSignatura.isValid();
			}
		};
		
		inputSignatura.addFocusListener(fl);
		botonGuardar.addFocusListener(fl);
		
		
		inputTipoDocumento.setContainerDataSource(Contenedor.obtenerContenedorTipoDocumento());
		inputSignatura.setEnabled(true);
		inputAutor.setReadOnly(true);
		inputTitulo.setReadOnly(true);
		inputInstitucion.setReadOnly(true);
		inputVolumen.setReadOnly(true);
		inputNumero.setReadOnly(true);
		inputEditorial.setReadOnly(true);
		inputAnio.setReadOnly(true);
		
		inputSignatura.setVisible(false);
		inputAutor.setVisible(false);
		inputTitulo.setVisible(false);
		inputInstitucion.setVisible(false);
		inputVolumen.setVisible(false);
		inputNumero.setVisible(false);
		inputEditorial.setVisible(false);
		labelEditorial.setVisible(false);
		inputAnio.setVisible(false);
		botonGuardar.setEnabled(false);
		
		labelSignatura.setVisible(false);
		labelAutor.setVisible(false);
		labelTitulo.setVisible(false);
		labelInstitucion.setVisible(false);
		labelVolumen.setVisible(false);
		labelNumero.setVisible(false);
		labelAnio.setVisible(false);
		labelAdvertencia.setVisible(false);
		
		inputTipoDocumento.setImmediate(true);
		inputSignatura.setImmediate(true);
		inputAutor.setImmediate(true);
		inputTitulo.setImmediate(true);
		inputInstitucion.setImmediate(true);
		inputVolumen.setImmediate(true);
		inputNumero.setImmediate(true);
		inputAnio.setImmediate(true);
		
		this.user = usuario;
		
		inputTipoDocumento.addValueChangeListener(new ValueChangeListener(){
			
			 @Override 
			 public void valueChange(ValueChangeEvent vcEvent)
			 {
				 
					setValue(inputTitulo,"");
					setValue(inputSignatura,"");
					setValue(inputAutor,"");
					setValue(inputNumero,"");
					setValue(inputVolumen,"");
					setValue(inputAnio,"");
					setValue(inputInstitucion,"");
					setValue(inputEditorial,"");
				 
				 if(inputTipoDocumento.getValue().toString().equals("Libro"))
					{
					 
						inputSignatura.setVisible(true);
						inputAutor.setVisible(true);
						inputTitulo.setVisible(true);
						inputInstitucion.setVisible(false);
						inputVolumen.setVisible(false);
						inputNumero.setVisible(false);
						inputEditorial.setVisible(false);
						labelEditorial.setVisible(false);
						inputAnio.setVisible(false);
						botonGuardar.setEnabled(true);
						
						labelSignatura.setVisible(true);
						labelAutor.setVisible(true);
						labelTitulo.setVisible(true);
						labelInstitucion.setVisible(false);
						labelVolumen.setVisible(false);
						labelNumero.setVisible(false);
						labelAnio.setVisible(false);
						labelAdvertencia.setVisible(false);
						
					}
					else if(inputTipoDocumento.getValue().toString().equals("Revista"))
					{
						
						inputSignatura.setVisible(true);
						inputAutor.setVisible(true);
						inputTitulo.setVisible(true);
						inputInstitucion.setVisible(false);
						inputVolumen.setVisible(true);
						inputNumero.setVisible(true);
						inputEditorial.setVisible(false);
						labelEditorial.setVisible(false);
						inputAnio.setVisible(true);
						botonGuardar.setEnabled(true);
						
						labelSignatura.setVisible(true);
						labelAutor.setVisible(true);
						labelTitulo.setVisible(true);
						labelInstitucion.setVisible(false);
						labelVolumen.setVisible(true);
						labelNumero.setVisible(true);
						labelAnio.setVisible(true);
						labelAdvertencia.setVisible(false);
					}
					else if(inputTipoDocumento.getValue().toString().equals("Tesis"))
					{
						
						inputSignatura.setVisible(true);
						inputAutor.setVisible(true);
						inputTitulo.setVisible(true);
						inputInstitucion.setVisible(true);
						inputVolumen.setVisible(false);
						inputNumero.setVisible(false);
						inputEditorial.setVisible(false);
						labelEditorial.setVisible(false);
						inputAnio.setVisible(true);
						botonGuardar.setEnabled(true);
						
						labelSignatura.setVisible(true);
						labelAutor.setVisible(true);
						labelTitulo.setVisible(true);
						labelInstitucion.setVisible(true);
						labelVolumen.setVisible(false);
						labelNumero.setVisible(false);
						labelAnio.setVisible(true);
						labelAdvertencia.setVisible(false);
					}
			 }
		});
		
		
		inputSignatura.addValueChangeListener(new ValueChangeListener()
		{
			@Override 
			 public void valueChange(ValueChangeEvent vcEvent)
			 {
				String signatura = inputSignatura.getValue();
				String tipoDocumento = inputTipoDocumento.getValue().toString();
				String titulo;
				String autor;
				String volumen;
				String numero;
				String anio;
				String institucion;
				
				ResultSet rs = dbc.query("SELECT 1 FROM Documento WHERE signatura='"+signatura+"' AND tipoDocumento='"+tipoDocumento+"'");
				ResultSet rs2;
				
				try{
					if(!rs.next()){//Si no exiten regitros con esta signatura y tipoDocumento
						System.out.println("Documento no encontrado en la base de datos.");
						labelAdvertencia.setVisible(true);
						bandera = true;
					}
					else
					{
						labelAdvertencia.setVisible(false);
						bandera = false;
						if(tipoDocumento.equals("Libro"))
						{
							rs2 = dbc.query("SELECT d.titulo, (SELECT GROUP_CONCAT(a.nombre) FROM autor a, documentoautor da "
									+ "WHERE d.signatura = da.documento AND da.autor = a.id ) AS autor FROM documento d "
									+ "WHERE d.signatura = '"+ signatura +"' GROUP BY d.signatura ");
							
							if(rs2.next())
							{	
								titulo = rs2.getString("titulo");
								autor = rs2.getString("autor");
								setValue(inputTitulo,titulo);
								setValue(inputAutor,autor);
							}
						}
						else if(tipoDocumento.equals("Revista"))
						{
							rs2 = dbc.query("SELECT d.titulo, (SELECT GROUP_CONCAT(a.nombre) FROM autor a, documentoautor da "
									+ "WHERE d.signatura = da.documento AND da.autor = a.id ) AS autor, d.volumen, d.numero, d.anio FROM documento d "
									+ "WHERE d.signatura = '"+ signatura +"' GROUP BY d.signatura ");
							
							if(rs2.next())
							{	
								titulo = rs2.getString(1);
								autor = rs2.getString(2);
								volumen = rs2.getString(3);
								numero = rs2.getString(4);
								anio = rs2.getString(5);
										
								setValue(inputTitulo,titulo);
								setValue(inputAutor,autor);
								setValue(inputVolumen,volumen);
								setValue(inputNumero,numero);
								setValue(inputAnio,anio);
							}
						}
						else if(tipoDocumento.equals("Tesis"))
						{
							rs2 = dbc.query("SELECT d.titulo, (SELECT GROUP_CONCAT(a.nombre) FROM autor a, documentoautor da "
									+ "WHERE d.signatura = da.documento AND da.autor = a.id ) AS autor,d.anio, d.institucion FROM documento d "
									+ "WHERE d.signatura = '"+ signatura +"' GROUP BY d.signatura ");
							
							if(rs2.next())
							{
								titulo = rs2.getString(1);
								autor = rs2.getString(2);
								anio = rs2.getString(3);
								institucion = rs2.getString(4);
										
								setValue(inputTitulo,titulo);
								setValue(inputAutor,autor);
								setValue(inputAnio,anio);
								setValue(inputInstitucion,institucion);
							}
						}
					}
				}catch(Exception sqe){
					sqe.printStackTrace();
				}
			 }
		});
		
		
		botonGuardar.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 47254532L;
			@Override
			public void buttonClick(ClickEvent event) {
				
				inputSignatura.addValidator(new IsSignatura());
				
				String signatura;
				String fechaSolicitud = (new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime()));
				if(!bandera)
				{
					if(inputSignatura.isValid()){
						
						signatura = inputSignatura.getValue();
	
						ResultSet rs = dbc.query("SELECT 1 FROM Documento WHERE signatura='"+signatura+"'");
						try{
							if(!rs.next()){//Si no exiten regitros con esta signatura
								System.out.println("Documento no encontrado en la base de datos.");
								
							}
							else
							{
								//TODO: insertar usuario en Prestamo
								dbc.insert("Prestamo",null,signatura,user,null,fechaSolicitud,null,null,null,null);
							}
						}catch(Exception sqe){
							sqe.printStackTrace();
						}
						
						setValue(inputTitulo,"");
						setValue(inputSignatura,"");
						setValue(inputAutor,"");
						setValue(inputNumero,"");
						setValue(inputVolumen,"");
						setValue(inputAnio,"");
						setValue(inputInstitucion,"");
						setValue(inputEditorial,"");
						
						MyUI.tabsheet.replaceComponent(MyUI.tabsheet.getSelectedTab(), new prestamosActuales(user));
					}
				}
			}
		});
		
		buttonCancelar.addClickListener(new ClickListener()
		{
			private static final long serialVersionUID = 47254532L;
			@Override
			public void buttonClick(ClickEvent event)
			{
				MyUI.tabsheet.replaceComponent(MyUI.tabsheet.getSelectedTab(), new prestamosActuales(user));
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
		
		// labelTituloP
		labelTituloP = new Label();
		labelTituloP.setImmediate(false);
		labelTituloP.setWidth("-1px");
		labelTituloP.setHeight("-1px");
		labelTituloP.setValue("NUEVA SOLICITUD");
		mainLayout.addComponent(labelTituloP, "top:2.0px;left:291.0px;");
		
		// inputAutor
		inputAutor = new TextField();
		inputAutor.setImmediate(false);
		inputAutor.setWidth("480px");
		inputAutor.setHeight("-1px");
		mainLayout.addComponent(inputAutor, "top:200.0px;left:120.0px;");
		
		// labelAutor
		labelAutor = new Label();
		labelAutor.setImmediate(false);
		labelAutor.setWidth("-1px");
		labelAutor.setHeight("-1px");
		labelAutor.setValue("Autor:");
		mainLayout.addComponent(labelAutor, "top:202.0px;left:48.0px;");
		
		// labelNumero
		labelNumero = new Label();
		labelNumero.setImmediate(false);
		labelNumero.setWidth("-1px");
		labelNumero.setHeight("-1px");
		labelNumero.setValue("Número:");
		mainLayout.addComponent(labelNumero, "top:381.0px;left:33.0px;");
		
		// labelSignatura
		labelSignatura = new Label();
		labelSignatura.setImmediate(false);
		labelSignatura.setWidth("-1px");
		labelSignatura.setHeight("-1px");
		labelSignatura.setValue("Signatura:");
		mainLayout.addComponent(labelSignatura, "top:142.0px;left:25.0px;");
		
		// inputSignatura
		inputSignatura = new TextField();
		inputSignatura.setImmediate(false);
		inputSignatura.setWidth("-1px");
		inputSignatura.setHeight("-1px");
		mainLayout.addComponent(inputSignatura, "top:140.0px;left:120.0px;");
		
		// inputNumero
		inputNumero = new TextField();
		inputNumero.setImmediate(false);
		inputNumero.setWidth("80px");
		inputNumero.setHeight("-1px");
		mainLayout.addComponent(inputNumero, "top:381.0px;left:120.0px;");
		
		// inputTipoDocumento
		inputTipoDocumento = new ComboBox();
		inputTipoDocumento.setCaption("Tipo de documento:");
		inputTipoDocumento.setImmediate(false);
		inputTipoDocumento.setWidth("141px");
		inputTipoDocumento.setHeight("-1px");
		mainLayout.addComponent(inputTipoDocumento, "top:80.0px;left:120.0px;");
		
		// labelInstitucion
		labelInstitucion = new Label();
		labelInstitucion.setImmediate(false);
		labelInstitucion.setWidth("-1px");
		labelInstitucion.setHeight("-1px");
		labelInstitucion.setValue("Institución:");
		mainLayout.addComponent(labelInstitucion, "top:324.0px;left:25.0px;");
		
		// inputInstitucion
		inputInstitucion = new TextField();
		inputInstitucion.setImmediate(false);
		inputInstitucion.setWidth("-1px");
		inputInstitucion.setHeight("-1px");
		mainLayout.addComponent(inputInstitucion, "top:318.0px;left:120.0px;");
		
		// inputTitulo
		inputTitulo = new TextField();
		inputTitulo.setImmediate(false);
		inputTitulo.setWidth("480px");
		inputTitulo.setHeight("-1px");
		mainLayout.addComponent(inputTitulo, "top:260.0px;left:120.0px;");
		
		// labelTitulo
		labelTitulo = new Label();
		labelTitulo.setImmediate(false);
		labelTitulo.setWidth("-1px");
		labelTitulo.setHeight("-1px");
		labelTitulo.setValue("Título:");
		mainLayout.addComponent(labelTitulo, "top:262.0px;left:46.0px;");
		
		// labelFechaHora
		labelFechaHora = new Label();
		labelFechaHora.setImmediate(false);
		labelFechaHora.setWidth("-1px");
		labelFechaHora.setHeight("-1px");
		labelFechaHora.setValue("1/6/2016  14:21");
		mainLayout.addComponent(labelFechaHora, "top:42.0px;left:520.0px;");
		
		// botonGuardar
		botonGuardar = new Button();
		botonGuardar.setCaption("ENVIAR SOLICITUD");
		botonGuardar.setImmediate(true);
		botonGuardar.setWidth("240px");
		botonGuardar.setHeight("40px");
		mainLayout.addComponent(botonGuardar, "top:548.0px;left:120.0px;");
		
		// labelFecha
		labelFecha = new Label();
		labelFecha.setImmediate(false);
		labelFecha.setWidth("-1px");
		labelFecha.setHeight("100.0%");
		labelFecha.setValue("Fecha:");
		mainLayout.addComponent(labelFecha, "top:40.0px;bottom:-21.0px;left:443.0px;");
		
		// labelAnio
		labelAnio = new Label();
		labelAnio.setImmediate(false);
		labelAnio.setWidth("-1px");
		labelAnio.setHeight("-1px");
		labelAnio.setValue("Año:");
		mainLayout.addComponent(labelAnio, "top:441.0px;left:54.0px;");
		
		// inputAnio
		inputAnio = new TextField();
		inputAnio.setImmediate(false);
		inputAnio.setWidth("60px");
		inputAnio.setHeight("-1px");
		mainLayout.addComponent(inputAnio, "top:441.0px;left:120.0px;");
		
		// labelVolumen
		labelVolumen = new Label();
		labelVolumen.setImmediate(false);
		labelVolumen.setWidth("-1px");
		labelVolumen.setHeight("-1px");
		labelVolumen.setValue("Volumen:");
		mainLayout.addComponent(labelVolumen, "top:324.0px;left:31.0px;");
		
		// inputVolumen
		inputVolumen = new TextField();
		inputVolumen.setImmediate(false);
		inputVolumen.setWidth("140px");
		inputVolumen.setHeight("-1px");
		mainLayout.addComponent(inputVolumen, "top:319.0px;left:120.0px;");
		
		// inputEditorial
		inputEditorial = new TextField();
		inputEditorial.setImmediate(false);
		inputEditorial.setWidth("-1px");
		inputEditorial.setHeight("-1px");
		mainLayout.addComponent(inputEditorial, "top:319.0px;left:459.0px;");
		
		// labelEditorial
		labelEditorial = new Label();
		labelEditorial.setImmediate(false);
		labelEditorial.setWidth("-1px");
		labelEditorial.setHeight("-1px");
		labelEditorial.setValue("Editorial:");
		mainLayout.addComponent(labelEditorial, "top:324.0px;left:373.0px;");
		
		// labelAdvertencia
		labelAdvertencia = new Label();
		labelAdvertencia.setImmediate(false);
		labelAdvertencia.setWidth("-1px");
		labelAdvertencia.setHeight("-1px");
		labelAdvertencia.setValue("No existe un documento con esta signatura en la base de datos.");
		mainLayout.addComponent(labelAdvertencia, "top:140.0px;left:300.0px;");
		
		// buttonCancelar
		buttonCancelar = new Button();
		buttonCancelar.setCaption("CANCELAR");
		buttonCancelar.setImmediate(true);
		buttonCancelar.setWidth("160px");
		buttonCancelar.setHeight("40px");
		mainLayout.addComponent(buttonCancelar, "top:548.0px;left:460.0px;");
		
		return mainLayout;
	}

}
