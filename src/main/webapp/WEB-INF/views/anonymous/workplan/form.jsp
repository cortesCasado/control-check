<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form readonly="true">


	<acme:form-textbox code="anonymous.workplan.form.label.title" path="title"/>
	<acme:form-double code="anonymous.workplan.form.label.workload" path="workload"/>
	<acme:form-moment code="anonymous.workplan.form.label.execution_period_initial_date" path="executionPeriod.initialDate"/>
	<acme:form-moment code="anonymous.workplan.form.label.execution_period_final_date" path="executionPeriod.finalDate"/>
	
	
	<h4>
		<acme:message code="anonymous.workplan.form.label.task"/>
	</h4>	
	<table class="table table-sm">
	<thead>
	<tr>
		<th>
			<acme:message code="anonymous.workplan.list.label.title"/>
		</th>
		<th>
			<acme:message code="anonymous.workplan.list.label.execution_period_initial_date"/>
		</th>
		<th>
			<acme:message code="anonymous.workplan.list.label.execution_period_final_date"/>
		</th>
	</tr>
	</thead>
	<jstl:forEach items = "${task}" var = "t">
	<tr>
		<td>
			<acme:print value="${t.title}"/>
		</td>
		<td>
			<acme:print value="${t.executionPeriod.initialDate}"/>
		</td>
		<td>
			<acme:print value="${t.executionPeriod.finalDate}"/>
		</td>
	</tr>
	</jstl:forEach>
	</table>
	
	<acme:form-return code="anonymous.workplan.form.button.return"/>
</acme:form>