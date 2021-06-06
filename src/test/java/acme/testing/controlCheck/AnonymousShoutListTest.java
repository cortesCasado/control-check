
package acme.testing.controlCheck;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutListTest extends AcmePlannerTest {

	/**
	 * As an anonymous user, list all the shouts that were created less than
	 * a month ago sorted by date and check that every value is correct.
	 * No errors expected.
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void listRecentShoutsByMoment(final int id, final int version, String moment, final String author, final String text, final String info, final String infoSheet_rareID, final String infoSheet_moment, final String infoSheet_money,
		final String infoSheet_flag) {
		super.clickOnMenu("Anonymous", "List shouts");

		super.checkColumnHasValue(id, 0, moment);
		super.checkColumnHasValue(id, 1, author);
		super.checkColumnHasValue(id, 2, text);
		super.checkColumnHasValue(id, 3, info);
		super.checkColumnHasValue(id, 4, infoSheet_rareID);
		super.checkColumnHasValue(id, 5, infoSheet_moment);
		super.checkColumnHasValue(id, 6, infoSheet_money);
		super.checkColumnHasValue(id, 7, infoSheet_flag);
	}

	/**
	 * Due to is an action with no constraints, there no exists negative test cases fos shout listing
	 */
}
