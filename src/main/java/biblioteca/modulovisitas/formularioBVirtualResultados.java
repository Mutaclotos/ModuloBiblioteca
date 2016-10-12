package biblioteca.modulovisitas;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

public class formularioBVirtualResultados extends CustomComponent {
	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private Table table_1;

	@AutoGenerated
	private Label labelNuevaEntrada;

	public static final long serialVersionUID = 1202;
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private DBConnector dbc;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public formularioBVirtualResultados(String sql) {
		
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		dbc = new DBConnector("192.168.56.101","root","GESAVA954");
		try{
			procesarSQL(sql);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		this.table_1.setColumnCollapsingAllowed(true);
		this.table_1.setSelectable(true);
	}
	
	private void procesarSQL(String sql) throws SQLException{
		ResultSet rs = dbc.query(sql);
		int nColumnas = this.updateMetaProperties(rs);
		Integer i = 0;
		while(rs.next()){
			Object[] datos = new String[nColumnas];
			for(int k=1 ;k<=nColumnas ;++k){
				datos[k-1] = rs.getString(k);
			}
			table_1.addItem(datos,i); 
			++i;
		}
	} 
	
   private int updateMetaProperties(ResultSet rs) throws SQLException {
	   ResultSetMetaData rsmt = rs.getMetaData();
	   int n = rsmt.getColumnCount();
	   Object[] columnas = new String[n];
	   for(int i=1;i<=n;++i){
		   table_1.addContainerProperty(rsmt.getColumnLabel(i), String.class, null);
		   columnas[i-1] = (Object)rsmt.getColumnLabel(i);
	   }
	   this.table_1.setVisibleColumns(columnas);
	   return n;
   }

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("960px");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("960px");
		
		// labelNuevaEntrada
		labelNuevaEntrada = new Label();
		labelNuevaEntrada.setImmediate(false);
		labelNuevaEntrada.setWidth("360px");
		labelNuevaEntrada.setHeight("18px");
		labelNuevaEntrada.setValue("RESULTADOS DE LA BUSQUEDA");
		mainLayout.addComponent(labelNuevaEntrada, "top:0.0px;left:260.0px;");
		
		// table_1
		table_1 = new Table();
		table_1.setImmediate(false);
		table_1.setWidth("90.0%");
		table_1.setHeight("280px");
		table_1.setTabIndex(4);
		mainLayout.addComponent(table_1, "top:57.0px;left:20.0px;");
		
		return mainLayout;
	}

}