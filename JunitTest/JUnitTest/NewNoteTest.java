package JUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import application.NewNoteModel;

public class NewNoteTest {

	NewNoteModel noteModel = new NewNoteModel();
	@Test
	public void addNoteTest() {
		assertTrue(noteModel.addNote("user"," note"," category", "dateAdded", "dueDate", "time", "priority"));
	}

}
