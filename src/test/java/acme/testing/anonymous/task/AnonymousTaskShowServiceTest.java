package acme.testing.anonymous.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskShowServiceTest extends AcmePlannerTest {

	// Test cases -------------------------------------------------------------

	
	/**
	 * Sign in as a Anonymous list and show all public and non-finished task
	 * and check that every value is correct.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/task/showPositiveAnonymous.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	void ShowTaskAnonymousPositive(final int id, final int version,final String description, final String execution_period_final_date
		, final String execution_period_initial_date
		, final Boolean isPrivate, final String title,
		final String url, final String workload,
		final int user_account_id) {
			
			super.clickOnMenu("Anonymous", "List tasks");

			super.checkColumnHasValue(id, 0, title);
			super.checkColumnHasValue(id, 2, execution_period_final_date);
			super.checkColumnHasValue(id, 1, execution_period_initial_date);
			
			super.clickOnListingRecord(id);

			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("executionPeriod.initialDate", execution_period_initial_date);
			super.checkInputBoxHasValue("executionPeriod.finalDate", execution_period_final_date);

		}
	
	/**
	 * Signs in as a Anonymous, tries to show an unfinished task and expect a panic.
	 */
	@Test
	@Order(20)
	void showTaskAnonymousNegativeFinished() {
		
	
	super.navigate("/anonymous/task/show", "id=26");
	super.checkErrorsExist();

		
	}
	
	/**
	 * Signs in as a Anonymous, tries to show a non non existing task and expect a panic.
	 */
	@Test
	@Order(20)
	void showTaskAnonymousNegativeNonExist() {
		

	super.navigate("/anonymous/task/show", "id=245");
	super.checkErrorsExist();
	
		
	}
	
	
	
}
