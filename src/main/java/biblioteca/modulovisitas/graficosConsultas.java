package biblioteca.modulovisitas;

import java.util.Collection;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ContainerDataSeries;
import com.vaadin.addon.tableexport.ExcelExport;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

public class graficosConsultas extends CustomComponent {
	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private Button boton_excel;

	@AutoGenerated
	private Table table_excel;

	@AutoGenerated
	private Button button_volver;

	@AutoGenerated
	private ComboBox input_tipoConsulta;

	@AutoGenerated
	private Label label_1;

	@AutoGenerated
	private Button button_Dibujar;

	@AutoGenerated
	private Label label_tipo2;

	@AutoGenerated
	private Label label_Fecha;

	@AutoGenerated
	private Label label_tipo;

	@AutoGenerated
	private AbsoluteLayout espacioGrafico;

	@AutoGenerated
	private ComboBox input_por;

	@AutoGenerated
	private ComboBox input_fecha;

	@AutoGenerated
	private ComboBox input_tipo;

	@AutoGenerated
	private Label labelTitulo;

	public static final long serialVersionUID = 12764;
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private final DBConnector dbc;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public graficosConsultas() {
		
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		dbc = new DBConnector(MyUI.address,MyUI.user,MyUI.password);
		
		this.input_tipoConsulta.setNullSelectionAllowed(false);
		this.input_tipoConsulta.setContainerDataSource(Contenedor.obtenerContenedorTipoConsulta(null));
		
		this.input_tipo.setNullSelectionAllowed(false);
		this.input_tipo.setContainerDataSource(Contenedor.obtenerContenedorGraficoTipo());
		
		this.input_por.setNullSelectionAllowed(false);
		this.input_por.setContainerDataSource(Contenedor. obtenerContenedorGraficoConsultasPor());
		this.input_fecha.setNullSelectionAllowed(false);
		
		this.button_Dibujar.addClickListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				dibujarGrafico();
			}
		});
		this.input_tipo.addValueChangeListener(new ValueChangeListener(){
			@Override
			public void valueChange(ValueChangeEvent event) {
				actualizarFecha();
			}
		});
		
		button_volver.addClickListener(new ClickListener()
		{
			private static final long serialVersionUID = 47254532L;
			@Override
			public void buttonClick(ClickEvent event)
			{
				MyUI.tabsheet.replaceComponent(MyUI.tabsheet.getSelectedTab(), new formularioConsultaCompletada());
			}
	 	});
		
		boton_excel.addClickListener(new ClickListener()
		{
			private static final long serialVersionUID = 47254532L;
			@Override
			public void buttonClick(ClickEvent event)
			{
				
				try{
					excel();
				}
				catch(Exception e){
					
				}
			}
	 	});
		
		
	}
  
	private void actualizarFecha(){
		this.input_fecha.setContainerDataSource(Contenedor.obtenerContenedorGraficoConsultasFecha(this.input_tipo.getValue().toString(), dbc));
	}
	
	
	
	private void excel(){

		table_excel.setImmediate(true);

		ExcelExport excelExport;

		    excelExport = new ExcelExport(table_excel);

		     excelExport.excludeCollapsedColumns();

		     excelExport.setReportTitle("Numero de Consultas");

		     excelExport.export();

		}
	
	
	
	private void dibujarGrafico(){

		Chart chart = new Chart();

	    final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.PIE);
        configuration.getTitle().setText(" ");

        ContainerDataSeries container = 
        		new ContainerDataSeries(
        				Contenedor.obtenerContenedorGraficoConsultas(this.input_tipoConsulta.getValue().toString(),this.input_tipo.getValue().toString(),this.input_fecha.getValue().toString(),this.input_por.getValue().toString(),dbc)
        				);
        container.setName("Cantidad");
        container.setYPropertyId("y");
        container.setNamePropertyId("name");
        configuration.setSeries(container);
        configuration.setExporting(true);
        chart.drawChart(configuration);
        espacioGrafico.addComponent(chart);	
        //////////////////////////////////////temporary
        int i =0;
        String prueba=container.toString();
        System.out.println("tthis is ittt");
        System.out.println(prueba);
        Container container2 = Contenedor.obtenerContenedorGraficoConsultas(this.input_tipoConsulta.getValue().toString(),this.input_tipo.getValue().toString(),this.input_fecha.getValue().toString(),this.input_por.getValue().toString(),dbc);
        Collection <?> listaIds = container2.getItemIds();
        Object Ids [] =listaIds.toArray();
        int contadorTabla = listaIds.size();
        
        //Table table_excel = new Table("Datos"/*,container2*/ );
        table_excel.setPageLength(container2.size());
        //table_excel.setVisibleColumns("name","y");
        table_excel.addContainerProperty("Nombre", String.class, null);
        table_excel.addContainerProperty("Numero", String.class, null);
        table_excel.setImmediate(true);
        String docu ="hehe 1hola";
        String g []=docu.split("\\s+(?=[0-9])");
    	System.out.println(g[0]+"@"+g[1]);
        //table_excel.addItem(new Object[ ]{docu}, 1);
    	String separador;
        int j=0;
        while (j<contadorTabla){
        	
        	separador =container2.getItem(Ids[j]).toString();
        	String b [] = separador.split("\\s+(?=[0-9])");
        	System.out.println(b[0]+"@"+b[1]);
        	table_excel.addItem(new Object[]{b[0],b[1]},j);
        	j++;
        }
        
        
	}
	
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("830px");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("830px");
		
		// labelTitulo
		labelTitulo = new Label();
		labelTitulo.setImmediate(false);
		labelTitulo.setWidth("-1px");
		labelTitulo.setHeight("-1px");
		labelTitulo.setValue("CONTROL CONSULTAS");
		mainLayout.addComponent(labelTitulo, "top:0.0px;left:256.0px;");
		
		// input_tipo
		input_tipo = new ComboBox();
		input_tipo.setImmediate(false);
		input_tipo.setWidth("150px");
		input_tipo.setHeight("-1px");
		mainLayout.addComponent(input_tipo, "top:120.0px;left:75.0px;");
		
		// input_fecha
		input_fecha = new ComboBox();
		input_fecha.setImmediate(false);
		input_fecha.setWidth("150px");
		input_fecha.setHeight("-1px");
		mainLayout.addComponent(input_fecha, "top:120.0px;left:295.0px;");
		
		// input_por
		input_por = new ComboBox();
		input_por.setImmediate(false);
		input_por.setWidth("150px");
		input_por.setHeight("-1px");
		mainLayout.addComponent(input_por, "top:120.0px;right:43.0px;left:500.0px;");
		
		// espacioGrafico
		espacioGrafico = new AbsoluteLayout();
		espacioGrafico.setImmediate(false);
		espacioGrafico.setWidth("500px");
		espacioGrafico.setHeight("500px");
		mainLayout.addComponent(espacioGrafico, "top:220.0px;left:40.0px;");
		
		// label_tipo
		label_tipo = new Label();
		label_tipo.setImmediate(false);
		label_tipo.setWidth("-1px");
		label_tipo.setHeight("-1px");
		label_tipo.setValue("Tipo:");
		mainLayout.addComponent(label_tipo, "top:122.0px;left:35.0px;");
		
		// label_Fecha
		label_Fecha = new Label();
		label_Fecha.setImmediate(false);
		label_Fecha.setWidth("-1px");
		label_Fecha.setHeight("-1px");
		label_Fecha.setValue("Fecha:");
		mainLayout.addComponent(label_Fecha, "top:122.0px;left:244.0px;");
		
		// label_tipo2
		label_tipo2 = new Label();
		label_tipo2.setImmediate(false);
		label_tipo2.setWidth("-1px");
		label_tipo2.setHeight("-1px");
		label_tipo2.setValue("Por:");
		mainLayout.addComponent(label_tipo2, "top:122.0px;left:460.0px;");
		
		// button_Dibujar
		button_Dibujar = new Button();
		button_Dibujar.setCaption("Generar");
		button_Dibujar.setImmediate(true);
		button_Dibujar.setWidth("-1px");
		button_Dibujar.setHeight("-1px");
		mainLayout.addComponent(button_Dibujar, "top:174.0px;left:256.0px;");
		
		// label_1
		label_1 = new Label();
		label_1.setImmediate(false);
		label_1.setWidth("-1px");
		label_1.setHeight("-1px");
		label_1.setValue("Tipo de consulta");
		mainLayout.addComponent(label_1, "top:40.0px;left:200.0px;");
		
		// input_tipoConsulta
		input_tipoConsulta = new ComboBox();
		input_tipoConsulta.setImmediate(false);
		input_tipoConsulta.setWidth("125px");
		input_tipoConsulta.setHeight("-1px");
		mainLayout.addComponent(input_tipoConsulta, "top:36.0px;left:335.0px;");
		
		// button_volver
		button_volver = new Button();
		button_volver.setCaption("VOLVER");
		button_volver.setImmediate(true);
		button_volver.setWidth("-1px");
		button_volver.setHeight("-1px");
		mainLayout.addComponent(button_volver, "top:774.0px;left:253.0px;");
		
		// table_excel
		table_excel = new Table();
		table_excel.setImmediate(false);
		table_excel.setWidth("220px");
		table_excel.setHeight("360px");
		mainLayout.addComponent(table_excel, "top:220.0px;left:580.0px;");
		
		// boton_excel
		boton_excel = new Button();
		boton_excel.setCaption("Exportar a Excel");
		boton_excel.setImmediate(false);
		boton_excel.setWidth("-1px");
		boton_excel.setHeight("-1px");
		mainLayout.addComponent(boton_excel, "top:174.0px;left:624.0px;");
		
		return mainLayout;
	}

}
