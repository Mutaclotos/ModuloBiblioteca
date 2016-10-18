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
	public static String user = "root";
	
	public static String password = "GESAVA954";
	
	public static String address = "192.168.56.101";
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
       setContent(new formularioLoginNormal(this));
    }

    void usuarioNormal(){
    	tabsheet = new TabSheet();

    	//setContent(tabsheet);
    	//BIBLIOTECA VIRTUAL
    	tabsheet.addTab(new formularioBVirtualBusqueda(),"Busqueda Libro");
    	
    	// CONTROL DE VISITAS
    	tabsheet.addTab(new formularioentrada(),"FormularioEntrada");
    	
    	//PRESTAMOS DE LIBRO
    	tabsheet.addTab(new formularioNuevoPrestamo(),"Nuevo Prestamo");
    	tabsheet.addTab(new formularioHistorialPrestamos(),"Historial de Préstamos");
    	tabsheet.addTab(new prestamosActuales(),"Prestamos Actuales");
    	setContent(tabsheet);
    }
    void usuarioAdministrativo(){
    	tabsheet = new TabSheet();
    	//BIBLIOTECA VIRTUAL
    	tabsheet.addTab(new formularioBVirtual ("/home/geo/Documentos/Inge_Biblioteca/uploads"),"Ingresar Libro");
    	// CONTROL DE VISITAS
    	tabsheet.addTab(new graficosVisitas(),"Gráfico de Visitas");
    	tabsheet.addTab(new graficosConsultas(),"Gráfico de Consultas");
    	//CONSULTAS
    	tabsheet.addTab(new formularioNuevaConsulta(),"Nueva Consulta");
    	tabsheet.addTab(new formularioNuevaConsulta(),"Solicitudes Pendientes");
    	tabsheet.addTab(new formularioConsultaCompletada(),"Consulta Completa");
    	//tabsheet.addTab(new formularioConsultaNoCompletada(),"Consulta Errónea");
    	setContent(tabsheet);
    }
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
