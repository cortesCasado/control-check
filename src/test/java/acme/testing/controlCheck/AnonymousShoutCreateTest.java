
package acme.testing.controlCheck;

import java.util.Date;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.entities.receiptEx.ReceiptEx;
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
		final String receiptEx_referenciaEx, final String receiptEx_deadlineEx, final String receiptEx_totalPriceEx, 
		final String receiptEx_paidEx) {
		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", info);
		
		
		Date d = new Date(System.currentTimeMillis() - 1);
		String referenciaEx = ReceiptEx.getReferenciaExRegExp(d, receiptEx_paidEx) + " " + receiptEx_referenciaEx;
		
		super.fillInputBoxIn("receiptEx.referenciaEx", referenciaEx);
//		super.fillInputBoxIn("receiptEx.deadlineEx", receiptEx_deadlineEx);
		super.fillInputBoxIn("receiptEx.totalPriceEx", receiptEx_totalPriceEx);
		super.fillInputBoxIn("receiptEx.paidEx", receiptEx_paidEx);

		super.clickOnSubmitButton("Shout!");

		super.clickOnMenu("Anonymous", "List shouts");

		super.checkColumnHasValue(id, 1, author);
		super.checkColumnHasValue(id, 2, text);
		super.checkColumnHasValue(id, 3, info);
		super.checkColumnHasValue(id, 4, referenciaEx);
//		super.checkColumnHasValue(id, 5, receiptEx_deadlineEx);
		super.checkColumnHasValue(id, 6, receiptEx_totalPriceEx);
		super.checkColumnHasValue(id, 7, receiptEx_paidEx);

	}

	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created
	 * because it have errors, finish.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void createNegative(final int id, final int version, final String author, final String text, final String info, 
		final String receiptEx_referenciaEx, final String receiptEx_deadlineEx, final String receiptEx_totalPriceEx, 
		final String receiptEx_paidEx) {
		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", info);
		super.fillInputBoxIn("receiptEx.referenciaEx", receiptEx_referenciaEx);
//		super.fillInputBoxIn("receiptEx.deadlineEx", receiptEx_deadlineEx);
		super.fillInputBoxIn("receiptEx.totalPriceEx", receiptEx_totalPriceEx);
		super.fillInputBoxIn("receiptEx.paidEx", receiptEx_paidEx);

		super.clickOnSubmitButton("Shout!");

		super.checkErrorsExist("author");
		super.checkErrorsExist("text");
		super.checkErrorsExist("link");
		super.checkErrorsExist("receiptEx.referenciaEx");
//		super.checkErrorsExist("receiptEx.deadlineEx");
		super.checkErrorsExist("receiptEx.totalPriceEx");

	}
}
