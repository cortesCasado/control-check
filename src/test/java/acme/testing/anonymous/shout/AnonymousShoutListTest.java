package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutListTest extends AcmePlannerTest{
	
	
	/**
	    * As an anonymous user, list all the shouts that were created less than 
	    * a month ago sorted by date and check that every value is correct.
	    * No errors expected.
	    */
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listRecentShoutsByMoment(final int recordIndex, final String author, final String link, final String moment, final String title) {
		super.clickOnMenu("Anonymous", "List shouts");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, title);
		super.checkColumnHasValue(recordIndex, 3, link);
	}
	
}
