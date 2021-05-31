package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{

	
	/**
	 * Click on Anonymous - Shout!, create a shout, list all shouts, show the recently created one
	 * and check that every value is correct.
	 * No errors expected.
	 */
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final String author, final String text, final String link) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmitButton("Shout!");
		
		super.clickOnMenu("Anonymous", "List shouts");
		
		super.checkColumnHasValue(0, 1, author);
		super.checkColumnHasValue(0, 2, text);
		super.checkColumnHasValue(0, 3, link);
		
	}
	
	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created 
	 * because it have errors, finish.
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative( final String author, final String text, final String link) {
		
	super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
	}
}
