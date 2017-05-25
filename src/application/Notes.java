package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Notes {
	
	private final SimpleStringProperty userName;
	private final SimpleStringProperty notes;
	private final SimpleStringProperty category;
	private final SimpleStringProperty dateAdded;
	private final SimpleStringProperty dueDate;
	private final SimpleStringProperty time;
	private final SimpleStringProperty priority;
	private final SimpleIntegerProperty id;
	
	public Notes(String userName,String notes, String category, String dateAdded, String dueDate, String time, String priority,int id) {
		super();
		this.userName = new SimpleStringProperty(userName);
		this.notes = new SimpleStringProperty(notes);
		this.category = new SimpleStringProperty(category);
		this.dateAdded = new SimpleStringProperty(dateAdded);
		this.dueDate = new SimpleStringProperty(dueDate);
		this.time = new SimpleStringProperty(time);
		this.priority = new SimpleStringProperty(priority);
		this.id = new SimpleIntegerProperty(id);
	}


	public int getId() {
		return id.get();
	}


	public String getUserName() {
		return userName.get();
	}


	public String getNotes() {
		return notes.get();
	}

	public String getCategory() {
		return category.get();
	}

	public String getDateAdded() {
		return dateAdded.get();
	}

	public String getDueDate() {
		return dueDate.get();
	}

	public String getTime() {
		return time.get();
	}

	public String getPriority() {
		return priority.get();
	}

//Setters

	public void setUser(String user){
		userName.set(user);
	}
	public void setNote(String note){
		notes.set(note);
	}
	
	public void setCategory(String categories){
		category.set(categories);
	}
	
	public void setDateAdded(String addDate){
		dateAdded.set(addDate);
	}
	
	public void setDueDate(String due){
		dueDate.set(due);
	}
	
	public void setTime(String timer){
		time.set(timer);
	}
	
	public void setPriority(String priorities){
		priority.set(priorities);
	}
	
	public void setId(int Id){
		id.set(Id);
	}
}
