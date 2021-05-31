package acme.testing.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanDeleteTaskServiceTest extends AcmePlannerTest {

	/**
	 * Sign in as a manager, list all my workplans, show the first one,
	 * delete the first task associated to it and check that the workload
	 * has been updated succesfully.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/deleteTaskFromWorkplan/delete-task-from-workplan-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	void deleteTaskFromWorkplanManagerPositive(final int id, final int version, final String execution_period_final_date, final String execution_period_initial_date, final Boolean isPrivate, final String title,
		final int user_account_id, final String workload, final Double workloadDiff) {
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

		final By locator = By.xpath("//*[@id=\"form\"]/table/tbody/tr[2]/td[5]/button");
		super.clickAndWait(locator);
		
		super.checkColumnHasValue(id, 0, title);
		super.checkColumnHasValue(id, 1, execution_period_final_date);
		super.checkColumnHasValue(id, 2, execution_period_initial_date);
		super.checkColumnHasValue(id, 3, String.format("%.2f", Double.parseDouble(workload) - workloadDiff).replace(",", "."));
		
		super.signOut();
	}
	
	/**
	 * Sign in as a manager and try to delete a task from a workplan that I do not own.
	 * Panic is expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/deleteTaskFromWorkplan/delete-task-from-workplan-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	void deleteTaskFromWorkplanManagerNegative(final int id, final int version, final String execution_period_final_date, final String execution_period_initial_date, final Boolean isPrivate, final String title,
		final int user_account_id, final String workload, final Double workloadDiff) {
		super.signIn("Sergio", "Rojas");
		
		super.navigate("/manager/workplan/delete_task_workplan", "workplanId=22&taskId=34");
		super.checkPanicExists();
		
		super.signOut();
	}
	
}
