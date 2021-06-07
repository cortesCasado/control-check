
package acme.testing.controlCheck;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.features.anonymous.shout.AnonymousShoutCreateService;
import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest {

	/**
	 * Click on Anonymous - Shout!, create a shout, list all shouts, show the recently created one
	 * and check that every value is correct.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void createPositive(final int id, final int version, final String author, final String text, final String info, final String infoSheet_reference, final String infoSheet_moment, final String infoSheet_money, final String infoSheet_flag) {
		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", info);
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis() - 1));
		String reference = AnonymousShoutCreateService.getReferenceRegExp(c, "-") + " " + infoSheet_reference;
		
		super.fillInputBoxIn("receipt.reference", reference);
//		super.fillInputBoxIn("receipt.deadline", infoSheet_moment);
		super.fillInputBoxIn("receipt.totalPrice", infoSheet_money);
		super.fillInputBoxIn("receipt.paid", infoSheet_flag);

		super.clickOnSubmitButton("Shout!");

		super.clickOnMenu("Anonymous", "List shouts");

		super.checkColumnHasValue(id, 1, author);
		super.checkColumnHasValue(id, 2, text);
		super.checkColumnHasValue(id, 3, info);
		super.checkColumnHasValue(id, 4, reference);
//		super.checkColumnHasValue(id, 5, infoSheet_moment);
		super.checkColumnHasValue(id, 6, infoSheet_money);
		super.checkColumnHasValue(id, 7, infoSheet_flag);

	}

	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created
	 * because it have errors, finish.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void createNegative(final int id, final int version, final String author, final String text, final String info, final String infoSheet_reference, final String infoSheet_moment, final String infoSheet_money, final String infoSheet_flag) {
		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", info);
		super.fillInputBoxIn("receipt.reference", infoSheet_reference);
//		super.fillInputBoxIn("receipt.deadline", infoSheet_moment);
		super.fillInputBoxIn("receipt.totalPrice", infoSheet_money);
		super.fillInputBoxIn("receipt.paid", infoSheet_flag);

		super.clickOnSubmitButton("Shout!");

		super.checkErrorsExist("author");
		super.checkErrorsExist("text");
		super.checkErrorsExist("link");
		super.checkErrorsExist("receipt.reference");
//		super.checkErrorsExist("receipt.deadline");
		super.checkErrorsExist("receipt.totalPrice");

	}
}