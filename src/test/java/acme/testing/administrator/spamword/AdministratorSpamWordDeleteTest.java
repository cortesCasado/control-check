package acme.testing.administrator.spamword;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordDeleteTest extends AcmePlannerTest{
	
	/**
	 * Sign in as a administrator and delete a spamword.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/deletePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive(final int id, final int lastRowIndex) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Customisation Parameters");
		
		final By deleteButton = By.xpath("//*[@id=\"form\"]/table/tbody/tr["+ id + "]/td[2]/button");
		super.clickAndWait(deleteButton);
		
		final By lastRow = By.xpath("//*[@id=\"form\"]/table/tbody/tr[" + lastRowIndex + "]");
		super.checkNotExists(lastRow);
		
		super.signOut();
	}
	
	/**
	 * Sign in as a administrator and try to delete a spamword.
	 * Panic expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/deleteNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void deleteNegative(final int id) {
		super.signIn("administrator", "administrator");
		
		super.navigate("/administrator/spamword/delete", "id=" + id);
		
		super.checkPanicExists();
		
		super.signOut();
	}
	
}
