package repository;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.*;

public class DatabaseService {
	static Connection connection = null;

    private static void connectDB() throws SQLException, ClassNotFoundException{
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Create a connection to the database
            connection = DriverManager.getConnection("jdbc:sqlite:database/bdd.db");

    }

    public static void initialiseDB() throws SQLException, ClassNotFoundException {
    	connectDB();
        String createTable = "CREATE TABLE IF NOT EXISTS stock("
        					+ "idAction INTEGER PRIMARY KEY AUTOINCREMENT ,"
        					+ "actionType TEXT,"
        					+ "material TEXT,"
        					+ "quantity INTEGER,"
        					+ "description TEXT,"
        					+ "from_ TEXT,"
        					+ "to_ TEXT,"
        					+ "date_ DATE)";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }
 
	public static ArrayList<Action> getAll(String orderBy) throws SQLException, ClassNotFoundException{
		ArrayList<Action> data = new ArrayList<Action>();
		connectDB();
		// Execute a SELECT query
	    String selectQuery = "SELECT * FROM stock ORDER BY " +orderBy;
	    if (orderBy == "date_") selectQuery += " DESC";
	    selectQuery += ", idAction DESC";
	    PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	    ResultSet resultSet = preparedStatement.executeQuery();
	        // Process the result set
	        while (resultSet.next()) {
	        	Material material = new Material(resultSet.getString("material"), resultSet.getInt("quantity"));
	            Action action = new Action (resultSet.getString("idAction"),
	            							resultSet.getString("actionType"),
	            							material,
	            							resultSet.getString("description"),
	            							resultSet.getString("from_"),
	            							resultSet.getString("to_"),
	            							resultSet.getString("date_")
	            							);
	            data.add(action);
	        }
	        System.out.println("Got all data.");
			return data;
	    
	    }
	
	public static void insert(Action action) throws SQLException, ClassNotFoundException{
		connectDB();
		// Execute an INSERT query
	    String insertQuery = "INSERT INTO stock VALUES(?,?,?,?,?,?,?,?)";
	    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	    
	    preparedStatement.setString(2, action.getActionType());
	    preparedStatement.setString(3, action.getMaterial().getName());
	    preparedStatement.setInt(4, action.getMaterial().getQuantity());
	    preparedStatement.setString(5, action.getDescription());
	    preparedStatement.setString(6, action.getFrom());
	    preparedStatement.setString(7, action.getTo());
	    preparedStatement.setString(8, action.getDate());
	    
		preparedStatement.executeUpdate();
		System.out.println("Inserted successfully.");
	    }
	
	public static void deleteAll() throws SQLException, ClassNotFoundException{
		connectDB();
		
		String insertQuery = "DELETE FROM stock";
	    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	    preparedStatement.executeUpdate();
	    
	    System.out.println("Deleted all successfully.");
	}
	
	public static ArrayList<Action> search(String searchBy) throws ClassNotFoundException, SQLException{
		connectDB();
		ArrayList<Action> data = new ArrayList<Action>();
		String selectQuery = "SELECT * FROM stock WHERE"			
			    + " material LIKE '%" + searchBy + "%'"
			    + " OR quantity LIKE '%" + searchBy + "%'"
			    + " OR description LIKE '%" + searchBy + "%'"
			    + " OR from_ LIKE '%" + searchBy + "%'"
			    + " OR to_ LIKE '%" + searchBy + "%'"
			    + " OR date_ LIKE '%" + searchBy + "%'";

	    PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	    ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet .next()) {
        	Material material = new Material(resultSet.getString("material"), resultSet.getInt("quantity"));
            Action action = new Action (resultSet.getString("idAction"),
            							resultSet.getString("actionType"),
            							material,
            							resultSet.getString("description"),
            							resultSet.getString("from_"),
            							resultSet.getString("to_"),
            							resultSet.getString("date_")
            							);
            data.add(action);
        }
		return data;
	}
	
	public static void deleteAction(ArrayList<String> ids) throws SQLException, ClassNotFoundException{
		connectDB();
		for(String id : ids) {
			String deleteQuery = "DELETE FROM stock WHERE idAction = "+id;
		    PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
		    preparedStatement.executeUpdate();
		    
		    System.out.println("Deleted successfully.");
		}
	}
	
	
}
	
