package repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class DatabaseCreation {
	 String path = System.getProperty("user.dir")+"/database";
	
	public static void createDB() throws SQLException, ClassNotFoundException, IOException {
		// Define the name of the SQLite database file
        String databaseName = "path"+"/bdd.db";

        // Check if the database file already exists
        File databaseFile = new File(databaseName);
        
        if (databaseFile.exists()) {
            System.out.println("Database already exists.");
        } else {
        	Files.createDirectories(Paths.get(System.getProperty("user.dir")+"/database"));
            // Create the SQLite database
            DatabaseService.initialiseDB();
            System.out.println("Database created successfully.");
        }
	}
}
