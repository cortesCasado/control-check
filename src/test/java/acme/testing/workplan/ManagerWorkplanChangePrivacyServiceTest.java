package acme.testing.workplan;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanChangePrivacyServiceTest extends AcmePlannerTest {

	/**
	 * Sign in as a manager and change the privacy of a work plan from public to private correctly
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/changePrivacy/change-privacy-to-private-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void changeWorkplantoPrivatePositive(final int id, final int version, final String execution_period_initial_date, final String execution_period_final_date, final Boolean isPrivate, final String title,
		final int user_account_id, final String workload) {
		super.signIn("Antonio", "Campuzano");

		super.clickOnMenu("Manager", "List workplans");

		super.clickOnListingRecord(id);

		super.clickOnSubmitButton("Change privacy to private");

		super.signOut();
	}
	
	/**
	 * Sign in as a manager and change the privacy of a work plan from private to public correctly
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/changePrivacy/change-privacy-to-public-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void changeWorkplanToPublicPositive(final int id, final int version, final String execution_period_initial_date, final String execution_period_final_date, final Boolean isPrivate, final String title,
		final int user_account_id, final String workload) {
		super.signIn("Sergio", "Rojas");

		super.clickOnMenu("Manager", "List workplans");

		super.clickOnListingRecord(id);

		super.clickOnSubmitButton("Change privacy to public");

		super.signOut();
	}
	
	/**
	 * Sign in as a manager and try to change a private work plan with private tasks to
	 * public showing errors 
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/changePrivacy/change-privacy-to-public-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void changeWorkplanToPublicNegative(final int id, final int version, final String execution_period_initial_date, final String execution_period_final_date, final Boolean isPrivate, final String title,
		final int user_account_id, final String workload) {
		super.signIn("Antonio", "Campuzano");

		super.clickOnMenu("Manager", "List workplans");

		super.clickOnListingRecord(id);

		super.clickOnSubmitButton("Change privacy to public");
		
		super.checkErrorsExist();

		super.signOut();
	}

}
