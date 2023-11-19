package models;

import java.sql.Date;

public class Action {
	private String actionType;
	private Material material;
	private String description;
	private String from;
	private String to;
	private String date;
	
	public Action(String actionType, Material material, String description, String from, String to, String date) {
		this.actionType = actionType;
		this.material = material;
		this.description = description;
		this.from = from;
		this.to = to;
		this.date = date;
		
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
