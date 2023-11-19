package repository;

import java.io.File;
import java.sql.SQLException;

public class DatabaseCreation {
	
	public static void createDB() throws SQLException, ClassNotFoundException {
		// Define the name of the SQLite database file
        String databaseName = "bdd.db";

        // Check if the database file already exists
        File databaseFile = new File(databaseName);
        
        if (databaseFile.exists()) {
            System.out.println("Database already exists.");
        } else {
            // Create the SQLite database
            DatabaseService.initialiseDB();
            System.out.println("Database created successfully.");
        }
	}
}
