package biblioteca.modulovisitas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnector 
{
	Connection conn;
	public DBConnector()
	{
		try
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://10.1.129.60:3306", "matias", "a1b2c3d4e5");
			System.out.println("Connection successful");

            conn.close();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void insert(String tableName, String... columns)
	{
		
		try
		{
	        String query = "Insert into " + tableName + " values (";
	        for (int i = 0; i < columns.length; ++i) 
			{
	        	if(i < columns.length-1)
	        	{
	        		query += "?,";
	        	}
	        	else
	        	{
	        		query += "?)";
	        	}
		        
		    }
	        
	        PreparedStatement statement = conn.prepareStatement(query);
	        
			System.out.print("Inserted into " + tableName + " values: ");
			
			for (int i = 0; i < columns.length; ++i) 
			{
		        String column = columns[i];
		        statement.setString(i+1, column);
		        System.out.print(column + " ");
		    }
			System.out.println();
	        
	        
	        statement.execute();
	        conn.close();
    	}
    	catch(Exception e)
    	{
    		System.err.println(e.getMessage());
    		e.printStackTrace();
    	}
	}
	
	public void select(String tableName, String... columns)
	{
		try
        {
            String query = "Select * from " + tableName;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
            	for (int i = 0; i < columns.length; ++i) 
    			{
    		        String column = columns[i];
    		        System.out.print(resultSet.getString(column) + " ");
    		    }
            	System.out.println();
            }
        }
        catch(Exception e)
        {
        	System.err.println(e.getMessage());
            e.printStackTrace();
        }
	}
}
