package JUnitTest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import application.ForgotPasswordModel;

public class ForgotPasswordTest {

	ForgotPasswordModel forgotModel = new ForgotPasswordModel();
	@Test
	public void isEmailAvailableTest() throws SQLException {
		assertTrue(forgotModel.isEmailAvailable("karthik.vbdon88@gmail.com"));
	}

	@Test
	public void isCodeValidatedTest(){
		assertTrue(ForgotPasswordModel.isCodeValidted(123, 123));
	}
	
	

	
}
