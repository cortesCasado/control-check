package acme.testing.manager.task;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateServiceTest extends AcmePlannerTest {

	// Test cases -------------------------------------------------------------

	
	/**
	 * Sign in as a manager, create a task, list all my task, show the recently created one
	 * and check that every value is correct.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/task/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	void createTaskManagerPositive(final int id, final int version,final String description, final String execution_period_initial_date
		, final String execution_period_final_date
		, final Boolean isPrivate, final String title,
		final String url, final String workload,
		final int user_account_id){
		super.signIn("Antonio", "Campuzano");
		
		super.clickOnMenu("Manager", "Create tasks");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("url", url);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("executionPeriod.initialDate", execution_period_initial_date);
		super.fillInputBoxIn("executionPeriod.finalDate", execution_period_final_date);
		super.clickOnSubmitButton("Create");
		

		super.clickOnMenu("Manager", "List tasks");

		super.checkColumnHasValue(id, 0, title);
		super.checkColumnHasValue(id, 1, execution_period_final_date);
		super.checkColumnHasValue(id, 2, execution_period_initial_date);
		
		super.clickOnListingRecord(10);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("executionPeriod.initialDate", execution_period_initial_date);
		super.checkInputBoxHasValue("executionPeriod.finalDate", execution_period_final_date);

		super.signOut();
	
	}
	
	/**
	 * Sign in as a manager, create a task with negative workload.
	 * Workload error expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/task/createNegativeWorkload.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	void createTaskManagerNegativeWorkload(final int id, final int version,final String description, final String execution_period_initial_date
		, final String execution_period_final_date
		, final Boolean isPrivate, final String title,
		final String url, final String workload,
		final int user_account_id){
		super.signIn("Antonio", "Campuzano");
		
		super.clickOnMenu("Manager", "Create tasks");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("url", url);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("executionPeriod.initialDate", execution_period_initial_date);
		super.fillInputBoxIn("executionPeriod.finalDate", execution_period_final_date);
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	
	}
	
	/**
	 * Sign in as a manager, create a task with more workload hours than the hours between initial
	 * and final date.
	 * Workload error expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/task/createNegativeUpperWorkload.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	void createTaskManagerNegativeUpperWorkload(final int id, final int version,final String description, final String execution_period_initial_date
		, final String execution_period_final_date
		, final Boolean isPrivate, final String title,
		final String url, final String workload,
		final int user_account_id){
		super.signIn("Antonio", "Campuzano");
		
		super.clickOnMenu("Manager", "Create tasks");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("url", url);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("executionPeriod.initialDate", execution_period_final_date);
		super.fillInputBoxIn("executionPeriod.finalDate", execution_period_initial_date);
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	
	}

	/**
	 * Sign in as a manager, create a task with spam title and description.
	 * Title and description errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/task/createNegativeSpamText.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	void createTaskManagerNegativeSpam(final int id, final int version,final String description, final String execution_period_initial_date
		, final String execution_period_final_date
		, final Boolean isPrivate, final String title,
		final String url, final String workload,
		final int user_account_id){
		super.signIn("Antonio", "Campuzano");
		
		super.clickOnMenu("Manager", "Create tasks");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("url", url);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("executionPeriod.initialDate", execution_period_initial_date);
		super.fillInputBoxIn("executionPeriod.finalDate", execution_period_final_date);
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	
	}

	
	/**
	 * Sign in as a manager, create a task with a final date before than initial date.
	 *  final date error expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/task/createNegativeFinalDate.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	void createTaskManagerNegativeFinalDate(final int id, final int version,final String description, final String execution_period_initial_date
		, final String execution_period_final_date
		, final Boolean isPrivate, final String title,
		final String url, final String workload,
		final int user_account_id){
		super.signIn("Antonio", "Campuzano");
		
		super.clickOnMenu("Manager", "Create tasks");
		
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("url", url);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("executionPeriod.initialDate", execution_period_initial_date);
		super.fillInputBoxIn("executionPeriod.finalDate", execution_period_final_date);
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	
	}
	
	/**
	 * Sign in as a manager, create a task with a initial date before than actual date.
	 *  initial date error expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/task/createNegativeInitialDate.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	void createTaskManagerNegativeInitialDate(final int id, final int version,final String description, final String execution_period_initial_date
		, final String execution_period_final_date
		, final Boolean isPrivate, final String title,
		final String url, final String workload,
		final int user_account_id){
		super.signIn("Antonio", "Campuzano");
		
		super.clickOnMenu("Manager", "Create tasks");
		
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("url", url);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("executionPeriod.initialDate", execution_period_initial_date);
		super.fillInputBoxIn("executionPeriod.finalDate",execution_period_final_date );
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	
	}
}
