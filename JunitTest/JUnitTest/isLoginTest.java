package JUnitTest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import application.LoginModel;

public class isLoginTest {

	@Test
	public void isLoginTested() {
		LoginModel loginModel = new LoginModel();
		try {
			
			assertTrue(loginModel.isLogin("saran", "saran123"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
