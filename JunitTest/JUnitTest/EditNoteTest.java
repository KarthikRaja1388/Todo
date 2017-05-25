package JUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import application.EditNoteModel;

public class EditNoteTest {

	EditNoteModel editModel = new EditNoteModel();
	@Test
	public void isNoteSavedTest() {
		assertTrue(editModel.isNoteSaved("note"," category", "dateAdded", "dueDate", "time", "priority", "id"));
	}

}
