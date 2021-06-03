package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamUpdateTest extends AcmePlannerTest{
	
	/**
	 * Sign in as a administrator and update the threshold value.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final String threshold) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Customisation Parameters");
		
		super.fillInputBoxIn("threshold", threshold);
		super.clickOnSubmitButton("Update");

		super.checkInputBoxHasValue("threshold", threshold);
		
		super.signOut();
	}
	
	/**
	 * Sign in as a administrator and update the threshold value with invalid values.
	 * Errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateNegative(final String threshold) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Customisation Parameters");
		
		super.fillInputBoxIn("threshold", threshold);
		super.clickOnSubmitButton("Update");

		super.checkErrorsExist();
		
		super.signOut();
	}
	
}
