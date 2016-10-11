package biblioteca.modulovisitas;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vaadin.addon.charts.model.ContainerDataSeries;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

public class Contenedor {
	public static Container obtenerContenedorTipoUsuario(String datos){
		Container c = new IndexedContainer();
		c.addContainerProperty("TipoUsuario", String.class, "");
		c.addItem("Estudiante");
		c.addItem("Funcionario");
		c.addItem("Asociado");
		c.addItem("Publico");
		return c;
	}
	
	
	public static Container obtenerContenedorTipoConsulta(String datos){
		Container c = new IndexedContainer();
		c.addContainerProperty("TipoConsulta", String.class, "");
		c.addItem("Telefonica");
		c.addItem("Presencial");
		return c;
	}
	
	public static Container obtenerContenedorTipoBusqueda(String datos){
		Container c = new IndexedContainer();
		c.addContainerProperty("TipoBusqueda", String.class, "");
		c.addItem("Nombre de usuario");
		c.addItem("Tema de consulta");
		c.addItem("Fecha de emisión");
		return c;
	}
	
	public static Container obtenerContenedorTipoBusqueda2(String datos){
		Container c = new IndexedContainer();
		c.addContainerProperty("TipoBusqueda", String.class, "");
		c.addItem("Nombre de usuario");
		c.addItem("Tema de consulta");
		c.addItem("Fecha de emisión");
		c.addItem("Fecha de entrega");
		return c;
	}
	public static Container obtenerContenedorGraficoPor(){
		Container c = new IndexedContainer();
		c.addContainerProperty("values", String.class, null);
		c.addItem("Tipo Usuario");
		c.addItem("Institucion");
		c.addItem("Tema");
		return c;
	}
	
	public static Container obtenerContenedorGraficoConsultasPor(){
		Container c = new IndexedContainer();
		c.addContainerProperty("values", String.class, null);
		c.addItem("Tipo Usuario");
		c.addItem("Institucion");
		c.addItem("Tema");
		c.addItem("Base de datos");
		return c;
	}
	
	public static Container obtenerContenedorGraficoTipo(){
		Container c = new IndexedContainer();
		c.addContainerProperty("values", String.class, null);
		c.addItem("Mensual");
		c.addItem("Anual");
		return c;
	}
	
	public static Container obtenerContenedorGraficoConsultasFecha(String tipo, DBConnector dbc){
		Container c = new IndexedContainer();
		c.addContainerProperty("values", String.class,null);
		ResultSet rs = null;
		if(tipo=="Mensual")rs = dbc.query("SELECT CONCAT(year(fechaEmision),'/',month(fechaEmision)) as value FROM `consulta` group by year(fechaEmision),month(fechaEmision)");
		else rs = dbc.query("SELECT year(fechaEmision) as value FROM `consulta` group by year(fechaEmision)");
		try {
			while(rs.next()){
				c.addItem(rs.getString("value"));
				System.out.println(rs.getString("value"));
			}
		} catch (Exception e) {}
		return c;
		
	}
	
	public static Container obtenerContenedorGraficoVisitaFecha(String tipo, DBConnector dbc){
		Container c = new IndexedContainer();
		c.addContainerProperty("values", String.class,null);
		ResultSet rs = null;
		if(tipo=="Mensual")rs = dbc.query("SELECT CONCAT(year(horaLlegada),'/',month(horaLlegada)) as value FROM `visitas` group by year(horaLlegada),month(horaLlegada)");
		else rs = dbc.query("SELECT year(horaLlegada) as value FROM `visitas` group by year(horaLlegada)");
		try {
			while(rs.next()){
				c.addItem(rs.getString("value"));
				System.out.println(rs.getString("value"));
			}
		} catch (Exception e) {}
		return c;
		
	}
	
	public static Container obtenerContenedorGraficoConsultas(String tipoConsulta, String tipo, String fecha, String por,DBConnector dbc){
		IndexedContainer vaadinContainer = new IndexedContainer();
        vaadinContainer.addContainerProperty("name", String.class, null);
        vaadinContainer.addContainerProperty("y", Number.class, null);
        if(tipo=="Mensual") fecha = fecha+"/00' and '"+fecha+"/31";
        else fecha = fecha+"/00/00' and '"+fecha+"/12/31";
        ResultSet rs = null;
        if(por=="Tema") rs = dbc.query("SELECT count(c.tema) as value, c.tema as name FROM consulta c WHERE c.tipo = '"+tipoConsulta+"' and fechaEmision between '"+fecha+"' GROUP BY c.tema"); 
      	if(por=="Tipo Usuario") rs = dbc.query("SELECT count(u.tipo) as value, u.tipo as name FROM consulta c, usuario u WHERE c.tipo = '"+tipoConsulta+"' and c.usuario = u.cedula and fechaEmision between '"+fecha+"' GROUP BY u.tipo"); 
      	if(por=="Institucion")rs = dbc.query("SELECT count(u.institucion) as value, u.institucion as name FROM consulta c, usuario u WHERE c.tipo = '"+tipoConsulta+"' and c.usuario = u.cedula and fechaEmision between '"+fecha+"' GROUP BY u.institucion");
      	if(por=="Base de datos") rs = dbc.query("SELECT count(bd.nombre) as value, bd.nombre as name FROM consulta c,basesdatos bd, consultabase cb WHERE c.tipo = '"+tipoConsulta+"' and c.id = cb.consulta and cb.basedatos = bd.id and fechaEmision between '"+fecha+"' GROUP BY bd.nombre ");
        if(rs!=null){
	        try {
	        	int i=0;
				while (rs.next()) {
				    String name = rs.getString("name");
				    int value = rs.getInt("value");
				    Item ie = vaadinContainer.addItem(i++);
		            ie.getItemProperty("name").setValue(name);
		            ie.getItemProperty("y").setValue(value);
				}
			} catch (SQLException e) {}
        }
		return vaadinContainer;
	}
	
	public static Container obtenerContenedorGraficoVisita(String tipo,String fecha,String por, DBConnector dbc){
		IndexedContainer vaadinContainer = new IndexedContainer();
        vaadinContainer.addContainerProperty("name", String.class, null);
        vaadinContainer.addContainerProperty("y", Number.class, null);
        
        ResultSet rs = null;
        if(tipo=="Mensual"){
        	if(por=="Tipo Usuario") rs = dbc.query("SELECT u.tipo as name,count(u.tipo) as value FROM usuario u, visitas v where u.cedula=v.usuario AND v.horaLlegada between '"+fecha+"/00' and '"+fecha+"/31' group by u.tipo");
        	if(por=="Institucion") rs = dbc.query("SELECT u.institucion as name,count(u.institucion) as value FROM usuario u, visitas v where u.cedula=v.usuario AND v.horaLlegada between '"+fecha+"/00' and '"+fecha+"/31' group by u.institucion");
        	if(por=="Tema") rs = dbc.query("SELECT v.tema as name,count(v.tema) as value FROM usuario u, visitas v where u.cedula=v.usuario AND v.horaLlegada between '"+fecha+"/00' and '"+fecha+"/31' group by v.tema");
        }else{
        	if(por=="Tipo Usuario") rs = dbc.query("SELECT u.tipo as name,count(u.tipo) as value FROM usuario u, visitas v where u.cedula=v.usuario AND v.horaLlegada between '"+fecha+"/00/00' and '"+fecha+"/12/31' group by u.tipo");
        	if(por=="Institucion") rs = dbc.query("SELECT u.institucion as name,count(u.institucion) as value FROM usuario u, visitas v where u.cedula=v.usuario AND v.horaLlegada between '"+fecha+"/00/00' and '"+fecha+"/12/31' group by u.institucion");
        	if(por=="Tema") rs = dbc.query("SELECT v.tema as name,count(v.tema) as value FROM usuario u, visitas v where u.cedula=v.usuario AND v.horaLlegada between '"+fecha+"/00/00' and '"+fecha+"/12/31' group by v.tema");
        }
        if(rs!=null){
	        try {
	        	int i=0;
				while (rs.next()) {
				    String name = rs.getString("name");
				    int value = rs.getInt("value");
				    Item ie = vaadinContainer.addItem(i++);
		            ie.getItemProperty("name").setValue(name);
		            ie.getItemProperty("y").setValue(value);
				}
			} catch (SQLException e) {}
        }
		return vaadinContainer;
	}
	public static Container obtenerContenedorTipoDocumento(){
		Container c = new IndexedContainer();
		c.addContainerProperty("TipoDocumento", String.class, "");
		c.addItem("Libro");
		c.addItem("Revista");
		c.addItem("Tesis");
		return c;
	}
	
	public static Container obtenerContenedorTipoArchivo(){
		Container c = new IndexedContainer();
		c.addContainerProperty("TipoDocumento", String.class, "");
		c.addItem("Enlace");
		c.addItem("Archivo");
		return c;
	}
	
}
