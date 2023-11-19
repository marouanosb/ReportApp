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
            connection = DriverManager.getConnection("jdbc:sqlite:bdd.db");

    }

    public static void initialiseDB() throws SQLException, ClassNotFoundException {
    	connectDB();
        String createTable = "CREATE TABLE IF NOT EXISTS stock("
        					+ "actionType TEXT,"
        					+ "material TEXT,"
        					+ "quantity INTEGER,"
        					+ "description TEXT,"
        					+ "from_ TEXT,"
        					+ "to_ TEXT,"
        					+ "date_ TEXT)";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }
 
	public static ArrayList<Action> getAll(String orderBy) throws SQLException, ClassNotFoundException{
		ArrayList<Action> data = new ArrayList<Action>();
		connectDB();
		// Execute a SELECT query
	    String selectQuery = "SELECT * FROM stock ORDER BY "+orderBy;
	    PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	    ResultSet resultSet = preparedStatement.executeQuery();
	        // Process the result set
	        while (resultSet.next()) {
	        	Material material = new Material(resultSet.getString("material"), resultSet.getInt("quantity"));
	            Action action = new Action (resultSet.getString("actionType"),
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
	
	public static void insert(Action action) throws SQLException, ClassNotFoundException{
		connectDB();
		// Execute an INSERT query
	    String insertQuery = "INSERT INTO stock VALUES(?,?,?,?,?,?,?)";
	    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	    
	    preparedStatement.setString(1, action.getActionType());
	    preparedStatement.setString(2, action.getMaterial().getName());
	    preparedStatement.setInt(3, action.getMaterial().getQuantity());
	    preparedStatement.setString(4, action.getDescription());
	    preparedStatement.setString(5, action.getFrom());
	    preparedStatement.setString(6, action.getTo());
	    preparedStatement.setString(7, action.getDate());
	    
		preparedStatement.executeUpdate();
	    }
	
	public static void deleteAll() throws SQLException, ClassNotFoundException{
		connectDB();
		
		String insertQuery = "DELETE * FROM stock";
	    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	    preparedStatement.executeUpdate();
	}
	
	
}
	
