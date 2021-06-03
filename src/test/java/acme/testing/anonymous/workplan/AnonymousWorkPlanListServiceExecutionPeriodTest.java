package acme.testing.anonymous.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousWorkPlanListServiceExecutionPeriodTest extends AcmePlannerTest{

	
	/**
	 * Click on Anonymous- List workplans by Execution Period,
	 *  List the Public Workplans not finished yet, 
	 *  ordered by Execution Period ( from closed to distant date)
	 * and check that the order is correct.
	 * No errors expected.
	 */
	 
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/workplan/ListWorkPlanExecutionPeriod.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void ListarWorkPlanExecutionPeriod(final int id,  
		final String execution_period_final_date, final String execution_period_initial_date,
		final String title, final String workload) {
		
		super.clickOnMenu("Anonymous", "List workplans by Execution Period");
		
		super.checkColumnHasValue(id, 0, title);
		super.checkColumnHasValue(id, 1, execution_period_final_date);
		super.checkColumnHasValue(id, 2, execution_period_initial_date);
		super.checkColumnHasValue(id, 3, workload);

		
	}
}
