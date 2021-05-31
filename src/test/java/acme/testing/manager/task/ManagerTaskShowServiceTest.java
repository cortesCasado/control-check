package acme.testing.manager.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmePlannerTest;

public class ManagerTaskShowServiceTest extends AcmePlannerTest {

	// Test cases -------------------------------------------------------------

	
	/**
	 * Sign in as a manager, create a task, list all my task, show the recently created one
	 * and check that every value is correct.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/task/showPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	void showTaskManagerPositive(final int id, final int version,final String description, final String execution_period_initial_date
		, final String execution_period_final_date
		, final Boolean isPrivate, final String title,
		final String url, final String workload,
		final int user_account_id) {
			super.signIn("Antonio", "Campuzano");
			
			super.clickOnMenu("Manager", "List tasks");

			super.checkColumnHasValue(id, 0, title);
			super.checkColumnHasValue(id, 1, execution_period_final_date);
			super.checkColumnHasValue(id, 2, execution_period_initial_date);
			
			super.clickOnListingRecord(id);

			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("executionPeriod.initialDate", execution_period_initial_date);
			super.checkInputBoxHasValue("executionPeriod.finalDate", execution_period_final_date);

			super.signOut();
		}
	
	
	/**
	 * Signs in as a manager, try to show a task that is not yours and wait for a panic.
	 */
	@Test
	@Order(20)
	void showTaskManagerNegative() {
		
	super.signIn("Antonio", "Campuzano");
	super.navigate("/manager/task/show", "id=21");
	super.checkErrorsExist();
	super.signOut();
		
	}
	
	/**
	 * Signs in as a manager, tries to show a  non existing task and expect a panic.
	 */
	@Test
	@Order(20)
	void showTaskManagerNegativeNonExist() {
		
	super.signIn("Antonio", "Campuzano");
	super.navigate("/manager/task/show", "id=245");
	super.checkErrorsExist();
	super.signOut();
		
	}
	
	
}
