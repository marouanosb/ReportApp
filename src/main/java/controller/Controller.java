package controller;

import repository.DatabaseService;
import repository.DatabaseCreation;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Action;

public class Controller  {
	
	public void createDB() throws ClassNotFoundException, SQLException {
		DatabaseCreation.createDB();
	}
	
	public void insert(Action action)  throws ClassNotFoundException, SQLException{
		DatabaseService.insert(action);
	}
	
	public void deleteAll() throws ClassNotFoundException, SQLException{
		DatabaseService.deleteAll();
	}
	
	public ArrayList<Action> getAll(String orderBy) throws ClassNotFoundException, SQLException {
		if (orderBy == "date")	orderBy += "_"; //in database its name is date_ not date due to conflictions
		
		return DatabaseService.getAll(orderBy);
	}
	
}
