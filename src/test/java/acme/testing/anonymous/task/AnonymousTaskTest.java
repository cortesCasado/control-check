package acme.testing.anonymous.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskTest  extends AcmePlannerTest  {

	
		// Test cases -------------------------------------------------------------

		
		/**
		 * Sign in as a anonymous, list all public and non-finished task check that every value is correct.
		 * No errors expected.
		 */
		@ParameterizedTest
		@CsvFileSource(resources = "/task/listAnonymousTask.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		void listMineTaskAnonymousPositive(final int id, final int version,final String description, final String execution_period_final_date
			, final String execution_period_initial_date
			, final Boolean isPrivate, final String title,
			final String url, final String workload,
			final int user_account_id) {
			
			super.clickOnMenu("Anonymous", "List tasks");
			
			super.checkColumnHasValue(id, 0, title);
			super.checkColumnHasValue(id, 1, execution_period_initial_date);
			super.checkColumnHasValue(id, 2, execution_period_final_date);

			
		}
		
}
