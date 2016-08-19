package biblioteca.modulovisitas;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("mytheme")
@Widgetset("biblioteca.modulovisitas.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        formularioentrada fme = new formularioentrada();
        //layout.addComponent(fme);
        //formularioNuevaConsulta fmnc = new formularioNuevaConsulta();
        //layout.addComponent(fmnc);
        //formularioConsultaNoCompletada fmcnc = new formularioConsultaNoCompletada();
        //layout.addComponent(fmcnc);
        //formularioConsultaCompletada fmcnc = new formularioConsultaCompletada();
        layout.addComponent(fme);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
