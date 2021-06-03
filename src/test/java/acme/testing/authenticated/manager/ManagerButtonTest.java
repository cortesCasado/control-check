package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class ManagerButtonTest extends AcmePlannerTest{
	
	@Test
	@Order(10)
	public void ManagerButton() {
		
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Account", "Become a manager");
		
		super.clickOnSubmitButton("Confirm");
		
		final By menuManager = By.xpath("//*[@id=\"mainMenu\"]/ul[1]/li[3]");
		super.checkExists(menuManager);
	}

}
