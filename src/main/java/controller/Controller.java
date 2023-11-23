package controller;

import repository.DatabaseService;
import repository.DatabaseCreation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Action;

public class Controller  {
	
	public void createDB() throws ClassNotFoundException, SQLException, IOException {
		DatabaseCreation.createDB();
	}
	
	public void insert(Action action)  throws ClassNotFoundException, SQLException{
		DatabaseService.insert(action);
	}
	
	public void deleteAll() throws ClassNotFoundException, SQLException{
		DatabaseService.deleteAll();
	}
	
	public ArrayList<Action> getAll(String orderBy) throws ClassNotFoundException, SQLException {
		orderBy = orderBy.toLowerCase();
		
		if (orderBy.equals("date"))	orderBy = "date_";
		if (orderBy.equals("action")) orderBy = "actionType";
		
		return DatabaseService.getAll(orderBy);
	}
	
	public ArrayList<Action> search(String searchBy) throws ClassNotFoundException, SQLException {
		searchBy = searchBy.toLowerCase();
		return DatabaseService.search(searchBy);
	}
	
	public void deleteAction(ArrayList<String> ids) throws ClassNotFoundException, SQLException{
		DatabaseService.deleteAction(ids);
	}
	
}
