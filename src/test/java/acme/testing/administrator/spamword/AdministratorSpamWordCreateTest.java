package acme.testing.administrator.spamword;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordCreateTest extends AcmePlannerTest{
	
	/**
	 * Sign in as a administrator and create a new spamword.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final String word, final int lastRowIndex) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Customisation Parameters");
		super.clickOnReturnButton("Create new Spam Word");
		
		super.fillInputBoxIn("word", word);
		super.clickOnSubmitButton("Create new Spam Word");
		
		final By newLastRow = By.xpath("//*[@id=\"form\"]/table/tbody/tr[" + lastRowIndex + "]");
		super.checkExists(newLastRow);
		
		super.signOut();
	}
	
	/**
	 * Sign in as a administrator and create a new spamword.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamWord/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final String word) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Customisation Parameters");
		super.clickOnReturnButton("Create new Spam Word");
		
		super.fillInputBoxIn("word", word);
		super.clickOnSubmitButton("Create new Spam Word");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
}
