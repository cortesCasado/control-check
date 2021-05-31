
package acme.testing.workplan;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanDeleteServiceTest extends AcmePlannerTest {

	/**
	 * Sign in as a manager and delete a workplan with tasks associated.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/delete/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void deleteWorkplanPositive(final int id, final int version, final String execution_period_initial_date, final String execution_period_final_date, final Boolean isPrivate, final String title, final int user_account_id, final String workload) {
		super.signIn("Antonio", "Campuzano");
		super.clickOnMenu("Manager", "List workplans");
		super.clickOnListingRecord(id);
		super.clickOnSubmitButton("Delete workplan");
		super.signOut();
	}

	/**
	 * Try to delete a workplan being anonymous.
	 * Panic expected because active role is not manager.
	 */
	@Test
	public void deleteWorkplanThatIDontOwn() {
		super.navigate("/manager/workplan/delete", "id=22");
		super.checkPanicExists();
	}

	/**
	 * Sign in as a manager and try to delete a workplan not owned by the principal.
	 * Panic expected.
	 */
	@Test
	public void deleteAWorkplanThatDoesntExist() {
		super.signIn("Antonio", "Campuzano");
		super.navigate("/manager/workplan/delete", "id=24");
		super.checkPanicExists();
		super.signOut();
	}

}
