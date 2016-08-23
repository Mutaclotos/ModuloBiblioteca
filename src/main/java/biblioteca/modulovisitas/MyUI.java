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
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("mytheme")
@Widgetset("biblioteca.modulovisitas.MyAppWidgetset")
public class MyUI extends UI 
{
	
	final VerticalLayout layout = new VerticalLayout();
	
	
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //final VerticalLayout layout = new VerticalLayout();
        //layout.setMargin(true);
        //setContent(layout);
        //CustomComponent cc = new graficosVisitas(); 
        //layout.addComponent(cc);
   

        	
        setContent(new formularioConsultaNoCompletada());
        //formularioentrada fme = new formularioentrada();
        //layout.addComponent(fme);
        //formularioNuevaConsulta fmnc = new formularioNuevaConsulta();
        //layout.addComponent(fmnc);
        //formularioConsultaNoCompletada fmcnc = new formularioConsultaNoCompletada();
        //layout.addComponent(fmcnc);
        
        //int i = layout.getComponentIndex(fmcnc);
        //System.out.println("Index: " + i);
        
        //formularioConsultaCompletada fmcnc = new formularioConsultaCompletada();
        //layout.addComponent(fmcnc);
        //formularioEditarConsulta fmnc = new formularioEditarConsulta();
        //layout.addComponent(fmnc);
        
       
    }
    
    public void changeLayout(CustomComponent c)
    {
    	
    	layout.replaceComponent(layout.getComponent(1), c);
    	//layout.removeComponent();
    	//layout.addComponent(c);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
