
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
	public void createPositive(final int id, final int version, final String author, final String text, final String info, 
		final String receipt_referenciaEx, final String receipt_deadlineEx, final String receipt_totalPriceEx, 
		final String receipt_paidEx) {
		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", info);
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis() - 1));
		String referenciaEx = AnonymousShoutCreateService.getReferenciaExRegExp(c, "-") + " " + receipt_referenciaEx;
		
		super.fillInputBoxIn("receipt.referenciaEx", referenciaEx);
//		super.fillInputBoxIn("receipt.deadlineEx", receipt_deadlineEx);
		super.fillInputBoxIn("receipt.totalPriceEx", receipt_totalPriceEx);
		super.fillInputBoxIn("receipt.paidEx", receipt_paidEx);

		super.clickOnSubmitButton("Shout!");

		super.clickOnMenu("Anonymous", "List shouts");

		super.checkColumnHasValue(id, 1, author);
		super.checkColumnHasValue(id, 2, text);
		super.checkColumnHasValue(id, 3, info);
		super.checkColumnHasValue(id, 4, referenciaEx);
//		super.checkColumnHasValue(id, 5, receipt_deadlineEx);
		super.checkColumnHasValue(id, 6, receipt_totalPriceEx);
		super.checkColumnHasValue(id, 7, receipt_paidEx);

	}

	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created
	 * because it have errors, finish.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void createNegative(final int id, final int version, final String author, final String text, final String info, 
		final String receipt_referenciaEx, final String receipt_deadlineEx, final String receipt_totalPriceEx, 
		final String receipt_paidEx) {
		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", info);
		super.fillInputBoxIn("receipt.referenciaEx", receipt_referenciaEx);
//		super.fillInputBoxIn("receipt.deadlineEx", receipt_deadlineEx);
		super.fillInputBoxIn("receipt.totalPriceEx", receipt_totalPriceEx);
		super.fillInputBoxIn("receipt.paidEx", receipt_paidEx);

		super.clickOnSubmitButton("Shout!");

		super.checkErrorsExist("author");
		super.checkErrorsExist("text");
		super.checkErrorsExist("link");
		super.checkErrorsExist("receipt.referenciaEx");
//		super.checkErrorsExist("receipt.deadlineEx");
		super.checkErrorsExist("receipt.totalPriceEx");

	}
}
