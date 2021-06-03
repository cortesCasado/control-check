package acme.testing.anonymous.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousWorkPlanListServiceWorkLoadTest extends AcmePlannerTest{

	/**
	 * Click on Anonymous- List workplans by Execution Period,
	 * List the Public Workplans not finished yet, 
	 * ordered by Workload (from long to short)
	 * and check that the order is correct.
	 * No errors expected.
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/workplan/ListWorkPlanWorkloadPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void ListarWorkPlanWorkLoad(final int id,  
		final String execution_period_final_date, final String execution_period_initial_date,
		final String title, final String workload) {
		
		super.clickOnMenu("Anonymous", "List workplans by Workload");
		
		super.checkColumnHasValue(id, 0, title);
		super.checkColumnHasValue(id, 1, execution_period_final_date);
		super.checkColumnHasValue(id, 2, execution_period_initial_date);
		super.checkColumnHasValue(id, 3, workload);

		
	}
}
