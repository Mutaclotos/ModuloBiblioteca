package biblioteca.modulovisitas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector 
{
	Connection conn;
	
	public DBConnector(String ip, String usuario, String pass){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/Proyecto", usuario, pass);

			System.out.println("Connection successful");
		}catch(Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void insert(String tableName, String... columns)
	{
		
		try
		{
	        String query = "Insert into " + tableName + " values (";
	        for (int i = 0; i < columns.length; ++i) {
	        	if(i < columns.length-1){	    
	        		query += "?,";
	        	} else {
	        		query += "?)";
	        	}
		    }
	        PreparedStatement statement = conn.prepareStatement(query);
			for (int i = 0; i < columns.length; ++i) {
		        String column = columns[i];
		        statement.setString(i+1, column);
		    }
	        statement.execute();
    	}catch(Exception e){
    		System.err.println(e.getMessage());
    		e.printStackTrace();
    	}
	}
	
	public ResultSet query(String query){
		Statement statement;
		ResultSet rs = null;
		try {
			statement = conn.createStatement();
			statement.executeQuery(query);
			rs = statement.getResultSet();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void update(String tableName, String columnsToUpdate, String... conditions)
	{
		try
        {
            String query = "Update "+ tableName + " set " + columnsToUpdate + " where ";
            for (int i = 0; i < conditions.length; ++i) 
			{
		        String column = conditions[i];
		        if(i < conditions.length-1)
	        	{
	        		query += column + " and ";
	        	}
	        	else
	        	{
	        		query += column;
	        	}
		    }
            Statement statement = conn.createStatement();
            statement.executeQuery(query);
            
        }
        catch(Exception e)
        {
        	System.err.println(e.getMessage());
            e.printStackTrace();
        }
	}
}
