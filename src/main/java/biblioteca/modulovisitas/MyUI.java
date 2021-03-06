package biblioteca.modulovisitas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsBar;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("mytheme")
@Widgetset("biblioteca.modulovisitas.MyAppWidgetset")
public class MyUI extends UI 
{
	
	public static TabSheet tabsheet;
	//Credenciales para todas las clases
	public static String user = "Mutaclotos";
	
	public static String password = "we105769";
	
	public static String address = "localhost";
	
	public static String uploadsPath = "/home/geo/Documentos/Inge_Biblioteca/uploads";
	
	private AbsoluteLayout main;
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	this.set(new formularioLoginNormal(this));
    }

    public void set(Component c){
    	if(this.main!=null) this.main.removeAllComponents();
    	
    	this.main = new AbsoluteLayout();
    	setContent(this.main);
    	this.main.setWidth("100%");
    	this.main.addComponent(c, "left: 50%; top: 0px;" );
    	c.setStyleName("cosoCentro");
    }
    
    public void usuarioNormal(String usuario)
    {
    	tabsheet = new TabSheet();

    	//setContent(tabsheet);
    	//BIBLIOTECA VIRTUAL
    	tabsheet.addTab(new formularioBVirtualBusqueda(),"Biblioteca Virtual");
    	
    	// CONTROL DE VISITAS
    	tabsheet.addTab(new formularioentrada(),"Formulario de Entrada");
    	
    	//PRESTAMOS DE LIBRO
    	tabsheet.addTab(new prestamosActuales(usuario),"Préstamos");
    	
    	//Deslogeo, salir usuario
    	tabsheet.addTab(new formularioDeslogeo(this,usuario),"Salir de cuenta");
    	set(tabsheet);
    }
    public void usuarioAdministrativo(String usuario)
    {
    	tabsheet = new TabSheet();
    	//BIBLIOTECA VIRTUAL
    	tabsheet.addTab(new formularioBVirtual (this.uploadsPath),"Biblioteca Virtual");
    	
    	// CONTROL DE VISITAS
    	tabsheet.addTab(new graficosVisitas(),"Visitas");
    	//CONSULTAS
    	tabsheet.addTab(new formularioConsultaNoCompletada(),"Consultas");
    	//PRESTAMOS
    	tabsheet.addTab(new formularioSolicitudesPendientes(),"Préstamos");
    	
    	//Deslogeo, salir usuario
    	tabsheet.addTab(new formularioDeslogeo(this,usuario),"Salir de cuenta");
    	
    	set(tabsheet);
    }
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
