package biblioteca.modulovisitas;

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
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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
	public static String user = "moises";
	
	public static String password = "315600";
	
	public static String address = "localhost";
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
       setContent(new formularioLoginNormal(this));
    }

    void usuarioNormal(){
    	tabsheet = new TabSheet();

    	//setContent(tabsheet);
    	//BIBLIOTECA VIRTUAL
    	tabsheet.addTab(new formularioBVirtualBusqueda(),"Biblioteca Virtual");
    	
    	// CONTROL DE VISITAS
    	tabsheet.addTab(new formularioentrada(),"Formulario de Entrada");
    	
    	//PRESTAMOS DE LIBRO
    	tabsheet.addTab(new prestamosActuales(),"Préstamos");
    	
    	setContent(tabsheet);
    }
    void usuarioAdministrativo(){
    	tabsheet = new TabSheet();
    	//BIBLIOTECA VIRTUAL
    	tabsheet.addTab(new formularioBVirtual ("/home/geo/Documentos/Inge_Biblioteca/uploads"),"Biblioteca Virtual");
    	// CONTROL DE VISITAS
    	tabsheet.addTab(new graficosVisitas(),"Visitas");
    	//CONSULTAS
    	tabsheet.addTab(new formularioConsultaNoCompletada(),"Consultas");
    	//PRESTAMOS
    	tabsheet.addTab(new formularioSolicitudesPendientes(),"Préstamos");
    	
    	setContent(tabsheet);
    }
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
