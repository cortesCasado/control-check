<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form>


	<acme:form-textbox code="anonymous.task.form.label.title" path="title"/>
	<acme:form-textarea code="anonymous.task.form.label.description" path="description"/>
	<acme:form-textbox code="anonymous.task.form.label.url" path="url"/>
	<acme:form-checkbox code="manager.task.form.label.isPrivate" path="isPrivate"/>
	<acme:form-double code="anonymous.task.form.label.workload" path="workload"/>
	<acme:form-textbox code="anonymous.task.form.label.execution_period_initial_date" path="executionPeriod.initialDate"/>
	<acme:form-textbox code="anonymous.task.form.label.execution_period_final_date" path="executionPeriod.finalDate"/>


	<acme:form-return code="anonymous.task.form.button.return"/>
</acme:form>