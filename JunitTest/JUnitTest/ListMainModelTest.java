package JUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import application.ListMainModel;

public class ListMainModelTest {

	ListMainModel listModel = new ListMainModel();
/*	Notes note = new Notes("userName", "notes", "category", "dateAdded", "dueDate", "time", "priority", 1);
*/	
/*	private ObservableList<Notes>data = FXCollections.observableArrayList();
*/	@Test
	public void deleteDataTest() {
		assertTrue(listModel.deleteData(16));
	}
	
	/*@Test
	public void loadTableTest() {
		assertTrue(listModel.loadTableData(ObservableList<Notes>data, TableView<Notes>note));
	}*/
	
	

}
