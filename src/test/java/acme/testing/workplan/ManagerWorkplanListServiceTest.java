package acme.testing.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.features.manager.workplan.ManagerWorkplanRepository;
import acme.testing.AcmePlannerTest;

public class ManagerWorkplanListServiceTest extends AcmePlannerTest {

	// Internal state ---------------------------------------------------------

	@Autowired
	ManagerWorkplanRepository managerWorkplanRepository;
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	
	/**
	 * Sign in as a manager, list all my workplans and check that every value is correct.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/list/list-mine-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	void listMineWorkplanManagerPositive(final int id, final int version, final String execution_period_initial_date, final String execution_period_final_date, final Boolean isPrivate, final String title,
		final int user_account_id, final String workload) {
		super.signIn("Antonio", "Campuzano");
		
		super.clickOnMenu("Manager", "List workplans");
		
		super.checkColumnHasValue(id, 0, title);
		super.checkColumnHasValue(id, 1, execution_period_final_date);
		super.checkColumnHasValue(id, 2, execution_period_initial_date);
		super.checkColumnHasValue(id, 3, workload);

		super.signOut();
	}
	
	/**
	 * Try to list workplans as an anonymous principal.
	 * Panic expected.
	 */
	@Test
	public void listMineNegative() {
			super.navigate("/manager/workplan/list", "");
			super.checkPanicExists();
	}
	
	// Ancillary methods ------------------------------------------------------

}
