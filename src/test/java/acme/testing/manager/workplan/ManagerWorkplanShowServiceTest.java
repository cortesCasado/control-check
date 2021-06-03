package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanShowServiceTest extends AcmePlannerTest {

	/**
	 * Sign in as a manager, list all my workplans, show one
	 * and check that every value is right.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/show/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	void showWorkplanManagerPositive(final int id, final int version, final String execution_period_final_date, final String execution_period_initial_date, final Boolean isPrivate, final String title,
		final int user_account_id, final String workload) {
		super.signIn("Antonio", "Campuzano");
		
		super.clickOnMenu("Manager", "List workplans");

		super.checkColumnHasValue(id, 0, title);
		super.checkColumnHasValue(id, 1, execution_period_final_date);
		super.checkColumnHasValue(id, 2, execution_period_initial_date);
		super.checkColumnHasValue(id, 3, workload);
		
		super.clickOnListingRecord(id);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("executionPeriod.initialDate", execution_period_initial_date);
		super.checkInputBoxHasValue("executionPeriod.finalDate", execution_period_final_date);

		super.signOut();
	}
	
	/**
	 * Signs in as a manager, tries to show a non existing workplan and expect a panic.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/show/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	void showWorkplanManagerNegative(final int id, final int version, final String execution_period_initial_date, final String execution_period_final_date, final Boolean isPrivate, final String title,
		final int user_account_id, final String workload) {
		super.signIn("Antonio", "Campuzano");
		
		super.navigate("/manager/workplan/show", "id=" + id);
		super.checkPanicExists();
		
		super.signOut();
	}

}
