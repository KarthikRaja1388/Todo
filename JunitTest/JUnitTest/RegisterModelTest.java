package JUnitTest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import application.RegisterModel;

public class RegisterModelTest {

	RegisterModel model = new RegisterModel();
	@Test
	public void isRegisterTest() {
		assertTrue(model.isRegister("testFN", "testLN", "karthik.vbdon88@gmail.com", "021987740", "1988-03-01", "testUn", "testPW", "testPW"));
	}

	@Test
	public void isNameValidatedTest(){
		assertTrue(RegisterModel.isNameValidated("Karthik", "Rajendran"));
	}
	
	@Test
	public void isMailValidatedTest(){
		assertTrue(RegisterModel.isMailValidated("karthik.vbdon@gmail.com"));
	}
	
	@Test
	public void isPhoneValidatedTes(){
		assertTrue(RegisterModel.isPhoneValidated("021987740"));
	}
	
	@Test
	public void isUserNameValidatedTest(){
		try {
			assertTrue(RegisterModel.isUserNameValidated("test1"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void isPasswordMatchesTest(){
		assertFalse(RegisterModel.isPwdMatches("test", "test", "test"));
	}
	
	@Test
	public void isDateValidatedTest(){
		assertTrue(RegisterModel.isDateValidated("01-03-2016"));
	}
}
