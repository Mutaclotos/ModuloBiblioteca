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
	
	
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	TabSheet tabsheet = new TabSheet();
    	setContent(tabsheet);
    	
    	//BIBLIOTECA VIRTUAL
    	tabsheet.addTab(new formularioBVirtual ("/home/geo/Documentos/Inge_Biblioteca/uploads"),"Ingresar Libro");
    	tabsheet.addTab(new formularioBVirtualBusqueda(),"Busqueda Libro");
    	tabsheet.addTab(new formularioBVirtualResultados("select GROUP_CONCAT( concat(',', a.nombre )) as Autores,d.titulo as Titulo, d.signatura as Signatura,d.tipoDocumento as Tipo FROM documentoautor da right outer join documento d on d.signatura= da.documento left outer join  autor a on a.id=da.autor left outer join descriptor de on de.signatura=d.signatura WHERE 1=1 GROUP BY d.signatura"),"Resultados B.Virtual");
    	
    	// CONTROL DE VISITAS
    	tabsheet.addTab(new formularioentrada(),"FormularioEntrada");
    	tabsheet.addTab(new graficosVisitas(),"Gráfico de Visitas");
    	tabsheet.addTab(new graficosConsultas(),"Gráfico de Consultas");
    	
    	//PRESTAMOS DE LIBRO
    	tabsheet.addTab(new formularioNuevoPrestamo(),"Nuevo Prestamo");
    	tabsheet.addTab(new formularioHistorialPrestamos(),"Historial de Préstamos");
    	tabsheet.addTab(new prestamosActuales(),"Prestamos Actuales");
    	
    	//CONSULTAS
    	tabsheet.addTab(new formularioNuevaConsulta(),"Nueva Consulta");
    	tabsheet.addTab(new formularioNuevaConsulta(),"Solicitudes Pendientes");
    	tabsheet.addTab(new formularioConsultaCompletada(),"Consulta Completa");
    	tabsheet.addTab(new formularioConsultaNoCompletada(),"Consulta Errónea");
    	
    	
    	
    	//*setContent(new formularioBVirtual("/home/geo/Documentos/Inge_Biblioteca/uploads"));
    	//*setContent(new formularioBVirtualBusqueda());
    	//*setContent(new formularioBVirtualResultados("select GROUP_CONCAT( concat(',', a.nombre )) as Autores,d.titulo as Titulo, d.signatura as Signatura,d.tipoDocumento as Tipo FROM documentoautor da right outer join documento d on d.signatura= da.documento left outer join  autor a on a.id=da.autor left outer join descriptor de on de.signatura=d.signatura WHERE 1=1 GROUP BY d.signatura"));
    	
    	
    	//*setContent(new formularioentrada());
    	//*setContent(new graficosConsultas());
    	//*setContent( new graficosVisitas()); 
    	
        //*setContent(new formularioConsultaNoCompletada());
        //*setContent(new formularioNuevaConsulta());
        //*setContent(new formularioSolicitudesPendientes());
        //*setContent(new formularioConsultaCompletada());

    	//*setContent(new formularioNuevoPrestamo());
    	//*setContent(new formularioHistorialPrestamos());
    	//*setContent(new prestamosActuales());

       
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
